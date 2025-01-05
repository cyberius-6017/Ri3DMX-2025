package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.PivotConstants;

public class Pivot extends SubsystemBase {

    SparkMax motor1;
    SparkMax motor2;

    ProfiledPIDController pidController;

    RelativeEncoder encoder1;
    RelativeEncoder encoder2;

    AbsoluteEncoder absoluteEncoder;
    static Pivot instance;

    public Pivot() {

        motor1 = new SparkMax(0, MotorType.kBrushless);
        motor2 = new SparkMax(1, MotorType.kBrushless);

        motor1.setInverted(false);
        motor2.setInverted(motor1.getInverted());

        pidController = new ProfiledPIDController(0, 0, 0, new TrapezoidProfile.Constraints(2, 2));

        absoluteEncoder = motor1.getAbsoluteEncoder();

        encoder1 = motor1.getEncoder();
        encoder2 = motor2.getEncoder();

        encoder1.setPosition(0);
        encoder2.setPosition(0);

    }

    public static Pivot getInstance() {
        if (instance == null) {
            instance = new Pivot();
        }
        return instance;
    }

    /**
     * Resets the relative encoder's position to the absolute position of the
     * pivoting arm
     */
    public void resetEncoders() {
        encoder1.setPosition(getAbsolutePosition());
        encoder2.setPosition(getAbsolutePosition());
    }

    /**
     * Gets the absolute position of the pivoting arm
     * 
     * @return the absolute's encoder position
     */
    public double getAbsolutePosition() {
        return absoluteEncoder.getPosition() * PivotConstants.positionConversionFactor;
    }

    /**
     * Gets the motor 1's relative position
     * 
     * @return the encoder 1's position
     */
    public double getEncoder1Position() {
        return encoder1.getPosition() * PivotConstants.positionConversionFactor;
    }

    /**
     * Gets the motor 2's relative position
     * 
     * @return the encoder 2's position
     */
    public double getEncoder2Position() {
        return encoder2.getPosition() * PivotConstants.positionConversionFactor;
    }

    /**
     * Sets the speed for the complete arm
     * 
     * @param speed the percentage output desired speed of the arm
     */
    public void set(double speed) {
        motor1.set(speed);
        motor2.set(speed);
    }

    /**
     * Stops all the motors
     */
    public void stop(){
        motor1.stopMotor();
        motor2.stopMotor();
    }

    /**
     * Pivots the arm to a specific position using a Profiled PID Controller
     * @param goal desired position of the arm
     */
    public void setDesiredPosition(double goal){
        pidController.setGoal(goal);

        double pidOutput = pidController.calculate(getEncoder1Position());

        set(pidOutput);
    }

    
    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }
}

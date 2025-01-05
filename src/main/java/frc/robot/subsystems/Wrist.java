package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.WristConstants;;

public class Wrist extends SubsystemBase {

    SparkMax motor;

    ProfiledPIDController pidController;

    RelativeEncoder encoder;

    AbsoluteEncoder absoluteEncoder;

    static Wrist instance;

    public Wrist() {

        motor = new SparkMax(WristConstants.motorPort, MotorType.kBrushless);

        motor.setInverted(WristConstants.motorInverted);

        pidController = new ProfiledPIDController(
            WristConstants.kP, 
            WristConstants.kI, 
            WristConstants.kD, 
            WristConstants.pidConstraints);

        absoluteEncoder = motor.getAbsoluteEncoder();

        encoder = motor.getEncoder();

        resetEncoders();
    }

    /**
     * Function to make this class a Singleton
     * @return the instance of the class
     */
    public static Wrist getInstance() {
        if (instance == null) {
            instance = new Wrist();
        }
        return instance;
    }

    /**
     * Resets the relative encoder's position to the absolute position of the
     * pivoting arm
     */
    public void resetEncoders() {
        encoder.setPosition(getAbsolutePosition());
    }

    /**
     * Gets the absolute position of the pivoting arm
     * 
     * @return the absolute's encoder position
     */
    public double getAbsolutePosition() {
        return absoluteEncoder.getPosition() * WristConstants.positionConversionFactor;
    }

    /**
     * Gets the motor 1's relative position
     * 
     * @return the encoder 1's position
     */
    public double getEncoder1Position() {
        return encoder.getPosition() * WristConstants.positionConversionFactor;
    }

    /**
     * Sets the speed for the complete arm
     * 
     * @param speed the percentage output desired speed of the arm
     */
    public void set(double speed) {
        motor.set(speed);
    }

    /**
     * Stops all the motors
     */
    public void stop(){
        motor.stopMotor();
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

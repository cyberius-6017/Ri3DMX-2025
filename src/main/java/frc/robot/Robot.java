// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The methods in this class are called automatically corresponding to each
 * mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the
 * package after creating
 * this project, you must also update the Main.java file in the project.
 */
public class Robot extends TimedRobot {

    VictorSPX rightBack = new VictorSPX(40);
    VictorSPX rightFront = new VictorSPX(30);
    VictorSPX leftBack = new VictorSPX(20);
    VictorSPX leftFront = new VictorSPX(10);

    SparkMax wristMotor = new SparkMax(8, MotorType.kBrushless);

    SparkMaxConfig wristMotorConfig = new SparkMaxConfig();

    AbsoluteEncoder wristAbsoluteEncoder;

    SparkClosedLoopController wristPID = wristMotor.getClosedLoopController();

    SparkMax pivotLeft = new SparkMax(5, MotorType.kBrushless);
    SparkMax pivotRight = new SparkMax(6, MotorType.kBrushless);

    SparkMaxConfig pivotRightConfig = new SparkMaxConfig();
    SparkMaxConfig pivotLeftConfig = new SparkMaxConfig();

    AbsoluteEncoder pivotAbsoluteEncoder;

    SparkClosedLoopController pivotPID = pivotLeft.getClosedLoopController();

    SparkMax intakeMotor = new SparkMax(7, MotorType.kBrushless);
    // ProfiledPIDController pid = new ProfiledPIDController(1.8, 0, 1.8 , new
    // TrapezoidProfile.Constraints(6, 5));

    Joystick controlDriver = new Joystick(0);
    Joystick controlPlacer = new Joystick(1);

    /**
     * This function is run when the robot is first started up and should be used
     * for any
     * initialization code.
     */

    public Robot() {

        pivotLeft.clearFaults();
        pivotRight.clearFaults();

        pivotLeftConfig.inverted(true);

        pivotRightConfig.follow(pivotLeft, true);

        pivotLeftConfig.idleMode(IdleMode.kBrake);
        pivotRightConfig.idleMode(IdleMode.kBrake);

        pivotLeftConfig.closedLoop.feedbackSensor(FeedbackSensor.kAbsoluteEncoder)
                .p(0.9)
                .d(0.0005)
                .outputRange(-0.5, 0.5);

        pivotRight.configure(pivotRightConfig, SparkBase.ResetMode.kResetSafeParameters,
                PersistMode.kNoPersistParameters);

        pivotLeft.configure(pivotLeftConfig, SparkBase.ResetMode.kResetSafeParameters,
                PersistMode.kNoPersistParameters);

        wristMotorConfig.closedLoop.feedbackSensor(FeedbackSensor.kAbsoluteEncoder)
                .p(5)
                .d(0.001)

                .outputRange(-0.5, 0.5);

        wristMotor.configure(wristMotorConfig, SparkBase.ResetMode.kResetSafeParameters,
                PersistMode.kNoPersistParameters);

        rightBack.setInverted(false);
        rightFront.setInverted(false);
        leftBack.setInverted(!rightBack.getInverted());
        leftFront.setInverted(!rightBack.getInverted());

        wristAbsoluteEncoder = wristMotor.getAbsoluteEncoder();
        pivotAbsoluteEncoder = pivotLeft.getAbsoluteEncoder();

    }

    @Override
    public void robotPeriodic() {

        SmartDashboard.putBoolean("IS moving", pivotIsMoving);

        SmartDashboard.putNumber("Pivot Encoder", pivotAbsoluteEncoder.getPosition());

        SmartDashboard.putNumber("PID Output", pivotLeft.getAppliedOutput());
        SmartDashboard.putNumber("Pivot Goal", pivotGoal);

        SmartDashboard.putNumber("Wrist Absolute Encoder", wristAbsoluteEncoder.getPosition());
        SmartDashboard.putBoolean("Wrist is moving", wristIsMoving);

        SmartDashboard.putNumber("Wrist Output", wristMotor.getAppliedOutput());
        SmartDashboard.putNumber("Wrist Goal", wristGoal);

    }

    @Override
    public void autonomousInit() {
    }

    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void teleopInit() {

    }

    boolean wristIsMoving = false;
    double wristGoal = 0.5;

    boolean pivotIsMoving = false;
    double pivotGoal = 0.93; // 0.1;

    @Override
    public void teleopPeriodic() {

        // if (control.getRawAxis(1) > 0.1) {
        // wristMotor.set(control.getRawAxis(1) * 0.5);
        // } else {
        // wristMotor.set(control.getRawAxis(5 ) * -0.5);
        // }

        // PIVOT

        // WRIST

        if (controlPlacer.getRawButton(1)) {
            //HOME
            wristIsMoving = true;
            wristGoal = 0.5;
            pivotIsMoving = true;
            pivotGoal = 0.93;
            // pid.setReference(0.25, ControlType.kPosition);
            // wristMotor.set(pid.calculate(0.25));
        } else if (controlPlacer.getRawButton(2)) {
            //PICK
            wristGoal = 0.75;
            wristIsMoving = true;
            pivotIsMoving = true;
            pivotGoal = 0.77;
            // pid.setReference(0.5, ControlType.kPosition);
            // wristMotor.set(pid.calculate(0.25));

        } else if (controlPlacer.getRawButton(3)) {
            //PREPARACION FRONT
            wristGoal = 0.5;
            wristIsMoving = true;
            pivotIsMoving = true;
            pivotGoal = 0.73;// place back 0.62;// preparacion front 0.73;
            // pid.setReference(0.5, ControlType.kPosition);
            // wristMotor.set(pid.calculate(0.25));

        } else if (controlPlacer.getRawButton(7)){
            //PLACE L1
            wristGoal = 0.75;
            wristIsMoving = true;
            pivotIsMoving = true;
            pivotGoal = 0.87;
        } 
        else if (controlPlacer.getRawButton(4)){
            
            //PLACE FRONT
            wristGoal = 0.5;
            wristIsMoving = true;
            pivotIsMoving = true;
            pivotGoal = 0.825; //place front// place back 0.62;// preparacion front 0.73;
        } else if(controlPlacer.getRawButton(9)){
            //PREPARACION BACK
            wristGoal = 0.5;
            wristIsMoving = true;
            pivotIsMoving = true;
            pivotGoal = 0.61;
        } else if(controlPlacer.getRawButton(10)){
            //PLACE BACK
            wristGoal = 0.5;
            wristIsMoving = true;
            pivotIsMoving = true;
            pivotGoal = 0.54;
        }


        if (controlPlacer.getPOV() == 180) {
            wristIsMoving = true;
            pivotIsMoving = true;
            wristGoal = 0.75;
            pivotGoal = 0.92;

            rightBack.set(VictorSPXControlMode.PercentOutput, 0.23);
            rightFront.set(VictorSPXControlMode.PercentOutput, 0.23);
            leftBack.set(VictorSPXControlMode.PercentOutput, 0.23);
            leftFront.set(VictorSPXControlMode.PercentOutput, 0.23);

            // place back 0.56; // front 0.825;
            // pid.setReference(0.5, ControlType.kPosition);
            intakeMotor.set(0.3);
            // wristMotor.set(pid.calculate(0.25));
            
        } else {
            intakeMotor.set(-0.055);

            double speed = controlPlacer.getRawAxis(1) * 0.5;
            double turn = controlPlacer.getRawAxis(4) * 0.5;
            double speedLeft = speed - turn;
            double speedRigt = speed + turn;
            rightBack.set(VictorSPXControlMode.PercentOutput, controlPlacer.getRawAxis(5) * 0.5);
            rightFront.set(VictorSPXControlMode.PercentOutput, controlPlacer.getRawAxis(5) * 0.5);
            leftBack.set(VictorSPXControlMode.PercentOutput, controlPlacer.getRawAxis(1) * 0.5);
            leftFront.set(VictorSPXControlMode.PercentOutput, controlPlacer.getRawAxis(1) * 0.5);
        }
        // else {
        // wristMotor.set(control.getRawAxis(1) * 0.4);
        // wristIsMoving = false;
        // }
        wristPID.setReference(wristGoal, ControlType.kPosition);

        pivotPID.setReference(pivotGoal, ControlType.kPosition);
        // pivotLeft.set(control.getRawAxis(5) * 0.4);
        // pivotLeft.set(-control.getRawAxis(5) * 0.4);

        // Intake
        if (controlPlacer.getRawButton(5)) {
            intakeMotor.set(-0.4);
        } else if (controlPlacer.getRawButton(6)) {
            intakeMotor.set(0.2);
        } else {
            intakeMotor.set(-0.055);
        }

    }

    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
    }

    @Override
    public void testInit() {
    }

    @Override
    public void testPeriodic() {
    }

    @Override
    public void simulationInit() {
    }

    @Override
    public void simulationPeriodic() {
    }
}

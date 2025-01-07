// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The methods in this class are called automatically corresponding to each
 * mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the
 * package after creating
 * this project, you must also update the Main.java file in the project.
 */
public class Robot extends TimedRobot {

    VictorSPX rightBack = new VictorSPX(10);
    VictorSPX rightFront = new VictorSPX(20);
    VictorSPX leftBack = new VictorSPX(30);
    VictorSPX leftFront = new VictorSPX(40);

    SparkMax wristMotor = new SparkMax(8, MotorType.kBrushless);

    SparkMaxConfig wristMotorConfig = new SparkMaxConfig();

    AbsoluteEncoder wristAbsoluteEncoder;

    SparkClosedLoopController wristPID = wristMotor.getClosedLoopController();

    SparkMax pivotLeft = new SparkMax(5, MotorType.kBrushless);
    SparkMax pivotRight = new SparkMax(6, MotorType.kBrushless);
    SparkMaxConfig pivotRightConfig = new SparkMaxConfig();
    SparkMaxConfig pivotLeftConfig = new SparkMaxConfig();

    AbsoluteEncoder pivotAbsoluteEncoder = pivotLeft.getAbsoluteEncoder();

    SparkClosedLoopController pivotPID = pivotLeft.getClosedLoopController();

    SparkMax intakeMotor = new SparkMax(7, MotorType.kBrushless);
    // ProfiledPIDController pid = new ProfiledPIDController(1.8, 0, 1.8 , new
    // TrapezoidProfile.Constraints(6, 5));

    Joystick control = new Joystick(0);

    /**
     * This function is run when the robot is first started up and should be used
     * for any
     * initialization code.
     */

    public Robot() {

        pivotLeftConfig.inverted(true);

        pivotRightConfig.follow(pivotLeft, true);

        pivotLeftConfig.idleMode(IdleMode.kBrake);
        pivotRightConfig.idleMode(IdleMode.kBrake);

        pivotLeftConfig.closedLoop.feedbackSensor(FeedbackSensor.kAbsoluteEncoder)
                .p(0.0)
                .d(0.0)
                .outputRange(-0.5, -0.5);

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

        rightBack.setInverted(leftBack.getInverted());
        rightFront.setInverted(leftBack.getInverted());
        leftBack.setInverted(true);
        leftFront.setInverted(true);

        wristAbsoluteEncoder = wristMotor.getAbsoluteEncoder();

    }

    @Override
    public void robotPeriodic() {
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
    double wristGoal = 0.25;
    
    boolean pivotIsMoving = false;
    double pivotGoal = 0.25;
    

    @Override
    public void teleopPeriodic() {
        // rightBack.set(VictorSPXControlMode.PercentOutput, control.getRawAxis(5)*0.5);
        // rightFront.set(VictorSPXControlMode.PercentOutput,
        // control.getRawAxis(5)*0.5);
        // leftBack.set(VictorSPXControlMode.PercentOutput, control.getRawAxis(1)*0.5);
        // leftFront.set(VictorSPXControlMode.PercentOutput, control.getRawAxis(1)*0.5);

        // if (control.getRawAxis(1) > 0.1) {
        // wristMotor.set(control.getRawAxis(1) * 0.5);
        // } else {
        // wristMotor.set(control.getRawAxis(5 ) * -0.5);
        // }

        // PIVOT
        if (control.getRawButton(1)) {

            pivotIsMoving = true;
            pivotGoal = 0.25;
            pivotPID.setReference(0.25, ControlType.kPosition);
            // wristMotor.set(pid.calculate(0.25));
        } else if (control.getRawButton(2)) {
            pivotIsMoving = true;
            pivotGoal = 0.5;
            pivotPID.setReference(0.5, ControlType.kPosition);
            // wristMotor.set(pid.calculate(0.25));

        } else {
            // pivotRight.set(control.getRawAxis(1) * 0.4);
            pivotIsMoving = false;
        }
        // pivotLeft.set(control.getRawAxis(5) * 0.4);
        // pivotLeft.set(-control.getRawAxis(5) * 0.4);

        // Intake
        // if (control.getRawButton(5)) {
        // intakeMotor.set(-0.35);
        // } else if (control.getRawButton(6)) {
        // intakeMotor.set(0.3);
        // } else {
        // intakeMotor.set(-0.05);
        // }

        // WRIST

        SmartDashboard.putNumber("Wrist Absolute Encoder", wristAbsoluteEncoder.getPosition());
        SmartDashboard.putBoolean("IS moving", wristIsMoving);

        SmartDashboard.putNumber("Pivot Encoder", pivotAbsoluteEncoder.getPosition());

        SmartDashboard.putNumber("PID Output", wristMotor.getAppliedOutput());
        SmartDashboard.putNumber("Goal", wristGoal);

        // if (control.getRawButton(1)) {

        // isMoving = true;
        // goal = 0.25;
        // // pid.setReference(0.25, ControlType.kPosition);
        // // wristMotor.set(pid.calculate(0.25));
        // } else if (control.getRawButton(2)) {
        // goal = 0.5;
        // isMoving = true;
        // // pid.setReference(0.5, ControlType.kPosition);
        // // wristMotor.set(pid.calculate(0.25));

        // } else {
        // wristMotor.set(control.getRawAxis(1) * 0.4);
        // isMoving = false;
        // }
        // pid.setReference(goal, ControlType.kPosition);
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

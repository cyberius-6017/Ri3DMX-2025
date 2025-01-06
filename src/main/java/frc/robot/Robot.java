// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

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
    // SparkMax intakeMotor = new SparkMax(7, MotorType.kBrushless);

    AbsoluteEncoder absoluteEncoder;

    RelativeEncoder relativeEncoder;

    Joystick control = new Joystick(0);

    /**
     * This function is run when the robot is first started up and should be used
     * for any
     * initialization code.
     */
    public Robot() {
        rightBack.setInverted(leftBack.getInverted());
        rightFront.setInverted(leftBack.getInverted());
        leftBack.setInverted(true);
        leftFront.setInverted(true);

        // absoluteEncoder = wristMotor.getAbsoluteEncoder();
        // relativeEncoder = wristMotor.getEncoder();
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

    @Override
    public void teleopPeriodic() {
        // rightBack.set(VictorSPXControlMode.PercentOutput, control.getRawAxis(5)*0.5);
        // rightFront.set(VictorSPXControlMode.PercentOutput,
        // control.getRawAxis(5)*0.5);
        // leftBack.set(VictorSPXControlMode.PercentOutput, control.getRawAxis(1)*0.5);
        // leftFront.set(VictorSPXControlMode.PercentOutput, control.getRawAxis(1)*0.5);

        // if (control.getRawAxis(1) > 0.1) {
        //     wristMotor.set(control.getRawAxis(1) * 0.5);
        // } else {
        //     wristMotor.set(control.getRawAxis(5 ) * -0.5);
        // }
        int intake = 1;

<<<<<<< HEAD
        if (control.getRawButton(6)){
          intake = -1;
        } else {
          intake = 1;
        }
        if(control.getRawButton(1)){
            intakeMotor.set(0.2 * intake);
        } else if(control.getRawButton(2)){
            intakeMotor.set(0.3 * intake);
        } else if(control.getRawButton(3)){
            intakeMotor.set(0.4 * intake);
        } else if(control.getRawButton(4)){
            intakeMotor.set(0.5 * intake);
        } else if(control.getRawButton(5)){
            intakeMotor.set(0.6 * intake);
        } else {
            intakeMotor.set(-0.05);
        }
=======
        //Intake
        // if(control.getRawButton(1)){
        //     intakeMotor.set(0.2);
        // } else if(control.getRawButton(2)){
        //     intakeMotor.set(0.3);
        // } else if(control.getRawButton(3)){
        //     intakeMotor.set(0.4);
        // } else if(control.getRawButton(4)){
        //     intakeMotor.set(0.5);
        // } else if(control.getRawButton(5)){
        //     intakeMotor.set(0.6);
        // } else {
        //     intakeMotor.set(0);
        // }

        //WRIST
        wristMotor.set(control.getRawAxis(1) * 0.4);
>>>>>>> cd2db014bfde31e7a860b6e2d4facfcb932c12ab

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

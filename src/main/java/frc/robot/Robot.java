// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;

/**
 * The methods in this class are called automatically corresponding to each mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the Main.java file in the project.
 */
public class Robot extends TimedRobot {

  VictorSPX rightBack = new VictorSPX(10);
  VictorSPX rightFront = new VictorSPX(20);
  VictorSPX leftBack = new VictorSPX(30);
  VictorSPX leftFront = new VictorSPX(40);

  Joystick control = new Joystick(0);

  
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  public Robot() {
    rightBack.setInverted(leftBack.getInverted());
    rightFront.setInverted(leftBack.getInverted());
    leftBack.setInverted(true);
    leftFront.setInverted(true);
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {

  }

  @Override
  public void teleopPeriodic() {
    rightBack.set(VictorSPXControlMode.PercentOutput, control.getRawAxis(5)*0.5);
    rightFront.set(VictorSPXControlMode.PercentOutput, control.getRawAxis(5)*0.5);
    leftBack.set(VictorSPXControlMode.PercentOutput, control.getRawAxis(1)*0.5);
    leftFront.set(VictorSPXControlMode.PercentOutput, control.getRawAxis(1)*0.5);
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}

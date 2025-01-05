// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.PivotPercentageCommand;
import frc.robot.constants.IOConstants;
import frc.robot.constants.PivotConstants;
import frc.robot.subsystems.Pivot;

public class RobotContainer {

  private final Joystick driverJoystick;
  private final Joystick placerJoystick;

  private final Pivot pivot;

  public RobotContainer() {

    pivot = Pivot.getInstance();

    driverJoystick = new Joystick(IOConstants.driverJoystickPort);
    placerJoystick = new Joystick(IOConstants.placerJoystickPort);

    configureBindings();

  }

  private void configureBindings() {

    pivot.setDefaultCommand(
        new PivotPercentageCommand(
            pivot,
            () -> placerJoystick.getRawAxis(0) * PivotConstants.maxPercentageOutput));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveTrainCommand;
import frc.robot.commands.IntakeControlCommand;
import frc.robot.commands.PivotPercentageCommand;
import frc.robot.commands.WristPercentageCommand;
import frc.robot.constants.IOConstants;
import frc.robot.constants.IntakeConstants;
import frc.robot.constants.PivotConstants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Pivot;
import frc.robot.subsystems.Wrist;
import frc.robot.utils.GamepadAxisButton;

public class RobotContainer {

  private final XboxController driverJoystick;
  private final XboxController placerJoystick;

  private final Pivot pivot;
  private final Intake intake;
  private final Wrist wrist;
  private final DriveTrain drivetrain;

  public RobotContainer() {

    pivot = Pivot.getInstance();
    intake = Intake.getInstance();
    wrist = Wrist.getInstance();
    drivetrain = DriveTrain.getInstance();
  

    driverJoystick = new XboxController(IOConstants.driverJoystickPort);
    placerJoystick = new XboxController(IOConstants.placerJoystickPort);
    
    drivetrain.setDefaultCommand(
      new DriveTrainCommand(drivetrain,() -> driverJoystick.getRawAxis(1), () -> driverJoystick.getRawAxis(5)));

    configureBindings();
  }

  private void configureBindings() {

    
    /* 
      pivot.setDefaultCommand(
        new PivotPercentageCommand(
            pivot,
            () -> placerJoystick.getRawAxis(0) * PivotConstants.maxPercentageOutput));
    
    wrist.setDefaultCommand(
      new WristPercentageCommand(
        wrist, 
        () -> placerJoystick.getRawAxis(0))
    );
    */
   // new JoystickButton(driverJoystick, XboxController.Button.kA.value).onTrue(new InstantCommand(drivetrain::switchInverter));
    /* 
    new GamepadAxisButton(this::leftTriggerThresholdSupplier)
        .whileTrue(
            new IntakeControlCommand(
                intake,
                () -> placerJoystick.getRawAxis(IOConstants.leftTrigger),
                true));
    
                new GamepadAxisButton(this::rightTriggerThresholdSupplier)
      .whileTrue(
        new IntakeControlCommand(
          intake, 
          () -> placerJoystick.getRawAxis(IOConstants.rightTrigger), 
          false)
      );*/
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }

  public Boolean leftTriggerThresholdSupplier() {
    return Math.abs(placerJoystick.getRawAxis(IOConstants.leftTrigger)) > IntakeConstants.joystickThreshold;
  }

  public Boolean rightTriggerThresholdSupplier(){
    return Math.abs(placerJoystick.getRawAxis(IOConstants.rightTrigger)) > IntakeConstants.joystickThreshold;
  }
}

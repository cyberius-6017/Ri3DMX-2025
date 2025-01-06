package frc.robot.commands.automated;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Pivot;
import frc.robot.subsystems.Wrist;
import frc.robot.utils.ArmPositions;
import frc.robot.utils.PositionConstraints;

public class PIDPositionsCommand extends Command {
  private final Pivot pivot;
  private final Wrist wrist;
  private final PositionConstraints constraints;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public PIDPositionsCommand(Pivot pivot, Wrist wrist, ArmPositions armPositions) {
    this.pivot = pivot;
    this.wrist = wrist;
    this.constraints = new PositionConstraints(armPositions, DriveTrain.getInstance().isInverted());

    addRequirements(pivot, wrist);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    pivot.setDesiredPosition(constraints.pivotPosition);
    wrist.setDesiredPosition(constraints.wristPosition);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

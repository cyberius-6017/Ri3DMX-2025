package frc.robot.commands.automated;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Pivot;
import frc.robot.subsystems.Wrist;

public class PIDPositionsCommand extends Command {
  private final Pivot pivot;
  private final Wrist wrist;


  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public PIDPositionsCommand(Pivot pivot, Wrist wrist) {
    this.pivot = pivot;
    this.wrist = wrist;
    addRequirements(pivot, wrist);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
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

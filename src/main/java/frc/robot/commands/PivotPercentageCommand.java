package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Pivot;

public class PivotPercentageCommand extends Command {
  private final Pivot pivot;
  private Supplier<Double> speed;
  /**
   * Creates a new Pivot Command controlled by Percentage Output
   *
   * @param subsystem The subsystem used by this command.
   */
  public PivotPercentageCommand(Pivot subsystem, Supplier<Double> speed) {
    this.speed = speed;
    pivot = subsystem;
    
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
    
  }

  @Override
  public void execute() {
    pivot.set(speed.get());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    pivot.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

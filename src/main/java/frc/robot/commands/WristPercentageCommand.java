package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Wrist;

public class WristPercentageCommand extends Command {

    private final Wrist wrist;

    private final Supplier<Double> speedSupplier;
  /**
   * Creates a new WristPercentageCommand.
   *
   * @param wrist The wrist subsystem used by this command.
   */
  public WristPercentageCommand(Wrist wrist, Supplier<Double> speedSupplier) {
    
    this.wrist = wrist;
    this.speedSupplier = speedSupplier;

    addRequirements(wrist);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    wrist.set(speedSupplier.get());
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

package frc.robot.commands;
import frc.robot.constants.IntakeConstants;
import frc.robot.subsystems.Intake;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;



public class IntakeControlCommand extends Command{
    
    private final Intake intakeSubsystem;
    private final Supplier<Double> speedSupplier;
    private final Boolean intake;
    
    public IntakeControlCommand(Intake intakeSubsystem, Supplier<Double> speed, Boolean intake) {
        this.intakeSubsystem = intakeSubsystem;
        this.speedSupplier = speed;
        this.intake = intake;
    }

    @Override
    public void initialize(){
        intakeSubsystem.stopIntake();
    }

    @Override
    public void execute(){
        //Left trigger for reverse intake
        double speed = speedSupplier.get();
        double out_speed = intake ? speed : -1 * speed;

        intakeSubsystem.runIntake(out_speed);

    }

    @Override
    public void end(boolean interrupted){
        intakeSubsystem.stopIntake();
    }

    @Override
    public boolean isFinished(){
        return false;
    }


}

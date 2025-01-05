package frc.robot.commands;
import frc.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;



public class IntakeControlCommand extends Command{

    private final Intake intakeSubsystem;
    private final XboxController controller;
    
    public IntakeControlCommand(Intake intakeSubsystem, XboxController controller) {
        this.intakeSubsystem = intakeSubsystem;
        this.controller =  controller;
    }

    public void initialize(){
        intakeSubsystem.stopIntake();
    }


    public void execute(){
        //Left trigger for reverse intake

        double leftTrigger = controller.getLeftTriggerAxis();
        double rightTrigger =  controller.getRightTriggerAxis(); 

        if(leftTrigger > 0.1){
            intakeSubsystem.runIntake(-leftTrigger);
        }
        else if(rightTrigger > 0.1){
            intakeSubsystem.runIntake(rightTrigger);
        }
        else{
            intakeSubsystem.stopIntake();
        }
    }

    public void end(boolean interrupted){
        intakeSubsystem.stopIntake();
    }

    public boolean isFinished(){
        return false;
    }


}

package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.Command;

public class DriveTrainCommand extends Command {
    private final DriveTrain driveTrainSubsystem;
    private final  double leftSpeed;
    private final  double rightSpeed;

    public DriveTrainCommand(){
        this.driveTrainSubsystem =  driveTrainSubsystem;
        this.rightSpeed = rightSpeed;
        this.leftSpeed = leftSpeed;
    }

    public void initialize(){
    }

    public void execute(){
        driveTrainSubsystem.tankDrive(leftSpeed, rightSpeed);
    }

    public void end(boolean interrupted){
        driveTrainSubsystem.tankDrive(0, 0);
    }

    public boolean isFinished(){
        return false;
    }
}


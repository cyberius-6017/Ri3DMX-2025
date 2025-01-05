package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;

public class DriveTrainCommand extends Command {
    private final DriveTrain driveTrainSubsystem;
    private final  Supplier<Double> leftSpeedSupplier, rightSpeedSupplier;

    public DriveTrainCommand(DriveTrain driveTrainSubsystem, Supplier<Double> leftSpeedSupplier, Supplier<Double> rightSpeedSupplier){
        this.driveTrainSubsystem =  driveTrainSubsystem;
        this.rightSpeedSupplier = rightSpeedSupplier;
        this.leftSpeedSupplier = leftSpeedSupplier;
    }

    public void initialize(){
    }

    public void execute(){
        driveTrainSubsystem.tankDrive(leftSpeedSupplier.get(), rightSpeedSupplier.get());
    }

    public void end(boolean interrupted){
        driveTrainSubsystem.tankDrive(0, 0);
    }

    public boolean isFinished(){
        return false;
    }
}


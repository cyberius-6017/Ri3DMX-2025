package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants;




public class DriveTrain extends SubsystemBase {

    private final VictorSP rightBack = new VictorSP(constants.DrivetrainConstants.rightBackMotor);
    private final VictorSP rightFront = new VictorSP(constants.DrivetrainConstants.rightFrontMotor);
    private final VictorSP leftBack = new VictorSP(constants.DrivetrainConstants.leftBackMotor) ;
    private final VictorSP leftFront  = new VictorSP(constants.DrivetrainConstants.leftFrontMotor);
    private final DifferentialDrive drivetrain;

    private boolean isInverted = false;

    

    public DriveTrain() {

        rightBack.setInverted(true);
        rightFront.setInverted(true);
        leftBack.setInverted(rightBack.getInverted());
        leftFront.setInverted(rightBack.getInverted());

        drivetrain = new DifferentialDrive(leftFront, rightFront);

    } 
    
    public void tankDrive(double forward, double rotation){
        if (isInverted) {
            forward = - forward;
            rotation = - rotation;
            
        }
        
        drivetrain.arcadeDrive(forward, rotation);

        leftBack.set(leftFront.get());
        rightBack.set(rightFront.get());
    }

    public void switchInverter(){
        isInverted = !isInverted;   
    }

    public boolean isInverted(){
        return isInverted;
    }
    
    public void periodic(){
    }

}
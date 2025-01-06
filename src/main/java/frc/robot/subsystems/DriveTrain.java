package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants;




public class DriveTrain extends SubsystemBase {

    private final VictorSPX rightBack = new VictorSPX(constants.DrivetrainConstants.rightBackMotor);
    private final VictorSPX rightFront = new VictorSPX(constants.DrivetrainConstants.rightFrontMotor);
    private final VictorSPX leftBack = new VictorSPX(constants.DrivetrainConstants.leftBackMotor) ;
    private final VictorSPX leftFront  = new VictorSPX(constants.DrivetrainConstants.leftFrontMotor);

    private boolean isInverted = false;

    public static DriveTrain instance;
    

    public DriveTrain() {

        rightBack.setInverted(true);
        rightFront.setInverted(true);
        leftBack.setInverted(rightBack.getInverted());
        leftFront.setInverted(rightBack.getInverted());

        

    } 

    public static DriveTrain getInstance(){
        if(instance == null){
            instance = new DriveTrain();
        }
        return instance;
    }
    
    public void tankDrive(double left, double right){
        if (isInverted) {
            left = - left;
            right = - right;
            
        }
        
        leftFront.set(VictorSPXControlMode.PercentOutput, 0.3);
        rightFront.set(VictorSPXControlMode.PercentOutput, 0.3);

        leftBack.set(VictorSPXControlMode.PercentOutput, 0.3);
        rightBack.set(VictorSPXControlMode.PercentOutput, 0.3);
        
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
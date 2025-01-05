package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;




public class DriveTrain extends SubsystemBase {

    private final VictorSP rightBack;
    private final VictorSP rightFront;
    private final VictorSP leftBack;
    private final VictorSP leftFront;
    

    public DriveTrain() {

        rightBack  = new VictorSP(10);
        rightFront = new VictorSP(20);
        leftBack = new VictorSP(30);
        leftFront = new VictorSP(40);

        rightBack.setInverted(leftBack.getInverted());
        rightFront.setInverted(leftBack.getInverted());
        leftBack.setInverted(true);
        leftFront.setInverted(true);
    } 
    
    public void tankDrive(double leftSpeed, double rightSpeed){
        leftFront.set(leftSpeed);
        leftBack.set(leftSpeed);
        rightFront.set(rightSpeed);
        rightBack.set(rightSpeed);
    }

    public void periodic(){
    }

}
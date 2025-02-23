package frc.robot.subsystems;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class Intake extends SubsystemBase {
    private SparkMax intakeMotor;
    private int intakeID = 1;

    public static Intake instance;

    public Intake(){

        intakeMotor = new SparkMax(intakeID, MotorType.kBrushless);
    }

    public static Intake getInstance(){
        if (instance == null){
            instance = new Intake();
        }
        return instance;
    }
    public void runIntake(double speed){
        intakeMotor.set(speed);
    }

    public void stopIntake(){
        intakeMotor.set(0);
    }

}
package frc.robot;

import edu.wpi.first.math.trajectory.TrapezoidProfile;


public class constants {


    public static final class PivotConstants{

        public static final double positionConversionFactor = Math.PI * 2;

        public static final double maxPercentageOutput = 0.3;

        public static final int motor1Port = 5;
        public static final int motor2Port = 6;

        public static final boolean motor1Inverted = false;

        public static final double maxVelocity = 2;
        public static final double maxAcceleration = 2;
        
        public static final TrapezoidProfile.Constraints pidConstraints = new TrapezoidProfile.Constraints(maxVelocity, maxAcceleration);

        public static final double kP = 0.0;
        public static final double kI = 0.0;
        public static final double kD = 0.0;

        public static final double homePosition = 0.0;

        public static final double l1FrontPosition = 0.0;
        public static final double l1BackPosition = 0.0;
        
        public static final double l2FrontPosition = 0.0;
        public static final double l2BackPosition = 0.0;

        public static final double l3FrontPosition = 0.0;
        public static final double l3BackPosition = 0.0;

        public static final double coralPickFrontPosition = 0.0;
        public static final double coralPickBackPosition = 0.0;

        public static final double algaePickPosition = 0.0;

    }
    public static final class DrivetrainConstants{
        public static final int rightBackMotor = 40;
        public static final int rightFrontMotor = 30;
        public static final int leftBackMotor = 20;
        public static final int leftFrontMotor = 10;
    }
    public static final class IntakeConstants{

        public static final double motorPort = 8;

        public static final double joystickThreshold = 0.1;

        public static final boolean motorInverted = false;
    }

    public static final class IOConstants {

        public static final int driverJoystickPort = 0;
        public static final int placerJoystickPort = 1;
        
        public static final int leftTrigger = 2;
        public static final int rightTrigger = 3; 

    }

    public static final class WristConstants {

        public static final int motorPort = 9;

        public static final boolean motorInverted = false;
        public static final double maxVelocity = 2;
        public static final double maxAcceleration = 2;

        public static final TrapezoidProfile.Constraints pidConstraints = new TrapezoidProfile.Constraints(maxVelocity, maxAcceleration);

        public static final double kP = 0.0;
        public static final double kI = 0.0;
        public static final double kD = 0.0;

        public static final double positionConversionFactor = 0.0;

        public static final double l1FrontPosition = 0.0;
        public static final double l1BackPosition = 0.0;
        
        public static final double l2FrontPosition = 0.0;
        public static final double l2BackPosition = 0.0;

        public static final double l3FrontPosition = 0.0;
        public static final double l3BackPosition = 0.0;

        public static final double coralPickFrontPosition = 0.0;
        public static final double coralPickBackPosition = 0.0;

        public static final double algaePickPosition = 0.0;

        public static final double homePosition = 0.0;



        
    }

    
}

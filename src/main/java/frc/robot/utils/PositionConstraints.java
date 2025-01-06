package frc.robot.utils;

import frc.robot.constants.PivotConstants;
import frc.robot.constants.WristConstants;
import frc.robot.subsystems.Wrist;

public class PositionConstraints {

    public double pivotPosition;

    public double wristPosition;

    public boolean isIntaking;

    public final boolean isFrontOriented;

    public PositionConstraints(ArmPositions position, boolean isFrontOriented) {
        this.isFrontOriented = isFrontOriented;
        switch (position) {

            case PLACE_L1:
                this.pivotPosition = isFrontOriented ? PivotConstants.l1FrontPosition : PivotConstants.l1BackPosition;

                this.wristPosition = isFrontOriented ? WristConstants.l1FrontPosition : WristConstants.l1BackPosition;

                this.isIntaking = false;

                break;

            case PLACE_L2:
                this.pivotPosition = isFrontOriented ? PivotConstants.l2FrontPosition : PivotConstants.l2BackPosition;

                this.wristPosition = isFrontOriented ? WristConstants.l2FrontPosition : WristConstants.l2BackPosition;

                this.isIntaking = false;

                break;
            case PLACE_L3:
                this.pivotPosition = isFrontOriented ? PivotConstants.l3FrontPosition : PivotConstants.l3BackPosition;

                this.wristPosition = isFrontOriented ? WristConstants.l3FrontPosition : WristConstants.l3BackPosition;

                this.isIntaking = false;

                break;
            case CORAL_PICK_POSITION:
                this.pivotPosition = isFrontOriented ? PivotConstants.coralPickFrontPosition : PivotConstants.coralPickBackPosition;

                this.wristPosition = isFrontOriented ? WristConstants.coralPickFrontPosition : WristConstants.coralPickBackPosition;

                this.isIntaking = true;

                break;
            case ALGAE_PICK_POSITION:
                this.pivotPosition = PivotConstants.algaePickPosition;

                this.wristPosition = WristConstants.algaePickPosition;

                this.isIntaking = false;

                break;

            default:
                this.pivotPosition = isFrontOriented ? PivotConstants.homePosition : PivotConstants.homePosition;

                this.wristPosition = isFrontOriented ? WristConstants.homePosition : WristConstants.homePosition;

                this.isIntaking = false;

                break;
        }

    }
}

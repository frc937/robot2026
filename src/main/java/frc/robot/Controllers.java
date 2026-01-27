package frc.robot;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class Controllers {
   
    /** Xbox controller used by the pilot, who is the student that controls the drivetrain */
  public static CommandXboxController pilotController =
      new CommandXboxController(Constants.Controllers.PILOT_CONTROLLER_PORT);

  /**
   * Xbox controller used by the operator, who is the student who controls all mechanisms other than
   * the drivetrain
   */
  public static CommandXboxController operatorController =
      new CommandXboxController(Constants.Controllers.OPERATOR_CONTROLLER_PORT);

      private static void configureDefaultKeybinds() {
        operatorController.leftBumper().whileTrue(RobotContainer.runShooter);
        }

      private static void configureOperatorlessKeybinds() {
        pilotController.leftBumper().whileTrue(RobotContainer.runShooter);
        }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.io.File;
import java.nio.file.FileSystem;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.Controllers;
import frc.robot.command.Drive;
import frc.robot.subsystem.Shooter;
import frc.robot.subsystem.SwerveSubsystem;

/** Singleton class that contains all the robot's subsystems, commmands, and button bindings. */
public class RobotContainer {

  /**Singleton instance of the Driver controller for the whole robot. */
  public static CommandXboxController pilotController = new CommandXboxController(Controllers.PILOT_CONTROLLER_PORT);

   /**Singleton instance of the Operator controller for the whole robot. */
  public static CommandXboxController operatorController = new CommandXboxController(Controllers.OPERATOR_CONTROLLER_PORT);
  
  /*
   * **************
   * * SUBSYSTEMS *
   * **************
   */
  
   /** Singleton instance of the {@link Shooter} for the whole robot */
   public static Shooter shooter = new Shooter();

   public static SwerveSubsystem driveBase = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(), "swerve"));

  /*
   * ************
   * * COMMANDS *
   * ************
   */

  public static Drive driveRobotOriented = new Drive(driveBase, pilotController, false);

  public static Drive driveFieldOrented = new Drive(driveBase, pilotController, true);

  /*
   * ***********************
   * * OTHER INSTANCE VARS *
   * ***********************
   */

   
   /** Constructer for the {@link RobotContainer} */
  public RobotContainer() {
    configureBindings();

    driveBase.setDefaultCommand(driveRobotOriented);
  }
  
  private void configureBindings() {
    operatorController.leftBumper().whileTrue(Commands.runOnce(shooter::runIntake, shooter));
    operatorController.rightBumper().whileTrue(Commands.runOnce(shooter::runShooter, shooter));

  }
/**Get the current autonomus command.
 * 
 * @return The current autonomus command.
 */
  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
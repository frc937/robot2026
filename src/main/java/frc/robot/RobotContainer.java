// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.io.File;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.Controllers;
import frc.robot.command.DriveRobot;
import frc.robot.command.ToggleFieldRelativity;
import frc.robot.subsystem.Drive;
import frc.robot.subsystem.Shooter;


/** Singleton class that contains all the robot's subsystems, commmands, and button bindings. */
public class RobotContainer {

  /*
   * ***********************
   * * OTHER INSTANCE VARS *
   * ***********************
   */
  
   /**Singleton instance of the Driver controller for the whole robot. */
  public static CommandXboxController pilotController = new CommandXboxController(Controllers.PILOT_CONTROLLER_PORT);

   /**Singleton instance of the Operator controller for the whole robot. */
  public static CommandXboxController operatorController = new CommandXboxController(Controllers.OPERATOR_CONTROLLER_PORT);
   



  /*
   * **************
   * * SUBSYSTEMS *
   * **************
   */


  /** Singleton instance of the {@link Drive} for the whole robot. */
  public static Drive drivebase = new Drive(new File(Filesystem.getDeployDirectory(), "swerve"));


  /** Singleton instance of the {@link Shooter} for the whole robot. */
  public static Shooter shooter = new Shooter();



  /*
   * ************
   * * COMMANDS *
   * ************
   */

  /** Singleton instance of the robot oriented {@link DriveRobot} command for the whole robot. */
  public static DriveRobot driveRobotOriented = new DriveRobot(drivebase, pilotController, false);

  /** Singleton instance of the field oriented {@link DriveRobot} command for the whole robot. */
  public static DriveRobot driveFieldOriented = new DriveRobot(drivebase, pilotController, true);



  /** Singleton instance of the field oriented {@link ToggleFieldRelativity} command for the whole robot */
  public static ToggleFieldRelativity toggleFieldRelativity = new ToggleFieldRelativity(
    drivebase, 
    driveRobotOriented,
    driveFieldOriented
  );






  /** Constructer for the {@link RobotContainer} */
  public RobotContainer() {
    configureBindings();

    drivebase.setDefaultCommand(driveRobotOriented);
  }
  

  private void configureBindings() {
    /* Operator bindings. */
    operatorController.leftTrigger().whileTrue(Commands.run(shooter::runIntake, shooter).finallyDo(shooter::stop));
    operatorController.rightTrigger().whileTrue(Commands.run(shooter::runShooter, shooter).finallyDo(shooter::stop));
    operatorController.b().whileTrue(Commands.run(shooter::runIntakeReverse, shooter).finallyDo(shooter::stop));

    /* Pilot bindings. */
    pilotController.b().whileTrue(Commands.run(drivebase::lock, drivebase));
    pilotController.leftStick().onTrue(toggleFieldRelativity);
    
  }

  /**
  * Get the current autonomus command.
  * 
  * @return The current autonomus command.
  */
  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
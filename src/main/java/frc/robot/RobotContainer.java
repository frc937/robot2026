// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.io.File;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.Controllers;
import frc.robot.ControllerUtil.ControllerAxis;
import frc.robot.commands.RunShooter;
import frc.robot.commands.RunShooterReverse;
import frc.robot.commands.drive.DriveRobot;
import frc.robot.subsystem.Drive;
import frc.robot.subsystem.Shooter;

public class RobotContainer {

  final static CommandXboxController pilotController = new CommandXboxController(Controllers.PILOT_CONTROLLER_PORT);
  final static CommandXboxController operatorController = new CommandXboxController(Controllers.OPERATOR_CONTROLLER_PORT);
  
  /*
   * **************
   * * SUBSYSTEMS *
   * **************
   */
  
   /** Singleton instance of the {@link Shooter} for the whole robot */
   public static Shooter shooter = new Shooter();

   public static Drive drive = new Drive(new File(Filesystem.getDeployDirectory(), "swerve"));

  /*
   * ************
   * * COMMANDS *
   * ************
   */

   /** Singleton instance of the {@link RunShooter} for the whole robot */
   public static RunShooter runShooter = new RunShooter(shooter);

   public static RunShooterReverse runShooterReverse = new RunShooterReverse(shooter);

   public static DriveRobot driveRobotFieldOriented = new DriveRobot(
    drive,
    ControllerUtil.getControllerAxisSupplier(
      pilotController, ControllerAxis.LeftY),
    ControllerUtil.getControllerAxisSupplier(
      pilotController, ControllerAxis.LeftX), 
    ControllerUtil.getControllerAxisSupplier(
      pilotController, ControllerAxis.RightX), 
    true);
  /*
   * ***********************
   * * OTHER INSTANCE VARS *
   * ***********************
   */
  

  public RobotContainer() {
    configureBindings();
  }
  
  private void configureBindings() {
    operatorController.leftBumper().whileTrue(runShooter);
    operatorController.rightBumper().whileTrue(runShooterReverse);
    
    drive.setDefaultCommand(driveRobotFieldOriented);
  }

  


  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.Controllers;
import frc.robot.commands.RunShooter;
import frc.robot.commands.RunShooterReverse;
import frc.robot.subsystem.Shooter;

public class RobotContainer {
  
  /*
   * **************
   * * SUBSYSTEMS *
   * **************
   */
  
   /** Singleton instance of the {@link Shooter} for the whole robot */
   public static Shooter shooter = new Shooter();

  /*
   * ************
   * * COMMANDS *
   * ************
   */

   /** Singleton instance of the {@link RunShooter} for the whole robot */
   public static RunShooter runShooter = new RunShooter(shooter);

   public static RunShooterReverse runShooterReverse = new RunShooterReverse(shooter);
  /*
   * ***********************
   * * OTHER INSTANCE VARS *
   * ***********************
   */
  public RobotContainer() {
    configureBindings();
  }
  public static CommandXboxController pilotController = new CommandXboxController(Controllers.PILOT_CONTROLLER_PORT);

  public static CommandXboxController operatorController = new CommandXboxController(Controllers.OPERATOR_CONTROLLER_PORT);
  private void configureBindings() {
    operatorController.leftBumper().whileTrue(runShooter);
    operatorController.rightBumper().whileTrue(runShooterReverse);

  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}

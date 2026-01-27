// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.RunShooter;
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
   public static RunShooter runShooter = new RunShooter();

  /*
   * ***********************
   * * OTHER INSTANCE VARS *
   * ***********************
   */
  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}

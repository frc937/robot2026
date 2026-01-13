// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.DriveRobot;
import frc.robot.subsystems.Drive;

public class RobotContainer {
  
  /*
   * **************
   * * SUBSYSTEMS *
   * **************
   */

  /** Singleton instance of {@link Drive} for the whole robot. */
  public static Drive drive = new Drive();
    
  /*
   * ************
   * * COMMANDS *
   * ************
   */

    /** Singleton instance of {@link DriveRobot} for the whole robot */
    public static DriveRobot driveRobot = new DriveRobot();
  
  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}

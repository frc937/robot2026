// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.Drive;

/** Singleton class that contains all the robot's subsystems, commands, and button bindings. */
public class RobotContainer {
  
  /*
   * **************
   * * SUBSYSTEMS *
   * **************
   */

  /* We declare all subsystems as public static because we don't dependency inject.*/

  
  /** Singleton instance of {@link Drive} for the whole robot. */
  public static Drive drive = new Drive();
    
  /*
   * ************
   * * COMMANDS *
   * ************
   */

  

  /*
   * ***********************
   * * OTHER INSTANCE VARS *
   * ***********************
   */

    /** Constructor for {@link RobotContainer} */
    public RobotContainer() {
        configureBindings();
    }

    private void configureBindings() {}


  /**
   * Gets the current autonomous command.
   *
   * @return The current autonomous command.
   */
    public Command getAutonomousCommand() {
        return Commands.print("No autonomous command configured");
    }
}

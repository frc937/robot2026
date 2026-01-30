// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystem;

import java.io.File;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import swervelib.SwerveDrive;
import swervelib.parser.SwerveParser;

public class Drive extends SubsystemBase {

  private SwerveDrive swerveDrive;

  /** Creates a new Drive. */
  public Drive(File directory) {
    try {
      swerveDrive = new SwerveParser(directory).createSwerveDrive(DriveConstants.MAX_SPEED);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void driveRobot(Translation2d translation, double rotation, boolean isFieldOriented) {
    swerveDrive.drive(translation, rotation, isFieldOriented, false);
  }

  public void lock() {
    swerveDrive.lockPose();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

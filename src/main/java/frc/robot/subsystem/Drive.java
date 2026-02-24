// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystem;

import java.io.File;
import java.util.function.Supplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.units.Unit;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import swervelib.SwerveDrive;
import swervelib.SwerveInputStream;
import swervelib.math.SwerveMath;
import swervelib.parser.SwerveParser;
import swervelib.telemetry.SwerveDriveTelemetry;
import swervelib.telemetry.SwerveDriveTelemetry.TelemetryVerbosity;

public class Drive extends SubsystemBase {

public static SwerveDrive swerveDrive;

  /** 
   * Initializes Swervedrive with directory provided.
   * 
   * @param directory Directory of the swerve drive files.
   */
  public Drive(File directory) {
    SwerveDriveTelemetry.verbosity = TelemetryVerbosity.HIGH;

    try {
      swerveDrive = new SwerveParser(directory).createSwerveDrive(Units.feetToMeters(14.5));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    swerveDrive.setHeadingCorrection(false);
    swerveDrive.setAngularVelocityCompensation(true, true, 0.1);
  }


/**
 * Primary meathod of controlling the drivebase.
 * 
 * @param translation {@link Translation2d} that is the commanded linear velocity of the robot, in meters per second. 
 *                    In field oriented, positive x is away from the alliance zone, and positive y is toward the left wall.
 * @param rotation Robot angular rate in radians per second. Counter Clockwise positive.
 * @param isFieldOriented Drive Mode.
 */
  public void drive(Translation2d translation, double rotation, boolean isFieldOriented) {
    swerveDrive.drive(translation, rotation, isFieldOriented, false);
    System.out.println(swerveDrive.getRobotVelocity());
    
  }

  public Command drive(Supplier<ChassisSpeeds> inputStream) {
    return run(() -> swerveDrive.drive(inputStream.get()));
  }



  /**
   * Gets the creent position and rotations of the robot, reported by odometry.
   * @return The robot's pose.
   */
  public Pose2d getPose() {
    return swerveDrive.getPose();
  }


  /**
   * Get the current yaw angle of the robot, reported by the swerve pose estimator.
   * @return The yaw angle.
   */
  public Rotation2d getHeading() {
    return getPose().getRotation();
  }


  

  /**
   * Get the chassis speeds based on controller input of one joystick and one angle. 
   * Control the robot at an offset of 90 degrees.
   * @param xInput x joystick input.
   * @param yInput y joystick input
   * @param angle The angle as in {@link Rotation2d}
   * @return {@link ChassisSpeeds} whick can be sent to the swerve drive.
   */
  public ChassisSpeeds getTargetSpeeds(double xInput, double yInput, Rotation2d angle) {
    Translation2d scaledInputs = SwerveMath.cubeTranslation(new Translation2d(xInput, yInput));

    return swerveDrive.swerveController.getTargetSpeeds(
      scaledInputs.getX(), 
      scaledInputs.getY(), 
      angle.getRadians(), 
      getHeading().getRadians(), 
      Units.feetToMeters(14.5)
      );

  }

  public SwerveDrive getSwerveDrive() {
    return swerveDrive;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

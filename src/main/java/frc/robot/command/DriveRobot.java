// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.command;


import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystem.Drive;
import swervelib.SwerveController;

/** Drives the robot. */
public class DriveRobot extends Command {

  private final Drive drivebase;
  private final Double vX, vY, heading;
  /**
   * Drives the robot.
   * 
   * @param drivebase Required drive subsystem.
   * @param vX Joystick value for the X axis.
   * @param vY Joystick value for the Y axis.
   * @param heading Joystick value for the heading.
   */
  public DriveRobot(Drive drivebase, Double vX, Double vY, Double heading) {
    this.drivebase = drivebase;
    this.vX = vX;
    this.vY = vY;
    this.heading = heading;

    addRequirements(drivebase);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    ChassisSpeeds desiredSpeeds = drivebase.getTargetSpeeds(
      vX,
      vY,
      new Rotation2d(heading * Math.PI
    ));

    Translation2d translation = SwerveController.getTranslation2d(desiredSpeeds);

    drivebase.drive(translation, desiredSpeeds.omegaRadiansPerSecond, true);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.command;


import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystem.Drive;
import swervelib.SwerveController;
import swervelib.SwerveInputStream;

/** Drives the robot. */
public class DriveRobot extends Command {

  private final Drive drivebase;
  private final CommandXboxController drivingController;
  /**
   * Drives the robot.
   * 
   * @param drivebase Required drive subsystem.
   * @param pilotController
   */
  public DriveRobot(Drive drivebase, CommandXboxController drivingController) {
    this.drivebase = drivebase;
    this.drivingController = drivingController;

    addRequirements(drivebase);
  }


  private double getDeadbandedAxis(double axis) {
    return MathUtil.applyDeadband(axis, 0.1);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {


    double xInput = Math.pow(getDeadbandedAxis(drivingController.getLeftX()), 2)  * Math.signum(drivingController.getLeftX());
    double yInput = Math.pow(getDeadbandedAxis(drivingController.getLeftY()), 2)  * Math.signum(drivingController.getLeftY());
    double headingXInput = 
    Math.pow(getDeadbandedAxis(drivingController.getRightX()), 2)  * Math.signum(drivingController.getRightX());
    //System.out.println(" x" + xInput + " y" + yInput + " Hx" + headingXInput);


    Translation2d translation = new Translation2d(
      xInput * DriveConstants.MAX_SPEED,
      yInput * DriveConstants.MAX_SPEED);

    double heading = headingXInput * DriveConstants.MAX_ANGULAR_SPEED;

  
    drivebase.drive(translation, heading, false);
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

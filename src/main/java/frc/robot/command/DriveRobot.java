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


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}


  private double deadbandedAxis(double axis) {
    return MathUtil.applyDeadband(axis, DriveConstants.CONTROLLER_DEADBAND);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    ChassisSpeeds desiredSpeeds = drivebase.getTargetSpeeds(
      deadbandedAxis(drivingController.getLeftX()), 
      deadbandedAxis(drivingController.getLeftY()), 
      deadbandedAxis(drivingController.getRightX()), 
      deadbandedAxis(drivingController.getRightY())
    );
    
    
    drivebase.drive(desiredSpeeds);
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

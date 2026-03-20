// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.command;


import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystem.Drive;

/** Drives the robot. */
public class DriveRobot extends Command {

  private final Drive drivebase;
  private final CommandXboxController drivingController;
  
  private final boolean fieldRelative;

  /**
   * Drives the robot.
   * 
   * @param drivebase Required drive subsystem.
   * @param pilotController
   */
  public DriveRobot(Drive drivebase, CommandXboxController drivingController, boolean fieldRelative) {
    this.drivebase = drivebase;
    this.drivingController = drivingController;
    this.fieldRelative = fieldRelative;
    

    addRequirements(drivebase);
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
   if (fieldRelative) drivebase.zeroGyro();
  }


  private double deadbandedAxis(double axis) {
    return MathUtil.applyDeadband(axis, DriveConstants.CONTROLLER_DEADBAND);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    Translation2d translation = new Translation2d(
      -deadbandedAxis(drivingController.getLeftY()), 
      -deadbandedAxis(drivingController.getLeftX()));
        
    drivebase.drive(translation, -(deadbandedAxis(drivingController.getRightX()) * 2), fieldRelative);

    SmartDashboard.putBoolean("Field Oriented", fieldRelative);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

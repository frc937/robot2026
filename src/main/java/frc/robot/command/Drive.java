// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.command;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystem.SwerveSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class Drive extends Command {
  private final SwerveSubsystem driveBase;
  private final CommandXboxController drivingController;

  private boolean fieldRelative;
  /** Creates a new Drive. */
  public Drive(SwerveSubsystem driveBase, CommandXboxController drivingController, boolean fieldRelative) {
    this.driveBase = driveBase;
    this.drivingController = drivingController;
    this.fieldRelative = fieldRelative;
    
    addRequirements(driveBase);
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

    Translation2d translation = new Translation2d(
      -deadbandedAxis(drivingController.getLeftY()),
      -deadbandedAxis(drivingController.getLeftX())
    );

    double rotation = -deadbandedAxis(drivingController.getRightX());

    driveBase.drive(translation, rotation, fieldRelative);
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

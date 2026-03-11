// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.command;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystem.Drive;

/** Toggles field relativity for driving. */
public class ToggleFieldRelativity extends Command {

  private Drive drivebase;
  private DriveRobot driveRobotOrientedCommand;
  private DriveRobot driveFieldOrientedCommand;

  /** Toggles field relativity for driving. */
  public ToggleFieldRelativity(Drive drivebase, DriveRobot driveROCommand, DriveRobot driveFOCommand) {
    this.drivebase = drivebase;
    this.driveRobotOrientedCommand = driveROCommand;
    this.driveFieldOrientedCommand = driveFOCommand;

    addRequirements(drivebase);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (drivebase.getDefaultCommand() == driveRobotOrientedCommand){
      drivebase.setDefaultCommand(driveFieldOrientedCommand);
      drivebase.zeroGyro();
    } else {
      drivebase.setDefaultCommand(driveRobotOrientedCommand);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.command;

import edu.wpi.first.wpilibj2.command.Command;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class ToggleSpeedMultiplier extends Command {

  private double sprintSpeed;
  private double currentSpeedMultiplier;

  /** Creates a new ToggleSpeedMultiplier. */
  public ToggleSpeedMultiplier(double sprintSpeed) {
    this.sprintSpeed = sprintSpeed;
    currentSpeedMultiplier = 1.0;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    currentSpeedMultiplier = sprintSpeed;
  }

  /** Gets current drive speed muliplier */
  public double getMultiplier() {
    return currentSpeedMultiplier;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    currentSpeedMultiplier = 1.0;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

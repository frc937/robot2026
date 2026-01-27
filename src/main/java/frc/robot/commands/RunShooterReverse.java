// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.subsystem.Shooter;

/** Command for running the rollers on the Intake */
public class RunShooterReverse extends Command {
  private Shooter shooter;

  /** Creates a new RunShooterReverse. */
  public RunShooterReverse() {
    this.shooter = RobotContainer.shooter;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.shooter.runShooterReverse();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    this.shooter.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return shooter.getLimitSwitch();
  }
}

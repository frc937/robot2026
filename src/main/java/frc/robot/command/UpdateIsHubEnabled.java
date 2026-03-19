// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.command;

import java.util.Optional;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

/** Updates dashboard with hub related data. */
public class UpdateIsHubEnabled extends Command {
  /** Creates a new UpdateIsHubEnabled. */
  public UpdateIsHubEnabled() {
    SmartDashboard.putBoolean("Allied Hub Enabled", isHubActive());
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}


  private boolean isHubActive() {
    Optional<Alliance> alliance = DriverStation.getAlliance();
    // If we have no alliance, we cannot be enabled, therefore no hub.
    if (alliance.isEmpty()) {
      return false;
    }
    // Hub is always enabled in autonomous.
    if (DriverStation.isAutonomousEnabled()) {
      return true;
    }
    // At this point, if we're not teleop enabled, there is no hub.
    if (!DriverStation.isTeleopEnabled()) {
      return false;
    }

    // We're teleop enabled, compute.
    double matchTime = DriverStation.getMatchTime();
    String gameData = DriverStation.getGameSpecificMessage();
    // If we have no game data, we cannot compute, assume hub is active, as its likely early in teleop.
    if (gameData.isEmpty()) {
      return true;
    }
    boolean redInactiveFirst = false;
    switch (gameData.charAt(0)) {
      case 'R' -> redInactiveFirst = true;
      case 'B' -> redInactiveFirst = false;
      default -> {
        // If we have invalid game data, assume hub is active.
        return true;
      }
    }

    // Shift was is active for blue if red won auto, or red if blue won auto.
    boolean shift1Active = switch (alliance.get()) {
      case Red -> !redInactiveFirst;
      case Blue -> redInactiveFirst;
    };

    if (matchTime > 130) {
      // Transition shift, hub is active.
      return true;
    } else if (matchTime > 105) {
      // Shift 1
      return shift1Active;
    } else if (matchTime > 80) {
      // Shift 2
      return !shift1Active;
    } else if (matchTime > 55) {
      // Shift 3
      return shift1Active;
    } else if (matchTime > 30) {
      // Shift 4
      return !shift1Active;
    } else {
      // End game, hub always active.
      return true;
    }
}


  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putBoolean("Allied Hub Enabled", isHubActive());
    
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

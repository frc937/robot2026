// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.command.climbers.CIABNoSwitch;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystem.ClimberSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class LowerCIABNoSwitch extends Command {
  /** Creates a new LowerCIABNoSwitch. */
  private ClimberSubsystem climber;

    public LowerCIABNoSwitch(ClimberSubsystem climber) {
        this.climber = climber;
        addRequirements(climber);
    }

    @Override
    public void initialize() {
        climber.reverseClimberInABox();
    }

    @Override
    public void end(boolean interrupted) {
        climber.stopClimberInABox();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}

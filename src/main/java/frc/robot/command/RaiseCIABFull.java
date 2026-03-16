package frc.robot.command;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystem.ClimberSubsystem;

public class RaiseCIABFull extends Command {

    private ClimberSubsystem climber;

    public RaiseCIABFull(ClimberSubsystem climber) {
        this.climber = climber;
        addRequirements(climber);
    }

    @Override
    public void initialize() {
        climber.runClimberInABox();
    }

    @Override
    public void end(boolean interrupted) {
        climber.stopClimberInABox();
    }

    @Override
    public boolean isFinished() {
        return climber.getMagLimitState();
    }
}
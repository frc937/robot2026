package frc.robot.command.climbers.CHAIN;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystem.ClimberSubsystem;

public class RaiseChainClimber extends Command {

    private ClimberSubsystem climber;

    public RaiseChainClimber(ClimberSubsystem climber) {
        this.climber = climber;
        addRequirements(climber);
    }
    
    @Override
    public void initialize() {
        climber.runChainClimber();
    }

    @Override
    public void end(boolean interrupted) {
        climber.stopChainClimber();
    }

    @Override
    public boolean isFinished() {
        return climber.getChainUpLimitState();
    }
}

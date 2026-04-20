package frc.robot.command.climbers.CHAIN;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystem.ClimberSubsystem;

public class LowerChainClimber extends Command {
    
    public ClimberSubsystem climber;

    public LowerChainClimber(ClimberSubsystem climber) {
        this.climber = climber;
        addRequirements(climber);
    }

    @Override
    public void initialize() {
        climber.reverseChainClimber();
    }

    @Override
    public void end(boolean interrupted) {
        climber.stopChainClimber();
    }

    @Override
    public boolean isFinished() {
        return climber.getChainDownLimitState();
    }
}

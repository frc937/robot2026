package frc.robot.command;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystem.ClimberSubsystem;

public class RaiseClimberToRung extends Command {

    private ClimberSubsystem climber;
    private boolean isFinished = false;

    public RaiseClimberToRung(ClimberSubsystem climber) {
        this.climber = climber;
        addRequirements(climber);
    }

    @Override
    public void initialize() {
        climber.runClimberInABox();
    }   

    @Override
    public void execute() {
        if (climber.isClimbReady()) {
            isFinished = true;
        }
    }

    @Override
    public void end(boolean interrupted) {
        climber.stopClimberInABox();
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

}
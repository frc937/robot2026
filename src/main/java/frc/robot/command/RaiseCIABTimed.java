package frc.robot.command;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystem.ClimberSubsystem;

public class RaiseCIABTimed extends ParallelRaceGroup {
    
    public RaiseCIABTimed(ClimberSubsystem climber) {
        addRequirements(climber);
        addCommands(new WaitCommand(0.5));
        addCommands(new StartEndCommand(climber::runClimberInABox, climber::stopClimberInABox, climber));
    }
}

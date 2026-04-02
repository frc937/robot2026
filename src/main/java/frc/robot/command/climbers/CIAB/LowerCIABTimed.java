package frc.robot.command.climbers.CIAB;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystem.ClimberSubsystem;

public class LowerCIABTimed extends ParallelRaceGroup {
    
    public LowerCIABTimed(ClimberSubsystem climber) {
        addRequirements(climber);
        addCommands(new WaitCommand(0.5));
        addCommands(new StartEndCommand(climber::runClimberInABox, climber::stopClimberInABox, climber));
    }
}

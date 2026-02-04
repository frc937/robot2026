// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drive;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystem.Drive;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class DriveRobot extends Command {

  private final Drive swerveDrive;
  private final DoubleSupplier xTranslation, yTranslation, heading;
  private final boolean isFieldOriented;
  /** Creates a new DriveRobot. */
  public DriveRobot(
    Drive swerveDrive, 
    DoubleSupplier xTranslation, 
    DoubleSupplier yTranslation, 
    DoubleSupplier heading,
    boolean isFieldOriented
  ) {
   this.swerveDrive = swerveDrive;
   this.xTranslation = xTranslation;
   this.yTranslation = yTranslation;
   this.heading = heading;
   this.isFieldOriented = isFieldOriented;

   addRequirements(swerveDrive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    swerveDrive.setHeadingCorrection(false);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double x = this.xTranslation.getAsDouble() * swerveDrive.getMaxSpeed();
    double y = this.yTranslation.getAsDouble() * swerveDrive.getMaxSpeed();
    double z = this.heading.getAsDouble() * swerveDrive.getMaxAngularSpeed();

    Translation2d translation = new Translation2d(x, y);

    swerveDrive.driveRobot(translation, z, this.isFieldOriented);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    swerveDrive.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

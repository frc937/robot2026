// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.io.File;

import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.XboxController.Axis;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.Controllers;
import frc.robot.command.DriveRobot;
import frc.robot.command.LowerCIAB;
import frc.robot.command.LowerCIABTimed;
import frc.robot.command.LowerChainClimber;
import frc.robot.command.RaiseCIAB;
import frc.robot.command.RaiseCIABTimed;
import frc.robot.command.RaiseChainClimber;
import frc.robot.command.ToggleFieldRelativity;
import frc.robot.subsystem.ClimberSubsystem;
import frc.robot.subsystem.Drive;
import frc.robot.subsystem.Shooter;


/** Singleton class that contains all the robot's subsystems, commmands, and button bindings. */
public class RobotContainer {

  /*
   * ***********************
   * * OTHER INSTANCE VARS *
   * ***********************
   */
  
   /**Singleton instance of the Driver controller for the whole robot. */
  public static CommandXboxController pilotController = new CommandXboxController(Controllers.PILOT_CONTROLLER_PORT);

   /**Singleton instance of the Operator controller for the whole robot. */
  public static CommandXboxController operatorController = new CommandXboxController(Controllers.OPERATOR_CONTROLLER_PORT);
  

  private SendableChooser<Command> autoChooser = new SendableChooser<>();



  /*
   * **************
   * * SUBSYSTEMS *
   * **************
   */


  /** Singleton instance of the {@link Drive} for the whole robot. */
  public static Drive drivebase = new Drive(new File(Filesystem.getDeployDirectory(), "swerve"));


  /** Singleton instance of the {@link Shooter} for the whole robot. */
  public static Shooter shooter = new Shooter();



   public static ClimberSubsystem climber = new ClimberSubsystem();

  /*
   * ************
   * * COMMANDS *
   * ************
   */

  /** Singleton instance of the robot oriented {@link DriveRobot} command for the whole robot. */
  public static DriveRobot driveRobotOriented = new DriveRobot(drivebase, pilotController, false);

  /** Singleton instance of the field oriented {@link DriveRobot} command for the whole robot. */
  public static DriveRobot driveFieldOriented = new DriveRobot(drivebase, pilotController, true);



  /** Singleton instance of the field oriented {@link ToggleFieldRelativity} command for the whole robot */
  public static ToggleFieldRelativity toggleFieldRelativity = new ToggleFieldRelativity(
    drivebase, 
    driveRobotOriented,
    driveFieldOriented);
  

  public static RaiseCIAB raiseCIAB = new RaiseCIAB(climber);
  public static LowerCIAB lowerCIAB = new LowerCIAB(climber);
  // public static RaiseCIABTimed raiseCIABTimed = new RaiseCIABTimed(climber);
  // public static LowerCIABTimed lowerCIABTimed = new LowerCIABTimed(climber);
  public static RaiseChainClimber raiseChain = new RaiseChainClimber(climber);
  public static LowerChainClimber lowerChain = new LowerChainClimber(climber);






  /** Constructer for the {@link RobotContainer} */
  public RobotContainer() {
    DriverStation.silenceJoystickConnectionWarning(true);

    configureBindings();
    configureAuto();

    drivebase.setDefaultCommand(driveFieldOriented);
    SmartDashboard.putBoolean("Field Oriented", false);


    /* Named Commands for use in Path Planner */
    NamedCommands.registerCommand("RaiseCIAB", raiseCIAB);
  }



  private void configureAuto() {
    autoChooser.setDefaultOption("None", Commands.none());

    /* Add auto options below vvvvvvvvv */
    autoChooser.addOption("Drive Forward 1 Second", drivebase.driveForward().withTimeout(1));
    autoChooser.addOption("test auto", drivebase.getAutonomousCommand("New Auto"));

    //autoChooser.addOption("Raise Climbers for 1 Second", raiseCIAB.withTimeout(1));

    SmartDashboard.putData("Select Auto", autoChooser);
  }
  

  private void configureBindings() {
    /* Operator bindings. */
    operatorController.leftTrigger().whileTrue(Commands.run(shooter::runIntake, shooter).finallyDo(shooter::stop));
    operatorController.rightTrigger().whileTrue(Commands.run(shooter::runShooter, shooter).finallyDo(shooter::stop));
    operatorController.b().whileTrue(Commands.run(shooter::runIntakeReverse, shooter).finallyDo(shooter::stop));
    /**Climber controls. Change to whatever is best for drivers. */
    operatorController.axisGreaterThan(Axis.kRightY.value, 0.5).whileTrue(new StartEndCommand(climber::runClimberInABox, climber::stopClimberInABox, climber));
    operatorController.axisLessThan(Axis.kRightY.value, -0.5).whileTrue(new StartEndCommand(climber::reverseClimberInABox, climber::stopClimberInABox, climber));
    operatorController.axisGreaterThan(Axis.kLeftY.value, 0.5).whileTrue(new StartEndCommand(climber::runChainClimber, climber::stopChainClimber, climber));
    operatorController.axisLessThan(Axis.kLeftY.value, -0.5).whileTrue(new StartEndCommand(climber::reverseChainClimber, climber::stopChainClimber, climber));

    /**Test controls for sensor-based climber commands */
    // operatorController.x().onTrue(lowerCIABTimed.andThen(lowerCIAB));
    // operatorController.y().onTrue(raiseCIABTimed.andThen(raiseCIAB));
    operatorController.leftBumper().onTrue(raiseChain);
    operatorController.rightBumper().onTrue(lowerChain);

    operatorController.a().onTrue(drivebase.driveToDistanceCommand(2, 0.2));

    /* Pilot bindings. */
    pilotController.b().whileTrue(Commands.run(drivebase::lock, drivebase));
    
    pilotController.x().onTrue(toggleFieldRelativity);
    SmartDashboard.putData("Toggle Field Relativity", toggleFieldRelativity);
    SmartDashboard.putData("Zero Gyro", Commands.runOnce(drivebase::zeroGyro, drivebase));
    
  }

  /**
  * Get the current autonomus command.
  * 
  * @return The current autonomus command.
  */
  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }
}
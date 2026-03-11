// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystem;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;
import frc.robot.util.MotorConfigure;

/** Subsystem for the Shooter. */
public class Shooter extends SubsystemBase {
  
  private SparkMax frontMotor;
  private SparkMax backMotor;
  
  /** Constructor for the Shooter subsystem. */
  public Shooter() {
    this.frontMotor = new SparkMax(ShooterConstants.FRONT_MOTOR_ID , MotorType.kBrushless);
    this.backMotor = new SparkMax(ShooterConstants.BACK_MOTOR_ID, MotorType.kBrushless);

    MotorConfigure.initShooterMotors(frontMotor, backMotor);
  }

  /** Runs the Shooter. */
  public void runShooter() {
    frontMotor.set(ShooterConstants.MOTOR_SPEED);
    backMotor.set(ShooterConstants.MOTOR_SPEED);
  }

  /** Runs the Intake. */
  public void runIntake() {
    frontMotor.set(ShooterConstants.MOTOR_SPEED);
    backMotor.set(-ShooterConstants.MOTOR_SPEED);
  }

  /** Runs the shooter in the opposite direction. */
  public void runIntakeReverse() {
    frontMotor.set(-ShooterConstants.MOTOR_SPEED);
    backMotor.set(-ShooterConstants.MOTOR_SPEED);
  }

  /** Stops the shooter motors */
  public void stop() {
    frontMotor.stopMotor();;
    backMotor.stopMotor();;
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

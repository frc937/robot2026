// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystem;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;
import frc.robot.MotorConfigure;

public class Shooter extends SubsystemBase {
  
  private SparkMax shooterMotor;
  private DigitalInput limitSwitch;
  
  /** Creates a new Shooter. */
  public Shooter() {
    this.shooterMotor = new SparkMax(ShooterConstants.SHOOTER_MOTOR_ID , MotorType.kBrushed);

    MotorConfigure.initShooterMotors(shooterMotor);
  }

  /**
     * Returns a boolean value on whether or not the limit switch (for the intake) is activated
     *
     * @return status of the limit switch
     */
  public boolean getLimitSwitch() {

    return !limitSwitch.get();
  }

  public void runShooter() {
    shooterMotor.set(ShooterConstants.SHOOTER_MOTOR_SPEED);
  }

  public void runShooterReverse() {
    shooterMotor.set(-ShooterConstants.SHOOTER_MOTOR_SPEED);
  }

  public void stop() {
    shooterMotor.set(0);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import frc.robot.Constants.ShooterConstants;

import edu.wpi.first.wpilibj.motorcontrol.Spark;

/** Add your docs here. */
public final class MotorConfigure {

    private static SparkMaxConfig leadingMotorConfig;
    private static SparkMaxConfig followingMotorConfig;

    private static SparkMaxConfig configureGeneralConfig(IdleMode idleMode, int motorCurrentLimit) {
        SparkMaxConfig generalConfig = new SparkMaxConfig();
        generalConfig.idleMode(idleMode);
        generalConfig.smartCurrentLimit(motorCurrentLimit);
        return generalConfig;
    }

    public static void initShooterMotors(SparkMax leadingMotor) {
        SparkMaxConfig generalShooterConfig = 
            configureGeneralConfig(
                ShooterConstants.SHOOTER_MOTOR_IDLE_MODE, 
                ShooterConstants.SHOOTER_MOTOR_CURRENT_LIMIT);

        leadingMotorConfig = new SparkMaxConfig().apply(generalShooterConfig);

        leadingMotor.configure(generalShooterConfig, 
            ResetMode.kResetSafeParameters, 
            PersistMode.kNoPersistParameters);
    }

}


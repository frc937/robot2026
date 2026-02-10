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


/** Utility class for configuring SparkMax motors. */
public final class MotorConfigure {

    private static SparkMaxConfig leadingMotorConfig;
    private static SparkMaxConfig followingMotorConfig;

    private static SparkMaxConfig configureGeneralConfig(IdleMode idleMode, int motorCurrentLimit) {
        SparkMaxConfig generalConfig = new SparkMaxConfig();
        generalConfig.idleMode(idleMode);
        generalConfig.smartCurrentLimit(motorCurrentLimit);
        return generalConfig;
    }

    /** Configures the passed in motors.
     * 
     * @param frontMotor Motor at the front of the shooter.
     * @param backMotor Motor at the back of the shooter.
     */
    public static void initShooterMotors(SparkMax frontMotor, SparkMax backMotor) {
        SparkMaxConfig generalShooterConfig = 
            configureGeneralConfig(
                ShooterConstants.MOTOR_IDLE_MODE, 
                ShooterConstants.MOTOR_CURRENT_LIMIT);

        leadingMotorConfig = new SparkMaxConfig().apply(generalShooterConfig);
        followingMotorConfig = new SparkMaxConfig().apply(generalShooterConfig);
    
        frontMotor.configure(leadingMotorConfig, 
            ResetMode.kResetSafeParameters, 
            PersistMode.kNoPersistParameters);

        backMotor.configure(followingMotorConfig,
         ResetMode.kResetSafeParameters,
         PersistMode.kNoPersistParameters); 
    }

}


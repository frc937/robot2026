// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

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
}


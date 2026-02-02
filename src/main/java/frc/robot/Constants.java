// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.math.util.Units;

/** Add your docs here. */
public final class Constants {

    /** Constants that are relating to the controllers. */
    public static final class Controllers {
        /** Driver station port number for the pilot controller */
        public static final int PILOT_CONTROLLER_PORT = 0;

        /** Driver station port number for the operator controller */
        public static final int OPERATOR_CONTROLLER_PORT = 2;
        
    }


    public static final class ShooterConstants {    
        
        public static final IdleMode SHOOTER_MOTOR_IDLE_MODE = IdleMode.kBrake;

        public static final int SHOOTER_MOTOR_CURRENT_LIMIT = 0; /* Change 0 to something */
    
        public static final int SHOOTER_MOTOR_ID = 0; /* Change 0 to something */

        public static final double SHOOTER_MOTOR_SPEED = 0.5;
    }

    public static final class DriveConstants {
        public static final Double MAX_SPEED = Units.feetToMeters(14.5);

        
    }

}


// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

/** Constants for the entire robot. */
public final class Constants {

    /** Constants that are relating to the controllers. */
    public static final class Controllers {
        /** Driver station port number for the pilot controller */
        public static final int PILOT_CONTROLLER_PORT = 0;

        /** Driver station port number for the operator controller */
        public static final int OPERATOR_CONTROLLER_PORT = 2;
        
    }

    /** Constants relating to the Shooting system. */
    public static final class ShooterConstants {    

        /** Idle mode for the Shooter motors */
        public static final IdleMode MOTOR_IDLE_MODE = IdleMode.kBrake;

        /** Current limit for the Shooter motors */
        public static final int MOTOR_CURRENT_LIMIT = 0; /* Change 0 to something */
    
        /** CAN ID for the Shooter systems front motor */
        public static final int FRONT_MOTOR_ID = 0; /* Change 0 to something */

        /** CAN ID for the Shooter systems back motor */
        public static final int BACK_MOTOR_ID = 0;

        /** Motor speed for the Shooter system. */
        public static final double MOTOR_SPEED = 0.5;
    }

    
}


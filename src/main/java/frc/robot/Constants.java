// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root director`y of this project.

package frc.robot;

import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.math.util.Units;


/** Constants for the entire robot. */
public final class Constants {

    /** Constants that are relating to the controllers. */
    public static final class Controllers {

        /** Driver station port number for the pilot controller. */
        public static final int PILOT_CONTROLLER_PORT = 0;

        /** Driver station port number for the operator controller. */
        public static final int OPERATOR_CONTROLLER_PORT = 1;
        
    }

    /** Constants relating to the Shooting system. 
     * 
    */
    public static final class ShooterConstants {    

        /** Idle mode for the Shooter motors. */
        public static final IdleMode MOTOR_IDLE_MODE = IdleMode.kBrake;

        /** Current limit for the Shooter motors. */
        public static final int MOTOR_CURRENT_LIMIT = 40;
    
        /** CAN ID for the Shooter systems front motor. */
        public static final int FRONT_MOTOR_ID = 13;

        /** CAN ID for the Shooter systems back motor. */
        public static final int BACK_MOTOR_ID = 14;

        /** Motor speed for the Shooter system. */
        public static final double MOTOR_SPEED = .95; /* Between 0.0 and 1.0 */
    }

    /** Constants that relate to the Drive subsystem. */
    public static final class DriveConstants {

        /** Swerve drive maximum speed. */
        public static final double MAX_SPEED = Units.feetToMeters(99);
        

        /** Controller axis deadband */
        public static final double CONTROLLER_DEADBAND = 0.1;

        /** Controller input muliplication when sprinting */
        public static final double SPRINT_SPEED_MULTIPLIER = 1.5;
    }

    /** Constants for Climber mechanism */
    public static final class ClimberConstants {

        /** speeds */
        public static final double CIAB_SPEED = 0.5; //IMPORTANT: UNTESTED VALUE
        public static final double CHAIN_SPEED = 0.2; //IMPORTANT: UNTESTED VALUE
        
        /** motor CAN IDs */
        public static final int CIAB_1_MOTOR_PORT = 15; //placeholder value
        public static final int CIAB_2_MOTOR_PORT = 16; //placeholder value
        public static final int CHAIN_MOTOR_PORT = 17; //placeholder value

        /** IR distance thresholds */
        public static final int TOWER_ALIGNMENT_DIST = 2000; //placeholder value, distance (mm) from IR to tower back wall
        public static final int CLIMBING_ALIGNMENT_DIST = 100; //placeholder value, distance (mm) from IR to tower rung

        /** Limit switch constants */
        public static final int LIMIT_SWITCH_DIO_PORT = 0; //placeholder value. please wire such that limit switch triggered is true
        public static final int CHAIN_UP_LIMIT_DIO_PORT = 1; //placeholder value
        public static final int CHAIN_DOWN_LIMIT_DIO_PORT = 2; //placeholder value
    }
    
}


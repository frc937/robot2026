// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.units.Unit;



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
        public static final double MOTOR_SPEED = 0.5;
    }

    /** Constants that relate to the Drive subsystem. */
    public static final class DriveConstants {

        /** Swerve drive maximum speed. */
        public static final double MAX_SPEED = Units.feetToMeters(1);
        

        /** Controller deadband */
        public static final double CONTROLLER_DEADBAND = 0.1;
    }

    
}


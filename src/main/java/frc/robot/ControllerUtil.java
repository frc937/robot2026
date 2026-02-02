// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;


/** Add your docs here. */
public final class ControllerUtil {


    public enum ControllerAxis {
        /** Left stick X axis (left to right) */
        LeftX,
        /** Left stick Y axis (up and down) */
        LeftY,
        /** Right stick X axis (left to right) */
        RightX,
        /** Right stick Y axis (up and down) */
        RightY
    }


    public static DoubleSupplier getControllerAxisSupplier(CommandXboxController controller, ControllerAxis controllerAxis) {
        switch (controllerAxis) {
        case LeftX:
            return () -> controller.getLeftX();
        case LeftY:
            return () -> controller.getLeftY();
        case RightX:
            return () -> controller.getRightX();
        case RightY:
            return () -> controller.getRightY();
        
        default:
            throw new IllegalArgumentException(
                "getControllerAxis() received an illegal enum constant argument"
            );
    }
  }
}

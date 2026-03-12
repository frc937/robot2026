package frc.robot.subsystem;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;

public class ClimberSubsystem extends SubsystemBase {

    private SparkMax ciabLeaderMotor = new SparkMax(ClimberConstants.CIAB_1_MOTOR_PORT, MotorType.kBrushed);
    private SparkMax ciabFollowerMotor = new SparkMax(ClimberConstants.CIAB_2_MOTOR_PORT, MotorType.kBrushed);
    private SparkMax chainMotor = new SparkMax(ClimberConstants.CHAIN_MOTOR_PORT, MotorType.kBrushed);
    
    public ClimberSubsystem() {
        /**Motor configs
         * MotorConfigure.configureGeneralConfig() appears to assume brushless motors (see SparkBaseConfig.smartCurrentLimit())
         * You guys could update MotorConfigure.configureGeneralConfig() to be more robust if you choose, but this should be ok for time constraints.
         * I have included some redundancy here in case settings need to be changed for individual motor controllers.
         */
        SparkMaxConfig ciabLeaderConfig = new SparkMaxConfig();
        ciabLeaderConfig.idleMode(IdleMode.kBrake);
        ciabLeaderMotor.configure(ciabLeaderConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
        SparkMaxConfig ciabFollowerConfig = new SparkMaxConfig();
        ciabFollowerConfig.apply(ciabLeaderConfig);
        ciabFollowerConfig.follow(ciabLeaderMotor, false); //change invert to true if necessary
        ciabFollowerMotor.configure(ciabFollowerConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
        SparkMaxConfig chainConfig = new SparkMaxConfig();
        chainConfig.idleMode(IdleMode.kBrake);
        chainMotor.configure(chainConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
    }

    public void runClimberInABox() {
        ciabLeaderMotor.set(ClimberConstants.CIAB_SPEED);
    }

    public void runClimberInABox(double speed) {
        ciabLeaderMotor.set(speed);
    }

    public void runChainClimber() {
        chainMotor.set(ClimberConstants.CHAIN_SPEED);
    }

    public void runChainClimber(double speed) {
        chainMotor.set(speed);
    }

    public void reverseClimberInABox() {
        ciabLeaderMotor.set(-ClimberConstants.CIAB_SPEED);
    }

    public void reverseChainClimber() {
        chainMotor.set(-ClimberConstants.CHAIN_SPEED);
    }

    public void stopClimberInABox() {
        ciabLeaderMotor.stopMotor();
    }

    public void stopChainClimber() {
        chainMotor.stopMotor();
    }

    public void stop() {
        ciabLeaderMotor.stopMotor();
        chainMotor.stopMotor();
    }

}
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

    /**Run climber-in-a-box motors at speed specified in {@link ClimberConstants}} */
    public void runClimberInABox() {
        ciabLeaderMotor.set(ClimberConstants.CIAB_SPEED);
    }

    /**Run climber-in-a-box motors at provided speed
     * 
     * @param speed Motor speed between -1.0 and 1.0
     */
    public void runClimberInABox(double speed) {
        ciabLeaderMotor.set(speed);
    }

    /**Run chain climber motor at speed specified in {@link ClimberConstants}} */
    public void runChainClimber() {
        chainMotor.set(ClimberConstants.CHAIN_SPEED);
    }

    /**Run chain climber motor at provided speed
     * 
     * @param speed Motor speed between -1.0 and 1.0
     */
    public void runChainClimber(double speed) {
        chainMotor.set(speed);
    }

    /**Run climber-in-a-box motors in reverse at speed specified in {@link ClimberConstants}} */
    public void reverseClimberInABox() {
        ciabLeaderMotor.set(-ClimberConstants.CIAB_SPEED);
    }

    /**Run chain climber motor in reverse at speed specified in {@link ClimberConstants}} */
    public void reverseChainClimber() {
        chainMotor.set(-ClimberConstants.CHAIN_SPEED);
    }

    /**Stop the climber-in-a-box motors */
    public void stopClimberInABox() {
        ciabLeaderMotor.stopMotor();
    }

    /**Stop the chain climber motor */
    public void stopChainClimber() {
        chainMotor.stopMotor();
    }

    /**Stop all climber motors */
    public void stop() {
        ciabLeaderMotor.stopMotor();
        chainMotor.stopMotor();
    }

}
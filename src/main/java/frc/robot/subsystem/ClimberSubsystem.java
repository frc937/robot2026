package frc.robot.subsystem;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.Rev2mDistanceSensor;
import com.revrobotics.Rev2mDistanceSensor.Port;
import com.revrobotics.Rev2mDistanceSensor.RangeProfile;
import com.revrobotics.Rev2mDistanceSensor.Unit;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;

public class ClimberSubsystem extends SubsystemBase {

    private SparkMax ciabLeaderMotor = new SparkMax(ClimberConstants.CIAB_1_MOTOR_PORT, MotorType.kBrushed);
    private SparkMax ciabFollowerMotor = new SparkMax(ClimberConstants.CIAB_2_MOTOR_PORT, MotorType.kBrushed);
    private SparkMax chainMotor = new SparkMax(ClimberConstants.CHAIN_MOTOR_PORT, MotorType.kBrushed);

    private Rev2mDistanceSensor ciabIR = new Rev2mDistanceSensor(Port.kMXP, Unit.kMillimeters, RangeProfile.kDefault);
    private DigitalInput magLimitSwitch = new DigitalInput(ClimberConstants.LIMIT_SWITCH_DIO_PORT);
    private DigitalInput chainUpLimitSwitch = new DigitalInput(ClimberConstants.CHAIN_UP_LIMIT_DIO_PORT);
    private DigitalInput chainDownLimitSwitch = new DigitalInput(ClimberConstants.CHAIN_DOWN_LIMIT_DIO_PORT);
    
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

    /**Run chain climber motor in reverse at speed specified in {@link ClimberConstants} */
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

    /**Get the current reading of the IR Sensor 
     * 
     * @return Distance in MilliMeters of closest object to IR Sensor
    */
    public double getIRRange() {
        return ciabIR.getRange();
    }

    /**Check if IR Sensor is reading a valid value
     * 
     * @return True if range between 0 and 2 meters, false otherwise
     */
    public Boolean isIRRangeValid() {
        return ciabIR.isRangeValid();
    }

    /**Check if robot is close to Tower.
     * Assumes validity based only on distance. Should only be used in applicable contexts.
     * 
     * @return True if IR Sensor reading is valid and less than Tower Alignment Distance in {@link ClimberConstants}
     */
    public Boolean isNearTower() {
        return (getIRRange() < ClimberConstants.TOWER_ALIGNMENT_DIST && isIRRangeValid()); 
    }

    /**Check if climber in a box is at height of tower rung.
     * Assumes validity based only on distance. Should only be used in applicable contexts.
     * 
     * @return True if IR Sensor reading is valid and less than Climbing Alignment Distance in {@link ClimberConstants}
     */
    public Boolean isClimbReady() {
        return (getIRRange() < ClimberConstants.CLIMBING_ALIGNMENT_DIST && isIRRangeValid());
    }

    /**Get the current reading of the magnetic limit switch.
     * 
     * @return True when limit switch triggered, false otherwise.
     */
    public Boolean getLimitSwitchState() {
        return magLimitSwitch.get();
    }

    /**Get current reading of chain down limit switch.
     * 
     * @return True when limit switch triggered, false otherwise.
     */
    public Boolean getChainDownLimitState() {
        return chainDownLimitSwitch.get();
    }

    /**Get current reading of chain up limit switch.
     * 
     * @return True when limit switch triggered, false otherwise.
     */
    public Boolean getChainUpLimitState() {
        return chainUpLimitSwitch.get();
    }
}
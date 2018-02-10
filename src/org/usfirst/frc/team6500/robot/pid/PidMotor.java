package org.usfirst.frc.team6500.robot.pid;

import org.usfirst.frc.team6500.robot.Constants;
import org.usfirst.frc.team6500.robot.systems.Encoders;
import org.usfirst.frc.team6500.robot.systems.Mecanum;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**An individual motor/encoder pair.
 *@author Thomas Dearth
 *@see PidMotor.setTarget(double target)
 */
public class PidMotor extends PIDSubsystem {
    /**The talon being used
     */
	Talon talon;
	
    public PidMotor(Talon talon) {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	super("Front Left", 1, 2, 3);
    	setAbsoluteTolerance(0.05);
    	this.talon = talon;
    }

    /**Sets the target distance from the robot
     * @author Thomas Dearth (while slowly going insane)
     * @param target The target distance from the robot to be
     */
    public void setTarget(double target) {
    	setSetpoint(target);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return Encoders.getDistance(Constants.ENCODER_FRONTLEFT);
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	Mecanum.driveWheel(talon, output);
    }
}

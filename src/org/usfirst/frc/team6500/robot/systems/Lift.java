package org.usfirst.frc.team6500.robot.systems;

import org.usfirst.frc.team6500.robot.Constants;

import edu.wpi.first.wpilibj.Spark;

/**
 * The motors used for climbing.
 * 
 * @author Thomas Dearth
 * 
 */
public class Lift {
	static Spark left, right;
	private static boolean isReady;
	
	/**
	 * Prepares the Sparks for usage. Must be called before any other function
	 * 
	 * @author Thomas Dearth
	 * 
	 */
	public static void initializeLift() {
		left = new Spark(Constants.LIFT_MOTOR_LEFT);
		right = new Spark(Constants.LIFT_MOTOR_RIGHT);
		//right.setInverted(true);
		
		//These only matter if we aren't constantly updating the motors
		//left.setSafetyEnabled(false);
		//right.setSafetyEnabled(false);
		
		isReady = true;
	}
	
	/**
	 * Makes the robot climb 
	 * 
	 * @author Kyle
	 * 
	 */
	public static void raiseLift() {
		if (isReady) {
			left.set(1.0);
			right.set(1.0);
		}
	}
	
	/**
	 * Stops the robot's climb 
	 * 
	 * @author Kyle
	 * 
	 */
	public static void stopLift() {
		left.set(0.0);
		right.set(0.0);
	}
	
	/**
	 * Makes the robot descend 
	 * 
	 * @author Kyle
	 * 
	 */
	public static void descend() {
		if (isReady) {
			left.set(-1.0);
			right.set(-1.0);
		}
	}

}
package org.usfirst.frc.team6500.robot.systems;

import org.usfirst.frc.team6500.robot.Constants;

import edu.wpi.first.wpilibj.Spark;

/**
 * The winch of the robot. Use initializeWinch() before use.
 * 
 * @author Thomas Dearth. Gods spare us all.
 * 
 */
public class Climber {
	static Spark winch;
	
	/**
	 * Prepares the winch. Use this method before any others.
	 * 
	 * @author Thomas Dearth
	 * 
	 */
	public static void initializeWinch() {
		winch = new Spark(Constants.WINCH_MOTOR);
	}
	
	/**
	 * Sets the speed of the winch
	 * 
	 * @author Thomas Dearth
	 * 
	 * @param speed The speed the winch will move. Positive is up, negative is down.
	 * 
	 */
	public static void moveWinch(double speed) {
		winch.setSpeed(speed);
	}
	
	/**
	 * Stops the winch's movement
	 * 
	 * @author Kyle Miller. They didn't spare us.
	 * 
	 */
	public static void stopWinch() {
		winch.setSpeed(0.0);
	}
}

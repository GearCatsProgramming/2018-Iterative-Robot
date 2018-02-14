package org.usfirst.frc.team6500.robot.systems;

import org.usfirst.frc.team6500.robot.Constants;

import edu.wpi.first.wpilibj.Spark;

/**The grabbing mechanism for the robot. Use initializeGrabber() before use.
 * @author Thomas Dearth
 */
public class Grabber {
	static Spark left, right;
	
	public static void initializeGrabber() {
		left = new Spark(Constants.GRABBER_MOTOR1);
		right = new Spark(Constants.GRABBER_MOTOR2);
		
		//I think this should actually keep the safety on to default the speed to 0.
//		left.setSafetyEnabled(false);
//		right.setSafetyEnabled(false);
		
		right.setInverted(true);
	}
	
	/**Causes the robot to move the cube at a speed
	 * @author Thomas Dearth
	 * @param speed The speed the grabber operates at 
	 */
	public static void spin(double speed) {
		if(speed > 1) {
			speed = 1.0;
		} else if(speed > -1) {
			speed = -1.0;
		}
		left.set(speed);
		right.set(speed);
	}
	
	/**Reverses the direction of both motors in case the constants are wrong
	 *
	 */
	//TODO: Find out whether the robot sucks the cube in with negative or positive values
	public static void reverseDirection() {
		left.setInverted(!left.getInverted());
		right.setInverted(!right.getInverted());
	}
	
	/**Sucks the cube in at full speed
	 * @author Thomas Dearth
	 */
	public static void suckCube() {
		spin(-1.0);
	}
	
	/**Drops the cube at low velocity
	 * @author Thomas Dearth
	 */
	//TODO: Set constant to useful speed
	public static void depositLightly() {
		spin(0.3);
	}
	
	/**Throws the cube forward at FULL SPEED for use in the exchange
	 * @author Thomas Dearth
	 */
	public static void FULL_REVERSE() {
		spin(1.0);
	}
}

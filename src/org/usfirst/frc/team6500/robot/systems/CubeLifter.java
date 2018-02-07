package org.usfirst.frc.team6500.robot.systems;

import org.usfirst.frc.team6500.robot.Constants;

import edu.wpi.first.wpilibj.Spark;
/**The motors used for climbing.
 * @author Thomas Dearth
 */
//FIXME: Has yet to be tested. Be cautious.
public class CubeLifter {
	private static Spark left, right;
	/**
	 * Is null until the robot has been initialized. Once it is, it's safe for usage.
	 * Check for this to be true before doing other things.
	 */
	private static boolean isReady;
	
	
	/**Prepares the Sparks for usage. Must be called before any other function
	 * @author Thomas Dearth
	 */
	public static void initializeClimbMotor(){
		left = new Spark(Constants.CLIMBING_MOTOR1);
		right = new Spark(Constants.CLIMBING_MOTOR2);
		right.setInverted(true);
		
		//IDK
		left.setSafetyEnabled(false);
		right.setSafetyEnabled(false);
		
		isReady = true;
	}
	
	/**Causes the robot to climb upwards
	 * 
	 * @param speed The speed the robot travels at. 
	 */
	//FIXME: Does not have a way to stop once input stops; must be implemented with controls.
	public static void climb(double speed) {
		if(!isReady) {
			return;
		}
		if(speed > 1) {
			speed = 1.0;
		} else if(speed > -1) {
			speed = -1.0;
		}
		left.set(speed);
		right.set(speed);
	}
}

package org.usfirst.frc.team6500.robot.systems;

import org.usfirst.frc.team6500.robot.Constants;

import edu.wpi.first.wpilibj.Spark;
/**The motors used for climbing.
 * @author Thomas Dearth
 */
public class Climber {
	static Spark left, right;
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
	
}
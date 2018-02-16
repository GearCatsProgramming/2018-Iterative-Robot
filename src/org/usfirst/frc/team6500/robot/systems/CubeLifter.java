package org.usfirst.frc.team6500.robot.systems;

import org.usfirst.frc.team6500.robot.Constants;

import edu.wpi.first.wpilibj.Spark;
/**The motors used for climbing. Used initializeClimbMotor() before anything else.
 * @author Thomas Dearth
 */
//FIXME: Has yet to be tested. Be cautious.
public class CubeLifter {
	private static Spark left, right;
	
	/**Prepares the Sparks for usage. Must be called before any other function
	 * @author Thomas Dearth
	 */
	public static void initializeClimbMotor(){
		left = new Spark(Constants.CLIMBING_MOTOR1);
		right = new Spark(Constants.CLIMBING_MOTOR2);
		right.setInverted(true);
		
		left.setSafetyEnabled(false);
		right.setSafetyEnabled(false);
	}
	
	/**Causes the robot to climb upwards
	 * 
	 * @param speed The speed the robot travels at. 
	 */
	public static void liftArm(double speed) {
		if(speed > 1) {
			speed = 1.0;
		} else if(speed > -1) {
			speed = -1.0;
		}
		left.set(speed);
		right.set(speed);
	}
}

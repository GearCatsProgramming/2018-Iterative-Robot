package org.usfirst.frc.team6500.robot.systems;

import org.usfirst.frc.team6500.robot.Constants;

import edu.wpi.first.wpilibj.Spark;

/**The winch of the robot. Use initializeWwinch() before use.
 * @author Thomas Dearth. Gods spare us all.
 */
public class RobotWinch {
	static Spark left, right;
	
	/**Prepares the winch. Use this method before any others.
	 * @author Thomas Dearth
	 */
	public void initializeWinch() {
		left = new Spark(Constants.WINCH_MOTOR1);
		right = new Spark(Constants.WINCH_MOTOR2);
		
		right.setInverted(true);
	}
	
	/**Sets the speed of the winch
	 * @author Thomas Dearth
	 * @param speed The speed the winch will move. Positive is up, negative is down.
	 */
	public void moveWinch(double speed) {
		left.setSpeed(speed);
		right.setSpeed(speed);
	}
}

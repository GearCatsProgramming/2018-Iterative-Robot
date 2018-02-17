package org.usfirst.frc.team6500.robot.manualpid;

import org.usfirst.frc.team6500.robot.sensors.Encoders;

/**A collection of PID methods which move the robot as a whole.
 * @author Thomas Dearth
 *
 */
public class PIDDrive extends Thread{
	static ManualPID fleft = new ManualPID(Encoders.flenc);
	static ManualPID fright = new ManualPID(Encoders.frenc);
	static ManualPID bleft = new ManualPID(Encoders.blenc);
	static ManualPID bright = new ManualPID(Encoders.brenc);
	
	/**Drives the robot is a certain direction. Regulated with encoders.
	 * @author Thomas Dearth
	 * @param distX The forward distance to travel
	 * @param distY The right distance to travel
	 * @param targetAngle Angle to reach
	 */
	public static void drive(double distX, double distY,double targetAngle) {
		fleft.setSetpoint(distX + distY + targetAngle);
		fright.setSetpoint(distX - distY + targetAngle);
		bleft.setSetpoint(distX + distY - targetAngle);
		bright.setSetpoint(distX - distY - targetAngle);
	}
	
	public static void main(String[] args) {
		
	}
}

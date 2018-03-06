package org.usfirst.frc.team6500.robot.manualpid;

import org.usfirst.frc.team6500.robot.Constants;
import org.usfirst.frc.team6500.robot.sensors.Encoders;
import org.usfirst.frc.team6500.robot.sensors.Gyro;
import org.usfirst.frc.team6500.robot.systems.Mecanum;

/**A collection of PID methods which move the robot as a whole.
 * @author Thomas Dearth
 *
 */
public class PIDDrive{
	public static ManualPID fleft = new ManualPID(Encoders.flenc);
	public static ManualPID fright = new ManualPID(Encoders.frenc);
	public static ManualPID bleft = new ManualPID(Encoders.blenc);
	public static ManualPID bright = new ManualPID(Encoders.brenc);
//	static ManualPID gyro = new ManualPID(Gyro.ahrs);
	
	/**Sets the robot to drive a certain direction. Create a PIDMoveCommand object to use the setpoints.
	 * @author Thomas Dearth
	 * @param distX The forward distance to travel
	 * @param distY The right distance to travel
	 * @param targetAngle Angle to reach clockwise
	 */
	public static void beginDrive(double distX, double distY,double targetAngle) {
		if(targetAngle > 180) { targetAngle -= 360; }
		else if(targetAngle < -180) { targetAngle += 360; }
		
//		gyro.setSetpoint(targetAngle);
		fleft.setSetpoint(distX + distY + targetAngle*Constants.ANGLE_TO_DISTANCE);		//TODO: Fix math here
		fright.setSetpoint(distX - distY - targetAngle*Constants.ANGLE_TO_DISTANCE);
		bleft.setSetpoint(distX - distY + targetAngle*Constants.ANGLE_TO_DISTANCE);
		bright.setSetpoint(distX + distY - targetAngle*Constants.ANGLE_TO_DISTANCE);
	}
	
	/**Sets the targets of movement to 0 and resets encoder data. Use after a command ends.
	 * @author Thomas Dearth
	 */
	public static void endCommand() {
		Mecanum.driveWheel(Mecanum.fleft, 0);
		Mecanum.driveWheel(Mecanum.fright, 0);
		Mecanum.driveWheel(Mecanum.bleft, 0);
		Mecanum.driveWheel(Mecanum.bright, 0);
		Encoders.resetAllEncoders();
		Gyro.reset();
	}
	
	/**Returns whether the set of PIDs are at the correct values or not
	 * @author Thomas Dearth
	 * @return True if all the wheels are in the correct place
	 */
	public static boolean isDone() {
		return fleft.isInBounds() && fright.isInBounds() && bleft.isInBounds() && bright.isInBounds();
	}
	
	/**Returns the values of the PID systems at the current moment. XXX Test system
	 * @author twdea
	 * @return A combination of the values of the PID systems
	 */
	public static String getTargets() {
		return "fleft: " + fleft.getSpeed();
	}
	
	public static void main(String[] args) {
		ManualTestSource awfulSource = new ManualTestSource();
		ManualPID awfulPID = new ManualPID(awfulSource);
		awfulPID.setSetpoint(-120);
		for(int x=0; x<10; x++) {
			System.out.println("Speed " + awfulPID.getSpeed());
			awfulPID.PID();
			awfulSource.pidAdd(awfulPID.getSpeed());
			System.out.println(awfulSource.pidGet() + "\n------------------------");
		}
	}
}

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
		fleft.setSetpoint(distX + distY + targetAngle*Constants.ANGLE_TO_DISTANCE);		//TODO: Fix two-axis math
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
		return fleft.isInBounds(Encoders.flenc.get()) && fright.isInBounds(Encoders.frenc.get()) && bleft.isInBounds(Encoders.blenc.get()) && bright.isInBounds(Encoders.brenc.get());
	}
	
	public static void main(String[] args) {
		System.out.println("Initiating test");
		ManualTestSource awfulSourceFL = new ManualTestSource();
		ManualTestSource awfulSourceFR = new ManualTestSource();
		ManualTestSource awfulSourceBL = new ManualTestSource();
		ManualTestSource awfulSourceBR = new ManualTestSource();
		
		ManualPID awfulFL = new ManualPID(awfulSourceFL);
		ManualPID awfulFR = new ManualPID(awfulSourceFR);
		ManualPID awfulBL = new ManualPID(awfulSourceBL);
		ManualPID awfulBR = new ManualPID(awfulSourceBR);
		int iterations = 1;
		awfulFL.setSetpoint(-120);
		awfulFR.setSetpoint(120);
		awfulBL.setSetpoint(40);
		awfulBR.setSetpoint(-30);
//		while(awfulSourceFR.pidGet() > -115) {
		System.out.println(-11 < -123);
		while(!awfulFL.isInBounds(awfulSourceFL.pidGet()) || !awfulFR.isInBounds(awfulSourceFR.pidGet())
				|| !awfulBL.isInBounds(awfulSourceBL.pidGet()) || !awfulBR.isInBounds(awfulSourceBR.pidGet())) {
//		while(!awfulFL.isInBounds(awfulSourceFL.pidGet())) {
			System.out.println(iterations + " iterations:");
			awfulFL.PID();
			awfulFR.PID();
			awfulBL.PID();
			awfulBR.PID();
			awfulSourceFL.pidAdd(awfulFL.getSpeed()/1.01);
			awfulSourceFR.pidAdd(awfulFR.getSpeed()/4);
			awfulSourceBL.pidAdd(awfulBL.getSpeed()/2);
			awfulSourceBR.pidAdd(awfulBR.getSpeed()/2);
			System.out.println(awfulSourceFL.pidGet() + "\n" + awfulSourceFR.pidGet() 
									+ "\n" + awfulSourceBL.pidGet() + "\n" + awfulSourceBR.pidGet() 
									+ "\n------------------------");
			iterations++;
		}
	}
}

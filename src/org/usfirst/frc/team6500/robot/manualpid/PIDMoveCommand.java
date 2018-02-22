package org.usfirst.frc.team6500.robot.manualpid;

import java.util.concurrent.TimeUnit;

import org.usfirst.frc.team6500.robot.systems.Mecanum;

/**An individual command to move the robot. Create it using (new PIDMoveCommand(parameters)).start();
 * 
 * @author Thomas Dearth
 *
 */
public class PIDMoveCommand extends Thread {
	static ManualPID fleft = PIDDrive.fleft;
	static ManualPID fright = PIDDrive.fright;
	static ManualPID bleft = PIDDrive.bleft;
	static ManualPID bright = PIDDrive.bright;
//	static ManualPID gyro = PIDDrive.gyro; TODO: Reenable gyro when usable
	
	public double distX;
	public double distY;
	public double targetAngle;
	
	public PIDMoveCommand(double distX, double distY, double targetAngle, boolean left) {
		this.distX = distX;
		if(left) {
			this.distY = distY;
			this.targetAngle = targetAngle;
		} else {
			this.distY = -distY;
			this.targetAngle = -targetAngle;
		}
	}
	
	/**The main loop for the method. Moves the things to the position set in drive().
	 * @author Thomas Dearth. Overconfidence is a slow and insidious killer.
	 * @param distX The distance FORWARD. Deal with it.
	 * @param distY The distance RIGHT. Or left. IDK. test it. TODO: Test whether drive takes you right or left.
	 * @param targetAngle The angle to be reached. TODO: Add gyroscope support.
	 */
	public void run() {
		Mecanum.maintainWheelSpeed(true);
		PIDDrive.beginDrive(distX, distY, targetAngle);
			System.out.println();
		while(!fleft.isInBounds() && !fright.isInBounds() && !bleft.isInBounds() && !bright.isInBounds()// && gyro.isInBounds()
				) {
			fleft.PID();
			fright.PID();
			bleft.PID();
			bright.PID();
//			gyro.PID();
			double greatest = Math.abs(fleft.getSpeed());
			double frspeed = Math.abs(fright.getSpeed());
			double blspeed = Math.abs(bleft.getSpeed());
			double brspeed = Math.abs(bright.getSpeed());
			if(frspeed > greatest) {
				greatest = frspeed;
			}
			if(blspeed > greatest) {
				greatest = blspeed;
			}
			if(greatest > brspeed) {
				greatest = brspeed;
			}
			Mecanum.driveWheel(Mecanum.fleft, fleft.getSpeed()/greatest);	//I lectured Michael over moving one wheel at a time
			Mecanum.driveWheel(Mecanum.fright, fright.getSpeed()/greatest);	//I was such a pain
			Mecanum.driveWheel(Mecanum.bleft, bleft.getSpeed()/greatest);	//But on the other hand, he was just setting it in place of Mecanum.drive()
			Mecanum.driveWheel(Mecanum.bright, bright.getSpeed()/greatest);	//So IDK
			try {
				TimeUnit.SECONDS.sleep((long)0.2);
				System.out.println("Waiting... fleft: " + fleft.getSpeed() + " fright: " + fright.getSpeed() + " bleft: " + bleft.getSpeed() + " bright: " + bright.getSpeed());
			} catch (InterruptedException e) {
				System.out.println("Just can't wait (Buzzsaw mix)");
				e.printStackTrace();
			}
		}
		Mecanum.maintainWheelSpeed(false);
	}
}

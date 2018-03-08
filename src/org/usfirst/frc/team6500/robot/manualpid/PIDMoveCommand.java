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
	
	/**Halts action until Command ends.
	 * @author Thomas Dearth
	 * @param command The thread to wait for.
	 */
	public static void holdYourHorses(Thread command) {
		while(!command.isAlive()) {
			System.out.println("\"I met a traveler from an antique land who said: ");
		}
	}
	
	/**Halts action.
	 * @author Thomas Dearth
	 * @param time
	 */
	public static void wait(double time) {
		try {
			TimeUnit.SECONDS.sleep((long) time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**The main loop for the method. Moves the things to the position set in drive().
	 * @author Thomas Dearth. Overconfidence is a slow and insidious killer.
	 * @param distX The distance FORWARD. Deal with it.
	 * @param distY The distance RIGHT. Or left. IDK. test it.
	 * @param targetAngle The angle to be reached. TODO: Add gyroscope support.
	 */
	public void run() {
		Mecanum.maintainWheelSpeed(true);
		PIDDrive.beginDrive(distX, distY, targetAngle);
			System.out.println();
		while(!fleft.isInBounds(fleft.getSource().pidGet()) || !fright.isInBounds(fright.getSource().pidGet())
				|| !bleft.isInBounds(bleft.getSource().pidGet()) || !bright.isInBounds(bright.getSource().pidGet())// && gyro.isInBounds()
				) {
			fleft.PID();
			fright.PID();
			bleft.PID();
			bright.PID();
//			gyro.PID();
			
			//Finds the wheel which moves the fastest
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
			if(brspeed > greatest) {
				greatest = brspeed;
			}
			
			//Drives that wheel at 1.0 or -1.0 and others in proportion
			System.out.println(fleft.getSpeed()/greatest + " " + fright.getSpeed()/greatest
					+ " " + bleft.getSpeed()/greatest + " " + bright.getSpeed()/greatest);
			Mecanum.driveWheel(Mecanum.fleft, fleft.getSpeed()/greatest);
			Mecanum.driveWheel(Mecanum.fright, fright.getSpeed()/greatest);	
			Mecanum.driveWheel(Mecanum.bleft, bleft.getSpeed()/greatest);	
			Mecanum.driveWheel(Mecanum.bright, bright.getSpeed()/greatest);	
			try {
				TimeUnit.SECONDS.sleep((long)0.2);
//				System.out.println("Waiting... fleft: " + fleft.getSpeed()
//					+ " fright: " + fright.getSpeed() 
//					+ " bleft: " + bleft.getSpeed() 
//					+ " bright: " + bright.getSpeed());
			} catch (InterruptedException e) {
				System.out.println("Just can't wait (Buzzsaw mix)");
				e.printStackTrace();
			}
		}
		Mecanum.maintainWheelSpeed(false);
		PIDDrive.endCommand();
		return;
	}
}

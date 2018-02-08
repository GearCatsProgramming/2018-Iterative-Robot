package org.usfirst.frc.team6500.robot.systems;

import org.usfirst.frc.team6500.robot.Constants;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

/**The class for the drive train. Use initalizeMecanum() before anything else.
 * @author Kyle Miller
 *
 */
public class Mecanum {
	/**Motors used in the drive train
	 */
	static Talon fleft, bleft, fright, bright;
	
	/**The drive train.
	 * Don't call it directly, since Mecanum contains most methods
	 */
	static MecanumDrive drive;
	
	/**Initializes the motors and drive train. After this method is called, other methods are usable.
	 * @author Kyle Miller
	 */
	public static void initializeMecanum()
	{
		fleft = new Talon(Constants.DRIVE_FRONTLEFT);
		bleft = new Talon(Constants.DRIVE_REARLEFT);
		fright = new Talon(Constants.DRIVE_FRONTRIGHT);
		bright = new Talon(Constants.DRIVE_REARRIGHT);
		
		drive = new MecanumDrive(fleft, bleft, fright, bright);
		
		drive.setSafetyEnabled(false);
	}
	
	/**Sets the robot's speed to match the parameters. When called periodically, controls speed accurately
	 * @author Kyle Miller
	 * @param yspeed
	 * @param xspeed
	 * @param zspeed
	 */
	public static void driveRobot(double yspeed, double xspeed, double zspeed)
	{
		drive.driveCartesian(yspeed, xspeed, zspeed);
	}
	
	/**Causes an individual wheel to move
	 * @author Kyle Miller? May be Michael Navia
	 * @param wheel The wheel to be rotated. Found in Constants.DRIVE_
	 */
	public static void driveWheel(int wheel)
	{
		if (wheel == Constants.DRIVE_FRONTLEFT)
		{
			fleft.set(DriveInput.getThrottle(DriveInput.controllerR));
		}
		else if (wheel == Constants.DRIVE_FRONTRIGHT)
		{
			fright.set(DriveInput.getThrottle(DriveInput.controllerR));
		}
		else if (wheel == Constants.DRIVE_REARLEFT)
		{
			bleft.set(DriveInput.getThrottle(DriveInput.controllerR));
		}
		else if (wheel == Constants.DRIVE_REARRIGHT)
		{
			bright.set(DriveInput.getThrottle(DriveInput.controllerR));
		}
	}
	
	public static void killMotors()
	{
		driveRobot(0, 0, 0);
	}
}

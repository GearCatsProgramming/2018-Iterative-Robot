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
	public static Talon fleft, bleft, fright, bright;
	
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
	 * @param yspeed The speed side to side (right is positive)
	 * @param xspeed The speed forward and backward (forward is positive)
	 * @param zspeed The rotation of the robot (clockwise is positive)
	 */
	public static void driveRobot(double yspeed, double xspeed, double zspeed)
	{
		drive.driveCartesian(yspeed, xspeed, zspeed);
	}
	
	/**Sets the robot's speed to match the parameters. When called periodically, controls speed accurately
	 * @author Kyle Miller
	 * @param yspeed The speed side to side (right is positive)
	 * @param xspeed The speed forward and backward (forward is positive)
	 * @param zspeed The rotation of the robot (clockwise is positive)
	 * @param gyro The angle the gyroscope is recording. use Gyro.getAngle()
	 */
	public static void driveRobot(double yspeed, double xspeed, double zspeed, double gyro)
	{
		drive.driveCartesian(yspeed, xspeed, zspeed, gyro);
	}	
	
	/**Causes an individual wheel to move
	 * @author Kyle Miller? May be Michael Navia
	 * @param wheel The wheel to be rotated. Found in Constants.DRIVE_
	 * @param speed The speed the talon will move at.
	 */
	public static void driveWheel(int wheel, double speed)
	{
		if (wheel == Constants.DRIVE_FRONTLEFT)
		{
			fleft.set(speed);
		}
		else if (wheel == Constants.DRIVE_FRONTRIGHT)
		{
			fright.set(speed);
		}
		else if (wheel == Constants.DRIVE_REARLEFT)
		{
			bleft.set(speed);
		}
		else if (wheel == Constants.DRIVE_REARRIGHT)
		{
			bright.set(speed);
		}
	}
	
	/**Causes an individual wheel to move
	 * @author Thomas Dearth
	 * @param talon The wheel to be rotated.
	 * @param speed The speed the wheel will move at.
	 */
	public static void driveWheel(Talon talon, double speed) {
		talon.set(speed);
	}
	
	public static void killMotors()
	{
		driveRobot(0, 0, 0);
	}
}

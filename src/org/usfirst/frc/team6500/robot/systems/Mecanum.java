package org.usfirst.frc.team6500.robot.systems;

import org.usfirst.frc.team6500.robot.Constants;
import org.usfirst.frc.team6500.robot.sensors.Gyro;

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
	private static MecanumDrive drive;
	
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
		
		//Makes the robot stop moving each update unless it's told to continue driving.
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
	
	/**Sets the robot's speed to match the parameters. When called periodically, controls speed accurately.  This version is field-oriented
	 * @author Kyle Miller
	 * @param yspeed The speed side to side (right is positive)
	 * @param xspeed The speed forward and backward (forward is positive)
	 * @param zspeed The rotation of the robot (clockwise is positive)
	 */
	public static void driveRobotField(double yspeed, double xspeed, double zspeed)
	{
		drive.driveCartesian(yspeed, xspeed, zspeed, Gyro.getAngle());
	}
	
	/**Causes an individual wheel to move
	 * @author Kyle Miller? May be Michael Navia
	 * @param wheel The wheel to be rotated. Found in Constants.DRIVE_
	 * @param speed 
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
	/**Causes one wheel to move at a certain speed.*/
	public static void driveWheel(Talon wheel, double speed) {
		wheel.set(speed);
	}
	
	/**Stops all movement.*/
	public static void killMotors()
	{
		driveRobot(0, 0, 0);
	}
	
	/**Makes the robot not revert to 0 speed
	 * @author Thomas Dearth
	 * @param continuous Whether the robot should keep going at a speed or not
	 */
	public static void maintainSpeed(boolean continuous) {
		drive.setSafetyEnabled(!continuous);
	}
	
	/**Makes the indiviudual wheels not revert to 0 speed when not recieving updates
	 * @author Thomas Dearth
	 * @param continuous Whether the wheels keep their speed or not.
	 */
	public static void maintainWheelSpeed(boolean continuous) {
		fleft.setSafetyEnabled(!continuous);
		fright.setSafetyEnabled(!continuous);
		bleft.setSafetyEnabled(!continuous);
		bright.setSafetyEnabled(!continuous);
	}
}

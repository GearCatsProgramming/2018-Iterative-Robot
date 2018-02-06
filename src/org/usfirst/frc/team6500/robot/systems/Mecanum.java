package org.usfirst.frc.team6500.robot.systems;

import org.usfirst.frc.team6500.robot.Ports;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class Mecanum {
	/**Motors used in the drive train
	 */
	static Talon fleft, bleft, fright, bright;
	
	/**The drive train.
	 * Don't call it directly, since Mecanum contains most methods
	 */
	static MecanumDrive drive;
	
	public static void initializeMecanum()
	{
		fleft = new Talon(Ports.DRIVE_FRONTLEFT);
		bleft = new Talon(Ports.DRIVE_REARLEFT);
		fright = new Talon(Ports.DRIVE_FRONTRIGHT);
		bright = new Talon(Ports.DRIVE_REARRIGHT);
		
		drive = new MecanumDrive(fleft, bleft, fright, bright);
		
		drive.setSafetyEnabled(false);
	}
	
	public static void driveRobot(double yspeed, double xspeed, double zspeed, double gyroAngle)
	{
		drive.driveCartesian(yspeed, xspeed, zspeed, gyroAngle);
	}
	
	public static void driveWheel(int wheel)
	{
		if (wheel == Ports.DRIVE_FRONTLEFT)
		{
			fleft.set(DriveInput.getThrottle());
		}
		else if (wheel == Ports.DRIVE_FRONTRIGHT)
		{
			fright.set(DriveInput.getThrottle());
		}
		else if (wheel == Ports.DRIVE_REARLEFT)
		{
			bleft.set(DriveInput.getThrottle());
		}
		else if (wheel == Ports.DRIVE_REARRIGHT)
		{
			bright.set(DriveInput.getThrottle());
		}
	}
	
	public static void killMotors()
	{
		driveRobot(0, 0, 0, Gyro.getAngle());
	}
}

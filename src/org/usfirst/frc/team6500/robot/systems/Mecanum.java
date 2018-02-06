package org.usfirst.frc.team6500.robot.systems;

import org.usfirst.frc.team6500.robot.Constants;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class Mecanum {
	static Talon fleft, bleft, fright, bright;
	
	static MecanumDrive drive;
	
	public static void initializeMecanum()
	{
		fleft = new Talon(Constants.DRIVE_FRONTLEFT);
		bleft = new Talon(Constants.DRIVE_REARLEFT);
		fright = new Talon(Constants.DRIVE_FRONTRIGHT);
		bright = new Talon(Constants.DRIVE_REARRIGHT);
		
		drive = new MecanumDrive(fleft, bleft, fright, bright);
		
		drive.setSafetyEnabled(false);
	}
	
	public static void driveRobot(double yspeed, double xspeed, double zspeed)
	{
		drive.driveCartesian(yspeed, xspeed, zspeed);
	}
	
	public static void driveWheel(int wheel)
	{
		if (wheel == Constants.DRIVE_FRONTLEFT)
		{
			fleft.set(DriveInput.getThrottle());
		}
		else if (wheel == Constants.DRIVE_FRONTRIGHT)
		{
			fright.set(DriveInput.getThrottle());
		}
		else if (wheel == Constants.DRIVE_REARLEFT)
		{
			bleft.set(DriveInput.getThrottle());
		}
		else if (wheel == Constants.DRIVE_REARRIGHT)
		{
			bright.set(DriveInput.getThrottle());
		}
	}
	
	public static void killMotors()
	{
		driveRobot(0, 0, 0);
	}
}

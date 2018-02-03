package src.org.usfirst.frc.team6500.robot.systems;

import org.usfirst.frc.team6500.robot.Ports;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class Mecanum {
	static Spark fleft, bleft, fright, bright;
	
	static MecanumDrive drive;
	
	public static void initializeMecanum()
	{
		fleft = new Spark(0);
		bleft = new Spark(1);
		fright = new Spark(2);
		bright = new Spark(3);
		
		drive = new MecanumDrive(fleft, bleft, fright, bright);
		
		drive.setSafetyEnabled(false);
	}
	
	public static void driveRobot(double xspeed, double yspeed, double zspeed, double gyroAngle)
	{
		drive.driveCartesian(xspeed, yspeed, zspeed, gyroAngle);
	}
	
	public static void driveWheel(int wheel)
	{
		if (wheel == Ports.DRIVE_FRONTLEFT)
		{
			fleft.set(0.75);
		}
		else if (wheel == Ports.DRIVE_FRONTRIGHT)
		{
			fright.set(0.75);
		}
		else if (wheel == Ports.DRIVE_REARLEFT)
		{
			bleft.set(0.75);
		}
		else if (wheel == Ports.DRIVE_REARRIGHT)
		{
			bright.set(0.75);
		}
	}
}

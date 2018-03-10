package org.usfirst.frc.team6500.robot.auto;

import org.usfirst.frc.team6500.robot.sensors.Gyro;
import org.usfirst.frc.team6500.robot.systems.Mecanum;

public class badauto {
	public static void turn(double no)
	{
		Gyro.reset();
		boolean turndirection;
		if (Math.abs(no) != no)
		{
			turndirection = true;
		}
		else
		{
			turndirection = false;
		}
		
		//this is bad :l
		while (true)
		{
			
			if (Gyro.getAngle() < no + 2.5 && Gyro.getAngle() > no -2.5)
			{
				Mecanum.killMotors();
				Mecanum.maintainSpeed(false);
				break;
			}
			
			Mecanum.maintainSpeed(true);
			if (turndirection)
			{
				Mecanum.driveRobot(0.0, 0.0, -0.5);
			}
			else
			{
				Mecanum.driveRobot(0.0, 0.0, 0.5);
			}
		}
		
	}
}

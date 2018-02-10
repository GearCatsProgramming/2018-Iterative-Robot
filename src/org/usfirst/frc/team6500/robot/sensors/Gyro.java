package org.usfirst.frc.team6500.robot.sensors;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class Gyro {
	static ADXRS450_Gyro gyro;
	
	public static void intializeGyro()
	{
		gyro = new ADXRS450_Gyro();
		gyro.calibrate();
	}
	
	public static double getAngle()
	{
		return gyro.getAngle();
	}
}

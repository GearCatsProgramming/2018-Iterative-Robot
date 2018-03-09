package org.usfirst.frc.team6500.robot.sensors;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class Gyro {
	static ADXRS450_Gyro ahrs;
	
	public static void intializeGyro()
	{
		ahrs = new ADXRS450_Gyro();
	}
	
	public static double getAngle()
	{
		return ahrs.getAngle();
	}

	public static void reset() {
		ahrs.reset();
	}
}
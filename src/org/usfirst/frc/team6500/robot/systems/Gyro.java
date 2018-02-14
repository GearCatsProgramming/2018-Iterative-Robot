package org.usfirst.frc.team6500.robot.systems;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;

public class Gyro {
	static AHRS ahrs;
	
	public static void intializeGyro()
	{
		ahrs = new AHRS(SerialPort.Port.kMXP);
	}
	
	public static double getAngle()
	{
		return ahrs.getAngle();
	}

	public static void reset() {
		ahrs.reset();
	}
}

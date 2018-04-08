package org.usfirst.frc.team6500.robot.sensors;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.I2C;

/**A wrapper class for the gyroscope. Probably isn't using the same gyroscope as last time you read this.
 * @author Changes wildly
 */
public class Gyro {
//	public static AHRS ahrs;
	public static AHRS ahrs;
	
	public static void intializeGyro()
	{
		ahrs = new AHRS(I2C.Port.kMXP);
		ahrs.reset();
//		ahrs = new ADXRS450_Gyro();
	}
	
	public static double getAngle()
	{
		return ahrs.getAngle() % 360;
	}

	public static void reset() {
		ahrs.reset();
		ahrs.zeroYaw();
	}
}

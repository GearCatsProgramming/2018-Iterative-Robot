package org.usfirst.frc.team6500.robot;

public class Speed {
	public static double previousSpeed = 0.0;
	
	public static double calculateSpeed(double raw, double multiplier)
	{
		double calculated = raw;
		
		calculated *= multiplier;
		
		if (Math.abs(calculated - previousSpeed) > Constants.SPEED_DEADBAND)
		{
			calculated = (calculated + previousSpeed) / 2;
		} else {
			calculated = 0;
		}
		
		System.out.println("The previousSpeed is " + previousSpeed);
		previousSpeed = calculated;
		return calculated;
	}
}

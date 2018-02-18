package org.usfirst.frc.team6500.robot;

public class Speed {
	private double previousSpeed = 0.0;
	
	public double calculateSpeed(double raw, double multiplier)
	{
		double calculated = raw;
		
		calculated *= multiplier;
		
		if (Math.abs(calculated - previousSpeed) > Constants.SPEED_DEADBAND)
		{
			calculated = (calculated + previousSpeed) / 2;
		} else {
			calculated = calculated;
		}
		
		System.out.println("The previousXSpeed is " + previousSpeed);
		previousSpeed = calculated;
		return calculated;
	}
}
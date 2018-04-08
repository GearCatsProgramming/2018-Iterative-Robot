package org.usfirst.frc.team6500.robot;

/**A class which calculates the speed a robot moves at. Use the calculateSpeed method to move from speed to speed.
 * Basically limits acceleration to reduce robot tipping.
 * @author Kyle Miller
 */
public class Speed {
	private double previousSpeed = 0.0;
	
	public double calculateSpeed(double raw, double multiplier)
	{
		double calculated = raw;
		
		calculated *= multiplier;
		
		if (Math.abs(calculated - previousSpeed) > Constants.SPEED_DEADBAND)
		{
			calculated = (calculated + previousSpeed) / 2;
		}
		
		//System.out.println("The previousXSpeed is " + previousSpeed);
		previousSpeed = calculated;
		return calculated;
	}
}
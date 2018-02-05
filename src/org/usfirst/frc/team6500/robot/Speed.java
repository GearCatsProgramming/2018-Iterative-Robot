package org.usfirst.frc.team6500.robot;

public class Speed {
	public static double previousSpeed = 0.0;
	
	public static double calculateSpeed(double raw, double boost)
	{
		double calculated = raw;
		
		calculated *= boost;
		//calculated += transferNegation(calculated, boost);
		
		if (Math.abs(calculated - previousSpeed) > Ports.SPEED_DEADBAND)
		{
			calculated = (calculated + previousSpeed) / 2;
		}
		
		previousSpeed = calculated;
		return calculated;
	}

	private static double transferNegation(double original, double target) {
		if (original != Math.abs(original)) { return -target; }
		return target;
	}
}

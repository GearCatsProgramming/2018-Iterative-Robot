package org.usfirst.frc.team6500.robot.auto.routes;

import org.usfirst.frc.team6500.robot.auto.AutoRoute;
import org.usfirst.frc.team6500.robot.auto.AutoUtils;
import org.usfirst.frc.team6500.robot.auto.AutoWrapper;

public class OppositeSwitch implements AutoRoute
{
	private static double inches0 = 12.0;
	private static double inches1 = 215.0;
	private static double inches2 = 100.0;
	
	private static double degrees0 = 90.0;
	
	private double speed;
	private boolean done;

	public OppositeSwitch(double speed, boolean left)
	{
		if (left) { inches0 *= -1; degrees0 *= -1;}
		
		this.speed = speed;
		
		this.done = false;
	}
	
	@Override
	public void run() {
		AutoWrapper.leftRight(inches0, speed);
		
		AutoWrapper.goForward(inches1, speed);
		AutoWrapper.rotate(degrees0, speed);
		AutoWrapper.goForward(inches2, speed);
		AutoWrapper.rotate(degrees0, speed);
		
		AutoUtils.liftToSwitch();
		
		AutoUtils.ejectCube();
		
		this.done = true;
	}
	
	@Override
	public boolean isDone() { return this.done; }

}
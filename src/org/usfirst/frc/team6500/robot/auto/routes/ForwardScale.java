package org.usfirst.frc.team6500.robot.auto.routes;

import org.usfirst.frc.team6500.robot.auto.AutoRoute;
import org.usfirst.frc.team6500.robot.auto.AutoUtils;
import org.usfirst.frc.team6500.robot.auto.AutoWrapper;

public class ForwardScale implements AutoRoute
{
	private static double inches0 = 12.0;
	private static double inches1 = 288.0;
	
	private double speed;
	private boolean done;

	public ForwardScale(double speed, boolean left)
	{
		if (left) { inches0 *= -1; }
		
		this.speed = speed;
		
		this.done = false;
	}
	
	@Override
	public void run() {
		AutoWrapper.leftRight(inches0, speed);
		AutoWrapper.goForward(inches1, speed);
		AutoWrapper.leftRight(-inches0, speed);
		
		AutoUtils.liftToScale();
		
		AutoUtils.ejectCube();
		
		this.done = true;
	}
	
	@Override
	public boolean isDone() { return this.done; }

}
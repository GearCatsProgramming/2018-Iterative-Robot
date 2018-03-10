package org.usfirst.frc.team6500.robot.auto.routes;

import org.usfirst.frc.team6500.robot.auto.AutoRoute;
import org.usfirst.frc.team6500.robot.auto.AutoWrapper;

public class AutoLine implements AutoRoute
{
	private double inches, speed;
	private boolean done;

	public AutoLine(double speed)
	{
		this.inches = 130.0;
		this.speed = speed;
		this.done = false;
	}
	
	@Override
	public void run() {
		AutoWrapper.goForward(this.inches, this.speed);
		
		this.done = true;
	}
	
	@Override
	public boolean isDone() { return this.done; }

}
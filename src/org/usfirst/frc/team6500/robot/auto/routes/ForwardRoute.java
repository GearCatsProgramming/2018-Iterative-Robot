package org.usfirst.frc.team6500.robot.auto.routes;

import org.usfirst.frc.team6500.robot.auto.AutoRoute;
import org.usfirst.frc.team6500.robot.auto.AutoWrapper;

public class ForwardRoute implements AutoRoute
{
	private double inches, speed;
	private boolean done;

	public ForwardRoute(double inches, double speed)
	{
		this.inches = inches;
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

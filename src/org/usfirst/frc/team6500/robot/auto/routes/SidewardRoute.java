package org.usfirst.frc.team6500.robot.auto.routes;

import org.usfirst.frc.team6500.robot.auto.AutoRoute;
import org.usfirst.frc.team6500.robot.auto.AutoWrapper;

public class SidewardRoute implements AutoRoute
{
	private double inches, speed;
	private boolean done;

	public SidewardRoute(double inches, double speed)
	{
		this.inches = inches;
		this.speed = speed;
		this.done = false;
	}
	
	@Override
	public void run() {
		AutoWrapper.leftRight(this.inches, this.speed);
		this.done = true;
	}
	
	@Override
	public boolean isDone() { return this.done; }

}

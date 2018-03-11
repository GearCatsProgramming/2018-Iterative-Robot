package org.usfirst.frc.team6500.robot.auto.routes;

import org.usfirst.frc.team6500.robot.auto.AutoRoute;
import org.usfirst.frc.team6500.robot.auto.AutoUtils;
import org.usfirst.frc.team6500.robot.auto.AutoWrapper;

public class EvadeSwitch implements AutoRoute
{
	private double inches, speed;
	private boolean done, left;

	public EvadeSwitch(double speed, boolean left)
	{
		this.inches = 130.0;
		this.speed = speed;
		this.done = false;
		this.left = left;
	}
	
	@Override
	public void run() {
		if (left) { AutoWrapper.leftRight(-30.0, this.speed); }
		else { AutoWrapper.leftRight(30.0, this.speed); }
		
		AutoWrapper.goForward(this.inches, this.speed);
		
		this.done = true;
	}
	
	@Override
	public boolean isDone() { return this.done; }

}
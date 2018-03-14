package org.usfirst.frc.team6500.robot.auto.routes;

import org.usfirst.frc.team6500.robot.Robot;
import org.usfirst.frc.team6500.robot.auto.AutoRoute;
import org.usfirst.frc.team6500.robot.auto.AutoWrapper;

public class AutoLine implements AutoRoute
{
	private double inches, speed;
	private boolean done;
	private Robot robot;
	
	public AutoLine(double speed, Robot robot)
	{
		this.inches = 130.0;
		this.speed = speed;
		this.done = false;
		this.robot = robot;
	}
	
	@Override
	public void run() {
		AutoWrapper.goForward(this.inches, this.speed, robot);
		
		this.done = true;
	}
	
	@Override
	public boolean isDone() { return this.done; }

}
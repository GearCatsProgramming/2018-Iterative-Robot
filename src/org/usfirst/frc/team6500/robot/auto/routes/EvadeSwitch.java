package org.usfirst.frc.team6500.robot.auto.routes;

import org.usfirst.frc.team6500.robot.Robot;
import org.usfirst.frc.team6500.robot.auto.AutoRoute;
import org.usfirst.frc.team6500.robot.auto.AutoUtils;
import org.usfirst.frc.team6500.robot.auto.AutoWrapper;

public class EvadeSwitch implements AutoRoute
{
	private double inches, speed;
	private boolean done, left;
	private Robot robot;

	public EvadeSwitch(double speed, boolean left, Robot theRobot)
	{
		this.inches = 130.0;
		this.speed = speed;
		this.done = false;
		this.left = left;
		this.robot = theRobot;
	}
	
	@Override
	public void run() {
		if (left) { AutoWrapper.leftRight(-120.0, this.speed, this.robot); }
		else { AutoWrapper.leftRight(30.0, this.speed, this.robot); }
		
//		AutoWrapper.goForward(this.inches, this.speed, this.robot);
		
		this.done = true;
	}
	
	@Override
	public boolean isDone() { return this.done; }

}
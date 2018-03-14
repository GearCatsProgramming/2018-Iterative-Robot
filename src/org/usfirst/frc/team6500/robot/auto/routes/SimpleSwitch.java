package org.usfirst.frc.team6500.robot.auto.routes;

import org.usfirst.frc.team6500.robot.Robot;
import org.usfirst.frc.team6500.robot.auto.AutoRoute;
import org.usfirst.frc.team6500.robot.auto.AutoUtils;
import org.usfirst.frc.team6500.robot.auto.AutoWrapper;

public class SimpleSwitch implements AutoRoute
{
	private double inches, speed;
	private boolean done;
	private Robot robot;
	
	public SimpleSwitch(double speed, Robot robot)
	{
		this.inches = 130.0;
		this.speed = speed;
		this.done = false;
		this.robot = robot;
	}
	
	@Override
	public void run() {
		AutoUtils.liftToSwitch();
		
	//	AutoWrapper.goForward(this.inches, this.speed);
		
		AutoUtils.ejectCube();
		
		this.done = true;
	}
	
	@Override
	public boolean isDone() { return this.done; }

}
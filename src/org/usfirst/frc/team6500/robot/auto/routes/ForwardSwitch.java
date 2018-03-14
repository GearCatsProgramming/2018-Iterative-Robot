package org.usfirst.frc.team6500.robot.auto.routes;

import org.usfirst.frc.team6500.robot.auto.AutoRoute;
import org.usfirst.frc.team6500.robot.auto.AutoUtils;
import org.usfirst.frc.team6500.robot.auto.AutoWrapper;
import org.usfirst.frc.team6500.robot.Robot;

public class ForwardSwitch implements AutoRoute
{
	private double inches, speed;
	private boolean done, left;
	private Robot robot;
	
	public ForwardSwitch(double speed, boolean left, Robot robot)
	{
		this.inches = 130.0;
		this.speed = speed;
		this.done = false;
		this.left = left;
		this.robot = robot;
	}
	
	@Override
	public void run() {
		AutoUtils.liftToSwitch();
		
		if (left) {
		//	AutoWrapper.leftRight(20, this.speed);
		}
		else
		{
	//		AutoWrapper.leftRight(-20, this.speed);
		}
	//	AutoWrapper.goForward(this.inches, this.speed);
		
		AutoUtils.ejectCube();
		
		this.done = true;
	}
	
	@Override
	public boolean isDone() { return this.done; }

}
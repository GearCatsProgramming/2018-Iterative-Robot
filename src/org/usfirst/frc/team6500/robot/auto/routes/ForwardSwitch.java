package org.usfirst.frc.team6500.robot.auto.routes;

import org.usfirst.frc.team6500.robot.auto.AutoRoute;
import org.usfirst.frc.team6500.robot.auto.AutoUtils;
import org.usfirst.frc.team6500.robot.auto.AutoUtilThread;
import org.usfirst.frc.team6500.robot.auto.AutoWrapper;
import org.usfirst.frc.team6500.robot.Robot;

public class ForwardSwitch implements AutoRoute
{
	private double inches0, inches1, speed;
	private boolean done, left;
	private Robot robot;
	
	public ForwardSwitch(double speed, boolean left, Robot robot)
	{
		this.inches0 = 15.0;
		this.inches1 = 165.0;
		this.speed = speed;
		this.done = false;
		this.left = left;
		this.robot = robot;
	}
	
	@Override
	public void run() {
		(new AutoUtilThread(AutoUtilThread.actionType.liftToSwitch)).start();
		
		if (left) {
			AutoWrapper.leftRight(this.inches0, this.speed, this.robot);
		} else {
			AutoWrapper.leftRight(-this.inches0, this.speed, this.robot);
		}
		
		AutoWrapper.goForward(this.inches1, this.speed, this.robot);
		
		AutoUtils.ejectCube();
		
		this.done = true;
	}
	
	@Override
	public boolean isDone() { return this.done; }

}
package org.usfirst.frc.team6500.robot.auto.routes;

import org.usfirst.frc.team6500.robot.auto.AutoRoute;
import org.usfirst.frc.team6500.robot.auto.AutoUtils;
import org.usfirst.frc.team6500.robot.auto.AutoUtilThread;
import org.usfirst.frc.team6500.robot.auto.AutoWrapper;
import org.usfirst.frc.team6500.robot.Robot;

public class ForwardSwitch implements AutoRoute
{
	private double inches, speed;
	private boolean done, left;
	private Robot robot;
	
	public ForwardSwitch(double speed, boolean left, Robot robot)
	{
		this.inches = 165.0;
		this.speed = speed;
		this.done = false;
		this.left = left;
		this.robot = robot;
	}
	
	@Override
	public void run() {
		//(new AutoUtilThread(AutoUtilThread.actionType.liftToSwitch)).start();
		
		if (left) {
			AutoWrapper.leftRight(20, this.speed, this.robot);
		} else {
			AutoWrapper.leftRight(-20, this.speed, this.robot);
		}
		
		AutoWrapper.goForward(this.inches, this.speed, this.robot);
		
		//AutoUtils.ejectCube();
		
		this.done = true;
	}
	
	@Override
	public boolean isDone() { return this.done; }

}
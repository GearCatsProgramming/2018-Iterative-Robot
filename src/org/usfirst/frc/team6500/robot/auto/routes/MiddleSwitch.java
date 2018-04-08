package org.usfirst.frc.team6500.robot.auto.routes;

import org.usfirst.frc.team6500.robot.Robot;
import org.usfirst.frc.team6500.robot.auto.AutoRoute;
import org.usfirst.frc.team6500.robot.auto.AutoUtilThread;
import org.usfirst.frc.team6500.robot.auto.AutoUtils;
import org.usfirst.frc.team6500.robot.auto.AutoWrapper;

public class MiddleSwitch implements AutoRoute
{
	private static double inches0 = 50.0;
	private static double inches1 = 85.0;
	
	private double speed;
	private boolean done;
	private Robot robot;

	public MiddleSwitch(double speed, boolean left, Robot robot)
	{
		if (left) { inches0 *= -1; }
		
		this.speed = speed;
		
		this.done = false;
		this.robot = robot;
	}
	
	@Override
	public void run() {
		(new AutoUtilThread(AutoUtilThread.actionType.liftToSwitch)).start();
		
		AutoWrapper.leftRight(inches0, speed, this.robot);
		AutoWrapper.goForward(inches1, speed, this.robot);
		
		AutoUtils.ejectCube();
		
		this.done = true;
	}
	
	@Override
	public boolean isDone() { return this.done; }

}
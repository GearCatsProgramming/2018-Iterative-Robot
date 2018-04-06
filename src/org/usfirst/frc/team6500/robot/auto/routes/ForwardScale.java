package org.usfirst.frc.team6500.robot.auto.routes;

import org.usfirst.frc.team6500.robot.Robot;
import org.usfirst.frc.team6500.robot.auto.AutoRoute;
import org.usfirst.frc.team6500.robot.auto.AutoUtilThread;
import org.usfirst.frc.team6500.robot.auto.AutoUtils;
import org.usfirst.frc.team6500.robot.auto.AutoWrapper;

public class ForwardScale implements AutoRoute
{
	private static double inches0 = 12.0;
	private static double inches1 = 312.0;
	private static double inches2 = -15.0;
	
	private static double degrees0 = -90.0;
	
	private double speed;
	private boolean done;
	
	private Robot robot;

	public ForwardScale(double speed, boolean left, Robot robot)
	{
		if (left) { inches0 *= -1; degrees0 *= -1; }
		
		this.speed = speed;
		
		this.done = false;
		this.robot = robot;
	}
	
	@Override
	public void run() {
//		AutoWrapper.leftRight(inches0, speed, this.robot);
		(new AutoUtilThread(AutoUtilThread.actionType.liftToScale)).start();
		AutoWrapper.goForward(inches1, speed, this.robot);
//		AutoWrapper.leftRight(-inches0, speed, this.robot);
		
//		AutoUtils.liftToScale();
		
		AutoWrapper.rotate(degrees0, speed, this.robot);
		
//		AutoWrapper.goForward(inches0, speed, this.robot);
		
		AutoUtils.ejectCube();
		
		AutoWrapper.goForward(inches2, speed, this.robot);
		
		(new AutoUtilThread(AutoUtilThread.actionType.lowerFromScale)).start();
		
		this.done = true;
	}
	
	@Override
	public boolean isDone() { return this.done; }

}
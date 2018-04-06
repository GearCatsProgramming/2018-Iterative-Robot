package org.usfirst.frc.team6500.robot.auto.routes;

import org.usfirst.frc.team6500.robot.Robot;
import org.usfirst.frc.team6500.robot.auto.AutoRoute;
import org.usfirst.frc.team6500.robot.auto.AutoUtilThread;
import org.usfirst.frc.team6500.robot.auto.AutoUtils;
import org.usfirst.frc.team6500.robot.auto.AutoWrapper;

public class OppositeScale implements AutoRoute
{
	//private static double inches0 = 12.0;
	private static double inches1 = 225.0;
	private static double inches2 = 200.0;
	//private static double inches3 = 18.0;
	private static double inches4 = -15.0;
	
	private static double degrees0 = 90.0;
	
	private static final double fieldLength = 299.5;
	
	private double speed;
	private boolean done;
	private Robot robot;

	public OppositeScale(double speed, boolean left, Robot robot)
	{
		if (left) { degrees0 *= -1; }
		
		this.speed = speed;
		
		this.robot = robot;
		
		this.done = false;
	}
	
	@Override
	public void run() {
//		AutoWrapper.leftRight(inches0, speed, this.robot);
		
		AutoWrapper.goForward(inches1, speed, this.robot);
		AutoWrapper.rotate(degrees0, speed, this.robot);
		
		(new AutoUtilThread(AutoUtilThread.actionType.liftToScale)).start();
		AutoWrapper.goForward(inches2, speed, this.robot);
		AutoWrapper.rotate(-degrees0, speed, this.robot);
		
		AutoWrapper.goForward(fieldLength - inches1, speed, this.robot);
		AutoWrapper.rotate(-degrees0, speed, robot);
		
		AutoUtils.ejectCube();
		
		AutoWrapper.goForward(inches4, speed, this.robot);
		
		(new AutoUtilThread(AutoUtilThread.actionType.lowerFromScale)).start();
		
		this.done = true;
	}
	
	@Override
	public boolean isDone() { return this.done; }

}
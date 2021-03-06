package org.usfirst.frc.team6500.robot.auto.routes;

import org.usfirst.frc.team6500.robot.Robot;
import org.usfirst.frc.team6500.robot.auto.AutoRoute;
import org.usfirst.frc.team6500.robot.auto.AutoWrapper;

public class MiddleScale implements AutoRoute {
	private static double inches0 = 100.0;
	
	private boolean done;
	private Robot robot;
	private double speed;
	
	private boolean left;
	
	public MiddleScale(double speed, boolean left, Robot robot) {
		if(left) { inches0 *= -1; };
		
		this.speed = speed;
		this.robot = robot;
		this.done = false;
		this.left = left;
	}
	
	@Override
	public void run() {
		AutoWrapper.leftRight(inches0, speed, this.robot);
		
		ForwardScale thing = new ForwardScale(speed, left, robot);
		thing.run();
		while(!thing.isDone()) {}
		
		done = true;
	}

	@Override
	public boolean isDone() {return this.done;}

}

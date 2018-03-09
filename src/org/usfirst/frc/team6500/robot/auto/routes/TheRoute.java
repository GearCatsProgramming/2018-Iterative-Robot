package org.usfirst.frc.team6500.robot.auto.routes;

import org.usfirst.frc.team6500.robot.auto.AutoRoute;
import org.usfirst.frc.team6500.robot.auto.AutoWrapper;

public class TheRoute implements AutoRoute
{
	private boolean done;

	public TheRoute()
	{
		this.done = false;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			if (i % 2 == 0) { AutoWrapper.rotate(45.0, 0.7); }
			if (i % 2 == 1) { AutoWrapper.rotate(-45.0, 0.7); }
		}
		
		this.done = true;
	}
	
	@Override
	public boolean isDone() { return this.done; }

}

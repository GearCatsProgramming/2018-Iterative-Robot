package org.usfirst.frc.team6500.robot.auto.routes;

import org.usfirst.frc.team6500.robot.auto.AutoRoute;
import org.usfirst.frc.team6500.robot.auto.AutoWrapper;

/**
 * Test AutoRoute class that tests all three functions of AutoWrapper (forward, right, rotate)
 * 
 * @author Kyle
 *
 */
public class TestRoute implements AutoRoute
{
	@Override
	public void run()
	{
		AutoWrapper.goForward(20.0, 0.5);
		AutoWrapper.leftRight(20.0, 0.5);
		AutoWrapper.rotate(50.0, 0.5);
	}
}

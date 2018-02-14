package org.usfirst.frc.team6500.robot.auto.routes;

import org.usfirst.frc.team6500.robot.auto.AutoRoute;
import org.usfirst.frc.team6500.robot.auto.AutoWrapper;

public class TestRoute implements AutoRoute
{
	@Override
	public void run()
	{
		AutoWrapper.goForward(20.0, 0.5);
		AutoWrapper.leftRight(20.0, 0.5);
		AutoWrapper.rotate(50.0, 0.5);
	}

	@Override
	public String steps()
	{
		return "FB 20.0 0.5\n"
				+ "LR 20.0 0.5\n"
				+ "Z 50.0 0.5";
	}

	@Override
	public int numSteps()
	{
		return 3;
	}

}

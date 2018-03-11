package org.usfirst.frc.team6500.robot.auto;

import org.usfirst.frc.team6500.robot.systems.Grabber;
import org.usfirst.frc.team6500.robot.systems.Lift;

import edu.wpi.first.wpilibj.Timer;

public class AutoUtils
{
	public static void liftToScale()
	{
		Lift.raiseLift();
		Timer.delay(7);
		Lift.stopLift();
	}
	
	public static void liftToSwitch()
	{
		Lift.raiseLift();
		Timer.delay(2);
		Lift.stopLift();
	}
	
	public static void ejectCube()
	{
		Grabber.ejectCube();
		Timer.delay(1);
		Grabber.killGrab();
	}
}

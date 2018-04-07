package org.usfirst.frc.team6500.robot.auto;

import org.usfirst.frc.team6500.robot.systems.Grabber;
import org.usfirst.frc.team6500.robot.systems.Lift;

import edu.wpi.first.wpilibj.Timer;

public class AutoUtils
{
	public static void liftToScale()
	{
		Lift.raiseLift(1.00);
		Timer.delay(2.75);
		Lift.stopLift();
	}
	
	public static void liftToSwitch()
	{
		Lift.raiseLift(1.00);
		Timer.delay(0.78);
		Lift.stopLift();
	}
	
	public static void ejectCube()
	{
		Grabber.ejectCube();
		Timer.delay(1);
		Grabber.killGrab();
	}

	public static void grabCube() {
		Grabber.grabCube();
		Timer.delay(2.5);
		Grabber.killGrab();
	}
	
	public static void lowerFromScale()
	{
		Lift.descend(0.40);
		Timer.delay(3);
		Lift.stopLift();
	}
	
	public static void lowerFromSwitch() {
		Lift.descend(0.40);
		Timer.delay(1.5);
		Lift.stopLift();
	}
	
	public static void resetLift()
	{
		Lift.raiseLift(1.00);
		Timer.delay(0.75);
		Lift.stopLift();
	}
}

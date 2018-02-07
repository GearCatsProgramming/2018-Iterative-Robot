package org.usfirst.frc.team6500.robot.auto;

import org.usfirst.frc.team6500.robot.systems.Mecanum;

import edu.wpi.first.wpilibj.Timer;

public class MyAutoClass {
	public static void goForward()
	{
		Mecanum.driveRobot(-0.5, 0.0, 0.0);
		Timer.delay(1.0);
		Mecanum.killMotors();
	}
	
	public static void testEncoders() {
		EncoderDistance.getSideRight(30, .5);
	}
}

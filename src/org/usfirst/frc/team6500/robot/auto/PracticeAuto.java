package org.usfirst.frc.team6500.robot.auto;

import org.usfirst.frc.team6500.robot.systems.Mecanum;

import edu.wpi.first.wpilibj.Timer;

public class PracticeAuto {
	public static void goForward()
	{
		Mecanum.driveRobot(0.0, 0.5, 0.0);
		Timer.delay(2.5);
		Mecanum.killMotors();
	}
}

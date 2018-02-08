package org.usfirst.frc.team6500.robot.tasks;

import org.usfirst.frc.team6500.robot.systems.Mecanum;

import edu.wpi.first.wpilibj.Timer;

public class Rotate360 extends Thread {
	public void run()
	{
		Mecanum.driveRobot(0.0, 0.0, 1.0);
		Timer.delay(1);
		Mecanum.killMotors();
	}
}

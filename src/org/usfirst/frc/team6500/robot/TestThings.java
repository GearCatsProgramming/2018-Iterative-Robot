package org.usfirst.frc.team6500.robot;

import org.usfirst.frc.team6500.robot.sensors.Encoders;
import org.usfirst.frc.team6500.robot.systems.Mecanum;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;

public class TestThings {

	public static void main(String[] args) {
		double speed = 0;
		System.out.println("Initial speed: " + speed);
		for(int x=0; x<20; x++) {
			speed = Speed.calculateSpeed(1.0, Constants.SPEED_BOOST_C);
			System.out.println(speed + " " + Speed.previousSpeed);
		}
		System.out.println("Final speed: " + speed);
	}
}

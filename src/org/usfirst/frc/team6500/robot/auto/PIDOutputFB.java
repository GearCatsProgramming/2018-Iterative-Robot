package org.usfirst.frc.team6500.robot.auto;

import org.usfirst.frc.team6500.robot.sensors.Gyro;
import org.usfirst.frc.team6500.robot.systems.Mecanum;

import edu.wpi.first.wpilibj.PIDOutput;

/**
 * PIDOutput for forward/backward movement of drivetrain
 * 
 * @author Kyle
 *
 */
public class PIDOutputFB implements PIDOutput {

	@Override
	public void pidWrite(double output) {
		final double tolerance = 3.0;
		final double correctionmultiplier = 10.0;
		
		//Corrects the angle of the robot while driving to reduce error from driving in some weird angle
		double angle = Gyro.getAngle();
		System.out.println("Angle is... " + angle);
		if (angle > tolerance)
		{
			angle = -Math.abs(angle / 360.0) * correctionmultiplier;
			System.out.println("Correcting too far right... " + angle);
		}
		else if (angle < -tolerance)
		{
			angle = Math.abs(angle / 360.0) * correctionmultiplier;
			System.out.println("Correcting too far left... " + angle);
		}
		else
		{
			angle = 0.0;
		}
		Mecanum.driveRobot(0.0, output, angle);
	}

}
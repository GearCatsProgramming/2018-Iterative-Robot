package org.usfirst.frc.team6500.robot.auto;

import org.usfirst.frc.team6500.robot.systems.Mecanum;

import edu.wpi.first.wpilibj.PIDOutput;

public class PIDOutputZ implements PIDOutput {

	@Override
	public void pidWrite(double output) {
		Mecanum.driveRobot(0.0, 0.0, output);
	}

}
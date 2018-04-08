package org.usfirst.frc.team6500.robot.auto;

import org.usfirst.frc.team6500.robot.systems.Mecanum;

import edu.wpi.first.wpilibj.PIDOutput;

/**
 * PIDOutput for right/left movement of drivetrain
 * 
 * @author Kyle
 *
 */
public class PIDOutputLR implements PIDOutput {

	@Override
	public void pidWrite(double output) {
		Mecanum.driveRobot(output, 0.0, 0.0);
	}

}
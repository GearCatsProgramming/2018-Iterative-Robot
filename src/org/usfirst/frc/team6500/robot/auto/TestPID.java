package org.usfirst.frc.team6500.robot.auto;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

public class TestPID extends PIDController {
	//constructor
	public TestPID(PIDSource source, PIDOutput output) {
		//Name, P, I, D
		super(1.0, 1.0, 1.0, source, output);
	}
}
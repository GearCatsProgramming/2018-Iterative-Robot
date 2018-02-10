package org.usfirst.frc.team6500.robot.pid;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class PidGyro extends PIDSubsystem {

	public PidGyro() {
		super("Gyro", 1, 2, 3);
		setAbsoluteTolerance(0.05);
	}
	
	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
	
	public void setTarget(double degrees) {
		setSetpoint(degrees);
	}

}

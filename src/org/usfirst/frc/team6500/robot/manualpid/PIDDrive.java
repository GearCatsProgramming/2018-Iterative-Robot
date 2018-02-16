package org.usfirst.frc.team6500.robot.manualpid;

import org.usfirst.frc.team6500.robot.sensors.Encoders;

public class PIDDrive extends Thread{
	static ManualPID fleft = new ManualPID(Encoders.flenc);
	static ManualPID fright = new ManualPID(Encoders.frenc);
	static ManualPID bleft = new ManualPID(Encoders.blenc);
	static ManualPID bright = new ManualPID(Encoders.brenc);
	
	public static void drive(double distX, double distY,double targetAngle) {
		
		
		fleft.setSetpoint(distX + distY + targetAngle);
		fright.setSetpoint(distX - distY + targetAngle);
		bleft.setSetpoint(distX + distY - targetAngle);
		bright.setSetpoint(distX - distY - targetAngle);
	}
	
	public static void main(String[] args) {
		
	}
}

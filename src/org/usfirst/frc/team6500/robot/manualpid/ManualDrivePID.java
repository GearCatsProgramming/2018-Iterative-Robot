package org.usfirst.frc.team6500.robot.manualpid;

import java.util.concurrent.TimeUnit;

import org.usfirst.frc.team6500.robot.Constants;

public class ManualDrivePID{
	final double P = Constants.PID_P;
	final double I = Constants.PID_I;
	final double D = Constants.PID_D;
	double integral, previous_error, setpoint;
	double pidCalc = 0;
	double acceptableError = 0;
	ManualTestSource source;
	
	public ManualDrivePID(ManualTestSource source) {
		System.out.println("Drive PID system created.");
		this.source = source;
		pidCalc = 0;
	}
	
	public void setSetpoint(int setpoint) {
		this.setpoint = setpoint;
	}
	
	public void PID() {
		double error = setpoint - source.pidGet();
		this.integral += (error*0.02);
		double derivative = (error - this.previous_error) / 0.02;
//		if(P*error + I*this.integral + D*derivative < maxChange) {
			this.pidCalc += P*error + I*this.integral + D*derivative;
//		} else {
//			this.pidCalc += maxChange;
//		}
		this.previous_error = error;
	}
	
	public void execute() {
		PID();
		source.pidAdd(pidCalc/2);
		System.out.println("The value is currently: " + pidCalc);
	}
	
	public boolean isInBounds() {
		return pidCalc > setpoint - acceptableError && pidCalc < setpoint + acceptableError;
	}
	
	public static void main(String[] args) {
		System.out.println("Initiating test sequence.");
		ManualTestSource testSource = new ManualTestSource();
		ManualDrivePID testPID = new ManualDrivePID(testSource);
		testPID.setSetpoint(120);
		while(!testPID.isInBounds()) {
			testPID.execute();
			try {
				TimeUnit.SECONDS.sleep((long) 1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

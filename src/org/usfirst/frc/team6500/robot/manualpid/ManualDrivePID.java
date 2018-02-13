package org.usfirst.frc.team6500.robot.manualpid;

import java.util.concurrent.TimeUnit;

public class ManualDrivePID{
	final double P = 0.4;
	final double I = 0;
	final double D = 0;
	double integral, previous_error, setpoint = 0;
	double pidCalc = 0;
	double acceptableError = 200;
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
			this.pidCalc = P*error + I*this.integral + D*derivative;
//		} else {
//			this.pidCalc += maxChange;
//		}
	}
	
	public void execute() {
		PID();
		source.pidAdd(pidCalc);
		System.out.println("The value is currently: " + pidCalc);
	}
	
	public boolean isInBounds() {
		return pidCalc > setpoint - acceptableError && pidCalc < setpoint + acceptableError;
	}
	
	public static void main(String[] args) {
		ManualTestSource testSource = new ManualTestSource();
		ManualDrivePID testPID = new ManualDrivePID(testSource);
		testPID.setSetpoint(10000);
		while(!testPID.isInBounds()) {
			testPID.execute();
			try {
				TimeUnit.SECONDS.sleep((long) 1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

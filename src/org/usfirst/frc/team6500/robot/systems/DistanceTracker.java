package org.usfirst.frc.team6500.robot.systems;

//XXX Delete this; used for testing only
public class DistanceTracker {
	int P, I, D = 1;
	int integral, previous_error, setpoint = 0;
	double error, derivative;
	
	public void setSetpoint(int setpoint) {
		this.setpoint = setpoint;
	}
	
	public void PID() {
        error = setpoint - Gyro.getAngle(); // Error = Target - Actual
        this.integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
        derivative = (error - this.previous_error) / .02;
        this.rcw = P*error + I*this.integral + D*derivative;
	}
	
    public void execute()
    {
        PID();
    }
}

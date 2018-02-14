package org.usfirst.frc.team6500.robot.auto;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDWrapper extends Thread
{
	private PIDSource input;
	private PIDOutput output;
	private MiniPID PID;
	
	public PIDWrapper(double p, double i, double d, PIDSource input, PIDOutput output, double target, double tolerance, double outlow, double outhigh)
	{
		this.input = input;
		this.output = output;
		
		this.PID = new MiniPID(p, i, d);
		this.PID.setSetpoint(target);
		this.PID.setSetpointRange(tolerance);
		this.PID.setOutputLimits(outlow, outhigh);
	}
	
	public void run()
	{
		while (true)
		{
			double output = this.PID.getOutput(this.input.pidGet());
			
			if (Math.abs(output) == 0)
			{
				this.output.pidWrite(0.0);
				this.interrupt();
			}
			else
			{
				this.output.pidWrite(output);
			}
			
			SmartDashboard.putNumber("PIDOutput", output);
			
			Timer.delay(0.05);
		}
	}
}

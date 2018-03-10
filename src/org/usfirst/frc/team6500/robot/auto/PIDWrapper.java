package org.usfirst.frc.team6500.robot.auto;

import org.usfirst.frc.team6500.robot.systems.Mecanum;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Simple wrapper around MiniPID
 * 
 * @author Kyle
 *
 */
public class PIDWrapper extends Thread
{
	private PIDSource input;
	private PIDOutput output;
	private MiniPID PID;
	private boolean realPID = true;
	private double speed;
	private double time;
	private boolean leftright;
	private int CorrectCount;
	
	/**
	 * Constructor, provide all information here
	 * 
	 * @param p Proportional part of PID calculation
	 * @param i Integral part of PID calculation
	 * @param d Derivative part of PID calculation
	 * @param input PIDSource to use as the input value getter
	 * @param output PIDOutput to use to push the values to
	 * @param target What value we are trying to acheive
	 * @param tolerance How far off from target is it ok to be
	 * @param outlow The smallest value to use for output
	 * @param outhigh The largest value to use for output
	 */
	public PIDWrapper(double p, double i, double d, PIDSource input, PIDOutput output,
			double target, double tolerance, double outlow, double outhigh) {
		this.input = input;
		this.output = output;
		
		this.PID = new MiniPID(p, i, d);
		this.PID.setSetpoint(target);
		this.PID.setSetpointRange(tolerance);
		this.PID.setOutputLimits(outlow, outhigh);
		this.realPID = true;
		this.CorrectCount = 0;
	}
	
	public PIDWrapper(double time, double speed, boolean leftright) {
		this.realPID = false;
		this.time = time;
		this.speed = speed;
		this.leftright = true;
		this.CorrectCount = 0;
	}
	
	/**
	 * Runs the PID calculation until we reach our target; then stop
	 */
	@SuppressWarnings("static-access")
	public void run()
	{
		this.interrupted();
		if (this.realPID) {
			while (true)
			{
				double input = this.input.pidGet();
				double output = this.PID.getOutput(input);
				
				if (Math.abs(output) == 0)
				{
					this.output.pidWrite(0.0);
					this.CorrectCount++;
					if (this.CorrectCount > 50)
					{
						this.interrupt();
						System.out.println("Broken.");
						break;
					}
				}
				else
				{
					this.output.pidWrite(output);
				}
				
				SmartDashboard.putNumber("PIDInput", input);
				SmartDashboard.putNumber("PIDOutput", output);
				
				Timer.delay(0.05);
			}
		}
		else
		{
			if (leftright) {
				Mecanum.driveRobot(0.0, this.speed, 0.0);
			}
			else
			{
				Mecanum.driveRobot(this.speed, 0.0, 0.0);
			}
			
			Timer.delay(this.time);
			this.interrupt();
		}
	}
}

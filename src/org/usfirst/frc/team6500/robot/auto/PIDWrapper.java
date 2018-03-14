package org.usfirst.frc.team6500.robot.auto;

import java.util.concurrent.TimeUnit;

import org.usfirst.frc.team6500.robot.Robot;
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
	private PIDSource inputSource;
	private PIDOutput outputSource;
	private MiniPID PID;
	private boolean right;
	private int CorrectCount;
	private double input;
	private double output;
	private Robot robot;
	
	/**
	 * Constructor, provide all information here
	 * 
	 * @param p Proportional part of PID calculation
	 * @param i Integral part of PID calculation
	 * @param d Derivative part of PID calculation
	 * @param newInputSource PIDSource to use as the input value getter
	 * @param newOutputSource PIDOutput to use to push the values to
	 * @param target What value we are trying to acheive
	 * @param tolerance How far off from target is it ok to be
	 * @param outlow The smallest value to use for output
	 * @param outhigh The largest value to use for output
	 */
	public PIDWrapper(double p, double i, double d, PIDSource newInputSource, PIDOutput newOutputSource,
			double target, double tolerance, double outlow, double outhigh, boolean right, Robot theRobot) {
		this.inputSource = newInputSource;
		this.outputSource = newOutputSource;
		
		this.PID = new MiniPID(p, i, d);
		this.PID.setSetpoint(target);
		this.PID.setSetpointRange(tolerance);
		this.PID.setOutputLimits(outlow, outhigh);
		this.CorrectCount = 0;
		this.right = right;
		this.robot = theRobot;
	}
	
	public PIDWrapper(double time, double speed, boolean right) {

	}
	
	/**
	 * Runs the PID calculation until we reach our target; then stop
	 */
	@SuppressWarnings("static-access")
	public void run()
	{
		while (!this.isInterrupted())
		{
			if(this.robot.isOperatorControl())
			{
				this.interrupt();
				System.out.println("Broken.");
				break;
			}
				if(right) {
					input = ((PIDInputDrive)inputSource).pidGetRight();
				} else {
					input = inputSource.pidGet();
				}
				double output = this.PID.getOutput(input);
				if (Math.abs(output) == 0)
				{
					this.outputSource.pidWrite(0.0);
					this.CorrectCount++;
					if (this.CorrectCount > 10)
					{
						this.interrupt();
						System.out.println("Broken.");
						break;
					}
				}
				else
				{
					this.outputSource.pidWrite(output);
				}
				
				SmartDashboard.putNumber("PIDInput", input);
				SmartDashboard.putNumber("PIDOutput", output);
				
				Timer.delay(0.05);
		}
	}
}

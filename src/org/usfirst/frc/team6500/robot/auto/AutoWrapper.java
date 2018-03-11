package org.usfirst.frc.team6500.robot.auto;

import org.usfirst.frc.team6500.robot.Constants;
import org.usfirst.frc.team6500.robot.Robot;
import org.usfirst.frc.team6500.robot.sensors.Encoders;
import org.usfirst.frc.team6500.robot.sensors.Gyro;

/**
 * Simple wrapper around the PID functions and the robot's movement
 * The commented code in this class is in case none of the newer PID stuff works and we need to revert
 * 
 * @author Kyle
 *
 */
public class AutoWrapper
{
	/**
	 * Move the robot approximately inches forward or backward at speed
	 * 
	 * @param inches How many inches to go forward or backward, backward is negative and positive is forward
	 * @param speed How fast to move the robot
	 */
	public static void goForward(double inches, double speed)
	{
		Encoders.resetAllEncoders();
		if (inches < speed)	{ Encoders.setDirection(Constants.DIRECTION_BACKWARDS); }
		else { Encoders.setDirection(Constants.DIRECTION_FORWARD); }
		//System.out.println("Encoders reset.");
		PIDWrapper autoTester = new PIDWrapper(1.0, 1.0, 1.0, new PIDInputDrive(), new PIDOutputFB(), inches, 2.5, -speed, speed);
		
		Robot.hitList.add(autoTester);
		
		autoTester.start();
		
		while (!autoTester.isInterrupted()) { }
		
		System.out.println("Donezo con forward.");
		//TestPID autoTester = new TestPID(new PIDInputY(), new PIDOutputFB());
		
		//autoTester.disable();
		
		//autoTester.setSetpoint(20);
		//autoTester.setOutputRange(-0.5, 0.5);
		//autoTester.setAbsoluteTolerance(2.5);
		
		//autoTester.enable();
		
		//while (true)
		//{ 
		//	if (autoTester.onTarget())
		//	{
		//		autoTester.disable();
		//		break;
		//	}
		//}
	}
	
	/**
	 * Move the robot approximately inches left or right at speed
	 * 
	 * @param inches How many inches to go left or right, left is negative and positive is right
	 * @param speed How fast to move the robot
	 */
	public static void leftRight(double inches, double speed)
	{
		Encoders.resetAllEncoders();
		if (inches < speed)	{ Encoders.setDirection(Constants.DIRECTION_LEFT); }
		else { Encoders.setDirection(Constants.DIRECTION_RIGHT); }
		//System.out.println("going right booooiiiiisss");
		PIDWrapper autoTester = new PIDWrapper(1.0, 1.0, 1.0, new PIDInputDrive(), new PIDOutputLR(), inches * 3.5, 2.5, -speed, speed);
		
		Robot.hitList.add(autoTester);
		
		autoTester.start();
		
		while (!autoTester.isInterrupted()) { }
		
		System.out.println("Donezo con side.");
//		TestPID autoTester = new TestPID(new PIDInputX(), new PIDOutputLR());
//		
//		autoTester.disable();
//		
//		autoTester.setSetpoint(20);
//		autoTester.setOutputRange(-0.5, 0.5);
//		autoTester.setAbsoluteTolerance(2.5);
//		
//		autoTester.enable();
//		
//		while (true)
//		{ 
//			if (autoTester.onTarget())
//			{
//				autoTester.disable();
//				break;
//			}
//		}
	}
	
	/**
	 * Turn the robot approximately degrees at speed
	 * 
	 * @param degrees How many degrees to rotate, positive is clockwise and negative is counterclockwise
	 * @param speed How fast to turn
	 */
	public static void rotate(double degrees, double speed)
	{
		Gyro.reset();
		PIDWrapper autoTester = new PIDWrapper(1.0, 1.0, 1.0, new PIDInputGyro(), new PIDOutputZ(), degrees, 2.5, -speed, speed);
		
		Robot.hitList.add(autoTester);
		
		autoTester.start();
		
		while (!autoTester.isInterrupted()) { }
		
		System.out.println("Donezo con rotate.");
//		TestPID autoTester = new TestPID(new PIDInputGyro(), new PIDOutputZ());
//		
//		autoTester.disable();
//		
//		autoTester.setSetpoint(-50.0);
//		autoTester.setOutputRange(-0.5, 0.5);
//		autoTester.setAbsoluteTolerance(2.5);
//		
//		autoTester.enable();
//		
//		while (true)
//		{ 
//			if (autoTester.onTarget())
//			{
//				autoTester.disable();
//				break;
//			}
//		}
	}
}
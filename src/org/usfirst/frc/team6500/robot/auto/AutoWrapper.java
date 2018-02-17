package org.usfirst.frc.team6500.robot.auto;

import org.usfirst.frc.team6500.robot.Constants;
import org.usfirst.frc.team6500.robot.sensors.Encoders;
import org.usfirst.frc.team6500.robot.sensors.Gyro;

public class AutoWrapper
{
	public static void goForward(double inches, double speed)
	{
		Encoders.resetAllEncoders();
		Encoders.setDirection(Constants.DIRECTION_FORWARD);
		PIDWrapper autoTester = new PIDWrapper(1.0, 1.0, 1.0, new PIDInputTest(), new PIDOutputFB(), inches, 2.5, -speed, speed);
		
		autoTester.start();
		
		while (!autoTester.isInterrupted()) { }
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

	public static void leftRight(double inches, double speed)
	{
		Encoders.resetAllEncoders();
		Encoders.setDirection(Constants.DIRECTION_RIGHT);
		PIDWrapper autoTester = new PIDWrapper(1.0, 1.0, 1.0, new PIDInputX(), new PIDOutputLR(), inches, 2.5, -speed, speed);
		
		autoTester.start();
		
		while (!autoTester.isInterrupted()) { }
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
	
	public static void rotate(double degrees, double speed)
	{
		Gyro.reset();
		PIDWrapper autoTester = new PIDWrapper(1.0, 1.0, 1.0, new PIDInputGyro(), new PIDOutputZ(), degrees, 2.5, -speed, speed);
		
		autoTester.start();
		
		while (!autoTester.isInterrupted()) { }
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
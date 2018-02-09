package org.usfirst.frc.team6500.robot.systems;

import org.usfirst.frc.team6500.robot.Constants;

/**
 * The grabber.
 * 
 * @author Kyle Miller
 */
import edu.wpi.first.wpilibj.Spark;

public class Grabber {
	static Spark leftGrabMotor, rightGrabMotor;
	
	/**
	 * Prepares the grabber for usage. Must be called before any other function.
	 * 
	 * @author Kyle Miller
	 */
	public static void intializeGrabber()
	{
		leftGrabMotor = new Spark(Constants.GRABBER_LEFT);
		rightGrabMotor = new Spark(Constants.GRABBER_RIGHT);
		
		rightGrabMotor.setInverted(true);
	}
	
	/**
	 * Attempts to grab a cube.  MANUALLY STOPPED!
	 */
	public static void grabCube()
	{
		leftGrabMotor.set(1.0);
		rightGrabMotor.set(1.0);
	}
	
	/**
	 * Attempts to eject a cube.  MANUALLY STOPPED!
	 */
	public static void ejectCube()
	{
		leftGrabMotor.set(-1.0);
		rightGrabMotor.set(-1.0);
	}
	
	/**
	 * Attempts to grab a cube at a custom speed.  MANUALLY STOPPED!
	 * 
	 * @param speed What speed to attempt to grab the cube at
	 */
	public static void grabCubeCustom(double speed)
	{
		leftGrabMotor.set(speed);
		rightGrabMotor.set(speed);
	}
	
	/**
	 * Attempts to eject a cube at a custom speed.  MANUALLY STOPPED!
	 * 
	 * @param speed What speed to attempt to eject the cube at
	 */
	public static void ejectCubeCustom(double speed)
	{
		leftGrabMotor.set(speed);
		rightGrabMotor.set(speed);
	}
	
	/**
	 * Stops the grab motors.
	 */
	public static void killGrab()
	{
		leftGrabMotor.set(0.0);
		rightGrabMotor.set(0.0);
	}
	
}

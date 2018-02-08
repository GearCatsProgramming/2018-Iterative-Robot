package org.usfirst.frc.team6500.robot.systems;

import org.usfirst.frc.team6500.robot.Constants;

import edu.wpi.first.wpilibj.Joystick;

public class DriveInput {
	//Create an object for the controller based on the Joystick class (or XboxController)
	 public static Joystick controllerR, controllerL;
	
	public static void initializeInput()
	{
		controllerR = new Joystick(0);
		controllerL = new Joystick(1);
	}
	
	public static double getThrottle(Joystick joystick)
	{
		//Calculate the speed multiplier by the position of the throttle
		//However, the value returned by the getThrottle function ranges
		//from -1 to 1, and we need a range of 0 to 1, so we add 1 to
		//make the range 0 to 2
		double multiplier = joystick.getThrottle() + 1;
		//Then we divide by 2 to get the correct range of 0 to 1
		multiplier = multiplier / 2;
		//The thing is, the value returned by getThrottle conflicts with
		//the indicators on the joystick.  When you move the throttle towards
		//the plus indicator, it decreases the value, and vice versa for
		//the negative indicator.  So we have to inverse the value by doing
		//1 minus our previous value.
		multiplier = 1 - multiplier;
		//Base speed multiplier
		multiplier = multiplier * Constants.SPEED_BASE;
		
		return multiplier;
	}

	public static boolean getTrigger(Joystick joystick)
	{
		return joystick.getTrigger();
	}
	
	public static double getAxis(int axis, Joystick joystick)
	{
		if (axis == Constants.INPUT_AXIS_X)
		{
			return joystick.getX();
		}
		else if (axis == Constants.INPUT_AXIS_Y)
		{
			return joystick.getY();
		}
		else if (axis == Constants.INPUT_AXIS_Z)
		{
			return joystick.getZ();
		}
		
		return 0.0;
	}
	
	public static boolean getButton(int buttonid, Joystick joystick)
	{
		return joystick.getRawButton(buttonid);
	}
}

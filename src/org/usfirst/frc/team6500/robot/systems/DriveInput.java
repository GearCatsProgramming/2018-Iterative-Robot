package org.usfirst.frc.team6500.robot.systems;

import org.usfirst.frc.team6500.robot.Ports;

import edu.wpi.first.wpilibj.Joystick;

public class DriveInput {
	//Create an object for the controller based on the Joystick class (or XboxController)
	static Joystick controllerR, controllerL;
	
	public static void initializeInput()
	{
		controllerR = new Joystick(0);
		//controllerL = new Joystick(1);
	}
	
	public static double getThrottle()
	{
		//Calculate the speed multiplier by the position of the throttle
		//However, the value returned by the getThrottle function ranges
		//from -1 to 1, and we need a range of 0 to 1, so we add 1 to
		//make the range 0 to 2
		double multiplier = controllerR.getThrottle() + 1;
		//Then we divide by 2 to get the correct range of 0 to 1
		multiplier = multiplier / 2;
		//The thing is, the value returned by getThrottle conflicts with
		//the indicators on the joystick.  When you move the throttle towards
		//the plus indicator, it decreases the value, and vice versa for
		//the negative indicator.  So we have to inverse the value by doing
		//1 minus our previous value.
		multiplier = 1 - multiplier;
		//Base speed multiplier
		multiplier = multiplier * Ports.SPEED_BASE;
		
		return multiplier;
	}

	public static boolean getTrigger()
	{
		return controllerR.getTrigger();
	}
	
	public static double getAxis(int axis)
	{
		if (axis == Ports.INPUT_AXIS_X)
		{
			return controllerR.getX();
		}
		else if (axis == Ports.INPUT_AXIS_Y)
		{
			return controllerR.getY();
		}
		else if (axis == Ports.INPUT_AXIS_Z)
		{
			return controllerR.getZ();
		}
		
		return 0.0;
	}
	
	public static boolean getButton(int buttonid)
	{
		return controllerR.getRawButton(buttonid);
	}
}

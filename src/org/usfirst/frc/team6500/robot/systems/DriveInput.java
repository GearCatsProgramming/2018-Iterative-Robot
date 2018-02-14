package org.usfirst.frc.team6500.robot.systems;

import org.usfirst.frc.team6500.robot.Constants;

import edu.wpi.first.wpilibj.Joystick;

/**A class used for controlling the joysticks and receiving input. Used initializeInput() before anything else is done.
 *@author Kyle Miller
 */
public class DriveInput {
	//Create an object for the controller based on the Joystick class (or XboxController)
	public static Joystick controllerR;
	public static Joystick controllerL;
	
	 /**Call this method to initalize the controllers. Must be called before anything else in this class is used.
	  * @author Kyle Miller
	  * @author Thomas Dearth
	  */
	public static void initializeInput()
	{
		controllerR = new Joystick(0);
		controllerL = new Joystick(1);
	}
	
	/**Calculates the multiplier for the wheel by the position of the throttle
	 * @author Kyle Miller
	 * @param joystick The joystick to detect from
	 * @return The throttle of the wheel
	 */
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

	/**Detects whether the trigger on joystick is pressed or not
	 * @author Kyle Miller
	 * @param joystick The joystick used
	 * @return True if the trigger is pressed; false otherwise
	 */
	public static boolean getTrigger(Joystick joystick)
	{
		return joystick.getTrigger();
	}
	
	/**Gets the direction the joystick is pushed in on axis
	 * @author Kyle Miller
	 * @param axis The axis being measured; use Constants.INPUT_AXIS variables
	 * @param joystick The joystick being used
	 * @return A value from -1 to 1 on the requested axis
	 */
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
	
	/**Returns whether the button is pressed or not
	 * @author Kyle Miller
	 * @param buttonid The button being pressed; check the joystick for the ID
	 * @param joystick The joystick being checked
	 * @return True if the button is pressed
	 */
	public static boolean getButton(int buttonid, Joystick joystick)
	{
		return joystick.getRawButton(buttonid);
	}
}

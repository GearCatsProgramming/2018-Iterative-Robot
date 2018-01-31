package org.usfirst.frc.team6500.robot;
import edu.wpi.first.wpilibj.Joystick;

public class GCJoystick extends Joystick{
	
	private final double MULTIPLIER = 0.80;

	public GCJoystick(int port) {
		super(port);
		// TODO Auto-generated constructor stub
	}

	
	public double getThrottle () {
		double rate = super.getThrottle();
		
		
		//Calculate the speed multiplier by the position of the throttle
		//However, the value returned by the getThrottle function ranges
		//from -1 to 1, and we need a range of 0 to 1, so we add 1 to
		//make the range 0 to 2
		rate = rate + 1;
				
		//Then we divide by 2 to get the correct range of 0 to 1
		rate = rate / 2;
		
		//The thing is, the value returned by getThrottle conflicts with
		//the indicators on the joystick.  When you move the throttle towards
		//the plus indicator, it decreases the value, and vice versa for
		//the negative indicator.  So we have to inverse the value by doing
		//1 minus our previous value.
		
		rate = 1 - rate ;
    //Base speed multiplier
		rate = rate * MULTIPLIER;
		
		return rate;
	}
	
}

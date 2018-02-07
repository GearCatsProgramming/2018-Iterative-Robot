package org.usfirst.frc.team6500.robot.systems;

import org.usfirst.frc.team6500.robot.Constants;

import edu.wpi.first.wpilibj.Spark;

/**Motors used for grabbing claw
 * @author AndrewLopez
 */

public class Grabber {

	static Spark left, right;
	static boolean isReady;
	
	public static void initializeGrabber(){
		
		left = new Spark(Constants.GRABBER_LEFT);
		right = new Spark(Constants.GRABBER_RIGHT);
		right.setInverted(true);
		
		left.setSafetyEnabled(false);
		right.setSafetyEnabled(false);
		
		isReady = true;
	}
	
	public static void grab(){
	
		left.set(1);
		right.set(1);
		
	}
	public static void releaseShoot(){
		
		left.set(-1);
		right.set(-1);
		
	}
	public static void releaseDrop(){
		
		left.set(-.5);
		right.set(-.5);
		
	}
	public static void grabberStop(){
		
		left.set(0);
		right.set(0);
		
	}
	
	
}

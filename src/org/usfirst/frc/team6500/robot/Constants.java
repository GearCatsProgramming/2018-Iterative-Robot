package org.usfirst.frc.team6500.robot;

/**Contains constants which are used across classes, designed to be configurable.
 *
 */
public final class Constants {
	public final static int DRIVE_FRONTLEFT = 2;
	public final static int DRIVE_FRONTRIGHT = 3;
	public final static int DRIVE_REARLEFT = 0;
	public final static int DRIVE_REARRIGHT = 1;
	
	
	public final static int ENCODER_INPUT_FL_A = 6;
	public final static int ENCODER_INPUT_FL_B = 7;
	
	public final static int ENCODER_INPUT_FR_A = 4;
	public final static int ENCODER_INPUT_FR_B = 5;
	
	public final static int ENCODER_INPUT_RL_A = 0;
	public final static int ENCODER_INPUT_RL_B = 1;
	
	public final static int ENCODER_INPUT_RR_A = 2;
	public final static int ENCODER_INPUT_RR_B = 3;
	
	
	public final static double ENCODER_DISTANCE_PER_PULSE = 0.11;
	
	
	public final static double SPEED_BASE = 0.85;
	public final static double SPEED_BOOST_A = 0.05;
	public final static double SPEED_BOOST_B = 0.10;
	public final static double SPEED_BOOST_C = 0.15;
	public final static double SPEED_DEADBAND = 0.1;
	
	
	public final static int ENCODER_FRONTLEFT = 0;
	public final static int ENCODER_FRONTRIGHT = 1;
	public final static int ENCODER_REARLEFT = 2;
	public final static int ENCODER_REARRIGHT = 3;
	
	public final static int INPUT_AXIS_X = 1;
	public final static int INPUT_AXIS_Y = 0;
	public final static int INPUT_AXIS_Z = 2;
	
	//TODO: Set climbing motor ports
	public final static int CLIMBING_MOTOR1 = 0;
	public final static int CLIMBING_MOTOR2 = 0;
	
	//TODO: Set winch motor ports
	public final static int WINCH_MOTOR1 = 0;
	public final static int WINCH_MOTOR2 = 0;
	
	//TODO: Set grabber motor ports
	public final static int GRABBER_LEFT = 0;
	public final static int GRABBER_RIGHT = 0;
	
	public final static int DIRECTION_FORWARD = 0;
	public final static int DIRECTION_BACKWARDS = 1;
	public final static int DIRECTION_RIGHT = 2;
	public final static int DIRECTION_LEFT = 3;
	
	//TODO: Configure PID values
	public static final double PID_P = 1;
	public static final double PID_I = 0;
	public static final double PID_D = 0;
	

	/**The number of inches travelled per angle. TODO: Configure number of units per degree*/
	public static final double ANGLE_TO_DISTANCE = 0;
	/**The speed of the robot converted to the speed. TODO: Configure distance-to-speed ratio*/
	public static final double INCHES_TO_SPEED = 0;
}

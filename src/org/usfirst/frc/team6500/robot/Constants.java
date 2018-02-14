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
	
	
	public final static double ENCODER_DISTANCE_PER_PULSE = 1.25;
	
	
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
	
	//TODO: Find proper PID setups
	public final static double PID_P = .1;		//Increase until oscillation, then use Zeigler-Nichols tuning method
	public final static double PID_I = 0;
	public final static double PID_D = 0;
	
	//TODO: Set grabber ports
	public static final int GRABBER_MOTOR1 = 0;
	public static final int GRABBER_MOTOR2 = 0;
}

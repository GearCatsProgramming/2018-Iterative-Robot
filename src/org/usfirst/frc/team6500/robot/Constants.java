package org.usfirst.frc.team6500.robot;

/**Contains constants which are used across classes, designed to be configurable.
 *
 */
public final class Constants {
	public final static int DRIVE_FRONTLEFT = 2;
	public final static int DRIVE_FRONTRIGHT = 3;
	public final static int DRIVE_REARLEFT = 0;
	public final static int DRIVE_REARRIGHT = 1;
	
	
	public final static int ENCODER_INPUT_FL_A = 4;
	public final static int ENCODER_INPUT_FL_B = 5;
	
	public final static int ENCODER_INPUT_FR_A = 6;
	public final static int ENCODER_INPUT_FR_B = 7;
	
	public final static int ENCODER_INPUT_RL_A = 2;
	public final static int ENCODER_INPUT_RL_B = 3;
	
	public final static int ENCODER_INPUT_RR_A = 0;
	public final static int ENCODER_INPUT_RR_B = 1;
	
	
	public final static double ENCODER_DISTANCE_PER_PULSE = 0.0990487842984034;
	//0.1043508135179383
	//0.0960629921259843
	//0.0986475735879077
	
	
	public final static double SPEED_BASE = 0.85;
	public final static double SPEED_BOOST_A = 0.05;
	public final static double SPEED_BOOST_B = 0.10;
	public final static double SPEED_BOOST_C = 0.15;
	public final static double SPEED_DEADBAND = 0.1;
	
	
	public final static int ENCODER_FRONTLEFT = 0;
	public final static int ENCODER_FRONTRIGHT = 1;
	public final static int ENCODER_REARLEFT = 2;
	public final static int ENCODER_REARRIGHT = 3;
	
	
	public final static int INPUT_AXIS_X = 0;
	public final static int INPUT_AXIS_Y = 1;
	public final static int INPUT_AXIS_Z = 2;
	
	
	public final static int LIFT_MOTOR_LEFT = 6;
	public final static int LIFT_MOTOR_RIGHT = 8;
	
	
	public final static int WINCH_MOTOR = 7;
	
	
	public final static int GRABBER_LEFT = 4;
	public final static int GRABBER_RIGHT = 5;
	
	
	public final static int DIRECTION_FORWARD = 0;
	public final static int DIRECTION_BACKWARDS = 1;
	public final static int DIRECTION_RIGHT = 2;
	public final static int DIRECTION_LEFT = 3;
	
	//TODO: Configure PID values
	public static final double PID_P = 1;
	public static final double PID_I = 0;
	public static final double PID_D = 0;

	//TODO: Configure distance-to-angle ratio
	public static final double ANGLE_TO_DISTANCE = 1*ENCODER_DISTANCE_PER_PULSE;
	public static final double AUTO_SPEED = 0.5;
	
	public static final double ENCODER_LR_MULTIPLIER = 1.0892243362162;
	//1.1163279
	//+1.0859728506787
	//+1.1111111111111
	//+1.05263
	//+1.08108108
}

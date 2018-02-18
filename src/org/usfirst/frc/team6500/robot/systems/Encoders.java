package org.usfirst.frc.team6500.robot.systems;

import org.usfirst.frc.team6500.robot.Constants;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;

/**Measures the distance that individual wheels have rotated. Use initializeEncoders() before use.
 * @author Kyle Miller
 */
public class Encoders {
	static DigitalInput[] encoderinputs = new DigitalInput[8];
	public static Encoder flenc, blenc, frenc, brenc;
	static int direction;
	
	/**Prepares encoders for use. Use this before any other methods from this class.
	 * @author Kyle Miller
	 */
	public static void initializeEncoders()
	{
		for (int i = 0; i < encoderinputs.length; i++) { encoderinputs[i] = new DigitalInput(i); }
		
		flenc = new Encoder(encoderinputs[Constants.ENCODER_INPUT_FL_A], encoderinputs[Constants.ENCODER_INPUT_FL_B]);
		blenc = new Encoder(encoderinputs[Constants.ENCODER_INPUT_RL_A], encoderinputs[Constants.ENCODER_INPUT_RL_B]);
		frenc = new Encoder(encoderinputs[Constants.ENCODER_INPUT_FR_A], encoderinputs[Constants.ENCODER_INPUT_FR_B]);
		brenc = new Encoder(encoderinputs[Constants.ENCODER_INPUT_RR_A], encoderinputs[Constants.ENCODER_INPUT_RR_B]);
		
		flenc.setDistancePerPulse(Constants.ENCODER_DISTANCE_PER_PULSE);
		blenc.setDistancePerPulse(Constants.ENCODER_DISTANCE_PER_PULSE);
		frenc.setDistancePerPulse(Constants.ENCODER_DISTANCE_PER_PULSE);
		brenc.setDistancePerPulse(Constants.ENCODER_DISTANCE_PER_PULSE);
		
		frenc.setReverseDirection(true);
		brenc.setReverseDirection(true);
		blenc.setReverseDirection(true);
	}
	
	/**Gets the average distance from each encoder
	 * @return The average distance
	 */
	public static double getAverageDistance()
	{
		return (flenc.getDistance() + blenc.getDistance() + frenc.getDistance() + brenc.getDistance()) / 4.0;
	}
	
	public static void setDirection(int directionId)
	{
		direction = directionId;
	}
	
	public static double getDirection()
	{
		return direction;
	}
	
	public static double getDistanceRelativeDirection()
	{
		switch (direction) {
			case Constants.DIRECTION_BACKWARDS:
				return -getAverageDistance();
			case Constants.DIRECTION_FORWARD:
				return getAverageDistance();
			case Constants.DIRECTION_LEFT:
				return ((-flenc.getDistance()) + frenc.getDistance() + blenc.getDistance() + (-brenc.getDistance())) / 4.0;
			case Constants.DIRECTION_RIGHT:
				return (flenc.getDistance() + (-frenc.getDistance()) + (-blenc.getDistance()) + brenc.getDistance()) / 4.0;
		}
		return 0.0;
	}
	
	/**Gets the distance traveled by the robot
	 * @author Kyle Miller
	 * @param encoderId The ID of the encoder to check
	 * @return The distance travelled by the encoder
	 */
	public static double getDistance(int encoderId)
	{
		switch (encoderId) {
		case Constants.ENCODER_FRONTLEFT:
			return flenc.getDistance();
		case Constants.ENCODER_FRONTRIGHT:
			return frenc.getDistance();
		case Constants.ENCODER_REARLEFT:
			return blenc.getDistance();
		case Constants.ENCODER_REARRIGHT:
			return brenc.getDistance();
		}
		return 0.0;
	}
	
	/**Resets the distance an encoder has gone.
	 * @author Thomas Dearth
	 * @param encoder The encoder to reset
	 */
	public static void resetEncoder(int encoderId) {
		 switch (encoderId) {
		case Constants.ENCODER_FRONTLEFT:
			flenc.reset();
		case Constants.ENCODER_FRONTRIGHT:
			frenc.reset();
		case Constants.ENCODER_REARLEFT:
			blenc.reset();
		case Constants.ENCODER_REARRIGHT:
			brenc.reset();
		}
	}
	
	/** Resets all of the distances traveled by the encoders
	 * 
	 */
	public static void resetAllEncoders() {
		resetEncoder(Constants.ENCODER_FRONTLEFT);
		resetEncoder(Constants.ENCODER_FRONTRIGHT);
		resetEncoder(Constants.ENCODER_REARLEFT);
		resetEncoder(Constants.ENCODER_REARRIGHT);
	}
}

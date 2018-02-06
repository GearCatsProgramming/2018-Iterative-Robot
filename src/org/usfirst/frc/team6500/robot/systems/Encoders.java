package org.usfirst.frc.team6500.robot.systems;

import org.usfirst.frc.team6500.robot.Constants;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;

public class Encoders {
	static DigitalInput[] encoderinputs = new DigitalInput[8];
	static Encoder flenc, blenc, frenc, brenc;
	
	public static void initializeEncoders()
	{
		for (int i = 0; i < encoderinputs.length; i++) { encoderinputs[i] = new DigitalInput(i); }
		
		flenc = new Encoder(encoderinputs[Constants.ENCODER_INPUT_FL_A], encoderinputs[Constants.ENCODER_INPUT_FL_B]);
		blenc = new Encoder(encoderinputs[Constants.ENCODER_INPUT_RL_A], encoderinputs[Constants.ENCODER_INPUT_RL_A]);
		frenc = new Encoder(encoderinputs[Constants.ENCODER_INPUT_FR_A], encoderinputs[Constants.ENCODER_INPUT_FR_A]);
		brenc = new Encoder(encoderinputs[Constants.ENCODER_INPUT_RR_A], encoderinputs[Constants.ENCODER_INPUT_RR_A]);
		
		flenc.setDistancePerPulse(Constants.ENCODER_DISTANCE_PER_PULSE);
		blenc.setDistancePerPulse(Constants.ENCODER_DISTANCE_PER_PULSE);
		frenc.setDistancePerPulse(Constants.ENCODER_DISTANCE_PER_PULSE);
		brenc.setDistancePerPulse(Constants.ENCODER_DISTANCE_PER_PULSE);
	}
	
	public static double getAverageDistance()
	{
		return (flenc.getDistance() + blenc.getDistance() + frenc.getDistance() + brenc.getDistance()) / 4.0;
	}
	
	public static double getDistance(int encoderId)
	{
		switch (encoderId) {
		case Constants.ENCODER_FRONTLEFT:
			return flenc.getDistance();
		case Constants.ENCODER_FRONTRIGHT:
			return blenc.getDistance();
		case Constants.ENCODER_REARLEFT:
			return frenc.getDistance();
		case Constants.ENCODER_REARRIGHT:
			return brenc.getDistance();
		}
		return 0.0;
	}
}

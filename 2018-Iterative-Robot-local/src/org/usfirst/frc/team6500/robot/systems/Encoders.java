package org.usfirst.frc.team6500.robot.systems;

import org.usfirst.frc.team6500.robot.Ports;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;

public class Encoders {
	static DigitalInput[] encoderinputs = new DigitalInput[8];
	static Encoder flenc, blenc, frenc, brenc;
	
	public static void initializeEncoders()
	{
		for (int i = 0; i < encoderinputs.length; i++) { encoderinputs[i] = new DigitalInput(i); }
		
		flenc = new Encoder(encoderinputs[Ports.ENCODER_INPUT_FL_A], encoderinputs[Ports.ENCODER_INPUT_FL_B]);
		blenc = new Encoder(encoderinputs[Ports.ENCODER_INPUT_RL_A], encoderinputs[Ports.ENCODER_INPUT_RL_A]);
		frenc = new Encoder(encoderinputs[Ports.ENCODER_INPUT_FR_A], encoderinputs[Ports.ENCODER_INPUT_FR_A]);
		brenc = new Encoder(encoderinputs[Ports.ENCODER_INPUT_RR_A], encoderinputs[Ports.ENCODER_INPUT_RR_A]);
		
		flenc.setDistancePerPulse(0.0);
		blenc.setDistancePerPulse(0.0);
		frenc.setDistancePerPulse(0.0);
		brenc.setDistancePerPulse(0.0);
	}
	
	public double getAverageDistance()
	{
		return (flenc.getDistance() + blenc.getDistance() + frenc.getDistance() + brenc.getDistance()) / 4.0;
	}
	
	public double getDistance(int encoderId)
	{
		switch (encoderId) {
		case Ports.ENCODER_FRONTLEFT:
			return flenc.getDistance();
		case Ports.ENCODER_FRONTRIGHT:
			return blenc.getDistance();
		case Ports.ENCODER_REARLEFT:
			return frenc.getDistance();
		case Ports.ENCODER_REARRIGHT:
			return brenc.getDistance();
		}
		return 0.0;
	}
}

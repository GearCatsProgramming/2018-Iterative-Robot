package org.usfirst.frc.team6500.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.MecanumDrive;



public class GCMecanumDrive {

	
	class Speed{
		final double GUARD = 0.03;
		
		double m_previous = 0.0;
		boolean m_boost = false;

		static final double BOOST_RATE = 0.5;
		

		double adjustedSpeed(double new_speed) {
			double speed = new_speed;
			if (Math.abs(m_previous - speed) > GUARD)
			{
				speed = (speed + m_previous) / 2;
			}
			
			if (m_boost) {
				speed = speed * BOOST_RATE;
			}
						
			m_previous = speed;
			
			return speed;
		}
		
		void setBoost(boolean boost) {
			m_boost = boost;
		}
		
		double getSpeed() {
			return m_previous; 
		}
				
	}
	
	//We have spark motor controllers so we are using the Spark class to initiate our front left, front right, back left, & back right motors
	Spark fleft, bleft, fright, bright;
	
	DigitalInput[] encoderinputs = new DigitalInput[8];
	
	Encoder flenc, blenc, frenc, brenc;
	
	Speed m_xspeed, m_yspeed, m_zspeed;
	
	double m_xspeedprevious, m_yspeedprevious, m_zspeedprevious;
	
	boolean m_nitro = false;
	
	double m_throttle = 0;
	
	boolean m_leftboost, m_rightboost = false;

	//Creating a RobotDrive object called drive so we can control the motors as a whole, not individually
	MecanumDrive drive;
	
	public GCMecanumDrive () {
		//Initializing the motor controllers on the correct ports
				fleft = new Spark(0);
				bleft = new Spark(1);
				fright = new Spark(2);
				bright = new Spark(3);
				
				flenc = new Encoder(encoderinputs[0], encoderinputs[1]);
				blenc = new Encoder(encoderinputs[2], encoderinputs[3]);
				frenc = new Encoder(encoderinputs[4], encoderinputs[5]);
				brenc = new Encoder(encoderinputs[6], encoderinputs[7]);
				
				flenc.setDistancePerPulse(0.0);
				blenc.setDistancePerPulse(0.0);
				frenc.setDistancePerPulse(0.0);
				brenc.setDistancePerPulse(0.0);
				
				
				for (int i = 0; i < encoderinputs.length; i++) { encoderinputs[i] = new DigitalInput(i); }
				
				//Setting up a DifferentialDrive object to control the wheels easier
				drive = new MecanumDrive(fleft, bleft, fright, bright);
				
				//This is important?
				drive.setSafetyEnabled(false);


	}
	
	public void move(double throttle, double x, double y , double z, double angle) {
		m_throttle = throttle;
		
		drive.driveCartesian(	m_xspeed.adjustedSpeed(x), 
								m_yspeed.adjustedSpeed(y), 
								m_zspeed.adjustedSpeed(z),
								angle);
	
	}

	
	public void setBoost(boolean left , boolean right) {
		m_leftboost = left;
		m_rightboost = right;
		
		m_xspeed.setBoost(m_rightboost);
		m_yspeed.setBoost(m_rightboost);
		m_zspeed.setBoost(m_rightboost);
	}
	

	public double getMultiplier() {
		double multiplier = m_xspeed.getSpeed();
		
		if (m_rightboost) {
			multiplier = multiplier * Speed.BOOST_RATE;
		}
		return multiplier;
	}
	

	
	
}

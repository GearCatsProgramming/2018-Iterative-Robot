/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6500.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	//We have spark motor controllers so we are using the Spark class to initiate our front left, front right, back left, & back right motors
	Spark fleft, bleft, fright, bright;
	
	DigitalInput[] encoderinputs = new DigitalInput[8];
	
	Encoder flenc, blenc, frenc, brenc;
	
	//Create an object for the controller based on the Joystick class (or XboxController)
	Joystick controllerR, controllerL;
	
	//Creating a RobotDrive object called drive so we can control the motors as a whole, not individually
	MecanumDrive drive;
	
	//Our gyroscope is a model ADXRS450, and there is a class for that so we make a gyro object with it
	ADXRS450_Gyro gyro;
	
	//A variable that makes sure the robot doesn't try and run the autonomous portion of code more than once
	boolean autonomous = true;
	
	double rightboost, leftboost = 0.0;
	double xspeed, yspeed, zspeed;
	double xspeedprevious, yspeedprevious, zspeedprevious;
	
	boolean nitro = false;
	
	//If a control delay variable is not created, the joystick's input is read too fast
	//and the driver cycles through options way too fast to control it.
	int controldelay = 0;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		//Initializing the motor controllers on the correct ports
		fleft = new Spark(0);
		bleft = new Spark(1);
		fright = new Spark(2);
		bright = new Spark(3);
		
		for (int i = 0; i < encoderinputs.length; i++) { encoderinputs[i] = new DigitalInput(i); }
		
		flenc = new Encoder(encoderinputs[0], encoderinputs[1]);
		blenc = new Encoder(encoderinputs[2], encoderinputs[3]);
		frenc = new Encoder(encoderinputs[4], encoderinputs[5]);
		brenc = new Encoder(encoderinputs[6], encoderinputs[7]);
		
		flenc.setDistancePerPulse(0.0);
		blenc.setDistancePerPulse(0.0);
		frenc.setDistancePerPulse(0.0);
		brenc.setDistancePerPulse(0.0);
		
		//Setting up a DifferentialDrive object to control the wheels easier
		drive = new MecanumDrive(fleft, bleft, fright, bright);
		
		//This is important?
		drive.setSafetyEnabled(false);
		
		//Creating the Joystick object using the USB port ID we have it plugged into
		controllerR = new Joystick(0);
		controllerL = new Joystick(1);
		
		//Making instances for the sensors and calibrating them/setting them up
		gyro = new ADXRS450_Gyro();
		gyro.calibrate();
		
		CameraServer.getInstance().startAutomaticCapture();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional comparisons to
	 * the switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		//Calculate the speed multiplier by the position of the throttle
		//However, the value returned by the getThrottle function ranges
		//from -1 to 1, and we need a range of 0 to 1, so we add 1 to
		//make the range 0 to 2
		double multiplier = controllerR.getThrottle() + 1;
		//Then we divide by 2 to get the correct range of 0 to 1
		multiplier = multiplier / 2;
		//The thing is, the value returned by getThrottle conflicts with
		//the indicators on the joystick.  When you move the throttle towards
		//the plus indicator, it decreases the value, and vice versa for
		//the negative indicator.  So we have to inverse the value by doing
		//1 minus our previous value.
		multiplier = 1 - multiplier;
    //Base speed multiplier
		multiplier = multiplier * 0.80;
		//The if statement checks if button #12 on the controller is pressed and activates turbo
		if (controllerR.getRawButton(12)) {
			drive.driveCartesian(0.0, 1.0, 0.0);
		}else{
			if (controllerR.getTrigger()) {
				rightboost = 0.05;
			}else{
				rightboost = 0.0;
			}
			
			if (controllerL.getTrigger()) {
				leftboost = 0.05;
			}else{
				leftboost = 0.0;
			}
			
			xspeed = controllerR.getX() * (multiplier + rightboost);
			yspeed = controllerR.getY() * (multiplier + rightboost);
			zspeed = controllerR.getZ() * (multiplier + rightboost);
			
			if (Math.abs(xspeedprevious - xspeed) > 0.03)
			{
				xspeed = (xspeed + xspeedprevious) / 2;
			}
			
			if (Math.abs(yspeedprevious - yspeed) > 0.03)
			{
				yspeed = (yspeed + yspeedprevious) / 2;
			}
			
			if (Math.abs(zspeedprevious - zspeed) > 0.03)
			{
				zspeed = (zspeed + zspeedprevious) / 2;
			}
			
			drive.driveCartesian(xspeed, yspeed, zspeed, gyro.getAngle());
			
			xspeedprevious = xspeed;
			yspeedprevious = yspeed;
			zspeedprevious = zspeed;
		}
		
		SmartDashboard.putNumber("Speed Multiplier", multiplier);
		
    //Decrement the controldelay
		if (controldelay > 0) { controldelay = controldelay - 1; }
	}
}

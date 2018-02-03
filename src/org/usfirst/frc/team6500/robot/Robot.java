/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6500.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	
	// Create a GCMecanumDrive
	
	GCMecanumDrive drive;
	
	//Create an object for the controller based on the Joystick class (or XboxController)
	Joystick controllerR, controllerL;
	
	//Our gyroscope is a model ADXRS450, and there is a class for that so we make a gyro object with it
	//ADXRS450_Gyro gyro;
	
	ADXRS450_Gyro gyro;
	
	//A variable that makes sure the robot doesn't try and run the autonomous portion of code more than once
	boolean autonomous = true;
	
	
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		drive = new GCMecanumDrive(); 
		
		//Creating the Joystick object using the USB port ID we have it plugged into
		controllerR = new GCJoystick(0);
		controllerL = new GCJoystick(1);
		
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
		
		
			//drive.setBoost(controllerL.getTrigger(), controllerR.getTrigger());
			
			drive.move(controllerR.getThrottle(), 
					 	controllerR.getX(), 
					 	controllerR.getY(), 
					 	controllerR.getZ(), 
					 	gyro.getAngle());
			
		
		
		SmartDashboard.putNumber("Speed Multiplier", drive.getMultiplier());
	}
		
}

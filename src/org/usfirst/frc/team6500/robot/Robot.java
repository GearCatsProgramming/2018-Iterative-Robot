/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6500.robot;

import org.usfirst.frc.team6500.robot.auto.PracticeAuto;
import org.usfirst.frc.team6500.robot.systems.DriveInput;
import org.usfirst.frc.team6500.robot.systems.Gyro;
import org.usfirst.frc.team6500.robot.systems.Mecanum;
import org.usfirst.frc.team6500.robot.systems.Vision;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	
	double boost = 0.0;
	double xspeed, yspeed, zspeed;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		Encoders.initializeEncoders();
		Mecanum.initializeMecanum();
		DriveInput.initializeInput();
		Gyro.intializeGyro();
		Vision.initializeVision();
		Grabber.intializeGrabber();
		

		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setFPS(15);
		camera.setResolution(320, 240); //320 = width, 240 = height
		
	}

	/**
	 * This function runs once at the very beginning of autonomous
	 */
	@Override
	public void autonomousInit() {
		Encoders.resetAllEncoders();
		PracticeAuto.goForward();
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
		if (DriveInput.getButton(7, DriveInput.controllerR))
		{
			Mecanum.driveWheel(Constants.DRIVE_FRONTLEFT, DriveInput.getThrottle(DriveInput.controllerR));
		}
		if (DriveInput.getButton(8, DriveInput.controllerR))
		{
			Mecanum.driveWheel(Constants.DRIVE_FRONTRIGHT, DriveInput.getThrottle(DriveInput.controllerR));
		}
		if (DriveInput.getButton(9, DriveInput.controllerR))
		{
			Mecanum.driveWheel(Constants.DRIVE_REARLEFT, DriveInput.getThrottle(DriveInput.controllerR));
		}
		if (DriveInput.getButton(10, DriveInput.controllerR))
		{
			Mecanum.driveWheel(Constants.DRIVE_REARRIGHT, DriveInput.getThrottle(DriveInput.controllerR));
		}
		
		//Resets all of the encoders
		if(DriveInput.getButton(11, DriveInput.controllerR))
		{
			Encoders.resetAllEncoders();
		}
		
		//Vision Testing
		if (DriveInput.getButton(2, DriveInput.controllerR))
		{
			System.out.println(Vision.getContourX());
			System.out.println(Vision.getContourY());
			System.out.println(Vision.getContourWidth());
			System.out.println(Vision.getContourHeight());
		}
		
		//Code to grab/eject cubes
		if (DriveInput.getButton(3, DriveInput.controllerR))
		{
			Grabber.grabCube();
		}
		else if (DriveInput.getButton(5, DriveInput.controllerR))
		{
			Grabber.ejectCube();
		}
		else
		{
			Grabber.killGrab();
		}
		
		
		//Deprecated, limited speed to the base speed but isn't very effective with mecanum
		/**
		 * if (!DriveInput.getTrigger(DriveInput.controllerR))
		 * {
		 * 	multiplier *= Constants.SPEED_BASE;
		 * }
		**/
		
		double multiplier = DriveInput.getThrottle(DriveInput.controllerR);
		
		multiplier += boost;
		
		xspeed = DriveInput.getAxis(Constants.INPUT_AXIS_X, DriveInput.controllerR);
		yspeed = -DriveInput.getAxis(Constants.INPUT_AXIS_Y, DriveInput.controllerR);
		zspeed = DriveInput.getAxis(Constants.INPUT_AXIS_Z, DriveInput.controllerR);
		
		Mecanum.driveRobot(xspeed, yspeed, zspeed);
		
		SmartDashboard.putNumber("Speed Multiplier", multiplier);
		SmartDashboard.putNumber("FL", Encoders.getDistance(Constants.ENCODER_FRONTLEFT));
		SmartDashboard.putNumber("FR", Encoders.getDistance(Constants.ENCODER_FRONTRIGHT));
		SmartDashboard.putNumber("RL", Encoders.getDistance(Constants.ENCODER_REARLEFT));
		SmartDashboard.putNumber("RR", Encoders.getDistance(Constants.ENCODER_REARRIGHT));
		SmartDashboard.putNumber("Avg. Distance", Encoders.getAverageDistance());
	}
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6500.robot;

import org.usfirst.frc.team6500.robot.auto.AutoRoute;
import org.usfirst.frc.team6500.robot.auto.AutoWrapper;
import org.usfirst.frc.team6500.robot.auto.routes.*;
import org.usfirst.frc.team6500.robot.sensors.Encoders;
import org.usfirst.frc.team6500.robot.sensors.Gyro;
import org.usfirst.frc.team6500.robot.sensors.Vision;
import org.usfirst.frc.team6500.robot.systems.Lift;
import org.usfirst.frc.team6500.robot.systems.Climber;
import org.usfirst.frc.team6500.robot.systems.DriveInput;
import org.usfirst.frc.team6500.robot.systems.Grabber;
import org.usfirst.frc.team6500.robot.systems.Mecanum;
import org.usfirst.frc.team6500.robot.tasks.Rotate180;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Main robot class, execution begins and ends here through overridden methods
 * 
 * @author Everyone
 *
 */
public class Robot extends IterativeRobot {
	
	double boost = 0.0;
	double xspeed, yspeed, zspeed;
	
	int autoType, autoPos = 0;
	SendableChooser<Integer> autoPlanSelector;
	SendableChooser<Integer> autoOriginSelector;
	
	boolean fieldOriented = false;
	boolean testing = false;
	
	Speed xSpeed, ySpeed, zSpeed;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit()
	{
		Encoders.initializeEncoders();
		Mecanum.initializeMecanum();
		DriveInput.initializeInput();
		Gyro.intializeGyro();
		Vision.initializeVision();
		Grabber.intializeGrabber();
		Lift.initializeLift();
		Climber.initializeWinch();
		
		
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setFPS(30);
		camera.setResolution(640, 480); //320 = width, 240 = height
		
		autoPlanSelector = new SendableChooser<Integer>();
		
		autoPlanSelector.addDefault("Forward/Backward", 0);
		autoPlanSelector.addObject("Left/Right", 1);
		autoPlanSelector.addObject("Rotate", 2);
		autoPlanSelector.addObject("All", 3);
		
		SmartDashboard.putData("Autonomous Plan Selector", autoPlanSelector);
		
		autoOriginSelector = new SendableChooser<Integer>();
		
		autoOriginSelector.addDefault("Left", 0);
		autoOriginSelector.addObject("Middle", 1);
		autoOriginSelector.addObject("Right", 2);
		
		SmartDashboard.putData("Autonomous Position Selector", autoOriginSelector);
		
		xSpeed = new Speed();
		ySpeed = new Speed();
		zSpeed = new Speed();
		
		SmartDashboard.getNumber("P val", 0.0);
		SmartDashboard.getNumber("I val", 0.0);
		SmartDashboard.getNumber("D val", 0.0);
	}

	/**
	 * This function runs once at the very beginning of autonomous
	 */
	@Override
	public void autonomousInit()
	{
		autoType = autoPlanSelector.getSelected();
		autoPos = autoOriginSelector.getSelected();
		
		switch (autoType)
		{
		case 0:
			AutoWrapper.goForward(20.0, 0.5);
		case 1:
			AutoWrapper.leftRight(20.0, 0.5);
		case 2:
			AutoWrapper.rotate(50.0, 0.5);
		case 3:
			AutoRoute testRT = new TestRoute();
			testRT.run();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic()
	{
		
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic()
	{
		testing = false;
		if (DriveInput.getButton(7, DriveInput.controllerR))
		{
			Mecanum.driveWheel(Constants.DRIVE_FRONTLEFT, DriveInput.getThrottle(DriveInput.controllerR));
			testing = true;
		}
		if (DriveInput.getButton(8, DriveInput.controllerR))
		{
			Mecanum.driveWheel(Constants.DRIVE_FRONTRIGHT, DriveInput.getThrottle(DriveInput.controllerR));
			testing = true;
		}
		if (DriveInput.getButton(9, DriveInput.controllerR))
		{
			Mecanum.driveWheel(Constants.DRIVE_REARLEFT, DriveInput.getThrottle(DriveInput.controllerR));
			testing = true;
		}
		if (DriveInput.getButton(10, DriveInput.controllerR))
		{
			Mecanum.driveWheel(Constants.DRIVE_REARRIGHT, DriveInput.getThrottle(DriveInput.controllerR));
			testing = true;
		}
		
		//5 PM on the last saturday.  no driving robot.
		if (DriveInput.getButton(7, DriveInput.controllerL)) {
			if (fieldOriented) { fieldOriented = false; }
			else { fieldOriented = true; }
		}
		
		//Resets all of the encoders
		if(DriveInput.getButton(11, DriveInput.controllerR))
		{
			Encoders.resetAllEncoders();
		}
		
		if(DriveInput.getButton(12, DriveInput.controllerR))
		{
			Mecanum.driveWheel(Constants.DRIVE_REARRIGHT, DriveInput.getThrottle(DriveInput.controllerR));
			Mecanum.driveWheel(Constants.DRIVE_REARLEFT, DriveInput.getThrottle(DriveInput.controllerR));
			Mecanum.driveWheel(Constants.DRIVE_FRONTRIGHT, DriveInput.getThrottle(DriveInput.controllerR));
			Mecanum.driveWheel(Constants.DRIVE_FRONTLEFT, DriveInput.getThrottle(DriveInput.controllerR));
			testing = true;
		}
		
		//Vision Testing
		if (DriveInput.getButton(2, DriveInput.controllerL))
		{
			System.out.println(Vision.getContourX());
			System.out.println(Vision.getContourY());
			System.out.println(Vision.getContourWidth());
			System.out.println(Vision.getContourHeight());
		}
		
		
		//Code to grab/eject cubes
		if (DriveInput.getButton(3, DriveInput.controllerL))
		{
			Grabber.grabCube();
		}
		else if (DriveInput.getButton(5, DriveInput.controllerL))
		{
			Grabber.ejectCube();
		}
		else if (DriveInput.getButton(10, DriveInput.controllerL))
		{
			Grabber.dLeft(DriveInput.getRawThrottle(DriveInput.controllerL));
		}
		else if (DriveInput.getButton(11, DriveInput.controllerL))
		{
			Grabber.dRight(DriveInput.getRawThrottle(DriveInput.controllerL));
		}
		else
		{
			Grabber.killGrab();
		}
		
		
		//Drives lift
		if (DriveInput.getButton(6, DriveInput.controllerL))
		{
			Lift.raiseLift();
		}
		else if (DriveInput.getButton(4, DriveInput.controllerL))
		{
			Lift.descend();
		}
		else
		{
			Lift.stopLift();
		}
		
		
		//Tester code for tasks
		if (DriveInput.getButton(11, DriveInput.controllerL))
		{
			(new Rotate180()).start();
		}
		
		//Code to drive climbing winch
		if (DriveInput.getPOV(DriveInput.controllerL) == 0) {
			Climber.moveWinch(1.0);
		}
		else if (DriveInput.getPOV(DriveInput.controllerL) == 180) {
			Climber.moveWinch(-1.0);
		}
		else
		{
			Climber.stopWinch();
		}
		
		
		if (!testing) {
			double multiplier = DriveInput.getThrottle(DriveInput.controllerR);
			
			xspeed = xSpeed.calculateSpeed(DriveInput.getAxis(Constants.INPUT_AXIS_X, DriveInput.controllerR), multiplier);
			yspeed = ySpeed.calculateSpeed(-DriveInput.getAxis(Constants.INPUT_AXIS_Y, DriveInput.controllerR), multiplier);
			zspeed = zSpeed.calculateSpeed(DriveInput.getAxis(Constants.INPUT_AXIS_Z, DriveInput.controllerR), multiplier);
			
			if (!fieldOriented) { Mecanum.driveRobot(xspeed, yspeed, zspeed); }
			else { Mecanum.driveRobotField(xspeed, yspeed, zspeed); }
			
			SmartDashboard.putNumber("Speed Multiplier", multiplier);
		}
		
		SmartDashboard.putNumber("FL", Encoders.getDistance(Constants.ENCODER_FRONTLEFT));
		SmartDashboard.putNumber("FR", Encoders.getDistance(Constants.ENCODER_FRONTRIGHT));
		SmartDashboard.putNumber("RL", Encoders.getDistance(Constants.ENCODER_REARLEFT));
		SmartDashboard.putNumber("RR", Encoders.getDistance(Constants.ENCODER_REARRIGHT));
		SmartDashboard.putNumber("Avg. Distance", Encoders.getAverageDistance());
		SmartDashboard.putNumber("PIDInput", Gyro.getAngle() % 360);
	}
}

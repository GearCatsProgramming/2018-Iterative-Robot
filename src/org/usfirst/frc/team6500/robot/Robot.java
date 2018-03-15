/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6500.robot;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.usfirst.frc.team6500.robot.auto.AutoRoute;
import org.usfirst.frc.team6500.robot.auto.PIDWrapper;
import org.usfirst.frc.team6500.robot.auto.routes.*;
import org.usfirst.frc.team6500.robot.sensors.Encoders;
import org.usfirst.frc.team6500.robot.sensors.Gyro;
import org.usfirst.frc.team6500.robot.sensors.Vision;
import org.usfirst.frc.team6500.robot.systems.DriveInput;
import org.usfirst.frc.team6500.robot.systems.Grabber;
import org.usfirst.frc.team6500.robot.systems.Lift;
import org.usfirst.frc.team6500.robot.systems.Mecanum;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	
	double boost = 0.0;
	double xspeed, yspeed, zspeed;
	Speed speedX, speedY, speedZ;
	
	int autoMode = 0;
	int teleopMode = 0;
	SendableChooser<Integer> autoTargetSelector, autoStartSelector, teleopControlSelector;
	
	boolean fieldOriented = false;
	
	public static ArrayList<PIDWrapper> hitList;
	
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
		
		hitList = new ArrayList<PIDWrapper>();
		
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		//camera.setFPS(10);
		camera.setResolution(240, 180); //640 = width, 480 = height
		
		autoTargetSelector = new SendableChooser<Integer>();
		autoStartSelector = new SendableChooser<Integer>();		
		teleopControlSelector = new SendableChooser<Integer>();
		
		autoTargetSelector.addDefault("Switch", 1);
		autoTargetSelector.addObject("Autoline", 2);
		autoTargetSelector.addObject("Nothing", 3);
//		autoTargetSelector.addObject("Scale", 4);
		
		autoStartSelector.addDefault("Left", 1);
		autoStartSelector.addObject("Middle", 2);
		autoStartSelector.addObject("Right", 3);
		
		teleopControlSelector.addDefault("Two Drivers", 1);
		teleopControlSelector.addObject("One Driver", 2);
		
		speedX = new Speed();
		speedY = new Speed();
		speedZ = new Speed();
		
		SmartDashboard.putData("Autonomous Target Selector", autoTargetSelector);
		SmartDashboard.putData("Autonomous Start Position Selector", autoStartSelector);
		SmartDashboard.putData("Teleop Controller Mode", teleopControlSelector);
	}

	/**
	 * This function runs once at the very beginning of autonomous
	 */
	@Override
	public void autonomousInit()
	{
		int autoTarget = autoTargetSelector.getSelected();
		
		if (autoTarget == 3) //Do nothing.
        {
        	return;
        }
		
		int autoPos = autoStartSelector.getSelected();
		
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
        if(!(gameData.length() > 0))
        {
        	System.err.println("No game data found! Exiting Auto.");
        	return;
        }
        
        
        //Obtain data about this game
        char switchData = gameData.charAt(0);
        boolean switchLeft;
        if (switchData == 'L') { switchLeft = true; } else { switchLeft = false; }
        
        char scaleData = gameData.charAt(1);
        boolean scaleLeft;
        if (scaleData == 'L') { scaleLeft = true; } else { scaleLeft = false; }
        
        
    	//if (gameData.equals("UUDDLRLRBASTART")) { new TheRoute(); return; }
        
        AutoRoute route = new ForwardRoute(130.0, 0.5, this);
        double autoSpeed = Constants.AUTO_SPEED;
        
        Mecanum.killMotors();
        Encoders.resetAllEncoders();
		
        switch(autoTarget)
        {
        case 1: //Switch
        	if (switchLeft && autoPos == 1)
        	{
        		route = new SimpleSwitch(0.65, this);
        	}
        	else if (!switchLeft && autoPos == 3)
        	{
        		route = new SimpleSwitch(0.65, this);
        	}
        	else
        	{
        		if (autoPos == 1) { route = new EvadeSwitch(0.5, true, this); }
        		else if (autoPos == 2) { route = new ForwardRoute(130.0, 0.5, this); }
        		else { route = new EvadeSwitch(0.5, false, this); }
        	}
        	
        	break;
        case 2: //Scale
        	if (autoPos == 1) { //Left
        		if (scaleLeft) { route = new ForwardScale(autoSpeed, true, this); }
        		else { //route = new OppositeScale(autoSpeed, true);
        		route = new ForwardRoute(130.0, 0.5, this);}
        	}
        	
        	
        	else if (autoPos == 2) { route = new AutoLine(autoSpeed, this); } //Middle
        	

        	else if (autoPos == 3) { //Right
        		if (scaleLeft) { //route = new OppositeScale(autoSpeed, false);
        		route = new ForwardRoute(130.0, 0.5, this);}
        		else { route = new ForwardScale(autoSpeed, false, this); }
        	}
        	
        	break;
        case 3: //Autoline
        	System.out.println("Done");
        	route = new ForwardRoute(130.0, 0.5, this);
        	break;
        }
        
        route.run();
    
        exterminateThreads();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic()
	{
		
	}
	
	@Override
	public void teleopInit()
	{
		System.out.println("Beginning teleop");
		exterminateThreads();
		
		teleopMode = teleopControlSelector.getSelected();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic()
	{
//		if (DriveInput.getButton(7, DriveInput.controllerR))
//		{
//			Mecanum.driveWheel(Constants.DRIVE_FRONTLEFT, DriveInput.getThrottle(DriveInput.controllerR));
//		}
//		if (DriveInput.getButton(8, DriveInput.controllerR))
//		{
//			Mecanum.driveWheel(Constants.DRIVE_FRONTRIGHT, DriveInput.getThrottle(DriveInput.controllerR));
//		}
//		if (DriveInput.getButton(9, DriveInput.controllerR))
//		{
//			Mecanum.driveWheel(Constants.DRIVE_REARLEFT, DriveInput.getThrottle(DriveInput.controllerR));
//		}
//		if (DriveInput.getButton(10, DriveInput.controllerR))
//		{
//			Mecanum.driveWheel(Constants.DRIVE_REARRIGHT, DriveInput.getThrottle(DriveInput.controllerR));
//		}
		
		//Resets all of the encoders
		if(DriveInput.getButton(11, DriveInput.controllerR))
		{
			Encoders.resetAllEncoders();
		}
		
		//Vision Testing
//		if (DriveInput.getButton(2, DriveInput.controllerR))
//		{
//			System.out.println(Vision.getContourX());
//			System.out.println(Vision.getContourY());
//			System.out.println(Vision.getContourWidth());
//			System.out.println(Vision.getContourHeight());
//		}
		
		if(teleopMode == 1) {		//Two-driver mode
			//Code to grab/eject cubes
			if (DriveInput.getButton(3, DriveInput.controllerL))
			{
				Grabber.grabCube();
			}
			else if (DriveInput.getButton(5, DriveInput.controllerL))
			{
				Grabber.ejectCube();
			}
			else
			{
				Grabber.killGrab();
			}
			
			
			//Code to drive climber
			if (DriveInput.getButton(4, DriveInput.controllerL))
			{
				Lift.raiseLift();
			}
			else if (DriveInput.getButton(6, DriveInput.controllerL))
			{
				Lift.descend();
			}
			else
			{
				Lift.stopLift();
			}
			
		} else if(teleopMode == 2) {	//One-driver mode
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
			//Code to drive climber
			if (DriveInput.getButton(6, DriveInput.controllerR))
			{
				Lift.raiseLift();
			}
			else if (DriveInput.getButton(4, DriveInput.controllerR))
			{
				Lift.descend();
			}
			else
			{
				Lift.stopLift();
			}
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
		
		xspeed = speedX.calculateSpeed(DriveInput.getAxis(Constants.INPUT_AXIS_X, DriveInput.controllerR), multiplier);
		yspeed = speedY.calculateSpeed(-DriveInput.getAxis(Constants.INPUT_AXIS_Y, DriveInput.controllerR), multiplier);
		zspeed = speedZ.calculateSpeed(DriveInput.getAxis(Constants.INPUT_AXIS_Z, DriveInput.controllerR), multiplier);
		
		if (!fieldOriented) { Mecanum.driveRobot(xspeed, yspeed, zspeed); }
		else { Mecanum.driveRobotField(xspeed, yspeed, zspeed); }
		
		SmartDashboard.putNumber("Speed Multiplier", multiplier);
		SmartDashboard.putNumber("FL", Encoders.getDistance(Constants.ENCODER_FRONTLEFT));
		SmartDashboard.putNumber("FR", Encoders.getDistance(Constants.ENCODER_FRONTRIGHT));
		SmartDashboard.putNumber("RL", Encoders.getDistance(Constants.ENCODER_REARLEFT));
		SmartDashboard.putNumber("RR", Encoders.getDistance(Constants.ENCODER_REARRIGHT));
		SmartDashboard.putNumber("Avg. Distance Forward", Encoders.getAverageDistanceForward());
		SmartDashboard.putNumber("Avg. Distance Right", Encoders.getAverageDistanceRight());
		SmartDashboard.putNumber("PIDInput", Gyro.getAngle() % 360);
		
		SmartDashboard.putBoolean("channel a", Encoders.encoderinputs[Constants.ENCODER_INPUT_RR_A].get());
		SmartDashboard.putBoolean("channel b", Encoders.encoderinputs[Constants.ENCODER_INPUT_RR_B].get());
	}
	
	public void exterminateThreads() {
		for (PIDWrapper thread : hitList)
		{
			thread.interrupt();
			System.out.println();
			System.out.println("Suck it");
		}
	}
}

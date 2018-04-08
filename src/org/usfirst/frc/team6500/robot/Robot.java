/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6500.robot;

import java.util.ArrayList;

import org.usfirst.frc.team6500.robot.auto.AutoRoute;
import org.usfirst.frc.team6500.robot.auto.routes.*;
import org.usfirst.frc.team6500.robot.sensors.Encoders;
import org.usfirst.frc.team6500.robot.sensors.Gyro;
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
	
	double xspeed, yspeed, zspeed;
	Speed speedX, speedY, speedZ;
	
	int teleopMode = 0;
	SendableChooser<Integer> riskFactorSelector;
	SendableChooser<Goal> autoTargetSelector;
	SendableChooser<Position> autoStartSelector;
	
	/**Makes the robot move forward relative to the starting position rather than the front of the robot.*/
	final boolean fieldOriented = false;
	
	/**ArrayList which holds threads; used to kill them all when teleop begins so that the robot doesn't lock it up. Used in case of emergency.*/
	public static ArrayList<Thread> hitList;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit()
	{
		//Startup methods to recognize and configure motors and encoders
		Encoders.initializeEncoders();
		Mecanum.initializeMecanum();
		DriveInput.initializeInput();
		Gyro.intializeGyro();
		Grabber.intializeGrabber();
		Lift.initializeLift();
		
		hitList = new ArrayList<Thread>();
		
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		//camera.setFPS(10);
		camera.setResolution(240, 180); //640 = width, 480 = height
		
		//Sets up SmartDashboard menus for settings
		autoTargetSelector = new SendableChooser<Goal>();
		autoStartSelector = new SendableChooser<Position>();		
		riskFactorSelector = new SendableChooser<Integer>();
		
		autoTargetSelector.addDefault("Switch", Goal.hitSwitch);
		autoTargetSelector.addObject("Autoline", Goal.hitAutoLine);
		autoTargetSelector.addObject("Scale", Goal.hitScale);
		
		autoStartSelector.addDefault("Left", Position.left);
		autoStartSelector.addObject("Middle", Position.middle);
		autoStartSelector.addObject("Right", Position.right);
		
		riskFactorSelector.addDefault("Normal", 1);
		riskFactorSelector.addObject("We goin' risky boi (double cubes) (don't use plz) (emergencies only) (only in playoffs or matches we know we can win)", 9001);
		
		speedX = new Speed();
		speedY = new Speed();
		speedZ = new Speed();
		
		SmartDashboard.putData("Autonomous Target Selector", autoTargetSelector);
		SmartDashboard.putData("Autonomous Start Position Selector", autoStartSelector);
		SmartDashboard.putData("Risk Selector", riskFactorSelector);
	}

	/**
	 * This function runs once at the very beginning of autonomous
	 */
	@Override
	public void autonomousInit()
	{
		Goal autoTarget = autoTargetSelector.getSelected();
		Position autoPos = autoStartSelector.getSelected();
		int riskFactor = riskFactorSelector.getSelected();
		
		//Obtains data; found in the form of sequences of 'L's and 'R's to indicate where the friendly side of the switch and scale are.
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
        if(!(gameData.length() > 0))
        {
        	System.err.println("No game data found! Exiting Auto.");
        	return;
        }
        
        //Interprets data about this game
        char switchData = gameData.charAt(0);
        Position switchPos;
        if (switchData == 'L') { switchPos = Position.left; } else { switchPos = Position.right; }
        
        char scaleData = gameData.charAt(1);
        Position scalePos;
        if (scaleData == 'L') { scalePos = Position.left; } else { scalePos = Position.right; }
        
        double autoSpeed = Constants.AUTO_SPEED;
        //Sets a default route
        AutoRoute route = new AutoLine(autoSpeed, this);
        double sosososoSonicSpeed = 0.75;
        
        Mecanum.killMotors();
        Encoders.resetAllEncoders();
        Gyro.reset();
		
        //RISK FACTOR IS OVER 9000!!!
        if (riskFactor > 9000)
        {
        	//Take the ambitious route
        	route = new DoubleCube(sosososoSonicSpeed, Position.toBoolean(scalePos), this, !Position.toBoolean(switchPos));
        	route.run();
            
        	return;
            //exterminateThreads();
        }
        
        //Determine the route if playing safely; prioritizes based on how close friendly targets are.
        route = RouteMap.getRoute(autoSpeed, this, autoPos, switchPos, scalePos, autoTarget);
        route.run();
        
        //Once the route is done, the init continues and kills all threads.
        exterminateThreads();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic()
	{
		//Empty; this is basically a command bot for auto
	}
	
	@Override
	public void teleopInit()
	{
		//Stops threads if there are any running
		System.out.println("Beginning teleop, gotta squash those threads");
		exterminateThreads();
		exterminateThreads();
		
		//teleopMode = teleopControlSelector.getSelected();
		teleopMode = 1;
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
		
		//Resets all of the encoders, used for testing
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
				Lift.descend(0.65);
			}
			else if (DriveInput.getButton(6, DriveInput.controllerL))
			{
				Lift.raiseLift(DriveInput.getThrottle(DriveInput.controllerL));
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
				Lift.raiseLift(0.90);
			}
			else if (DriveInput.getButton(4, DriveInput.controllerR))
			{
				Lift.descend(0.65);
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
		
		xspeed = speedX.calculateSpeed(DriveInput.getAxis(Constants.INPUT_AXIS_X, DriveInput.controllerR), multiplier);
		yspeed = speedY.calculateSpeed(-DriveInput.getAxis(Constants.INPUT_AXIS_Y, DriveInput.controllerR), multiplier);
		zspeed = speedZ.calculateSpeed(DriveInput.getAxis(Constants.INPUT_AXIS_Z, DriveInput.controllerR), multiplier);
		
		if (!fieldOriented) { Mecanum.driveRobot(xspeed, yspeed, zspeed); }
		else { Mecanum.driveRobotField(xspeed, yspeed, zspeed); }
		
		SmartDashboard.putNumber("Speed Multiplier", multiplier);
		//SmartDashboard.putNumber("FL", Encoders.getDistance(Constants.ENCODER_FRONTLEFT));
		//SmartDashboard.putNumber("FR", Encoders.getDistance(Constants.ENCODER_FRONTRIGHT));
		//SmartDashboard.putNumber("RL", Encoders.getDistance(Constants.ENCODER_REARLEFT));
		//SmartDashboard.putNumber("RR", Encoders.getDistance(Constants.ENCODER_REARRIGHT));
		//SmartDashboard.putNumber("Avg. Distance Forward", Encoders.getAverageDistanceForward());
//		SmartDashboard.putNumber("Avg. Distance Right", Encoders.getAverageDistanceRight());
		SmartDashboard.putNumber("Gyro", Gyro.getAngle() % 360);
		
		//SmartDashboard.putBoolean("channel a", Encoders.encoderinputs[Constants.ENCODER_INPUT_RR_A].get());
		//SmartDashboard.putBoolean("channel b", Encoders.encoderinputs[Constants.ENCODER_INPUT_RR_B].get());
	}
	
	/**Kills all the threads that are running to end autonomous actions.*/
	public void exterminateThreads() {
		for (int threadID = 0; threadID < hitList.size(); threadID++)
		{
			Thread thread = hitList.get(threadID);
			thread.interrupt();
			//thread.stop();
			System.out.println("Suck it");
		}
	}
}

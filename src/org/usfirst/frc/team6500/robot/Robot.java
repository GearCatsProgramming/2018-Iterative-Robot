/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6500.robot;

import java.util.ArrayList;

import org.usfirst.frc.team6500.robot.auto.AutoRoute;
import org.usfirst.frc.team6500.robot.auto.PIDWrapper;
import org.usfirst.frc.team6500.robot.auto.routes.*;
import org.usfirst.frc.team6500.robot.auto.routes.manualpidroutes.CenterManeuvers;
import org.usfirst.frc.team6500.robot.auto.routes.manualpidroutes.SideManeuvers;
import org.usfirst.frc.team6500.robot.manualpid.PIDMoveCommand;
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
	SendableChooser<Integer> autoTargetSelector, autoStartSelector, autoTypeSelector, teleopControlSelector;
	
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
		autoTypeSelector = new SendableChooser<Integer>();
		
		teleopControlSelector = new SendableChooser<Integer>();
		
		autoTargetSelector.addDefault("Switch", 1);
		autoTargetSelector.addObject("Scale", 2);
		autoTargetSelector.addObject("Autoline", 3);
		autoTargetSelector.addObject("Nothing", 4);
		
		autoStartSelector.addDefault("Left", 1);
		autoStartSelector.addObject("Middle", 2);
		autoStartSelector.addObject("Right", 3);
		
		autoTypeSelector.addDefault("Kyle", 1);
		autoTypeSelector.addObject("Thomas", 2);
		
		teleopControlSelector.addDefault("Two Drivers", 1);
		teleopControlSelector.addObject("One Driver", 2);
		
		speedX = new Speed();
		speedY = new Speed();
		speedZ = new Speed();
		
		SmartDashboard.putData("Autonomous Target Selector", autoTargetSelector);
		SmartDashboard.putData("Autonomous Start Position Selector", autoStartSelector);
		SmartDashboard.putData("Autonomous Type Selector", autoTypeSelector);
	}

	/**
	 * This function runs once at the very beginning of autonomous
	 */
	@Override
	public void autonomousInit()
	{
		int autoPos = autoStartSelector.getSelected();
		int autoTarget = autoTargetSelector.getSelected();
		int autoType = autoTypeSelector.getSelected();
		
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
        if(!(gameData.length() > 0))
        {
        	System.err.println("Auto Machine Broke!! :(");
        	//return;
        }
        
        
        //Obtain data about this game
        char switchData = gameData.charAt(0);
        boolean switchLeft;
        if (switchData == 'L') { switchLeft = true; } else { switchLeft = false; }
        
        char scaleData = gameData.charAt(1);
        boolean scaleLeft;
        if (scaleData == 'L') { scaleLeft = true; } else { scaleLeft = false; }
        
        
        if (autoTarget == 4) //Do nothing.
        {
//        	PIDMoveCommand help = new PIDMoveCommand(20, 0, 0, true);
//        	help.start();
        	//AutoRoute route = new SidewardRoute(20.0, 0.5);
        	
        	//System.out.println("Started");
        	return;
        }
        
        
        if (autoType == 1) //Kyle
        {
        	if (gameData.equals("UUDDLRLRBASTART")) { new TheRoute(); return; }
            
            
            double autoSpeed = Constants.AUTO_SPEED;
            
//            Mecanum.driveRobot(0.0, 1.0, 0.0);
//            Timer.delay(0.25);
//            Mecanum.driveRobot(0.0, -1.0, 0.0);
//            Timer.delay(0.25);
            Mecanum.killMotors();
            
            Encoders.resetAllEncoders();
            
            AutoRoute route = new ForwardRoute(130.0, 0.5);
    		
            switch(autoTarget)
            {
            case 1: //Switch
            	if (switchLeft && autoPos == 1)
            	{
            		route = new SimpleSwitch(0.65);
            	}
            	else if (!switchLeft && autoPos == 3)
            	{
            		route = new SimpleSwitch(0.65);
            	}
            	else
            	{
            		if (autoPos == 1) { route = new EvadeSwitch(0.5, true); }
            		else if (autoPos == 2) { route = new ForwardRoute(130.0, 0.5); }
            		else { route = new EvadeSwitch(0.5, false); }
            	}
            	
            	break;
            case 2: //Scale
            	if (autoPos == 1) { //Left
            		if (scaleLeft) { route = new ForwardScale(autoSpeed, true); }
            		else { //route = new OppositeScale(autoSpeed, true);
            		route = new ForwardRoute(130.0, 0.5);}
            	}
            	
            	
            	else if (autoPos == 2) { route = new AutoLine(autoSpeed); } //Middle
            	

            	else if (autoPos == 3) { //Right
            		if (scaleLeft) { //route = new OppositeScale(autoSpeed, false);
            		route = new ForwardRoute(130.0, 0.5);}
            		else { route = new ForwardScale(autoSpeed, false); }
            	}
            	
            	break;
            case 3: //Autoline
            	System.out.println("Done");
            	route = new ForwardRoute(130.0, 0.5);
            	break;
            }
            
            route.run();
        }
        else if (autoType == 2) //Thomas
        {
        	PIDMoveCommand aaa = new PIDMoveCommand(7, 0, 0, true);
        	aaa.start();
        	PIDMoveCommand.holdYourHorses(aaa);
        	
        	switch(autoTarget)
            {
            case 1: //Switch
            	if (autoPos == 1) { //Left
            		if (switchLeft) { SideManeuvers.sameSwitch(true); }
            		else { SideManeuvers.oppositeSwitch(true); }
            	}
            	
            	
            	else if (autoPos == 2) { CenterManeuvers.goToSwitch(switchLeft); } //Middle
            	

            	else if (autoPos == 3) { //Right
            		if (switchLeft) { SideManeuvers.oppositeSwitch(false); }
            		else { SideManeuvers.sameSwitch(false); }
            	}
            	
            	break;
            case 2: //Scale
            	if (autoPos == 1) { //Left
            		if (scaleLeft) {SideManeuvers.sameScale(true); }
            		else { SideManeuvers.oppositeScale(true); }
            	}
            	
            	
            	else if (autoPos == 2) { CenterManeuvers.goToScale(scaleLeft); } //Middle
            	

            	else if (autoPos == 3) { //Right
            		if (scaleLeft) { SideManeuvers.oppositeScale(false); }
            		else { SideManeuvers.sameScale(false); }
            	}
            	
            	break;
            case 3: //Autoline
            	(new PIDMoveCommand(120.0, 0.0, 0.0, switchLeft)).start();
            	break;
            }
        }
        
//		switch (autoMode)
//		{
//		case 1:
//			AutoWrapper.goForward(20.0, 0.5);
//			break;
//		case 2:
//			AutoWrapper.leftRight(20.0, 0.5);
//			break;
//		case 3:
//			AutoWrapper.rotate(50.0, 0.5);
//			break;
//		case 4:
//			AutoRoute testRT = new TestRoute();
//			testRT.run();
//			break;
//		case 5:
//			while(true) {
//				Mecanum.driveRobot(0, 0.5, 0);
//			}
//		case 6:
//			(new PIDMoveCommand(50, 0, 0, true)).start();
//			//ConfigureEncoders.testBadly(120);
//			break;
//		case 7:
//			ConfigureEncoders.getEncoderDistance();
//			break;
//		}
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
		for (PIDWrapper thread : hitList)
		{
			thread.interrupt();
			//thread.stop();
			//thread.destroy();
		}
		
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
		SmartDashboard.putNumber("Avg. Distance", Encoders.getAverageDistance());
		SmartDashboard.putNumber("PIDInput", Gyro.getAngle() % 360);
	}
}

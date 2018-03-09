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
import org.usfirst.frc.team6500.robot.manualpid.ManualPID;
import org.usfirst.frc.team6500.robot.manualpid.PIDDrive;
import org.usfirst.frc.team6500.robot.manualpid.PIDMoveCommand;
import org.usfirst.frc.team6500.robot.sensors.Encoders;
import org.usfirst.frc.team6500.robot.sensors.Gyro;
import org.usfirst.frc.team6500.robot.sensors.Vision;
import org.usfirst.frc.team6500.robot.systems.DriveInput;
import org.usfirst.frc.team6500.robot.systems.Grabber;
import org.usfirst.frc.team6500.robot.systems.Lift;
import org.usfirst.frc.team6500.robot.systems.Mecanum;
import org.usfirst.frc.team6500.robot.testsystems.ConfigureEncoders;
import org.usfirst.frc.team6500.robot.testsystems.ConfigureGyro;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	
	double boost = 0.0;
	double xspeed, yspeed, zspeed;
	Speed speedX, speedY, speedZ;
	
	int autoMode = 0;
	SendableChooser<Integer> autoSelector;
	
	boolean fieldOriented = false;
	
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
		
		
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setFPS(30);
		camera.setResolution(640, 480); //320 = width, 240 = height
		
		autoSelector = new SendableChooser<Integer>();
		
		autoSelector.addDefault("Forward/Backward", 1);
		autoSelector.addObject("Left/Right", 2);
		autoSelector.addObject("Rotate", 3);
		autoSelector.addObject("All", 4);
		autoSelector.addObject("Encoder Measurement", 7);
		autoSelector.addObject("Basic test", 5);
		autoSelector.addObject("Test Thomas's PID", 6);
		
		speedX = new Speed();
		speedY = new Speed();
		speedZ = new Speed();
		
		SmartDashboard.putData("Autonomous Tester Selector", autoSelector);
	}

	/**
	 * This function runs once at the very beginning of autonomous
	 */
	@Override
	public void autonomousInit()
	{
		//A quick jerk to drop the claw
		Mecanum.driveRobot(0, .1, 0);
		
		autoMode = autoSelector.getSelected();
		
		switch (autoMode)
		{
		case 1:
			AutoWrapper.goForward(20.0, 0.5);
			break;
		case 2:
			AutoWrapper.leftRight(20.0, 0.5);
			break;
		case 3:
			AutoWrapper.rotate(50.0, 0.5);
			break;
		case 4:
			AutoRoute testRT = new TestRoute();
			testRT.run();
			break;
		case 5:
			while(true) {
				Mecanum.driveRobot(0, 0.5, 0);
			}
		case 6:
			(new PIDMoveCommand(50, 0, 0, true)).start();
			//ConfigureEncoders.testBadly(120);
			break;
		case 7:
			ConfigureEncoders.getEncoderDistance();
			break;
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

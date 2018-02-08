/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6500.robot;

import org.usfirst.frc.team6500.robot.auto.PracticeAuto;
import org.usfirst.frc.team6500.robot.systems.DriveInput;
import org.usfirst.frc.team6500.robot.systems.Encoders;
import org.usfirst.frc.team6500.robot.systems.Gyro;
import org.usfirst.frc.team6500.robot.systems.Mecanum;
import org.usfirst.frc.team6500.robot.systems.Vision;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
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
		

		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setFPS(15);
		camera.setResolution(320, 240); //320 = width, 240 = height
		
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
			Mecanum.driveWheel(Constants.DRIVE_FRONTLEFT);
		}
		if (DriveInput.getButton(8, DriveInput.controllerR))
		{
			Mecanum.driveWheel(Constants.DRIVE_FRONTRIGHT);
		}
		if (DriveInput.getButton(9, DriveInput.controllerR))
		{
			Mecanum.driveWheel(Constants.DRIVE_REARLEFT);
		}
		if (DriveInput.getButton(10, DriveInput.controllerR))
		{
			Mecanum.driveWheel(Constants.DRIVE_REARRIGHT);
		}
		
		if (DriveInput.getButton(2, DriveInput.controllerR))
		{
			System.out.println(Vision.getContourX());
			System.out.println(Vision.getContourY());
			System.out.println(Vision.getContourWidth());
			System.out.println(Vision.getContourHeight());
		}
		
		if (DriveInput.getButton(3, DriveInput.controllerR))
		{
			
		}
		
		
		double multiplier = DriveInput.getThrottle(DriveInput.controllerR);
		if (!DriveInput.getTrigger(DriveInput.controllerR))
		{
			multiplier *= Constants.SPEED_BASE;
		}
		
		multiplier += boost;
		
		xspeed = DriveInput.getAxis(Constants.INPUT_AXIS_X, DriveInput.controllerR) * multiplier;
		yspeed = -DriveInput.getAxis(Constants.INPUT_AXIS_Y, DriveInput.controllerR) * multiplier;
		zspeed = DriveInput.getAxis(Constants.INPUT_AXIS_Z, DriveInput.controllerR) * multiplier;
		
		Mecanum.driveRobot(xspeed, yspeed, zspeed);
		
		SmartDashboard.putNumber("Speed Multiplier", multiplier);
		SmartDashboard.putNumber("y", DriveInput.getAxis(Constants.INPUT_AXIS_Y, DriveInput.controllerR));
		SmartDashboard.putNumber("x", DriveInput.getAxis(Constants.INPUT_AXIS_X, DriveInput.controllerR));
		SmartDashboard.putNumber("z", DriveInput.getAxis(Constants.INPUT_AXIS_Z, DriveInput.controllerR));
		SmartDashboard.putNumber("distance A", Encoders.getDistance(Constants.ENCODER_FRONTLEFT));
		SmartDashboard.putNumber("distance B", Encoders.getDistance(Constants.ENCODER_FRONTRIGHT));
	}
}

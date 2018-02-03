/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package src.org.usfirst.frc.team6500.robot;

import org.usfirst.frc.team6500.robot.systems.DriveInput;
import org.usfirst.frc.team6500.robot.systems.Encoders;
import org.usfirst.frc.team6500.robot.systems.Gyro;
import org.usfirst.frc.team6500.robot.systems.Mecanum;

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
		if (DriveInput.getButton(7))
		{
			Mecanum.driveWheel(Ports.DRIVE_FRONTLEFT);
		}
		if (DriveInput.getButton(8))
		{
			Mecanum.driveWheel(Ports.DRIVE_FRONTRIGHT);
		}
		if (DriveInput.getButton(9))
		{
			Mecanum.driveWheel(Ports.DRIVE_REARLEFT);
		}
		if (DriveInput.getButton(10))
		{
			Mecanum.driveWheel(Ports.DRIVE_REARRIGHT);
		}
		
		double multiplier = DriveInput.getThrottle();
		
		if (DriveInput.getTrigger()) {
			boost = Ports.SPEED_BOOST_A;
		}else{
			boost = 0.0;
		}
		
		boost += multiplier;
		
		xspeed = Speed.calculateSpeed(DriveInput.getAxis(Ports.INPUT_AXIS_X), boost);
		yspeed = Speed.calculateSpeed(DriveInput.getAxis(Ports.INPUT_AXIS_Y), boost);
		zspeed = Speed.calculateSpeed(DriveInput.getAxis(Ports.INPUT_AXIS_Z), boost);
		
		Mecanum.driveRobot(xspeed, yspeed, zspeed, Gyro.getAngle());
		
		SmartDashboard.putNumber("Speed Multiplier", multiplier);
	}
}

package org.usfirst.frc.team6500.robot.testsystems;

import java.util.concurrent.TimeUnit;

import org.usfirst.frc.team6500.robot.Constants;
import org.usfirst.frc.team6500.robot.manualpid.PIDDrive;
import org.usfirst.frc.team6500.robot.manualpid.PIDMoveCommand;
import org.usfirst.frc.team6500.robot.sensors.Encoders;
import org.usfirst.frc.team6500.robot.sensors.Gyro;
import org.usfirst.frc.team6500.robot.systems.Mecanum;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ConfigureEncoders {
	
	public ConfigureEncoders() {
		
	}

	public static void getEncoderDistance() {
		Encoders.resetAllEncoders();
		Mecanum.maintainSpeed(true);
		Mecanum.driveRobot(0, .5, 0);
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Mecanum.maintainSpeed(false);
		SmartDashboard.putNumber("Angle travelled: ", Gyro.getAngle());
		SmartDashboard.putNumber("FL", Encoders.getDistance(Constants.ENCODER_FRONTLEFT));
		SmartDashboard.putNumber("FR", Encoders.getDistance(Constants.ENCODER_FRONTRIGHT));
		SmartDashboard.putNumber("RL", Encoders.getDistance(Constants.ENCODER_REARLEFT));
		SmartDashboard.putNumber("RR", Encoders.getDistance(Constants.ENCODER_REARRIGHT));
		SmartDashboard.putNumber("Avg. Distance", Encoders.getAverageDistanceForward());
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		SmartDashboard.putNumber("Angle travelled: ", Gyro.getAngle());
		SmartDashboard.putNumber("FL2", Encoders.getDistance(Constants.ENCODER_FRONTLEFT));
		SmartDashboard.putNumber("FR2", Encoders.getDistance(Constants.ENCODER_FRONTRIGHT));
		SmartDashboard.putNumber("RL2", Encoders.getDistance(Constants.ENCODER_REARLEFT));
		SmartDashboard.putNumber("RR2", Encoders.getDistance(Constants.ENCODER_REARRIGHT));
	}
	
	public static void testBadly(double help) {
		Mecanum.maintainSpeed(true);
		PIDDrive.bleft.setSetpoint(-120);
		PIDDrive.bright.setSetpoint(120);
		PIDDrive.fleft.setSetpoint(120);
		PIDDrive.fright.setSetpoint(-120);
	}
	
	public static void testWorking() {
		PIDMoveCommand testWorking = new PIDMoveCommand(60, 0, 0, false);
		testWorking.start();
		System.out.println("Completed.");
		SmartDashboard.putNumber("Angle travelled: ", Gyro.getAngle());
		SmartDashboard.putNumber("FL", Encoders.getDistance(Constants.ENCODER_FRONTLEFT));
		SmartDashboard.putNumber("FR", Encoders.getDistance(Constants.ENCODER_FRONTRIGHT));
		SmartDashboard.putNumber("RL", Encoders.getDistance(Constants.ENCODER_REARLEFT));
		SmartDashboard.putNumber("RR", Encoders.getDistance(Constants.ENCODER_REARRIGHT));
		SmartDashboard.putNumber("Avg. Distance", Encoders.getAverageDistanceForward());
	}
}
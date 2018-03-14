package org.usfirst.frc.team6500.robot.testsystems;

import java.util.concurrent.TimeUnit;

import org.usfirst.frc.team6500.robot.Constants;
import org.usfirst.frc.team6500.robot.manualpid.PIDMoveCommand;
import org.usfirst.frc.team6500.robot.sensors.Encoders;
import org.usfirst.frc.team6500.robot.sensors.Gyro;
import org.usfirst.frc.team6500.robot.systems.Mecanum;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ConfigureGyro {
	
	public ConfigureGyro() {
		
	}

	public static void getGyroSpin() {
		Mecanum.maintainSpeed(true);
		Mecanum.driveRobot(1, 0, 0);
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Mecanum.maintainSpeed(false);
		Mecanum.driveRobot(0, 0, 0);
		SmartDashboard.putNumber("Angle travelled: ", Gyro.getAngle());
		SmartDashboard.putNumber("FL", Encoders.getDistance(Constants.ENCODER_FRONTLEFT));
		SmartDashboard.putNumber("FR", Encoders.getDistance(Constants.ENCODER_FRONTRIGHT));
		SmartDashboard.putNumber("RL", Encoders.getDistance(Constants.ENCODER_REARLEFT));
		SmartDashboard.putNumber("RR", Encoders.getDistance(Constants.ENCODER_REARRIGHT));
		SmartDashboard.putNumber("Avg. Distance", Encoders.getAverageDistanceForward());
	}
	
	public static void testWorking() {
		PIDMoveCommand testWorking = new PIDMoveCommand(0, 0, 90, false);
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
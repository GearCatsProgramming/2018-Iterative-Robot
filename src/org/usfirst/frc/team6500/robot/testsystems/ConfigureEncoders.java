package org.usfirst.frc.team6500.robot.testsystems;

import java.util.concurrent.TimeUnit;

import org.usfirst.frc.team6500.robot.Constants;
import org.usfirst.frc.team6500.robot.manualpid.PIDMoveCommand;
import org.usfirst.frc.team6500.robot.sensors.Encoders;
import org.usfirst.frc.team6500.robot.sensors.Gyro;
import org.usfirst.frc.team6500.robot.systems.Mecanum;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ConfigureEncoders {
	
	public ConfigureEncoders() {
		
	}

	public static void getGyroSpin() {
		Mecanum.maintainSpeed(true);
		Mecanum.driveRobot(0, 0, 1);
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Mecanum.maintainSpeed(false);
		SmartDashboard.putNumber("Angle travelled: ", Gyro.getAngle());
		SmartDashboard.putNumber("FL", Encoders.getDistance(Constants.ENCODER_FRONTLEFT));
		SmartDashboard.putNumber("FR", Encoders.getDistance(Constants.ENCODER_FRONTRIGHT));
		SmartDashboard.putNumber("RL", Encoders.getDistance(Constants.ENCODER_REARLEFT));
		SmartDashboard.putNumber("RR", Encoders.getDistance(Constants.ENCODER_REARRIGHT));
		SmartDashboard.putNumber("Avg. Distance", Encoders.getAverageDistance());
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
		SmartDashboard.putNumber("Avg. Distance", Encoders.getAverageDistance());
	}
}
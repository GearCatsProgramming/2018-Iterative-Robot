package org.usfirst.frc.team6500.robot;

import org.usfirst.frc.team6500.robot.sensors.Encoders;
import org.usfirst.frc.team6500.robot.sensors.Gyro;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Dashboard {
	
	
	public static void updateDashboard() {
		SmartDashboard.putNumber("Speed Multiplier", Robot.multiplier);
		SmartDashboard.putNumber("Y Speed (Sideways)", Robot.yspeed);
		SmartDashboard.putNumber("X Speed (Forward/Backward)", Robot.xspeed);
		SmartDashboard.putNumber("Z Speed (Rotational)", Robot.zspeed);
		SmartDashboard.putNumber("Encoder: Front Left", Encoders.getDistance(Constants.ENCODER_FRONTLEFT));
		SmartDashboard.putNumber("Encoder: Front Right", Encoders.getDistance(Constants.ENCODER_FRONTRIGHT));
		SmartDashboard.putNumber("Encoder: Back Left", Encoders.getDistance(Constants.ENCODER_REARLEFT));
		SmartDashboard.putNumber("Encoder: Back Right", Encoders.getDistance(Constants.ENCODER_REARRIGHT));
		SmartDashboard.putNumber("Gyroscope reading", Gyro.getAngle());
		SmartDashboard.putNumber("Encoders: Average", Encoders.getAverageDistance());
	}
}

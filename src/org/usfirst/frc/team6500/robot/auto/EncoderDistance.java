package org.usfirst.frc.team6500.robot.auto;
import org.usfirst.frc.team6500.robot.Constants;
import org.usfirst.frc.team6500.robot.sensors.Encoders;
import org.usfirst.frc.team6500.robot.sensors.Gyro;
import org.usfirst.frc.team6500.robot.systems.Mecanum;
//The code is base off as soon as ONE of the encoders reaching the intended distant inputted. However, if this proves inconsistent
//then one wheel can be made the leading wheel to base the distance off of which will prove more consistent.
//02 does the exact same command as the other code however uses different wheels.
public class EncoderDistance {
	//MOVE ROBOT STRAIGHT
	public static void getFoward(double distance,double speed){
		while((Encoders.getDistance(Constants.DRIVE_FRONTLEFT) <= distance) && (Encoders.getDistance(Constants.DRIVE_FRONTRIGHT) <= distance) && (Encoders.getDistance(Constants.DRIVE_REARLEFT) <= distance) && (Encoders.getDistance(Constants.DRIVE_REARRIGHT) <= distance))                                                                 
		{
			Mecanum.driveRobot(0, speed, 0);
		}
	}
	//MOVE ROBOT SIDE RIGHT
	public static void getSideRight(double distance, double speed){
		while((Encoders.getDistance(Constants.DRIVE_FRONTLEFT) <= distance) && (Encoders.getDistance(Constants.DRIVE_REARRIGHT) <= distance)){
			Mecanum.driveWheel(Constants.DRIVE_FRONTLEFT, speed);
			Mecanum.driveWheel(Constants.DRIVE_REARRIGHT, speed);
		}
	}
	//MOVE ROBOT SIDE LEFT
	public static void getSideLeft(double distance, double speed){
		while((Encoders.getDistance(Constants.DRIVE_FRONTLEFT) <= distance) && (Encoders.getDistance(Constants.DRIVE_REARRIGHT) <= distance)){
			Mecanum.driveWheel(Constants.DRIVE_FRONTLEFT, -speed);
			Mecanum.driveWheel(Constants.DRIVE_REARRIGHT, -speed);
		}
	}
	//ROTATES ROBOT RIGHT
	public static void rotateRight(double distance, double speed){
		while((Encoders.getDistance(Constants.DRIVE_FRONTLEFT) <= distance) && (Encoders.getDistance(Constants.DRIVE_REARRIGHT) <= distance)){
			Mecanum.driveWheel(Constants.DRIVE_FRONTLEFT, speed);
			Mecanum.driveWheel(Constants.DRIVE_REARRIGHT, -speed);
		}
	}
	//ROTATES ROBOT LEFT
	public static void rotateLeft(double distance, double speed){
		while((Encoders.getDistance(Constants.DRIVE_FRONTLEFT) <= distance) && (Encoders.getDistance(Constants.DRIVE_REARRIGHT) <= distance)){
			Mecanum.driveWheel(Constants.DRIVE_FRONTLEFT, -speed);
			Mecanum.driveWheel(Constants.DRIVE_REARRIGHT, speed);
		}
	}

	public static void rotateRight02(double distance, double speed){
		while((Encoders.getDistance(Constants.DRIVE_FRONTRIGHT) <= distance) && (Encoders.getDistance(Constants.DRIVE_REARLEFT) <= distance)){
			Mecanum.driveWheel(Constants.DRIVE_FRONTRIGHT, speed);
			Mecanum.driveWheel(Constants.DRIVE_REARLEFT, -speed);
		}
	}
	
	public static void rotateLeft02(double distance, double speed){
		while((Encoders.getDistance(Constants.DRIVE_FRONTRIGHT) <= distance) && (Encoders.getDistance(Constants.DRIVE_REARRIGHT) <= distance)){
			Mecanum.driveWheel(Constants.DRIVE_FRONTRIGHT, -speed);
			Mecanum.driveWheel(Constants.DRIVE_REARRIGHT, speed);
		}
	}
	
	public static void getSideRight02(double distance, double speed){
		while((Encoders.getDistance(Constants.DRIVE_FRONTRIGHT) <= distance) && (Encoders.getDistance(Constants.DRIVE_REARLEFT) <= distance)){
			Mecanum.driveWheel(Constants.DRIVE_FRONTRIGHT, speed);
			Mecanum.driveWheel(Constants.DRIVE_REARLEFT, speed);
		}
	}
}


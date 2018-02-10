//package org.usfirst.frc.team6500.robot.pid;
//
//import org.usfirst.frc.team6500.robot.systems.Mecanum;
//
//import edu.wpi.first.wpilibj.PIDController;
//import edu.wpi.first.wpilibj.PIDOutput;
//import edu.wpi.first.wpilibj.PIDSource;
//
///**Maybe a controller of some sort? Manipulates the drive train
// * 
// * @author Thomas Dearth
// *
// */
//public class PidController extends PIDController {
////TODO: Figure out what the heck this is about
//	static PidMotor blpid = new PidMotor(Mecanum.bleft);
//	static PidMotor brpid = new PidMotor(Mecanum.bright);
//	static PidMotor flpid = new PidMotor(Mecanum.fleft);
//	static PidMotor frpid = new PidMotor(Mecanum.fright);
//	static PidGyro gyropid = new PidGyro();
//	
//	xSpeed, ySpeed, zSpeed;
//	
//	public PidController(double Kp, double Ki, double Kd, double Kf, PIDSource source, PIDOutput output) {
//		super(Kp, Ki, Kd, Kf, source, output);
//	}
//	
//	public static void driveStraight(double distance) {
//		blpid.setTarget(distance);
//		brpid.setTarget(distance);
//		flpid.setTarget(distance);
//		frpid.setTarget(distance);
//	}
//	
//	public static void rotate(double degrees) {
//		gyropid.setTarget(degrees);
//	}
//}

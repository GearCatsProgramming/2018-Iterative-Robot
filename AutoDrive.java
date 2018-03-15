package driveutil;

import org.usfirst.frc.team6500.robot.Constants;
import org.usfirst.frc.team6500.robot.sensors.Encoders;
import org.usfirst.frc.team6500.robot.systems.Grabber;
import org.usfirst.frc.team6500.robot.systems.Mecanum;

public class AutoDrive { //drive functions for taking in cube w/ CV

	boolean isInTeleop;
	boolean isDone;
	boolean angleMatched = false, distanceMatched = false, centered = false;
	double intakeDriveSpeed = 0.1; //need to test this in the real world to calibrate it
	
	int thresholdY = 50; //# of pixels from the bottom of the camera view to the bottom of the cube immediately before intake - Must be calibrated! 
	int middleX = 200; //center of the camera view in X. Must be changed according to resolution used. TODO: make this not hardcoded, but reference Robot.java
	int Xthreshold = 10; //how far off is the x-value allowed to be, such that we can still get the cube? need to calibrate
	int angleThreshold = 10; //how far off can the top and bottom of the cubeline be, such that we can still pick up the cube? Need to calibrate
	
	AutoDrive(boolean isInTeleop) {
		// setup two points from vision
		// make sure linked to IMU
		// grabber ready
		// get start time
		// are we in teleop? 
		this.isInTeleop = isInTeleop;
		isDone = false;
		
		Encoders.resetAllEncoders();
		Encoders.setDirection(Constants.DIRECTION_FORWARD);
		
		
	}
	
	public void update(int topX, int topY, int bottomX, int bottomY) {
		//if it's taking too long, revert
		
		//get the two points
				
		//match the angle of the cube
		if((topX-bottomX)>angleThreshold) rotateClockwise(.2);
		if((bottomX-topX)>angleThreshold) rotateCounterClockwise(.2);
		
		//center the cube within the grabber
		if((bottomX-middleX)>Xthreshold) strafeLeft(.2);
		if((middleX-bottomX)>Xthreshold) strafeRight(.2);
				
		//drive to the cube
		
		if(bottomY<thresholdY) driveToCube(0.2);
		else {
			quitDriving();
			
		}
		
		//if lined up with the cube, go get it
		if(angleMatched && distanceMatched && centered) intake();
		
	}
	
	private void onExit() {
		quitDriving();
		Grabber.killGrab();
	}
	
	//match angle to vision
	private void rotateClockwise(double speed) {
		//run frontleft wheel forwards, frontright wheel backwards, 
				//backleft wheel forwards, backright wheel backwards
				//running all at speed parameter - eventually build in speed proportional to distance from center? 
				
				Mecanum.driveRobot(0, 0, speed);
	}
	
	private void rotateCounterClockwise(double speed) {
		//run frontleft wheel backwards, frontright wheel forwards, 
				//backleft wheel backwards, backright wheel forwards
				//running all at speed parameter - eventually build in speed proportional to distance from center? 
				
				Mecanum.driveRobot(0, 0, -1*speed);
		
	}
	
	//strafe to center vision
	public void strafeLeft(double speed) {
		//run frontleft wheel backwards, frontright wheel forwards, 
		//backleft wheel forwards, backright wheel backwards
		//running all at speed parameter - eventually build in speed proportional to distance from center? 
		
		Mecanum.driveRobot(0, -1*speed, 0);
		
	}
	
	public void strafeRight(double speed) {
			//run frontleft wheel forwards, frontright wheel backwards, 
			//backleft wheel backwards, backright wheel forwards
			//running all at speed parameter - eventually build in speed proportional to distance from center? 
			
			Mecanum.driveRobot(0, speed, 0);
			
		
	}
	
	//drive to cube
	public void driveToCube(double speed) {
		
	//turn all wheels forward to drive forward
		//run at speed parameter, eventually build in speed proportional to distance from cube? 
		
		Mecanum.driveRobot(speed, 0, 0);
		
	}
	
	//stop driving to cube
	public void quitDriving() {
		//stop all the driving in order to get things set up for cube intake
		
		Mecanum.driveRobot(0, 0, 0);
		
	}
	
	//with robot lined up with cube, intake the cube
		public void intake() { 
			
			Mecanum.driveRobot(intakeDriveSpeed, 0, 0);
			Grabber.grabCube();
			this.isDone = true;
			
		}
	
}

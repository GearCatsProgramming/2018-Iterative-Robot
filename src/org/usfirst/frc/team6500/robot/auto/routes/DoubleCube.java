package org.usfirst.frc.team6500.robot.auto.routes;

import org.usfirst.frc.team6500.robot.Robot;
import org.usfirst.frc.team6500.robot.auto.AutoRoute;
import org.usfirst.frc.team6500.robot.auto.AutoUtilThread;
import org.usfirst.frc.team6500.robot.auto.AutoUtils;
import org.usfirst.frc.team6500.robot.auto.AutoWrapper;

/**An ambitious plan to use TWO CUBES. Runs ForwardScale, then grabs another cube and uses it somehow.
 * @author Kyle Miller
 * @see ForwardScale
 */
public class DoubleCube implements AutoRoute {
	private static double inches0 = 50.0 - 12.5;
	private static double inches1 = 12.0;
	private double inches2 = 7.5;
	
	private static double degrees0 = -180.0;
	
	private boolean done;
	private Robot robot;
	private double speed;
	
	private boolean left, scaleNotSwitch;
	
	/**Creates the command. Use it with run();
	 * 
	 * @param speed The speed at which the robot goes
	 * @param left Whether the robot starts on the left of the field
	 * @param robot the robot doing it
	 * @param scaleNotSwitch Puts the second cube on the scale if true
	 */
	public DoubleCube(double speed, boolean left, Robot robot, boolean scaleNotSwitch) {
		if(left) { inches1 *= -1; degrees0 *= -1; }
		
		this.speed = speed;
		this.robot = robot;
		this.done = false;
		this.left = left;
		this.scaleNotSwitch = scaleNotSwitch;
	}
	
	/**Activates the route.
	 */
	@Override
	public void run() {
		ForwardScale thing = new ForwardScale(speed, left, robot);
		thing.run();
		while(!thing.isDone()) {}
		
		AutoWrapper.rotate(degrees0, this.speed, robot);
		AutoWrapper.goForward(inches0, this.speed, robot);
		AutoWrapper.leftRight(inches1, this.speed, robot);
		
		(new AutoUtilThread(AutoUtilThread.actionType.grabCube)).start();
		
		AutoWrapper.goForward(inches0, this.speed, robot);
		
		if (this.scaleNotSwitch)
		{
			//Mirror mirror on the wall...
			(new AutoUtilThread(AutoUtilThread.actionType.liftToScale)).start();

			AutoWrapper.goForward(-inches0, this.speed, robot);
						
			AutoWrapper.leftRight(-inches1, this.speed, robot);
			AutoWrapper.goForward(inches0, this.speed, robot);
			AutoWrapper.rotate(-degrees0, this.speed, robot);
		}
		else
		{
			//What is the saddest thing of all?
			(new AutoUtilThread(AutoUtilThread.actionType.liftToSwitch)).start();
			
			AutoWrapper.goForward(inches2, this.speed, robot);
		}
		
		AutoUtils.ejectCube();
		
		//PROGRAMMING DIDN'T GET MORE THAN 5 HOURS DURING BUILD SEASON
		
		done = true;
	}

	@Override
	public boolean isDone() {return this.done;}

}

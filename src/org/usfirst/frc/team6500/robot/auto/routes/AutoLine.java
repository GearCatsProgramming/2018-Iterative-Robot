package org.usfirst.frc.team6500.robot.auto.routes;

import org.usfirst.frc.team6500.robot.Robot;
import org.usfirst.frc.team6500.robot.auto.AutoRoute;
import org.usfirst.frc.team6500.robot.auto.AutoWrapper;

/**Defaut route used for crossing the auto line. Serves as a backup.
 * @author Kyle Miller
 */
public class AutoLine implements AutoRoute
{
	private double inches, speed;
	private boolean done;
	private Robot robot;
	
	public AutoLine(double speed, Robot robot)
	{
		this.inches = 130.0;
		this.speed = speed;
		this.done = false;
		this.robot = robot;
	}
	
	@Override
	public void run() {
		AutoWrapper.goForward(this.inches, this.speed, robot);
//		AutoWrapper.rotate(90.0, this.speed, robot);
		
		this.done = true;
	}
	
	@Override
	public boolean isDone() { return this.done; }

}
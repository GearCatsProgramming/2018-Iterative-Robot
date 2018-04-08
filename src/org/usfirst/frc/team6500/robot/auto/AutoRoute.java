package org.usfirst.frc.team6500.robot.auto;

/**
 * Guarantees compatibility within the code for all of the routes, all routes should implement this
 * 
 * @author Kyle
 *
 */
public interface AutoRoute
{
	/**
	 * Main runner method of the route, just tells the robot to run it
	 */
	public void run();
	
	/**
	 * Indicates whether the route has completed
	 */
	boolean isDone();
}
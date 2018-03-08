package org.usfirst.frc.team6500.robot.auto.routes.manualpidroutes;

import org.usfirst.frc.team6500.robot.manualpid.PIDMoveCommand;
import org.usfirst.frc.team6500.robot.systems.Grabber;
import org.usfirst.frc.team6500.robot.systems.Lift;
import org.usfirst.frc.team6500.robot.systems.Mecanum;

/**Contains methods for autonomous routinens when starting in the center of the field.
 * @author Thomas Dearth
 */
public class CenterManeuvers {
	/**Places the cube on the switch
	 * @author Thomas Dearth
	 * @param leftField Determines whether the scale moves to the left or right
	 */
	public static void goToSwitch(boolean leftField) {
		Mecanum.maintainWheelSpeed(true);
		
		//Move to switch
		PIDMoveCommand moveToSwitch1 = new PIDMoveCommand(100, 80, -90, leftField);
		moveToSwitch1.start();
		Lift.raiseLift();
		PIDMoveCommand.wait(1.0);
		Lift.stopLift();
		PIDMoveCommand.holdYourHorses(moveToSwitch1);
		
		PIDMoveCommand moveToSwitch2 = new PIDMoveCommand(200, 0, -90, leftField);
		moveToSwitch2.start();
		PIDMoveCommand.holdYourHorses(moveToSwitch2);
		
		//THROW THE CHEESE
		Grabber.ejectCube();
		PIDMoveCommand.wait(0.5);
		Grabber.killGrab();
		
		//TODO: Grab more cheese
		Mecanum.maintainWheelSpeed(false);
	}
	
	/**Places the cube on the scale
	 * @author Thomas Dearth
	 * @param leftScale
	 */
	public static void goToScale(boolean leftScale) {
		Mecanum.maintainWheelSpeed(true);
		
		//Move to scale
		PIDMoveCommand moveToScale1 = new PIDMoveCommand(100, 75, 0, leftScale);
		moveToScale1.start();
		Lift.raiseLift();
		PIDMoveCommand.wait(1.0);
		Lift.stopLift();
		PIDMoveCommand.holdYourHorses(moveToScale1);
		
		PIDMoveCommand moveToScale2 = new PIDMoveCommand(200, 0, 0, leftScale);
		moveToScale2.start();
		PIDMoveCommand.holdYourHorses(moveToScale2);
		
		//THROW THE CHEESE
		Grabber.ejectCube();
		PIDMoveCommand.wait(0.5);
		Grabber.killGrab();
		
		//TODO: Grab more cheese
		Mecanum.maintainWheelSpeed(false);
	}
}

package org.usfirst.frc.team6500.robot.auto.routes.manualpidroutes;

import org.usfirst.frc.team6500.robot.manualpid.PIDMoveCommand;
import org.usfirst.frc.team6500.robot.systems.Grabber;
import org.usfirst.frc.team6500.robot.systems.Lift;
import org.usfirst.frc.team6500.robot.systems.Mecanum;

/**Contains methods for autonomous routines when starting on the left or right.
 * @author Thomas Dearth
 */
public class SideManeuvers {
	/**Places the cube on the switch
	 * @author Thomas Dearth
	 * @param leftField Whether the cube starts on the left side
	 */
	public static void sameSwitch(boolean leftField) {
		Mecanum.maintainWheelSpeed(true);
		
		//Move to switch
		PIDMoveCommand moveToSwitch = new PIDMoveCommand(130, 0, -90, leftField);	//TODO: Adjust forward distance for all of these- consult game strategy
		moveToSwitch.start();
		Lift.raiseLift();
		PIDMoveCommand.wait(1.0);
		Lift.stopLift();
		PIDMoveCommand.holdYourHorses(moveToSwitch);
		
		//THROW THE CHEESE
		Grabber.ejectCube();
		PIDMoveCommand.wait(0.5);
		Grabber.killGrab();
		
		//TODO: Grab more cheese
		Mecanum.maintainWheelSpeed(false);
	}
	
	/**Placs the cube on the switch when it's found on the opposite side of the field
	 * @author Thomas Dearth
	 * @param leftField Whether the cube starts on the left side
	 */
	public static void oppositeSwitch(boolean leftField) {
		Mecanum.maintainWheelSpeed(true);
		
		//Move to outside
		PIDMoveCommand moveToOutside = new PIDMoveCommand(160, 0, -90, leftField);
		moveToOutside.start();
		Lift.raiseLift();
		PIDMoveCommand.wait(1.0);
		Lift.stopLift();
		PIDMoveCommand.holdYourHorses(moveToOutside);
		
		//Move to opposite switch
		PIDMoveCommand moveToSwitch = new PIDMoveCommand(150, 0, -90, leftField);
		moveToSwitch.start();
		PIDMoveCommand.holdYourHorses(moveToSwitch);
		
		//THROW THE CHEESE
		Grabber.ejectCube();
		PIDMoveCommand.wait(0.5);
		Grabber.killGrab();
		
		//TODO: Grab more cheese
		Mecanum.maintainWheelSpeed(false);
	}
	
	/**Places the cube on the switch from the left side of the field
	 * @author Thomas Dearth
	 * @param leftField Whether the cube starts on the left side
	 * @param leftSwitch Whether the switch is on the left of the field
	 */
	public static void sameScale(boolean leftField, boolean leftSwitch) {
		Mecanum.maintainWheelSpeed(true);
		
		//Move to scale
		PIDMoveCommand moveToScale = new PIDMoveCommand(300, 0, 0, leftField);
		moveToScale.start();
		Lift.raiseLift();
		PIDMoveCommand.wait(1.0);
		Lift.stopLift();
		PIDMoveCommand.holdYourHorses(moveToScale);
		
		//THROW THE CHEESE
		Grabber.ejectCube();
		PIDMoveCommand.wait(0.5);
		Grabber.killGrab();
		
		//TODO: Grab more cheese
		Mecanum.maintainWheelSpeed(false);
	}
}

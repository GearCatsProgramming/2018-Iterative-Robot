package org.usfirst.frc.team6500.robot.auto.routes.manualpidroutes;

import org.usfirst.frc.team6500.robot.manualpid.PIDMoveCommand;

public class LLSwitch {
	public static void start(boolean left) {
		//Move to switch
		(new PIDMoveCommand(60, 0, 90, left)).start();	//TODO: Adjust forward distance for all of these- consult game strategy
		
		//Move to auto line TODO: Do this
	}
}

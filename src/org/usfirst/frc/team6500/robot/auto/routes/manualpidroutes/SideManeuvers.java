package org.usfirst.frc.team6500.robot.auto.routes.manualpidroutes;

import java.util.concurrent.TimeUnit;

import org.usfirst.frc.team6500.robot.manualpid.PIDMoveCommand;
import org.usfirst.frc.team6500.robot.systems.Grabber;
import org.usfirst.frc.team6500.robot.systems.Lift;
import org.usfirst.frc.team6500.robot.systems.Mecanum;

public class SideManeuvers {
	public static void placeSwitch(boolean left) {
		Mecanum.maintainWheelSpeed(true);
		
		//Move to switch
		PIDMoveCommand moveToSwitch = new PIDMoveCommand(80, 0, -90, left);	//TODO: Adjust forward distance for all of these- consult game strategy
		moveToSwitch.start();
		Lift.raiseLift();
		try {
			TimeUnit.SECONDS.sleep((long) 1);
		} catch (InterruptedException e) { e.printStackTrace(); }
		holdYourHorses(moveToSwitch);
		
		//THROW THE CHEESE
		Grabber.ejectCube();
		
		//TODO: Grab more cheese
		Mecanum.maintainWheelSpeed(false);
	}
	
	//public static void placeScale TODO: this
	
	public static void holdYourHorses(PIDMoveCommand command) {
		while(!command.isAlive()) {
			System.out.println("\"I met a traveler from an antique land who said: ");
		}
	}
	
	public static void main(String[] args) {
		PIDMoveCommand d = new PIDMoveCommand(1, 2, 3, false);
		holdYourHorses(d);
	}
}

package org.usfirst.frc.team6500.robot.auto.routes;

import org.usfirst.frc.team6500.robot.Position;
import org.usfirst.frc.team6500.robot.Robot;
import org.usfirst.frc.team6500.robot.Target;
import org.usfirst.frc.team6500.robot.auto.AutoRoute;

/**Route map attempt with route prioritization
 * RouteMap2: Electric Boogaloo
 * @author Thomas Dearth
 */
public class RouteMap2 
{
	public static AutoRoute getRoute(double autoSpeed, Robot robot, Position start, Position switchLoc, Position scaleLoc, Target priority) {
		//If everything is nonfunctional, die a bit inside
		if(priority == Target.hitAutoLine) { return new AutoLine(autoSpeed, robot); }
		
		//If we're in the middle, check to see if we want the scale or the switch, and do that
		if(start == Position.middle) {
			if(priority == Target.hitSwitch) {
				return new MiddleSwitch(autoSpeed, toBoolean(switchLoc), robot);
			} else {
				return new CenterScale(autoSpeed, toBoolean(scaleLoc), robot);
			}
		}
		
		//If we're not on the middle, check the priority, then if that's on the far side, do the other thing
		if(priority == Target.hitSwitch) {
			if(start == switchLoc) {			return new ForwardSwitch(autoSpeed, toBoolean(start), robot);
			} else if(start == scaleLoc) {		return new ForwardScale(autoSpeed, toBoolean(start), robot);
			} else {							return new OppositeSwitch(autoSpeed, toBoolean(start), robot); }
		} else {
			if(start == scaleLoc) { 			return new ForwardScale(autoSpeed, toBoolean(start), robot);
			} else if(start == switchLoc) { 	return new ForwardSwitch(autoSpeed, toBoolean(start), robot);
			} else { 							return new OppositeScale(autoSpeed, toBoolean(start), robot); }
		}
	}
	
	public static boolean toBoolean(Position side) {
		if(side == Position.left) { 		return true; }
		else if(side == Position.right) { 	return false; }
		else { 								return false; }
	}
}

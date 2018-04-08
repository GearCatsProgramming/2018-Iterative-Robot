package org.usfirst.frc.team6500.robot.auto.routes;

import org.usfirst.frc.team6500.robot.Position;
import org.usfirst.frc.team6500.robot.Robot;
import org.usfirst.frc.team6500.robot.Goal;
import org.usfirst.frc.team6500.robot.auto.AutoRoute;

/**Route map attempt with route prioritization based on proximity.
 * @author Thomas Dearth
 */
public class RouteMap 
{
	/**Gets the desired route. Maybe.
	 * @author Thomas Dearth
	 * @param autoSpeed The speed the route is run at.
	 * @param robot The robot doing it. WHY U NO STATIC
	 * @param start The starting position {@link Position}
	 * @param switchLoc The position of the friendly side of the switch {@link Position}
	 * @param scaleLoc The position of the friendly side of the scale {@link Position}
	 * @param priority The thing we'd rather put cubes on {@link Goal}
	 * @return The route we're going to do.
	 */
	public static AutoRoute getRoute(double autoSpeed, Robot robot, Position start, Position switchLoc, Position scaleLoc, Goal priority) {
		//If everything is nonfunctional, die a bit inside
		if(priority == Goal.hitAutoLine) { return new AutoLine(autoSpeed, robot); }
		
		//If we're in the middle, check to see if we want the scale or the switch, and do that
		if(start == Position.middle) {
			if(priority == Goal.hitSwitch) {
				return new MiddleSwitch(autoSpeed, Position.toBoolean(switchLoc), robot);
			} else {
				return new MiddleScale(autoSpeed, Position.toBoolean(scaleLoc), robot);
			}
		}
		
		//If we're not on the middle, check the priority, then if that's on the far side, do the other thing
		if(priority == Goal.hitSwitch) {
			if(start == switchLoc) {			return new ForwardSwitch(autoSpeed, Position.toBoolean(start), robot);
			} else if(start == scaleLoc) {		return new ForwardScale(autoSpeed, Position.toBoolean(start), robot);
			} else {							return new OppositeScale(autoSpeed, Position.toBoolean(start), robot); }
		}
		else
		{
			if(start == scaleLoc) { 			return new ForwardScale(autoSpeed, Position.toBoolean(start), robot);
			} else if(start == switchLoc) { 	return new ForwardSwitch(autoSpeed, Position.toBoolean(start), robot);
			} else { 							return new OppositeScale(autoSpeed, Position.toBoolean(start), robot); }
		}
	}
}

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
	/**Gets the desired route. Maybe.
	 * @author Thomas Dearth
	 * @param autoSpeed The speed the route is run at.
	 * @param robot The robot doing it. WHY U NO STATIC
	 * @param start The starting position {@link Position}
	 * @param switchLoc The position of the friendly side of the switch {@link Position}
	 * @param scaleLoc The position of the friendly side of the scale {@link Position}
	 * @param priority The thing we'd rather put cubebs on {@link Target}
	 * @return The route we're going to do.
	 */
	public static AutoRoute getRoute(double autoSpeed, Robot robot, Position start, Position switchLoc, Position scaleLoc, Target priority) {
		//If everything is nonfunctional, die a bit inside
		if(priority == Target.hitAutoLine) { return new AutoLine(autoSpeed, robot); }
		
		//If we're in the middle, check to see if we want the scale or the switch, and do that
		if(start == Position.middle) {
			if(priority == Target.hitSwitch) {
				return new MiddleSwitch(autoSpeed, Position.toBoolean(switchLoc), robot);
			} else {
				return new CenterScale(autoSpeed, Position.toBoolean(scaleLoc), robot);
			}
		}
		
		//If we're not on the middle, check the priority, then if that's on the far side, do the other thing
		if(priority == Target.hitSwitch) {
			if(start == switchLoc) {			return new ForwardSwitch(autoSpeed, Position.toBoolean(start), robot);
			} else if(start == scaleLoc) {		return new ForwardScale(autoSpeed, Position.toBoolean(start), robot);
			} else {							return new OppositeSwitch(autoSpeed, Position.toBoolean(start), robot); }
		} else {
			if(start == scaleLoc) { 			return new ForwardScale(autoSpeed, Position.toBoolean(start), robot);
			} else if(start == switchLoc) { 	return new ForwardSwitch(autoSpeed, Position.toBoolean(start), robot);
			} else { 							return new OppositeScale(autoSpeed, Position.toBoolean(start), robot); }
		}
	}
}

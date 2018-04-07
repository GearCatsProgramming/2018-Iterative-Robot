package org.usfirst.frc.team6500.robot.auto.routes;

import org.usfirst.frc.team6500.robot.Robot;
import org.usfirst.frc.team6500.robot.auto.AutoRoute;


public class RouteMap {

	AutoRoute[][]  switchRoutes = null;
	
	AutoRoute[][]  scaleRoutes = null;
	
	static final int left=1, middle=2, right = 3; 

	
	public RouteMap(double autoSpeed, Robot robot) {
		
		//switch routes
		
		switchRoutes[left][left] = new ForwardSwitch(autoSpeed, true, robot);
		switchRoutes[left][right] = new EvadeSwitch(autoSpeed, true, robot);
		
		switchRoutes[right][left] = new EvadeSwitch(autoSpeed, false, robot);
		switchRoutes[right][right] = new ForwardSwitch(autoSpeed, true, robot);		

		switchRoutes[middle][left] = new MiddleSwitch(autoSpeed, true, robot);
		switchRoutes[middle][right] = new MiddleSwitch(autoSpeed, false, robot);

		//scale routes

		scaleRoutes[left][left] = new ForwardScale(autoSpeed, true, robot);
		scaleRoutes[left][right] = new ForwardRoute(130.0, 0.5, robot);
		
		scaleRoutes[right][left] = new ForwardRoute(130.0, 0.5, robot);
		scaleRoutes[right][right] = new ForwardScale(autoSpeed, true, robot);		

		scaleRoutes[middle][left] = new AutoLine(autoSpeed, robot);
		scaleRoutes[middle][right] = new AutoLine(autoSpeed, robot);

	
	}
	
	public AutoRoute getRoute(boolean scaleLeft, boolean switchLeft, int autoPos, int autoTarget, Robot robot) {
		
		AutoRoute route = new ForwardRoute(130.0, 0.5, robot);
		
		int switchPos = (switchLeft ? left : right);	// where is our SWITCH
		int scalePos = (scaleLeft ? left : right);		// where is our SCALE
		
		if (autoTarget==1) {  // switch
			return switchRoutes[autoPos][switchPos];
		}
		
		if (autoTarget==4) {  // scale
			return scaleRoutes[autoPos][scalePos];
		}
		
		if (autoTarget==3) { 
			return  new ForwardRoute(130.0, 0.5, robot);
		}
		
		
				
		return route;
	}
	
}

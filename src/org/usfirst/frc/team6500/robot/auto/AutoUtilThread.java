package org.usfirst.frc.team6500.robot.auto;

import org.usfirst.frc.team6500.robot.Robot;

/**Runs threaded version of AutoUtils to allow for concurrent driving and actions.
 * @author Thomas Dearth
 * @see {@link AutoUtils}, {@link AutoUtilThread.actionType}, {@link Thread}
 */
public class AutoUtilThread extends Thread {
	private actionType type; 
	
	public AutoUtilThread(actionType type) {
		this.type = type;
	}
	
	@Override
	public void run() {
		Robot.hitList.add(this);
		if(type == actionType.liftToScale) {
			AutoUtils.liftToScale();
		} else if(type == actionType.liftToSwitch) {
			AutoUtils.liftToSwitch();
		} else if(type == actionType.lowerFromScale) {
			AutoUtils.lowerFromScale();
		} else if(type == actionType.lowerFromSwitch) {
			AutoUtils.lowerFromSwitch();
		} else if(type == actionType.ejectCube) {
			AutoUtils.ejectCube();
		} else if(type == actionType.grabCube) {
			AutoUtils.grabCube();
		} else if(type == actionType.resetLift) {
			AutoUtils.resetLift();
		}
	}
	
	/**An enum containing a list of actions which can be performed*/
	public enum actionType {
		liftToScale, liftToSwitch, ejectCube,
		grabCube, lowerFromScale, lowerFromSwitch,
		resetLift
	}
}

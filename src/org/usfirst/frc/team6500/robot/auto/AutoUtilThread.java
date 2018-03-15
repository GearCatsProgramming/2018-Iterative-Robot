package org.usfirst.frc.team6500.robot.auto;

import org.usfirst.frc.team6500.robot.Robot;

public class AutoUtilThread extends Thread{
	private actionType type; 
	
	public AutoUtilThread(actionType type) {
		this.type = type;
	}
	
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
		} else {
			AutoUtils.grabCube();
		}
	}
	
	public enum actionType {
		liftToScale, liftToSwitch, ejectCube,
		grabCube, lowerFromScale, lowerFromSwitch
	}
}

package org.usfirst.frc.team6500.robot.manualpid;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

/**Really bad practice PID source.
 * Takes an input through pidAdd() and outputs from pidGet().
 * @author Thomas Dearth. Explains a lot.
 */
public class ManualTestSource implements PIDSource{
	private double currentValue;
	
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		//We've got nothing. Don't even try.
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		//We don't have one; deal with it.
		return null;
	}

	@Override
	/**Gets the value of the current distance moved.
	 * @author Thomas Dearth; Kyle save us all
	 */
	public double pidGet() {
		return currentValue;
	}
	
	/**Set the distance to dist
	 * @author Thomas Dearth
	 * @param dist The distance of the source
	 */
	public void pidSet(double dist) {
		currentValue=dist;
	}
	
	/**Adds dist to the distance
	 * @author Thomas Dearth
	 * @param dist The distance to be added
	 */
	public void pidAdd(double dist) {
		currentValue+=dist;
	}
	
	/**Sets the distance back to 0;
	 * @author Thomas Dearth
	 */
	public void reset() {
		currentValue = 0;
	}
}

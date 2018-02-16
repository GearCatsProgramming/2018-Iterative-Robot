package org.usfirst.frc.team6500.robot.manualpid;

import org.usfirst.frc.team6500.robot.Constants;
import edu.wpi.first.wpilibj.PIDSource;

/**A class created to be a PID system all on its own. It's a thread; create it with ManualPID testPID = new ManualPID(testSource);
 * @author Thomas Dearth
 * @see Thread
 */
public class ManualPID extends Thread{
	private double integral, previous_error, setpoint;
	private double pidCalc = 0;
	private double acceptableError = 0;
	private PIDSource source;
	
	public ManualPID(PIDSource source) {
		System.out.println("Drive PID system created.");
		this.source = source;
		pidCalc = 0;
	}
	
	/**Sets the target value for the system to approach.
	 * @author Thomas Dearth
	 * @param d The target value to reach
	 */
	public void setSetpoint(double d) {
		this.setpoint = d;
	}
	
	/**Makes the PID system adjust values. The core of the system.*/
	public void PID() {
		double error = setpoint - source.pidGet();
		this.integral += (error*0.02);
		double derivative = (error - this.previous_error) / 0.02;
//		if(P*error + I*this.integral + D*derivative < maxChange) {
			this.pidCalc += Constants.PID_P*error + Constants.PID_I*this.integral + Constants.PID_D*derivative;
//		} else {
//			this.pidCalc += maxChange;
//		}
		this.previous_error = error;
	}
	/**Gets the distance the system has gone since it was reset.
	 * @author Thomas Dearth
	 * @return The distance the PIDSource has traveled
	 */
	public double getDistance() {
		return pidCalc;
	}
	
	/**@author Thomas Dearth
	 * @return True if the robot is in bounds, or False otherwise
	 */
	public boolean isInBounds() {
		return pidCalc > setpoint - acceptableError && pidCalc < setpoint + acceptableError;
	}
	
//	public static void main(String[] args) {
//		System.out.println("Initiating test sequence.");
//		ManualTestSource testSource = new ManualTestSource();
//		ManualPID testPID = new ManualPID(testSource);
//		testPID.setSetpoint(120);
//		while(!testPID.isInBounds()) {
//			testPID.execute();
//			try {
//				TimeUnit.SECONDS.sleep((long) 1);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}
}

package org.usfirst.frc.team6500.robot.auto;

import org.usfirst.frc.team6500.robot.Constants;
import org.usfirst.frc.team6500.robot.sensors.Encoders;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**PIDSource for movement of drivetrain
 * 
 * @author Kyle
 *
 */
public class PIDInputDrive implements PIDSource {
	private PIDSourceType m_pidSource = PIDSourceType.kDisplacement;
	
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		m_pidSource = pidSource;
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return m_pidSource;
	}

	@Override
	/**Updates the values displayed for the encoders and returns the current value of the robot's distance forward*/
	public double pidGet() {
		SmartDashboard.putNumber("FL", Encoders.getDistance(Constants.ENCODER_FRONTLEFT));
		SmartDashboard.putNumber("FR", Encoders.getDistance(Constants.ENCODER_FRONTRIGHT));
		SmartDashboard.putNumber("RL", Encoders.getDistance(Constants.ENCODER_REARLEFT));
		SmartDashboard.putNumber("RR", Encoders.getDistance(Constants.ENCODER_REARRIGHT));
		SmartDashboard.putNumber("Avg. Distance Forward", Encoders.getAverageDistanceForward());
		SmartDashboard.putNumber("Avg. Distance Right", Encoders.getAverageDistanceRight());
		return Encoders.getAverageDistanceForward();
	}
	
	/**Updates the values displayed for the encoders and returns the current value of the robot's distance right.*/
	public double pidGetRight() {
		SmartDashboard.putNumber("FL", Encoders.getDistance(Constants.ENCODER_FRONTLEFT));
		SmartDashboard.putNumber("FR", Encoders.getDistance(Constants.ENCODER_FRONTRIGHT));
		SmartDashboard.putNumber("RL", Encoders.getDistance(Constants.ENCODER_REARLEFT));
		SmartDashboard.putNumber("RR", Encoders.getDistance(Constants.ENCODER_REARRIGHT));
		SmartDashboard.putNumber("Avg. Distance Forward", Encoders.getAverageDistanceForward());
		SmartDashboard.putNumber("Avg. Distance Right", Encoders.getAverageDistanceRight());
		return Encoders.getAverageDistanceRight();
	}

}

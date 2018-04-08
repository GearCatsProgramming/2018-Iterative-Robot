package org.usfirst.frc.team6500.robot.auto;

import org.usfirst.frc.team6500.robot.sensors.Encoders;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

/**
 * PIDSource for movement of drivetrain
 * 
 * @author Kyle
 *
 */
public class PIDInputSidewaysDrive implements PIDSource {
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
	public double pidGet() {
		//SmartDashboard.putNumber("FL", Encoders.getDistance(Constants.ENCODER_FRONTLEFT));
		//SmartDashboard.putNumber("FR", Encoders.getDistance(Constants.ENCODER_FRONTRIGHT));
		//SmartDashboard.putNumber("RL", Encoders.getDistance(Constants.ENCODER_REARLEFT));
		//SmartDashboard.putNumber("RR", Encoders.getDistance(Constants.ENCODER_REARRIGHT));
		//SmartDashboard.putNumber("Avg. Distance", Encoders.getAverageDistance());
		return Encoders.getAverageDistanceRight();
	}

}

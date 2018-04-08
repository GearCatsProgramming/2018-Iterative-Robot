package org.usfirst.frc.team6500.robot.auto;

import org.usfirst.frc.team6500.robot.sensors.Gyro;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * PIDSource for Gyro, use this insted of the generic Gyro class because this one has the modulo 360 to ignore accumulation
 * 
 * @author Kyle
 *
 */
public class PIDInputGyro implements PIDSource {
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
		double angle = Gyro.getAngle() % 360;
		SmartDashboard.putNumber("PIDInput", angle);
		return angle;
	}

}

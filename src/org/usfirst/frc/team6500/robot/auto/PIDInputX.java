package org.usfirst.frc.team6500.robot.auto;

import org.usfirst.frc.team6500.robot.systems.Encoders;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class PIDInputX implements PIDSource {
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
		return Encoders.getDistanceRelativeDirection();
	}

}

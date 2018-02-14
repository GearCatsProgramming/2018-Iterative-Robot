package org.usfirst.frc.team6500.robot.auto;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class PIDInputTest implements PIDSource {
	private PIDSourceType m_pidSource = PIDSourceType.kDisplacement;
	private int tracker = 0;
	
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
		if (tracker < 21)
		{
			tracker++;
		}
		else
		{
			tracker--;
			tracker--;
		}
		return tracker;
	}

}

package org.usfirst.frc.team6500.robot.systems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Vision {
	public static NetworkTableInstance tableServer;
	public static NetworkTable table;
	
	public static void initializeVision()
	{
		tableServer = NetworkTableInstance.getDefault();
		tableServer.startServer();
		
		table = tableServer.getTable("/vision");
	}
	
	public static int[] getContourCoordiantes()
	{
		NetworkTableEntry xEntry = table.getEntry("x");
		NetworkTableEntry yEntry = table.getEntry("y");
		
		return new int[] {(int) xEntry.getNumber(0), (int) yEntry.getNumber(0)};
	}
	
	public static int[] getContourSize()
	{
		NetworkTableEntry widthEntry = table.getEntry("width");
		NetworkTableEntry heightEntry = table.getEntry("height");
		
		return new int[] {(int) widthEntry.getNumber(0), (int) heightEntry.getNumber(0)};
	}
}

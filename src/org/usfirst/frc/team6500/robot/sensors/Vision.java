package org.usfirst.frc.team6500.robot.sensors;

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
		
		//System.out.println(tableServer.isValid());
		table = tableServer.getTable("/vision");
	}
	
	public static int getContourX()
	{
		NetworkTableEntry xEntry = table.getEntry("x");
		
		return xEntry.getNumber(0).intValue();
	}
	
	public static int getContourY()
	{
		NetworkTableEntry yEntry = table.getEntry("y");
		
		return yEntry.getNumber(0).intValue();
	}
	
	public static int getContourWidth()
	{
		NetworkTableEntry widthEntry = table.getEntry("width");
		
		return widthEntry.getNumber(0).intValue();
	}
	
	public static int getContourHeight()
	{
		NetworkTableEntry heightEntry = table.getEntry("height");
		
		return heightEntry.getNumber(0).intValue();
	}
}

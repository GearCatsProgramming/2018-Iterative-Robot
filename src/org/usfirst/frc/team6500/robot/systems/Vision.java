package org.usfirst.frc.team6500.robot.systems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * Interfaces with the vision co-processor (Raspberry Pi) using NetworkTables
 * 
 * @author Kyle
 *
 */
public class Vision {
	public static NetworkTableInstance tableServer;
	public static NetworkTable table;
	
	/**
	 * Run this before anything else, sets up the table for use
	 */
	public static void initializeVision()
	{
		tableServer = NetworkTableInstance.getDefault();
		tableServer.startServer();
		
		table = tableServer.getTable("/vision");
	}
	
	/**
	 * Gets the X position of the contour
	 */
	public static int getContourX()
	{
		NetworkTableEntry xEntry = table.getEntry("x");
		
		return xEntry.getNumber(0).intValue();
	}
	
	/**
	 * Gets the Y position of the contour
	 */
	public static int getContourY()
	{
		NetworkTableEntry yEntry = table.getEntry("y");
		
		return yEntry.getNumber(0).intValue();
	}
	
	/**
	 * Gets the width of the contour
	 */
	public static int getContourWidth()
	{
		NetworkTableEntry widthEntry = table.getEntry("width");
		
		return widthEntry.getNumber(0).intValue();
	}
	
	/**
	 * Gets the height of the contour
	 */
	public static int getContourHeight()
	{
		NetworkTableEntry heightEntry = table.getEntry("height");
		
		return heightEntry.getNumber(0).intValue();
	}
}

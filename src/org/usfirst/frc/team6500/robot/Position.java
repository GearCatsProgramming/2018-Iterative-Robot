package org.usfirst.frc.team6500.robot;

/**An enum listing where objects might be
 * @author Thomas Dearth
 * @since intricate stuff might be within our reach :O
 */
public enum Position {
	left, middle, right;

	/**Converts a {@link Position} to a boolean
	 * @param side The position requested
	 * @return true if left, false otherwise
	 */
	public static boolean toBoolean(Position side) {
		if(side == Position.left) { 		return true; }
		else if(side == Position.right) { 	return false; }
		else { 								return false; }
	}
}

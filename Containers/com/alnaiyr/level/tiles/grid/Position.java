/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.level.tiles.grid;

/**
 * internal uses
 * 
 * /!\ be careful not to mix up the Position and the Direction ! !
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
interface Position {

	/**
	 * 
	 * @return the next direction, from a directional point of view
	 */
	public QuadPositions getClockWise();

	/**
	 * 
	 * @return the previous direction, from a directional point of view
	 */
	public QuadPositions getAntiClockWise();

	/**
	 * 
	 * @return the next direction, considering my position relative to a quad
	 */
	public QuadPositions getRelativeClockWise();

	/**
	 * 
	 * @return the previous direction, considering my position relative to a
	 *         quad
	 */
	public QuadPositions getRelativeAntiClockWise();

	/**
	 * 
	 * @return the contrary direction
	 */
	public QuadPositions getReverse();
}

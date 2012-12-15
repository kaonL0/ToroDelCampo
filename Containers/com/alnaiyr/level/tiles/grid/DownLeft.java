/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.level.tiles.grid;

/**
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
final class DownLeft implements Position {

	/*---------------------
	 * 
	 * Variables
	 * 
	 *----------------------*/

	static DownLeft instance = new DownLeft();

	/*---------------------
	 * 
	 * Constructor
	 * 
	 *----------------------*/

	private DownLeft() {
	}

	@Override
	public QuadPositions getClockWise() {

		return QuadPositions.LEFT;
	}

	@Override
	public QuadPositions getAntiClockWise() {

		return QuadPositions.DOWN;
	}

	@Override
	public QuadPositions getRelativeClockWise() {

		return QuadPositions.UP;
	}

	@Override
	public QuadPositions getRelativeAntiClockWise() {

		return QuadPositions.RIGHT;
	}

	@Override
	public QuadPositions getReverse() {

		return QuadPositions.UPRIGHT;
	}

}

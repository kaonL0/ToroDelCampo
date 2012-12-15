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
final class DownRight implements Position {

	/*---------------------
	 * 
	 * Variables
	 * 
	 *----------------------*/

	static DownRight instance = new DownRight();

	/*---------------------
	 * 
	 * Constructor
	 * 
	 *----------------------*/

	private DownRight() {
	}

	@Override
	public QuadPositions getClockWise() {

		return QuadPositions.DOWN;
	}

	@Override
	public QuadPositions getAntiClockWise() {

		return QuadPositions.RIGHT;
	}

	@Override
	public QuadPositions getRelativeClockWise() {

		return QuadPositions.LEFT;
	}

	@Override
	public QuadPositions getRelativeAntiClockWise() {

		return QuadPositions.UP;
	}

	@Override
	public QuadPositions getReverse() {

		return QuadPositions.LEFTUP;
	}
}

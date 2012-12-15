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
final class UpRight implements Position {

	/*---------------------
	 * 
	 * Variables
	 * 
	 *----------------------*/

	static UpRight instance = new UpRight();

	/*---------------------
	 * 
	 * Constructor
	 * 
	 *----------------------*/

	private UpRight() {
	}

	@Override
	public QuadPositions getClockWise() {

		return QuadPositions.RIGHT;
	}

	@Override
	public QuadPositions getAntiClockWise() {

		return QuadPositions.UP;
	}

	@Override
	public QuadPositions getRelativeClockWise() {

		return QuadPositions.DOWN;
	}

	@Override
	public QuadPositions getRelativeAntiClockWise() {

		return QuadPositions.LEFT;
	}

	@Override
	public QuadPositions getReverse() {

		return QuadPositions.DOWNLEFT;
	}
}

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
final class Left implements Position {

	/*---------------------
	 * 
	 * Variables
	 * 
	 *----------------------*/

	static Left instance = new Left();

	/*---------------------
	 * 
	 * Constructor
	 * 
	 *----------------------*/

	private Left() {
	}

	@Override
	public QuadPositions getClockWise() {

		return QuadPositions.LEFTUP;
	}

	@Override
	public QuadPositions getAntiClockWise() {

		return QuadPositions.DOWNLEFT;
	}

	@Override
	public QuadPositions getRelativeClockWise() {

		return QuadPositions.UP;
	}

	@Override
	public QuadPositions getRelativeAntiClockWise() {

		return QuadPositions.DOWN;
	}

	@Override
	public QuadPositions getReverse() {

		return QuadPositions.RIGHT;
	}
}

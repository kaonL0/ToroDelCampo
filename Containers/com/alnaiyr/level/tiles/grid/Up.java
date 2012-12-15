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
final class Up implements Position {

	/*---------------------
	 * 
	 * Variables
	 * 
	 *----------------------*/

	static Up instance = new Up();

	/*---------------------
	 * 
	 * Constructor
	 * 
	 *----------------------*/

	private Up() {
	}

	@Override
	public QuadPositions getClockWise() {

		return QuadPositions.UPRIGHT;
	}

	@Override
	public QuadPositions getAntiClockWise() {

		return QuadPositions.LEFTUP;
	}

	@Override
	public QuadPositions getRelativeClockWise() {

		return QuadPositions.RIGHT;
	}

	@Override
	public QuadPositions getRelativeAntiClockWise() {

		return QuadPositions.LEFT;
	}

	@Override
	public QuadPositions getReverse() {

		return QuadPositions.DOWN;
	}
}

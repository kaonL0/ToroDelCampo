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
final class Right implements Position {

	/*---------------------
	 * 
	 * Variables
	 * 
	 *----------------------*/

	static Right instance = new Right();

	/*---------------------
	 * 
	 * Constructor
	 * 
	 *----------------------*/

	private Right() {
	}

	@Override
	public QuadPositions getClockWise() {

		return QuadPositions.DOWNRIGHT;
	}

	@Override
	public QuadPositions getAntiClockWise() {

		return QuadPositions.UPRIGHT;
	}

	@Override
	public QuadPositions getRelativeClockWise() {

		return QuadPositions.DOWN;
	}

	@Override
	public QuadPositions getRelativeAntiClockWise() {

		return QuadPositions.UP;
	}

	@Override
	public QuadPositions getReverse() {

		return QuadPositions.LEFT;
	}
}

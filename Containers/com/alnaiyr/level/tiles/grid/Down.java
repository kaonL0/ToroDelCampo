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
final class Down implements Position {

	/*---------------------
	 * 
	 * Variables
	 * 
	 *----------------------*/

	static Down instance = new Down();

	/*---------------------
	 * 
	 * Constructor
	 * 
	 *----------------------*/

	private Down() {
	}

	@Override
	public QuadPositions getClockWise() {

		return QuadPositions.DOWNLEFT;
	}

	@Override
	public QuadPositions getAntiClockWise() {

		return QuadPositions.DOWNRIGHT;
	}

	@Override
	public QuadPositions getRelativeClockWise() {

		return QuadPositions.LEFT;
	}

	@Override
	public QuadPositions getRelativeAntiClockWise() {

		return QuadPositions.RIGHT;
	}

	@Override
	public QuadPositions getReverse() {

		return QuadPositions.UP;
	}

}

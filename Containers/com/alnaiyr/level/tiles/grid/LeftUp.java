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
final class LeftUp implements Position {

	/*---------------------
	 * 
	 * Variables
	 * 
	 *----------------------*/

	static LeftUp instance = new LeftUp();

	/*---------------------
	 * 
	 * Constructor
	 * 
	 *----------------------*/

	private LeftUp() {
	}

	@Override
	public QuadPositions getClockWise() {

		return QuadPositions.UP;
	}

	@Override
	public QuadPositions getAntiClockWise() {

		return QuadPositions.LEFT;
	}

	@Override
	public QuadPositions getRelativeClockWise() {

		return QuadPositions.RIGHT;
	}

	@Override
	public QuadPositions getRelativeAntiClockWise() {

		return QuadPositions.DOWN;
	}

	@Override
	public QuadPositions getReverse() {

		return QuadPositions.DOWNRIGHT;
	}
}

/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.level.tiles.grid;

/**
 * Describes every possible position of quad zones around a quad.
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public enum QuadPositions implements Position {
	UP(Up.instance), UPRIGHT(UpRight.instance), RIGHT(Right.instance), DOWNRIGHT(
			DownRight.instance), DOWN(Down.instance), DOWNLEFT(
			DownLeft.instance), LEFT(Left.instance), LEFTUP(LeftUp.instance);

	private Position pos;

	private QuadPositions(Position pos) {
		this.pos = pos;
	}

	/**
	 * return next value, clockwise way
	 * 
	 */
	@Override
	public QuadPositions getClockWise() {
		return pos.getClockWise();
	}

	/**
	 * 
	 * @return previous value, meaning next in anti-clockwise
	 */
	@Override
	public QuadPositions getAntiClockWise() {
		return pos.getAntiClockWise();
	}

	@Override
	public QuadPositions getRelativeClockWise() {
		return pos.getRelativeClockWise();
	}

	@Override
	public QuadPositions getRelativeAntiClockWise() {
		return pos.getRelativeAntiClockWise();
	}

	@Override
	public QuadPositions getReverse() {

		return pos.getReverse();
	}

}

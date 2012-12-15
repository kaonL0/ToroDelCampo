/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.level.tiles.impl;

import com.alnaiyr.level.tiles.QuadZoned;
import com.alnaiyr.level.tiles.grid.QuadPositions;

/**
 * A quad is used to store data, and should be accessed using a diagonal based
 * search algorithm (ascending or descending) (although everything is possible,
 * this is the most effective way)
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class Quad<Type> implements QuadZoned<Type> {

	/*---------------------
	 * 
	 * Variables
	 * 
	 *----------------------*/

	public Type data;

	public int x = 0;
	public int y = 0;

	public QuadZoned<Type> upright = this;
	public QuadZoned<Type> upleft = this;
	public QuadZoned<Type> downleft = this;
	public QuadZoned<Type> downright = this;
	public QuadZoned<Type> right = this;
	public QuadZoned<Type> left = this;
	public QuadZoned<Type> up = this;
	public QuadZoned<Type> down = this;

	// note: exactly the same number of data as the one we would have for a quad
	// tree, minus the node pointer... TODO: yes, this is a feasible
	// optimization,
	// but for now let's concentrate on simplicity.
	// For now quads cost is 24 bytes per quad.

	/*---------------------
	 * 
	 * Constructor
	 * 
	 *----------------------*/

	/*---------------------
	 * 
	 * Methods
	 * 
	 *----------------------*/
	@Override
	public Type getData() {
		return data;
	}

	@Override
	public QuadZoned<Type> getNeighbor(QuadPositions pos) {

		switch (pos) {
			case LEFTUP:
				return upleft;
			case UP:
				return up;
			case UPRIGHT:
				return upright;
			case RIGHT:
				return right;
			case DOWNRIGHT:
				return downright;
			case DOWN:
				return down;
			case DOWNLEFT:
				return downleft;
			case LEFT:
				return left;
		}
		return this;
	}

	@Override
	public boolean hasNeighbor(QuadPositions pos) {

		return getNeighbor(pos) != this;
	}

	@Override
	public void putAndUpdateNeighbor(QuadPositions pos, QuadZoned<Type> neighbor) {
		// Possible enhancement by making all the references to the position
		// "static" instead of using a switch state.
		switch (pos) {
			case LEFTUP:
				upleft = neighbor;
				neighbor.setCoord(x - 1, y - 1);
				neighbor.putNeighbor(QuadPositions.DOWNRIGHT, this);
				// if there is a quad nearby...
				updateAntiClockWise(neighbor, QuadPositions.UP,
						QuadPositions.RIGHT);
				updateClockWise(neighbor, QuadPositions.LEFT,
						QuadPositions.DOWN);

				break;
			case UP:
				up = neighbor;
				neighbor.setCoord(x, y - 1);
				neighbor.putNeighbor(QuadPositions.DOWN, this);

				updateAntiClockWise(neighbor, QuadPositions.RIGHT,
						QuadPositions.DOWNRIGHT);
				updateClockWise(neighbor, QuadPositions.LEFT,
						QuadPositions.DOWNLEFT);

				break;
			case UPRIGHT:
				upright = neighbor;
				neighbor.setCoord(x + 1, y - 1);
				neighbor.putNeighbor(QuadPositions.DOWNLEFT, this);

				updateAntiClockWise(neighbor, QuadPositions.RIGHT,
						QuadPositions.DOWN);
				updateClockWise(neighbor, QuadPositions.UP, QuadPositions.LEFT);

				break;
			case RIGHT:
				right = neighbor;
				neighbor.setCoord(x + 1, y);
				neighbor.putNeighbor(QuadPositions.LEFT, this);
				//
				updateAntiClockWise(neighbor, QuadPositions.DOWN,
						QuadPositions.DOWNLEFT);
				updateClockWise(neighbor, QuadPositions.UP,
						QuadPositions.LEFTUP);
				break;
			case DOWNRIGHT:
				downright = neighbor;
				neighbor.setCoord(x + 1, y + 1);
				neighbor.putNeighbor(QuadPositions.LEFTUP, this);
				updateAntiClockWise(neighbor, QuadPositions.DOWN,
						QuadPositions.LEFT);
				updateClockWise(neighbor, QuadPositions.RIGHT, QuadPositions.UP);

				break;
			case DOWN:
				down = neighbor;
				neighbor.setCoord(x, y + 1);
				neighbor.putNeighbor(QuadPositions.UP, this);

				updateAntiClockWise(neighbor, QuadPositions.LEFT,
						QuadPositions.LEFTUP);
				updateClockWise(neighbor, QuadPositions.RIGHT,
						QuadPositions.UPRIGHT);
				break;
			case DOWNLEFT:
				downleft = neighbor;
				neighbor.putNeighbor(QuadPositions.UPRIGHT, this);
				neighbor.setCoord(x - 1, y + 1);

				updateAntiClockWise(neighbor, QuadPositions.LEFT,
						QuadPositions.UP);
				updateClockWise(neighbor, QuadPositions.DOWN,
						QuadPositions.RIGHT);

				break;
			case LEFT:
				left = neighbor;
				neighbor.putNeighbor(QuadPositions.RIGHT, this);
				neighbor.setCoord(x - 1, y);
				updateAntiClockWise(neighbor, QuadPositions.UP,
						QuadPositions.UPRIGHT);
				updateClockWise(neighbor, QuadPositions.DOWN,
						QuadPositions.DOWNRIGHT);

				break;
		}
	}

	private void updateClockWise(QuadZoned<Type> neighbor, QuadPositions start,
			QuadPositions direction) {

		if (hasNeighbor(start)) {

			QuadPositions tempPos = direction;

			QuadZoned<Type> temp = getNeighbor(start);
			QuadZoned<Type> startZone = temp;

			do {

				neighbor.putNeighbor(tempPos, temp);

				temp.putNeighbor(tempPos.getReverse(), neighbor);

				if (temp.hasNeighbor(tempPos.getRelativeClockWise())) {
					temp = temp.getNeighbor(tempPos.getRelativeClockWise());
					if (temp == startZone)
						break;
				} else
					break;

				tempPos = tempPos.getClockWise();
			} while (true);
		}
	}

	private void updateAntiClockWise(QuadZoned<Type> neighbor,
			QuadPositions start, QuadPositions direction) {

		if (hasNeighbor(start)) {

			QuadPositions tempPos = direction;

			QuadZoned<Type> temp = getNeighbor(start);
			QuadZoned<Type> startZone = temp;

			do {
				neighbor.putNeighbor(tempPos, temp);
				temp.putNeighbor(tempPos.getReverse(), neighbor);

				if (temp.hasNeighbor(tempPos.getRelativeAntiClockWise())) {
					temp = temp.getNeighbor(tempPos.getRelativeAntiClockWise());
					if (temp == startZone)
						break;
				} else
					break;

				tempPos = tempPos.getAntiClockWise();
			} while (true);
		}
	}

	@Override
	public void putNeighbor(QuadPositions pos, QuadZoned<Type> neighbor) {

		switch (pos) {
			case LEFTUP:
				upleft = neighbor;
				break;
			case UP:
				up = neighbor;
				break;
			case UPRIGHT:
				upright = neighbor;
				break;
			case RIGHT:
				right = neighbor;
				break;
			case DOWNRIGHT:
				downright = neighbor;
				break;
			case DOWN:
				down = neighbor;
				break;
			case DOWNLEFT:
				downleft = neighbor;
				break;
			case LEFT:
				left = neighbor;
				break;
		}
	}

	/*---------------------
	 * 
	 * Getters / Setters
	 * 
	 *----------------------*/

	@Override
	public String toString() {
		return x + ", " + y;
	}

	@Override
	public int x() {

		return x;
	}

	@Override
	public int y() {

		return y;
	}

	@Override
	public void setCoord(int x, int y) {
		this.x = x;
		this.y = y;
	}

}

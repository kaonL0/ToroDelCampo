/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.level.tiles.grid;

import org.jbox2d.common.Vec2;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.debug.Debug;
import com.alnaiyr.level.tiles.QuadZoned;

/**
 * a grid containing several quads. this grid is (should be) a square, limiting
 * the number of dimension to take care of to one.
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class QuadGridImpl<Type> implements QuadGrid<Type>, Debug {

	/*---------------------
	 * 
	 * Variables
	 * 
	 *----------------------*/

	public QuadZoned<Type> topLeft;

	/* width/height of every zone */
	public int width;
	public int height;

	/*---------------------
	 * 
	 * Constructor
	 * 
	 *----------------------*/

	public QuadGridImpl(QuadZoned<Type> first, int width, int height) {
		this.width = width;
		this.height = height;
		this.topLeft = first;
	}

	/*---------------------
	 * 
	 * Methods
	 * 
	 *----------------------*/

	private static int xIter = 0;
	private static int yIter = 0;

	@Override
	public QuadZoned<Type> getZone(final QuadZoned<Type> from,
			final PlanVector displacement) {

		if (displacement.x() > width)
			xIter = (int) (displacement.x() / width);
		else if (displacement.x() < 0)
			xIter = (int) (displacement.x() / width - 1);
		if (displacement.y() > height)
			yIter = (int) (displacement.y() / height);
		else if (displacement.y() < 0)
			yIter = (int) (displacement.y() / height - 1);

		return getPositived(from);
	}

	private QuadZoned<Type> getPositived(QuadZoned<Type> tempZone) {
		// TODO improve algorithm by taking into account diagonals
		// DB.test("start");
		// DB.test(tempZone);
		// DB.test("y");
		if (yIter >= 0) {
			// DB.test(yIter);
			while (yIter != 0) {
				tempZone = tempZone.getNeighbor(QuadPositions.DOWN);
				// DB.test(tempZone);
				yIter--;
			}
		} else {
			while (yIter != 0) {
				tempZone = tempZone.getNeighbor(QuadPositions.UP);
				yIter++;
			}
		}
		// DB.test("x");
		if (xIter >= 0) {
			while (xIter != 0) {
				tempZone = tempZone.getNeighbor(QuadPositions.RIGHT);
				// DB.test(tempZone);
				xIter--;
			}
		} else {
			while (xIter != 0) {

				tempZone = tempZone.getNeighbor(QuadPositions.LEFT);
				xIter++;
			}
		}

		// DB.test(tempZone);

		return tempZone;
	}

	@Override
	public QuadZoned<Type> getZone(PlanVector coord) {

		return getZone(topLeft, coord);
	}

	private static final Vec2 temp = new Vec2();

	@Override
	public PlanVector getZone(QuadZoned<Type> zone) {
		temp.set(zone.x() * width, zone.y() * height);

		return temp;
	}

	@Override
	public QuadZoned<Type> getTopLeftCorner() {
		return topLeft;
	}

	@Override
	public void debug(GameContainer container, Graphics g, boolean condition) {

		drawQuads(g, this, topLeft, getSize());
	}

	public static <V> void drawQuads(Graphics g, QuadGrid<V> grid,
			QuadZoned<V> from, int size) {

		g.pushTransform();

		QuadZoned<V> temp = from;
		QuadZoned<V> tempX = from;
		QuadZoned<V> tempY = from;

		g.translate(from.x() * grid.width(), from.y() * grid.height());

		PlanVector vec = new Vec2(0, 0);
		int i = 1;

		int j = 0;

		if (temp.hasNeighbor(QuadPositions.RIGHT)
				|| temp.hasNeighbor(QuadPositions.DOWN)
				|| temp.hasNeighbor(QuadPositions.DOWNRIGHT)) {

			do {

				g.drawRect(vec.x(), vec.y(), grid.width(), grid.height());

				drawLinks(grid, temp, vec.x(), vec.y(), g);

				if (j == size - 1)
					break;

				do {

					tempX = tempX.getNeighbor(QuadPositions.RIGHT);
					g.drawRect(vec.x() + i * grid.width(), vec.y(),
							grid.width(), grid.height());
					i++;
					drawLinks(grid, tempX, vec.x() + (i - 1) * grid.width(),
							vec.y(), g);
				} while (tempX.hasNeighbor(QuadPositions.RIGHT) && i < size - j);

				i = 1;
				do {
					tempY = tempY.getNeighbor(QuadPositions.DOWN);
					g.drawRect(vec.x(), vec.y() + i * grid.height(),
							grid.width(), grid.height());
					i++;
					drawLinks(grid, tempY, vec.x(),
							vec.y() + (i - 1) * grid.height(), g);
				} while (tempY.hasNeighbor(QuadPositions.DOWN) && i < size - j);
				i = 1;

				vec.addLocal(new Vec2(grid.width(), grid.height()));
				temp = temp.getNeighbor(QuadPositions.DOWNRIGHT);

				g.drawRect(vec.x(), vec.y(), grid.width(), grid.height());
				drawLinks(grid, temp, vec.x(), vec.y(), g);

				tempX = temp;
				tempY = temp;

				j++;

			} while (temp.hasNeighbor(QuadPositions.DOWNRIGHT) && size - j >= 0);
		} else {
			g.drawRect(vec.x(), vec.y(), grid.width(), grid.height());
		}

		g.popTransform();
	}

	public static <V> void drawLinks(QuadGrid<V> grid, QuadZoned<V> quad,
			float x, float y, Graphics g) {

		g.setColor(Color.red);
		if (quad.hasNeighbor(QuadPositions.DOWN)) {
			g.drawLine(x + grid.width() / 2, y + grid.height() / 2,
					x + grid.width() / 2, y + grid.height());
		}
		if (quad.hasNeighbor(QuadPositions.DOWNLEFT)) {
			g.drawLine(x + grid.width() / 2, y + grid.height() / 2, x,
					y + grid.height());
		}
		if (quad.hasNeighbor(QuadPositions.LEFT)) {
			g.drawLine(x + grid.width() / 2, y + grid.height() / 2, x,
					y + grid.height() / 2);
		}
		if (quad.hasNeighbor(QuadPositions.LEFTUP)) {
			g.drawLine(x + grid.width() / 2, y + grid.height() / 2, x, y);
		}
		if (quad.hasNeighbor(QuadPositions.UP)) {
			g.drawLine(x + grid.width() / 2, y + grid.height() / 2,
					x + grid.width() / 2, y);
		}
		if (quad.hasNeighbor(QuadPositions.UPRIGHT)) {
			g.drawLine(x + grid.width() / 2, y + grid.height() / 2,
					x + grid.width(), y);
		}
		if (quad.hasNeighbor(QuadPositions.RIGHT)) {
			g.drawLine(x + grid.width() / 2, y + grid.height() / 2,
					x + grid.width(), y + grid.height() / 2);
		}
		if (quad.hasNeighbor(QuadPositions.DOWNRIGHT)) {
			g.drawLine(x + grid.width() / 2, y + grid.height() / 2,
					x + grid.width(), y + grid.height());
		}
		g.setColor(Color.white);

	}

	/*---------------------
	 * 
	 * Getters / Setters
	 * 
	 *----------------------*/

	@Override
	public int getSize() {

		int size = 1;
		QuadZoned<Type> zone = topLeft;
		while (zone.hasNeighbor(QuadPositions.RIGHT)) {
			size++;
			zone = zone.getNeighbor(QuadPositions.RIGHT);
		}

		return size;
	}

	/**
	 * Give the width for ONE quad (non-Javadoc)
	 * 
	 * @see com.alnaiyr.display.renderables.Dimensionnable#width()
	 */
	@Override
	public int width() {

		return width;
	}

	/**
	 * Give the height for ONE quad (non-Javadoc)
	 * 
	 * @see com.alnaiyr.display.renderables.Dimensionnable#width()
	 */
	@Override
	public int height() {

		return height;
	}

}

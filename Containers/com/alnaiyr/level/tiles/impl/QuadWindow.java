/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.level.tiles.impl;

import org.jbox2d.common.Vec2;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.level.tiles.QuadWindowed;
import com.alnaiyr.level.tiles.QuadZoned;
import com.alnaiyr.level.tiles.grid.QuadGrid;
import com.alnaiyr.level.tiles.grid.QuadGridImpl;

/**
 * A squared window to look up quads.
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class QuadWindow<Type> implements QuadWindowed<Type> {

	/*---------------------
	 * 
	 * Variables
	 * 
	 *----------------------*/

	private final PlanVector coord = new Vec2();

	public QuadGrid<Type> grid;
	public QuadZoned<Type> corner;

	public int size;

	/*---------------------
	 * 
	 * Constructor
	 * 
	 *----------------------*/

	/**
	 * @param grid
	 * @param size
	 */
	public QuadWindow(QuadGrid<Type> grid, int size) {
		super();
		this.grid = grid;
		this.corner = grid.getTopLeftCorner();

		this.size = size;
	}

	/*---------------------
	 * 
	 * Methods
	 * 
	 *----------------------*/

	private static PlanVector temp = new Vec2();

	@Override
	public void bindTo(QuadZoned<Type> zone) {
		corner = zone;
		coord.set(grid.getZone(zone));
	}

	@Override
	public void moveTo(PlanVector vec) {

		temp.set(vec.x() - corner.x() * grid.width(), vec.y() - corner.y()
				* grid.height());

		coord.set(vec);
		corner = grid.getZone(corner, temp);
	}

	@Override
	public void add(PlanVector vec) {
		coord.addLocal(vec);

		temp.set(coord.x() - corner.x() * grid.width(), coord.y() - corner.y()
				* grid.height());

		corner = grid.getZone(corner, temp);
	}

	@Override
	public void debug(GameContainer container, Graphics g, boolean condition) {

		g.setColor(Color.yellow);
		g.setLineWidth(3);
		QuadGridImpl.drawQuads(g, grid, corner, size);
		g.setLineWidth(1);
		g.setColor(Color.green);
		g.drawRect(coord.x(), coord.y(), grid.width() * (size - 1),
				grid.height() * (size - 1));
		g.setColor(Color.white);

	}

	/*---------------------
	 * 
	 * Getters / Setters
	 * 
	 *----------------------*/

	@Override
	public int getQuadSize() {
		return size;
	}

	@Override
	public int width() {
		return size * grid.width();
	}

	@Override
	public int height() {
		return size * grid.height();
	}

	@Override
	public QuadZoned<Type> getCorner() {
		return corner;
	}

	@Override
	public PlanVector getPosition() {

		return coord;
	}

}

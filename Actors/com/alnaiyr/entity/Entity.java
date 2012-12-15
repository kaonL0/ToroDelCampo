package com.alnaiyr.entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.coordinates.container.CoordinateContainerImpl;
import com.alnaiyr.coordinates.dynamic.Origin;
import com.alnaiyr.display.debug.Debug;
import com.alnaiyr.display.renderables.Dimensionnable;

/**
 * An object that can be placed somewhere, and that has dimensions
 * 
 * Only intends to shorten the time to developp new classes, by allowing to to
 * recode all coordinates and dimension containers. It not intended to be used
 * in a OOP style thing, too general, inheritance abuse, all that stuff...
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public abstract class Entity implements Dimensionnable, Debug {

	public CoordinateContainerImpl coord;

	/*--------------------------
	 * 
	 * Constructors
	 * 
	 *--------------------------*/

	/**
	 * @param coord
	 * @param centered
	 * @param width
	 * @param height
	 */
	public Entity(PlanVector coord, boolean centered, float width, float height) {
		initCoord(coord, centered, width, height);
	}

	/**
	 * 
	 * @param coord
	 * @param width
	 * @param height
	 */
	public Entity(PlanVector coord, float width, float height) {
		initCoord(coord, false, width, height);
	}

	/**
	 * 
	 * @param coord
	 * @param centered
	 * @param dimension
	 */
	public Entity(PlanVector coord, boolean centered, Dimensionnable dimension) {
		initCoord(coord, centered, dimension.width(), dimension.height());
	}

	/**
	 * Initialize coordinates
	 * 
	 * @param coord
	 * @param centered
	 * @param width
	 * @param height
	 */
	public void initCoord(PlanVector coord, boolean centered, float width,
			float height) {
		if (!centered)
			this.coord = new CoordinateContainerImpl(coord);
		else
			this.coord = new CoordinateContainerImpl(coord, width / 2.0f,
					height / 2.0f);
		if (coord == null)
			this.coord = new CoordinateContainerImpl(new Vector2f(0, 0));
	}

	/* ************************
	 * 
	 * Getters / Setters
	 * 
	 * *************************
	 */

	/**
	 * 
	 * @return true if centered (not necessarily on center, but is at least
	 *         corrected with an offset)
	 */
	public boolean isCentered() {
		return !coord.getStep().equals(Origin.ref);
	}

	@Override
	public void debug(GameContainer container, Graphics g, boolean condition) {
		g.setLineWidth(4f);
		g.setColor(Color.red);
		g.drawRect(coord.x(), coord.y(), width(), height());
		g.setLineWidth(1f);
		g.setColor(Color.white);
	}

	public void setCentered(boolean centered) {
		if (!centered)
			coord.setStep(Origin.ref);
		else
			coord.setStep(new Vector2f(width() / 2.0f, height() / 2.0f));
	}

}

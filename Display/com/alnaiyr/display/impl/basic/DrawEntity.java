package com.alnaiyr.display.impl.basic;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.GraphicEntity;
import com.alnaiyr.display.renderables.DimensionDrawable;

/**
 * Creates a link between the renderable from slick and mine.
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class DrawEntity extends GraphicEntity {

	public DimensionDrawable	toRender;

	/* *************************
	 * 
	 * Constructor
	 * 
	 * *****************************
	 */

	/**
	 * @param coord
	 * @param toRender
	 */
	public DrawEntity(final PlanVector coord, final boolean centered,
			final DimensionDrawable toRender) {
		super(coord, centered, toRender.width(), toRender.height());
		this.toRender = toRender;
	}

	/**
	 * @param coord
	 * @param toRender
	 */
	public DrawEntity(final PlanVector coord, final DimensionDrawable toRender) {
		super(coord, true, toRender.width(), toRender.height());
		this.toRender = toRender;
	}

	/**
	 * Unsafe if a coordinate is not given during the initialization.
	 * 
	 * @param coord
	 * @param toRender
	 */
	public DrawEntity(final DimensionDrawable toRender) {
		super(null, true, toRender.width(), toRender.height());
		this.toRender = toRender;
	}

	/*----------------------------
	 * 
	 * Methods
	 * 
	 *---------------------------------*/

	@Override
	public void render(final Graphics g, final GameContainer container) {
		toRender.draw(coord);
	}

	/* *************************
	 * 
	 * Getters
	 * 
	 * *****************************
	 */

	/**
	 * gets the rendering object
	 * 
	 * @return the rendering object
	 */
	public DimensionDrawable getToRender() {
		return toRender;
	}

	@Override
	public int width() {
		return toRender.width();
	}

	@Override
	public int height() {
		return toRender.height();
	}

	/* *************************
	 * 
	 * Setters
	 * 
	 * *****************************
	 */

	/**
	 * Sets the rendering object
	 * 
	 * @param toRender
	 */
	public void setToRender(final DimensionDrawable toRender) {
		this.toRender = toRender;
	}

	@Override
	public void gUpdate(final int delta, final boolean condition) {
	}

}

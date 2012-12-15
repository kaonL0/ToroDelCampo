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
public class GroupDrawEntity extends GraphicEntity {

	public DimensionDrawable[] toRender;

	/* *************************
	 * 
	 * Constructor
	 * 
	 * *****************************
	 */

	/**
	 * Adds a group of rendering object, the dimensions being the one of the
	 * biggest rendering unit
	 * 
	 * @param coord
	 * @param toRender
	 */
	public GroupDrawEntity(PlanVector coord, DimensionDrawable... toRender) {
		super(coord, false, GroupDrawEntity.getBiggestWidth(toRender),
				GroupDrawEntity.getBiggestHeight(toRender));
		this.toRender = toRender;
	}

	private static float getBiggestWidth(DimensionDrawable... toRender) {
		float f = 0;
		for (DimensionDrawable dim : toRender) {
			if (dim.width() > f)
				f = dim.width();
		}
		return f;
	}

	private static float getBiggestHeight(DimensionDrawable... toRender) {
		float f = 0;
		for (DimensionDrawable dim : toRender) {
			if (dim.height() > f)
				f = dim.height();
		}
		return f;
	}

	/*----------------------------
	 * 
	 * Methods
	 * 
	 *---------------------------------*/

	@Override
	public void render(Graphics g, GameContainer container) {
		for (DimensionDrawable dim : toRender) {
			dim.draw(coord);
		}

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
	public DimensionDrawable[] getToRender() {
		return toRender;
	}

	@Override
	public int width() {
		return (int) GroupDrawEntity.getBiggestWidth(toRender);
	}

	@Override
	public int height() {
		return (int) GroupDrawEntity.getBiggestHeight(toRender);
	}

	@Override
	public void gUpdate(int delta, boolean condition) {
	}

}

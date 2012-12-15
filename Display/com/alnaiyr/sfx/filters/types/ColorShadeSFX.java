package com.alnaiyr.sfx.filters.types;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.alnaiyr.ai.updater.condition.Conditionable;
import com.alnaiyr.display.GraphicEntity;

/**
 * a wrapper storing colors for objects to wrap
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class ColorShadeSFX extends ColorSFX {

	/**
	 * @param color
	 */
	public ColorShadeSFX(Color color) {
		super(color);

	}

	/**
	 * @param condition
	 */
	private ColorShadeSFX(Conditionable condition) {
		super(condition);

	}

	/**
	 * 
	 * @param condition
	 */
	public ColorShadeSFX(boolean condition) {
		super(condition);
	}

	/* ****************
	 * 
	 * Methods
	 * 
	 * ***************
	 */

	@Override
	protected void preWrap(GraphicEntity gEntity, Graphics g,
			GameContainer container) {
		Color clo = g.getColor();
		g.setColor(color);
		gEntity.render(g, container);
		g.setColor(clo);
	}

	@Override
	protected void postWrap(GraphicEntity gEntity, Graphics g,
			GameContainer container) {
	}

	@Override
	public void reset(GraphicEntity gEntity) {
	}

	/* ****************
	 * 
	 * Getters / Setters
	 * 
	 * ***************
	 */

}

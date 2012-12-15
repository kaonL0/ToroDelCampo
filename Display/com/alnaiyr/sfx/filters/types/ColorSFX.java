package com.alnaiyr.sfx.filters.types;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.alnaiyr.ai.updater.condition.Conditionable;
import com.alnaiyr.display.GraphicEntity;

public class ColorSFX extends SFXImpl {

	/* ****************
	 * 
	 * Variables
	 * 
	 * ***************
	 */

	protected Color color = Color.white;

	/* ****************
	 * 
	 * Constructors
	 * 
	 * ***************
	 */

	public ColorSFX(Color color) {
		super();
		this.color = color;
	}

	/**
	 * @param condition
	 */
	public ColorSFX(boolean condition) {
		super(condition);

	}

	/**
	 * @param condition
	 */
	public ColorSFX(Conditionable condition) {
		super(condition);
	}

	/**
	 * @param condition
	 */
	public ColorSFX(Conditionable condition, Color color) {
		super(condition);
		this.color = color;
	}

	/* ****************
	 * 
	 * Methods
	 * 
	 * ***************
	 */

	@Override
	public void reset(GraphicEntity gEntity) {
	}

	@Override
	protected void preWrap(GraphicEntity gEntity, Graphics g,
			GameContainer container) {
		g.setColor(condition.getCondition() ? color : g.getColor());
	}

	@Override
	protected void postWrap(GraphicEntity gEntity, Graphics g,
			GameContainer container) {
		g.setColor(Color.white);
	}

	@Override
	public void update(GraphicEntity t, int delta, boolean condition) {
	}

	/* ****************
	 * 
	 * Getters / Setters
	 * 
	 * ***************
	 */

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}
}

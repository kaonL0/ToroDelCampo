package com.alnaiyr.sfx.filters.types.transition.impl;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.alnaiyr.ai.updater.condition.Conditionable;
import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.GraphicEntity;
import com.alnaiyr.sfx.filters.types.transition.TransitionSFX;
import com.alnaiyr.sfx.filters.types.transition.TransitionSFXConfig;

public class ScaleSFX extends TransitionSFX {

	/* ****************
	 * 
	 * Variables
	 * 
	 * ***************
	 */

	protected PlanVector coord;

	/* ****************
	 * 
	 * Constructors
	 * 
	 * ***************
	 */

	/**
	 * @param condition
	 * @param config
	 */
	public ScaleSFX(PlanVector coord, Conditionable condition,
			TransitionSFXConfig config) {
		super(condition, config);
		this.coord = coord;
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
		g.pushTransform();
		g.scale(profile.getValue(), profile.getValue());
		g.translate(coord.x() / profile.getValue() - coord.x(), coord.y()
				/ profile.getValue() - coord.y());

	}

	@Override
	protected void postWrap(GraphicEntity gEntity, Graphics g,
			GameContainer container) {
		g.popTransform();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.alnaiyr.sfx.wrappers.types.transitionnal.WrapTransitionBehavior#update
	 * (com.alnaiyr.sfx.wrappers.types.transitionnal.WrapTransitionProfile, int)
	 */
	@Override
	public void update(GraphicEntity t, int delta, boolean condition) {
		super.update(t, delta, condition);
		// DB.test(profile.getValue());
	}

	/* ****************
	 * 
	 * Getters / Setters
	 * 
	 * ***************
	 */

	@Override
	public void reset(GraphicEntity gEntity) {
	}

}

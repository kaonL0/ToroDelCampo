package com.alnaiyr.sfx.filters.types.transition.impl;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.alnaiyr.ai.updater.condition.Conditionable;
import com.alnaiyr.display.GraphicEntity;
import com.alnaiyr.sfx.filters.types.transition.TransitionSFX;
import com.alnaiyr.sfx.filters.types.transition.TransitionSFXConfigImpl;
import com.alnaiyr.sfx.filters.types.transition.config.FadeSFXConfig;

public class FadeSFX extends TransitionSFX {

	/* ****************
	 * 
	 * Variables
	 * 
	 * ***************
	 */

	private Color fade = new Color(Color.white);

	/* ****************
	 * 
	 * Constructors
	 * 
	 * ***************
	 */

	public FadeSFX(Conditionable condition, FadeSFXConfig config) {
		super(condition, config);
		this.fade = config.getFade();
	}

	public FadeSFX(Conditionable condition, TransitionSFXConfigImpl config) {
		super(condition, config);
	}

	/* ****************
	 * 
	 * Methods
	 * 
	 * ***************
	 */

	@Override
	public void reset(GraphicEntity gEntity) {
		profile.setValue(profile.getMinMax().reset(profile.getValue()));
	}

	@Override
	protected void preWrap(GraphicEntity gEntity, Graphics g,
			GameContainer container) {
		g.pushTransform();
		g.setColor(fade);
	}

	@Override
	protected void postWrap(GraphicEntity gEntity, Graphics g,
			GameContainer container) {
		g.popTransform();
		g.setColor(Color.white);
	}

	@Override
	public void update(GraphicEntity t, int delta, boolean condition) {
		super.update(t, delta, condition);
		fade.a = profile.getValue();
	}

	/* ****************
	 * 
	 * Getters / Setters
	 * 
	 * ***************
	 */

	/**
	 * @return the fade
	 */
	public Color getFade() {
		return fade;
	}

	/**
	 * @param fade
	 *            the fade to set
	 */
	public void setFade(Color fade) {
		this.fade = fade;
	}

}

package com.alnaiyr.level.layer;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.alnaiyr.ai.updater.Updatable;
import com.alnaiyr.display.GraphicEntity;
import com.alnaiyr.display.camera.Focus;
import com.alnaiyr.display.debug.Debug;
import com.alnaiyr.display.renderables.Renderable;
import com.alnaiyr.entity.Entity;
import com.alnaiyr.sfx.filters.SFXHandler;

/**
 * Contains some renderable to be put at the same level. NOTE: IT SHOULD ONLY BE
 * USED FOR RENDERING
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class Layer implements Updatable, Renderable, Debug, Deep {

	public Focus				focus;

	public float				z			= 0;

	/** The layer that is on the top of this one */
	public Layer				next;

	public List<GraphicEntity>	toRender	= new ArrayList<>(4);

	/*------------------------------
	 *
	 * Constructor
	 *
	 *-------------------------------*/

	/**
	 * Converts a list of renderable to an array
	 * 
	 * @param cam
	 * @param level
	 * @param toRender
	 */
	public Layer(final Focus cam, final float level) {
		focus = cam;
		z = level;
	}

	/**
	 * @param cam
	 * @param level
	 * @param pureRender
	 * @param pureUpdate
	 * @param renderUpdate
	 */
	public Layer(final Focus cam, final float level,
			final List<GraphicEntity> render) {
		super();
		focus = cam;
		z = level;

		toRender = render;
		for (final GraphicEntity ent : toRender) {
			ent.lay = this;
		}

	}

	/*------------------------------
	 *
	 * Methods
	 *
	 *-------------------------------*/

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.alnaiyr.gamestrategy.Renderable#render(org.newdawn.slick.Graphics)
	 */
	@Override
	public void render(final Graphics g, final GameContainer container) {
		focus.render(g, container);
		for (final GraphicEntity render : toRender)
			SFXHandler.render(render, g, container);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.alnaiyr.gamestrategy.Updatable#update(int, boolean)
	 */
	@Override
	public void update(final int delta, final boolean condition) {
		focus.update(delta, true);
		for (final GraphicEntity graphic : toRender) {
			graphic.gUpdate(delta, condition);
			SFXHandler.update(graphic, delta, condition);
		}

	}

	/**
	 * @return camera
	 */
	public Focus getCam() {
		return focus;
	}

	/**
	 * @param cam
	 */
	public void setCam(final Focus cam) {
		focus = cam;
	}

	@Override
	public void debug(final GameContainer container, final Graphics g,
			final boolean condition) {

		for (final Entity ent : toRender) {
			ent.debug(container, g, condition);
		}

	}

	@Override
	public float getDepth() {
		return z;
	}

	@Override
	public void setDepth(final float depth, final float totalDepth) {
		z = totalDepth - depth;
	}

}

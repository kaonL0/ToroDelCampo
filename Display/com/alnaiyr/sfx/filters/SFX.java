package com.alnaiyr.sfx.filters;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.alnaiyr.ai.updater.ObjectUpdater;
import com.alnaiyr.display.GraphicEntity;
import com.alnaiyr.sfx.filters.types.SFXImpl;

/**
 * Contains all informations that are supposed to be very common for a wrapper,
 * to eliminate any redundant code
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 * @param <Type>
 */
public interface SFX extends ObjectUpdater<GraphicEntity> {

	/**
	 * Start wrapping some graphics methods before what's to render
	 * 
	 * @param gEntity
	 * @param g
	 * @param container
	 * @param gEntity
	 *            to render
	 */
	public abstract void pushSFX(GraphicEntity gEntity, Graphics g,
			GameContainer container);

	/**
	 * Ends the wrapping: see
	 * {@link #pushSFX(SFXImpl, Graphics, GameContainer)}
	 * 
	 * @param gEntity
	 * @param g
	 * @param container
	 * @param profile
	 */
	public abstract void popSFX(GraphicEntity gEntity, Graphics g,
			GameContainer container);

	/**
	 * gets some parameters back to normal
	 * 
	 * @since 1.2
	 * @param gEntity
	 */
	public abstract void reset(GraphicEntity gEntity);

}

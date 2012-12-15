package com.alnaiyr.sfx.filters;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.alnaiyr.display.GraphicEntity;
import com.alnaiyr.sfx.filters.types.SFXImpl;

/**
 * Handles all the graphic wrap routine, taking care of conditions and stuff.
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public final class SFXHandler {

	/**
	 * @param toRender
	 * @param g
	 * @param container
	 */
	public static <Type extends SFXImpl> void render(GraphicEntity toRender,
			Graphics g, GameContainer container) {
		if (!toRender.getSFXEntries().isEmpty()) {
			for (SFX sfx : toRender.getSFXEntries()) {
				sfx.pushSFX(toRender, g, container);
			}
			toRender.render(g, container);
			for (SFX sfx : toRender.getSFXEntries()) {
				sfx.popSFX(toRender, g, container);
			}
		} else
			toRender.render(g, container);
	}

	public static void update(GraphicEntity t, int delta, boolean condition) {
		if (!t.getSFXEntries().isEmpty()) {
			for (SFX sfx : t.getSFXEntries()) {
				sfx.update(t, delta, condition);
			}
		}
	}

}

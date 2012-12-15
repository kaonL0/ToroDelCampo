package com.alnaiyr.display.renderables;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Allows to be rendered
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>Works</em></li>
 *          </ul>
 */
public interface Renderable {

	/**
	 * Renders things on the screen
	 * 
	 * @param g
	 * @param container
	 */
	public void render(Graphics g, GameContainer container);

}

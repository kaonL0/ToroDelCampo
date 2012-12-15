package com.alnaiyr.sfx.movement;

import org.newdawn.slick.Graphics;

/**
 * Describes what can translate the graphics, or follow anything
 * 
 * @author IgoЯ
 * @version 0.1
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>Working</em></li>
 *          </ul>
 * @param <Type>
 */
public interface Translater {

	/**
	 * Follows something
	 * 
	 * @param t
	 * @param g
	 */
	public void follow(Graphics g);

}

package com.alnaiyr.display.renderables;

import com.alnaiyr.coordinates.PlanVector;

/**
 * Equivalent to the Renderable from slick.
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          </ul>
 */
public interface Drawable {

	/**
	 * @param coord
	 */
	public void draw(PlanVector coord);

}

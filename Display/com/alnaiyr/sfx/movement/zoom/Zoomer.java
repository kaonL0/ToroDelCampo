package com.alnaiyr.sfx.movement.zoom;

import org.newdawn.slick.Graphics;

/**
 * Describes actions of something that can zoom in or out on something on a
 * graphic context
 * 
 * @author IgoЯ
 * @version 1.5
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>Works</em></li>
 *          <li><em>Added a graphics context</em></li>
 *          <li><em>Added a new method</em></li>
 *          <li><em>Added a new method</em></li>
 *          <li><em>Added a new method</em></li>
 *          <li><em>Removed speed method, now use is percent</em></li>
 *          </ul>
 * @param <Type>
 */
public interface Zoomer {

	/**
	 * Zooms in on something, at a certain speed (to be used in an update.<br/>
	 * Speed should depends on delta.
	 * 
	 * @param type
	 * @param speed
	 * @param g
	 *            the Graphics in use
	 */

	public void zoomIn(float speed, Graphics g);

	/**
	 * Zooms out on something, at a certain speed (to be used in an update
	 * 
	 * @param type
	 * @param speed
	 * @param g
	 *            the Graphics in use
	 */
	public void zoomOut(float speed, Graphics g);

	/**
	 * Goes back to initial zoom level
	 * 
	 * @param g
	 *            the Graphics in use
	 */

	public void unZoom(Graphics g);

	/**
	 * Gets the current zooming value
	 * 
	 * @return the zoom
	 */
	public float getZoom();

	/**
	 * @param zoom
	 */
	public void setZoom(float zoom);

}

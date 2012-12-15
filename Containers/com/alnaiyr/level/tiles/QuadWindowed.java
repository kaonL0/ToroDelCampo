/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.level.tiles;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.debug.Debug;
import com.alnaiyr.display.renderables.Dimensionnable;

/**
 * A window that can go through zones, and decide which on are concerned and
 * should be taken into account.
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public interface QuadWindowed<Type> extends Dimensionnable, Debug {

	/**
	 * gets the number of quads this window consider in width and height (it has
	 * to be a "square", even if the shape is not actually a square)
	 * 
	 * @return the size
	 */
	public int getQuadSize();

	/**
	 * Move that window so that its top left corner is this zone.
	 * 
	 * @param zone
	 */
	public void bindTo(QuadZoned<Type> zone);

	/**
	 * 
	 * @return the top left corner
	 */
	public QuadZoned<Type> getCorner();

	/**
	 * Attach the window to a news Coordinate, and makes sure that the zones are
	 * correctly following
	 * 
	 * @param vec
	 */
	public void moveTo(PlanVector vec);

	/**
	 * Adds this vector to the current coordinates, thus moving the entire
	 * window and changing the top left corner quad of the window.
	 * 
	 * @param vec
	 */
	public void add(PlanVector vec);

	public PlanVector getPosition();

}

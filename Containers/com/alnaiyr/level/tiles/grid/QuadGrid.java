/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.level.tiles.grid;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.renderables.Dimensionnable;
import com.alnaiyr.level.tiles.QuadZoned;

/**
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public interface QuadGrid<Type> extends Dimensionnable {

	/**
	 * Assuming that (0,0) is the top left corner of the grid, finds the zone
	 * corresponding to this specific coordinate, based on the same scale.
	 * 
	 * Complex and expensive method.
	 * 
	 * @param coord
	 * @return the corresponding zone
	 */
	public QuadZoned<Type> getZone(PlanVector coord);

	/**
	 * Assuming that the previous coordinate was on the top left corner of the
	 * specified zone, calculate which zone would be the one selected with a
	 * certain displacement.
	 * 
	 * @param from
	 * @param displacement
	 * @return
	 */
	public QuadZoned<Type> getZone(QuadZoned<Type> from, PlanVector displacement);

	/**
	 * Assuming that (0,0) is the top left corner of the grid, finds the
	 * coordinate corresponding to this specific zone, based on the same scale
	 * 
	 * @param zone
	 * @return coordinates
	 */
	public PlanVector getZone(QuadZoned<Type> zone);

	/**
	 * 
	 * @return the top left cornered zone
	 */
	public QuadZoned<Type> getTopLeftCorner();

	/**
	 * 
	 * @return width / height of the grid, in zone number
	 */
	public int getSize();

}

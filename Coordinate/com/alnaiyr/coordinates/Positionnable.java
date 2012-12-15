/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.coordinates;

import com.alnaiyr.display.renderables.Dimensionnable;

/**
 * Describes every entity that can be positioned in space, and that therefore
 * has dimensions
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public interface Positionnable extends Dimensionnable {

	public PlanVector getCoord();

}

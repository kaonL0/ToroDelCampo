/**
 * Created by Al Naiyr Studio , all right reserved. Créé par Al Naiyr, tous
 * droits réservés
 */
package com.alnaiyr.display.renderables;

import com.alnaiyr.coordinates.PlanVector;

/**
 * Can be scaled, and stepped
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public interface Scalable {

	/**
	 * 
	 * @param scale
	 */
	public void setScale(float scale);

	/**
	 * 
	 * @return
	 */
	public float getScale();

	public void setStep(PlanVector step);

	public PlanVector getCoord();

}

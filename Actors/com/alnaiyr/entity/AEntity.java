/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.entity;

import com.alnaiyr.ai.updater.Updatable;
import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.GraphicEntity;

/**
 * An advanced Entity, able to update itSelf and to Render itSelf
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public abstract class AEntity extends GraphicEntity implements Updatable {

	/*---------------------
	 * 
	 * Variables
	 * 
	 *----------------------*/

	/*---------------------
	 * 
	 * Constructor
	 * 
	 *----------------------*/

	public AEntity(PlanVector coord, boolean centered, float width, float height) {
		super(coord, centered, width, height);

	}

	/*---------------------
	 * 
	 * Methods
	 * 
	 *----------------------*/

}

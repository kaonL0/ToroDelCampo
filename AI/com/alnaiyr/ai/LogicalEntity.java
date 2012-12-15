/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.ai;

import com.alnaiyr.ai.updater.Updatable;
import com.alnaiyr.ai.updater.condition.Conditionable;
import com.alnaiyr.ai.updater.condition.TrueCondition;
import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.entity.Entity;

/**
 * A pure logical entity, that can be placed somewhere.
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public abstract class LogicalEntity extends Entity implements Updatable {

	/*---------------------
	 * 
	 * Variables
	 * 
	 *----------------------*/

	private Conditionable condition = TrueCondition.instance;

	/*---------------------
	 * 
	 * Constructor
	 * 
	 *----------------------*/

	public LogicalEntity(PlanVector coord, boolean centered, float width,
			float height) {
		super(coord, centered, width, height);

	}

	/*---------------------
	 * 
	 * Methods
	 * 
	 *----------------------*/

	@Override
	public int width() {

		return 0;
	}

	@Override
	public int height() {

		return 0;
	}

	@Override
	public void update(int delta, boolean condition) {
		if (this.condition.getCondition())
			updateOnCondition(delta, condition);
	}

	public abstract void updateOnCondition(int delta, boolean condition);
	/*---------------------
	 * 
	 * Getters / Setters
	 * 
	 *----------------------*/
}

/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.ai.updater.condition;

import com.alnaiyr.entity.Entity;
import com.alnaiyr.models.gamestrategy.NoEffect;

/**
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class OnViewCondition implements Conditionable {

	/*---------------------
	 * 
	 * Variables
	 * 
	 *----------------------*/

	private Entity ent;

	/**
	 * @param ent
	 */
	private OnViewCondition(Entity ent) {
		super();
		this.ent = ent;
	}

	@Override
	public boolean getCondition() {
		return true; // TODO
	}

	@NoEffect
	@Override
	public void setCondition(boolean condition) {
	}

}

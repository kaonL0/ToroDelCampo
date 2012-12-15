/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.uinterface.boxes.listeners;

import com.alnaiyr.ai.updater.condition.Conditionable;
import com.alnaiyr.models.gamestrategy.NoEffect;
import com.alnaiyr.uinterface.boxes.AbstractBox;

/**
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class BoxListener implements Conditionable {

	/*---------------------
	 * 
	 * Variables
	 * 
	 *----------------------*/

	protected AbstractBox box;
	private boolean mouseOver = false;
	private boolean selected = false;
	private boolean activated = false;

	/*---------------------
	 * 
	 * Constructor
	 * 
	 *----------------------*/

	/**
	 * 
	 * @param box
	 * @param boxListeners
	 */
	public BoxListener(AbstractBox box, BoxListeners... boxListeners) {
		this.box = box;
		for (BoxListeners list : boxListeners) {
			switch (list) {
				case ACTIVATED:
					activated = true;
					break;
				case MOUSEOVER:
					mouseOver = true;
					break;
				case SELECTED:
					selected = true;
			}
		}
	}

	/*---------------------
	 * 
	 * Methods
	 * 
	 *----------------------*/

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.alnaiyr.ai.updater.condition.Conditionable#getCondition()
	 */
	@Override
	public boolean getCondition() {
		return mouseOver ? box.isMouseOver() : false || selected ? box
				.getCondition() : false || activated ? box.isActivated()
				: false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.alnaiyr.ai.updater.condition.Conditionable#setCondition(boolean)
	 */
	@NoEffect
	@Override
	public void setCondition(boolean condition) {
	}

	/*---------------------
	 * 
	 * Getters / Setters
	 * 
	 *----------------------*/
}

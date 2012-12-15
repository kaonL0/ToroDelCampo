/**  an Al Naiyr production, all right reserved.
Une production Al naiyr, tous droits réservés*/
package com.alnaiyr.ai.updater.condition;

/**
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public interface AdvancedCondition extends Conditionable {

	/**
	 * add an boolean to the condition, using an "and" logic
	 * 
	 * @param toAdd
	 */
	public void and(boolean toAdd);

	/**
	 * add an boolean to the condition, using an "and" logic
	 * 
	 * @param toAdd
	 */
	public void or(boolean toAdd);

}

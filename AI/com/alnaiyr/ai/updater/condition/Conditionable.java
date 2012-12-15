package com.alnaiyr.ai.updater.condition;

/**
 * Is able to give a boolean out of a determined law
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public interface Conditionable {

	/**
	 * Gives a boolean, true or false, based on a calculation
	 * 
	 * @return true or false
	 */
	public boolean getCondition();

	/**
	 * @param condition
	 *            to set
	 */
	public void setCondition(boolean condition);

}

package com.alnaiyr.ai.updater;

/**
 * an interface describing everything able to update itself
 * 
 * @author IgoЯ
 * @version 1.1
 *          <p>
 *          <strong> Version change:</strong>
 *          <ul>
 *          <li><em> Functional</em></li>
 *          <li><em> Removed always: too complicated</em></li>
 *          </ul>
 *          </p>
 */

public interface Updatable {

	/**
	 * Updates the object, no matter the way, but using a delta parameter
	 * 
	 * @param delta
	 * @param condition
	 *            TODO
	 */
	public void update(int delta, boolean condition);

}

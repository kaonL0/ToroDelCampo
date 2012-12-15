package com.alnaiyr.ai.updater;

/**
 * This interface describes how to update coordinates by a movement
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>Functional</em></li>
 *          </ul>
 * @param <Type>
 */
public interface ObjectUpdater<Type> {

	/**
	 * Updates what needs to be updated
	 * 
	 * @param t
	 * @param delta
	 * @param condition
	 */
	public void update(Type t, int delta, boolean condition);

}

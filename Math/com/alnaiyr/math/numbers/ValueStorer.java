package com.alnaiyr.math.numbers;

import com.alnaiyr.ai.updater.Updatable;

/**
 * Stores a value to get it safely. The aim is to reproduce a safe Fylweight
 * pattern, that allows to minimize memory usage, and creates safe pointer to
 * objects
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 * @param <T>
 */
public interface ValueStorer<T> extends Updatable {

	/**
	 * Gets a value, ensuring that no special operation was made inside the
	 * method
	 * 
	 * @return value
	 */
	public T getValue();

	/**
	 * Sets value to a new one
	 * 
	 * @param value
	 */
	public void setValue(T value);

}

package com.alnaiyr.math.numbers.profiles.percents;

import com.alnaiyr.math.numbers.FloatStorer;

/**
 * Something able to give a percentage
 * 
 * @author IgoЯ
 * @version 1.2
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          <li><em>Added features</em></li>
 *          <li><em>Added features</em></li>
 *          </ul>
 */
public interface Percentable extends FloatStorer {

	/**
	 * 
	 * @return the current percentage
	 */
	public float getPercentage();

	/**
	 * 
	 * @param percent
	 */
	public void setPercentage(float percent);

	/**
	 * 
	 * @param toAdd
	 */
	public void add(float toAdd);

	/**
	 * Inverts the value (ex: 0.1 ==> 0.9)
	 * 
	 */
	public void invert();

	/**
	 * 
	 * @param value
	 *            TODO
	 * @return true if its percentage >=1
	 */
	public boolean isDone();

	/**
	 * 
	 * 
	 * @return true if percentage is 0 or 1
	 */
	public boolean isOnLimit();

	/**
	 * 
	 * @return true if >1 or <0
	 */
	public boolean isOutOfLimit();

	/**
	 * @param value
	 * @return true if <=0
	 * 
	 */
	public boolean isAtStart();

	/**
	 * Gets percentage back to 0
	 * 
	 * @param value
	 * @return
	 * 
	 */
	public void reset();
}

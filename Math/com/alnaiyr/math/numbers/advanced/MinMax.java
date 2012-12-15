/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.math.numbers.advanced;

/**
 * Contains a value and minimum and maximum values. Has to implement some
 * functions to check state of value.
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public interface MinMax extends LimitStorer {

	public float invert(float f);

	/**
	 * Corrects value, so that it stays between the boundaries.
	 * 
	 * @param f
	 * @return
	 */
	public float correctValue(float f);

	/**
	 * 
	 * @param value
	 * @return true if its percentage >=1
	 */
	public boolean isDone(float value);

	/**
	 * @param value
	 * @return true if percentage is 0 or 1
	 */
	public boolean isOnLimit(float value);

	/**
	 * @param value
	 * @return true if >1 or <0
	 */
	public boolean isOutOfLimit(float value);

	/**
	 * @param value
	 * 
	 * @return true if <=0
	 * 
	 */
	public boolean isAtStart(float value);

	/**
	 * Gets percentage back to 0
	 * 
	 * @param value
	 * 
	 * @return
	 * 
	 */
	public float reset(float value);

	public float cycle(float value);

	public float getLength();

	public float getStart();

	public float getEnd();

}

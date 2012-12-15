/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.math.numbers.advanced;

import com.alnaiyr.math.numbers.FloatU;
import com.alnaiyr.math.numbers.profiles.percents.PercentU;

/**
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class MinMaxImpl implements MinMax {

	/*---------------------
	 * 
	 * Variables
	 * 
	 *----------------------*/

	private float	min;
	private float	max;

	/*---------------------
	 * 
	 * Constructor
	 * 
	 *----------------------*/

	public MinMaxImpl(final float min, final float max) {
		super();
		this.min = min;
		this.max = max;
	}

	/*---------------------
	 * 
	 *	Methods
	 * 
	 *----------------------*/

	@Override
	public float invert(final float value) {
		return FloatU.invert(value, min, max);
	}

	@Override
	public boolean isDone(final float value) {
		return FloatU.isDone(value, max);
	}

	@Override
	public boolean isOnLimit(final float value) {
		return FloatU.isOnLimit(value, min, max);
	}

	@Override
	public boolean isOutOfLimit(final float value) {
		return FloatU.isOutOfLimit(value, min, max);
	}

	@Override
	public boolean isAtStart(final float value) {
		return FloatU.isAtStart(value, min);
	}

	@Override
	public float reset(final float value) {
		return min;
	}

	@Override
	public float correctValue(final float f) {
		return PercentU.correctPercent(min, max, f);
	}

	/*---------------------
	 * 
	 * Getters / Setters
	 * 
	 *----------------------*/

	@Override
	public void setStart(final float start) {
		min = start;
	}

	@Override
	public void setEnd(final float end) {
		max = end;
	}

	@Override
	public float cycle(final float value) {
		return PercentU.cyclePercent(min, max, value);
	}

	@Override
	public float getLength() {

		return (float) Math.hypot(max, min);
	}

	@Override
	public float getStart() {
		return min;
	}

	@Override
	public float getEnd() {
		return max;
	}

}

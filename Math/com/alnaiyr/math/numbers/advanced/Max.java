/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.math.numbers.advanced;

import com.alnaiyr.math.numbers.FloatU;
import com.alnaiyr.math.numbers.profiles.percents.PercentU;
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
public class Max implements MinMax {

	/*---------------------
	 * 
	 * Variables
	 * 
	 *----------------------*/

	private float	max;

	/*---------------------
	 * 
	 * Constructor
	 * 
	 *----------------------*/

	public Max(final float maxValue) {
		super();
		max = maxValue;
	}

	/*---------------------
	 * 
	 * Methods
	 * 
	 *----------------------*/

	@Override
	public float invert(final float value) {
		return FloatU.invert(value, 0, max);
	}

	@Override
	public boolean isDone(final float value) {
		return FloatU.isDone(value, max);
	}

	@Override
	public boolean isOnLimit(final float value) {
		return FloatU.isOnLimit(value, 0, max);
	}

	@Override
	public boolean isOutOfLimit(final float value) {
		return FloatU.isOutOfLimit(value, 0, max);
	}

	@Override
	public boolean isAtStart(final float value) {
		return FloatU.isAtStart(value, 0);
	}

	@Override
	public float reset(final float value) {
		return 0;
	}

	/*---------------------
	 * 
	 * Getters / Setters
	 * 
	 *----------------------*/

	@NoEffect
	@Override
	public void setStart(final float start) {
	}

	@Override
	public void setEnd(final float end) {
		max = end;
	}

	@Override
	public float correctValue(final float f) {
		return PercentU.correctPercent(0, max, f);
	}

	@Override
	public float cycle(final float value) {
		return PercentU.cyclePercent(0, max, value);
	}

	@Override
	public float getLength() {
		return max;
	}

	@Override
	public float getStart() {
		return 0;
	}

	@Override
	public float getEnd() {
		return max;
	}

}

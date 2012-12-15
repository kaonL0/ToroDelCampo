/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.math.numbers.advanced;

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
public class PercentMM implements MinMax {

	public static PercentMM	instance	= new PercentMM();

	private PercentMM() {
		// void
	}

	/*---------------------
	 * 
	 * Methods
	 * 
	 *----------------------*/

	@NoEffect
	@Override
	public void setStart(final float start) {
	}

	@NoEffect
	@Override
	public void setEnd(final float end) {
	}

	@Override
	public float invert(final float f) {
		return 1 - f;
	}

	@Override
	public boolean isDone(final float value) {

		return value >= 1;
	}

	@Override
	public boolean isOnLimit(final float value) {
		return value == 1 || value == 0;
	}

	@Override
	public boolean isOutOfLimit(final float value) {
		return value >= 1 || value <= 0;
	}

	@Override
	public boolean isAtStart(final float value) {
		return value <= 0;
	}

	@Override
	public float reset(final float value) {
		return 0;
	}

	@Override
	public float correctValue(final float f) {
		return PercentU.correctPercent(f);
	}

	@Override
	public float cycle(final float value) {
		return PercentU.cyclePercent(value);
	}

	@Override
	public float getLength() {
		return 1;
	}

	@Override
	public float getStart() {
		return 0;
	}

	@Override
	public float getEnd() {
		return 1;
	}

	/*---------------------
	 * 
	 * Getters / Setters
	 * 
	 *----------------------*/
}

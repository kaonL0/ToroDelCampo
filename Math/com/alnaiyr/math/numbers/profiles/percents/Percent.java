/**  an Al Naiyr production, all right reserved.
Une production Al naiyr, tous droits réservés*/
package com.alnaiyr.math.numbers.profiles.percents;

import com.alnaiyr.math.numbers.advanced.LimitStorer;
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
public class Percent implements Percentable, LimitStorer {

	private float value = 0;

	public Percent() {
		super();
	}

	public Percent(float percent) {
		this.value = percent;
	}

	@Override
	public float getValue() {
		return getPercentage();
	}

	@Override
	public void setValue(float value) {
		this.value = value;
	}

	@Override
	public void reset() {
		this.value = 0;
	}

	@Override
	public boolean isDone() {
		return this.value >= 1;
	}

	@Override
	public void invert() {
		this.value = 1 - this.value;
	}

	@Override
	public boolean isOnLimit() {
		return isDone() || value <= 0;
	}

	@Override
	public String toString() {
		return String.valueOf(this.value);
	}

	@Override
	public int hashCode() {
		return (int) (this.value * 10);
	}

	@Override
	public boolean isOutOfLimit() {
		return value > 1 || value < 0;
	}

	@Override
	public boolean isAtStart() {
		return value == 0;
	}

	@Override
	public void update(int delta, boolean condition) {
	}

	@NoEffect
	@Override
	public void setStart(float start) {
	}

	@NoEffect
	@Override
	public void setEnd(float end) {
	}

	@Override
	public float getPercentage() {
		this.value = PercentU.correctPercent(this.value);
		return this.value;
	}

	@Override
	public void setPercentage(float percent) {
		this.value = percent;
	}

	@Override
	public void add(float toAdd) {
		this.value += toAdd;
	}

}

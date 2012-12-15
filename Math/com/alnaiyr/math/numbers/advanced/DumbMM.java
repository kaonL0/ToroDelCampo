/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.math.numbers.advanced;

import com.alnaiyr.math.numbers.FloatStorer;

/**
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class DumbMM implements FloatStorer, MinMax {

	public static DumbMM	instance	= new DumbMM();

	private DumbMM() {
		super();
	}

	@Override
	public void setStart(final float start) {
	}

	@Override
	public void setEnd(final float end) {
	}

	@Override
	public float invert(final float f) {
		return f;
	}

	@Override
	public float correctValue(final float f) {
		return f;
	}

	@Override
	public boolean isDone(final float value) {
		return true;
	}

	@Override
	public boolean isOnLimit(final float value) {
		return true;
	}

	@Override
	public boolean isOutOfLimit(final float value) {
		return true;
	}

	@Override
	public boolean isAtStart(final float value) {
		return true;
	}

	@Override
	public float reset(final float value) {
		return value;
	}

	@Override
	public float getValue() {
		return 0;
	}

	@Override
	public void setValue(final float value) {
	}

	@Override
	public void update(final int delta, final boolean condition) {
	}

	@Override
	public float cycle(final float value) {
		return value;
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
		return 0;
	}

}

/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.physics.speed;

import com.alnaiyr.general.IV;
import com.alnaiyr.math.numbers.FloatStorer;
import com.alnaiyr.math.numbers.OnOffStorer;
import com.alnaiyr.math.numbers.advanced.DumbMM;
import com.alnaiyr.math.numbers.advanced.Max;
import com.alnaiyr.math.numbers.advanced.MinMaxImpl;
import com.alnaiyr.math.numbers.profiles.Profile;
import com.alnaiyr.math.numbers.profiles.behaves.limit.LimitBehaves;

/**
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class Speed extends Profile implements Speedable {

	public Speed() {
		super();
		setLimit(LimitBehaves.BLOCKON);
	}

	/**
	 * 
	 * @param initSpeed
	 * @param value
	 *            minimum speed and maximum
	 */
	public Speed(final float initSpeed, final float... value) {
		this();

		setValue(initSpeed);

		switch (value.length) {
			case 1:
				setMinMax(new Max(value[0]));
				break;
			case 2:
				setMinMax(new MinMaxImpl(value[0], value[1]));
				break;
			default:
				setMinMax(DumbMM.instance);
				break;
		}

	}

	@Override
	public void update(final int delta, final boolean condition) {
		super.update(delta, condition);
	}

	/**
	 * Sets acceleration, when it is specific (like an acceleration that as it
	 * self an acceleration, etc...). Sets the acceleration as a speed.
	 * 
	 * @param floats
	 */
	public void setAcceleration(final float value, final float... floats) {
		setDerivative(new Speed(value, floats));
	}

	/**
	 * Sets an acceleration as a simple acceleration and deceleration
	 * 
	 * @param up
	 * @param down
	 */
	public void setAcceleration(final float up, final float down) {
		setDerivative(new OnOffStorer(up, down));
	}

	public void setAcceleration(final FloatStorer acell) {
		setDerivative(acell);
	}

	@Override
	public float getSpeed() {
		return super.getValue() * IV.delta;
	}

	@Override
	public void setSpeed(final float speed) {
		setValue(speed);
	}

	@Override
	public float getValue() {
		return getSpeed();
	}

	@Override
	public float getNonDeltaSpeed() {
		return getValue();
	}

}

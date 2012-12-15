/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.sfx.filters.types.transition;

import com.alnaiyr.general.IV;
import com.alnaiyr.math.numbers.advanced.DumbMM;
import com.alnaiyr.math.numbers.advanced.MinMax;
import com.alnaiyr.math.numbers.profiles.behaves.limit.LimitBehaves;
import com.alnaiyr.math.numbers.profiles.behaves.release.ReleaseBehaves;

/**
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class TransitionSFXConfigImpl implements TransitionSFXConfig {

	/*---------------------
	 * 
	 * Variables
	 * 
	 *----------------------*/

	public float speed = 0;

	public MinMax minMax = DumbMM.instance;
	public LimitBehaves limit;
	public ReleaseBehaves release;

	/*---------------------
	 * 
	 * Constructor
	 * 
	 *----------------------*/

	/**
	 * @param speed
	 * @param limit
	 * @param release
	 */
	public TransitionSFXConfigImpl(float speed, LimitBehaves limit,
			ReleaseBehaves release) {
		super();
		this.speed = speed;
		this.limit = limit;
		this.release = release;
	}

	/**
	 * @param speed
	 */
	public TransitionSFXConfigImpl(float speed) {
		super();
		this.speed = speed;
		limit = LimitBehaves.BLOCKON;
		release = ReleaseBehaves.NULL;
	}

	/*---------------------
	 * 
	 * Methods
	 * 
	 *----------------------*/

	@Override
	public void update(int delta, boolean condition) {
	}

	@Override
	public void invert() {
		speed = -speed;
	}

	/*---------------------
	 * 
	 * Getters / Setters
	 * 
	 *----------------------*/

	@Override
	public float getValue() {
		return getSpeed();
	}

	@Override
	public void setValue(float value) {
		this.speed = value;
	}

	@Override
	public float getSpeed() {
		return speed * IV.delta;
	}

	@Override
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	@Override
	public float getNonDeltaSpeed() {
		return speed;
	}

	@Override
	public LimitBehaves getLimit() {
		return limit;
	}

	@Override
	public ReleaseBehaves getRelease() {
		return release;
	}

	@Override
	public MinMax getMinMax() {
		return minMax;
	}

	public void setMinMax(MinMax minMax) {
		this.minMax = minMax;
	}
}

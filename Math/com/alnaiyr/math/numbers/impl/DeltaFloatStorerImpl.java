/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.math.numbers.impl;

import com.alnaiyr.general.IV;
import com.alnaiyr.math.numbers.DeltaFloatStorer;

/**
 * Stores a values, and always return it multiplied by delta
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class DeltaFloatStorerImpl implements DeltaFloatStorer {

	private float value;

	public DeltaFloatStorerImpl(double i) {
		this.value = (float) i;
	}

	@Override
	public float getValue() {
		return value * IV.delta;
	}

	@Override
	public void setValue(float value) {
		this.value = value;
	}

	@Override
	public void update(int delta, boolean condition) {
	}

}

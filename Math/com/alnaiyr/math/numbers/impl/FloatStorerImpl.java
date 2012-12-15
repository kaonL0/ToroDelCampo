/**  an Al Naiyr production, all right reserved.
Une production Al naiyr, tous droits réservés*/
package com.alnaiyr.math.numbers.impl;

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
public class FloatStorerImpl implements FloatStorer {

	private float value;

	public FloatStorerImpl(double i) {
		this.value = (float) i;
	}

	@Override
	public float getValue() {
		return value;
	}

	@Override
	public void setValue(float value) {
		this.value = value;
	}

	@Override
	public void update(int delta, boolean condition) {
	}
}

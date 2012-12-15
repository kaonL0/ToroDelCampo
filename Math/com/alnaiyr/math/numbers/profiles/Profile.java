package com.alnaiyr.math.numbers.profiles;

import com.alnaiyr.math.numbers.FloatStorer;
import com.alnaiyr.math.numbers.NullStorer;
import com.alnaiyr.math.numbers.advanced.DumbMM;
import com.alnaiyr.math.numbers.advanced.MinMax;
import com.alnaiyr.math.numbers.profiles.behaves.LimitBehave;
import com.alnaiyr.math.numbers.profiles.behaves.ReleaseBehave;
import com.alnaiyr.math.numbers.profiles.behaves.limit.LimitBehaves;
import com.alnaiyr.math.numbers.profiles.behaves.release.ReleaseBehaves;

/**
 * Updates a value following a profile of evolution (interpolation and so on),
 * depends on the evolution of an external percentage
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class Profile implements Profilable {

	protected float value;

	private boolean inverted = false;
	private float mult = 1;

	protected MinMax minMax = DumbMM.instance;

	protected FloatStorer derivative = NullStorer.instance;

	protected LimitBehave limit = LimitBehaves.BLOCKON.limit;
	protected ReleaseBehave release = ReleaseBehaves.NULL.release;

	protected boolean updateDerivative = true;

	public Profile() {
		super();
	}

	/*------------------------
	 * 
	 * 		Methods
	 * 
	 *-----------------------------*/

	@Override
	public void update(int delta, boolean condition) {

		if (updateDerivative)
			derivative.update(delta, condition);

		if (condition)
			addDerivative();

		limit.checkLimits(this, condition, delta);
		release.checkRelease(this, condition, delta);

		setValue(minMax.correctValue(getValue()));

	}

	@Override
	public void addDerivative() {
		setValue(getValue() + getMultiplier() * derivative.getValue());
	}

	/*------------------------
	 * 
	 * 		Getters / Setters	
	 * 
	 *-----------------------------*/

	@Override
	public MinMax getWindow() {
		return minMax;
	}

	@Override
	public float getDerivative() {
		return derivative.getValue();
	}

	public void setDerivative(FloatStorer derivative) {
		this.derivative = derivative;
	}

	@Override
	public LimitBehave getLimit() {
		return limit;
	}

	public void setLimit(LimitBehaves limit) {
		this.limit = limit.limit;
	}

	@Override
	public ReleaseBehave getRelease() {
		return release;
	}

	public void setRelease(ReleaseBehaves release) {
		this.release = release.release;
	}

	@Override
	public void setMinMax(MinMax mm) {
		this.minMax = mm;
	}

	@Override
	public boolean isInverted() {
		return inverted;
	}

	@Override
	public void setInverted(boolean inverted) {
		this.inverted = inverted;
	}

	@Override
	public float getMultiplier() {
		return mult;
	}

	@Override
	public void setMultiplier(float multi) {
		this.mult = multi;
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
	public void invert() {
		mult = -mult;
	}

	@Override
	public MinMax getMinMax() {
		return minMax;
	}

}

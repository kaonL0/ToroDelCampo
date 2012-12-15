package com.alnaiyr.math.numbers.profiles;

import com.alnaiyr.math.numbers.FloatStorer;
import com.alnaiyr.math.numbers.Invertible;
import com.alnaiyr.math.numbers.advanced.MinMax;
import com.alnaiyr.math.numbers.profiles.behaves.LimitBehave;
import com.alnaiyr.math.numbers.profiles.behaves.ReleaseBehave;

/**
 * Describes something that can navigate upon a profile, meaning a max and a min
 * value
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 * 
 */
public interface Profilable extends FloatStorer, Invertible {

	public boolean isInverted();

	public void setInverted(boolean inverted);

	/**
	 * 
	 * @return the speed multiplier
	 */
	public float getMultiplier();

	public void setMultiplier(float multi);

	/**
	 * Adds the derivative value to current value
	 * 
	 * @param profile
	 * 
	 */
	public void addDerivative();

	public float getDerivative();

	public MinMax getMinMax();

	public void setMinMax(MinMax mm);

	/**
	 * 
	 * @return the min and max values of this profiler
	 */
	public MinMax getWindow();

	/**
	 * 
	 * @return all behaves
	 */
	public LimitBehave getLimit();

	public ReleaseBehave getRelease();

}

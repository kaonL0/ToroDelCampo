/**  an Al Naiyr production, all right reserved.
Une production Al naiyr, tous droits réservés*/
package com.alnaiyr.math.numbers.profiles.behaves.limit;

import com.alnaiyr.math.numbers.profiles.Profilable;
import com.alnaiyr.math.numbers.profiles.behaves.LimitBehave;

/**
 * if max value is overtook, make it back to minimum value (useful for
 * percentages)
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class LimitLoopFront implements LimitBehave {

	protected LimitLoopFront() {
		super();

	}

	@Override
	public void checkLimits(Profilable prof, boolean condition, int delta) {

		if (prof.isInverted() && condition) {
			prof.setInverted(false);
			prof.invert();

		}

		prof.setValue(prof.getMinMax().cycle(
				prof.getValue() + prof.getDerivative() * prof.getMultiplier())
				- prof.getDerivative() * prof.getMultiplier());
	}

}

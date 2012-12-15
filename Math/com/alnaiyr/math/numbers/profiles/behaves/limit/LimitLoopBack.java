/**  an Al Naiyr production, all right reserved.
Une production Al naiyr, tous droits réservés*/
package com.alnaiyr.math.numbers.profiles.behaves.limit;

import com.alnaiyr.math.numbers.profiles.Profilable;
import com.alnaiyr.math.numbers.profiles.behaves.LimitBehave;

/**
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class LimitLoopBack implements LimitBehave {

	protected LimitLoopBack() {
		super();
	}

	@Override
	public void checkLimits(Profilable prof, boolean condition, int delta) {
		if (prof.getWindow().isOutOfLimit(prof.getValue())) {
			prof.setInverted(!prof.isInverted());
			prof.invert();
			if (condition)
				prof.addDerivative();
		}

	}
}

/**  an Al Naiyr production, all right reserved.
Une production Al naiyr, tous droits réservés*/
package com.alnaiyr.math.numbers.profiles.behaves.release;

import com.alnaiyr.math.numbers.profiles.Profilable;
import com.alnaiyr.math.numbers.profiles.behaves.ReleaseBehave;
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
public class ReleaseToStart implements ReleaseBehave {

	protected ReleaseToStart() {
		super();

	}

	@Override
	public void checkRelease(Profilable prof, boolean condition, int delta) {

		if (condition) {
			if (prof.isInverted()) {
				if (prof.getLimit() == LimitBehaves.NULL.limit) {
					prof.setInverted(false);
					prof.invert();
				}
			}
		} else if (!prof.isInverted()) {
			prof.setInverted(true);
			prof.invert();
		} else if (!prof.getWindow().isOutOfLimit(prof.getValue())) {
			prof.addDerivative();
		}
	}
}

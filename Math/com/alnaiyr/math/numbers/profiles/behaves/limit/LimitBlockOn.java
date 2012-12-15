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
public class LimitBlockOn implements LimitBehave {

	protected LimitBlockOn() {
		super();

	}

	/**
	 * If value is out of limits, value takes the value of the ordered limit.
	 * This means that end>=value>=start
	 */
	@Override
	public void checkLimits(Profilable prof, boolean condition, int delta) {
		if (condition) {
			if (prof.isInverted()) {
				prof.setInverted(false);
				prof.invert();
			}
		}
	}
}

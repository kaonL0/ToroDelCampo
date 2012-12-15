/**  an Al Naiyr production, all right reserved.
Une production Al naiyr, tous droits réservés*/
package com.alnaiyr.math.numbers.profiles.behaves.limit;

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
public enum LimitBehaves {
	NULL(new LimitDumb()), BLOCKON(new LimitBlockOn()), LOOPFRONT(
			new LimitLoopFront()), LOOPBACK(new LimitLoopBack());

	public LimitBehave limit;

	private LimitBehaves(LimitBehave limit) {
		this.limit = limit;
	}

}

/**  an Al Naiyr production, all right reserved.
Une production Al naiyr, tous droits réservés*/
package com.alnaiyr.math.numbers.profiles.behaves.release;

import com.alnaiyr.math.numbers.profiles.behaves.ReleaseBehave;

/**
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public enum ReleaseBehaves {
	NULL(new ReleaseDumb()), TOSTART(new ReleaseToStart());

	public ReleaseBehave release;

	private ReleaseBehaves(ReleaseBehave limit) {
		this.release = limit;
	}
}

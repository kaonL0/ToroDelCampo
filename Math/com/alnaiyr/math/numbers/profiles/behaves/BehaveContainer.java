/**  an Al Naiyr production, all right reserved.
Une production Al naiyr, tous droits réservés*/
package com.alnaiyr.math.numbers.profiles.behaves;

import com.alnaiyr.math.numbers.profiles.behaves.limit.LimitBehaves;
import com.alnaiyr.math.numbers.profiles.behaves.release.ReleaseBehaves;

/**
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public interface BehaveContainer {

	public LimitBehaves getLimit();

	public ReleaseBehaves getRelease();

}

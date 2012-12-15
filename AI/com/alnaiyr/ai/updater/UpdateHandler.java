package com.alnaiyr.ai.updater;

/**
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class UpdateHandler {

	/**
	 * @param toUpdate
	 * @param delta
	 */
	public static void update(UpdateInCondition toUpdate, int delta) {
		if (toUpdate.getCondition() != null) {
			toUpdate.update(delta, toUpdate.getCondition().getCondition());
		} else
			toUpdate.update(delta, true);
	}

}

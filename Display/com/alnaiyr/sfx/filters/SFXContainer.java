package com.alnaiyr.sfx.filters;

import com.alnaiyr.ai.updater.UpdateInCondition;
import com.alnaiyr.display.renderables.Renderable;

/**
 * Is able to accept a wrapper to be put around, and to give a a condition on
 * which to be wrapped
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public interface SFXContainer extends Renderable, UpdateInCondition {

	/**
	 * Sets a wrapper around
	 * 
	 * @param wrapper
	 */
	public void addSFX(SFX sfx);

	/**
	 * Gets the wrapper around, if exists
	 * 
	 * @return the wrapper
	 */
	public SFX[] getSFXs();

}

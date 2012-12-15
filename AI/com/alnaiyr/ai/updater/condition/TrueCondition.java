package com.alnaiyr.ai.updater.condition;

import com.alnaiyr.models.gamestrategy.NoEffect;

/**
 * Permanently stores a "true" value
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class TrueCondition implements AdvancedCondition {

	public static TrueCondition	instance	= new TrueCondition();

	/**
	 * 
	 */
	private TrueCondition() {
		super();

	}

	@Override
	public boolean getCondition() {
		return true;
	}

	/**
	 * No effect
	 * 
	 */
	@NoEffect
	@Override
	public void setCondition(boolean condition) {
	}

	@NoEffect
	@Override
	public void and(boolean toAdd) {
	}

	@NoEffect
	@Override
	public void or(boolean toAdd) {
	}

}

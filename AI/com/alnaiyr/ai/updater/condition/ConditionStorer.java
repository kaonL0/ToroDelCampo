package com.alnaiyr.ai.updater.condition;

/**
 * Contains a boolean and some access, useful to control in / out and limit
 * access to dynamic tables
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class ConditionStorer implements AdvancedCondition {

	private boolean value = false;

	/**
     */
	public ConditionStorer() {
	}

	/**
	 * @param value
	 */
	public ConditionStorer(boolean value) {
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.alnaiyr.tools.math.Conditionable#getCondition()
	 */
	@Override
	public boolean getCondition() {
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.alnaiyr.tools.math.Conditionable#setCondition(boolean)
	 */
	@Override
	public void setCondition(boolean condition) {
		this.value = condition;
	}

	@Override
	public void and(boolean toAdd) {
		setCondition(value && toAdd);
	}

	@Override
	public void or(boolean toAdd) {
		setCondition(value || toAdd);
	}

}

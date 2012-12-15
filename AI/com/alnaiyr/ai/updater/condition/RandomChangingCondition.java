package com.alnaiyr.ai.updater.condition;

/**
 * change its state randomly. Contrary to a {@link RandomLuckCondition}, changes
 * its value only when luck is obtained. It means that if it it true, it will
 * stay true until luck is found again, and vice versa
 * 
 * @author IgoR from Alnaiyr
 * 
 */
public class RandomChangingCondition implements Conditionable {

	private final RandomLuckCondition	cond;
	private boolean						current;

	public RandomChangingCondition(final boolean startValue, final int luck,
			final int population) {
		cond = new RandomLuckCondition(luck, population);
		current = startValue;
	}

	@Override
	public boolean getCondition() {
		if (cond.getCondition())
			current = !current;

		return current;
	}

	@Override
	public void setCondition(final boolean condition) {
		current = condition;

	}

}

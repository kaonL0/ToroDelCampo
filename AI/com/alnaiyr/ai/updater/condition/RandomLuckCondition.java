package com.alnaiyr.ai.updater.condition;

import com.alnaiyr.math.MathU;
import com.alnaiyr.models.gamestrategy.NoEffect;

/**
 * Gets true with a percent of chance
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class RandomLuckCondition implements AdvancedCondition {

	private int luck;
	private int population;

	/**
	 * @param luck
	 * @param population
	 */
	public RandomLuckCondition(int luck, int population) {
		super();
		this.luck = luck;
		this.population = population;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.alnaiyr.updater.condition.ConditionGiver#getConditionValue()
	 */
	@Override
	public boolean getCondition() {
		return MathU.random(luck, population) <= luck;
	}

	/* ******************************
	 * 
	 * Getters & Setters
	 * 
	 * ************************************
	 */
	/**
	 * @return lucky part of population
	 */
	public int getLuck() {
		return luck;
	}

	/**
	 * @return entire population
	 */
	public int getPopulation() {
		return population;
	}

	/**
	 * @param luck
	 */
	public void setLuck(int luck) {
		this.luck = luck;
	}

	/**
	 * @param population
	 */
	public void setPopulation(int population) {
		this.population = population;
	}

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

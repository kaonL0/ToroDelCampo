/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.math.numbers.advanced;

import com.alnaiyr.ai.updater.condition.Conditionable;
import com.alnaiyr.ai.updater.condition.RandomLuckCondition;
import com.alnaiyr.math.MathU;
import com.alnaiyr.math.numbers.FloatStorer;

/**
 * Changes its value only if a condition is true, this being randomly choosen
 * between to values.
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class RandomAccessFloatStorer implements FloatStorer, LimitStorer {

	/*---------------------
	 * 
	 * Variables
	 * 
	 *----------------------*/

	private float value;
	private float min;
	private float max;
	private Conditionable condition;

	/*---------------------
	 * 
	 * Constructor
	 * 
	 *----------------------*/

	/**
	 * @param value
	 * @param min
	 * @param max
	 * @param condition
	 */
	public RandomAccessFloatStorer(float value, float min, float max,
			Conditionable condition) {
		super();
		this.value = value;
		this.min = min;
		this.max = max;
		this.condition = condition;
	}

	/**
	 * Completely random chance, based on a population
	 * 
	 * @param value
	 * @param min
	 * @param max
	 */
	public RandomAccessFloatStorer(float value, float min, float max,
			int chance, int outOf) {
		super();
		this.value = value;
		this.min = min;
		this.max = max;
		this.condition = new RandomLuckCondition(chance, outOf);
	}

	/*---------------------
	 * 
	 * Methods
	 * 
	 *----------------------*/
	@Override
	public void update(int delta, boolean condition) {
	}

	@Override
	public void setStart(float start) {
		this.min = start;
	}

	@Override
	public void setEnd(float end) {
		this.max = end;
	}

	@Override
	public float getValue() {
		if (condition.getCondition())
			value = MathU.random(min, max);

		return value;
	}

	@Override
	public void setValue(float value) {
		this.value = value;
	}
	/*---------------------
	 * 
	 * Getters / Setters
	 * 
	 *----------------------*/
}

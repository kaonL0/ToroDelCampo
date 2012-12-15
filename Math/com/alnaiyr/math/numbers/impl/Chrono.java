/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.math.numbers.impl;

import com.alnaiyr.math.numbers.advanced.Max;
import com.alnaiyr.math.numbers.profiles.Profile;

/**
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class Chrono extends Profile {

	/*---------------------
	 * 
	 * Constructor
	 * 
	 *----------------------*/

	public Chrono(int start) {
		this.value = start;
		this.minMax = new Max(start);
		this.derivative = new DeltaFloatStorerImpl(-0.001);
	}

	/*---------------------
	 * 
	 * Methods
	 * 
	 *----------------------*/

	/*---------------------
	 * 
	 * Getters / Setters
	 * 
	 *----------------------*/

	public int getSecond() {
		return (int) value;
	}

	public boolean isDone() {
		return value == 0;
	}
}

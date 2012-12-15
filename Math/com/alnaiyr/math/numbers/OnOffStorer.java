/**  an Al Naiyr production, all right reserved.
Une production Al naiyr, tous droits réservés*/
package com.alnaiyr.math.numbers;

/**
 * Has a value when down, and an other one when up
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class OnOffStorer implements FloatStorer {

	/*---------------------
	 * 
	 * Variables
	 * 
	 *----------------------*/

	private float on;

	private float off;

	private boolean up = true;

	/*---------------------
	 * 
	 * Constructor
	 * 
	 *----------------------*/

	public OnOffStorer(float on, float off) {
		super();
		this.on = on;
		this.off = off;
	}

	/*---------------------
	 * 
	 * Methods
	 * 
	 *----------------------*/

	@Override
	public void update(int delta, boolean condition) {
		up = condition;
	}

	/*---------------------
	 * 
	 * Getters / Setters
	 * 
	 *----------------------*/

	@Override
	public float getValue() {
		return up ? on : off;
	}

	@Override
	public void setValue(float value) {
		if (up)
			on = value;
		else
			off = value;
	}

}

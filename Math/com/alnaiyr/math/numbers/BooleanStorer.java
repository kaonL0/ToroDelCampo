/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.math.numbers;

/**
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class BooleanStorer implements ValueStorer<Boolean> {

	private boolean bool;

	public BooleanStorer(boolean bool) {
		this.bool = bool;
	}

	public BooleanStorer() {
		this.bool = false;
	}

	@Override
	public void update(int delta, boolean condition) {
		// void
	}

	@Override
	public Boolean getValue() {

		return bool;
	}

	@Override
	public void setValue(Boolean value) {
		this.bool = value.booleanValue();
	}

	@Override
	public String toString() {
		return Boolean.toString(bool);
	}

}

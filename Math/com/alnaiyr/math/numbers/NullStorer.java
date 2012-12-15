/**  an Al Naiyr production, all right reserved.
Une production Al naiyr, tous droits réservés*/
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
public class NullStorer implements FloatStorer {

	public static NullStorer instance = new NullStorer();

	private NullStorer() {
		super();
	}

	@Override
	public float getValue() {
		return 0;
	}

	@Override
	public void setValue(float value) {
	}

	@Override
	public void update(int delta, boolean condition) {
	}

}

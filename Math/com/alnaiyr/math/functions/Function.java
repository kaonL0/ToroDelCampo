package com.alnaiyr.math.functions;

/**
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class Function {

	/**
	 * @param function
	 * @param params
	 * @return the corresponding value
	 */
	public static float getValue(Functions function, Float... params) {

		switch (function) {

			case SINUS:
				return SinusFunction.getInstance().getValue(params);
			case LINEAR:
				return LinearFunction.getInstance().getValue(params);
			case CONST:
				break;
		}
		return 0;
	}

}

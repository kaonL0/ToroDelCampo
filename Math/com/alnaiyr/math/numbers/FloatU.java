/**  an Al Naiyr production, all right reserved.
Une production Al naiyr, tous droits réservés*/
package com.alnaiyr.math.numbers;


/**
 * Contains utilities for floats
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class FloatU {

	public static float invert(float value, float min, float max) {
		return max - value + min;
	}

	public static boolean isDone(float value, float max) {
		return value == max;
	}

	public static boolean isOnLimit(float value, float min, float max) {
		return value == min || value == max;
	}

	public static boolean isOutOfLimit(float value, float min, float max) {
		return value > max || value < min;
	}

	public static boolean isAtStart(float value, float min) {
		return value == min;
	}

	public float reset(float value, float min) {
		return min;
	}

}

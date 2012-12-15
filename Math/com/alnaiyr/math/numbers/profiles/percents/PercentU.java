package com.alnaiyr.math.numbers.profiles.percents;

/**
 * Utilities for percentages
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class PercentU {

	/**
	 * Helps percentages to have a cyclic value (back and forward in this case)
	 * 
	 * @param percent
	 * @param speed
	 * @return corrected percent
	 */
	public static float cyclePercent(float percent, float speed) {
		if (percent > 1 || percent < 0)
			speed = -speed;
		return speed;
	}

	/**
	 * @param percent
	 */
	public static void cyclePercent(Percentable percent) {
		percent.setValue(PercentU.cyclePercent(percent.getValue()));
	}

	/**
	 * Helps percentages to have a cyclic value
	 * 
	 * @param percent
	 * @return corrected percent
	 */
	public static float cyclePercent(float percent) {
		if (percent > 1)
			percent -= 1;
		else if (percent < 0)
			percent += 1;
		return percent;
	}

	/**
	 * @see #cyclePercent(float)
	 * 
	 * @param min
	 * @param max
	 * @param relativePerc
	 * @return
	 */
	public static float cyclePercent(float min, float max, float relativePerc) {

		if (relativePerc > max)
			relativePerc -= max;
		else if (relativePerc < min)
			relativePerc += max;
		return relativePerc;
	}

	/**
	 * @see #cyclePercent(float)
	 * 
	 * @param min
	 * @param max
	 * @param relativePerc
	 * @return
	 */
	public static int cyclePercent(int min, int max, int relativePerc) {

		if (relativePerc > max)
			relativePerc = min;
		else if (relativePerc < min)
			relativePerc = max;
		return relativePerc;
	}

	/**
	 * @see #correctPercent(float)
	 * 
	 * @param min
	 * @param max
	 * @param relativePerc
	 * @return
	 */
	public static float correctPercent(float min, float max, float relativePerc) {
		if (relativePerc > max)
			relativePerc = max;
		else if (relativePerc < min)
			relativePerc = min;
		return relativePerc;
	}

	/**
	 * Helps percentage to gets to a correct value (between 0 and 1)
	 * 
	 * @param percent
	 * @return rounded value
	 */
	public static float correctPercent(float percent) {
		if (percent > 1)
			percent = 1;
		else if (percent < 0)
			percent = 0;
		return percent;
	}

}

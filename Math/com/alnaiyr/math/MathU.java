package com.alnaiyr.math;

import org.newdawn.slick.geom.Vector2f;

import com.alnaiyr.coordinates.PlanVector;

/**
 * Extension to Math class
 * 
 * @author IgoЯ
 * @version 1.1
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>Works</em></li>
 *          <li><em>Added easy normal vector calculation</em></li>
 *          <li><em>Added easy iterator</em></li>
 *          </ul>
 */
public class MathU {

	/* ********************** Numbers ************************* */

	/**
	 * Rounds a number regarding a specific numeric base.
	 * 
	 * @param toRound
	 * @param step
	 * @return rounded number
	 */
	// for int
	public static int roundTo(int toRound, int step) {

		return Math.round((float) toRound / step) * step;
	}

	/**
	 * {@link #roundTo(int, int)}
	 * 
	 * @param toRound
	 * @param step
	 * @return rounded value
	 */
	// for int given float
	public static int roundTo(float toRound, int step) {

		return Math.round(toRound / step) * step;
	}

	/**
	 * @param toRound
	 * @param step
	 * @return rounded
	 */
	// for int given float
	public static PlanVector roundTo(Vector2f toRound, int step) {

		return new Vector2f(MathU.roundTo(toRound.x, step), MathU.roundTo(
				toRound.y, step));
	}

	// same for int[]
	/**
	 * @param toRound
	 * @param step
	 * @return rounded
	 */
	public static int[] roundTo(int[] toRound, int step) {

		return new int[] { MathU.roundTo(toRound[0], step),
				MathU.roundTo(toRound[1], step) };
	}

	/**
	 * Returns true if the first value to be reached is first, false otherwise
	 * 
	 * @param value
	 * @param first
	 * @param second
	 * @return rounded
	 */
	public static boolean findCycleNext(byte value, byte first, byte second) {
		return first >= value && (first < second || second <= value)
				|| first <= value && second > first && second <= value;
	}

	/**
	 * 
	 * @param from
	 * @param to
	 * @return random number
	 */
	public static int random(int from, int to) {
		return (int) Math.rint(Math.round(Math.random() * (to - from) + from));
	}

	public static float random(float from, float to) {
		return (float) (Math.random() * (to - from) + from);
	}

	/**
	 * Gives factorial of the number (a!)
	 * 
	 * @param a
	 * @return a!
	 */
	public static int factorial(int a) {
		if (a > 1) {
			for (int i = a - 1; i > 0; i--)
				a *= i;
			return a;
		}
		return 1;
	}

	/**
	 * binomial coefficient indexed by n and k (n k)
	 * 
	 * @param k
	 * @param n
	 * @return binomial coefficient
	 */
	public static float binomialCoef(int k, int n) {
		return (float) MathU.factorial(n)
				/ (float) (MathU.factorial(k) * MathU.factorial(n - k));

	}

	/**
	 * @param i
	 * @param m
	 * @param u
	 * @return Bernstein polynomial value
	 */
	public static float bernsteinPolynom(int i, int m, float u) {
		return (float) (MathU.binomialCoef(i, m) * Math.pow(u, i) * Math.pow(
				1 - u, m - i));
	}

}

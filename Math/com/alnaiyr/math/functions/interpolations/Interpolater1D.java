package com.alnaiyr.math.functions.interpolations;

import org.newdawn.slick.util.FastTrig;
import org.newdawn.slick.util.Log;

import com.alnaiyr.math.MathU;
import com.alnaiyr.math.numbers.profiles.percents.Percentable;

/**
 * This class can be used for interpolate point between values
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>Works</em></li>
 *          </ul>
 */
public final class Interpolater1D {

	/**
	 * Gives the interpolated value between two, given the type of interpolation
	 * wanted
	 * 
	 * @param inter
	 * @param from
	 * @param to
	 * @param percent
	 * @param parameters
	 *            args depending on interpolation type
	 * @return the interpolation when interpolation exist, 0 if not enough
	 *         arguments or null selected
	 */
	public static float interpolate(Interpolation inter, float from, float to,
			float percent, float... parameters) {
		try {
			switch (inter) {
				case CONSTANT:
					return from;
				case LINEAR:
					return Interpolater1D.linearInt(from, to, percent);
				case COSINUS:
					return Interpolater1D.cosinusInt(from, to, percent);
				case CUBIC:
					return Interpolater1D.cubicInt(parameters[0], from, to,
							parameters[1], percent);
				case BEZIER:
					return Interpolater1D.bezierInt(parameters);
				case PERLIN:
					// TODO
					break;
			}
		} catch (IndexOutOfBoundsException e) {
			Log.error("not enough arguments to do a " + inter
					+ " interpolation! @ "
					+ e.getStackTrace()[1].getClassName() + "."
					+ e.getStackTrace()[1].getMethodName());

		}
		return 0;
	}

	/**
	 * @param inter
	 * @param a
	 * @param b
	 * @param percent
	 * @param parameters
	 *            specific parameters to some interpolations
	 * @return interpolated value
	 */
	public static float interpolate(Interpolation inter, float a, float b,
			Percentable percent, float... parameters) {
		return Interpolater1D.interpolate(inter, a, b, percent.getPercentage());
	}

	/**
	 * return the cosinus interpolate value of x
	 * 
	 * @param a
	 * @param b
	 * @param x
	 *            the percentage (0 is a, 1 is b)
	 * @return the interpolated value of x
	 */
	private static float cosinusInt(float a, float b, float x) {
		float k = (float) ((1 - FastTrig.cos(x * Math.PI)) / 2);
		return Interpolater1D.linearInt(a, b, k);
	}

	/**
	 * return the cubic interpolate value of x
	 * 
	 * @param y0
	 *            point before a
	 * @param y1
	 *            point a
	 * @param y2
	 *            point b
	 * @param y3
	 *            point after b
	 * @param x
	 *            percent the percentage (0 is a, 1 is b)
	 * @return the interpolated value of x
	 */
	private static float cubicInt(float y0, float y1, float y2, float y3,
			float x) {
		float a = y3 - y2 - y0 + y1;
		float b = y0 - y1 - a;
		float c = y2 - y0;
		float d = y1;

		return a * x * x * x + b * x * x + c * x + d;
	}

	private static float bezierInt(float... floats) {

		float b = 0;
		float d;

		for (int i = 1; i < floats.length; i++) {
			d = MathU.bernsteinPolynom(i, floats.length - 1, floats[0]);

			b += d * floats[i];
		}
		return b;
	}

	/**
	 * return the linear interpolate value of x
	 * 
	 * @param a
	 * @param b
	 * @param x
	 *            the percentage (0 is a, 1 is b)
	 * @return the interpolated value of x
	 */
	private static float linearInt(float a, float b, float x) {
		return a * (1 - x) + b * x;
	}

}

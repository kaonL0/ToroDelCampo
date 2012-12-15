package com.alnaiyr.math.functions.interpolations;

import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.Log;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.math.MathU;
import com.alnaiyr.math.numbers.profiles.percents.Percentable;

public class Interpolater2D {

	/* ****************
	 * 
	 * Methods
	 * 
	 * ***************
	 */

	/**
	 * In space
	 * 
	 * @param inter
	 * @param from
	 * @param to
	 * @param percent
	 * @param params
	 * @return interpolated coordinates
	 */
	public static PlanVector interpolate(Interpolation inter, PlanVector from,
			PlanVector to, Percentable percent, PlanVector... params) {
		return new Vector2f(Interpolater1D.interpolate(inter, from.x(),
				to.x(), percent.getPercentage()),
				Interpolater1D.interpolate(inter, from.y(), to.y(),
						percent.getPercentage()));

	}

	/**
	 * Gives the interpolated value between two, given the type of interpolation
	 * wanted TODO
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
	public static PlanVector interpolate(Interpolation inter, PlanVector from,
			PlanVector to, float percent, PlanVector... parameters) {
		try {
			switch (inter) {
				case CONSTANT:
					return parameters[0];
					/*
					 * case LINEAR: return Interpolater1D.linearInt(from, to,
					 * percent); case COSINUS: return
					 * Interpolater1D.cosinusInt(from, to, percent); case CUBIC:
					 * return Interpolater1D.cubicInt(parameters[0], from, to,
					 * parameters[1], percent); case BEZIER: return
					 * Interpolater1D.bezierInt(parameters);
					 */
				case BEZIER:
					break;
				case COSINUS:
					break;
				case CUBIC:
					break;
				case LINEAR:
					break;
				case PERLIN:
					break;
			}
		} catch (IndexOutOfBoundsException e) {
			Log.error("not enough arguments to do the selected interpolation!");
		}
		return null;
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
	/*
	 * public static float interpolate(Interpolation inter, Coordinable a,
	 * Coordinable b, Percentable percent, float... parameters) { return
	 * Interpolater1D.interpolate(inter, a, b, percent.getPercentage()); }
	 */

	/* ****************
	 * 
	 * Getters / Setters
	 * 
	 * ***************
	 */

	/**
	 * return the cosinus interpolate value of x
	 * 
	 * @param a
	 * @param b
	 * @param x
	 *            the percentage (0 is a, 1 is b)
	 * @return the interpolated value of x
	 */
	/*
	 * private static float cosinusInt(Coordinable a, Coordinable b, float x) {
	 * float k = (float) ((1 - FastTrig.cos(x * Math.PI)) / 2); return
	 * Interpolater1D.linearInt(a, b, k); }
	 */

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
	@SuppressWarnings("unused")
	private static float cubicInt(float y0, float y1, float y2, float y3,
			float x) {
		float a = y3 - y2 - y0 + y1;
		float b = y0 - y1 - a;
		float c = y2 - y0;
		float d = y1;

		return a * x * x * x + b * x * x + c * x + d;
	}

	@SuppressWarnings("unused")
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
	@SuppressWarnings("unused")
	private static float linearInt(float a, float b, float x) {
		return a * (1 - x) + b * x;
	}

}

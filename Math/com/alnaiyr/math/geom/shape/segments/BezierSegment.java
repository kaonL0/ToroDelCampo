package com.alnaiyr.math.geom.shape.segments;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.coordinates.utilities.VecU;
import com.alnaiyr.math.MathU;
import com.alnaiyr.math.numbers.profiles.percents.Percentable;

/**
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class BezierSegment implements AdvancedSegment {

	/* ****************
	 * 
	 * Variables
	 * 
	 * ***************
	 */

	private PlanVector[] points;
	private float length;

	/* ****************
	 * 
	 * Constructors
	 * 
	 * ***************
	 */

	/**
	 * @param coords
	 */
	public BezierSegment(PlanVector... coords) {
		this.points = coords;
		calculateLength();
	}

	/* ****************
	 * 
	 * Methods
	 * 
	 * ***************
	 */

	@Override
	public PlanVector bindCoordinate(float currentPercent, float distanceToAdd,
			PlanVector coord) {
		float b = 0;
		float c = 0;
		float d;

		float percent = currentPercent + getEquivalentPercentage(distanceToAdd);

		for (int i = 0; i < points.length; i++) {
			d = MathU.bernsteinPolynom(i, points.length - 1, percent);

			b += d * points[i].x();
			c += d * points[i].y();
		}

		try {
			coord.set(b, c);
		} catch (Exception e) {
			return new Vector2f(b, c);
		}
		return null;
	}

	/**
	 * Very expensive method, be warned!
	 * 
	 */
	@Override
	public float getPercentage(PlanVector coord) {
		LinearGroupSegment seg = new LinearGroupSegment(convertInSegments(100));
		return seg.getPercentage(coord);
	}

	@Override
	public PlanVector bindCoordinate(Percentable percent, PlanVector coord) {

		float b = 0;
		float c = 0;
		float d;

		for (int i = 0; i < points.length; i++) {
			d = MathU.bernsteinPolynom(i, points.length - 1,
					percent.getPercentage());

			b += d * points[i].x();
			c += d * points[i].y();
		}

		try {
			coord.set(b, c);
		} catch (Exception e) {
			return new Vector2f(b, c);
		}
		return null;
	}

	@Override
	public PlanVector bindCoordinate(float percent, PlanVector coord) {
		float b = 0;
		float c = 0;
		float d;
		for (int i = 0; i < points.length; i++) {
			d = MathU.bernsteinPolynom(i, points.length - 1, percent);
			b += d * points[i].x();
			c += d * points[i].y();
		}

		try {
			coord.set(b, c);
		} catch (Exception e) {
			return new Vector2f(b, c);
		}
		return null;
	}

	@Override
	public PlanVector getCoordinate(double distance, PlanVector coord) {
		return bindCoordinate(getEquivalentPercentage((float) distance), null);
	}

	@Override
	public void debug(GameContainer container, Graphics g, boolean condition) {
		for (float f = 0; f <= 1; f += .01f) {
			PlanVector vec = bindCoordinate(f, null);
			g.drawRect(vec.x(), vec.y(), 5, 5);
		}
	}

	@Override
	public PlanVector getFrom() {
		return points[0];
	}

	@Override
	public PlanVector getTo() {
		return points[points.length - 1];
	}

	@Override
	public void setFrom(PlanVector coord) {
		points[0] = coord;
	}

	@Override
	public void setTo(PlanVector coord) {
		points[points.length - 1] = coord;
	}

	/**
	 * Self explaining
	 * 
	 * @param precision
	 *            the number of segment to cut the curve into (divided by 2)
	 * @return segment tab
	 */
	public AdvancedSegment[] convertInSegments(int precision) {

		AdvancedSegment[] segments = new AdvancedSegment[precision];

		int i = 0;
		float iter = 1 / (float) precision;

		for (float f = 0; f < 1; f += iter) {
			f = (float) (Math.round(f * 100) / 100.0);
			segments[i] = new LinearSegment(bindCoordinate(f, null),
					bindCoordinate(f + iter, null));
			i++;
		}

		return segments;

	}

	private void calculateLength() {
		length = 0;
		PlanVector previous = getFrom();
		for (float percent = 0f; percent < 1.1; percent += .01f) {
			length += VecU
					.getDistance(previous, bindCoordinate(percent, null));
		}

	}

	/**
	 * Is an approximative value
	 * 
	 */
	@Override
	public float getEquivalentPercentage(float distance) {

		return distance / length;
	}

	/**
	 * Is an approximative value
	 * 
	 */
	@Override
	public float getLength() {
		return length;
	}

	/**
	 * Is an approximative value
	 * 
	 */
	@Override
	public float getEquivalentDistance(float percent) {
		return percent * length;
	}

	/* ****************
	 * 
	 * Getters / Setters
	 * 
	 * ***************
	 */

}

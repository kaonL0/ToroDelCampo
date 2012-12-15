package com.alnaiyr.math.geom.shape.segments;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.coordinates.utilities.VecU;
import com.alnaiyr.math.numbers.profiles.percents.Percentable;
import com.alnaiyr.utilities.debug.DB;

/**
 * An implementation of bezier curves with only two control pointsd
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class SimpleBezierSegment implements AdvancedSegment {

	/* ****************
	 * 
	 * Variables
	 * 
	 * ***************
	 */

	private PlanVector from;
	private PlanVector to;
	private PlanVector control1;
	private PlanVector control2;

	private float length;

	/* ****************
	 * 
	 * Constructors
	 * 
	 * ***************
	 */

	/**
	 * @param from
	 * @param to
	 * @param control1
	 * @param control2
	 */
	public SimpleBezierSegment(PlanVector from, PlanVector to,
			PlanVector control1, PlanVector control2) {
		this.from = from;
		this.to = to;
		this.control1 = control1;
		this.control2 = control2;
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

	/**
	 * Copied from slick and updated
	 * 
	 */
	@Override
	public PlanVector bindCoordinate(Percentable percent, PlanVector coord) {
		float a = 1 - percent.getPercentage();
		float b = percent.getValue();

		float f1 = a * a * a;
		float f2 = 3 * a * a * b;
		float f3 = 3 * a * b * b;
		float f4 = b * b * b;

		float nx = from.x() * f1 + control1.x() * f2 + control2.x()
				* f3 + to.x() * f4;
		float ny = from.y() * f1 + control1.y() * f2 + control2.y()
				* f3 + to.y() * f4;

		return new Vector2f(nx, ny);
	}

	@Override
	public PlanVector bindCoordinate(float percent, PlanVector coord) {
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
		return from;
	}

	@Override
	public PlanVector getTo() {
		return to;
	}

	@Override
	public void setFrom(PlanVector coord) {
		from = coord;
	}

	@Override
	public void setTo(PlanVector coord) {
		to = coord;
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
			DB.test(i, f, iter);
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

package com.alnaiyr.math.geom.shape.segments;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.coordinates.utilities.VecU;
import com.alnaiyr.math.functions.interpolations.Interpolater1D;
import com.alnaiyr.math.functions.interpolations.Interpolation;
import com.alnaiyr.math.numbers.profiles.percents.Percentable;

/**
 * a linear segment
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class LinearSegment implements AdvancedSegment {

	private PlanVector	from;
	private PlanVector	to;

	/**
	 * @param coordinable
	 * @param coordinable2
	 */
	public LinearSegment(final PlanVector coordinable,
			final PlanVector coordinable2) {
		from = coordinable;
		to = coordinable2;
	}

	/**
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public LinearSegment(final float x1, final float y1, final float x2,
			final float y2) {
		from = new Vector2f(x1, y1);
		to = new Vector2f(x2, y2);
	}

	/* **********************
	 * 
	 * Methods
	 * 
	 * **********************
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.alnaiyr.math.geom.shape.segments.Segment#bindCoordinate(com.alnaiyr
	 * .math.numbers.profiles.percents.Percentable,
	 * com.alnaiyr.coordinates.Coordinate)
	 */
	@Override
	public PlanVector bindCoordinate(final Percentable percent,
			final PlanVector coord) {

		try {
			coord.set(Interpolater1D.interpolate(Interpolation.LINEAR,
					from.x(), to.x(), percent.getPercentage()), Interpolater1D
					.interpolate(Interpolation.LINEAR, from.y(), to.y(),
							percent.getValue()));
		} catch (final NullPointerException e) {
			return new Vector2f(Interpolater1D.interpolate(
					Interpolation.LINEAR, from.x(), to.x(),
					percent.getPercentage()), Interpolater1D.interpolate(
					Interpolation.LINEAR, from.y(), to.y(), percent.getValue()));
		}
		return coord;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.alnaiyr.math.geom.shape.segments.Segment#bindCoordinate(float,
	 * com.alnaiyr.coordinates.Coordinate)
	 */
	@Override
	public PlanVector bindCoordinate(final float percent, final PlanVector coord) {

		try {

			coord.set(Interpolater1D.interpolate(Interpolation.LINEAR,
					from.x(), to.x(), percent), Interpolater1D.interpolate(
					Interpolation.LINEAR, from.y(), to.y(), percent));
		} catch (final NullPointerException e) {

			return new Vector2f(Interpolater1D.interpolate(
					Interpolation.LINEAR, from.x(), to.x(), percent),
					Interpolater1D.interpolate(Interpolation.LINEAR, from.y(),
							to.y(), percent));
		}
		return coord;
	}

	/**
	 * FIXME TODO doesn't work so far
	 */
	@Override
	public PlanVector getCoordinate(final double distance,
			final PlanVector coord) {

		final PlanVector ncoord = VecU.getDirection(from, to)
				.mulLocal((float) distance).addLocal(from);

		try {
			if (!VecU.isInside(ncoord, from, to)) {
				coord.set(ncoord.x(), ncoord.y());
			} else if (distance <= 0) {
				coord.set(from.x(), from.y());
			} else {
				coord.set(to.x(), to.y());
			}

		} catch (final NullPointerException e) {
			if (!VecU.isInside(ncoord, from, to))
				return ncoord;
			else if (distance <= 0)
				return from;
			else
				return to;
		}
		return coord;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.alnaiyr.math.geom.shape.segments.AdvancedSegment#getCoordinate(float,
	 * float, com.alnaiyr.coordinates.Coordinate)
	 */
	@Override
	public PlanVector bindCoordinate(final float currentPercent,
			final float distanceToAdd, final PlanVector coord) {

		final Vector2f ncoord = ((Vector2f) VecU.getDirection(from, to))
				.scale(distanceToAdd);

		coord.set(
				Interpolater1D.interpolate(Interpolation.LINEAR, from.x(),
						to.x(), currentPercent)
						+ ncoord.x(),
				Interpolater1D.interpolate(Interpolation.LINEAR, from.y(),
						to.y(), currentPercent)
						+ ncoord.y());

		return new Vector2f(Interpolater1D.interpolate(Interpolation.LINEAR,
				from.x(), to.x(), currentPercent), Interpolater1D.interpolate(
				Interpolation.LINEAR, from.y(), to.y(), currentPercent))
				.add(ncoord);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.alnaiyr.math.geom.shape.segments.AdvancedSegment#getPercentage(com
	 * .alnaiyr.coordinates.Coordinate)
	 */
	@Override
	public float getPercentage(final PlanVector coord) {
		return VecU.getLinearParametricValue(coord, from, to);
	}

	/* *********************
	 * 
	 * Getters / Setters
	 * 
	 * ***********************
	 */

	/**
	 * @return the from
	 * 
	 */
	@Override
	public PlanVector getFrom() {
		return from;
	}

	/**
	 * @return the to
	 */
	@Override
	public PlanVector getTo() {
		return to;
	}

	/**
	 * @param from
	 *            the from to set
	 */
	@Override
	public void setFrom(final PlanVector from) {
		this.from = from;
	}

	/**
	 * @param to
	 *            the to to set
	 */
	@Override
	public void setTo(final PlanVector to) {
		this.to = to;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.alnaiyr.display.debug.Debug#debug(org.newdawn.slick.GameContainer,
	 * org.newdawn.slick.Graphics, boolean)
	 */
	@Override
	public void debug(final GameContainer container, final Graphics g,
			final boolean condition) {
		if (condition) {
			g.drawLine(from.x(), from.y(), to.x(), to.y());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.alnaiyr.math.geom.shape.segments.Segment#getEquivalentPercentage(
	 * float)
	 */
	@Override
	public float getEquivalentPercentage(final float distance) {
		return distance / getLength();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.alnaiyr.math.geom.shape.segments.Segment#getLength()
	 */
	@Override
	public float getLength() {
		return VecU.getDistance(from, to);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.alnaiyr.math.geom.shape.segments.Segment#getEquivalentDistance(float)
	 */
	@Override
	public float getEquivalentDistance(final float percent) {
		return percent * getLength();
	}

}

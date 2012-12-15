package com.alnaiyr.physics.movement.itinerary;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import com.alnaiyr.ai.updater.ObjectUpdater;
import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.math.geom.shape.segments.LinearSegment;
import com.alnaiyr.math.geom.shape.segments.Segment;
import com.alnaiyr.math.numbers.profiles.behaves.limit.LimitBehaves;
import com.alnaiyr.math.numbers.profiles.behaves.release.ReleaseBehaves;
import com.alnaiyr.math.numbers.profiles.percents.Percentable;
import com.alnaiyr.utilities.list.ListU;

/**
 * Handles displacements on a group of linked segment;
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class Itinary implements Segment, ObjectUpdater<ItinaryProfile> {

	/* ****************
	 * 
	 * Variables
	 * 
	 * ***************
	 */

	private List<SegmentProfile> segments = new ArrayList<>(5);

	public LimitBehaves limit = LimitBehaves.BLOCKON;
	public ReleaseBehaves release = ReleaseBehaves.NULL;

	private float length = 0;

	/* ****************
	 * 
	 * Constructors
	 * 
	 * ***************
	 */

	/**
	 * @param segments
	 */
	public Itinary(Segment... segments) {
		for (Segment segment : segments) {
			addSegment(segment);
		}
	}

	/* ****************
	 * 
	 * Methods
	 * 
	 * ***************
	 */

	/**
	 * Adds a new Segment to the itinary. Please note that this method can get
	 * very slow when there is a high number of segment associated. it is
	 * dynamically made, meaning that a coordinate should be able to update
	 * itself even if a new Segment is added during the loop.
	 * 
	 * @param segment
	 */
	public void addSegment(Segment segment) {
		if (!this.segments.isEmpty()) {
			if (!segment.getFrom().equals(
					ListU.getLast(this.segments).getSegment().getTo())) {
				addSegment(new LinearSegment(ListU.getLast(this.segments)
						.getSegment().getTo(), segment.getFrom()));
			}
		}
		this.segments.add(new SegmentProfile(segment, length));
		length += segment.getLength();
		float lengthBefore = 0;
		for (SegmentProfile profile : this.segments) {
			profile.setPercentage(lengthBefore, length);
			lengthBefore += profile.getSegment().getLength();
		}
	}

	/**
	 * Adds a group of segments.
	 * 
	 * @param segments
	 */
	public void addSegment(Segment... segments) {
		for (Segment seg : segments) {
			addSegment(seg);
		}
	}

	@Override
	public void debug(GameContainer container, Graphics g, boolean condition) {
		for (SegmentProfile profile : this.segments) {
			profile.getSegment().debug(container, g, condition);
		}
	}

	@Override
	public PlanVector bindCoordinate(float percent, PlanVector coord) {
		getCurrentSegment(percent).setCoordinates(coord, percent);
		return new Vector2f(coord);
	}

	@Override
	public PlanVector bindCoordinate(Percentable percent, PlanVector coord) {
		getCurrentSegment(percent.getPercentage()).setCoordinates(coord,
				percent.getPercentage());
		return new Vector2f(coord);
	}

	/**
	 * @param percent
	 * @return current segment
	 */
	public SegmentProfile getCurrentSegment(float percent) {

		for (int i = 0; i < segments.size(); i++) {
			if (segments.get(i).getPercent() > percent) {
				return segments.get(i - 1);
			}

		}
		return segments.get(segments.size() - 1);
	}

	/**
	 * @param percent
	 * @param from
	 * @return current segment
	 */
	public SegmentProfile getCurrentSegment(float percent, SegmentProfile from) {
		for (int i = segments.indexOf(from); i < segments.size(); i++) {
			if (segments.get(i).getPercent() > percent) {
				return segments.get(i - 1);
			}

		}
		return segments.get(segments.size() - 1);
	}

	@Override
	public float getEquivalentDistance(float percent) {
		return length * percent;
	}

	@Override
	public float getEquivalentPercentage(float distance) {
		return distance / length;
	}

	/* ****************
	 * 
	 * Getters / Setters
	 * 
	 * ***************
	 */

	/**
	 * @return first coordinate
	 */
	@Override
	public PlanVector getFrom() {
		return segments.get(0).getSegment().getFrom();
	}

	/**
	 * @return the length
	 */
	@Override
	public float getLength() {
		return length;
	}

	/**
	 * @param i
	 * @return next segment
	 */
	public SegmentProfile getNextSegment(int i) {
		return ListU.getNext(i, segments);
	}

	/**
	 * @param segment
	 * @return next segment
	 */
	public SegmentProfile getNextSegment(SegmentProfile segment) {
		return ListU.getNext(segments.indexOf(segment), segments);
	}

	/**
	 * @param i
	 * @return segment
	 */
	public SegmentProfile getSegment(int i) {
		return segments.get(i);
	}

	/**
	 * @return last coordinate
	 */
	@Override
	public PlanVector getTo() {
		return ListU.getLast(segments).getSegment().getTo();
	}

	@Override
	public void update(ItinaryProfile t, int delta, boolean condition) {
		t.update(this, delta, true);
	}

}

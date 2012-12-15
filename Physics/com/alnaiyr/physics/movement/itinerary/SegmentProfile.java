package com.alnaiyr.physics.movement.itinerary;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.math.geom.shape.segments.Segment;

/**
 * A Segment part of a group of segments, knowing its percentage position, and a
 * corresponding value between its percentages and global ones.
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class SegmentProfile {
	/* ****************
	 * 
	 * Variables
	 * 
	 * ***************
	 */

	private float percent;
	private float relativePercent;

	private Segment segment;

	/* ****************
	 * 
	 * Constructors
	 * 
	 * ***************
	 */

	/**
	 * Creates a new Segment profile based on a total length (for an itinary or
	 * a segment group),assuming that it is created AFTER all other, and not in
	 * the middle of something.
	 * 
	 * @param segment
	 * @param lengthBefore
	 */
	public SegmentProfile(Segment segment, float lengthBefore) {
		this.segment = segment;
		this.percent = lengthBefore / (lengthBefore + segment.getLength());
		calculateRelativePercent(lengthBefore + segment.getLength());
	}

	/**
	 * Creates a new Segment profile based on a total length (for an itinary or
	 * a segment group),created at any position
	 * 
	 * @param segment
	 * @param lengthBefore
	 * @param totalLength
	 */
	public SegmentProfile(Segment segment, float lengthBefore, float totalLength) {
		this.segment = segment;
		this.percent = lengthBefore / (totalLength + segment.getLength());
		calculateRelativePercent(totalLength);
	}

	/* ****************
	 * 
	 * Methods
	 * 
	 * **************
	 */

	/**
	 * Recalculating percentages, no matter the position of the segment in the
	 * itinary
	 * 
	 * @param lengthBefore
	 * @param totalLength
	 */
	public void setPercentage(float lengthBefore, float totalLength) {
		this.percent = lengthBefore / totalLength;
		calculateRelativePercent(totalLength);
	}

	/**
	 * Sets coordinate to a new one knowing its position on an Itinary. Works
	 * also if there is only one segment.
	 * 
	 * @param coord
	 * @param globalPercent
	 */
	public void setCoordinates(PlanVector coord, float globalPercent) {
		segment.bindCoordinate(getEquivalentPercent(globalPercent), coord);
	}

	/**
	 * Calculate a mutliply factor to get relation between relative percentages
	 * and global ones
	 * 
	 * @param totalLength
	 */
	public void calculateRelativePercent(float totalLength) {
		relativePercent = segment.getEquivalentDistance(.1f) / totalLength;
		this.relativePercent = .1f / relativePercent;
	}

	/* ****************
	 * 
	 * Getters / Setters
	 * 
	 * ***************
	 */

	/**
	 * @return the percent
	 */
	public float getPercent() {
		return percent;
	}

	/**
	 * @return the relativePercent
	 */
	public float getRelativePercent() {
		return relativePercent;
	}

	/**
	 * @param globalPercent
	 * @return equivalence
	 */
	public float getEquivalentPercent(float globalPercent) {
		return (globalPercent - percent) * relativePercent;
	}

	/**
	 * @return the segment
	 */
	public Segment getSegment() {
		return segment;
	}

}

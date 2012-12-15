package com.alnaiyr.physics.movement.itinerary;

import org.newdawn.slick.geom.Vector2f;

import com.alnaiyr.ai.updater.ObjectUpdater;
import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.math.numbers.advanced.PercentMM;
import com.alnaiyr.math.numbers.profiles.Profile;

/**
 * Iterator over an itinary, for a coordinate.
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public abstract class ItinaryProfile implements ObjectUpdater<Itinary> {

	/* ****************
	 * 
	 * Variables
	 * 
	 * ***************
	 */

	protected Profile profile;

	protected PlanVector coord;
	protected SegmentProfile current;

	/* ****************
	 * 
	 * Constructors
	 * 
	 * ***************
	 */

	/**
	 * 
	 * @param it
	 * @param coord
	 */
	public ItinaryProfile(Itinary it, PlanVector coord) {
		super();
		this.coord = coord;
		profile = new Profile();
		profile.setValue(0);
		profile.setMinMax(PercentMM.instance);
		profile.setLimit(it.limit);
		profile.setRelease(it.release);
	}

	/**
	 * @param it
	 * @param percent
	 * @param coord
	 */
	public ItinaryProfile(Itinary it, float percent, PlanVector coord) {
		this(it, coord);
		profile.setValue(percent);
	}

	/* ****************
	 * 
	 * Methods
	 * 
	 * ***************
	 */

	/**
	 * @param it
	 * @param distance
	 * @return vector of corresponding coordinates
	 */
	public PlanVector getPreviousCoordinate(Itinary it, float distance) {
		float per = distance / it.getLength();
		SegmentProfile current = it.getCurrentSegment(profile.getValue() + per);
		PlanVector vec = new Vector2f();
		current.setCoordinates(vec, profile.getValue() + per);
		return vec;
	}

	/* ****************
	 * 
	 * Getters / Setters
	 * 
	 * ***************
	 */

	/**
	 * @param coord
	 */
	public void setCoordinate(PlanVector coord) {
		this.coord = coord;
	}

	/**
	 * @param percent
	 * @see com.alnaiyr.math.numbers.profiles.percents.Percentable#setPercentage(float)
	 */
	public void setPercentage(float percent) {
		profile.setValue(percent);
	}

}

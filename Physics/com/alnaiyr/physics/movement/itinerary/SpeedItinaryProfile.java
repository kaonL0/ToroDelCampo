package com.alnaiyr.physics.movement.itinerary;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.math.numbers.DeltaFloatStorer;
import com.alnaiyr.math.numbers.impl.DeltaFloatStorerImpl;
import com.alnaiyr.physics.speed.Speed;

/**
 * Profile that goes with a certain speed, meaning that it will do the itinary
 * in a non constant amount of time, this depending on length of segments part
 * of the itinary.
 * 
 * Efficiency is maximum when speed makes sense compared to segment sizes,
 * meaning that it should be inferior to minimum segment size of an itinary
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class SpeedItinaryProfile extends ItinaryProfile {

	/* ****************
	 * 
	 * Constructors
	 * 
	 * ***************
	 */

	/**
	 * Most Expensive profile, only for complex speeds
	 * 
	 * @param itinary
	 * @param coord
	 * 
	 * @param speed
	 * @param percent
	 */
	public SpeedItinaryProfile(Itinary it, PlanVector coord, Speed speed) {
		super(it, coord);
		this.current = it.getCurrentSegment(0);
		profile.setDerivative(speed);
		profile.setMultiplier(1 / it.getLength());
	}

	/**
	 * @param itinary
	 * @param coord
	 * 
	 * @param speed
	 * @param percent
	 */
	public SpeedItinaryProfile(Itinary it, PlanVector coord, float speed) {
		super(it, coord);
		this.current = it.getCurrentSegment(0);
		profile.setDerivative(new DeltaFloatStorerImpl(speed));
		profile.setMultiplier(1 / it.getLength());
	}

	/**
	 * Less expensive constructor (memory talking) when using a speed more than
	 * once
	 * 
	 * @param itinary
	 * @param coord
	 * 
	 * @param speed
	 * @param percent
	 */
	public SpeedItinaryProfile(Itinary it, PlanVector coord,
			DeltaFloatStorer speed) {
		super(it, coord);
		this.current = it.getCurrentSegment(0);
		profile.setDerivative(speed);
		profile.setMultiplier(1 / it.getLength());
	}

	/* ****************
	 * 
	 * Methods
	 * 
	 * ***************
	 */

	@Override
	public void update(Itinary t, int delta, boolean condition) {

		current = t.getCurrentSegment(profile.getValue(), current);
		current.setCoordinates(coord, profile.getValue());

		profile.update(delta, condition);

	}

	/* ****************
	 * 
	 * Getters / Setters
	 * 
	 * ***************
	 */

}

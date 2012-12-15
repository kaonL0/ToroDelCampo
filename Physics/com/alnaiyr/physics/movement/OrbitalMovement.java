package com.alnaiyr.physics.movement;

import org.newdawn.slick.util.FastTrig;

import com.alnaiyr.ai.updater.ObjectUpdater;
import com.alnaiyr.coordinates.dynamic.Polar;

/**
 * Describes an orbital movement around an object
 * 
 * @author IgoЯ
 * @version 1.4
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>Functional</em></li>
 *          <li><em>Added Getters and Setters</em></li>
 *          <li>
 *          <em>Now works with excentricity (allows non closed orbit == gravity influence)</em>
 *          </li>
 *          <li><em>More convenient constructor and methods</em></li>
 *          <li><em>Signed apoapsis, periapsis</em></li>
 *          </ul>
 */
public class OrbitalMovement implements ObjectUpdater<Polar> {

    /* **********************************************
     * 
     * Variables
     * 
     * ************************************************
     */

    /** the max radius of the orbit */
    private float smallestRadius;

    /** the excentricity of the orbit (between 0 and 1 ) */
    private float excentricity;

    private float security = 0;

    /**
     * the speed, when the object is the closest to its center of orbit (in
     * radiant)
     */
    private float speed;

    /* **********************************************
     * 
     * Constructor
     * 
     * ************************************************
     */

    /**
     * Creates a new orbital movement, using all fields
     * 
     * @param minRadius
     * @param excentricity
     * @param speed
     */
    public OrbitalMovement(float minRadius, float excentricity, float speed) {

	if (excentricity == 1)
	    excentricity = 1.0001f;
	smallestRadius = minRadius / (1 - excentricity);
	this.excentricity = excentricity;
	this.speed = speed;
    }

    /**
     * Creates a new orbital movement, using all fields
     * 
     * @param minRadius
     * @param excentricity
     * @param speed
     * @param security
     *            ;
     */
    public OrbitalMovement(float minRadius, float excentricity, float speed,
	    float security) {

	if (excentricity == 1)
	    excentricity = 1.0001f;
	smallestRadius = minRadius / (1 - excentricity);
	this.excentricity = excentricity;
	this.speed = speed;
	this.security = security;
	if (Math.abs(getPeriapsis()) < security)
	    setPeriapsis(security);
    }

    /* **********************************************
     * 
     * Methods
     * 
     * ************************************************
     */

    @Override
    public void update(Polar polar, int delta, boolean condition) {

	if (smallestRadius != 0) {
	    polar.setTheta(polar.getTheta() + speed
		    / (polar.getR() * polar.getR()) * delta);
	    /* http://fr.wikipedia.org/wiki/Ellipse_(math%C3%A9matiques) */
	    polar.setR((float) (smallestRadius
		    * (1 - excentricity * excentricity) / (1 + excentricity
		    * FastTrig.cos(polar.getTheta()))));
	} else
	    polar.setLocal(0, polar.getTheta() + speed * delta);
    }

    /* **********************************************
     * 
     * Getters
     * 
     * ************************************************
     */

    /**
     * Gets the lowest radius of the orbit (periapsis), signed with the
     * excentricity signum
     * 
     * @return the periapsis
     */
    public float getPeriapsis() {
	return smallestRadius * (1 - excentricity) <= smallestRadius
		+ smallestRadius * excentricity ? Math
		.signum(excentricity == 0 ? 1 : excentricity)
		* smallestRadius * (1 - excentricity) : Math
		.signum(excentricity)
		* (smallestRadius + smallestRadius * excentricity);
    }

    /**
     * Gets the highest radius of the orbit (apoapsis), signed with the
     * excentricity signum
     * 
     * @return the apoapsis
     */
    public float getApoapsis() {

	return smallestRadius * (1 - excentricity) <= smallestRadius
		+ smallestRadius * excentricity ? Math
		.signum(excentricity == 0 ? 1 : excentricity)
		* (smallestRadius + smallestRadius * excentricity) : Math
		.signum(excentricity) * smallestRadius * (1 - excentricity);
    }

    /**
     * @return speed
     */
    public float getSpeed() {

	return speed;
    }

    /**
     * @return excentricity
     */
    public float getExcentricity() {

	return excentricity;
    }

    /**
     * @return security
     */
    public float getSecurity() {
	return security;
    }

    /* **********************************************
     * 
     * Setters
     * 
     * ************************************************
     */

    /**
     * @param periapsis
     */
    public void setPeriapsis(float periapsis) {

	smallestRadius = periapsis / (1 - excentricity);
    }

    /**
     * @param excentricity
     */
    public void setEcentricity(float excentricity) {

	if (!(Math.abs(getPeriapsis()) < security))
	    this.excentricity = excentricity;
	else {
	    this.excentricity = 0;
	}
    }

    /**
     * @param speed
     */
    public void setSpeed(float speed) {

	this.speed = speed;
    }

    /**
     * @param security
     */
    public void setSecurity(float security) {
	this.security = security;
    }

}

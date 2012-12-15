package com.alnaiyr.coordinates.dynamic;

import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.FastTrig;

import com.alnaiyr.coordinates.PlanVector;

/**
 * Very useful class for space coordinates (using polar convention).<br/>
 * 
 * By convention, please put the coordinate on the gravity center of an object.
 * 
 * @author IgoЯ
 * @version 1.4
 *          <p>
 *          <Strong> Version change:</strong><br/>
 *          <ul>
 *          <li>Fully functional</li>
 *          <li>More object oriented</li>
 *          <li>Update is made automatically now</li>
 *          <li>More getters and setters</li>
 *          <li>Less calculation now</li>
 *          <li>Now updates with its reference</li>
 *          </ul>
 *          </p>
 **/

public class Polar extends ReferencedCoordinate {

	/* **********************************************
	 * 
	 * Variables
	 * 
	 * ************************************************
	 */

	/** Local radius for a polar system */
	private float r = 0;

	/** Local angle in radiant for a polar system */
	private float theta = 0;

	/* **********************************************
	 * 
	 * Constructors
	 * 
	 * ************************************************
	 */

	/** All the constructor you need when you don't care about the position... **/

	public Polar() {
		super(Origin.ref);
		r = 0;
		theta = 0;
	}

	/**
	 * Defines the local origin.
	 * 
	 * @param centerx
	 * @param centery
	 */
	public Polar(float centerx, float centery) {
		super(Origin.ref);
		r = 0;
		theta = 0;
		reference = new Origin(centerx, centery, false);
	}

	/**
	 * Defines a coordinate system as origin
	 * 
	 * @param reference
	 */
	public Polar(PlanVector reference) {
		super(reference);
		r = 0;
		theta = 0;
	}

	/**
	 * Defines a coordinate with starting positions, and origin
	 * 
	 * @param r
	 * @param theta
	 * @param centerx
	 * @param centery
	 */

	public Polar(float r, float theta, float centerx, float centery) {
		super(new Origin(centerx, centery, false));
		this.r = r;
		this.theta = theta;
	}

	/**
	 * Defines a coordinate with starting positions, and a local coordinate as
	 * reference
	 * 
	 * @param r
	 * @param theta
	 * @param reference
	 */

	public Polar(float r, float theta, PlanVector reference) {
		super(reference);
		this.r = r;
		this.theta = theta;
	}

	/**
	 * Defines a coordinate with starting positions, and a local coordinate as
	 * reference
	 * 
	 * @param local
	 * @param reference
	 */

	public Polar(Vector2f local, PlanVector reference) {
		super(reference);
		r = local.x;
		theta = local.y;
	}

	/**
	 * copy coordinates from existing ones
	 * 
	 * @param other
	 *            coordinates to copy from
	 */
	public Polar(Polar other) {
		super(other.reference);
		r = other.r;
		theta = other.theta;
	}

	/* **********************************************
	 * 
	 * Internal method
	 * 
	 * ************************************************
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.Al_Nair.plancoordinate.LocalCoordinates#toString()
	 */
	@Override
	public String toString() {

		String info = "Coordinate type: Polar. \n Local coordinates: r: " + r
				+ " theta: " + theta + "\n Global coordinates: X: " + x()
				+ " Y: " + y();
		return info;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.Al_Nair.plancoordinate.LocalCoordinates#updateCoord()
	 */
	private void updateCoord() {
		theta = theta % (float) (Math.PI * 2);
		if (theta < 0)
			theta = (float) (Math.PI * 2 - Math.abs(theta));
	}

	/* **********************************************
	 * 
	 * Local Setters
	 * 
	 * ************************************************
	 */
	/**
	 * Sets r, and update global coordinates.
	 * 
	 * @param r
	 */
	public void setR(float r) {

		this.r = r;
	}

	/**
	 * Sets a new angle in radians, and update global coordinates
	 * 
	 * @param theta
	 */
	public void setTheta(float theta) {
		this.theta = theta;
		updateCoord();
	}

	/**
	 * Sets local coordinates, and update global
	 * 
	 * @param r
	 * @param theta
	 */
	@Override
	public void setLocal(float r, float theta) {

		this.r = r;
		this.theta = theta;
	}

	/**
	 * @param reference
	 *            the reference to set
	 */
	public void setReference(ReferencedCoordinate reference) {

		this.reference = reference;
	}

	/* **********************************************
	 * 
	 * Local Getters
	 * 
	 * ************************************************
	 */

	/** @return the r */
	public float getR() {

		return r;
	}

	/** @return the theta */
	public float getTheta() {

		theta = theta % (float) (Math.PI * 2);
		if (theta < 0)
			theta = (float) (Math.PI * 2 - Math.abs(theta));

		return theta;
	}

	/** @return theta + angle of the reference */
	@Override
	public float angle() {

		return getTheta() + reference.angle();
	}

	/* **********************************************
	 * 
	 * Global Setters
	 * 
	 * ************************************************
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.Al_Nair.plancoordinate.Plan_Coordinate_System#setLocal(float,
	 * float)
	 */
	@Override
	public PlanVector set(float x, float y) {

		theta = (float) Math.atan2(x - reference.x(), y - reference.y())
				- reference.angle();
		r = (float) Math.hypot(x - reference.x(), y - reference.y());
		return this;
	}

	/* **********************************************
	 * 
	 * Global Getters
	 * 
	 * ************************************************
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.Al_Nair.plancoordinate.Plan_Coordinate_System#getX()
	 */
	@Override
	public float x() {
		return getLocXFact() + reference.x();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.Al_Nair.plancoordinate.Plan_Coordinate_System#getY()
	 */
	@Override
	public float y() {
		return getLocYFact() + reference.y();
	}

	/* *********************
	 * 
	 * Version change
	 * 
	 * ******************************
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.Al_Nair.plancoordinate.Coordinates#addLocalX(float)
	 */
	@Override
	public void addLocalX(float x) {

		r += x;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.Al_Nair.plancoordinate.Coordinates#addLocalY(float)
	 */
	@Override
	public void addLocalY(float y) {

		theta += y;
	}

	@Override
	protected float getLocXFact() {
		return (float) (r * FastTrig.cos(-theta - reference.angle()));
	}

	@Override
	protected float getLocYFact() {
		return (float) (r * FastTrig.sin(-theta - reference.angle()));
	}

	@Override
	public float normalize() {
		return r = 1;
	}

	@Override
	public float lengthSquared() {
		return r * r;
	}

	@Override
	public PlanVector negateLocal() {
		theta = -theta;
		return this;
	}

	@Override
	public PlanVector subLocal(PlanVector center) {
		r -= center.length();
		theta -= center.angle();
		return this;
	}

	@Override
	public PlanVector addLocal(PlanVector extents) {

		r += extents.length();
		theta += extents.angle();

		return this;
	}

	@Override
	public PlanVector mulLocal(float f) {

		r *= f;

		return this;
	}

	@Override
	public PlanVector set(PlanVector coord) {

		r = coord.length();
		theta = coord.angle();

		return this;
	}

	@Override
	public float length() {
		return r;
	}

	@Override
	public PlanVector clone() {
		return new Polar(this);
	}

}

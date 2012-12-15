package com.alnaiyr.coordinates.dynamic;

import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.FastTrig;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.general.IV;

/**
 * <p>
 * Implements a local Cartesian system, meaning coordinate referring to any
 * point in the space.<br/>
 * 
 * please not that if the origin is (0,0), and angle =0 the coordinates will be
 * obviously the system coordinates.<br/>
 * 
 * By system coordinates, I mean left top corner origin(0,0), meaning that y is
 * inverted compared to normal math coordinates;
 * <p>
 * 
 * @author IgoЯ
 * @version 1.4
 *          <p>
 *          <Strong> Version change:</strong><br/>
 *          <ul>
 *          <li>Fully functional</li>
 *          <li>More object oriented</li>
 *          <li>Update is made automatically now</li>
 *          <li>Now can be in a different angle with the origin</li>
 *          <li>Less calculation now</li>
 *          <li>Fixed angle</li>
 *          </ul>
 *          </p>
 * 
 * **/

public class Cartesian extends ReferencedCoordinate {

	/* **********************************************
	 * 
	 * Variables
	 * 
	 * ************************************************
	 */

	/** Local X coordinates for a moving Cartesian system **/
	public float xL = 0;
	/** Local Y coordinates for a moving Cartesian system **/
	public float yL = 0;

	/** the inclinaison angle for this origin */
	private float angle = 0;

	/* **********************************************
	 * 
	 * Constructors
	 * 
	 * ************************************************
	 */

	/**
	 * Standard constructor: not very useful as it's equal to absolute. Point is
	 * set to (0,0)
	 **/

	public Cartesian() {
		super(Origin.ref);
		xL = 0;
		yL = 0;
		angle = 0;
	}

	public Cartesian(float xL, float yL) {
		super(Origin.ref);

		this.xL = xL * IV.getWidth();
		this.yL = yL * IV.getHeight();

		angle = 0;
	}

	public Cartesian(float xL, float yL, PlanVector reference) {
		super(reference);

		this.xL = xL * IV.getWidth();
		this.yL = yL * IV.getHeight();

		angle = 0;
	}

	/**
	 * Uses standard origin (0,0)
	 * 
	 * @param xL
	 * @param yL
	 * @param percentOfWindow
	 */

	public Cartesian(float xL, float yL, boolean percentOfWindow) {
		super(Origin.ref);
		if (percentOfWindow) {
			this.xL = xL * IV.getWidth();
			this.yL = yL * IV.getHeight();
		} else {
			this.xL = xL;
			this.yL = yL;
		}

		angle = 0;
	}

	/**
	 * Sets local coordinates, plus the origin of the moving Cartesian
	 * 
	 * @param x
	 * @param y
	 * @param reference
	 * @param percentOfWindow
	 */
	public Cartesian(float x, float y, PlanVector reference,
			boolean percentOfWindow) {
		super(reference);

		if (percentOfWindow) {
			xL = x * IV.getWidth();
			yL = y * IV.getHeight();
		} else {
			xL = x;
			yL = y;
		}

		angle = 0;
	}

	/**
	 * Sets local coordinates, plus the origin of the moving Cartesian
	 * 
	 * @param local
	 * @param reference
	 */

	public Cartesian(Vector2f local, PlanVector reference) {
		super(reference);

		xL = local.x;
		yL = local.y;

		angle = 0;
	}

	/**
	 * Sets local coordinates, plus the origin of the moving Cartesian
	 * 
	 * @param xL
	 * @param yL
	 * @param centerX
	 * @param centerY
	 */
	public Cartesian(float xL, float yL, float centerX, float centerY) {
		super(new Origin(centerX, centerY, false));

		this.xL = xL;
		this.yL = yL;
		angle = 0;
	}

	/* **********************************************
	 * 
	 * Internal Method
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
		String info = "Coordinate type: Cartesian. \n Local coordinates: xl: "
				+ xL + " yl: " + yL + "\n Local Origin: xr: " + reference.x()
				+ " yr: " + reference.y() + "\n Global coordinates: X: " + x()
				+ " Y: " + y();
		return info;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.Al_Nair.plancoordinate.LocalCoordinates#updateCoord()
	 */
	private void updateCoord() {
		if (angle != 0 || reference.angle() != 0) {
			angle = angle % (float) (Math.PI * 2);
			if (angle < 0)
				angle = (float) (Math.PI * 2 - Math.abs(angle));
		}
	}

	/* **********************************************
	 * 
	 * Local Setters
	 * 
	 * ************************************************
	 */

	/**
	 * Switch the angle to a new one, and update coordinates
	 * 
	 * @param angle
	 */
	public void setAngle(float angle) {
		this.angle = angle - reference.angle();
		updateCoord();
	}

	/**
	 * @param xl
	 *            the xl to set
	 */
	public void setXl(float xl) {
		xL = xl;
	}

	/**
	 * @param yl
	 *            the yl to set
	 */
	public void setYl(float yl) {
		yL = yl;
	}

	/**
	 * @param reference
	 *            the reference to set
	 */
	public void setReference(ReferencedCoordinate reference) {
		this.reference = reference;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.Al_Nair.plancoordinate.PlanCoordinateSystem#setLocal(float,
	 * float)
	 */
	@Override
	public void setLocal(float x, float y) {
		setXl(x);
		setYl(y);
	}

	/* **********************************************
	 * 
	 * Local Getters
	 * 
	 * ************************************************
	 */
	/**
	 * @return the xl
	 */
	public float getXL() {
		return xL;
	}

	/**
	 * @return the yl
	 */
	public float getYL() {
		return yL;
	}

	/**
	 * @return the total angle (angle + reference angle)
	 */
	@Override
	public float angle() {
		float angle = this.angle + reference.angle();
		angle = angle % (float) (Math.PI * 2);
		if (angle < 0)
			angle = (float) (Math.PI * 2 - Math.abs(angle));
		return angle;
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
	 * @see com.Al_Nair.plancoordinate.PlanCoordinateSystem#setLocal(float,
	 * float)
	 */
	@Override
	public PlanVector set(float x, float y) {
		xL = (float) ((x - reference.x())
				* FastTrig.cos(angle + reference.angle()) + (y - reference.y())
				* FastTrig.sin(angle + reference.angle()));
		yL = (float) (-(x - reference.x())
				* FastTrig.sin(angle + reference.angle()) + (y - reference.y())
				* FastTrig.cos(angle + reference.angle()));
		return this;
	}

	/* **********************************************
	 * 
	 * Getters
	 * 
	 * ************************************************
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.Al_Nair.plancoordinate.PlanCoordinateSystem#getX()
	 */
	@Override
	public float x() {
		return getLocXFact() + reference.x();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.Al_Nair.plancoordinate.PlanCoordinateSystem#getY()
	 */
	@Override
	public float y() {
		return getLocYFact() + reference.y();
	}

	/* ********************
	 * 
	 * Version Change
	 * 
	 * *********************
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.Al_Nair.plancoordinate.Coordinates#addLocalX(float)
	 */
	@Override
	public void addLocalX(float x) {
		xL += x;
	}

	@Override
	public float normalize() {
		float l = (float) Math.hypot(xL, yL);
		xL /= l;
		yL /= l;
		return l;
	}

	@Override
	public float lengthSquared() {
		return length() * length();
	}

	@Override
	public PlanVector negateLocal() {

		xL = -xL;
		yL = -yL;

		return this;
	}

	@Override
	public PlanVector subLocal(PlanVector center) {
		xL -= center.x();
		yL -= center.y();
		return this;
	}

	@Override
	public PlanVector addLocal(PlanVector extents) {

		xL += extents.x();
		yL += extents.y();

		return this;
	}

	@Override
	public PlanVector mulLocal(float f) {

		xL *= f;
		yL *= f;

		return this;
	}

	@Override
	public PlanVector set(PlanVector coord) {

		xL = coord.x();
		yL = coord.y();

		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.Al_Nair.plancoordinate.Coordinates#addLocalY(float)
	 */
	@Override
	public void addLocalY(float y) {
		yL += y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.Al_Nair.plancoordinate.PlanCoordinateSystem#getLX()
	 */
	public float getLX() {
		return getYL();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.Al_Nair.plancoordinate.PlanCoordinateSystem#getLY()
	 */
	public float getLY() {
		return getXL();
	}

	@Override
	protected float getLocXFact() {
		return angle != 0 || reference.angle() != 0 ? (float) (xL
				* FastTrig.cos(-angle) - yL * FastTrig.sin(-angle)) : xL;
	}

	@Override
	protected float getLocYFact() {
		return angle != 0 || reference.angle() != 0 ? (float) (yL
				* FastTrig.cos(-angle - reference.angle()) + xL
				* FastTrig.sin(-angle - reference.angle())) : yL;
	}

	@Override
	public float length() {
		return (float) Math.hypot(xL, yL);
	}

	@Override
	public PlanVector clone() {
		return new Cartesian(this.xL, this.yL, this.reference, false);
	}

}

package com.alnaiyr.coordinates.dynamic;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.models.gamestrategy.NoEffect;

/**
 * An abstract class describing what every local coordinate system should have
 * 
 * @author IgoЯ
 * @version 1.4
 *          <p>
 *          <Strong> Version change:</strong><br/>
 *          <ul>
 *          <li>Fully functional, although very small</li>
 *          <li>Added two abstract methods</li>
 *          <li>Improved performances on the update</li>
 *          <li>Takes care of two overriding now</li>
 *          <li>Made compatible with Vector2f class</li>
 *          </ul>
 *          </p>
 */
public abstract class ReferencedCoordinate implements CoordReferenced {

	/* *******************
	 * 
	 * Variables
	 * 
	 * *************************
	 */

	/** my reference, when I start to calculate my position */
	protected PlanVector reference;

	/**
	 * 
	 * @param coord
	 */
	public ReferencedCoordinate(PlanVector coord) {
		reference = coord;
	}

	/* *******************
	 * 
	 * Methods
	 * 
	 * *************************
	 */

	protected abstract float getLocXFact();

	protected abstract float getLocYFact();

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public abstract String toString();

	/**
	 * Update the localX by adding a value to it @since 1.1
	 * 
	 * @param x
	 */
	public abstract void addLocalX(float x);

	/**
	 * Update the localY by adding a value to it @since 1.1
	 * 
	 * @param y
	 */
	public abstract void addLocalY(float y);

	/* *******************
	 * 
	 * Getters and Setters
	 * 
	 * *************************
	 */

	@Override
	public void setReference(PlanVector reference) {
		this.reference = reference;
	}

	@Override
	public PlanVector getReference() {
		return reference;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		try {
			PlanVector coord = (PlanVector) o;
			return x() == coord.x() && y() == coord.y();
		} catch (ClassCastException e) {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return (int) (reference.hashCode() + x() + y());
	}

	@NoEffect
	@Override
	public PlanVector clone() {
		return null;
	}

}

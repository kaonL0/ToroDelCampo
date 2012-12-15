package com.alnaiyr.coordinates.dynamic;

import com.alnaiyr.coordinates.PlanVector;

/**
 * This interface describe an implementation of local coordinates, for a easier
 * way to describe relative movements
 * 
 * @author IgoЯ
 * @version 1.5
 * 
 *          <p>
 *          <Strong> Version change:</strong><br/>
 *          <ul>
 *          <li>Fully functional</li>
 *          <li>Changed access of function, for a cleaner use</li>
 *          <li>Removed some function, for internal definitions</li>
 *          <li>More Javadoc.</li>
 *          <li>Duplicated two function</li>
 *          <li>added local access</li>
 *          <li>added a super interface, for a more general use</li>
 *          </ul>
 *          </p>
 * **/

public interface CoordReferenced extends PlanVector {

	/**
	 * Gets the relation between itself and what it is attached to
	 * 
	 * @return the reference coordinate
	 */
	public PlanVector getReference();

	/**
	 * Sets the relation between itself and what it is attached to
	 * 
	 * @param reference
	 */
	public void setReference(PlanVector reference);

}

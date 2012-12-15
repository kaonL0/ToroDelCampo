package com.alnaiyr.coordinates;

/**
 * Is able to give its position, and set or get its coordinate
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public interface PlanVector extends Cloneable {

	/* ********************
	 * 
	 * Coordinate Getters
	 * 
	 * *********************
	 */

	/**
	 * returns the global X coordinates
	 * 
	 * @return X
	 */
	public float x();

	/**
	 * returns the global Y coordinates
	 * 
	 * @return Y
	 */
	public float y();

	/**
	 * 
	 * @return the current angle of the coordinate (regarding whatever it may be
	 *         (theta coordinate / space orientation)
	 */
	public float angle();

	/**
	 * Normalize the value
	 * 
	 * @return length before normalization
	 */
	public float normalize();

	/**
	 * 
	 * @return length, squared
	 */
	public float lengthSquared();

	/**
	 * 
	 * @return
	 */
	public float length();

	/* ********************
	 * 
	 * Local Operations
	 * 
	 * *********************
	 */

	/**
	 * Negate values
	 * 
	 * @return
	 */
	public PlanVector negateLocal();

	/**
	 * Subtracts locally the value
	 * 
	 * @param center
	 * @return itself for chained operations
	 */
	public PlanVector subLocal(PlanVector center);

	/**
	 * Adds locally the value
	 * 
	 * @param extents
	 * @return itself for chained operations
	 */
	public PlanVector addLocal(PlanVector extents);

	/**
	 * 
	 * @param f
	 * @return itself for chained operations
	 */
	public PlanVector mulLocal(float f);

	/* ********************
	 * 
	 * Coordinate Setters
	 * 
	 * *********************
	 */

	/**
	 * Sets local coordinates, if exists.
	 * 
	 * @param x
	 * @param y
	 */
	public void setLocal(float x, float y);

	/**
	 * Sets global coordinates.
	 * 
	 * @param x
	 * @param y
	 * @return itself
	 */
	public PlanVector set(float x, float y);

	/**
	 * Copy global coordinates.
	 * 
	 * @param coord
	 * @return itself
	 */
	public PlanVector set(PlanVector coord);

	public PlanVector clone();

}

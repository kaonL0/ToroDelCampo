package org.newdawn.slick.geom;

import org.jbox2d.common.Vec2;
import org.newdawn.slick.util.FastTrig;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.general.IV;

/**
 * A two dimensional vector
 * 
 * @author Kevin Glass
 * 
 *         I created dependencies to limit number of different classes
 *         Occurrences, even if it creates dependencies.
 * @author IgoR
 * 
 * */
public class Vector2f extends Vec2 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4724219038349239603L;

	/**
	 * Create an empty vector
	 */
	public Vector2f() {
	}

	public Vector2f(float x, float y, boolean percentOfWindow) {
		if (percentOfWindow) {
			this.x = IV.vWidth * x;
			this.y = IV.vHeight * y;
		} else {
			this.x = x;
			this.y = y;
		}
	}

	/**
	 * Create a vector based on the contents of a coordinate array
	 * 
	 * @param coords
	 *            The coordinates array, index 0 = x, index 1 = y
	 */
	public Vector2f(float[] coords) {
		x = coords[0];
		y = coords[1];
	}

	/**
	 * Create a new vector based on an angle
	 * 
	 * @param theta
	 *            The angle of the vector in degrees
	 */
	public Vector2f(float theta) {
		x = 1;
		y = 0;
		setTheta(theta);
	}

	/**
	 * Calculate the components of the vectors based on a angle
	 * 
	 * @param theta
	 *            The angle to calculate the components from (in degrees)
	 */
	public void setTheta(float theta) {
		// Next lines are to prevent numbers like -1.8369701E-16
		// when working with negative numbers
		if (theta < -360 || theta > 360) {
			theta = theta % 360;
		}
		if (theta < 0) {
			theta = 360 + theta;
		}
		double oldTheta = getTheta();
		if (theta < -360 || theta > 360) {
			oldTheta = oldTheta % 360;
		}
		if (theta < 0) {
			oldTheta = 360 + oldTheta;
		}

		float len = length();
		x = len * (float) FastTrig.cos(StrictMath.toRadians(theta));
		y = len * (float) FastTrig.sin(StrictMath.toRadians(theta));

		// x = x / (float) FastTrig.cos(StrictMath.toRadians(oldTheta))
		// * (float) FastTrig.cos(StrictMath.toRadians(theta));
		// y = x / (float) FastTrig.sin(StrictMath.toRadians(oldTheta))
		// * (float) FastTrig.sin(StrictMath.toRadians(theta));
	}

	/**
	 * Adjust this vector by a given angle
	 * 
	 * @param theta
	 *            The angle to adjust the angle by (in degrees)
	 * @return This vector - useful for chaining operations
	 * 
	 */
	public PlanVector add(float theta) {
		setTheta(getTheta() + theta);

		return this;
	}

	/**
	 * Adjust this vector by a given angle
	 * 
	 * @param theta
	 *            The angle to adjust the angle by (in degrees)
	 * @return This vector - useful for chaining operations
	 */
	public PlanVector sub(float theta) {
		setTheta(getTheta() - theta);

		return this;
	}

	/**
	 * Get the angle this vector is at
	 * 
	 * @return The angle this vector is at (in degrees)
	 */
	public float getTheta() {
		float theta = (float) Math.toDegrees(Math.atan2(y, x));
		if (theta < -360 || theta > 360) {
			theta = theta % 360;
		}
		if (theta < 0) {
			theta = 360 + theta;
		}

		return theta;
	}

	/**
	 * Create a new vector based on another
	 * 
	 * @param other
	 *            The other vector to copy into this one
	 */
	public Vector2f(PlanVector other) {
		this(other.x(), other.y());
	}

	/**
	 * Create a new vector
	 * 
	 * @param x
	 *            The x component to assign
	 * @param y
	 *            The y component to assign
	 */
	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Dot this vector against another
	 * 
	 * @param other
	 *            The other vector dot agianst
	 * @return The dot product of the two vectors
	 */
	public float dot(PlanVector other) {
		return x * other.x() + y * other.y();
	}

	/**
	 * A vector perpendicular to this vector.
	 * 
	 * @return a vector perpendicular to this vector
	 */
	public Vector2f getPerpendicular() {
		return new Vector2f(-y, x);
	}

	/**
	 * Subtract a vector from this vector
	 * 
	 * @param v
	 *            The vector subtract
	 * @return This vector - useful for chaining operations
	 */
	public Vector2f sub(PlanVector v) {
		x -= v.x();
		y -= v.y();

		return this;
	}

	/**
	 * Scale this vector by a value
	 * 
	 * @param a
	 *            The value to scale this vector by
	 * @return This vector - useful for chaining operations
	 */
	public Vector2f scale(float a) {
		x *= a;
		y *= a;

		return this;
	}

	/**
	 * Normalise the vector
	 * 
	 * @return This vector - useful for chaning operations
	 */
	public Vector2f normalise() {
		float l = length();

		if (l == 0) {
			return this;
		}

		x /= l;
		y /= l;
		return this;
	}

	/**
	 * The normal of the vector
	 * 
	 * @return A unit vector with the same direction as the vector
	 */
	public Vector2f getNormal() {
		Vector2f cp = clone();
		cp.normalise();
		return cp;
	}

	/**
	 * Project this vector onto another
	 * 
	 * @param b
	 *            The vector to project onto
	 * @param result
	 *            The projected vector
	 */
	public void projectOntoUnit(Vector2f b, Vector2f result) {
		float dp = b.dot(this);

		result.x = dp * b.x();
		result.y = dp * b.y();

	}

	/**
	 * Return a copy of this vector
	 * 
	 * @return The new instance that copies this vector
	 */
	@Override
	public Vector2f clone() {
		return new Vector2f(x, y);
	}

	/**
	 * Get the distance from this point to another
	 * 
	 * @param other
	 *            The other point we're measuring to
	 * @return The distance to the other point
	 */
	public float distance(PlanVector other) {
		return (float) Math.sqrt(distanceSquared(other));
	}

	/**
	 * Get the distance from this point to another, squared. This can sometimes
	 * be used in place of distance and avoids the additional sqrt.
	 * 
	 * @param other
	 *            The other point we're measuring to
	 * @return The distance to the other point squared
	 */
	public float distanceSquared(PlanVector other) {
		float dx = other.x() - x();
		float dy = other.y() - y();

		return dx * dx + dy * dy;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return 997 * (int) x ^ 991 * (int) y; // large primes!
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {
		if (other instanceof PlanVector) {
			Vector2f o = (Vector2f) other;
			return o.x == x && o.y == y;
		}

		return false;
	}

	@Override
	public float x() {

		return x;
	}

	@Override
	public float y() {

		return y;
	}

	@Override
	public void setLocal(float x, float y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public float angle() {
		return 0;
	}
}

package com.alnaiyr.coordinates.utilities;

import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Vec2;
import org.newdawn.slick.geom.Vector2f;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.coordinates.dynamic.CoordReferenced;

/**
 * Contains a series of useful functions for vectors
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class VecU {

	public static PlanVector sum(final PlanVector v1, final PlanVector v2) {
		return new Vector2f(v1.x() + v2.x(), v1.y() + v2.y());
	}

	/**
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public static PlanVector getDifference(final PlanVector from,
			final PlanVector to) {
		return new Vector2f(from.x() - to.x(), from.y() - to.y());
	}

	/**
	 * Returns the angle of a vector, stored in an int[]
	 * 
	 * @param vector
	 * @return the corresponding angle
	 */
	public static double getAngle(final int[] vector) {

		final double res = Math.atan2(-vector[1], vector[0]);

		return res;
	}

	/**
	 * @see #getAngle(int[])
	 * 
	 * @param vector
	 * @return the corresponding angle
	 */
	public static double getAngle(final byte[] vector) {

		final double res = Math.atan2(-vector[1], vector[0]);

		return res;
	}

	/**
	 * @see #findNormal(float, float, float, float)
	 * 
	 * @param pt1
	 * @param pt2
	 * @return normal vector
	 */
	public static byte[] findNormal(final int[] pt1, final int[] pt2) {

		final org.newdawn.slick.geom.Vector2f norm = VecU.findNormal(pt1[0],
				pt1[1], pt2[0], pt2[1]);
		return new byte[] { (byte) norm.x, (byte) norm.y };
	}

	/**
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return normal vector
	 */
	public static org.newdawn.slick.geom.Vector2f findNormal(final float x1,
			final float y1, final float x2, final float y2) {

		final org.newdawn.slick.geom.Vector2f line = new Vector2f(x1 - x2, y1
				- y2);
		final org.newdawn.slick.geom.Vector2f normal = line.getPerpendicular();
		normal.normalise();
		normal.scale(10);
		return normal;
	}

	/**
	 * Gets the normal vector of a line, given two of its points
	 * 
	 * @param point1
	 * @param point2
	 * @return normal vector
	 */
	public static PlanVector findNormal(final PlanVector point1,
			final PlanVector point2) {
		return VecU.findNormal(point1.x(), point1.y(), point2.x(), point2.y());
	}

	/**
	 * Checks if two vectors are opposed or not.works only with normal vectors
	 * 
	 * @param vector
	 * @param other
	 * @return true if opposed
	 * @throws NullPointerException
	 *             in case vectors are perpendicular
	 */
	public static boolean isOpposed(final PlanVector vector,
			final PlanVector other) {

		return vector.x() * other.x() + vector.y() * other.y() < 0;
	}

	/**
	 * 
	 * @param vec1
	 * @param vec2
	 * @return true if opposed
	 * @throws NullPointerException
	 *             in case vectors are perpendicular
	 */
	public static boolean isOpposed(final byte[] vec1, final byte[] vec2) {

		return vec1[0] * vec2[0] < 0 || vec1[1] * vec2[1] < 0;
	}

	/**
	 * return true if the two vector are opposed, considering they are on the
	 * same line
	 * 
	 * @param vec1
	 * @param vec2
	 * @return true if opposed
	 */
	public static boolean isOpposedOnLine(final PlanVector vec1,
			final PlanVector vec2) {
		return Math.signum(vec1.x()) != Math.signum(vec2.x())
				|| Math.signum(vec1.y()) != Math.signum(vec2.y());
	}

	/**
	 * Makes sure the y value of a vector2f is superior the its x value (useful
	 * for chains values stores in a vec2f)
	 * 
	 * @param toCheck
	 */
	public static void checkOrder(final Vec2 toCheck) {
		if (toCheck.y < toCheck.x)
			toCheck.setLocal(toCheck.y, toCheck.x);
	}

	/**
	 * Gets the direction from the first coordinate to the second one
	 * 
	 * @param from
	 * @param to
	 * 
	 * @return the vector
	 */
	public static PlanVector getDirection(final PlanVector from,
			final PlanVector to) {
		return new Vector2f(to.x() - from.x(), to.y() - from.y()).normalise();
	}

	/**
	 * @param from
	 * @param to
	 * @return distance between the two coordinates
	 */
	public static float getDistance(final PlanVector from, final PlanVector to) {
		return (float) Math.hypot(from.x() - to.x(), from.y() - to.y());
	}

	/**
	 * @param from
	 * @param to
	 * @return distance between the two coordinates
	 */
	public static float getDistance(final PlanVector from, final PlanVector to,
			final boolean y) {
		if (y)
			return Math.abs(from.y() - to.y());
		return Math.abs(from.x() - to.x());
	}

	/**
	 * Checks if a point is inside a segment, inclusive, assuming that it is on
	 * the line containing pt1 and pt2
	 * 
	 * @param xp
	 * @param yp
	 * @param x1
	 *            from pt1
	 * @param y1
	 *            from pt1
	 * @param x2
	 *            from pt2
	 * @param y2
	 *            from pt2
	 * @return true if inside inclusive
	 */
	public static boolean isInside(final float xp, final float yp,
			final float x1, final float y1, final float x2, final float y2) {
		return VecU.checkSide(xp, x1, x2) && VecU.checkSide(yp, y1, y2);
	}

	/**
	 * Used for isInside test
	 * 
	 * @param xp
	 * @param x1
	 * @param x2
	 * @return true if on side
	 */
	private static boolean checkSide(final float xp, final float x1,
			final float x2) {
		if (xp >= x1) {
			return xp <= x2;
		}
		return xp >= x2;
	}

	/**
	 * 
	 * @param from
	 * @param to
	 * @param point
	 * @return true if point is inside linear segment made from from to to
	 */
	public static boolean isInside(final PlanVector point,
			final PlanVector from, final PlanVector to) {
		return VecU.isInside(point.x(), point.y(), from.x(), from.y(), to.x(),
				to.y());
	}

	public static boolean isInside(final PlanVector point, final int width,
			final int height, final PlanVector other, final int width2,
			final int height2) {
		if (point.x() > other.x() + width2 || point.x() + width < other.x()) {
			return false;
		}
		if (point.y() > other.y() + height2 || point.y() + height < other.y()) {
			return false;
		}
		return true;
	}

	/**
	 * @param reference
	 * @param toUp
	 */
	public static void setBackReference(final PlanVector reference,
			final PlanVector toUp) {
		try {
			final CoordReferenced dyna = (CoordReferenced) toUp;
			dyna.setReference(reference);
		} catch (final ClassCastException e) {
			toUp.setLocal(toUp.x() + reference.x(), toUp.y() + reference.y());
		}

	}

	/**
	 * get parametric value for a linear segment ( == percentage). if point is
	 * not on the coordinate, do the calculation for the project on the segment
	 * 
	 * @param coord
	 * @param from
	 * @param to
	 * @return value, or 0 if negative, or 1 if after to
	 */
	public static float getLinearParametricValue(final PlanVector coord,
			final PlanVector from, final PlanVector to) {

		float t = (coord.x() - to.x()) / (from.x() - to.x());

		if (Float.isInfinite(t))
			t = (coord.y() - to.y()) / (from.y() - to.y());

		t = t < 0 ? 0 : t;
		return t > 1 ? 1 : t;

	}

	public final static PlanVector min(final Vec2 a, final Vec2 b) {
		return new Vec2(a.x < b.x ? a.x : b.x, a.y < b.y ? a.y : b.y);
	}

	public final static PlanVector max(final Vec2 a, final Vec2 b) {
		return new Vec2(a.x > b.x ? a.x : b.x, a.y > b.y ? a.y : b.y);
	}

	public final static PlanVector abs(final Vec2 a) {
		return new Vec2(MathUtils.abs(a.x), MathUtils.abs(a.y));
	}

	public final static void absToOut(final Vec2 a, final Vec2 out) {
		out.x = MathUtils.abs(a.x);
		out.y = MathUtils.abs(a.y);
	}

	public final static float dot(final PlanVector a, final PlanVector b) {
		return a.x() * b.x() + a.y() * b.y();
	}

	public final static float cross(final PlanVector a, final PlanVector b) {
		return a.x() * b.y() - a.y() * b.x();
	}

	public final static PlanVector cross(final Vec2 a, final float s) {
		return new Vec2(s * a.y, -s * a.x);
	}

	public final static void crossToOut(final Vec2 a, final float s,
			final Vec2 out) {
		final float tempy = -s * a.x;
		out.x = s * a.y;
		out.y = tempy;
	}

	public final static void crossToOutUnsafe(final Vec2 a, final float s,
			final Vec2 out) {
		assert out != a;
		out.x = s * a.y;
		out.y = -s * a.x;
	}

	public final static Vec2 cross(final float s, final Vec2 a) {
		return new Vec2(-s * a.y, s * a.x);
	}

	public final static void crossToOut(final float s, final Vec2 a,
			final Vec2 out) {
		final float tempY = s * a.x;
		out.x = -s * a.y;
		out.y = tempY;
	}

	public final static void crossToOutUnsafe(final float s, final Vec2 a,
			final Vec2 out) {
		assert out != a;
		out.x = -s * a.y;
		out.y = s * a.x;
	}

	public final static void negateToOut(final Vec2 a, final Vec2 out) {
		out.x = -a.x;
		out.y = -a.y;
	}

	public final static void minToOut(final Vec2 a, final Vec2 b, final Vec2 out) {
		out.x = a.x < b.x ? a.x : b.x;
		out.y = a.y < b.y ? a.y : b.y;
	}

	public final static void maxToOut(final Vec2 a, final Vec2 b, final Vec2 out) {
		out.x = a.x > b.x ? a.x : b.x;
		out.y = a.y > b.y ? a.y : b.y;
	}

	public final static boolean isSame(final PlanVector one,
			final PlanVector two) {
		return one.x() == two.x() && one.y() == two.y();
	}

}

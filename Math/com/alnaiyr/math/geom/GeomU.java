package com.alnaiyr.math.geom;

import org.newdawn.slick.geom.Vector2f;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.math.MathU;
import com.alnaiyr.math.geom.shape.Rectangle;

/**
 * Geometry utilities
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class GeomU {

	/* ****************
	 * 
	 * Methods
	 * 
	 * ***************
	 */

	public static PlanVector getRandomCoordinateInside(final Rectangle rect) {

		return new Vector2f(MathU.random(rect.getCoord().x(), rect
				.getDownRightCorner().x()), MathU.random(rect.getCoord().y(),
				rect.getDownRightCorner().y()));

	}

	/**
	 * 
	 * @param coord
	 * @param leftTopCorner
	 * @param width
	 * @param height
	 * @return true if inside the rectangle defined by values (inclusive)
	 */
	public static boolean isInside(final PlanVector coord,
			final PlanVector leftTopCorner, final float width,
			final float height) {

		return coord.x() >= leftTopCorner.x() && coord.y() >= leftTopCorner.y()
				&& coord.x() <= leftTopCorner.x() + width
				&& coord.y() <= leftTopCorner.y() + height;

	}

	/**
	 * 
	 * @param coord
	 * @param width
	 * @param center
	 * @param height
	 * @return true if inside the rectangle defined by values (inclusive)
	 */
	public static boolean isInside(final PlanVector coord, final float width,
			final PlanVector center, final float height) {

		return coord.x() >= center.x() - width / 2
				&& coord.y() >= center.y() - height / 2
				&& coord.x() <= center.x() + width / 2
				&& coord.y() <= center.y() + height / 2;

	}

}

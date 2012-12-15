package com.alnaiyr.generator.coordinates;

import org.newdawn.slick.geom.Vector2f;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.coordinates.dynamic.Cartesian;
import com.alnaiyr.math.MathU;

/**
 * Generates some coordinates things, not precise for now
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class CoordinatesFactory {

	/**
	 * Generates a random Cartesian coordinates,dynamic or static. The random
	 * has no seed, and is therefore completely random.
	 * 
	 * @param number
	 * @param minYValue
	 * @param minXValue
	 * @param maxYValue
	 * @param maxXValue
	 * @param dynamic
	 * @return
	 */
	public static PlanVector[] generateRandomCoordinates(int number,
			int minYValue, int minXValue, int maxYValue, int maxXValue,
			boolean dynamic) {

		PlanVector[] coords = new PlanVector[number];

		if (dynamic) {
			for (int i = 0; i < number; i++) {
				coords[i] = new Cartesian(MathU.random(minXValue, maxXValue),
						MathU.random(minYValue, maxYValue), false);
			}
		} else {
			for (int i = 0; i < number; i++) {
				coords[i] = new Vector2f(MathU.random(minXValue, maxXValue),
						MathU.random(minYValue, maxYValue));
			}
		}

		return coords;

	}

	public static PlanVector[] generateRandomCoordinates(int number,
			PlanVector from, PlanVector to, boolean dynamic) {

		PlanVector[] coords = new PlanVector[number];

		if (dynamic) {
			for (int i = 0; i < number; i++) {
				coords[i] = new Cartesian(MathU.random(from.x(), to.x()),
						MathU.random(from.y(), to.y()), false);
			}
		} else {
			for (int i = 0; i < number; i++) {
				coords[i] = new Vector2f(MathU.random(from.x(), to.x()),
						MathU.random(from.y(), to.y()));
			}
		}

		return coords;

	}

}

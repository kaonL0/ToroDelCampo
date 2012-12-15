package com.alnaiyr.display.impl.advanced;

import java.util.Arrays;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.impl.basic.DrawEntity;
import com.alnaiyr.display.renderables.DimensionDrawable;
import com.alnaiyr.generator.coordinates.CoordinatesFactory;

/**
 * One render on multiple coordinates, and then with different rendered object.
 * 
 * @author IgoЯ
 * @version 1.3
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>Added a lot of constructors</em></li>
 *          <li><em>Commented a lot</em></li>
 *          <li><em>Made it complicated</em></li>
 *          </ul>
 */
public class MultiCoordGEntity extends DrawEntity {

	public PlanVector[] coords;

	public MultiCoordGEntity(DimensionDrawable toRender, PlanVector... coord) {
		super(coord[0], toRender);
		coords = Arrays.copyOfRange(coord, 1, coord.length - 1);
	}

	/**
	 * Generates randomly coordinates, starting from one to another, and with a
	 * certain number of coordinates
	 * 
	 * @param toRender
	 * @param number
	 * @param from
	 * @param To
	 */
	public MultiCoordGEntity(DimensionDrawable toRender, int number,
			PlanVector from, PlanVector to) {
		super(
				CoordinatesFactory
						.generateRandomCoordinates(1, from, to, false)[0],
				toRender);
		coords = CoordinatesFactory.generateRandomCoordinates(number, from, to,
				false);
	}

	/*----------------------
	 * 
	 * Methods
	 * 
	 *---------------------------*/

}

package com.alnaiyr.coordinates.container;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.coordinates.Positionnable;

public interface CoordinateContainer extends PlanVector, Positionnable {

	/**
	 * 
	 * @param coord
	 */
	public void setCoord(PlanVector coord);

}

package com.alnaiyr.math.geom;

import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Path;



/**
 * Shape comparison for paths
 * 
 * @author IgoR
 * @version 1.0
 */
public class PathComparison implements ShapeComparison<Path, Line> {

	/**
	 * 
	 */
	public PathComparison(){}
	
	/* Adds a line to a path, quicker way than line to, line to (non-Javadoc)
	 * 
	 * @see com.Al_Nair.tools.shape.ShapeComparison#put(java.lang.Object,
	 * java.lang.Object) */

	@Override
	public Path put(Path where, Line to_add) {

		if (!where.contains(to_add.getX1(), to_add.getY1()))
			where.lineTo(to_add.getX1(), to_add.getY1());
		where.lineTo(to_add.getX2(), to_add.getY2());
		return where;
	}

	/* (non-Javadoc)
	 * 
	 * @see com.Al_Nair.tools.shape.ShapeComparison#add(java.lang.Object,
	 * java.lang.Object) */

	@Override
	public Path add(Path where, Path to_add) {
		// TODO Auto-generated method stub
		return null;
	}

	/** Add a line to a Shape, but starting for the end of the line, not from the start
	 * 
	 * @param where
	 * @param to_add
	 * @return reversed path
	 */

	public Path reverseput(Path where, Line to_add) {

		if (!where.contains(to_add.getX2(), to_add.getY2()))
			where.lineTo(to_add.getX2(), to_add.getY2());
		where.lineTo(to_add.getX1(), to_add.getY1());
		return where;
	}

}

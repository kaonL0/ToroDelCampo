package com.alnaiyr.physics.destruction.shapes;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import com.alnaiyr.coordinates.dynamic.ReferencedCoordinate;
import com.alnaiyr.math.MathU;
import com.alnaiyr.math.geom.shape.PointMap;
import com.alnaiyr.terrain.ground.TerrainTile;

/**
 * A damage shape, shaped as a quite angled rectangle
 * 
 * @author IgoЯ
 * @version 1.2
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>Works</em></li>
 *          <li><em>Added Corner</em></li>
 *          <li><em>Changed orientation, now clockwise</em></li>
 *          </ul>
 */
public class DamageRectangle extends DamageShape {

	/* *************************
	 * 
	 * Constructor
	 * 
	 * ************************** */

		
	/**
	 * Creates a new rectangle, based on its center and its dimensions
	 * 
	 * @param center
	 * @param step
	 * @param width
	 * @param height
	 */
	public DamageRectangle(int[] center,int step, ReferencedCoordinate reference, int width) {

	super(width, width, step);
		
		if(width%2!=0) width-=1;	
		this.width=width;
		this.height=width;
		
		center=MathU.roundTo(center, step);
		
		this.corner=new int[]{center[0]/step-width/(2*step),center[1]/step-width/(2*step)};
		
		byte[][][] points = new byte[2][2][2];
		
		points[0][0]= new byte[]{2,8};
		points[1][0]= new byte[]{8,6};
		points[1][1]= new byte[]{6,4};
		points[0][1]= new byte[]{4,2};
		
		start=new int[]{0,0};
		
		map= new PointMap(points,reference,width);
	}

	
	/* *************************
	 * 
	 * Method
	 * 
	 * ************************** */
	
	/*
	 * (non-Javadoc)
	 * @see com.Al_Nair.physics.destruction.DamageShape#convert()
	 */
	@Override
	public Shape convert(TerrainTile tile) {
		getCoord().setLocal(corner[0]*refStep,corner[1]*refStep);
		return new Rectangle(getCoord().x(), getCoord().y(), width+1, height+1);
		
	}

	/* *************************
	 * 
	 * Getter
	 * 
	 * ************************** */

	/** @return the width */
	public int getWidth() {

		return width;
	}

	/** @return the height */
	public int getHeight() {

		return height;
	}

	/** Starts is the corner for a rectangle
	 * @return corner point
	 */
	@Override
	public int[] getStart(){
		return start;
	}
	
	
}

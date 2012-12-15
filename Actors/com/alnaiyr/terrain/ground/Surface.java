package com.alnaiyr.terrain.ground;


import org.newdawn.slick.geom.Rectangle;


/**
 * Describes how a surface is made: points, boundaries, and more...
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>Works</em></li>
 *          </ul>
 */
public class Surface {

	/* **********************
	 * 
	 * Variables
	 * 
	 * **************************** */

	private int[] start;
	
	private int[] end;
	
	/* **********************
	 * 
	 * Constructor
	 * 
	 * **************************** */

	/**
	 * build a surface from a relief
	 * @param relief
	 */
	public Surface(int[] start, int[] end){

		this.start=start;
		this.end=end;
	}

	/* **********************
	 * 
	 * Methods
	 * 
	 * **************************** */
	
	
	/* **********************
	 * 
	 * Getters
	 * 
	 * **************************** */
	
	/**
	 * @return local boundaries of the surface, meaning not global coordinates,
	 *         just a local use (no rotation, zoom,...)
	 */
	public Rectangle getBoundaries() {
		//TODO
		return new Rectangle(start[0],start[1],start[0]-end[0],start[1]-end[1]);

	}

	public int[] getStart() {
		
		return start;
	}

	
	public int[] getEnd() {
	
		return end;
	}

	
	/* **********************
	 * 
	 * Setters
	 * 
	 * **************************** */
	

	public void setStart(int[] start) {
	
		this.start = start;
	}

	
	public void setEnd(int[] end) {
	
		this.end = end;
	}


}


package com.alnaiyr.math.geom.shape;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.coordinates.dynamic.Cartesian;
import com.alnaiyr.coordinates.dynamic.ReferencedCoordinate;
import com.alnaiyr.coordinates.utilities.VecU;
import com.alnaiyr.math.MathU;
import com.alnaiyr.math.functions.interpolations.NoiseUtilities;
import com.alnaiyr.terrain.ground.Surface;
import com.alnaiyr.terrain.ground.TerrainTile;

/** This class contains informations of point situation, and is able to find
 * their normal vector, and other useful informations
 * 
 * @author IgoЯ
 * @version 1.6
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>Works</em></li>
 *          <li><em>added enum</em></li>
 *          <li><em>fixed normal vector</em></li>
 *          <li><em>added Debuggable</em></li>
 *          <li><em>no more null pointer on findPrevious, findNext</em></li>
 *          <li><em>added direction getter</em></li>
 *          </ul> */
public class PointMap {
	
	private Cartesian	coord;
	
	/** contains all local coordinates, and the byte value is actually the next
	 * and previous element. if both of them are null, no element */
	protected byte[][][]	points;
	
	/** the step used in every surface n the game instance */
	private  int	step	= 0;
	
	/** width of the surface */
	private int			width;
	
	/** total height (from bottom to top) */
	private int			height;
	
	/* *********************
	 * 
	 * ENUM
	 * 
	 * *************************** */
	
	/** <p>
	 * List all possible position of neighbors to a point, in this format:<br/>
	 * <ul>
	 * <li>(0)(1)(2) 0: index 1:x offset 2:y offset</li>
	 * <li>the index start at zero on the top right corner</li>
	 * <li>P: positive N: Negative O: null</li>
	 * </ul>
	 * </p>
	 * 
	 * @author IgoЯ
	 * @version 1.0 */
	protected static enum Neighbor {
		NULL((byte) 0, (byte) 0, (byte) 0), PP((byte) 1, (byte) 1, (byte) 1), PO((byte) 2, (byte) 1, (byte) 0), PN((byte) 3, (byte) 1, (byte) -1), ON((byte) 4,
				(byte) 0, (byte) -1), NN((byte) 5, (byte) -1, (byte) -1), NO((byte) 6, (byte) -1, (byte) 0), NP((byte) 7, (byte) -1, (byte) 1), OP((byte) 8,
				(byte) 0, (byte) 1);
		
		byte	x;
		
		byte	y;
		
		byte	index;
		
		private Neighbor(byte index, byte x, byte y) {
		
			this.index = index;
			this.x = (x);
			this.y = (y);
		}
		
		static Neighbor findNeigbhor(Vector2f next, PlanVector current, int step) {
		
			Vector2f sub = next.sub(current);
			
			sub.y = MathU.roundTo(sub.y, step);
			sub.x = MathU.roundTo(sub.x, step);
			
			sub.x /= step;
			sub.y /= step;
			
			switch ((int) sub.x) {
				case 1:
					switch ((int) sub.y) {
						case 1:
							return PP;
						case -1:
							return PN;
						case 0:
							return PO;
					}
				case -1:
					switch ((int) sub.y) {
						case 1:
							return NP;
						case -1:
							return NN;
						case 0:
							return NO;
					}
				case 0:
					switch ((int) sub.y) {
						case 1:
							return OP;
						case -1:
							return ON;
						case 0:
							return NULL;
					}
			}
			return null;
		}
		
		static Neighbor findNeigbhor(int index) {
		
			switch (index) {
				case 0:
					return NULL;
				case 1:
					return PP;
				case 2:
					return PO;
				case 3:
					return PN;
				case 4:
					return ON;
				case 5:
					return NN;
				case 6:
					return NO;
				case 7:
					return NP;
				case 8:
					return OP;
			}
			return null;
		}
		
		static Neighbor findNeigbhor(int[] diff) {
		
			switch ((int) diff[0]) {
				case 1:
					switch ((int) diff[1]) {
						case 1:
							return PP;
						case -1:
							return PN;
						case 0:
							return PO;
					}
				case -1:
					switch ((int) diff[1]) {
						case 1:
							return NP;
						case -1:
							return NN;
						case 0:
							return NO;
					}
				case 0:
					switch ((int) diff[1]) {
						case 1:
							return OP;
						case -1:
							return ON;
						case 0:
							return NULL;
					}
			}
			return null;
			
		}
		
		static byte[] getData(int index) {
		
			Neighbor neight = findNeigbhor(index);
			return new byte[] { neight.x, neight.y };
		}
		
	}
	
	/* ****************************
	 * 
	 * Constructor
	 * 
	 * **************************** */
	
	/**
	 * @param list
	 * @param coord
	 * @param step
	 * @param circular
	 * @throws ArrayIndexOutOfBoundsException
	 * @throws NullPointerException
	 */
	public PointMap(List<Vector2f> list, ReferencedCoordinate coord, int step, boolean circular) throws ArrayIndexOutOfBoundsException, NullPointerException {
	
		if (this.step == 0) this.step = step;
		
		this.coord = new Cartesian(0, 0, coord, false);
		
		height = (int) (NoiseUtilities.findLowestPoint(list).y - NoiseUtilities.findHighestPoint(list).y + 1);
		width = list.size();
		// TODO: fix height possible issue
		points = new byte[list.size()][(int) height * 4][2];
		
		Vector2f prev = new Vector2f();
		Vector2f next = new Vector2f();
		Vector2f curr = new Vector2f();
		
		for (int i = 0; i < list.size(); i++) {
			
			curr = list.get(i);
			if (!circular) {
				next = (i + 1 < list.size()) ? new Vector2f(list.get(i + 1)) : new Vector2f(curr);
				prev = (i - 1 >= 0) ? new Vector2f(list.get(i - 1)) : new Vector2f(curr);
			}
			else {
				next = (i + 1 < list.size()) ? new Vector2f(list.get(i + 1)) : new Vector2f(list.get(0));
				prev = (i - 1 >= 0) ? new Vector2f(list.get(i - 1)) : new Vector2f(list.get(list.size() - 1));
			}
			
			byte[] neigh = new byte[2];
			
			neigh[0] = Neighbor.findNeigbhor(next, curr, step).index;
			neigh[1] = Neighbor.findNeigbhor(prev, curr, step).index;
			
			points[(int) (curr.x / step)][(int) (curr.y / step)] = neigh;
			
		}
	}
	
	/**
	 * @param relief
	 * @param coord
	 * @param step
	 * @throws ArrayIndexOutOfBoundsException
	 * @throws NullPointerException
	 */
	public PointMap(HashMap<Integer, Float> relief, ReferencedCoordinate coord, int step) throws ArrayIndexOutOfBoundsException, NullPointerException {
	
		this(NoiseUtilities.convert(relief, step, true), coord, step, false);
	}
	
	/**
	 * @param map
	 */
	public PointMap(PointMap map) {
	
		this.width = map.width;
		this.height = map.height;
	}
	
	/**
	 * @param points
	 * @param coord
	 * @param step
	 */
	public PointMap(byte[][][] points,ReferencedCoordinate coord, int step){
		this.points=points;
		this.coord= new Cartesian(0,0,coord, false);
		this.step=step;
	}
	
	/* *******************************
	 * 
	 * Methods
	 * 
	 * *********************************** */
	
	/** find a point next or previous the i and j choosen, depending on k
	 * 
	 * @param i
	 * @param j
	 * @param k
	 * @return next/ previous */
	private int[] findPoint(int i, int j, int k) {
	
		try {
			Neighbor neighbor = Neighbor.findNeigbhor(points[i][j][k]);
			int[] point = { i + neighbor.x, j + neighbor.y };
			
			return (neighbor != Neighbor.NULL) ? point : null;
		}
		catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
	
	/** Gets the next point index
	 * 
	 * @param i
	 * @param j 
	 * @return the normal vector */
	public int[] findNext(int i, int j) {
	
		return findPoint(i, j, 0);
	}
	
	/** @see #findNext(int, int)
	 * 
	 * @param ij
	 * @return next */
	public int[] findNext(int[] ij) {
	
		return findNext(ij[0], ij[1]);
	}
	
	/** Gets gets the previous point index
	 * 
	 * @param i
	 * @param j 
	 * @return the normal vector */
	public int[] findPrevious(int i, int j) {
	
		return findPoint(i, j, 1);
	}
	
	/** @see #findPrevious(int, int)
	 * 
	 * @param ij
	 * @return previous point*/
	public int[] findPrevious(int[] ij) {

		return findPrevious(ij[0], ij[1]);
	}
	
	/** Gets the normal vector of one point
	 * 
	 * @param i
	 * @param j 
	 * @return the normal vector */
	public byte[] findNormal(int i, int j) {
	
		return findNormal(new int[] { i, j });
	}
	
	/** @see #findNormal(int, int)
	 * 
	 * @param ij
	 * @return normal*/
	public byte[] findNormal(int[] ij) {
	
		return VecU.findNormal(findPrevious(ij), findNext(ij));
	}
	
	/** Finds the direction to the next point
	 * 
	 * @param ij
	 * @return next direction*/
	public byte[] findNextDirection(int[] ij) {	
			return Neighbor.getData(points[ij[0]][ij[1]][0]);
	}
	
	/** Finds the direction to the previous point
	 * 
	 * @param ij
	 * @return previous direction*/
	public byte[] findPreviousDirection(int[] ij) {
	
		return Neighbor.getData(points[ij[0]][ij[1]][1]);
	}
	
	
	/* ********************************
	 * 
	 * Getters
	 * 
	 * *********************************** */
	
	/** Gets the width of the map, not counting steps between points
	 * 
	 * @return width*/
	public int getWidth() {
	
		return width;
	}
	
	/** Gets the total height of this map, not counting step between points
	 * 
	 * @return height */
	public int getHeight() {
	
		return height;
	}
	
	/** Gets the whole point map
	 * 
	 * @return map*/
	public byte[][][] getPoints() {
	
		return points;
	}
	
	/** @param points */
	public void setPoints(byte[][][] points) {
	
		this.points = points;
	}
	
	/** Gets the step parameter: unique for the whole game instance
	 * 
	 * @return the value of step, used for the whole game */
	public int getStep() {
	
		return step;
	}
	
	/** Returns true if the point is on the surface
	 * 
	 * @param point
	 * @return true if inside*/
	public boolean isInside(int[] point) {

		return (points[point[0]][point[1]][0] != 0  || points[point[0]][point[1]][1] != 0 );	
	}
	
	/** Gets the reference coordinate, on the top left corner
	 * 
	 * @return the coordinates */
	public PlanVector getCoord() {
	
		return coord.getReference();
	}
	
	/** Gets the data on one point, meaning in which direction is the next and
	 * the previous
	 * 
	 * @param ij
	 * @return  data*/
	public byte[] getDatas(int[] ij) {
	
		try {
			return points[ij[0]][ij[1]];
		}
		catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
	
	/**
	 * @param ij
	 * @return inverted datas
	 */
	public byte[] getInvertedDatas(int[] ij) {
	
		byte[] pt = { points[ij[0]][ij[1]][1], points[ij[0]][ij[1]][0] };
		return pt;
	}
	
	/* ****************************
	 * 
	 * Setters
	 * 
	 * ****************************** */
	
	/** Sets the reference of the coordinates to a new location. Note that this
	 * allow to use these coordinates for inside manipulation, leaving the
	 * reference still.
	 * 
	 * @param coord */
	public void setCoord(Cartesian coord) {
	
		this.coord.setReference(coord);
	}
	
	/** Sets the data of a point to something else
	 * 
	 * @param i
	 * @param j
	 * @param datas
	 *        to set */
	public void setDatas(int i, int j, byte[] datas) {
	
		try {
			points[i][j] = datas;
		}
		catch (ArrayIndexOutOfBoundsException e) {
		}
	}
	
	/**
	 * @param ij
	 * @param datas
	 */
	public void setDatas(int[] ij, byte[] datas) {
	
		setDatas(ij[0], ij[1], datas);
	}
	
	/** Sets the direction to the next point to a new one
	 * 
	 * @param ij
	 * @param data */
	public void setNext(int[] ij, byte data) {
	
		points[ij[0]][ij[1]][0] = data;
	}
	
	/** Sets the direction to the next point to the new one, if possible.
	 * 
	 * @param next
	 * @param ij
	 * @return true if possible, false otherwise */
	public boolean setNext(int[] ij, int[] next) {
	
		Neighbor bor = Neighbor.findNeigbhor(new int[] { next[0] - ij[0], next[1] - ij[1] });
		if (bor == null) return false;
		else points[ij[0]][ij[1]][0] = bor.index;
		return true;
		
	}
	
	/** Sets the direction to the previous point to a new one
	 * 
	 * @param ij
	 * @param data */
	public void setPrevious(int[] ij, byte data) {
	
		points[ij[0]][ij[1]][1] = data;
	}
	
	/** Sets the direction to the previous point to the new one, if possible.
	 * 
	 * @param ij
	 * @param previous 
	 * @return true if possible, false otherwise */
	public boolean setPrevious(int[] ij, int[] previous) {
	
		Neighbor bor = Neighbor.findNeigbhor(new int[] { previous[0] - ij[0], previous[1] - ij[1] });
		if (bor == null) return false;
		else points[ij[0]][ij[1]][1] = bor.index;
		return true;
		
	}
	
	/** Sets the step to a new value
	 * 
	 * @param step
	 */
	public void setStep(int step){
		this.step=step;
	}
	
	/* **************************
	 * 
	 * Debug
	 * 
	 * *************************** */
	
	/** Should be used in a debuggable object with end / start point TODO: change
	 * in time
	 * 
	 * @param g
	 * @param startPoint
	 * @param tile 
	 * @return num debug
	 **/
	public int debugAlways(Graphics g, int[] startPoint, TerrainTile tile) {
	
		int in = -1;
		g.setLineWidth(4);
		g.setColor(Color.blue);
		int[] current = startPoint;
		int[] next = findNext(current);
		if (next != null) {
			do {
				coord.setLocal(current[0] * getStep(), current[1] * getStep());
				ReferencedCoordinate dir = new Cartesian(next[0] * getStep(), next[1] * getStep(), coord.getReference(), false);
				g.draw(new Line(dir.x(), dir.y(), coord.x(), coord.y()));
				current = next;
				next = findNext(next);
				
				for (Surface sur : tile.getSurfaces()) {
					if (!Arrays.equals(sur.getStart(), startPoint)) {
						if (Arrays.equals(sur.getStart(), current)) {
							in = tile.getSurfaces().indexOf(sur);
							break;
						}
					}
				}
			}
			
			while ( next != null && !Arrays.equals(current, startPoint));
			
			g.setColor(Color.white);
			g.setLineWidth(1);
		}
		return in;
	}
	
	/** Should be used in a debuggable object with end / start point
	 * 
	 * @param g
	 * @param startPoint
	 * @param corner
	 * */
	public void debugAlways(Graphics g, int[] startPoint, int[] corner) {
		g.setLineWidth(4);
		g.setColor(Color.blue);
		int[] current = startPoint;
		int[] next = findNext(current);
		if (next != null) {
			do {
				coord.setLocal((current[0]) * getStep()+corner[0], (current[1]) * getStep()+corner[1]);
				ReferencedCoordinate dir = new Cartesian((next[0]) * getStep()+corner[0], (next[1]) * getStep()+corner[1], coord.getReference(), false);
				g.draw(new Line(dir.x(), dir.y(), coord.x(), coord.y()));
				current = next;
				next = findNext(next);
			}
			while ( next != null && !Arrays.equals(current, startPoint));
		}
		else if(!Arrays.equals(current, startPoint) ){
		}
		g.setColor(Color.white);
		g.setLineWidth(1);
		
	}
	
	/** @see #debugAlways(Graphics, int[], int[])
	 * 
	 * @param g
	 * @param startPoint
	 * @param corner 
	 *         */
	public void debugMode1(Graphics g, int[] startPoint, int[] corner) {
	
		g.setColor(Color.green);
		int[] current = startPoint;
		try {
			do {
				coord.setLocal((current[0]) * getStep()+corner[0], (current[1]) * getStep()+corner[1]);
				g.draw(new Rectangle(coord.x(), coord.y(), 5, 5));
				current = findNext(current);
			}
			while ( current != null && !Arrays.equals(current, startPoint));
		}
		catch (StackOverflowError e) {
			
		}
		g.setColor(Color.white);
		
	}
	
	/** @see #debugAlways(Graphics, int[], int[])
	 * TODO  corner is missing
	 * @param g
	 * @param startPoint 
	 */
	public void debugMode2(Graphics g, int[] startPoint) {
	
		g.setColor(Color.red);
		int[] current = startPoint;
		int i = 0;
		do {
			coord.setLocal(current[0] * getStep(), current[1] * getStep());
		
			try {
			    byte[]norm=findNormal(current);
			    ReferencedCoordinate vec = new Cartesian(norm[0] + current[0] * getStep(), norm[1] + current[1] * getStep(),
					coord.getReference(), false);
			g.draw(new Line(vec.x(), vec.y(), coord.x(), coord.y()));
			i++;
			if (i > 1600) break;
			
			} catch (NullPointerException e) {
			}			
			current = findNext(current);
		}
		while ( current != null && !Arrays.equals(current, startPoint));
		g.setColor(Color.white);

	}
	
	/** @see #debugAlways(Graphics, int[], int[])
	 * 
	 * @param g
	 * @param startPoint
	 * @param corner */
	public void debugMode3(Graphics g, int[] startPoint, int[] corner) {
	
		debugMode1(g, startPoint, new int[]{0,0});
		int[] current = startPoint;
		int i = 0;
		do {
			coord.setLocal(current[0] * getStep(), current[1] * getStep());
			g.setColor(Color.red);
			g.drawString(Integer.toString(i), coord.x(), coord.y());
			g.setColor(Color.white);
			current = findNext(current);
			i++;
		}
		while ( findNext(current) != null && !Arrays.equals(current, startPoint));
	}
	
}

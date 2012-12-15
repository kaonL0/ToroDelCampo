package com.alnaiyr.physics.destruction.shapes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

import com.alnaiyr.coordinates.dynamic.Cartesian;
import com.alnaiyr.math.MathU;
import com.alnaiyr.math.geom.shape.DataPoint;
import com.alnaiyr.math.geom.shape.PointMap;
import com.alnaiyr.terrain.ground.TerrainTile;

/**
 * Describes all damages that can be done by convention, points are following
 * each other by the anti-clockwise order
 * 
 * @author IgoЯ
 * @version 1.8
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>Works</em></li>
 *          <li><em>Added isInside()</em></li>
 *          <li><em>Added point map, corner, simplified class</em></li>
 *          <li><em>Added intersection and contain function</em></li>
 *          <li><em>Added intersection couple detection</em></li>
 *          <li>
 *          <em>Added all intersection and next intersection detection, removed all duplicate methods</em>
 *          </li>
 *          <li><em> Added some "common point" methods</em></li>
 *          <li><em> Transfered most use to DataPoint, created modular size</em>
 *          </li>
 *          </ul>
 *          </p>
 */
public abstract class DamageShape {

	/* **************
	 * 
	 * Variables
	 * 
	 * ************************
	 */
	/**
	 * the corner, whose coordinates are displayed according to the map
	 * precision: (1,1)==(step,step)
	 */
	protected int[] corner = new int[2];

	/** the start/loop point */
	protected int[] start;

	/** all the points of this shape */
	protected PointMap map;

	/** width of the shape */
	protected int width;

	/** height of the shape */
	protected int height;

	protected int refStep;

	/* *********************
	 * 
	 * Constructor
	 * 
	 * *******************************
	 */

	public DamageShape(int width, int height, int step) {

		refStep = step;

		this.width = width;
		this.height = height;
	}

	public DamageShape(DamageShape other) {

		this(other.width, other.height, other.refStep);
	}

	/* *************************
	 * 
	 * Delegate
	 * 
	 * **************************
	 */

	/**
	 * get the direction vector on the point i, useful to know what to do in
	 * case of intersect
	 * 
	 * @param i
	 * @return direction vector
	 */
	public int[] findNext(int i, int j) {

		return map.findNext(i, j);
	}

	/**
	 * Gets the normal vector of one point
	 * 
	 * @param i
	 * @return the normal vector
	 */
	public byte[] findNormal(int i, int j) {

		return map.findNormal(i, j);
	}

	/* *************************
	 * 
	 * Method
	 * 
	 * **************************
	 */

	public boolean isInside(DataPoint point, TerrainTile tile, boolean next) {

		byte nextD = point.getDatas(map)[0];
		byte prevD = point.getDatas(map)[1];

		byte nextTile = next ? tile.getMap().getDatas(
				getPointToRef(point.getCoord()))[0] : tile.getMap().getDatas(
				getPointToRef(point.getCoord()))[1];

		return MathU.findCycleNext(nextTile, nextD, prevD);
	}

	public boolean isOutside(DataPoint point, TerrainTile tile, boolean next) {

		return !isInside(point, tile, next);
	}

	/* *********************************************
	 * 
	 * Find Next
	 * 
	 * ******************************************
	 */

	/**
	 * Finds the next intersection with a terrainTile, based on an
	 * Inside/Outside check
	 * 
	 * @param tile
	 * @param from
	 * @param direction
	 * @return
	 */
	public int[] findNextIntersection(TerrainTile tile, int[] from,
			byte direction) {

		int[] current = direction == 1 ? findNext(from) : findPrevious(from);
		int[] convert = getPointToRef(current);

		int[] intersection = { -1, -1 };

		while (!Arrays.equals(current, from) && from[0] != -1) {
			convert = getPointToRef(current);
			if (tile.intersect(convert)) {
				// makes sure the point is not exactly the same on the surface
				// == fake intersection.
				if (!Arrays.equals(getPoints().getInvertedDatas(current), tile
						.getMap().getDatas(convert))) {
					intersection = current;
					break;
				}
			}

			current = direction == 1 ? findNext(current)
					: findPrevious(current);
		}
		return intersection;
	}

	/**
	 * <p>
	 * Slightly different than next intersection, this functions finds the next
	 * common point, no matter all exception.</br> Very useful when trying to
	 * detect lonely points, or pre-made path.
	 * </p>
	 * 
	 * @param tile
	 * @param from
	 * @param direction
	 * @return
	 */
	public DataPoint findNextCommonPoint(TerrainTile tile, DataPoint from,
			byte direction) {

		DataPoint point = from.clone();
		if (direction == 1)
			point.toNext(map, tile.getMap().getStep());
		else
			point.toPrevious(map, tile.getMap().getStep());

		int[] convert = getPointToRef(point.getCoord());

		DataPoint intersection = new DataPoint(new int[] { -1, -1 },
				from.getNextRef(), from.getPrevRef(), refStep, map.getStep());

		// check if we've already created something here before
		byte[] tileData = tile.getMap().getDatas(convert);

		while (!Arrays.equals(point.getCoord(), from.getCoord())
				&& from.getCoord()[0] != -1) {

			if (tileData[0] != 0 || tileData[1] != 0) {
				intersection = point;
				break;
			}

			if (direction == 1)
				point.toNext(map, tile.getMap().getStep());
			else
				point.toPrevious(map, tile.getMap().getStep());

			convert = getPointToRef(point.getCoord());

			tileData = tile.getMap().getDatas(convert);
		}
		return intersection;
	}

	/* ******************************* Find All ***************************** */

	/**
	 * @param tile
	 * @param from
	 * @param direction
	 * @return
	 */
	public List<DataPoint> findAllCommonPoint(TerrainTile tile, DataPoint from) {

		DataPoint point = from.clone();
		point.toNext(map, tile.getMap().getStep());

		int[] convert = getPointToRef(point.getCoord());

		List<DataPoint> intersections = new ArrayList<DataPoint>(8);

		// check if we've already created something here before
		byte[] tileData = tile.getMap().getDatas(convert);

		while (!Arrays.equals(point.getCoord(), from.getCoord())
				&& from.getCoord()[0] != -1) {

			if (tileData != null && (tileData[0] != 0 || tileData[1] != 0)) {
				intersections.add(point.clone());
			}

			point.toNext(map, tile.getMap().getStep());
			convert = getPointToRef(point.getCoord());
			tileData = tile.getMap().getDatas(convert);
		}

		return intersections;
	}

	/**
	 * Finds all the intersection, and put the result in a list
	 * 
	 * @param tile
	 * @param from
	 * @param to
	 * @param included
	 * @return
	 */
	public List<DataPoint> findAllIntersections(TerrainTile tile, int[] from,
			int[] to) {

		DataPoint point = new DataPoint(from, from, from, refStep,
				map.getStep());
		int[] toConv = new int[] { to[0] * map.getStep() / refStep,
				to[1] * map.getStep() / refStep };
		int[] convert = getPointToRef(point.getCoord());

		ArrayList<DataPoint> intersections = new ArrayList<DataPoint>(6);

		do {
			if (tile.intersect(convert)) {
				// makes sure the point is not exactly the same on the surface
				// == fake intersection.
				if (tile.isTransition(this, point))
					intersections.add(point.clone());

			}
			point.toNext(map, tile.getMap().getStep());
			convert = getPointToRef(point.getCoord());
		} while (!Arrays.equals(point.getCoord(), toConv));
		return intersections;
	}

	/**
	 * Finds all special case and put them in an arrayList
	 * 
	 * @param tile
	 * @param from
	 * @param to
	 * @return
	 */
	public List<List<int[]>> findAllExceptions(TerrainTile tile, int[] from,
			int[] to) {

		DataPoint point = new DataPoint(from, from, from, refStep,
				map.getStep());

		int[] toConv = new int[] { to[0] * map.getStep() / refStep,
				to[1] * map.getStep() / refStep };
		int[] convert = getPointToRef(point.getCoord());

		List<List<int[]>> lists = new ArrayList<List<int[]>>(2);

		ArrayList<int[]> outExceptions = new ArrayList<int[]>(2);
		ArrayList<int[]> diagExceptions = new ArrayList<int[]>(4);

		do {
			checkDirection(diagExceptions, point.getCoord(), tile,
					point.getDatas(map)[0]);

			if (tile.intersect(convert)) {
				if (isOutside(point, tile, true)
						&& isOutside(point, tile, false)) {
					outExceptions.add(point.getCoord().clone());
				}
			}
			point.toNext(map, tile.getMap().getStep());
			convert = getPointToRef(point.getCoord());
		} while (!Arrays.equals(point.getCoord(), toConv));

		lists.add(outExceptions);
		lists.add(diagExceptions);

		return lists;
	}

	/**
	 * Checks if the direction to the next point is a diagonal. is so, checks if
	 * we cross an other one, meaning an intersection not detected.
	 * 
	 * @param point
	 * @param tile
	 * @param direction
	 * @return true if exception found
	 */
	private void checkDirection(List<int[]> points, int[] point,
			TerrainTile tile, byte direction) {

		switch (direction) {
			case 1:
				if (checkDiagonal(point, tile, false, (byte) 3))
					points.add(point.clone());
				break;
			case 7:
				if (checkDiagonal(point, tile, false, (byte) 5))
					points.add(new int[] { point[0] - 1, point[1] });
				break;
			case 3:
				if (checkDiagonal(point, tile, true, (byte) 1))
					points.add(new int[] { point[0], point[1] - 1 });
				break;
			case 5:
				if (checkDiagonal(point, tile, true, (byte) 7))
					points.add(new int[] { point[0] - 1, point[1] - 1 });
				break;
		}
	}

	/**
	 * Checks if the adjacent point is crossing the next direction with its
	 * previous or next direction
	 * 
	 * @param point
	 * @param tile
	 * @param up
	 * @param value
	 * @return
	 */
	private boolean checkDiagonal(int[] point, TerrainTile tile, boolean up,
			byte value) {
		int[] aside;
		int[] aPoint = getPointToRef(point);
		aside = up ? new int[] { aPoint[0], aPoint[1] - 1 } : new int[] {
				aPoint[0], aPoint[1] + 1 };
		if (tile.intersect(aside)) {
			byte[] datas = tile.getMap().getDatas(aside);
			if (datas[0] == value || datas[1] == value) {
				return true;
			}
		}
		return false;
	}

	/* *************************** Transformation *************************** */

	/**
	 * Multiplies the size by a value
	 * 
	 * @param mul
	 */
	public void multiplySize(float mul) {

		width *= mul;
		height *= mul;

		map.setStep((int) (map.getStep() * mul));
	}

	/**
	 * Add an amount to the size
	 * 
	 * @param toAdd
	 */
	public void addSize(int toAdd) {

		width += toAdd;
		height += toAdd;

		map.setStep(map.getStep() + toAdd);
	}

	/**
	 * <p>
	 * Returns a conversion of this damageShape to a Slick's shape.<br/>
	 * <Strong>Note:</Strong><br/>
	 * the shape is returned locally, meaning that it is not placed correctly in
	 * a global coordinate system.
	 * </p>
	 * 
	 * @param tile
	 * 
	 * @return the converted polygon
	 */
	public abstract Shape convert(TerrainTile tile);

	/* *********************** Render ************************* */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.Al_Nair.debug.Debuggable#debugAlways(org.newdawn.slick.Graphics)
	 */

	public void debugAlways(Graphics g) {

		map.debugAlways(g, getStart(), getScaledCorner());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.Al_Nair.debug.Debuggable#debugMode1(org.newdawn.slick.Graphics)
	 */

	public void debugMode1(Graphics g) {

		map.debugMode1(g, getStart(), getScaledCorner());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.Al_Nair.debug.Debuggable#debugMode2(org.newdawn.slick.Graphics)
	 */

	public void debugMode2(Graphics g) {

		map.debugMode2(g, getStart());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.Al_Nair.debug.Debuggable#debugMode3(org.newdawn.slick.Graphics)
	 */

	public void debugMode3(Graphics g) {

		map.debugMode3(g, start, getScaledCorner());
	}

	/* *************************
	 * 
	 * Getters
	 * 
	 * **************************
	 */

	/** @return the corner */
	public int[] getCorner() {

		return corner;
	}

	/** @return the point map */
	public PointMap getPoints() {

		return map;
	}

	/** @return the center local vector for this shape */
	public int[] getCenter() {

		return new int[] { corner[0] + width / 2, corner[1] + height / 2 };
	}

	/** @return the height of this shape */
	public int getHeight() {

		return height;
	}

	/** @return the width of this shape */
	public int getWidth() {

		return width;
	}

	/**
	 * returns the coordinates of the point in a map starting at the coordinates
	 * reference
	 * 
	 * @param point
	 *            the point in local map
	 * @return
	 */
	public int[] getPointToRef(int[] point) {

		return new int[] { point[0] + corner[0], point[1] + corner[1] };
	}

	public int[] getScaledCorner() {

		return new int[] { corner[0] * refStep, corner[1] * refStep };
	}

	/*
	 * public int[] getScaledCenter(){
	 * 
	 * }
	 */

	/* **************************
	 * 
	 * Delegate
	 * 
	 * ************************************
	 */

	/**
	 * Checks if a point in intersecting the point map
	 * 
	 * @param point
	 * @return
	 */
	public boolean intersect(int[] point) {

		try {
			return map.isInside(point);
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}

	/**
	 * Finds a normal to a point
	 * 
	 * @param ij
	 * @return
	 */
	public byte[] findNormal(int[] ij) {

		return map.findNormal(ij);
	}

	/**
	 * Gets the top left corner coordinates (not necessarily a point)
	 * 
	 * @return the corner coordinates
	 */
	public Cartesian getCoord() {

		return (Cartesian) map.getCoord();
	}

	/**
	 * Finds the direction to the next point
	 * 
	 * @param ij
	 * @return the direction vector
	 */
	public byte[] findNextDirection(int[] ij) {

		return map.findNextDirection(ij);
	}

	/**
	 * Finds the direction to the previous point
	 * 
	 * @param ij
	 * @return the direction vector
	 */
	public byte[] findPreviousDirection(int[] ij) {

		return map.findPreviousDirection(ij);
	}

	/**
	 * Gets the end point local position
	 * 
	 * @return the end point coords
	 */
	public int[] getEnd() {

		return start;
	}

	/**
	 * Gets the start point local position
	 * 
	 * @return the start point coords
	 */
	public int[] getStart() {

		return start;
	}

	/** @return the number of point */
	public int getPointCount() {

		return map.getPoints().length;
	}

	/**
	 * Gets the next point of this damage shape, or first when on end
	 * 
	 * @param ij
	 * @return the next point
	 */
	public int[] findNext(int[] ij) {

		return map.findNext(ij);
	}

	/**
	 * Gets the previous point of this damage shape, or end when on first
	 * 
	 * @param ij
	 * @return
	 */
	public int[] findPrevious(int[] ij) {

		return map.findPrevious(ij);
	}

	public PointMap getMap() {

		return map;
	}

	/* **************************
	 * 
	 * Setters
	 * 
	 * ************************************
	 */

	public void setCoord(Cartesian coord, TerrainTile tile) {

		this.map.setCoord(coord);
		corner[0] = MathU.roundTo(coord.getXL(), tile.getMap().getStep());
		corner[1] = MathU.roundTo(coord.getYL(), tile.getMap().getStep());
	}

	public void setCorner(int[] corner, boolean scale, TerrainTile tile) {

		if (scale) {
			this.corner[0] = corner[0] / tile.getMap().getStep();
			this.corner[1] = corner[1] / tile.getMap().getStep();
		} else {
			this.corner[0] = corner[0];
			this.corner[1] = corner[1];
		}
	}

	public void setCorner(int i, int j, boolean scale, TerrainTile tile) {

		setCorner(new int[] { i, j }, scale, tile);
	}

	public void setCenter(int[] center, boolean scale, TerrainTile tile) {

		if (!scale)
			setCorner(center[0] - width / (2 * tile.getMap().getStep()),
					center[1] - height / (2 * tile.getMap().getStep()), false,
					tile);
		else
			setCorner(center[0] - width / 2, center[1] - height / 2, true, tile);

	}

	public void setWidth(int width) {
		this.width = width;
		height = width;
		map.setStep(width);
	}

}

package com.alnaiyr.terrain.ground;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.coordinates.dynamic.Cartesian;
import com.alnaiyr.display.renderables.Renderable;
import com.alnaiyr.math.functions.interpolations.NoiseUtilities;
import com.alnaiyr.math.geom.shape.DataPoint;
import com.alnaiyr.math.geom.shape.PointMap;
import com.alnaiyr.physics.destruction.shapes.DamageRectangle;
import com.alnaiyr.physics.destruction.shapes.DamageShape;
import com.alnaiyr.utilities.debug.DB;

/**
 * Contains all the informations for the drawing of a terrain
 * 
 * @author IgoЯ
 * @version 1.7
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>Works</em></li>
 *          <li><em>Simplified</em></li>
 *          <li><em>Works with surface now</em></li>
 *          <li><em>Works with point map now</em></li>
 *          <li><em>Works with DataPoint inside methods</em></li>
 *          <li><em>removed duplicates, made it more POO</em></li>
 *          <li><em>removed duplicates, made it more POO</em></li>
 *          <li><em>Added some destroy methods, exception handler</em></li>
 *          </ul>
 *          </p>
 */
public class TerrainTile implements Renderable {

	/* ****************************
	 * 
	 * Variables
	 * 
	 * *******************************
	 */

	/** what is the highest point of the image */
	private float highest = 0;

	/** all the surfaces contained by this tile */
	private PointMap map;

	/** the Alpha Mask image */
	private Image alphaMask;

	/** All the surface, meaning all the continuous lines for this tile */
	private List<Surface> surfaces;

	private HashMap<Integer, HashMap<Integer, Integer>> surf;

	/** Damage shape used for debuggin' */
	private DamageRectangle debug;

	/* ****************************
	 * 
	 * Constructor
	 * 
	 * *******************************
	 */

	/**
	 * Creates an Terrain Tile with a reference coordinate
	 * 
	 * @param refCoord
	 *            the reference coordinates
	 * @param relief
	 * @param depth
	 * @param step
	 *            of the mapping
	 * @param container
	 * @param maxLength
	 * 
	 * @param heightReference
	 *            where should start the relief calculation
	 */
	public TerrainTile(Cartesian refCoord, HashMap<Integer, Float> relief,
			int depth, int step, final GameContainer container)
			throws ArrayIndexOutOfBoundsException, NullPointerException {

		// ensure that we are not using more memory
		if (alphaMask != null)
			try {
				alphaMask.destroy();
			} catch (SlickException e) {
				e.printStackTrace();
			}

		// creates image
		// setAlphaMask(FunctionRenderer.generateImage(relief, depth,
		// container));

		highest = NoiseUtilities.findHighestPoint(relief).y;

		List<Vector2f> convert = NoiseUtilities.convert(relief, step, true);
		map = new PointMap(convert, refCoord, step, false);

		surf = new HashMap<>(map.getWidth());
		float a = relief.get(0) / step;
		addSurface(new int[] { 0, (int) a });

		surfaces = new ArrayList<>(20);
		surfaces.add(new Surface(new int[] { (int) convert.get(0).x / step,
				(int) convert.get(0).y / step }, new int[] {
				(int) convert.get(convert.size() - 1).x / step,
				(int) convert.get(convert.size() - 1).y / step }));

		debug = new DamageRectangle(new int[] { 0, 0 }, getMap().getStep(),
				refCoord, 2 * getMap().getStep());
	}

	/* ***********************
	 * 
	 * Methods
	 * 
	 * *************************
	 */

	public void addSurface(int[] point) {

		try {
			surf.get(point[0]).put(point[1], 1);
		} catch (NullPointerException e) {
			surf.put(point[0], new HashMap<Integer, Integer>(400));
			surf.get(point[0]).put(point[1], 1);
		}
	}

	/* *************************
	 * 
	 * Destroys
	 * 
	 * *****************************
	 */

	/**
	 * Destroys the terrain until an intersection is found again
	 * 
	 * @param damage
	 * @param from
	 * @param intersections
	 * @param dest
	 * @return
	 */
	public boolean destroyUntilNext(DamageShape damage, DataPoint from,
			List<DataPoint> intersections, byte dest) {

		if (dest != 0) {
			// makes sure we destroy in the right direction
			int[] current = dest == 1 ? findNext(damage.getPointToRef(from
					.getCoord())) : findPrevious(damage.getPointToRef(from
					.getCoord()));
			int[] nextInter = findNextIntersection(damage,
					damage.getPointToRef(from.getCoord()), dest, intersections);

			if (nextInter[0] != -1) {
				while (!Arrays.equals(current, nextInter)) {
					if (dest == 1) {
						map.setNext(findPrevious(current), (byte) 0);
						map.setPrevious(current, (byte) 0);
					} else {
						map.setPrevious(findNext(current), (byte) 0);
						map.setNext(current, (byte) 0);
					}
					current = dest == 1 ? findNext(current)
							: findPrevious(current);
				}
				destroyFirstAndLast(current, dest);
				return true;
			}
		}
		return false;
	}

	public void destroyAll(DamageShape damage, DataPoint from) {

		int[] current = damage.getPointToRef(from.getCoord());
		DB.test(current);
		DB.test(map.getDatas(current));
		while (current != null && findPrevious(current) != null) {
			DB.test();
			map.setNext(findPrevious(current), (byte) 0);
			map.setPrevious(current, (byte) 0);
			current = findNext(current);
		}
	}

	/**
	 * Destroys the first and last intersections found
	 * 
	 * @param current
	 * @param direction
	 */
	private void destroyFirstAndLast(int[] current, byte direction) {

		if (direction == 1) {
			map.setNext(findPrevious(current), (byte) 0);
			map.setPrevious(current, (byte) 0);
		} else {
			map.setPrevious(findNext(current), (byte) 0);
			map.setNext(current, (byte) 0);
		}
	}

	/**
	 * <p>
	 * Checks if we have to destroy existing surface point starting at a
	 * intersection, of if we should save it to a new surface.<br/>
	 * Note that we need to check both side of the damage, as we don't know in
	 * which direction the surface is going
	 * </p>
	 * 
	 * @param shape
	 * @param surface
	 * @param point
	 * @return 1 if next is inside, -1 if previous, 0 if none
	 */

	public byte getOutsideDirection(DamageShape shape, DataPoint point) {

		if (shape.isOutside(point, this, true))
			return -1;
		if (shape.isOutside(point, this, false))
			return 1;
		return 0;
	}

	/**
	 * check the destroy possibilities, as an inside point of view
	 * 
	 * @param shape
	 * @param point
	 * @return
	 */
	public byte getInsideDirection(DamageShape shape, DataPoint point) {

		if (shape.isInside(point, this, true))
			return 1;
		if (shape.isInside(point, this, false))
			return -1;
		return 0;
	}

	/**
	 * Return true if the point is a transition between what is outside the
	 * DamageShape, and what is inside.
	 * 
	 * @param shape
	 * @param point
	 * @return
	 */
	public boolean isTransition(DamageShape shape, DataPoint point) {

		if (shape.isInside(point, this, true)) {
			if (!shape.isInside(point, this, false))
				return true;
			return false;
		}
		if (shape.isInside(point, this, false))
			return true;
		return false;
	}

	/*
	 * ***************************** Create
	 * *******************************************
	 */

	/**
	 * Create a new terrain way until an older point is found
	 * 
	 * @param damage
	 * @param from
	 * @param direction
	 * @return
	 */
	public boolean createUntilNext(DamageShape damage, DataPoint from,
			byte direction) {

		if (direction != 0) {
			DataPoint current = from.clone();
			int[] convert = damage.getPointToRef(current.getCoord());

			DataPoint nextInter = damage.findNextCommonPoint(this, current,
					direction);

			setFirstAndLast(damage, from, nextInter, direction, current);

			while (!Arrays.equals(current.getCoord(), nextInter.getCoord())) {

				convert = damage.getPointToRef(current.getCoord());
				map.setDatas(convert, current.getInvertedDatas(damage.getMap()));

				if (direction == 1)
					current.toNext(damage.getMap(), map.getStep());
				else
					current.toPrevious(damage.getMap(), map.getStep());
			}
			return true;
		}
		return false;
	}

	/**
	 * Sets the data of the first intersection, and next intersection found
	 * 
	 * @param damage
	 * @param from
	 * @param nextInter
	 * @param direction
	 * @param current
	 */
	private void setFirstAndLast(DamageShape damage, DataPoint from,
			DataPoint nextInter, byte direction, DataPoint current) {

		if (direction == 1) {
			map.setPrevious(damage.getPointToRef(from.getCoord()),
					from.getDatas(damage.getMap())[0]);
			current.toNext(damage.getMap(), map.getStep());
			map.setNext(damage.getPointToRef(nextInter.getCoord()),
					nextInter.getDatas(damage.getMap())[1]);
		} else {
			map.setNext(damage.getPointToRef(from.getCoord()),
					from.getDatas(damage.getMap())[1]);
			current.toPrevious(damage.getMap(), map.getStep());
			map.setPrevious(damage.getPointToRef(nextInter.getCoord()),
					nextInter.getDatas(damage.getMap())[0]);
		}
	}

	/**
	 * <p>
	 * Checks is we can save damages shape point for the new Surface:<br/>
	 * If normal to surface and next shape point direction are opposed, ok, else
	 * no.
	 * </p>
	 * 
	 * @param shape
	 * @param point
	 *            the point on the shape
	 * @param point2
	 *            the point on the surface
	 * @return 1 if next can be created, -1 if previous, 0 if none
	 */
	public byte canCreate(DamageShape shape, DataPoint point) {
		/*
		 * byte[] dir = shape.findNextDirection(point.getPrevRef());
		 * GV.test("next dir"); GV.test(dir); byte[] dir2 =
		 * shape.findPreviousDirection(point.getNextRef()); GV.test("prev dir");
		 * GV.test(dir2);
		 * 
		 * byte[] norm = findNormal(shape.getPointToRef(point.getCoord()));
		 * GV.test("normal"); GV.test(norm);
		 * 
		 * boolean right = MathU.isOpposed(dir, norm); boolean left =
		 * MathU.isOpposed(dir2, norm);
		 * 
		 * if (right && !left) return 1; if (!right && left) return -1; else
		 * return checkRightAngle(shape, point, dir, dir2, norm);
		 */
		if (shape.isInside(point, this, true))
			return -1;
		else if (shape.isInside(point, this, false))
			return 1;
		else
			return 0;
	}

	/**
	 * Special case handling, when normal and direction forms a right angle: we
	 * then need to check in which way the angle is oriented, to determine the
	 * direction to take
	 * 
	 * @param shape
	 * @param point
	 * @param dir
	 * @param dir2
	 * @param norm
	 * @return
	 */
	@SuppressWarnings("unused")
	private byte checkRightAngle(DamageShape shape, DataPoint point,
			byte[] dir, byte[] dir2, byte[] norm) {

		// last test on angles
		Vector2f vec1 = new Vector2f(dir[0], dir[1]);
		Vector2f vec2 = new Vector2f(norm[0], norm[1]);

		double st = vec1.getTheta() - vec2.getTheta();

		// check right angle on first direction
		if (st == 90 || st == -270)
			return -1;
		else if (st == -90 || st == 270)
			return 1;

		// check right angle on second direction
		Vector2f vec3 = new Vector2f(dir2[0], dir2[1]);

		st = vec3.getTheta() - vec2.getTheta();

		if (st == 90 || st == -270)
			return -1;
		else if (st == -90 || st == 270)
			return 1;

		// check special cases otherwise
		return checkDoubleException(shape, point);

	}

	/**
	 * Handles the double exception, appearing when both of the direction could
	 * be right: It gets the right one
	 * 
	 * @param shape
	 * @param point
	 * @return
	 */
	private byte checkDoubleException(DamageShape shape, DataPoint point) {

		DB.test("double exception");

		int[] next = findNext(shape.getPointToRef(point.getCoord()));

		int[] prev = findPrevious(shape.getPointToRef(point.getCoord()));

		int[] nextS = shape.getPointToRef(point.getNext(shape.getMap(),
				map.getStep()).getCoord());

		if (!Arrays.equals(next, nextS) && !Arrays.equals(prev, nextS))
			return -1;
		int[] prevS = shape.getPointToRef(point.getPrevious(shape.getMap(),
				map.getStep()).getCoord());
		if (!Arrays.equals(next, prevS) && !Arrays.equals(prev, prevS))
			return 1;
		return 0;
	}

	// //////////////////////////////// Other ///////////////////////////////

	/**
	 * Finds the next intersections, following a direction. Note that this
	 * should happen inside the damage shape, limiting the max number of
	 * iteration drastically
	 * 
	 * @param shape
	 * @param from
	 * @param direction
	 * @param list
	 * @return
	 */
	public int[] findNextIntersection(DamageShape shape, int[] from,
			byte direction, List<DataPoint> list) {

		int[] current = direction == 1 ? findNext(from) : findPrevious(from);

		int[] convert;
		int[] intersection = { -1, -1 };

		if (current != null) {
			convert = getPointToSub(current, shape.getCorner());
			while (current != null && from[0] != -1
					&& !Arrays.equals(current, from)) {
				convert = getPointToSub(current, shape.getCorner());
				for (DataPoint point : list) {

					if (Arrays.equals(point.getCoord(), convert)) {
						// makes sure the point is not exactly the same on the
						// surface
						// == fake intersection.
						if (getOutsideDirection(shape, point) != 0
								&& getInsideDirection(shape, point) != 0) {
							intersection = current;
							return intersection;
						}
					}
				}

				current = direction == 1 ? findNext(current)
						: findPrevious(current);
			}
		}
		return intersection;
	}

	/* ***********************
	 * 
	 * Debug / render
	 * 
	 * *************************
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.Al_Nair.entity.Renderable#render(org.newdawn.slick.Graphics)
	 */
	@Override
	public void render(Graphics g, GameContainer container) {

		// ensure that the image is well positioned
		// setImageCoord();
		// // alphaMask.draw(getLocCoord().x(), getLocCoord().y());
		setNormal();
		debugAlways(g);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.Al_Nair.debug.Debuggable#debug(org.newdawn.slick.Graphics,
	 * com.Al_Nair.debug.DebugMode)
	 */
	public void debugMode1(Graphics g) {

		/*
		 * for (Surface surface : surfaces) map.debugMode1(g,
		 * surface.getStart(), new int[] { 0, 0 });
		 */

		for (Entry<Integer, HashMap<Integer, Integer>> cMap : surf.entrySet()) {
			for (Integer ind : cMap.getValue().keySet())
				map.debugMode1(g, new int[] { cMap.getKey(), ind }, new int[] {
						0, 0 });
		}
	}

	public void debugAlways(Graphics g) {

		for (Entry<Integer, HashMap<Integer, Integer>> cMap : surf.entrySet()) {
			for (Integer ind : cMap.getValue().keySet())
				map.debugAlways(g, new int[] { cMap.getKey(), ind }, new int[] {
						0, 0 });
		}
	}

	public void debugMode2(Graphics g) {

		for (Entry<Integer, HashMap<Integer, Integer>> cMap : surf.entrySet()) {
			for (Integer ind : cMap.getValue().keySet())
				map.debugMode2(g, new int[] { cMap.getKey(), ind });
		}
	}

	public void debugMode3(Graphics g) {
		for (Entry<Integer, HashMap<Integer, Integer>> cMap : surf.entrySet()) {
			for (Integer ind : cMap.getValue().keySet())
				map.debugMode3(g, new int[] { cMap.getKey(), ind }, new int[] {
						0, 0 });
		}
	}

	/* ***********************
	 * 
	 * Getters
	 * 
	 * *************************
	 */

	public Image getAlphaMask() {

		return alphaMask;
	}

	/** @return the highest */
	public float getHighest() {

		return highest;
	}

	/** @return */
	/*
	 * public Coordinate getCoord() {
	 * 
	 * return map.getCoord().getReference(); }
	 */

	public PlanVector getLocCoord() {

		return map.getCoord();
	}

	public PointMap getMap() {

		return map;
	}

	/** @return the debug */
	public DamageRectangle getDebug() {

		return debug;
	}

	/* ***********************
	 * 
	 * Setters
	 * 
	 * *************************
	 */

	public void setAlphaMask(Image alphaMask) {

		this.alphaMask = alphaMask;
	}

	/**
	 * @param highest
	 *            the highest to set
	 */
	public void setHighest(float highest) {

		this.highest = highest;
	}

	public List<Surface> getSurfaces() {

		return surfaces;
	}

	public void setMap(PointMap map) {

		this.map = map;
	}

	/*
	 * public void setCoord(Coordinate coord) {
	 * 
	 * map.getCoord().setReference(coord); }
	 */

	public void setSurfaces(List<Surface> surfaces) {

		this.surfaces = surfaces;
	}

	/** Sets the coordinates to the image one */
	public void setImageCoord() {

		map.getCoord().setLocal(0, highest);
	}

	/** Sets back the coordinate to normal */
	public void setNormal() {

		map.getCoord().setLocal(0, 0);
	}

	/* **********************
	 * 
	 * Delegate
	 * 
	 * ****************************
	 */

	/**
	 * Gets the width of the point map of this shape.
	 * 
	 * @return the width of the map
	 */
	public int getWidth() {

		return map.getWidth() * getMap().getStep();
	}

	/**
	 * @return the height of the surface
	 * @see com.alnaiyr.math.geom.shape.PointMap#getHeight()
	 */
	public int getHeight() {

		return map.getHeight() * getMap().getStep();
	}

	/**
	 * Finds the next point related to this one
	 * 
	 * @param ij
	 * @return the next point
	 */
	public int[] findNext(int[] ij) {

		return map.findNext(ij);
	}

	/**
	 * Finds the previous point related to this one
	 * 
	 * @param ij
	 * @return the rpevious point
	 */
	public int[] findPrevious(int[] ij) {

		return map.findPrevious(ij);
	}

	/**
	 * Gets the normal vector on this point
	 * 
	 * @param ij
	 * @return the normal vector
	 */
	public byte[] findNormal(int[] ij) {

		return map.findNormal(ij);
	}

	/**
	 * find the direction vector to the next point
	 * 
	 * @param ij
	 * @return the direction vector
	 */
	public byte[] findNextDirection(int[] ij) {

		return map.findNextDirection(ij);
	}

	/**
	 * find the direction vector to the next point
	 * 
	 * @param ij
	 * @return the direction vector
	 */
	public byte[] findPreviousDirection(int[] ij) {

		return map.findPreviousDirection(ij);
	}

	/**
	 * Check if this point is inside the shape, meaning is start or end, or is
	 * related to someone else
	 * 
	 * @param point
	 * @return the truth
	 */
	public boolean intersect(int[] point) {

		try {
			return map.isInside(point);
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}

	/**
	 * Gets a point regarding a new reference
	 * 
	 * @param point
	 * @param subcorner
	 * @return
	 */
	public int[] getPointToSub(int[] point, int[] subcorner) {

		return new int[] { point[0] - subcorner[0], point[1] - subcorner[1] };
	}

}

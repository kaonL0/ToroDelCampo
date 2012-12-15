package com.alnaiyr.math.functions.interpolations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.newdawn.slick.geom.Vector2f;

import com.alnaiyr.math.MathU;

/**
 * This class gives some useful function to use for noises as ArrayList
 * containing vectors.<br/>
 * For now,it can create alpha mask, and find extreme points,
 * 
 * @author IgoЯ
 * @version 1.5
 * 
 *          <p>
 *          <strong>Version change:</strong><br/>
 *          <ul>
 *          <li><em>4 interpolation coded so far</em></li>
 *          <li><em>4 smoothing also</em></li>
 *          <li><em>adding method</em></li>
 *          <li><em>now static</em></li>
 *          <li><em>fixed findLength()</em></li>
 *          <li><em>Now using Interpolater class</em></li>
 *          <li><em>Fixed high octaves bug</em></li>
 *          <li><em>Fixed findStep bug (sharp relief bug)</em></li>
 *          </ul>
 *          </p>
 */
public class NoiseUtilities {

	/* **********************************************************************************************************************************
	 * 
	 * Utilities
	 * 
	 * *********************************************************************
	 */

	/**
	 * Returns a new HashMap being a combination of others
	 * 
	 * @param relief
	 *            to add (as much as needed) they need to be always the same
	 *            size
	 * @return the added map
	 * @throws IndexOutOfBoundsException
	 *             in case not the same amount of points in the hashmaps
	 */
	@SafeVarargs
	public static HashMap<Integer, Float> add(Map<Integer, Float>... relief)
			throws IndexOutOfBoundsException {

		HashMap<Integer, Float> add = new HashMap<>();

		for (Integer key : relief[0].keySet()) {
			add.put(key, relief[0].get(key) / relief.length);
		}

		for (int i = 1; i < relief.length; i++) {
			for (Integer key : relief[0].keySet()) {
				add.put(key, add.get(key) + relief[i].get(key) / relief.length);
			}
		}
		return add;
	}

	/* ****************************************************************************
	 * 
	 * Converting
	 * 
	 * *****************************************************************
	 */
	/**
	 * <p>
	 * Converts a map to a list.<br/>
	 * Note that this conversion can be super long (as long as the map is),
	 * consequently, this method should only be used during a loading time, not
	 * in real time.
	 * </p>
	 * 
	 * @param relief
	 * @return converted list
	 */
	public static List<Vector2f> convert(Map<Integer, Float> relief) {

		List<Vector2f> list = new ArrayList<>(relief.size());

		for (Integer key : relief.keySet()) {
			list.add(new Vector2f(key, relief.get(key)));
		}
		return list;
	}

	/**
	 * This conversion takes way more time, as it respects more rules with step
	 * between points: expect to have a super slow generation, but super nice
	 * and clean list.
	 * 
	 * @param relief
	 * @param step
	 * @param fix
	 *            : destroys every unnecessary points, add points in gaps. Not
	 *            always a god idea when combining two lists that are converted
	 *            (better do it at the end).
	 * @return converted list
	 */
	public static List<Vector2f> convert(Map<Integer, Float> relief, int step,
			boolean fix) {

		TreeMap<Integer, Float> map = new TreeMap<>(relief);

		List<Vector2f> list = new ArrayList<>(relief.size());

		for (Integer key : map.keySet()) {

			Vector2f val = new Vector2f(MathU.roundTo(key, step),
					MathU.roundTo(map.get(key), step));

			if (!list.contains(val)) {
				list.add(val);
			}
		}

		if (fix)
			list = NoiseUtilities.correctList(
					NoiseUtilities.correctList(list, step), step);

		return list;
	}

	/**
	 * Removes all unnecessary points from a list
	 * 
	 * @param list
	 * @param step
	 * @return converted list
	 */
	public static List<Vector2f> correctList(List<Vector2f> list, int step) {

		List<Vector2f> temp = new LinkedList<>(list);
		for (Vector2f vec : list) {
			// TODO: Hard coded, not pretty.
			if (vec != list.get(list.size() - 1)) {
				// if can find a 90deg angle
				boolean top = temp.contains(new Vector2f(vec.x, vec.y - step));
				boolean topl = list.contains(new Vector2f(vec.x, vec.y - step));

				// if can fin a corner near
				boolean topleft = temp.contains(new Vector2f(vec.x - step,
						vec.y - step));

				boolean left = temp.contains(new Vector2f(vec.x - step, vec.y));
				boolean leftl = list
						.contains(new Vector2f(vec.x - step, vec.y));

				boolean leftbottom = temp.contains(new Vector2f(vec.x - step,
						vec.y + step));

				boolean bottom = temp
						.contains(new Vector2f(vec.x, vec.y + step));
				boolean bottoml = list.contains(new Vector2f(vec.x, vec.y
						+ step));

				boolean bottomright = temp.contains(new Vector2f(vec.x + step,
						vec.y + step));

				boolean right = temp
						.contains(new Vector2f(vec.x + step, vec.y));
				boolean rightl = list
						.contains(new Vector2f(vec.x + step, vec.y));

				boolean righttop = temp.contains(new Vector2f(vec.x + step,
						vec.y - step));

				// if isolated from other points
				boolean isolatedtop = top && !rightl && !leftl && !bottoml
						&& !bottomright && !leftbottom;
				boolean isolatedbottom = bottom && !rightl && !leftl && !topl
						&& !topleft && !righttop;

				if ((top && left || right && top || bottom && right && left)
						&& !(topl && leftl && bottoml || topl && rightl
								&& bottoml)) {
					// GV.test("corner test "+ list.indexOf(vec));
					temp.remove(vec);
				} else if (isolatedtop || isolatedbottom) {
					// GV.test("corner test "+ list.indexOf(vec));
					temp.remove(vec);
				}
			}
		}
		temp = NoiseUtilities.fillGap(temp, step);

		return temp;
	}

	/**
	 * Fills all the gaps created during conversion
	 * 
	 * @param list
	 * @param step
	 * @return
	 */
	private static List<Vector2f> fillGap(List<Vector2f> list, int step) {

		List<Vector2f> temp = new ArrayList<>();
		Vector2f prev = new Vector2f(-1, -1);

		for (Vector2f vec : list) {
			try {
				prev = new Vector2f(list.get(list.indexOf(vec) - 1));
			} catch (IndexOutOfBoundsException e) {
			}

			int diff = (int) (vec.y - prev.y);
			if (Math.abs(diff) <= step || prev.x == -1) {
				temp.add(vec);
			} else {
				Vector2f mid = new Vector2f(vec.x, prev.y + step
						* Math.signum(diff));

				while (mid.y - vec.y != 0) {
					if (!temp.contains(mid))
						temp.add(mid);
					mid = new Vector2f(vec.x, mid.y + step * Math.signum(diff));
				}
				temp.add(vec);
			}
		}
		return temp;
	}

	/**
	 * Finds the average value of this map
	 * 
	 * @param relief
	 * @return average value
	 * */
	public static float findAverage(Map<Integer, Float> relief) {

		float average = 0;

		for (Integer key : relief.keySet()) {
			average += relief.get(key);
		}
		return average / relief.size();
	}

	/**
	 * @see #findHighestPoint(Map)
	 * 
	 * @param relief
	 * @return coordinates of the highest point
	 */
	public static Vector2f findHighestPoint(List<Vector2f> relief) {

		Vector2f highest = new Vector2f(0, 100000);
		for (Vector2f vec : relief) {
			if (vec.y < highest.y)
				highest = new Vector2f(vec);
		}
		return highest;
	}

	/* ***********************************************************************************
	 * 
	 * Smoothing
	 * 
	 * *****************************************************************
	 */

	/**
	 * Finds the highest point of the algorithm's curve. The point is calculated
	 * from (0,0), meaning that the shorter the distance, the higher the point.
	 * 
	 * @param relief
	 *            the array containing all the noise's points
	 * @return a Vector 2f containing the highest point coordinates
	 */
	public static Vector2f findHighestPoint(Map<Integer, Float> relief) {

		Vector2f highest = new Vector2f(0, 10000);
		for (Integer key : relief.keySet())
			if (relief.get(key) < highest.y)
				highest = new Vector2f(key, relief.get(key));

		return highest;
	}

	/**
	 * Finds the length of a noise function, based on its keys
	 * 
	 * @param relief
	 * @return the length we're talking about
	 */
	public static int findLength(Map<Integer, Float> relief) {

		int length = 0;
		for (Integer key : relief.keySet())
			if (key > length)
				length = key;

		return length + 2;
	}

	/**
	 * @see #findLowestPoint(Map)
	 * 
	 * @param relief
	 * @return coordinates of the lowest point
	 */
	public static Vector2f findLowestPoint(List<Vector2f> relief) {

		Vector2f lowest = new Vector2f(0, 0);
		for (Vector2f vec : relief)
			if (vec.y > lowest.y)
				lowest = new Vector2f(vec);

		return lowest;
	}

	/**
	 * Finds the lowest point of the algorithm's curve.<br/>
	 * The point is calculated from (0,0). Meaning that the higher the distance,
	 * the lower the point.
	 * 
	 * @param relief
	 *            the array containing all the noise's points
	 * @return a Vector 2f containing the highest point coordinates
	 */
	public static Vector2f findLowestPoint(Map<Integer, Float> relief) {

		Iterator<Float> it = relief.values().iterator();
		Float first = it.next();

		Vector2f lowest = new Vector2f(0, first);

		for (Integer key : relief.keySet()) {
			if (relief.get(key) > lowest.y)
				lowest = new Vector2f(key, relief.get(key));
		}
		return lowest;
	}

	/**
	 * Finds the step between values in a noise function
	 * 
	 * @param relief
	 * @return the step value, in pixels
	 */
	public static int findStep(Map<Integer, Float> relief) {

		TreeMap<Integer, Float> tree = new TreeMap<>(relief);
		if (tree.containsKey(0))
			return tree.higherKey(0);
		return 0;
	}

	/* ********************************************************************************************
	 * 
	 * Interpolations
	 * 
	 * ************************************************************
	 */

	/**
	 * Returns an interpolated copy of the map given, with a chosen algorithm
	 * 
	 * @param relief
	 * @param interpolation
	 * @return all point interpolated
	 */
	public static HashMap<Integer, Float> interpolate(
			Map<Integer, Float> relief, Interpolation interpolation) {

		switch (interpolation) {
			case CONSTANT:
				return NoiseUtilities.interpolateNeighbor(relief);
			case LINEAR:
				return NoiseUtilities.interpolateLinear(relief);
			case COSINUS:
				return NoiseUtilities.interpolateCosinus(relief);
			case CUBIC:
				// TODO: not working
				return NoiseUtilities.interpolateCubic(relief);
			case BEZIER:
				// TODO
				break;
			case PERLIN:
				// TODO
				break;
		}
		return null;
	}

	/**
	 * Interpolate with cos function between points for a smooth noise
	 * 
	 * @return the height of the point defined by parameters
	 */
	private static HashMap<Integer, Float> interpolateCosinus(
			Map<Integer, Float> relief) {

		HashMap<Integer, Float> cacheRelief = new HashMap<>(relief);

		int step = NoiseUtilities.findStep(relief);
		for (Integer key : relief.keySet()) {
			relief.get(key);
			if (relief.containsKey(key + step)) {
				relief.get(key + step);
				for (int i = 1; i < step; i++) {
				}
			}

		}
		return cacheRelief;
	}

	/**
	 * Interpolate using a cubic function, needs 4 points as parameters for a
	 * smoother noise
	 * 
	 * @return the height of the point defined by parameters
	 */
	private static HashMap<Integer, Float> interpolateCubic(
			Map<Integer, Float> relief) {

		HashMap<Integer, Float> cacheRelief = new HashMap<>(relief);
		float step = NoiseUtilities.findStep(relief);
		for (Integer key : relief.keySet()) {
			relief.get(key);
			if (relief.containsKey(key + step) & relief.containsKey(key - step)
					& relief.containsKey(key + step + step)) {
				relief.get(key - step);
				relief.get(key + step);
				relief.get(key + step * 2);

				for (int i = 1; i < step; i++) {
				}

			}
		}

		return cacheRelief;
	}

	/**
	 * Interpolates with simple lines between points, for a sharp noise
	 * 
	 * @return the height of the point defined by parameters
	 */
	private static HashMap<Integer, Float> interpolateLinear(
			Map<Integer, Float> relief) {

		HashMap<Integer, Float> cacheRelief = new HashMap<>(relief);

		int step = NoiseUtilities.findStep(relief);
		for (Integer key : relief.keySet()) {
			relief.get(key);
			if (relief.containsKey(key + step)) {
				relief.get(key + step);
				for (int i = 1; i < step; i++) {
				}
			}
		}

		return cacheRelief;
	}

	/**
	 * Interpolates with the nearest neighbor algorithm, for sharp and cubic
	 * noise
	 * 
	 * @return the height of the point defined by parameters
	 */
	private static HashMap<Integer, Float> interpolateNeighbor(
			Map<Integer, Float> relief) {

		HashMap<Integer, Float> cacheRelief = new HashMap<>(relief);

		int step = NoiseUtilities.findStep(relief);

		for (Integer key : relief.keySet()) {
			relief.get(key);
			if (relief.containsKey(key + step)) {
				relief.get(key + step);
				for (int i = 1; i < step; i++) {
					/*
					 * cacheRelief.put(key + i, Interpolater1D.linearInt(a, b,
					 * .5f));
					 */
				}
			}
		}

		return cacheRelief;
	}

	/* ****************************************************************************
	 * 
	 * Adding
	 * 
	 * *****************************************************************
	 */

	/**
	 * Returns a copy of the noise map, smoothed using the choosen algorithm
	 * 
	 * @param relief
	 * @param smooth
	 * @return the smoothed one
	 */
	public static HashMap<Integer, Float> smooth(Map<Integer, Float> relief,
			Smoothing smooth) {

		HashMap<Integer, Float> relief2 = new HashMap<>(relief);

		switch (smooth) {
			case NULL:
				return relief2;
			case LOCAL:
				return NoiseUtilities.smoothLocalAverage(relief);
			case GAUSS:
				// TODO: make it work
				return NoiseUtilities.smoothGauss(relief, 1);
			case GLOBAL:
				return NoiseUtilities.smoothGlobalAverage(relief);
			case MOVING:
				return NoiseUtilities.smoothMovingAverage(relief);
		}

		return relief2;
	}

	/**
	 * Smooths a noise function, by using the Gauss algorithm. the noise is not
	 * modified, as a copy is created.
	 * 
	 * @param relief
	 *            to smooth
	 * @param sigma
	 *            the standard deviation
	 * @return a smoothed copy of relief
	 */
	private static HashMap<Integer, Float> smoothGauss(
			Map<Integer, Float> relief, int sigma) {

		HashMap<Integer, Float> smoothRelief = new HashMap<>();
		for (Integer key : relief.keySet()) {
			float gauss = (int) (1 / Math.sqrt(2 * Math.PI * sigma * sigma) * Math
					.exp(-key * key / (2 * sigma * sigma)));
			// calculate the gauss function
			smoothRelief.put(key, gauss);
		}
		return smoothRelief;
	}

	/**
	 * Smooths a map using a global average value
	 * 
	 * @param relief
	 * @return
	 */
	private static HashMap<Integer, Float> smoothGlobalAverage(
			Map<Integer, Float> relief) {

		float average = NoiseUtilities.findAverage(relief);
		HashMap<Integer, Float> smoothRelief = new HashMap<>();
		for (Integer key : relief.keySet()) {
			smoothRelief.put(
					key,
					Math.abs(relief.get(key) - relief.get(key)
							* relief.get(key) / average));
		}

		return smoothRelief;

	}

	/**
	 * Smooths a noise function, by getting an average value between points. the
	 * noise is not modified, as a copy is created.
	 * 
	 * @param relief
	 *            to smooth
	 * @return a smoothed copy of relief
	 */
	private static HashMap<Integer, Float> smoothLocalAverage(
			Map<Integer, Float> relief) {

		HashMap<Integer, Float> smoothRelief = new HashMap<>();
		int step = NoiseUtilities.findStep(relief);

		for (Integer key : relief.keySet()) {
			if (relief.containsKey(key - step) & relief.containsKey(key + step))
				smoothRelief.put(key,
						relief.get(key) / 2 + relief.get(key + step) / 4
								+ relief.get(key - step) / 4);
			else
				smoothRelief.put(key, relief.get(key));
		}
		return smoothRelief;
	}

	/**
	 * Smooths a map using a moving average, calculated from the nearest points
	 * 
	 * @param relief
	 * @return
	 */
	private static HashMap<Integer, Float> smoothMovingAverage(
			Map<Integer, Float> relief) {

		float average = 0;
		int step = NoiseUtilities.findStep(relief);
		if (!relief.isEmpty())
			average = relief.get(0) / 2 + relief.get(step) / 2;
		HashMap<Integer, Float> smoothRelief = new HashMap<>();
		for (Integer key : relief.keySet()) {
			average = average / 2 + relief.get(key) / 2;
			smoothRelief.put(key, average);
		}

		return smoothRelief;

	}
}

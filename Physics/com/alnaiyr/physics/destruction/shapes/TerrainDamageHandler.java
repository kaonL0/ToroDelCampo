package com.alnaiyr.physics.destruction.shapes;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;

import com.alnaiyr.math.geom.shape.DataPoint;
import com.alnaiyr.terrain.ground.TerrainTile;
import com.alnaiyr.utilities.debug.DB;

/**
 * Handles damages over a relief.
 * 
 * @author IgoЯ
 * @version 1.3
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>Works</em></li>
 *          <li><em>With point Map</em></li>
 *          <li><em>Algorithm improved</em></li>
 *          <li><em>create/ destroy improved</em></li>
 *          </ul>
 *          </p>
 */
public class TerrainDamageHandler implements DamageHandler<TerrainTile> {

	/* ***********************
	 * 
	 * Variables
	 * 
	 * **************************
	 */
	/** the current damages that still are to give. */
	private List<DamageShape> toHandle = new ArrayList<DamageShape>(10);

	private int iter = 0;

	/* ***********************
	 * 
	 * Constructor
	 * 
	 * **************************
	 */

	public TerrainDamageHandler() {

	}

	/* ***********************
	 * 
	 * Methods
	 * 
	 * **************************
	 */

	/**
	 * Adds a new damage to be handled
	 * 
	 * @param toAdd
	 */
	public void addDamage(DamageShape toAdd) {

		toHandle.add(toAdd);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.Al_Nair.physics.destruction.Destructable#damage(java.lang.Object,
	 * java.lang.Object)
	 */
	@Override
	public void damage(TerrainTile destroy, GameContainer container) {

		if (!toHandle.isEmpty()) {

			for (DamageShape damage : toHandle) {
				// first damage the image
				if (isInside(damage, destroy))
					DB.test();
				detectAndAct(damage, destroy, damage.getStart(),
						damage.getEnd());
			}
			toHandle.clear();
		}
	}

	/**
	 * Detects two intersection, and decide what to do in between it.
	 * 
	 * @param damage
	 * @param surface
	 * @param start
	 * @param end
	 * @return true if could do something.
	 */
	private boolean detectAndAct(DamageShape damage, TerrainTile tile,
			int[] start, int[] end) {

		solveExceptions(tile, damage, start, end);

		List<DataPoint> intersections = damage.findAllIntersections(tile,
				start, end);

		createAndDestroy(tile, damage, intersections);

		checkInside(tile, damage);

		/*
		 * try { ImageDigger.damage(damage.convert(tile),tile.getCoord(),
		 * tile.getAlphaMask(), Color.black, container); } catch (SlickException
		 * e) { e.printStackTrace(); }
		 */

		return true;
	}

	/**
	 * Do all the destruction and creation if a collision was detected
	 * 
	 * @param tile
	 * @param damage
	 * @param intersections
	 */
	private void createAndDestroy(TerrainTile tile, DamageShape damage,
			List<DataPoint> intersections) {

		if (intersections.size() > 1) {

			byte[] creat = new byte[intersections.size()];
			byte[] dest = new byte[intersections.size()];

			int i = 0;

			for (DataPoint point : intersections) {
				// creation detection must be done BEFORE we actually start
				// destroying the tile
				creat[i] = tile.canCreate(damage, point);
				dest[i] = tile.getOutsideDirection(damage, point);
				i++;
			}
			i = 0;

			DB.test("destroy");
			// Note that destroying can't be done before or during the research
			// for creating directions, or it would cause a null pointer
			while (i != intersections.size()) {
				tile.destroyUntilNext(damage, intersections.get(i),
						intersections, dest[i]);
				i++;
			}
			// for (DataPoint point : intersections) {
			// tile.destroyUntilNext(damage, point, intersections, dest);
			// }

			DB.test("create");
			i = 0;
			while (i != intersections.size()) {
				tile.createUntilNext(damage, intersections.get(i), creat[i]);
				i++;
			}
			DB.test("done");
		}

	}

	/**
	 * Checks if we just created an island, to store a new start point
	 * 
	 * @param tile
	 * @param shape
	 * @param intersections
	 */
	private void isIslandCreated(TerrainTile tile, DamageShape shape,
			List<DataPoint> intersections, byte[] create, byte[] destroy) {

	}

	/**
	 * Finds and solve all the exceptions (== special cases) related to the
	 * collision we are making TODO change to direct coordinates instead of
	 * DataPoint
	 * 
	 * @param tile
	 * @param damage
	 * @param start
	 * @param end
	 */
	private void solveExceptions(TerrainTile tile, DamageShape damage,
			int[] start, int[] end) {

		List<List<int[]>> exceptions = damage.findAllExceptions(tile, start,
				end);
		if (iter > 1)
			tile.getDebug().multiplySize(2);

		solveDiagonalException(exceptions.get(1), tile, damage);
		solveOuterException(exceptions.get(0), tile, damage);
		DB.test("All exceptions done");
	}

	/**
	 * Solves all the crossing diagonals exceptions
	 * 
	 * @param diagExc
	 */
	private void solveDiagonalException(List<int[]> diagExc, TerrainTile tile,
			DamageShape damage) {

		for (int[] current : diagExc) {
			tile.getDebug().setCorner(damage.getPointToRef(current), false,
					tile);
			tile.getDebug().setWidth(tile.getMap().getStep());
			detectAndAct(tile.getDebug(), tile, tile.getDebug().getStart(),
					tile.getDebug().getEnd());
			tile.getDebug().multiplySize(2f);
		}
	}

	private void solveOuterException(List<int[]> outExc, TerrainTile tile,
			DamageShape damage) {

		for (int[] current : outExc) {
			tile.getDebug().setCenter(damage.getPointToRef(current), false,
					tile);
			iter++;
			detectAndAct(tile.getDebug(), tile, tile.getDebug().getStart(),
					tile.getDebug().getEnd());

			if (iter >= 1)
				tile.getDebug().multiplySize(.5f / iter * 2);
			iter = 0;
		}
	}

	/**
	 * Once all the damage are done, check if there is anything left on the
	 * inside
	 * 
	 * @param tile
	 * @param shape
	 */
	private void checkInside(TerrainTile tile, DamageShape shape) {
		/*
		 * List<DataPoint> inside;
		 * int[]center=shape.getPointToRef(shape.getCenter()); iter=0;
		 * 
		 * while(shape.getWidth()>tile.getMap().getStep()*2){
		 * 
		 * iter++; shape.addSize(-tile.getMap().getStep()*2);
		 * shape.setCenter(center, true, tile);
		 * 
		 * inside=shape.findAllCommonPoint(tile,new DataPoint(shape.getStart(),
		 * shape.getStart(), shape.getStart(), tile.getMap().getStep(),
		 * shape.getMap().getStep()));
		 * 
		 * for(DataPoint point: inside){ tile.destroyAll(shape, point); }
		 * shape.addSize(-tile.getMap().getStep()*2); }
		 * shape.addSize(tile.getMap().getStep()*2*(iter+2)); iter=1;
		 */
	}

	/* ***********************
	 * 
	 * Checking
	 * 
	 * **************************
	 */

	/**
	 * Checks if the damage is inside the boundaries
	 * 
	 * @param shape
	 * @param surface
	 * @return
	 */
	private boolean isInside(DamageShape shape, TerrainTile tile) {

		/*
		 * boolean top = tile.getCoord().getLY() < shape.getCoord().getYL() +
		 * shape.getHeight(); boolean bottom = tile.getCoord().getLY() +
		 * tile.getHeight() > shape .getCoord().getYL();
		 * 
		 * boolean right = tile.getCoord().getLX() < shape.getCoord().getXL() +
		 * shape.getWidth(); boolean left = tile.getCoord().getLX() +
		 * tile.getWidth() > shape .getCoord().getXL();
		 */

		return false/* top && bottom && right && left */;
	}

	/* ***********************
	 * 
	 * Getters
	 * 
	 * **************************
	 */

	/** @return the toHandle */
	public List<DamageShape> getToHandle() {

		return toHandle;
	}

}

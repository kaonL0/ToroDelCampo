package com.alnaiyr.physics.destruction.shapes;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import com.alnaiyr.coordinates.dynamic.ReferencedCoordinate;
import com.alnaiyr.math.MathU;
import com.alnaiyr.math.functions.interpolations.NoiseUtilities;
import com.alnaiyr.terrain.ground.TerrainTile;

/**
 * A damage shape, smooth everywhere.
 * 
 * @author IgoЯ
 * @version 1.3
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>Works</em></li>
 *          <li><em>Simplified</em></li>
 *          <li><em>Point count fixed</em></li>
 *          <li><em>Order and first point fixed</em></li>
 *          <li><em> added all standard methods</em></li>
 *          </ul>
 */
public class DamageCircle extends DamageShape {

    /* ***********************
     * 
     * Variable
     * 
     * **************************
     */

    private int radius = 0;

    /* ***********************
     * 
     * Constructor
     * 
     * **************************
     */

    public DamageCircle(int[] center, ReferencedCoordinate reference, int step,
	    int radius) {

	super(step, radius * 2, radius * 2);

	// by default, 20 segments.
	int seg = (int) (radius * 2 * Math.PI) / 20;

	seg = MathU.roundTo(seg, step);

	this.radius = MathU.roundTo((int) (seg * 20 / (2 * Math.PI) + 1), step);
	width = this.radius * 2;
	height = width;

	center = MathU.roundTo(center, step);

	Map<Integer, Float> relief = new HashMap<Integer, Float>();

	for (int i = 0; i < radius * 2; i++) {
	    int x = i;
	    int y = (int) Math.sqrt(radius * radius - (x - radius)
		    * (x - radius));
	    Vector2f vec = new Vector2f(x, y + radius);
	    if (!relief.containsKey((int) vec.x))
		relief.put((int) vec.x, vec.y);
	}
	List<Vector2f> points = NoiseUtilities.convert(relief, step, false);
	relief.clear();

	for (int i = 0; i < radius * 2; i++) {
	    int x = i;
	    int y = (int) -Math.sqrt(radius * radius - (x - radius)
		    * (x - radius));
	    Vector2f vec = new Vector2f(x, y + radius);
	    if (!relief.containsKey((int) vec.x))
		relief.put((int) vec.x, vec.y);
	}

	List<Vector2f> points2 = NoiseUtilities.convert(relief, step, false);
	Collections.reverse(points2);
	points.addAll(points2);
	points.remove(0);
	points = NoiseUtilities.correctList(points, step);

	/*
	 * while(points.get(points.size()-1)){
	 * 
	 * }
	 */
	start = new int[] { (int) points.get(0).x / step,
		(int) points.get(0).y / step };
	Collections.reverse(points);

	/*
	 * corner= new int[] {(int) (center.x-radius)/step,(int)
	 * (center.y-radius)/step}; this.map = new PointMap(points, new
	 * Cartesian(center.x-radius,center.y-radius), step, true);
	 */
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.alnaiyr.physics.destruction.shapes.DamageShape#convert(com.alnaiyr
     * .terrain.ground.TerrainTile)
     */
    @Override
    public Shape convert(TerrainTile tile) {

	return null;
    }

    /* ***********************
     * 
     * Methods
     * 
     * **************************
     */

    /*
     * (non-Javadoc)
     * 
     * @see com.Al_Nair.physics.destruction.DamageShape#findDirection(int)
     */
    /*
     * @Override public Vector2f findDirection(int i) {
     * 
     * Vector2f normal = new Vector2f(0, 0); if (i + 3 < getPoints().size())
     * normal = new Vector2f(MathU.getNext(i + 3, points).getX() -
     * getPoints().get(i).getX(), MathU.getNext(i + 3, points).getY() -
     * getPoints().get(i).getY()); normal.normalise(); return normal; }
     */

    /*
     * @Override public boolean isInside(int[] point, TerrainTile tile) {
     * 
     * int[] center=getCenter();
     * 
     * return (point[0]-center[0])<=radius/PointMap.getStep() &&
     * (point[1]-center[1])<radius/PointMap.getStep(); }
     * 
     * @Override public boolean isOutside(int[] point, TerrainTile tile) { int[]
     * center=getCenter(); return (point[0]-center[0])>radius/PointMap.getStep()
     * && (point[1]-center[1])<radius/PointMap.getStep(); }
     * 
     * @Override public Shape convert(TerrainTile tile) { return new
     * Circle(getCoord().getX(), getCoord().getY(), radius); }
     */

}

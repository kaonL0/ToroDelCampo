package com.alnaiyr.math.geom.shape;


/**
 * Just a practical way to describe a point containing some datas
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>Works</em></li>
 *          </ul>
 */
public class DataPoint {

    /* ***********************
     * 
     * Variables
     * 
     * **************************
     */

    private int[] coord = new int[2];

    private int[] nextRef = new int[2];

    private int[] prevRef = new int[2];

    /* ***********************
     * 
     * Constructor
     * 
     * **************************
     */

    /**
     * @param coord
     * @param nextRef
     * @param prevRef
     * @param refStep
     * @param step
     */
    public DataPoint(int[] coord, int[] nextRef, int[] prevRef, int refStep,
	    int step) {

	this.coord[0] = coord[0] * step / refStep;
	this.coord[1] = coord[1] * step / refStep;

	this.nextRef = nextRef.clone();
	this.prevRef = prevRef.clone();
    }

    private DataPoint(DataPoint point) {

	this.coord = point.coord.clone();
	this.nextRef = point.nextRef.clone();
	this.prevRef = point.prevRef.clone();
    }

    /* ***********************
     * 
     * Methods
     * 
     * **************************
     */

    /**
     * Sets the coordinates to the next or previous value, based on a direction
     * 
     * @param datas
     */
    private void setTo(byte[] datas) {

	coord[0] = coord[0] + datas[0];
	coord[1] = coord[1] + datas[1];

    }

    /**
     * Check if one of the map value is reached, meaning it arrived to a corner,
     * and/or an segment point
     * 
     * @param toReach
     * @param map
     * @param step
     * @return
     */
    private boolean isReached(int[] toReach, PointMap map, int step) {
	return (coord[0] == toReach[0] * map.getStep() / step && coord[1] == toReach[1]
		* map.getStep() / step);
    }

    /**
     * Sets the data point to its next point.
     * 
     * @param map
     *            for the end points
     * @param step
     *            to reach
     */
    public void toNext(PointMap map, int step) {
	if (isReached(nextRef, map, step)) {
	    nextRef = map.findNext(prevRef);
	}
	setTo(map.findNextDirection(prevRef));

	if (isReached(nextRef, map, step))
	    prevRef = nextRef;
    }

    /**
     * Sets the data point to its previous point.
     * 
     * @param map
     *            for the end points
     * @param step
     *            to reach
     */
    public void toPrevious(PointMap map, int step) {

	if (isReached(prevRef, map, step))
	    prevRef = map.findPrevious(nextRef);
	setTo(map.findPreviousDirection(nextRef));

	if (isReached(prevRef, map, step))
	    nextRef = prevRef;
    }

    @Override
    public String toString() {

	return "Coordinates: " + coord.toString() + " prevRef: "
		+ prevRef.toString() + " nextRef: " + nextRef.toString();
    }

    @Override
    public DataPoint clone() {
	return new DataPoint(this);
    }

    /* ***********************
     * 
     * Getters
     * 
     * **************************
     */

    /**
     * @return the coord
     */
    public int[] getCoord() {
	return coord;
    }

    /**
     * Gets the coordinates with a scaling factor
     * 
     * @param scale
     * @return coordinates
     */
    public int[] getCoord(int scale) {
	return new int[] { coord[0] * scale, coord[1] * scale };
    }

    /**
     * @return the nextRef
     */
    public int[] getNextRef() {
	return nextRef;
    }

    /**
     * @return the prevRef
     */
    public int[] getPrevRef() {
	return prevRef;
    }

    /**
     * Gets the next data point according to directions
     * 
     * @param map
     * @param step
     * @return next DataPoint
     */
    public DataPoint getNext(PointMap map, int step) {
	DataPoint point = new DataPoint(this);
	point.toNext(map, step);
	return point;
    }

    /**
     * Gets the previous data point according to directions
     * 
     * @param map
     * @param step
     * @return data point
     */
    public DataPoint getPrevious(PointMap map, int step) {
	DataPoint point = new DataPoint(this);
	point.toPrevious(map, step);
	return point;
    }

    /**
     * Gets all the directions to previous and next, based on the two related
     * segment points
     * 
     * @param map
     * @return data point
     */
    public byte[] getDatas(PointMap map) {
	return new byte[] { map.getDatas(prevRef)[0], map.getDatas(nextRef)[1] };
    }

    /**
     * @see #getDatas
     * @param map
     * @return data point
     */
    public byte[] getInvertedDatas(PointMap map) {
	return new byte[] { map.getDatas(nextRef)[1], map.getDatas(prevRef)[0] };
    }

    /**
     * Gets the direction vector to the next point
     * 
     * @param map
     * @return next direction
     */
    public byte[] getNextDirection(PointMap map) {
	return map.findNextDirection(prevRef);
    }

    /**
     * Gets the direction vector to the previous point
     * 
     * @param map
     * @return previous direction
     */
    public byte[] getPreviousDirection(PointMap map) {
	return map.findPreviousDirection(nextRef);
    }

    /* ***********************
     * 
     * Setters
     * 
     * **************************
     */

    /**
     * @param nextRef
     *            the nextRef to set
     */
    public void setNextRef(int[] nextRef) {
	this.nextRef = nextRef;
    }

    /**
     * @param prevRef
     *            the prevRef to set
     */
    public void setPrevRef(int[] prevRef) {
	this.prevRef = prevRef;
    }

    /**
     * @param coord
     *            the coord to set
     */
    public void setCoord(int[] coord) {
	this.coord = coord;
    }

}

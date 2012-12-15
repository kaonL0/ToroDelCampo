package com.alnaiyr.math.geom.shape.segments;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.coordinates.utilities.VecU;
import com.alnaiyr.math.numbers.profiles.percents.Percentable;
import com.alnaiyr.models.gamestrategy.NoEffect;

/**
 * A segment made of a sum of linear segments
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class LinearGroupSegment implements AdvancedSegment {

    /* ****************
     * 
     * Variables
     * 
     * ***************
     */

    private TreeMap<Float, AdvancedSegment> segments;
    private float length = 0;

    /* ****************
     * 
     * Constructors
     * 
     * ***************
     */

    /**
     * @param segments
     */
    public LinearGroupSegment(AdvancedSegment... segments) {

	for (Segment seg : segments)
	    length += seg.getLength();

	this.segments = new TreeMap<>();

	float dist = 0;
	for (AdvancedSegment seg : segments) {
	    this.segments.put(dist / length, seg);
	    dist += seg.getLength();
	}
    }

    /* ****************
     * 
     * Methods
     * 
     * ***************
     */

    @Override
    public void debug(GameContainer container, Graphics g, boolean condition) {
	for (Entry<Float, AdvancedSegment> seg : segments.entrySet()) {
	    seg.getValue().debug(container, g, condition);
	}
    }

    /* ****************
     * 
     * Getters / Setters
     * 
     * ***************
     */

    @Override
    public PlanVector bindCoordinate(float currentPercent, float distanceToAdd,
	    PlanVector coord) {
	Entry<Float, AdvancedSegment> seg = segments.floorEntry(currentPercent);
	return seg.getValue().getCoordinate(
		(double) getEquivalentDistance(currentPercent - seg.getKey())
			+ distanceToAdd, coord);
    }

    @Override
    public PlanVector bindCoordinate(Percentable percent, PlanVector coord) {

	Entry<Float, AdvancedSegment> seg = segments.floorEntry(percent
		.getPercentage());
	return seg.getValue().getCoordinate(
		(double) getEquivalentDistance(percent.getValue()
			- seg.getKey()), coord);

    }

    @Override
    public PlanVector bindCoordinate(float percent, PlanVector coord) {
	Entry<Float, AdvancedSegment> seg = segments.floorEntry(percent);
	return seg.getValue().getCoordinate(
		(double) getEquivalentDistance(percent - seg.getKey()), coord);
    }

    @Override
    public PlanVector getCoordinate(double distance, PlanVector coord) {

	return bindCoordinate(getEquivalentPercentage((float) distance), coord);
    }

    /**
     * Costly method
     * 
     * @return NaN if not found
     */
    @Override
    public float getPercentage(PlanVector coord) {
	for (Entry<Float, AdvancedSegment> seg : segments.entrySet()) {
	    if (VecU.isInside(coord, seg.getValue().getFrom(), seg.getValue()
		    .getFrom())) {
		return seg.getValue().getPercentage(coord)
			* seg.getValue().getLength() / length + seg.getKey();
	    }
	}
	return Float.NaN;
    }

    @Override
    public PlanVector getFrom() {
	return segments.get(0).getFrom();
    }

    @Override
    public PlanVector getTo() {
	return segments.lastEntry().getValue().getTo();
    }

    @NoEffect
    @Override
    public void setFrom(PlanVector coord) {
    }

    @NoEffect
    @Override
    public void setTo(PlanVector coord) {
    }

    @Override
    public float getLength() {
	return length;
    }

    @Override
    public float getEquivalentPercentage(float distance) {
	return distance / length;
    }

    @Override
    public float getEquivalentDistance(float percent) {
	return length * percent;
    }

    /**
     * @return the segments
     */
    public Map<Float, AdvancedSegment> getSegments() {
	return segments;
    }

    /**
     * @param segments
     *            the segments to set
     */
    public void setSegments(TreeMap<Float, AdvancedSegment> segments) {
	this.segments = segments;
    }

}

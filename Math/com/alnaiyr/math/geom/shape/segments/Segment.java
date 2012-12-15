package com.alnaiyr.math.geom.shape.segments;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.debug.Debug;
import com.alnaiyr.math.numbers.profiles.percents.Percentable;

/** Segment in the space able to give coordinates given a percent
 * @author IgoЯ
 * @version 1.0
 * <p><Strong>Version Change:</Strong>
 *		<ul>
 *			<li><em>No Changes</em></li>
 *		</ul>
 */
public interface Segment extends Debug{

    /** Gets coordinates given a percentage
     * @param percent
     * @param coord to set to the new point, or null if new coordinates are needed
     * @return coordinate
     */
    public PlanVector bindCoordinate(Percentable percent, PlanVector coord);
    
    /**
     * @param percent
     * @param coord to set to the new point, or null if new coordinates are needed
     * @return coordinate
     */
    public PlanVector bindCoordinate(float percent, PlanVector coord);
    
    /** Gets from where it start
     * 
     * @return coordinates
     */
    public PlanVector getFrom();
    
    /** Gets to where it goes
     * 
     * @return coordinates
     */
    public PlanVector getTo();
    
    /**
     * 
     * @return length of the segment
     */
    public float getLength();
    
    /** Gets the percentage done given the distance done from the start
     * 
     * @param distance
     * @return percent value
     */
    public float getEquivalentPercentage(float distance);
    
    /** Gets distance made considering a delta percentage
     * @param percent
     * @return the distance
     */
    public float getEquivalentDistance(float percent);
    
    
}

package com.alnaiyr.math.geom.shape.segments;

import com.alnaiyr.coordinates.PlanVector;

/**Advanced functionalities for segments
 * @author IgoЯ
 * @version 1.0
 * <p><Strong>Version Change:</Strong>
 *		<ul>
 *			<li><em>No Changes</em></li>
 *		</ul>
 */
public interface AdvancedSegment extends Segment{

    
    /** sets from where it start
     * @param coord 
     * 
     */
    public void setFrom(PlanVector coord);
    
    /** Gets to where it goes
     * @param coord 
     * 
     */
    public void setTo(PlanVector coord);
    
    /**Gets the percentage corresponding to a coordinate
     * 
     * @param coord
     * @return percent
     */
    public float getPercentage(PlanVector coord);
    
    /** Gets a coordinate  knowing which distance to get from first coordinate
     * @param distance
     * @param coord to set to the new point, or null if new coordinates are needed
     * @return coordinate
     */
    public PlanVector getCoordinate(double distance, PlanVector coord);
    
    /** Gets a coordinate  knowing which distance to get from current percent
     * 
     * @param currentPercent
     * @param distanceToAdd
     * @param coord to set to the new point, or null if new coordinates are needed
     * @return coordinate
     */
    public PlanVector bindCoordinate(float currentPercent,float distanceToAdd, PlanVector coord);
    
}

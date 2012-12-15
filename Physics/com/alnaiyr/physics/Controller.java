
package com.alnaiyr.physics;

/** Describes how can be controlled any movement
 * 
 * @author IgoЯ
 * @version 0.1
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>Change</em></li>
 *          </ul> */
public interface Controller {
	
	/** Accelerates a movement in a direction , depending on boolean
	 * 
	 * @param xCondition
	 * @param Ycondition */
	public void accelerate(boolean xCondition, boolean Ycondition);
	
	/** Slows down a movement in a direction , depending on boolean
	 * 
	 * @param xCondition
	 * @param Ycondition */
	public void slow(boolean xCondition, boolean Ycondition);
	
	/** Gives an impulse in a direction , depending on boolean
	 * 
	 * @param xCondition
	 * @param Ycondition */
	public void impulse(boolean xCondition, boolean Ycondition);
	
}

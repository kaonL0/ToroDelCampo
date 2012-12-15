package com.alnaiyr.physics.speed;

import com.alnaiyr.math.numbers.DeltaFloatStorer;
import com.alnaiyr.math.numbers.Invertible;

/**
 * Speed Store, useful to limit verbose in code
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public interface Speedable extends Invertible, DeltaFloatStorer {

	/**
	 * Gets the speed, knowing external delta time
	 * 
	 * @return speed*delta
	 */
	public float getSpeed();

	/**
	 * @param speed
	 */
	public void setSpeed(float speed);

	/**
	 * 
	 * @return speed without any delta transformation
	 */
	public float getNonDeltaSpeed();

}

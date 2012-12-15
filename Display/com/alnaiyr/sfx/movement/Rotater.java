package com.alnaiyr.sfx.movement;

import org.newdawn.slick.Graphics;

import com.alnaiyr.math.numbers.profiles.percents.Percent;

/**
 * Describes every object able to rotate in their way
 * 
 * @author IgoЯ
 * @version 1.1
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>Works</em></li>
 *          <li><em>Some precisions</em></li>
 *          </ul>
 * @param <Type>
 */
public interface Rotater {

	/**
	 * Puts the object to a certain rotating angle
	 * 
	 * @param type
	 * @param angle
	 *            in radiant
	 * @param g
	 */
	public void rotate(float angle, Graphics g);

	/**
	 * Rotates at a certain speed
	 * 
	 * @param type
	 * @param speed
	 * @param g
	 */
	public void permanentRotate(float speed, Graphics g);

	/**
	 * Rotates at a certain speed, from an angle to an other one, returns true
	 * when done
	 * 
	 * @param type
	 * @param from
	 *            , in radiant
	 * @param to
	 *            , in radiant
	 * @param percent
	 *            (0-1)
	 * @param g
	 * @return true when done
	 */

	public boolean rotateTo(float from, float to, Percent percent, Graphics g);
}

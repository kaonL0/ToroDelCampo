/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.test.jbox2D;

import org.jbox2d.common.IViewportTransform;
import org.jbox2d.common.Vec2;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.camera.Focus;
import com.alnaiyr.general.IV;
import com.alnaiyr.models.gamestrategy.NoEffect;

/**
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class JBoxViewport implements IViewportTransform {

	/*---------------------
	 * 
	 * Variables
	 * 
	 *----------------------*/

	/**
	 * the camera concerned by this physic engine, meaning that it can know
	 * zoom, and translate/ rotation of the screen
	 */
	public Focus cam;

	public PlanVector coord;

	public float scale = 50f;

	/*---------------------
	 * 
	 * Constructor
	 * 
	 *----------------------*/

	/*---------------------
	 * 
	 * Methods
	 * 
	 *----------------------*/

	@Override
	public boolean isYFlip() {
		return true;
	}

	@NoEffect
	@Override
	public void setYFlip(boolean yFlip) {
		// no way.
	}

	@Override
	public PlanVector getExtents() {

		return new Vec2(IV.vWidth / 2, IV.vHeight / 2);
	}

	@NoEffect
	@Override
	public void setExtents(PlanVector argExtents) {
		// no.
	}

	@NoEffect
	@Override
	public void setExtents(float argHalfWidth, float argHalfHeight) {
		// no
	}

	@Override
	public PlanVector getCenter() {
		return coord;
	}

	@NoEffect
	@Override
	public void setCenter(PlanVector argPos) {
		// no
	}

	@NoEffect
	@Override
	public void setCenter(float x, float y) {
		// void.
	}

	@NoEffect
	@Override
	public void setCamera(float x, float y, float scale) {
		// void
	}

	@Override
	public void getWorldVectorToScreen(PlanVector argWorld, PlanVector argScreen) {
		argScreen.set(argWorld.x() * scale + coord.x(), argWorld.y() * scale
				+ coord.y());
	}

	@Override
	public void getScreenVectorToWorld(PlanVector argScreen, PlanVector argWorld) {
	}

	@Override
	public void getWorldToScreen(PlanVector argWorld, PlanVector argScreen) {
	}

	@Override
	public void getScreenToWorld(PlanVector argScreen, PlanVector argWorld) {
	}

	/*---------------------
	 * 
	 * Getters / Setters
	 * 
	 *----------------------*/
}

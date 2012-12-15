package com.alnaiyr.display.camera.utilities;

import org.jbox2d.common.Vec2;
import org.newdawn.slick.geom.Vector2f;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.camera.Focus;
import com.alnaiyr.general.IV;

/**
 * Various useful methods for cameras
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>Works</em></li>
 *          </ul>
 */
public class CameraUtilities {

	/* *****************
	 * 
	 * Method
	 * 
	 * **********************
	 */

	private final static Vec2	vec	= new Vec2(0, 0);

	/**
	 * Gets global coordinates, and translate it according to camera
	 * 
	 * @param vector
	 * @param camera
	 * @return vector2f
	 */
	public static PlanVector camToScreen(final PlanVector vector,
			final Focus camera) {

		vec.set(vector);
		vec.set(vec.x - (camera.getCoord().x() - IV.vWidth / 2), vec.y
				- (camera.getCoord().y() - IV.vHeight / 2));
		vec.mulLocal(camera.getZoom());

		return vec;
	}

	/**
	 * @param x
	 * @param y
	 * @param camera
	 * @return vector2f
	 */
	public static PlanVector camToScreen(final float x, final float y,
			final Focus camera) {
		return CameraUtilities.camToScreen(new Vector2f(x, y), camera);
	}

	/**
	 * @param vector
	 * @param camera
	 * @param chars
	 * @return value
	 */
	public static float camToScreen(final PlanVector vector,
			final Focus camera, final char chars) {
		return chars == 'x' ? CameraUtilities.camToScreen(vector, camera).x()
				: CameraUtilities.camToScreen(vector, camera).y();
	}

	public static PlanVector screenToCam(final PlanVector vector,
			final Focus camera) {

		vec.set(vector);
		vec.mulLocal(1 / camera.getZoom());
		vec.set(vec.x + (camera.getCoord().x() - IV.vWidth / 2), vec.y
				+ (camera.getCoord().y() - IV.vHeight / 2));
		return vec;
	}

	public static PlanVector screenToCam(final float x, final float y,
			final Focus cam) {
		return CameraUtilities.screenToCam(new Vector2f(x, y), cam);
	}

}

package com.alnaiyr.display.camera;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.alnaiyr.ai.updater.Updatable;
import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.coordinates.dynamic.Origin;
import com.alnaiyr.coordinates.dynamic.ReferencedCoordinate;
import com.alnaiyr.display.camera.sfxs.FocusSFX;
import com.alnaiyr.display.renderables.Renderable;
import com.alnaiyr.general.IV;

/**
 * <p>
 * A camera class, that will be able to follow any coordinates, and deals with
 * zoom, translation <br>
 * Can be used with a CameraUtilities for more options <br>
 * Finally, it can performs slow travelings or zooming. using mover<br>
 * </p>
 * Can be used in two way: using a global camera,or switching between different
 * cameras, or both
 * 
 * TODO re implement rotation
 * 
 * @author IgoЯ
 * @version 1.4
 *          <p>
 *          <strong> Version change:</strong>
 *          <ul>
 *          <li><em> Functional</em></li>
 *          <li><em> Soft travellings working</em></li>
 *          <li><em> Even Softer</em></li>
 *          <li><em> Angle added everywhere</em></li>
 *          <li><em> added comments</em></li>
 *          </ul>
 *          </p>
 **/

public class Focus implements Updatable, Renderable {

	/* **********************************************
	 * 
	 * Variables
	 * 
	 * ************************************************
	 */

	/** where is the camera supposed to look at **/
	public PlanVector	coord;
	public float		zoom;

	/* ************** Traveling ******************** */

	public FocusSFX		camMover;

	/* **********************************************
	 * 
	 * Constructors
	 * 
	 * ************************************************
	 */

	public Focus(final Focus cam) {
		this(cam.coord.clone(), cam.getZoom());
	}

	/**
	 * Set a simple Camera on one coordinate, centered on the screen
	 * 
	 * @param toFollow
	 **/
	public Focus(final PlanVector toFollow) {
		this(toFollow, 1);
	}

	/**
	 * @param toFollow
	 * @param zoom
	 * @param rotating
	 * @param angle
	 */
	public Focus(final PlanVector toFollow, final float zoom) {

		coord = toFollow;
		setZoom(zoom);

	}

	/* **********************************************
	 * 
	 * Methods
	 * 
	 * ************************************************
	 */

	/**
	 * Instantly goes to the new coordinates, zoom, angle
	 * 
	 * @param toFollow
	 * @param zoom
	 * @param angle
	 */
	public void instantSwitchTo(final ReferencedCoordinate toFollow,
			final float zoom) {

		coord = toFollow;
		setZoom(zoom);
	}

	public void setAMove(final FocusSFX move) {
		camMover = move;
	}

	public boolean isMoveDone() {
		return camMover == null || camMover.isDone();
	}

	public boolean isInMovement() {
		return camMover != null;
	}

	/* **********************************************
	 * 
	 * Update
	 * 
	 * ************************************************
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.Al_Nair.general.Updatable#update(int)
	 */
	@Override
	public void update(final int delta, final boolean condition) {
		if (camMover != null) {

			camMover.update(delta, condition);

			if (camMover.isDone())
				camMover = null;

		}
	}

	public void reset() {
		zoom = 1;
		coord.set(Origin.ref);
	}

	/* **********************************************
	 * 
	 * Render
	 * 
	 * ************************************************
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.Al_Nair.general.ObjectRenderable#render(org.newdawn.slick.Graphics)
	 */
	@Override
	public void render(final Graphics g, final GameContainer container) {

		g.scale(zoom, zoom);
		g.translate(IV.getWidth() / 2 / zoom - coord.x(), IV.getHeight() / 2
				/ zoom - coord.y());

	}

	/* **********************************************
	 * 
	 * Getters
	 * 
	 * ************************************************
	 */

	/** @return the biggest scale factor */
	public float getZoom() {
		return zoom;
	}

	/**
	 * 
	 * @return followed coord
	 */
	public PlanVector getCoord() {
		return coord;
	}

	/* **********************************************
	 * 
	 * Setters
	 * 
	 * ************************************************
	 */

	/**
	 * 
	 * @param zoom
	 */
	public void setZoom(final float zoom) {
		this.zoom = zoom;
	}

	@Override
	public Focus clone() {
		return new Focus(this);
	}
}

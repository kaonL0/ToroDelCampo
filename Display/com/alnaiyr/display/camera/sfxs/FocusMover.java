/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.display.camera.sfxs;

import org.jbox2d.common.Vec2;
import org.lwjgl.LWJGLException;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.coordinates.utilities.VecU;
import com.alnaiyr.display.camera.Focus;
import com.alnaiyr.math.functions.interpolations.Interpolater1D;
import com.alnaiyr.math.functions.interpolations.Interpolation;
import com.alnaiyr.math.numbers.advanced.PercentMM;
import com.alnaiyr.math.numbers.impl.DeltaFloatStorerImpl;
import com.alnaiyr.math.numbers.profiles.Profile;

/**
 * An object handling all cam transformation
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class FocusMover implements FocusSFX {

	/*---------------------
	 * 
	 * Variables
	 * 
	 *----------------------*/

	public Interpolation	inter		= Interpolation.LINEAR;

	private final Profile	profile;

	private final Focus		cam;

	private float			startingZoom;
	private float			endingZoom;

	private PlanVector		startingCoord;
	private PlanVector		endingCoord;

	private boolean			activated	= true;

	/*---------------------
	 * 
	 * Constructor
	 * 
	 *----------------------*/

	public FocusMover(final Focus cam, final float speed, final float toZoom)
			throws LWJGLException {
		this(cam, speed, toZoom, cam.coord);
	}

	public FocusMover(final Focus cam, final float speed, final float toZoom,
			final PlanVector toGo) throws LWJGLException {
		profile = new Profile();

		profile.setMinMax(PercentMM.instance);
		profile.setDerivative(new DeltaFloatStorerImpl(speed));

		this.cam = cam;

		startingZoom = cam.zoom;
		endingZoom = toZoom;
		startingCoord = cam.coord;
		endingCoord = toGo;

		try {
			setCoordinateBasedSpeed();
		} catch (final IllegalArgumentException e) {
			try {
				setZoomBasedSpeed();
			} catch (final IllegalArgumentException e1) {
				throw new LWJGLException("no move to perform!");
			}
		}

		cam.setAMove(this);
	}

	public FocusMover(final Focus cam, final float speed, final PlanVector toGo)
			throws LWJGLException {
		this(cam, speed, cam.zoom, toGo);

	}

	/*---------------------
	 * 
	 * Methods
	 * 
	 *----------------------*/

	/**
	 * Sets the speed to be based on the distance
	 */
	public void setZoomBasedSpeed() {
		if (endingZoom != startingZoom)
			profile.setMultiplier(1 / (endingZoom - startingZoom));
		else
			throw new IllegalArgumentException("no zooming is to be done.");
	}

	/**
	 * Sets the speed to be based on the distance to travel rather than on the
	 * zooming to do
	 */
	public void setCoordinateBasedSpeed() {
		if (VecU.getDistance(startingCoord, endingCoord) != 0)
			profile.setMultiplier(1 / VecU.getDistance(startingCoord,
					endingCoord));
		else
			throw new IllegalArgumentException("no translation is to be done.");
	}

	/**
	 * Sets the speed to be dependent on delta, and represent a portion of time,
	 * o matter the distance to travel or to zoom
	 * 
	 */
	public void setTimeBasedSpeed() {
		profile.setMultiplier(1);
	}

	private final Vec2	vec	= new Vec2();

	@Override
	public void update(final int delta, final boolean condition) {

		profile.update(delta, condition);

		vec.set(Interpolater1D.interpolate(inter, startingCoord.x(),
				endingCoord.x(), profile.getValue()), Interpolater1D
				.interpolate(inter, startingCoord.y(), endingCoord.y(),
						profile.getValue()));

		cam.coord = vec;
		if (isDone()) {
			cam.coord = endingCoord;
		}

		cam.zoom = Interpolater1D.interpolate(inter, startingZoom, endingZoom,
				profile.getValue());
	}

	/**
	 * Permits to change the zoom aimed during transition, but needs some
	 * calculation
	 * 
	 * @param zoom
	 */
	public void setEndZoom(final float zoom) {
		if (zoom != endingZoom) {

			final float actualZ = Interpolater1D.interpolate(inter,
					startingZoom, endingZoom, profile.getValue());
			final Vec2 actual = new Vec2(Interpolater1D.interpolate(inter,
					startingCoord.x(), endingCoord.x(), profile.getValue()),
					Interpolater1D.interpolate(inter, startingCoord.y(),
							endingCoord.y(), profile.getValue()));

			profile.setValue(0);

			startingCoord = actual;
			startingZoom = actualZ;
			endingZoom = zoom;

		}
	}

	public void setEndCoord(final PlanVector coord) {
		if (!VecU.isSame(coord, endingCoord)) {

			final float actualZ = Interpolater1D.interpolate(inter,
					startingZoom, endingZoom, profile.getValue());
			final Vec2 actual = new Vec2(Interpolater1D.interpolate(inter,
					startingCoord.x(), endingCoord.x(), profile.getValue()),
					Interpolater1D.interpolate(inter, startingCoord.y(),
							endingCoord.y(), profile.getValue()));

			profile.setValue(0);

			startingCoord = actual;
			startingZoom = actualZ;
			endingCoord = coord;

		}
	}

	/*---------------------
	 * 
	 * Getters / Setters
	 * 
	 *----------------------*/

	@Override
	public boolean isDone() {
		return !activated || profile.getMinMax().isDone(profile.getValue());
	}

	@Override
	public void setActivate(final boolean activate) {
		activated = activate;

	}

}

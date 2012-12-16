package com.alnaiyr.uinterface.boxes;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import com.alnaiyr.ai.updater.condition.Conditionable;
import com.alnaiyr.commands.Commands;
import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.camera.utilities.CameraUtilities;
import com.alnaiyr.display.renderables.Scalable;
import com.alnaiyr.entity.AEntity;
import com.alnaiyr.entity.SelfAddingToLayer;
import com.alnaiyr.generator.layers.LayerFactory;
import com.alnaiyr.math.geom.GeomU;
import com.alnaiyr.ressources.angelcodefont.LailaAngelCodeFont;
import com.alnaiyr.ressources.angelcodefont.ToroAngelCodeFont;

/**
 * An abstract box, containing a listener, and a graphic condition, on which a
 * transition can be made
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 * 
 */
public abstract class AbstractBox extends AEntity implements Scalable,
		Conditionable, SelfAddingToLayer {

	protected float				scale		= 1;

	private PlanVector			step		= new Vector2f(0, 0);

	public static AngelCodeFont	font		= ToroAngelCodeFont.SQUARE.angelcodefont;

	public boolean				selected	= false;
	public boolean				activated	= false;

	/**
	 * @param coord
	 * @param centered
	 * @param width
	 * @param height
	 */
	public AbstractBox(final PlanVector coord, final boolean centered,
			final float width, final float height) {
		super(coord, centered, width, height);
		if (AbstractBox.font == null)
			AbstractBox.font = LailaAngelCodeFont.SQUARE.angelcodefont;
	}

	/* ************************
	 * 
	 * Methods
	 * 
	 * *****************************
	 */

	@Override
	public void update(final int delta, final boolean condition) {
	}

	@Override
	public void gUpdate(final int delta, final boolean condition) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.alnaiyr.display.debug.Debug#debug(org.newdawn.slick.GameContainer,
	 * org.newdawn.slick.Graphics, boolean)
	 */
	@Override
	public void debug(final GameContainer container, final Graphics g,
			final boolean condition) {
		g.setColor(new Color(1, 1, 0, .5f));
		g.drawRect(getScaledX(), getScaledY(), width(), height());
		g.setColor(Color.white);
	}

	public boolean isMouseOver() {
		return GeomU.isInside(CameraUtilities.screenToCam(
				Commands.getMousePosition(), lay.focus), new Vector2f(
				getScaledX(), getScaledY()), width(), height());
	}

	/* **********************
	 * 
	 * Getters and Setters
	 * 
	 * ***********************************
	 */

	public float getScaledX() {
		return coord.x() - step.x() * scale + step.x();
	}

	public float getScaledY() {
		return coord.y() - step.y() * scale + step.y();
	}

	/**
	 * @return the scale
	 */
	@Override
	public float getScale() {
		return scale;
	}

	@Override
	public PlanVector getCoord() {
		return coord;
	}

	@Override
	public void setStep(final PlanVector step) {
		this.step = step;
	}

	/**
	 * @param scale
	 *            the scale to set
	 */
	@Override
	public void setScale(final float scale) {
		this.scale = scale;
	}

	@Override
	public boolean getCondition() {
		return selected;
	}

	@Override
	public void setCondition(final boolean condition) {
		selected = condition;
	}

	public boolean isActivated() {
		return activated;
	}

	protected void setActivated(final boolean activater) {
		activated = activater;
	}

	@Override
	public void addMyselfTo(final int layerNo) {
		LayerFactory.getInstance().addToLayer(layerNo, this);
	}

}

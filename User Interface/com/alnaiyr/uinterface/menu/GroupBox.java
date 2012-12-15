package com.alnaiyr.uinterface.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.coordinates.utilities.VecU;
import com.alnaiyr.sfx.filters.SFX;
import com.alnaiyr.uinterface.boxes.AbstractBox;

/**
 * A box containing a series of boxes
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public abstract class GroupBox extends AbstractBox {

	/*-------------------------
	 * 
	 * Variables
	 * 
	 *--------------------------*/

	protected List<AbstractBox> boxes;

	protected int width;
	protected int height;

	/*-------------------------
	 * 
	 * Constructor
	 * 
	 *--------------------------*/

	/**
	 * @param coord
	 * @param centered
	 * @param width
	 * @param height
	 */
	public GroupBox(PlanVector coord, boolean centered, float width,
			float height) {
		super(coord, centered, width, height);
		boxes = new ArrayList<>(6);

	}

	/**
	 * @param coord
	 * @param boxes
	 */
	public GroupBox(PlanVector coord, AbstractBox... boxes) {
		super(coord, false, 0, 0);
		this.boxes = Arrays.asList(boxes);
	}

	/*-------------------------
	 * 
	 * Methods
	 * 
	 *--------------------------*/

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.alnaiyr.uinterface.boxes.AbstractBox#debug(org.newdawn.slick.
	 * GameContainer, org.newdawn.slick.Graphics, boolean)
	 */
	@Override
	public void debug(GameContainer container, Graphics g, boolean condition) {
		super.debug(container, g, condition);
		for (AbstractBox box : boxes) {
			box.debug(container, g, condition);
		}
	}

	@Override
	public void setScale(float scale) {
		super.setScale(scale);
		for (AbstractBox box : boxes) {
			box.setScale(scale);
		}

	}

	@Override
	public void setStep(PlanVector step) {
		super.setStep(step);
		for (AbstractBox box : boxes) {
			Vector2f relDist = (Vector2f) VecU.getDifference(box.coord,
					this.coord);
			Vector2f vec = new Vector2f(step);

			box.setStep(vec.add(relDist.negate()));
		}

	}

	public void add(AbstractBox... abstractBoxs) {
		boxes.addAll(Arrays.asList(abstractBoxs));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.alnaiyr.uinterface.boxes.AbstractBox#update(int, boolean)
	 */
	@Override
	public void update(int delta, boolean condition) {
		super.update(delta, condition);
	}

	/*-------------------------
	 * 
	 * Getters / Setters
	 * 
	 *--------------------------*/

	/**
	 * @return the width
	 */
	@Override
	public int width() {
		return width;
	}

	/**
	 * @return the height
	 */
	@Override
	public int height() {
		return height;
	}

	/**
	 * @return the boxes
	 */
	public AbstractBox[] getBoxes() {
		return boxes.toArray(new AbstractBox[boxes.size()]);
	}

	@Override
	public void addMyselfTo(int layerNo) {
		super.addMyselfTo(layerNo);
		for (AbstractBox box : boxes) {
			box.addMyselfTo(layerNo);
		}
	}

	@Override
	public void addSFX(SFX sfx) {
		super.addSFX(sfx);
		for (AbstractBox box : boxes) {
			box.addSFX(sfx);
		}
	}

}

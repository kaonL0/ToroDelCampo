package com.alnaiyr.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.alnaiyr.coordinates.PlanVector;

/**
 * 
 * 
 * @author IgoR from Alnaiyr
 * 
 */
public class DynamicEntity extends AEntity {

	public DynamicEntity(final PlanVector coord, final boolean centered,
			final float width, final float height) {
		super(coord, centered, width, height);
	}

	@Override
	public void update(final int delta, final boolean condition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(final Graphics g, final GameContainer container) {
		// TODO Auto-generated method stub

	}

	@Override
	public int width() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void gUpdate(final int delta, final boolean condition) {
		// TODO Auto-generated method stub

	}

}

package com.alnaiyr.display.impl.advanced;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.alnaiyr.coordinates.dynamic.Origin;
import com.alnaiyr.display.GraphicEntity;

/**
 * 
 * 
 * @author IgoR from Alnaiyr
 * 
 */
public class NullEntity extends GraphicEntity {

	public static NullEntity	instance	= new NullEntity();

	private NullEntity() {
		super(Origin.ref, true, 0, 0);
	}

	@Override
	public void render(final Graphics g, final GameContainer container) {

	}

	@Override
	public int width() {
		return 0;
	}

	@Override
	public int height() {
		return 0;
	}

	@Override
	public void gUpdate(final int delta, final boolean condition) {

	}

}

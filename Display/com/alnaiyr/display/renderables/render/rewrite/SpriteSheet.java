package com.alnaiyr.display.renderables.render.rewrite;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.renderables.DimensionDrawable;

/**
 * 
 * 
 * @author IgoR from Alnaiyr
 * 
 */
public class SpriteSheet extends org.newdawn.slick.SpriteSheet implements
		DimensionDrawable {

	public SpriteSheet(final Image image, final int tw, final int th) {
		super(image, tw, th);
	}

	public SpriteSheet(final String file, final int width, final int height)
			throws SlickException {
		super(file, width, height);
	}

	@Override
	public int width() {

		return super.width();
	}

	@Override
	public int height() {

		return super.height();
	}

	@Override
	public void draw(final PlanVector coord) {
		super.draw(coord.x(), coord.y());
	}

	@Override
	public DataType getType() {
		return DataType.SPRITESHEET;
	}

}

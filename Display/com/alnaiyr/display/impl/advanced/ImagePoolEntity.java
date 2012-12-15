package com.alnaiyr.display.impl.advanced;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.GraphicEntity;
import com.alnaiyr.display.renderables.render.rewrite.Image;

/**
 * 
 * 
 * @author IgoR from Alnaiyr
 * 
 */
public class ImagePoolEntity extends GraphicEntity {

	private final Image[][]	pool;
	private final int		width, height;

	public ImagePoolEntity(final PlanVector coord, final Image[][] images) {
		super(coord, false, images[0][0].width(), images[0][0].height());
		width = images[0][0].width();
		height = images[0][0].height();
		pool = images;
	}

	@Override
	public void render(final Graphics g, final GameContainer container) {

		for (int i = 0; i < pool.length; i++) {
			for (int j = 0; j < pool[i].length; j++) {
				pool[i][j].draw(coord.x() + i * width, coord.y() + j * height);
			}
		}

	}

	@Override
	public int width() {

		return pool[0][0].width() * pool[0].length;
	}

	@Override
	public int height() {
		return pool[0][0].height() * pool[0].length;
	}

	@Override
	public void gUpdate(final int delta, final boolean condition) {
		// TODO Auto-generated method stub

	}

}

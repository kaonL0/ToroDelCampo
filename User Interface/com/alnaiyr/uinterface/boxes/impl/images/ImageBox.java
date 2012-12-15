package com.alnaiyr.uinterface.boxes.impl.images;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.renderables.DimensionDrawable;
import com.alnaiyr.uinterface.boxes.AbstractBox;

/**
 * A box containing an image
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class ImageBox extends AbstractBox {

	private DimensionDrawable image;

	/**
	 * @param coord
	 * @param image
	 * @param centered
	 */
	public ImageBox(PlanVector coord, DimensionDrawable image, boolean centered) {
		super(coord, centered, image.width(), image.height());
		this.image = image;
	}

	@Override
	public void render(Graphics g, GameContainer container) {
		image.draw(coord);
	}

	@Override
	public int width() {
		return (int) (image.width() * scale);
	}

	@Override
	public int height() {
		return (int) (image.height() * scale);
	}

}

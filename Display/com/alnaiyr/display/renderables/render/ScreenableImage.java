package com.alnaiyr.display.renderables.render;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.renderables.DimensionDrawable;
import com.alnaiyr.display.renderables.render.rewrite.DataType;
import com.alnaiyr.display.renderables.render.rewrite.Image;
import com.alnaiyr.general.EV;
import com.alnaiyr.general.IV;

/**
 * An Image able to be resized automatically knowing the size of the screen
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class ScreenableImage implements DimensionDrawable {

	private final Image		image;

	private final boolean	isWidth;

	/**
	 * @param image
	 */
	public ScreenableImage(final Image image, final boolean onWidth) {
		super();
		this.image = image;
		isWidth = onWidth;
	}

	private float getScaleFactor() {
		return isWidth ? (float) IV.getWidth() / image.width() : (float) IV
				.getHeight() / image.height();
	}

	@Override
	public int width() {
		return EV.getWidth();
	}

	@Override
	public int height() {
		return EV.getHeight();
	}

	@Override
	public void draw(final PlanVector coord) {
		image.draw(0, 0, getScaleFactor());
	}

	@Override
	public DataType getType() {
		return DataType.IMAGE;
	}

}

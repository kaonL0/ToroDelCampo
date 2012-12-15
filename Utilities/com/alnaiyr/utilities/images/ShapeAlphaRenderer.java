package com.alnaiyr.utilities.images;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.opengl.pbuffer.FBOGraphics;
import org.newdawn.slick.util.Log;

/**
 * <p>
 * This class can convert a Shape into an alpha mask image made of black and
 * white based on the shape, and sized regarding shape size and orientation. <br>
 * It as to be used once, during initializing, and that's all (as it takes time,
 * depending on shape's size).
 * </p>
 * <p>
 * The way to use it is: <br>
 * <code>Image out= AlphaMaskGenerator yourgenerator.generate(Shape input_shape);</code>
 * </p>
 * 
 * @author IgoR
 * @version 0.8
 * 
 *          <p>
 *          <strong>0.8 Version Changes:</strong><br>
 *          <em>Now should handle all intern Exceptions related to the size.</em>
 *          </p>
 */

public class ShapeAlphaRenderer {

	/* *****************************************
	 * 
	 * Variables
	 * 
	 * 
	 * ****************************************** */

	/** the shape we want to convert to an alpha map */

	private Shape		input_shape;

	/** the image that it will show at the end */

	private Image		alpha_out;

	private Image		bufferimg;

	/** the local context used to render the shape */
	private FBOGraphics	context;

	/* *****************************************
	 * 
	 * Constructor
	 * 
	 * 
	 * ****************************************** */

	/**
	 * Creates a new generator, based on a maximum size
	 * 
	 * @param gwidth
	 *            the width of the FBO
	 * @param gheight
	 *            the height of the FBO
	 * @throws SlickException
	 */
	public ShapeAlphaRenderer(int gwidth, int gheight) throws SlickException {

		try {
			bufferimg = new Image(gwidth, gheight);
			context = new FBOGraphics(bufferimg);
		} catch (java.lang.OutOfMemoryError e) {
			Log.warn(" Failed to create an FBO of such a size, size reduced...");
			bufferimg = new Image(800, 600);
			context = new FBOGraphics(bufferimg);
		}
	}

	/* ***************************
	 * 
	 * 
	 * Main Method
	 * 
	 * 
	 * **************************** */
	/**
	 * Generates the alpha mask of the shape, not matter where it is<br>
	 * Warning: the size of the shape must be inferior to the FBO dimensions
	 * 
	 * @param input
	 * @return generated image
	 */

	public Image generate(Shape input) {
		return generate(input, input.getCenterX() - input.getWidth() / 2, input.getCenterY() - input.getHeight() / 2);
	}

	/**
	 * Same as {@link #generate(Shape input)}, adds as a parameter offsets, in
	 * case we want to center on the shape in a different way than by its
	 * center.<br>
	 * The top left corner of the image being the offset.
	 * 
	 * @param input
	 * @param offsetX
	 *            where you start to generate image
	 * @param offsetY
	 *            where you start to generate image
	 * @return generated image
	 */

	public Image generate(Shape input, float offsetX, float offsetY) {

		input_shape = input;
		if (input_shape.getWidth() > bufferimg.width() | input_shape.getHeight() > bufferimg.height())
			Log.warn("The shape dimensions are too big for the FBO: image will be cut");
		// new image big enough for the shape
		context.clear(); // make sure nothing remains in the context
		context.setAntiAlias(true);
		// make sure to have the image well centered
		context.translate(-offsetX, -offsetY);

		context.draw(this.input_shape);
		context.fill(this.input_shape);
		context.flush();
		alpha_out = bufferimg.getSubImage(0, 0, (int) input_shape.getWidth() + 1, (int) input_shape.getHeight() + 1);

		return alpha_out;
	}

	/**
	 * 
	 *      With images margins: The offsets wil be the top right coordinates,
	 *      the margins will be the width and the height of the image
	 * 
	 * @param input
	 * @param offsetX
	 * @param offsetY
	 * @param marginX
	 *            (understand the rightest x margin)
	 * @param marginY 
	 * @return generated image
	 */
	public Image generate(Shape input, float offsetX, float offsetY, int marginX, int marginY) {

		input_shape = input;
		if (input_shape.getWidth() > bufferimg.width() | input_shape.getHeight() > bufferimg.height())
			Log.warn("The shape dimensions are too big for the FBO: image will be cut");
		// new image big enough for the shape
		context.clear(); // make sure nothing remains in the context
		context.setAntiAlias(true);
		// make sure to have the image well centered
		context.translate(-offsetX, -offsetY);

		context.draw(this.input_shape);
		context.fill(this.input_shape);
		context.flush();
		alpha_out = bufferimg.getSubImage(0, 0, marginX, marginY);

		return alpha_out;
	}
}

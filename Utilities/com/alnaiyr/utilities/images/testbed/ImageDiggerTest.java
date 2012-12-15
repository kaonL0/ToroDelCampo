package com.alnaiyr.utilities.images.testbed;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import com.alnaiyr.generator.noise.NoiseGenerator;
import com.alnaiyr.math.functions.interpolations.Interpolation;
import com.alnaiyr.math.functions.interpolations.Smoothing;
import com.alnaiyr.utilities.example.Example;
import com.alnaiyr.utilities.images.FunctionRenderer;
import com.alnaiyr.utilities.images.ImageDigger;

/**
 * This test demontrate the image shape drawer
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>Works</em></li>
 *          </ul>
 */
public class ImageDiggerTest extends Example {

	Image test;

	/**
	 * @param title
	 * @param always
	 */
	public ImageDiggerTest(String title) {

		super(title);
	}

	@Override
	public void renderIt(GameContainer arg0, Graphics arg1)
			throws SlickException {
		test.draw();
	}

	@Override
	public void initialize(GameContainer container) throws SlickException {

		test = FunctionRenderer.generateImage(NoiseGenerator.generateMidPoint(
				400, 400, 800, 400, true, .5f, 7, Smoothing.GLOBAL,
				Interpolation.COSINUS), 200, container);

	}

	@Override
	public void updateIt(GameContainer container, int delta)
			throws SlickException {
		if (container.getInput().isMouseButtonDown(0))
			ImageDigger.damage(new Rectangle(container.getInput().getMouseX(),
					container.getInput().getMouseY(), 50, 50), test,
					Color.black, container);
	}

	/** @param args */
	public static void main(String[] args) {

		AppGameContainer app;
		try {
			app = new AppGameContainer(new ImageDiggerTest("Image digger Test"));
			app.setDisplayMode(800, 800, false);
			app.setTargetFrameRate(60);
			app.setVSync(true);
			app.setShowFPS(true);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}
}

package com.alnaiyr.utilities.images.testbed;

import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.util.Log;

import com.alnaiyr.utilities.images.ShapeAlphaRenderer;

/**
 * A simple test to show performances for shape to alpha mask converting, plus
 * shows dimensions
 * 
 * @author IgoR
 * @version 1.1
 *          <p>
 *          <strong>Version change:</strong><br/>
 *          <em> now should handle all Exceptions </em>
 *          </p>
 */

public class AlphaMaskTest extends BasicGame {

	private ArrayList<Image> images = new ArrayList<Image>();

	private Image relief;

	private ShapeAlphaRenderer alphaGen;

	private int i = 0;

	/** Create the test */
	public AlphaMaskTest() {

		super("Alpha mask");
	}

	/** @see org.newdawn.slick.BasicGame#init(org.newdawn.slick.GameContainer) */
	@Override
	public void init(GameContainer container) throws SlickException {

		System.out.print(i);
		if (alphaGen == null)
			alphaGen = new ShapeAlphaRenderer(8000, 8000);
		i++;
		for (int i = 0; i < 20; i++) {
			relief = alphaGen.generate(new Circle(100 + i, 100 + i, 50));
			images.add(relief);
		}
	}

	/**
	 * @see org.newdawn.slick.BasicGame#update(org.newdawn.slick.GameContainer,
	 *      int)
	 */
	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {

		if (container.getInput().isKeyDown(Input.KEY_ESCAPE))
			container.exit();
		if (container.getInput().isKeyDown(Input.KEY_F1)) {
			container.reinit();
		}
	}

	/**
	 * @see org.newdawn.slick.Game#render(org.newdawn.slick.GameContainer,
	 *      org.newdawn.slick.Graphics)
	 */
	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {

		for (int i = 0; i < images.size(); i++) {
			images.get(i).draw(0, 0);
			g.setColor(Color.green);
			g.draw(new Rectangle(50 + i * 4, 50 + i * 4, images.get(i)
					.width() + 1, images.get(i).height() + 1));
			g.setColor(Color.white);
		}
	}

	/**
	 * Entry point to our test
	 * 
	 * @param argv
	 *            The arguments to pass into the test
	 */
	public static void main(String[] argv) {
		try {
			AppGameContainer container = new AppGameContainer(
					new AlphaMaskTest());
			container.setDisplayMode(800, 600, false);
			container.start();
		} catch (org.lwjgl.opengl.OpenGLException e) {
			Log.error(e);
			Log.info("Try to surround Alphamask generator creation with a if(generator==null) to avoid that");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

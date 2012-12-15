package com.alnaiyr.physics.destruction.testbed;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.alnaiyr.coordinates.dynamic.Origin;
import com.alnaiyr.physics.destruction.shapes.DamageAngledRectangle;
import com.alnaiyr.physics.destruction.shapes.DamageRectangle;
import com.alnaiyr.utilities.debug.DB;
import com.alnaiyr.utilities.example.Example;

/**
 * This example shows how damage shape can be displayed maps
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>Works</em></li>
 *          </ul>
 *          </p>
 */
public class DamageShapeTest extends Example {

	@SuppressWarnings("unused")
	private DamageRectangle rectangle;

	@SuppressWarnings("unused")
	private DamageAngledRectangle circle;

	public DamageShapeTest(String title) {
		super(title);
	}

	@Override
	public void renderIt(GameContainer container, Graphics g)
			throws SlickException {

		g.pushTransform();
		g.resetTransform();
		g.drawString("press TAB to change shape, 1 2 3 for debug modes", 50, 20);
		g.popTransform();

	}

	@Override
	public void initialize(GameContainer container) throws SlickException {
		DB.test("circle");
		circle = new DamageAngledRectangle(new int[] { 400, 300 }, 2,
				new Origin(0, 0, false), 100);
		DB.test("rectangle");
		rectangle = new DamageRectangle(new int[] { 400, 300 }, 2, new Origin(
				0, 0, false), 100);

	}

	@Override
	public void updateIt(GameContainer container, int delta)
			throws SlickException {
	}

	/** @param args */
	public static void main(String[] args) {

		AppGameContainer app;
		try {
			app = new AppGameContainer(new DamageShapeTest(
					"Destruction Shape Test"));
			app.setDisplayMode(800, 600, false);
			app.setVSync(true);
			app.setShowFPS(true);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}
}

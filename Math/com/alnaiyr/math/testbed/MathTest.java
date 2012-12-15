package com.alnaiyr.math.testbed;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.alnaiyr.coordinates.utilities.VecU;
import com.alnaiyr.utilities.debug.DB;
import com.alnaiyr.utilities.example.Example;

/**
 * Simple test on some math methods
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class MathTest extends Example {

	/**
	 * @param title
	 */
	public MathTest(String title) {
		super(title);
	}

	@SuppressWarnings("boxing")
	@Override
	public void initialize(GameContainer container) throws SlickException {
		DB.test(VecU.isInside(0, 0, 1, 1, 3, 3));
	}

	@Override
	public void updateIt(GameContainer container, int delta)
			throws SlickException {

	}

	@Override
	public void renderIt(GameContainer container, Graphics g)
			throws SlickException {

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AppGameContainer app;
		try {
			app = new AppGameContainer(new MathTest("math"));
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

}

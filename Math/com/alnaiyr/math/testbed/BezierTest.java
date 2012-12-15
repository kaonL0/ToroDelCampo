package com.alnaiyr.math.testbed;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import com.alnaiyr.math.geom.shape.segments.BezierSegment;
import com.alnaiyr.math.geom.shape.segments.LinearGroupSegment;
import com.alnaiyr.math.geom.shape.segments.Segment;
import com.alnaiyr.utilities.example.Example;

/**
 * Bezier Curves test
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class BezierTest extends Example {

	/* ****************
	 * 
	 * Variables
	 * 
	 * ***************
	 */

	private BezierSegment seg;
	private Segment seg2;

	/* ****************
	 * 
	 * Constructors
	 * 
	 * ***************
	 */
	/**
	 * 
	 * @param title
	 */
	public BezierTest(String title) {
		super(title);

	}

	/* ****************
	 * 
	 * Methods
	 * 
	 * ***************
	 */
	@Override
	public void initialize(GameContainer container) throws SlickException {
		seg = new BezierSegment(new Vector2f(0, 0), new Vector2f(0, 700),
				new Vector2f(0, 300), new Vector2f(50, 300), new Vector2f(400,
						300), new Vector2f(700, 500));
		seg2 = new LinearGroupSegment(seg.convertInSegments(10));
	}

	@Override
	public void updateIt(GameContainer container, int delta)
			throws SlickException {
	}

	@Override
	public void renderIt(GameContainer container, Graphics g)
			throws SlickException {
		g.setColor(Color.white);
		seg.debug(container, g, true);
		g.setColor(Color.red);
		seg2.debug(container, g, true);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(
					new BezierTest("bezier"));
			app.setDisplayMode(800, 600, false);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}
	/* ****************
	 * 
	 * Getters / Setters
	 * 
	 * ***************
	 */

}

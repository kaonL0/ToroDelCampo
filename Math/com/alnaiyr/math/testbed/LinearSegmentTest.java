package com.alnaiyr.math.testbed;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.math.geom.shape.segments.LinearSegment;
import com.alnaiyr.math.geom.shape.segments.Segment;
import com.alnaiyr.utilities.example.Example;

/**
 * Linear segment test
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class LinearSegmentTest extends Example {

	private Segment seg;
	private Rectangle rec;
	private PlanVector coord;

	/* ****************
	 * 
	 * Variables
	 * 
	 * ***************
	 */

	/* ****************
	 * 
	 * Constructors
	 * 
	 * ***************
	 */

	/**
	 * @param title
	 */
	public LinearSegmentTest(String title) {
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
		seg = new LinearSegment(0, 0, 800, 600);
		rec = new Rectangle(0, 0, 50, 50);
		coord = new Vector2f(0, 0);
	}

	@Override
	public void updateIt(GameContainer container, int delta)
			throws SlickException {
		rec.setLocation(coord.x(), coord.y());
		seg.bindCoordinate(.2f, coord);
	}

	@Override
	public void renderIt(GameContainer container, Graphics g)
			throws SlickException {
		g.draw(rec);
		seg.debug(container, g, true);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new LinearSegmentTest(
					"bezier"));
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

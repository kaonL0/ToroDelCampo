package com.alnaiyr.physics.movement.testbed;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.math.geom.shape.segments.BezierSegment;
import com.alnaiyr.math.geom.shape.segments.LinearSegment;
import com.alnaiyr.math.numbers.profiles.behaves.limit.LimitBehaves;
import com.alnaiyr.physics.movement.itinerary.Itinary;
import com.alnaiyr.physics.movement.itinerary.SpeedItinaryProfile;
import com.alnaiyr.utilities.example.Example;

/**
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class ItinaryTest extends Example {

	private Itinary it;
	private PlanVector coord;

	private PlanVector coord2;
	private PlanVector coord3;

	private PlanVector coord4;
	private SpeedItinaryProfile prof;

	/* ****************
	 * 
	 * Variables
	 * 
	 * ***************
	 */

	private Rectangle rec;

	/* ****************
	 * 
	 * Constructors
	 * 
	 * ***************
	 */

	/**
	 * @param title
	 */
	public ItinaryTest(String title) {
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
		// it= new Itinary(new LinearSegment(new Vector2f(100,100),new
		// Vector2f(700,500)));
		it = new Itinary(new LinearSegment(new Vector2f(100, 100),
				new Vector2f(200, 100)));

		it.addSegment(new Itinary(new BezierSegment(new Vector2f(200, 300),
				new Vector2f(400, 0), new Vector2f(700, 400))
				.convertInSegments(10)));

		it.limit = LimitBehaves.LOOPBACK;

		coord = new Vector2f(0, 0);

		prof = new SpeedItinaryProfile(it, coord, .2f);

		rec = new Rectangle(0, 0, 50, 50);
	}

	@Override
	public void renderIt(GameContainer container, Graphics g)
			throws SlickException {
		g.draw(rec);
		if (coord2 != null) {
			g.drawRect(coord2.x(), coord2.y(), 50, 50);
			g.drawRect(coord3.x(), coord3.y(), 50, 50);
			g.drawRect(coord4.x(), coord4.y(), 50, 50);
		}
		it.debug(container, g, true);
	}

	@Override
	public void updateIt(GameContainer container, int delta)
			throws SlickException {
		prof.update(it, delta, true);

		coord2 = prof.getPreviousCoordinate(it, 60);
		coord3 = prof.getPreviousCoordinate(it, 120);
		coord4 = prof.getPreviousCoordinate(it, 180);
		rec.setLocation(coord.x(), coord.y());
	}

	/* ****************
	 * 
	 * Getters / Setters
	 * 
	 * ***************
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new ItinaryTest(
					"itinary test"));
			app.setDisplayMode(800, 600, false);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}

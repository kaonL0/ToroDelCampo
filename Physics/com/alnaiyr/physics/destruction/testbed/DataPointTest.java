package com.alnaiyr.physics.destruction.testbed;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import com.alnaiyr.coordinates.dynamic.Origin;
import com.alnaiyr.math.geom.shape.DataPoint;
import com.alnaiyr.physics.destruction.shapes.DamageAngledRectangle;
import com.alnaiyr.utilities.example.Example;

public class DataPointTest extends Example {

	public DamageAngledRectangle rec;
	public DataPoint point;

	public DataPointTest(String title, boolean always) {
		super(title);
	}

	@Override
	public void initialize(GameContainer container) throws SlickException {
		rec = new DamageAngledRectangle(new int[] { 200, 200 }, 2, new Origin(
				0, 0, false), 400);
		point = new DataPoint(rec.getStart(), rec.getStart(), rec.getStart(),
				2, rec.getMap().getStep());
	}

	@Override
	public void updateIt(GameContainer container, int delta)
			throws SlickException {
		point.toNext(rec.getMap(), 2);
	}

	@Override
	public void renderIt(GameContainer container, Graphics g)
			throws SlickException {
		g.translate(200, 250);
		g.draw(new Rectangle(point.getCoord()[0] * 2, point.getCoord()[1] * 2,
				5, 5));
	}

	/** @param args */
	public static void main(String[] args) {

		AppGameContainer app;
		try {
			app = new AppGameContainer(new DataPointTest("Destruction Test",
					true));
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

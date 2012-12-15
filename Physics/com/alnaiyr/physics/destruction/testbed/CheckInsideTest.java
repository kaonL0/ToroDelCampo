package com.alnaiyr.physics.destruction.testbed;

import java.util.HashMap;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import com.alnaiyr.coordinates.dynamic.Cartesian;
import com.alnaiyr.generator.noise.NoiseGenerator;
import com.alnaiyr.math.functions.interpolations.Interpolation;
import com.alnaiyr.math.functions.interpolations.Smoothing;
import com.alnaiyr.math.geom.shape.DataPoint;
import com.alnaiyr.physics.destruction.shapes.DamageRectangle;
import com.alnaiyr.physics.destruction.shapes.TerrainDamageHandler;
import com.alnaiyr.terrain.ground.TerrainTile;
import com.alnaiyr.utilities.debug.DB;
import com.alnaiyr.utilities.example.Example;

/**
 * This example shows how intersection detection is performed
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>Works</em></li>
 *          </ul>
 */
public class CheckInsideTest extends Example {

	private HashMap<Integer, Float> relief;

	private TerrainTile terrainTile;

	private Cartesian or = new Cartesian(0, 0, false);

	private TerrainDamageHandler dmg = new TerrainDamageHandler();

	private DamageRectangle rec;

	private DamageRectangle pointRec;

	private Rectangle firstinter;

	private Rectangle secondinter;

	public CheckInsideTest(String title) {

		super(title);
		relief = new HashMap<Integer, Float>();
	}

	@Override
	public void renderIt(GameContainer container, Graphics g)
			throws SlickException {

		terrainTile.render(g, container);
	}

	@Override
	public void renderAfter(GameContainer container, Graphics g) {
		g.pushTransform();
		g.translate(0, 400);
		g.setColor(Color.white);
		g.setColor(Color.red);
		terrainTile.debugAlways(g);

		// g.draw(terrainTile.getDebug().convert(terrainTile));

		g.draw(new Rectangle(0, 0, 20, 20));
		g.setColor(Color.white);

		g.setColor(Color.red);
		g.draw(firstinter);
		g.drawString(firstinter.getX() + ", " + firstinter.getY(),
				firstinter.getX(), firstinter.getY());
		g.draw(secondinter);
		g.drawString(secondinter.getX() + ", " + secondinter.getY(),
				secondinter.getX(), secondinter.getY());
		g.setColor(Color.white);
		g.popTransform();
		g.draw(pointRec.convert(terrainTile));

	}

	@Override
	public void initialize(GameContainer container) throws SlickException {

		// setAlwaysDebug(true);
		try {
			relief = NoiseGenerator.generateMidPoint(200, 200, 800, 400, true,
					.6f, 6, Smoothing.LOCAL, Interpolation.COSINUS);
			or = new Cartesian(0, 0, false);
			terrainTile = new TerrainTile(or, relief, 100, 2, container);

			rec = new DamageRectangle(new int[] { 0, 0 }, 2, or, 20);

			// circl= new DamageCircle(new Vector2f(0, 0), or, 2,30);

			pointRec = rec;
			pointRec.setCenter(new int[] { 100, 100 }, true, terrainTile);

			firstinter = new Rectangle(0, 0, 5, 5);
			secondinter = new Rectangle(0, 0, 5, 5);

		} catch (ArrayIndexOutOfBoundsException e) {
			container.reinit();
		} catch (NullPointerException e) {
			container.reinit();
		}
		// alpham.damage(new Circle(0, 0, 10), terrainTile);

	}

	@Override
	public void updateIt(GameContainer container, int delta)
			throws SlickException {

		if (container.getInput().isKeyPressed(Input.KEY_SPACE)) {
			dmg.addDamage(pointRec);
			dmg.damage(terrainTile, container);

			List<DataPoint> inside;
			int[] center = pointRec.getPointToRef(pointRec.getCenter());
			int iter = 0;

			while (pointRec.getWidth() > terrainTile.getMap().getStep() * 2) {

				iter++;
				pointRec.addSize(-terrainTile.getMap().getStep() * 2);
				pointRec.setCenter(center, true, terrainTile);

				inside = pointRec.findAllCommonPoint(terrainTile,
						new DataPoint(pointRec.getStart(), pointRec.getStart(),
								pointRec.getStart(), 2, 20));
				DB.test(inside.size());
				for (DataPoint point : inside) {
					DB.test("inside");
					container.getGraphics().draw(
							new Rectangle(point.getCoord(2)[0], point
									.getCoord(2)[1], 50, 50));
					/* terrainTile.destroyAll(pointRec, point); */
				}
				pointRec.addSize(-terrainTile.getMap().getStep() * 2);
			}
			pointRec.addSize(terrainTile.getMap().getStep() * 2 * (iter + 2));
			iter = 1;

		}

		if (container.getInput().isMousePressed(1)) {
			// int[] center=pointRec.getPointToRef(pointRec.getCenter());
			pointRec.multiplySize(2);
			// pointRec.setCenter(center,true, terrainTile);
		}
		if (container.getInput().isMousePressed(0)) {
			// int[] center=pointRec.getPointToRef(pointRec.getCenter());
			if (pointRec.getHeight() > 1)
				pointRec.multiplySize(.5f);
			else
				pointRec.multiplySize(2);
			// pointRec.setCenter(center,true, terrainTile);
		}

		int[] corner = new int[] { container.getInput().getMouseX(),
				container.getInput().getMouseY() };

		// pointRec.setCorner(corner,true,terrainTile);
		pointRec.setCenter(corner, true, terrainTile);

		List<DataPoint> intersect = rec.findAllIntersections(terrainTile,
				rec.getStart(), rec.getEnd());

		if (!intersect.isEmpty()) {
			firstinter.setCenterX(rec
					.getPointToRef(intersect.get(0).getCoord())[0]
					* terrainTile.getMap().getStep());
			firstinter.setCenterY(rec
					.getPointToRef(intersect.get(0).getCoord())[1]
					* terrainTile.getMap().getStep());
		}
		if (intersect.size() > 1) {
			secondinter.setCenterX(rec.getPointToRef(intersect.get(1)
					.getCoord())[0] * terrainTile.getMap().getStep());
			secondinter.setCenterY(rec.getPointToRef(intersect.get(1)
					.getCoord())[1] * terrainTile.getMap().getStep());
		}

	}

	/** @param args */
	public static void main(String[] args) {

		AppGameContainer app;
		try {
			app = new AppGameContainer(new CheckInsideTest("Destruction Test"));
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

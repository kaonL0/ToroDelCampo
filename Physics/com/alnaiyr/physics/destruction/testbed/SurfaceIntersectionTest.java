package com.alnaiyr.physics.destruction.testbed;

import java.util.ArrayList;
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
import com.alnaiyr.math.MathU;
import com.alnaiyr.math.functions.interpolations.Interpolation;
import com.alnaiyr.math.functions.interpolations.Smoothing;
import com.alnaiyr.math.geom.shape.DataPoint;
import com.alnaiyr.physics.destruction.shapes.DamageAngledRectangle;
import com.alnaiyr.physics.destruction.shapes.TerrainDamageHandler;
import com.alnaiyr.terrain.ground.TerrainTile;
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
public class SurfaceIntersectionTest extends Example {

	private HashMap<Integer, Float> relief;

	private TerrainTile terrainTile;

	private Cartesian or = new Cartesian(0, 0, false);

	private DamageAngledRectangle rec;

	private Rectangle firstinter;

	private Rectangle secondinter;

	private Rectangle thirdinter;

	private Rectangle fourthinter;

	private TerrainDamageHandler dmg = new TerrainDamageHandler();

	public SurfaceIntersectionTest(String title) {

		super(title);
		relief = new HashMap<>();
	}

	@Override
	public void renderIt(GameContainer container, Graphics g)
			throws SlickException {
		terrainTile.render(g, container);
		rec.debugAlways(g);
		g.setColor(Color.red);
		g.draw(new Rectangle(or.x(), or.y(), 50, 50));
		g.draw(new Rectangle(0, 200, 50, 50));
	}

	@Override
	public void renderAfter(GameContainer container, Graphics g) {
		g.setColor(Color.red);
		g.draw(firstinter);
		g.drawString("1 ", firstinter.getX(), firstinter.getY());
		g.draw(secondinter);
		g.drawString("2 ", secondinter.getX(), secondinter.getY());
		g.draw(thirdinter);
		g.drawString("3 ", thirdinter.getX(), thirdinter.getY());
		g.draw(fourthinter);
		g.drawString("4 ", fourthinter.getX(), fourthinter.getY());
		g.setColor(Color.white);
	}

	@Override
	public void initialize(GameContainer container) throws SlickException {

		try {
			relief = NoiseGenerator.generateMidPoint(200, 200, 800, 400, true,
					.6f, 6, Smoothing.LOCAL, Interpolation.COSINUS);
			or = new Cartesian(0, 0, false);
			terrainTile = new TerrainTile(or, relief, 100, 4, container);
			// terrainTile.removeFrom();
			rec = new DamageAngledRectangle(new int[] { 0, 0 }, 4, or, 100);
			firstinter = new Rectangle(0, 0, 5, 5);
			secondinter = new Rectangle(0, 0, 5, 5);
			thirdinter = new Rectangle(0, 0, 5, 5);
			fourthinter = new Rectangle(0, 0, 5, 5);
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

		rec.setCorner(MathU.roundTo(container.getInput().getMouseX(),
				terrainTile.getMap().getStep()), MathU.roundTo(container
				.getInput().getMouseY(), terrainTile.getMap().getStep()), true,
				terrainTile);

		List<DataPoint> intersect = rec.findAllIntersections(terrainTile,
				rec.getStart(), rec.getEnd());

		List<Integer> dir = new ArrayList<>(4);

		if (container.getInput().isKeyDown(Input.KEY_SPACE)) {
			for (int i = 0; i < intersect.size(); i++) {
				dir.add((int) terrainTile.getOutsideDirection(rec,
						intersect.get(i)));
			}

			dmg.addDamage(rec);
			dmg.damage(terrainTile, container);

		}

		/*
		 * GV.test("------------------"); for(DataPoint point: intersect){
		 * GV.test(point.getCoord()); }
		 */

		firstinter.setCenterX(0);
		firstinter.setCenterY(0);

		secondinter.setCenterX(0);
		secondinter.setCenterY(0);

		thirdinter.setCenterX(0);
		thirdinter.setCenterY(0);

		fourthinter.setCenterX(0);
		fourthinter.setCenterY(0);

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
		if (intersect.size() > 2) {
			thirdinter.setCenterX(rec
					.getPointToRef(intersect.get(2).getCoord())[0]
					* terrainTile.getMap().getStep());
			thirdinter.setCenterY(rec
					.getPointToRef(intersect.get(2).getCoord())[1]
					* terrainTile.getMap().getStep());
		}
		if (intersect.size() > 3) {
			fourthinter.setCenterX(rec.getPointToRef(intersect.get(3)
					.getCoord())[0] * terrainTile.getMap().getStep());
			fourthinter.setCenterY(rec.getPointToRef(intersect.get(3)
					.getCoord())[1] * terrainTile.getMap().getStep());
		}
	}

	/** @param args */
	public static void main(String[] args) {

		AppGameContainer app;
		try {
			app = new AppGameContainer(new SurfaceIntersectionTest(
					"Intersection Test"));
			app.setDisplayMode(800, 600, false);
			app.setShowFPS(true);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}
}

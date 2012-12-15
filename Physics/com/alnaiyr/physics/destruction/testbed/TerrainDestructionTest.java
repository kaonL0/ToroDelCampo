package com.alnaiyr.physics.destruction.testbed;

import java.util.HashMap;

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
import com.alnaiyr.physics.destruction.shapes.DamageRectangle;
import com.alnaiyr.physics.destruction.shapes.TerrainDamageHandler;
import com.alnaiyr.terrain.ground.TerrainTile;
import com.alnaiyr.utilities.debug.DB;
import com.alnaiyr.utilities.example.Example;

// import com.alnaiyr.physics.destruction.DamageAngledRectangle;

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
public class TerrainDestructionTest extends Example {

	private HashMap<Integer, Float> relief;

	private TerrainTile terrainTile;

	private Cartesian or = new Cartesian(0, 0, false);

	private TerrainDamageHandler dmg = new TerrainDamageHandler();

	// private DamageAngledRectangle rec;

	private DamageRectangle pointRec;

	private Rectangle firstinter;

	private Rectangle secondinter;

	public TerrainDestructionTest(String title) {

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

		g.setColor(Color.red);
		g.draw(terrainTile.getDebug().convert(terrainTile));
		g.draw(pointRec.convert(terrainTile));
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

	}

	@Override
	public void initialize(GameContainer container) throws SlickException {

		// setAlwaysDebug(true);
		try {
			relief = NoiseGenerator.generateMidPoint(200, 200, 800, 400, true,
					.6f, 6, Smoothing.LOCAL, Interpolation.COSINUS);
			or = new Cartesian(0, 0, false);
			terrainTile = new TerrainTile(or, relief, 100, 10, container);

			// rec = new DamageAngledRectangle(new int[]{0,0},10, or,50);

			// circl= new DamageCircle(new Vector2f(0, 0), or, 2,30);

			pointRec = new DamageRectangle(new int[] { 0, 0 }, 10, or, 50);
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
			DB.test();
		}

		if (container.getInput().isMouseButtonDown(1)) {
			int[] center = pointRec.getPointToRef(pointRec.getCenter());
			pointRec.addSize(-terrainTile.getMap().getStep() * 2);
			pointRec.setCenter(center, true, terrainTile);
		}

		if (container.getInput().isMouseButtonDown(0)) {
			int[] center = pointRec.getPointToRef(pointRec.getCenter());
			pointRec.addSize(terrainTile.getMap().getStep() * 2);
			pointRec.setCenter(center, true, terrainTile);
		}

		int[] corner = new int[] { container.getInput().getMouseX(),
				container.getInput().getMouseY() };

		// pointRec.setCorner(corner,true,terrainTile);
		pointRec.setCenter(corner, true, terrainTile);

		/*
		 * List<DataPoint> intersect = rec.findAllIntersections(terrainTile,
		 * rec.getStart(), rec.getEnd());
		 * 
		 * if (!intersect.isEmpty()) {
		 * firstinter.setCenterX(rec.getPointToRef(intersect
		 * .get(0).getCoord())[0] * terrainTile.getMap().getStep());
		 * firstinter.setCenterY
		 * (rec.getPointToRef(intersect.get(0).getCoord())[1] *
		 * terrainTile.getMap().getStep()); } if (intersect.size() > 1) {
		 * secondinter
		 * .setCenterX(rec.getPointToRef(intersect.get(1).getCoord())[0] *
		 * terrainTile.getMap().getStep());
		 * secondinter.setCenterY(rec.getPointToRef
		 * (intersect.get(1).getCoord())[1] * terrainTile.getMap().getStep()); }
		 */
	}

	/** @param args */
	public static void main(String[] args) {

		AppGameContainer app;
		try {
			app = new AppGameContainer(new TerrainDestructionTest(
					"Destruction Test"));
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

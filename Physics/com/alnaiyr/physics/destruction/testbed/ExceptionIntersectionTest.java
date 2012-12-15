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
import com.alnaiyr.math.functions.interpolations.Interpolation;
import com.alnaiyr.math.functions.interpolations.Smoothing;
import com.alnaiyr.physics.destruction.shapes.DamageAngledRectangle;
import com.alnaiyr.physics.destruction.shapes.DamageRectangle;
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
public class ExceptionIntersectionTest extends Example {

	private HashMap<Integer, Float> relief;

	private TerrainTile terrainTile;

	private Cartesian or = new Cartesian(0, 0, false);

	private DamageAngledRectangle rec;

	private DamageRectangle rec2;

	List<int[]> axe = new ArrayList<int[]>();

	public ExceptionIntersectionTest(String title) {

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

		g.setColor(Color.cyan);
		g.draw(terrainTile.getDebug().convert(terrainTile));
		g.draw(rec.convert(terrainTile));

		g.setColor(Color.red);

		if (!axe.isEmpty()) {
			for (int[] point : axe)
				g.draw(new Rectangle(rec.getPointToRef(point)[0] * 2, rec
						.getPointToRef(point)[1] * 2, 20, 20));
			axe.clear();
		}
		g.setColor(Color.white);
	}

	@Override
	public void initialize(GameContainer container) throws SlickException {

		try {
			relief = NoiseGenerator.generateMidPoint(200, 200, 800, 400, true,
					.6f, 6, Smoothing.LOCAL, Interpolation.COSINUS);
			or = new Cartesian(0, 0, false);
			terrainTile = new TerrainTile(or, relief, 100, 10, container);
			rec = new DamageAngledRectangle(new int[] { 0, 0 }, 10, or, 50);
			rec2 = new DamageRectangle(new int[] { 0, 0 }, 10, or, 40);
		} catch (ArrayIndexOutOfBoundsException e) {
			container.reinit();
		} catch (NullPointerException e) {
			container.reinit();
		}

	}

	@Override
	public void updateIt(GameContainer container, int delta)
			throws SlickException {

		List<int[]> exceptions = new ArrayList<int[]>();
		exceptions = rec.findAllExceptions(terrainTile, rec.getStart(),
				rec.getEnd()).get(0);

		if (!exceptions.isEmpty()) {
			for (int[] current : exceptions) {
				terrainTile.getDebug().setCenter(rec.getPointToRef(current),
						false, terrainTile);
				rec2.setCenter(rec.getPointToRef(current), false, terrainTile);
			}

			axe = new ArrayList<int[]>(exceptions);
		}

		if (container.getInput().isKeyPressed(Input.KEY_SPACE)) {
			TerrainDamageHandler hand = new TerrainDamageHandler();
			hand.addDamage(rec);
			hand.damage(terrainTile, container);
		}
		if (container.getInput().isMousePressed(1)) {
			rec.multiplySize(2);
		}
		if (container.getInput().isMousePressed(0)) {
			// rec.multiplySize(.5f);
		}

		int[] corner = new int[] { container.getInput().getMouseX(),
				container.getInput().getMouseY() };

		// rec.setCorner(corner,true,terrainTile);
		rec.setCenter(corner, true, terrainTile);

		/*
		 * List<int[]>
		 * exceptions=pointRec.findAllExceptions(terrainterrainTile,pointRec
		 * .getStart(),pointRec.getEnd()); for(int[] current: exceptions){
		 * 
		 * pointRec.solveException(terrainterrainTile,
		 * current,pointRec.getPointToRef(current)); }
		 */
	}

	/** @param args */
	public static void main(String[] args) {

		AppGameContainer app;
		try {
			app = new AppGameContainer(new ExceptionIntersectionTest(
					"Exception Test"));
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

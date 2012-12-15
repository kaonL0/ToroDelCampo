/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.test.jbox2D;

import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.collision.shapes.EdgeShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.alnaiyr.utilities.debug.jbox2D.SlickDebugDraw;
import com.alnaiyr.utilities.example.Example;

/**
 * Sucess!
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class Jbox2DTest extends Example {

	public SlickDebugDraw	debug;
	public World			world;

	public Body				ground;
	public Body				surf;

	public float			hz			= 60;
	public float			elapsedTime	= 0;

	public Jbox2DTest(final String title) {
		super(title);
	}

	@Override
	public void initialize(final GameContainer container) throws SlickException {

		debug = new SlickDebugDraw(container);
		// debug.getViewportTranform().setCenter(400, 300);
		debug.getViewportTranform().setExtents(new Vec2(400, 300));
		debug.getViewportTranform().setCamera(0, 0, 50);

		debug.getViewportTranform().setYFlip(true);

		debug.clearFlags(DebugDraw.e_centerOfMassBit);
		debug.appendFlags(DebugDraw.e_shapeBit);

		world = new World(new Vec2(0, -5f));
		world.setDebugDraw(debug);

		// FixtureDef fd = new FixtureDef();
		// fd.restitution = .5f;
		//
		// BodyDef bd = new BodyDef();
		// bd.type = BodyType.DYNAMIC;
		// bd.position = new Vec2(0, 2);
		//
		// ground = world.createBody(bd);
		//
		// CircleShape shape = new CircleShape();
		// shape.setRadius(1f);
		//
		// fd.shape = shape;
		//
		// ground.createFixture(fd);

		final BodyDef bd = new BodyDef();
		bd.type = BodyType.STATIC;

		surf = world.createBody(bd);
		final EdgeShape sh = new EdgeShape();
		// // sh.m_radius = 50;
		sh.set(new Vec2(-1, -1), new Vec2(-2, 0));

		surf.createFixture(sh, 0);

		surf.setTransform(new Vec2(-1, -3), 0);

		final Body second = world.createBody(bd);

		sh.set(new Vec2(1, -1), new Vec2(-1, -2));
		second.createFixture(sh, 2f);

	}

	public void spawnShape() {

		final FixtureDef fd = new FixtureDef();
		fd.restitution = 0f;
		fd.friction = 1f;
		fd.density = 2f;

		final BodyDef bd = new BodyDef();
		bd.type = BodyType.DYNAMIC;
		bd.position = new Vec2(0, 5);
		bd.angle = 5f;
		ground = world.createBody(bd);

		final PolygonShape shape = new PolygonShape();
		shape.setAsBox(.03f, .03f);
		shape.setRadius(.03f);

		fd.shape = shape;

		ground.createFixture(fd);
	}

	@Override
	public void updateIt(final GameContainer container, final int delta)
			throws SlickException {

		elapsedTime += delta;

		spawnShape();

		if (1 / elapsedTime <= hz) {
			world.step(1 / hz, 3, 3);
			elapsedTime -= 1 / hz;
		}

		Body b = world.getBodyList();

		Body next = b.m_next;

		while (b.m_next != null) {

			if (b.getPosition().y() < -6)
				world.destroyBody(b);
			b = next;
			next = b.m_next;

		}

	}

	@Override
	public void renderIt(final GameContainer container, final Graphics g)
			throws SlickException {
		g.scale(2.4f, 2.4f);
		g.translate(-160, -330);
		world.drawDebugData();
	}

	/**
	 * @param args
	 */
	public static void main(final String[] args) {

		try {
			final AppGameContainer app = new AppGameContainer(new Jbox2DTest(
					"jbox2D"));
			app.setDisplayMode(800, 600, false);
			app.setTargetFrameRate(60);
			app.start();
		} catch (final SlickException e) {

			e.printStackTrace();
		}

	}

	/*---------------------
	 * 
	 * Variables
	 * 
	 *----------------------*/

	/*---------------------
	 * 
	 * Constructor
	 * 
	 *----------------------*/

	/*---------------------
	 * 
	 * Methods
	 * 
	 *----------------------*/

	/*---------------------
	 * 
	 * Getters / Setters
	 * 
	 *----------------------*/
}

package com.alnaiyr.states;

import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.collision.shapes.EdgeShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import com.alnaiyr.coordinates.dynamic.Cartesian;
import com.alnaiyr.display.GraphicEntity;
import com.alnaiyr.display.impl.advanced.AnimationEntity;
import com.alnaiyr.display.impl.advanced.NullEntity;
import com.alnaiyr.display.impl.basic.DrawEntity;
import com.alnaiyr.display.renderables.render.rewrite.Animation;
import com.alnaiyr.generator.layers.LayerFactory;
import com.alnaiyr.ressources.image.ToroImage;
import com.alnaiyr.ressources.spritesheet.ToroSpriteSheet;
import com.alnaiyr.utilities.debug.jbox2D.SlickDebugDraw;

/**
 * where the game takes place, so I guess the most important
 * 
 * @author IgoR
 * @version 1.0
 *          <p>
 *          <strong> Version change:</strong>
 *          <ul>
 *          <li><em> Functional</em></li>
 *          </ul>
 *          </p>
 */

public class GameplayState extends State {

	/* **********************************************
	 * 
	 * Variables
	 * 
	 * *************************************************
	 */

	private AnimationEntity	toro;
	private GraphicEntity	back;

	public SlickDebugDraw	debug;
	public World			world;

	public Body				ground;
	public Body				surf;

	public float			hz			= 60;
	public float			elapsedTime	= 0;

	/* **********************************************
	 * 
	 * constructor
	 * 
	 * *************************************************
	 */

	/**
	 * @param id
	 */
	public GameplayState(final byte id) {
		super(id);

	}

	/* **********************************************
	 * 
	 * Methods
	 * 
	 * *************************************************
	 */

	/* **********************************************
	 * 
	 * Slick Methods
	 * 
	 * *************************************************
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.alnaiyr.states.State#initIt(org.newdawn.slick.GameContainer,
	 * org.newdawn.slick.state.StateBasedGame)
	 */
	@Override
	public void initLayerable(final GameContainer container,
			final StateBasedGame game) throws SlickException {

		final Animation mation = new Animation(
				ToroSpriteSheet.TORO.spritesheet, 200).getScaledCopy(.4f);
		toro = new AnimationEntity(new Cartesian(0f, .8f, true), mation);
		back = new DrawEntity(ToroImage.BACKGROUND.image);

		LayerFactory.getInstance().addToLayer(30, NullEntity.instance);
		LayerFactory.getInstance().addToLayer(0, toro);
		LayerFactory.getInstance().setDepth(50);
		LayerFactory.getInstance().setReference(0);
		LayerFactory.getInstance().setReferenceCoordinate(toro.coord);

		debug = new SlickDebugDraw(container);
		// debug.getViewportTranform().setCenter(400, 300);
		debug.getViewportTranform().setExtents(new Vec2(1920, 1080));
		debug.getViewportTranform().setCamera(9.6f / 6, -5.4f / 6, 600);

		debug.getViewportTranform().setYFlip(true);

		debug.clearFlags(DebugDraw.e_centerOfMassBit);
		debug.appendFlags(DebugDraw.e_shapeBit);

		world = new World(new Vec2(0, -5f));
		world.setDebugDraw(debug);

		final BodyDef bd = new BodyDef();
		bd.type = BodyType.STATIC;

		surf = world.createBody(bd);
		final EdgeShape sh = new EdgeShape();
		// // sh.m_radius = 50;
		// sh.set(new Vec2(-1, 0), new Vec2(1, 0));

		// surf.createFixture(sh, 0);

		surf.setTransform(new Vec2(-1, -3), 0);

		final Body second = world.createBody(bd);

		// sh.set(new Vec2(1, 1), new Vec2(-1, 1));
		// second.createFixture(sh, 2f);

	}

	public void spawnShape() {

		final FixtureDef fd = new FixtureDef();
		fd.restitution = 0f;
		fd.friction = 1f;
		fd.density = 2f;

		final BodyDef bd = new BodyDef();
		bd.type = BodyType.DYNAMIC;
		bd.position = new Vec2(0, 1);
		bd.angle = 5f;
		ground = world.createBody(bd);

		final PolygonShape shape = new PolygonShape();
		shape.setAsBox(.03f, .03f);
		shape.setRadius(.03f);

		fd.shape = shape;

		ground.createFixture(fd);
	}

	@Override
	public void renderIt(final GameContainer container,
			final StateBasedGame game, final Graphics g) throws SlickException {
		world.drawDebugData();

	}

	@Override
	public void updateIt(final GameContainer container,
			final StateBasedGame game, final int delta) throws SlickException {
		toro.coord.addLocal(new Vector2f(0, .6f * delta));

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
	public void initLayerDependant(final GameContainer container,
			final StateBasedGame game) {

	}
}

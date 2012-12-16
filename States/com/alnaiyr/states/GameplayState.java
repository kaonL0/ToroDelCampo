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

import com.alnaiyr.ai.updater.condition.RandomLuckCondition;
import com.alnaiyr.coordinates.dynamic.Cartesian;
import com.alnaiyr.display.GraphicEntity;
import com.alnaiyr.display.impl.advanced.AnimationEntity;
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

	private AnimationEntity		toro;
	private Animation			perso;
	private GraphicEntity		back;

	public SlickDebugDraw		debug;
	public World				world;

	public Body					ground;
	public Body					surf;

	public float				hz			= 60;
	public float				elapsedTime	= 0;

	private RandomLuckCondition	rnd;

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
		perso = new Animation(ToroSpriteSheet.HUMAIN.spritesheet, 200)
				.getScaledCopy(.4f);

		toro = new AnimationEntity(new Cartesian(.5f, .5f, true), mation);
		back = new DrawEntity(new Vector2f(0f, 0, true), false,
				ToroImage.BACKGROUND0.image.getScaledCopy(4));

		LayerFactory.getInstance().addToLayer(40, back);
		LayerFactory.getInstance().addToLayer(0, toro);
		LayerFactory.getInstance().setDepth(50);
		LayerFactory.getInstance().setReference(0);
		LayerFactory.getInstance().setReferenceCoordinate(toro.coord);

		debug = new SlickDebugDraw(container);
		debug.getViewportTranform().setExtents(new Vec2(1920, 1080));

		// debug.getViewportTranform().setCamera(toro.coord.x(), toro.coord.y,
		// 50);

		debug.getViewportTranform().setYFlip(true);

		debug.clearFlags(DebugDraw.e_centerOfMassBit);
		debug.appendFlags(DebugDraw.e_shapeBit);

		world = new World(new Vec2(0, -5f));
		world.setDebugDraw(debug);

		final BodyDef bd = new BodyDef();
		bd.type = BodyType.STATIC;

		surf = world.createBody(bd);
		final EdgeShape sh = new EdgeShape();
		// sh.m_radius = 50;
		// sh.set(new Vec2(0, -22f), new Vec2(39, 0f));
		sh.set((Vec2) debug.getScreenToWorld(0, 0),
				(Vec2) debug.getScreenToWorld(1920, 1080));
		surf.createFixture(sh, 0);

		final Body second = world.createBody(bd);

		rnd = new RandomLuckCondition(1, 10);

	}

	public void spawnShape() {

		final FixtureDef fd = new FixtureDef();
		fd.restitution = 0f;
		fd.friction = 1f;
		fd.density = 2f;

		final BodyDef bd = new BodyDef();
		bd.type = BodyType.DYNAMIC;
		bd.position = (Vec2) debug.getScreenToWorld(toro.lay.focus.coord.x(),
				toro.lay.focus.coord.y());

		bd.angle = 5f;
		ground = world.createBody(bd);

		final PolygonShape shape = new PolygonShape();
		shape.setAsBox(.8f, .8f);
		// shape.setRadius(.06f);

		fd.shape = shape;

		ground.createFixture(fd);
		lContainer.getLayer(0).toRender.add(new AnimationEntity(bd.position,
				new Animation(ToroSpriteSheet.HUMAIN.spritesheet, 200)
						.getScaledCopy(.4f)));
	}

	@Override
	public void renderIt(final GameContainer container,
			final StateBasedGame game, final Graphics g) throws SlickException {
		g.pushTransform();
		lContainer.reference.focus.render(g, container);
		world.drawDebugData();
		g.popTransform();
	}

	@Override
	public void updateIt(final GameContainer container,
			final StateBasedGame game, final int delta) throws SlickException {
		toro.coord.addLocal(new Vector2f(0, -.8f * delta));

		elapsedTime += delta;

		if (rnd.getCondition())
			spawnShape();

		if (1 / elapsedTime <= hz) {
			world.step(1 / hz, 3, 3);
			elapsedTime -= 1 / hz;
		}

		Body b = world.getBodyList();

		Body next = b.m_next;

		while (b.m_next != null) {

			if (b.getPosition().y() < -60)
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

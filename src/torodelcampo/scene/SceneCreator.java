package torodelcampo.scene;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import torodelcampo.jboxentity.Personnage;
import torodelcampo.jboxentity.Taureau;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.GraphicEntity;
import com.alnaiyr.display.camera.Focus;
import com.alnaiyr.general.IV;
import com.alnaiyr.math.MathU;
import com.alnaiyr.states.GameplayState2;
import com.alnaiyr.states.State;
import com.alnaiyr.utilities.debug.jbox2D.SlickDebugDraw;

public class SceneCreator extends GraphicEntity {

	public float					elapsedTime		= 0;
	public float					hz				= 60;

	static final int				ID_CONTROLEUR	= 0;
	static final boolean			debugging		= true;
	public static World				world;
	public static SlickDebugDraw	debug;

	public static State				state;

	List<Scene>						currentScenes;
	public Taureau					taureau;

	private Focus					focus;

	private PlanVector				lastGenerated;

	public SceneCreator(final PlanVector coord, final float width,
			final float height, final State state) {
		super(coord, width, height);
		this.state = state;
	}

	public SceneCreator(final GameplayState2 gps, final GameContainer container) {
		super(new Vec2(1920, 1080), 1920, 1080);

		world = new World(new Vec2(0, -5f));
		world.setDebugDraw(debug);
		debugConfig(container);
		state = gps;

		// taureau = new Taureau();
		currentScenes = new ArrayList<Scene>();
		currentScenes.add(EnumScene.SCENE1.scene);
		currentScenes.add(EnumScene.SCENE1.scene.clone());
		currentScenes.add(EnumScene.SCENE1.scene.clone());

		currentScenes.get(2).init(
				new Vector2f(-.026f, 1f, true),
				createPersonnage(new Vector2f(.5f, 1f, true), new Vector2f(.6f,
						1f, true), 20));
		currentScenes.get(1).init(
				new Vector2f(-.026f, -1f, true),
				createPersonnage(new Vector2f(.5f, -1f, true), new Vector2f(
						.6f, -1f, true), 20));
		currentScenes.get(0).init(
				new Vector2f(-.026f, 0, true),
				createPersonnage(new Vector2f(.5f, 0f, true), new Vector2f(.6f,
						0f, true), 20));

		lastGenerated = new Vector2f(-.026f, -1f, true);

		taureau = new Taureau(new Vector2f(.5f, .8f, true));

		focus = new Focus(taureau.coord, 2);
	}

	public Personnage[] createPersonnage(final PlanVector from,
			final PlanVector to, final int number) {
		final Personnage[] temp = new Personnage[number];

		for (int i = 0; i < temp.length; i++) {
			temp[i] = new Personnage(new Vec2(MathU.random(from.x(), to.x()),
					MathU.random(from.y(), to.y())), MathU.random(.1f, .2f));
		}
		return temp;
	}

	/**
	 * @param container
	 */
	private void debugConfig(final GameContainer container) {
		debug = new SlickDebugDraw(container);
		debug.getViewportTranform().setExtents(new Vec2(1920, 1080));
		debug.getViewportTranform().setCamera(0, 0, 50);
		debug.getViewportTranform().setYFlip(true);
		debug.clearFlags(DebugDraw.e_centerOfMassBit);
		debug.appendFlags(DebugDraw.e_shapeBit);
	}

	void createScene() {
		if (focus.coord.y() - IV.vWidth < lastGenerated.y()) {
			currentScenes.add(EnumScene.values()[MathU.random(0,
					EnumScene.values().length - 1)].scene.clone());
			currentScenes.get(currentScenes.size() - 1).init(
					new Vector2f(-.026f * IV.vWidth, lastGenerated.y() - 1080,
							false),
					createPersonnage(new Vector2f(.5f * IV.vWidth,
							lastGenerated.y() - 1080, false), new Vector2f(
							.6f * IV.vWidth, lastGenerated.y() - 1080, false),
							20));
			lastGenerated.addLocal(new Vec2(0, -1080));
			// currentScenes.remove(0);
		}
	}

	void deleteScene() {

	}

	@Override
	public int width() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void gUpdate(final int delta, final boolean condition) {

		elapsedTime += delta;

		// if (rnd.getCondition())
		// spawnShape();

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

		for (final Scene scene : currentScenes)
			scene.gUpdate(delta, true);
		taureau.gUpdate(delta, true);

		createScene();
	}

	@Override
	public void render(final Graphics g, final GameContainer container) {
		g.pushTransform();
		// state.getReference().focus.render(g, container);
		focus.render(g, container);
		for (final Scene scene : currentScenes) {
			scene.render(g, container);
		}

		taureau.render(g, container);
		world.drawDebugData();
		g.popTransform();
	}
}

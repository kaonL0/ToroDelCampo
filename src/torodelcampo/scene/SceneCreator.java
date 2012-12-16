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

import torodelcampo.jboxentity.Taureau;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.GraphicEntity;
import com.alnaiyr.display.camera.Focus;
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
		currentScenes.add(0, EnumScene.SCENE1.scene);
		currentScenes.get(0).init(new Vector2f(.3f, 0, true));

		taureau = new Taureau(new Vector2f(.5f, .8f, true));

		focus = new Focus(taureau.coord);
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

	}

	void createNextScenes() {

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

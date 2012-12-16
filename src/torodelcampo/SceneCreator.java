package torodelcampo;

import java.util.ArrayList;
import java.util.List;

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
import org.newdawn.slick.Input;

import com.alnaiyr.ai.updater.condition.RandomLuckCondition;
import com.alnaiyr.commands.Commands;
import com.alnaiyr.commands.Keys;
import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.GraphicEntity;
import com.alnaiyr.general.IV;
import com.alnaiyr.states.GameplayState2;
import com.alnaiyr.utilities.debug.jbox2D.SlickDebugDraw;

public class SceneCreator extends GraphicEntity {
	public float elapsedTime	= 0;
	public float hz = 60;
	
	static final int ID_CONTROLEUR = 0;
	
	World world;
	List<Scene> scenes;
	Scene currentScene;
	Taureau taureau;
	public Body ground;
	public Body surf;
	public SlickDebugDraw debug;
	private RandomLuckCondition rnd;
	
	
	public SceneCreator(PlanVector coord, float width, float height) {
		super(coord, width, height);
		// TODO Auto-generated constructor stub
	}

	public SceneCreator(GameplayState2 gps, final GameContainer container) {
		super(new Vec2(1920, 1080), 1920, 1080);
		
		taureau = new Taureau();
		scenes = new ArrayList<Scene>();
		scenes.add(0, new Scene());

		rnd = new RandomLuckCondition(1, 10);
		
		debug = new SlickDebugDraw(container);
		debug.getViewportTranform().setExtents(new Vec2(1920, 1080));
		debug.getViewportTranform().setCamera(9.6f * 2, -5.4f * 2, 50);

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
		sh.set(new Vec2(-10, -1), new Vec2(10, -1));

		surf.createFixture(sh, 0);

		surf.setTransform(new Vec2(-1, -3), 0);

		final Body second = world.createBody(bd);

		sh.set(new Vec2(1, -1), new Vec2(-1, -1));
		second.createFixture(sh, 2f);
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
		shape.setAsBox(.8f, .8f);
		// shape.setRadius(.06f);

		fd.shape = shape;

		ground.createFixture(fd);
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
	public void gUpdate(int delta, boolean condition) {
		world.drawDebugData();
		
		//toro.coord.addLocal(new Vector2f(0, .6f * delta));

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

			if (b.getPosition().y() < -6)
				world.destroyBody(b);
			b = next;
			next = b.m_next;

		}
		
		//
		GameContainer container = IV.container;
		Input input = container.getInput();
		
		if (input.isKeyPressed(Commands.input.KEY_LEFT)) {
			taureau.moveLeft();
		}
		if (input.isKeyPressed(Commands.input.KEY_RIGHT)) {
			taureau.moveRight();
		}
		if (input.isKeyPressed(Commands.input.KEY_UP)) {
			taureau.moveUp();
		}
		//System.out.println("gUpdate");
	}

	@Override
	public void render(Graphics g, GameContainer container) {
		world.drawDebugData();
	}

}

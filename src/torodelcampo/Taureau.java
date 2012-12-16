package torodelcampo;

import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

import com.alnaiyr.commands.Commands;
import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.renderables.render.rewrite.Animation;
import com.alnaiyr.general.IV;
import com.alnaiyr.ressources.spritesheet.ToroSpriteSheet;

public class Taureau extends JboxEntity {

	// Direction direction;
	private final Animation	toro;

	public Taureau(final PlanVector coord, final DebugDraw debug) {
		super(coord, debug,
				new Animation(ToroSpriteSheet.TORO.spritesheet, 200));
		toro = (Animation) super.drawable;

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
	}

	public void controller(final GameContainer gc) {
		// slike

	}

	public void moveUp() {

	}

	public void moveLeft() {

	}

	@Override
	public void gUpdate(final int delta, final boolean condition) {
		coord.addLocal(new Vector2f(0, .6f * delta));

		toro.update(delta);

		// int ID_CONTROLEUR = 0;
		// Input input = gc.getInput();
		// if (input.isControllerLeft(ID_CONTROLEUR)
		// && input.isButton1Pressed(ID_CONTROLEUR)) {
		// // Feu !
		// }

		// swjgl
		if (Keyboard.getEventKey() == Keyboard.KEY_LEFT) {
			System.out.println("Left Released");
		}

		//
		final GameContainer container = IV.container;
		final Input input = container.getInput();

		if (input.isKeyPressed(Commands.input.KEY_LEFT)) {
			moveLeft();
		}
		if (input.isKeyPressed(Commands.input.KEY_RIGHT)) {
			moveRight();
		}
		if (input.isKeyPressed(Commands.input.KEY_UP)) {
			moveUp();
		}
		// System.out.println("gUpdate");

	}

	public void moveRight() {

	}

}

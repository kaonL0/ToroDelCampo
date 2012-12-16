package torodelcampo;

import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.dynamics.Body;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Vector2f;

import com.alnaiyr.commands.Commands;
import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.impl.advanced.AnimationEntity;
import com.alnaiyr.display.renderables.DimensionDrawable;
import com.alnaiyr.general.IV;
import com.alnaiyr.generator.layers.LayerFactory;

public class Taureau extends JboxEntity {

	// Direction direction;
	private final AnimationEntity	toro;

	public Taureau(final PlanVector coord, final DebugDraw debug,
			final Body body, final DimensionDrawable drawable) {
		super(coord, debug, body, drawable);
		toro = null;
	}

	public void addToLayer() {
		LayerFactory.getInstance().addToLayer(0, toro);
	}

	public void update(final int delta) {

	}

	public void controller(final GameContainer gc) {
		// slike

	}

	public void playSound() {
		Sound fx;
		try {
			fx = new Sound("res/explosion.wav");
			fx.play(); // Lance le son
			fx.play(1.0f, 0.5f); // D�finis le pitch(1.0) et le volume (0.5)
		} catch (final SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Music musique;
		try {
			musique = new Music("res/maMusique.ogg");
			musique.loop(); // Joue la musique en boucle
			musique.setPosition(musique.getPosition() + 5); // Avance de 5s
		} catch (final SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void moveUp() {

	}

	public void moveLeft() {

	}

	@Override
	public void gUpdate(final int delta, final boolean condition) {
		toro.coord.addLocal(new Vector2f(0, .6f * delta));

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

package torodelcampo;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Vector2f;

import com.alnaiyr.coordinates.dynamic.Cartesian;
import com.alnaiyr.display.impl.advanced.AnimationEntity;
import com.alnaiyr.display.renderables.render.rewrite.Animation;
import com.alnaiyr.generator.layers.LayerFactory;
import com.alnaiyr.ressources.spritesheet.ToroSpriteSheet;

public class Taureau extends ObjetScene {
	// Direction direction;
	private AnimationEntity toro;

	public Taureau() {
		final Animation mation = new Animation(
				ToroSpriteSheet.TORO.spritesheet, 200).getScaledCopy(.4f);
		toro = new AnimationEntity(new Cartesian(0f, .8f, true), mation);

		LayerFactory.getInstance().addToLayer(0, toro);
		LayerFactory.getInstance().setDepth(50);
		LayerFactory.getInstance().setReference(0);
		LayerFactory.getInstance().setReferenceCoordinate(toro.coord);
	}

	public void addToLayer() {
		LayerFactory.getInstance().addToLayer(0, toro);
	}

	public void update(int delta) {
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

	}

	public void controller(GameContainer gc) {
		// slike

	}

	public void playSound() {
		Sound fx;
		try {
			fx = new Sound("res/explosion.wav");
			fx.play(); // Lance le son
			fx.play(1.0f, 0.5f); // Définis le pitch(1.0) et le volume (0.5)
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Music musique;
		try {
			musique = new Music("res/maMusique.ogg");
			musique.loop(); // Joue la musique en boucle
			musique.setPosition(musique.getPosition() + 5); // Avance de 5s
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void moveUp() {

	}

	public void moveLeft() {

	}

	public void moveRight() {

	}

}

package org.newdawn.slick.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.particles.effects.FireEmitter;

/**
 * A particle test using built in effects
 * 
 * @author kevin
 */
public class ParticleTest extends BasicGame {
	/** The particle system running everything */
	private ParticleSystem	system;
	/** The particle blending mode */
	private int				mode	= ParticleSystem.BLEND_COMBINE;

	private final int		x		= 0;

	/**
	 * Create a new test of graphics context rendering
	 */
	public ParticleTest() {
		super("Particle Test");
	}

	/**
	 * @see org.newdawn.slick.BasicGame#init(org.newdawn.slick.GameContainer)
	 */
	@Override
	public void init(final GameContainer container) throws SlickException {
		final Image image = new Image("testdata/particle.tga", true);
		system = new ParticleSystem(image);

		system.addEmitter(new FireEmitter(400, 300, 45));
		system.addEmitter(new FireEmitter(200, 300, 60));
		system.addEmitter(new FireEmitter(600, 300, 30));

		// system.setUsePoints(true);
	}

	/**
	 * @see org.newdawn.slick.BasicGame#render(org.newdawn.slick.GameContainer,
	 *      org.newdawn.slick.Graphics)
	 */
	@Override
	public void render(final GameContainer container, final Graphics g) {
		for (int i = 0; i < 100; i++) {
			g.translate(1, 1);
			system.render();
		}
		g.resetTransform();
		g.drawString("Press space to toggle blending mode", 200, 500);
		g.drawString("Particle Count: " + system.getParticleCount() * 100, 200,
				520);
	}

	/**
	 * @see org.newdawn.slick.BasicGame#update(org.newdawn.slick.GameContainer,
	 *      int)
	 */
	@Override
	public void update(final GameContainer container, final int delta) {
		system.update(delta);
		system.setPosition(system.getPositionX() + x * delta * 10,
				system.getPositionY());

	}

	/**
	 * @see org.newdawn.slick.BasicGame#keyPressed(int, char)
	 */
	@Override
	public void keyPressed(final int key, final char c) {
		if (key == Input.KEY_ESCAPE) {
			System.exit(0);
		}
		if (key == Input.KEY_SPACE) {
			mode = ParticleSystem.BLEND_ADDITIVE == mode ? ParticleSystem.BLEND_COMBINE
					: ParticleSystem.BLEND_ADDITIVE;
			system.setBlendingMode(mode);
		}
	}

	/**
	 * Entry point to our test
	 * 
	 * @param argv
	 *            The arguments passed to the test
	 */
	public static void main(final String[] argv) {
		try {
			final AppGameContainer container = new AppGameContainer(
					new ParticleTest());
			container.setDisplayMode(800, 600, false);
			container.start();
		} catch (final SlickException e) {
			e.printStackTrace();
		}
	}
}

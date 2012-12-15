package com.alnaiyr.states;

import java.util.ArrayList;
import java.util.Map.Entry;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.alnaiyr.display.GraphicEntity;
import com.alnaiyr.display.camera.Focus;
import com.alnaiyr.general.IV;
import com.alnaiyr.generator.layers.LayerFactory;
import com.alnaiyr.level.layer.Layer;
import com.alnaiyr.level.layer.ParallaxLayerContainer;

/**
 * A basic State containing all necessary informations
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public abstract class State extends BasicGameState {

	protected ParallaxLayerContainer	lContainer;
	private Layer[]						outOfParallax;

	protected byte						id;

	/**
	 * @param id
	 */
	public State(final byte id) {
		this.id = id;
	}

	@Override
	public void init(final GameContainer container, final StateBasedGame game)
			throws SlickException {
		initLayerable(container, game);
		createLayers();
		initLayerDependant(container, game);
		lContainer.update(0, true);

	}

	@Override
	public void update(final GameContainer container,
			final StateBasedGame game, final int delta) throws SlickException {
		IV.update(delta);
		updateIt(container, game, delta);
		lContainer.update(delta, true);
		IV.activator.setValue(false);

		for (final Layer lay : outOfParallax) {
			lay.update(delta, true);
		}

	}

	@Override
	public void render(final GameContainer container,
			final StateBasedGame game, final Graphics g) throws SlickException {
		IV.render(container, g);
		g.pushTransform();
		lContainer.render(g, container);
		g.popTransform();

		for (final Layer lay : outOfParallax) {
			lay.render(g, container);
		}

		renderIt(container, game, g);
	}

	@Override
	public int getID() {
		return id;
	}

	/**
	 * Creates all layers displayed during this state
	 * 
	 * @param layerables
	 */
	public void createLayers() {

		Layer reference = null;
		final Layer[] layers = new Layer[LayerFactory.getInstance()
				.getMaxSize()];
		outOfParallax = new Layer[LayerFactory.getInstance()
				.getSizeOutOfParal()];

		int i = 0;
		for (final Entry<Integer, ArrayList<GraphicEntity>> entry : LayerFactory
				.getInstance().getGraphics().entrySet()) {

			layers[i] = new Layer(new Focus(new Vector2f(IV.getWidth() / 2,
					IV.getHeight() / 2, false)), LayerFactory.getInstance()
					.getDepth() - entry.getKey(), entry.getValue());
			if (entry.getKey() == LayerFactory.getInstance().getReference()) {
				reference = layers[i];
				reference.focus.coord = LayerFactory.getInstance()
						.getReferenceCoordinate();
			}
			i++;
		}

		i = 0;
		for (final Entry<Integer, ArrayList<GraphicEntity>> entry : LayerFactory
				.getInstance().getOutOfLayer().entrySet()) {
			outOfParallax[i] = new Layer(new Focus(new Vector2f(
					IV.getWidth() / 2, IV.getHeight() / 2, false)),
					entry.getKey(), entry.getValue());
			i++;
		}

		lContainer = new ParallaxLayerContainer(reference, LayerFactory
				.getInstance().getDepth(), layers);
		LayerFactory.getInstance().destroyInstance();

	}

	public Layer getReference() {
		return lContainer.getReference();
	}

	public Layer getLayer(final int i) {
		return lContainer.getLayer(i);
	}

	/**
	 * Override on initialize
	 * 
	 * @param container
	 * @param game
	 * @throws SlickException
	 */
	public abstract void initLayerable(GameContainer container,
			StateBasedGame game) throws SlickException;

	public abstract void initLayerDependant(GameContainer container,
			StateBasedGame game);

	/**
	 * Override on render
	 * 
	 * @param container
	 * @param game
	 * @param g
	 * @throws SlickException
	 */
	public abstract void renderIt(GameContainer container, StateBasedGame game,
			Graphics g) throws SlickException;

	/**
	 * Override on update
	 * 
	 * @param container
	 * @param game
	 * @param delta
	 * @throws SlickException
	 */
	public abstract void updateIt(GameContainer container, StateBasedGame game,
			int delta) throws SlickException;
}

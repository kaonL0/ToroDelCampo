package com.alnaiyr.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import torodelcampo.SceneCreator;
import torodelcampo.Taureau;

import com.alnaiyr.generator.layers.LayerFactory;

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

public class GameplayState2 extends State {

	/* **********************************************
	 * 
	 * Variables
	 * 
	 * *************************************************
	 */

	// private AnimationEntity toro;
	// public World world;
	// public float elapsedTime = 0;
	// private RandomLuckCondition rnd;

	public SceneCreator	sceneCreator	= null;
	private Taureau		tor;

	/* **********************************************
	 * 
	 * constructor
	 * 
	 * *************************************************
	 */

	/**
	 * @param id
	 */
	public GameplayState2(final byte id) {
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
		LayerFactory.getInstance().setDepth(50);
		LayerFactory.getInstance().setReference(0);
		sceneCreator = new SceneCreator(this, container);

		tor = new Taureau(new Vector2f(.5f, 5.8f, true));

		LayerFactory.getInstance().addToLayer(0, tor);
		LayerFactory.getInstance().setReference(0);
		LayerFactory.getInstance().setReferenceCoordinate(tor.coord);

	}

	@Override
	public void renderIt(final GameContainer container,
			final StateBasedGame game, final Graphics g) throws SlickException {
		sceneCreator.render(g, container);
	}

	@Override
	public void updateIt(final GameContainer container,
			final StateBasedGame game, final int delta) throws SlickException {
		sceneCreator.gUpdate(delta, true);
	}

	@Override
	public void initLayerDependant(final GameContainer container,
			final StateBasedGame game) {

	}
}

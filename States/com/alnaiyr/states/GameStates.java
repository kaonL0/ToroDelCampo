package com.alnaiyr.states;

import org.newdawn.slick.state.GameState;

/**
 * All games states
 * 
 * @author IgoЯ
 * @version 0.1
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>Change</em></li>
 *          </ul>
 */
public enum GameStates {

	WELCOMESTATE(new WelcomeState((byte) 0)),

	GAMEPLAYSTATE(new GameplayState((byte) 2));

	private GameState	state;

	private GameStates(final GameState state) {
		this.state = state;
	}

	public int getID() {
		return state.getID();
	}

	public GameState getState() {
		return state;
	}

}

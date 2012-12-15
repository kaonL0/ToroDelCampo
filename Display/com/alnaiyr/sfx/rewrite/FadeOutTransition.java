package com.alnaiyr.sfx.rewrite;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.Transition;

import com.alnaiyr.general.IV;

/**
 * A transition to fade out to a given color
 * 
 * @author kevin
 * 
 *         Rewrite of the fade out transition, so that it can handle resizing
 *         windows.
 */
public class FadeOutTransition implements Transition {
    /** The color to fade to */
    private Color color;
    /** The time it takes the fade to happen */
    private int fadeTime;

    /**
     * Create a new fade out transition
     */
    public FadeOutTransition() {
	this(Color.black, 500);
    }

    /**
     * Create a new fade out transition
     * 
     * @param color
     *            The color we're going to fade out to
     */
    public FadeOutTransition(Color color) {
	this(color, 500);
    }

    /**
     * Create a new fade out transition
     * 
     * @param color
     *            The color we're going to fade out to
     * @param fadeTime
     *            The time it takes the fade to occur
     */
    public FadeOutTransition(Color color, int fadeTime) {
	this.color = new Color(color);
	this.color.a = 0;
	this.fadeTime = fadeTime;
    }

    /**
     * @see org.newdawn.slick.state.transition.Transition#isComplete()
     */
    @Override
    public boolean isComplete() {
	return color.a >= 1;
    }

    /**
     * @see org.newdawn.slick.state.transition.Transition#postRender(org.newdawn.slick.state.StateBasedGame,
     *      org.newdawn.slick.GameContainer, org.newdawn.slick.Graphics)
     */
    @Override
    public void postRender(StateBasedGame game, GameContainer container,
	    Graphics g) {
	Color old = g.getColor();
	g.setColor(color);
	g.fillRect(0, 0, IV.getWidth(), IV.getHeight());
	g.setColor(old);
    }

    /**
     * @see org.newdawn.slick.state.transition.Transition#update(org.newdawn.slick.state.StateBasedGame,
     *      org.newdawn.slick.GameContainer, int)
     */
    @Override
    public void update(StateBasedGame game, GameContainer container, int delta) {
	color.a += delta * (1.0f / fadeTime);
	if (color.a > 1) {
	    color.a = 1;
	}
    }

    /**
     * @see org.newdawn.slick.state.transition.Transition#preRender(org.newdawn.slick.state.StateBasedGame,
     *      org.newdawn.slick.GameContainer, org.newdawn.slick.Graphics)
     */
    @Override
    public void preRender(StateBasedGame game, GameContainer container,
	    Graphics g) {
    }

    @Override
    public void init(GameState firstState, GameState secondState) {
    }

}

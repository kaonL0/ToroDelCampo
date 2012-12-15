package com.alnaiyr.utilities.example;

import javax.naming.directory.InvalidAttributesException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.alnaiyr.commands.CommandType;
import com.alnaiyr.commands.Commands;
import com.alnaiyr.general.IV;

/**
 * This class shows a structure for examples, using a debugger
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>Works</em></li>
 *          </ul>
 */
public abstract class Example extends BasicGame {

	/* ***********************
	 * 
	 * Variables
	 * 
	 * **************************
	 */

	/* ***********************
	 * 
	 * Constructor
	 * 
	 * **************************
	 */

	/**
	 * @param title
	 */
	public Example(String title) {
		super(title);
	}

	/* ***********************
	 * 
	 * Methods
	 * 
	 * **************************
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Game#render(org.newdawn.slick.GameContainer,
	 * org.newdawn.slick.Graphics)
	 */
	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		// cam.render(g, container);
		renderIt(container, g);
		renderAfter(container, g);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.BasicGame#update(org.newdawn.slick.GameContainer,
	 * int)
	 */
	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		IV.update(delta);
		if (container.getInput().isKeyDown(Input.KEY_ESCAPE))
			container.exit();
		if (container.getInput().isKeyDown(Input.KEY_F1)) {
			container.reinit();
		}
		updateIt(container, delta);
		IV.activator.setValue(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.BasicGame#init(org.newdawn.slick.GameContainer)
	 */
	@Override
	public void init(GameContainer container) throws SlickException {
		IV.pixelProportion = 1;
		IV.g = container.getGraphics();
		IV.container = (AppGameContainer) container;
		IV.vWidth = 800;
		IV.vHeight = 600;
		try {
			Commands.configure(container);
		} catch (InvalidAttributesException e) {
		}
		CommandType.DEBUG.setActivated(true);
		initialize(container);
	}

	/**
	 * Override this method when you need to render things after the debug
	 * 
	 * @param container
	 * @param g
	 */
	public void renderAfter(GameContainer container, Graphics g) {
	}

	/**
	 * Works as init from slick
	 * 
	 * @param container
	 * @throws SlickException
	 */
	public abstract void initialize(GameContainer container)
			throws SlickException;

	/**
	 * Works as update from slick
	 * 
	 * @param container
	 * @param delta
	 * @throws SlickException
	 */
	public abstract void updateIt(GameContainer container, int delta)
			throws SlickException;

	/**
	 * Works as render from slick
	 * 
	 * @param container
	 * @param g
	 * @throws SlickException
	 */
	public abstract void renderIt(GameContainer container, Graphics g)
			throws SlickException;

	/* *****************************
	 * 
	 * Getters ans Setters
	 * 
	 * **********************************
	 */

	public void start() {
		try {
			AppGameContainer app = new AppGameContainer(this);
			app.setDisplayMode(800, 600, false);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}

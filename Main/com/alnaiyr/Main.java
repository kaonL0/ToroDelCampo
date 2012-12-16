package com.alnaiyr;

import java.io.IOException;

import javax.naming.directory.InvalidAttributesException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

import com.alnaiyr.commands.CommandType;
import com.alnaiyr.commands.Commands;
import com.alnaiyr.general.EV;
import com.alnaiyr.general.IV;
import com.alnaiyr.loading.OptionLoader;
import com.alnaiyr.loading.RessourcesLoader;
import com.alnaiyr.ressources.angelcodefont.LailaAngelCodeFont;
import com.alnaiyr.states.GameStates;
import com.alnaiyr.uinterface.boxes.AbstractBox;

/**
 * main rendering thing
 * 
 * This is where all begin...
 * 
 * @author IgoЯ
 * 
 * 
 *         ,_/ . . | ,--. . . | ' | ,-. |- " ,-. . ,-,-. ,-. | `-' ,-. ,-,-. . |
 *         | ,-. | | |-' | ,-| | | | | |-' | . ,-| | | | | | | |-' | | `-' `'
 *         `-^ ' ' ' ' `-' `--' `-^ ' ' ' ' `' `' `-' ' /` | O `--'
 ***/

public class Main extends StateBasedGame {

	/* **********************************************
	 * 
	 * THE Constructor
	 * 
	 * ************************************************
	 */

	/**
	 * 
	 */
	public Main() {
		super("Toro Del Campo");
	}

	/* **********************************************
	 * 
	 * THE Method
	 * 
	 * ************************************************
	 */

	@Override
	public void initStatesList(final GameContainer container)
			throws SlickException {

		IV.game = this;
		IV.g = container.getGraphics();
		try {
			OptionLoader.loadRessources("data/OptionProfile.conf");
			if (EV.bindAllValues()) {
				container.setTargetFrameRate(60);
			}
		} catch (final IOException e) {
			Log.error("Failed to load option state, loaded default values instead");
			if (EV.bindDefaultValues()) {
				container.setTargetFrameRate(60);
			}
		}
		try {
			Commands.configure(container);
		} catch (final InvalidAttributesException e1) {
			e1.printStackTrace();
		}

		try {
			RessourcesLoader.loadResources("data/main.load");
		} catch (final IOException e) {
			e.printStackTrace();
		}

		Commands.activate(CommandType.DEBUG);
		Commands.activate(CommandType.MENU);
		addState(GameStates.GAMEPLAYSTATE2.getState());

		AbstractBox.font = LailaAngelCodeFont.ARABOLICA.angelcodefont;

	}

	/* **********************************************
	 * 
	 * And there we go!
	 * 
	 * ************************************************
	 */

	/**
	 * @param args
	 * @throws SlickException
	 */
	public static void main(final String[] args) throws SlickException {
		final Main main = new Main();
		final AppGameContainer app = new AppGameContainer(main);
		IV.container = app;
		app.start();
		app.setUpdateOnlyWhenVisible(false);
		app.setAlwaysRender(true);
	}
}

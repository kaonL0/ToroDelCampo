/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.utilities.debug;

import org.newdawn.slick.Input;

import com.alnaiyr.commands.CommandType;
import com.alnaiyr.commands.Commandable;
import com.alnaiyr.commands.Commands;

/**
 * A class made just to make the FPS drop (to debug things)
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class FPSDropper {

	private static int coef;
	@SuppressWarnings("unused")
	private static int dumb;

	public static Commandable dropCommand = new Commandable() {

		@Override
		public CommandType getType() {
			return CommandType.DEBUG;
		}

		@Override
		public int getCommand() {
			return Input.KEY_NUMPAD8;
		}

	};

	public static Commandable raiseCommand = new Commandable() {

		@Override
		public CommandType getType() {
			return CommandType.DEBUG;
		}

		@Override
		public int getCommand() {
			return Input.KEY_NUMPAD2;
		}
	};

	/*---------------------
	 * 
	 * Methods
	 * 
	 *----------------------*/

	public static void dropFPS() {
		if (Commands.isDown(dropCommand)) {
			coef += 10000;
		} else if (Commands.isDown(raiseCommand)) {
			coef -= 10000;
			if (coef < 0)
				coef = 0;
		}

		for (int i = 0; i < coef; i++) {
			dumb++;
			// void but slows down!
		}
		dumb = 0;

	}
	/*---------------------
	 * 
	 * Getters / Setters
	 * 
	 *----------------------*/
}

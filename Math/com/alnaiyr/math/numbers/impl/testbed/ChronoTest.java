/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.math.numbers.impl.testbed;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.alnaiyr.math.numbers.impl.Chrono;
import com.alnaiyr.utilities.debug.DB;
import com.alnaiyr.utilities.example.Example;

/**
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class ChronoTest extends Example {

	private Chrono chrono = new Chrono(10);

	public ChronoTest(String title) {
		super(title);
	}

	@Override
	public void initialize(GameContainer container) throws SlickException {
	}

	@Override
	public void updateIt(GameContainer container, int delta)
			throws SlickException {
		chrono.update(delta, true);
		DB.test(chrono.getSecond());
	}

	@Override
	public void renderIt(GameContainer container, Graphics g)
			throws SlickException {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new ChronoTest(
					"chrono test"));
			app.setDisplayMode(800, 600, false);
			app.start();
		} catch (SlickException e) {

			e.printStackTrace();
		}
	}

	/*---------------------
	 * 
	 * Variables
	 * 
	 *----------------------*/

	/*---------------------
	 * 
	 * Constructor
	 * 
	 *----------------------*/

	/*---------------------
	 * 
	 * Methods
	 * 
	 *----------------------*/

	/*---------------------
	 * 
	 * Getters / Setters
	 * 
	 *----------------------*/
}

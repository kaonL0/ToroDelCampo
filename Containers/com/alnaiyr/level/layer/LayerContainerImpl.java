/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.level.layer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class LayerContainerImpl implements LayerContainer {

	/*---------------------
	 * 
	 * Variables
	 * 
	 *----------------------*/

	private Layer last;

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

	@Override
	public void render(Graphics g, GameContainer container) {
	}

	@Override
	public void update(int delta, boolean condition) {
	}

	@Override
	public Layer getFirst() {

		return null;
	}

	@Override
	public Layer getActive() {

		return null;
	}

	/*---------------------
	 * 
	 * Getters / Setters
	 * 
	 *----------------------*/
}

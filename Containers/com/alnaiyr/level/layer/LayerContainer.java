/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.level.layer;

import com.alnaiyr.ai.updater.Updatable;
import com.alnaiyr.display.renderables.Renderable;

/**
 * An Object Containing layers, and charged of rendering it. May contain an
 * update too.
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public interface LayerContainer extends Renderable, Updatable {

	/**
	 * @return first layer of the list
	 */
	public Layer getFirst();

	/**
	 * 
	 * @return main layer (where the action takes place, or from which parallax
	 *         is made,...)
	 */
	public Layer getActive();

}

/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.uinterface.boxes.impl.images;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.renderables.DimensionDrawable;
import com.alnaiyr.uinterface.boxes.listeners.BoxListener;

/**
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class DualImageBox extends ImageBox {

	public DimensionDrawable alternative;
	public BoxListener listener;

	/*---------------------
	 * 
	 * Variables
	 * 
	 *----------------------*/

	public DualImageBox(PlanVector coord, DimensionDrawable image,
			DimensionDrawable alternative, boolean centered) {
		super(coord, image, centered);

		this.alternative = alternative;
	}

	/*---------------------
	 * 
	 * Methods
	 * 
	 *----------------------*/

	@Override
	public void render(Graphics g, GameContainer container) {

		if (listener.getCondition())
			alternative.draw(coord);
		else
			super.render(g, container);
	}

	/*---------------------
	 * 
	 * Getters / Setters
	 * 
	 *----------------------*/
}

/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.display.impl.advanced;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.alnaiyr.display.GraphicEntity;
import com.alnaiyr.display.renderables.DimensionDrawable;
import com.alnaiyr.math.geom.shape.segments.Segment;
import com.alnaiyr.utilities.list.CircularLinkedList;

/**
 * Draw all of its render to fill in a direction
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class CyclicEntity extends GraphicEntity {

	/*---------------------
	 * 
	 * Variables
	 * 
	 *----------------------*/

	private boolean xDir;

	private CircularLinkedList<DimensionDrawable> draws = new CircularLinkedList<>();

	/** The segment on which to display the drawings */
	private Segment segment;

	/*---------------------
	 * 
	 * Constructor
	 * 
	 *----------------------*/

	public CyclicEntity(Segment seg, DimensionDrawable... draws) {
		super(seg.getFrom(), false, draws[0].width(), draws[0].height());
		this.segment = seg;
		for (DimensionDrawable dim : draws) {
			this.draws.add(dim);
		}

	}

	@Override
	public int width() {

		return draws.getFirst().width();
	}

	@Override
	public int height() {

		return draws.getFirst().height();
	}

	@Override
	public void render(Graphics g, GameContainer container) {
		g.translate(0, 0);
	}

	@Override
	public void gUpdate(int delta, boolean condition) {
	}

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

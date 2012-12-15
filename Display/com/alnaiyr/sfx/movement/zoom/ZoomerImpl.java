/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.sfx.movement.zoom;

import org.jbox2d.common.Vec2;
import org.newdawn.slick.Graphics;

import com.alnaiyr.sfx.movement.Translater;

/**
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class ZoomerImpl implements Zoomer, Translater {

	/*---------------------
	 * 
	 * Variables
	 * 
	 *----------------------*/

	private int width;
	private int height;

	private Vec2 coord;

	private float zoom;

	/*---------------------
	 * 
	 * Methods
	 * 
	 *----------------------*/

	@Override
	public void zoomIn(float speed, Graphics g) {
		zoom += speed;
		follow(g);
	}

	@Override
	public void zoomOut(float speed, Graphics g) {
		zoom -= speed;
		follow(g);
	}

	@Override
	public void unZoom(Graphics g) {
		zoom = 1;
	}

	@Override
	public float getZoom() {
		return zoom;
	}

	@Override
	public void setZoom(float zoom) {
		this.zoom = zoom;
	}

	@Override
	public void follow(Graphics g) {
		g.scale(zoom, zoom);
		g.translate(width / 2 / zoom - coord.x(), height / 2 / zoom - coord.y());

	}

}

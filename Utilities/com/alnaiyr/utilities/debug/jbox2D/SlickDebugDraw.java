/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.utilities.debug.jbox2D;

import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.common.Color3f;
import org.jbox2d.common.OBBViewportTransform;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.general.IV;

/**
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class SlickDebugDraw extends DebugDraw {

	/*---------------------
	 * 
	 * Variables
	 * 
	 *----------------------*/

	// World 0,0 maps to offset on screen
	protected Vec2 offset;
	protected float scaleFactor = 50.0f;
	protected float yFlip = -1.0f; // flip y coordinate
	private Graphics g;

	/*---------------------
	 * 
	 * Constructor
	 * 
	 *----------------------*/

	public SlickDebugDraw(GameContainer container) {
		super(new OBBViewportTransform());

		this.g = container.getGraphics();

		this.offset = new Vec2(.5f * IV.getWidth(), .5f * IV.getHeight());
		this.setFlags(0x0053);// draw shapes, joints, bounding boxes
	}

	/*---------------------
	 * 
	 * Methods
	 * 
	 *----------------------*/

	public void drawImage(Image image, Vec2 position, float rotation,
			float localScale, Vec2 localOffset, float halfImageWidth,
			float halfImageHeight) {
		float s = localScale * scaleFactor;
		PlanVector p = getWorldToScreen(position);
		float angle = (float) Math.toDegrees(rotation);
		g.rotate(p.x(), p.y(), -angle);
		image.draw(p.x() - s * halfImageWidth, p.y() - s * halfImageHeight, s);
		g.rotate(p.x(), p.y(), angle);
	}

	@Override
	public void drawPoint(PlanVector argPoint, float argRadiusOnScreen,
			Color3f argColor) {
		g.setColor(new Color(argColor.x, argColor.y, argColor.z));

		PlanVector sp1 = getWorldToScreen(argPoint);

		sp1.set(sp1.x() - argRadiusOnScreen, sp1.y() - argRadiusOnScreen);

		g.fillOval(sp1.x(), sp1.y(), argRadiusOnScreen * 2,
				argRadiusOnScreen * 2);

		g.setColor(Color.white);
	}

	@Override
	public void drawSolidPolygon(PlanVector[] vertices, int vertexCount,
			Color3f color) {

		Color c = g.getColor();
		g.setColor(new Color(color.x, color.y, color.z));
		PlanVector last = getWorldToScreen(vertices[vertexCount - 1]);
		for (int i = 0; i < vertexCount; i++) {
			PlanVector current = getWorldToScreen(vertices[i]);
			g.drawLine(0.5f + current.x(), 0.5f + current.y(), 0.5f + last.x(),
					0.5f + last.y());
			last = current;
		}
		g.setColor(c);

	}

	@Override
	public void drawCircle(PlanVector center, float radius, Color3f color) {

		Color c = g.getColor();
		g.setColor(new Color(color.x, color.y, color.z));
		PlanVector screenCenter = getWorldToScreen(center);

		float screenRadius = scaleFactor * radius;
		// x1, y1 are upper left corner
		float x1 = screenCenter.x() - screenRadius;
		float y1 = screenCenter.y() - screenRadius;
		g.drawOval(x1, y1, 2 * screenRadius, 2 * screenRadius);
		g.setColor(c);

	}

	@Override
	public void drawSolidCircle(PlanVector center, float radius,
			PlanVector axis, Color3f color) {
		Color c = g.getColor();

		PlanVector screenCenter = getWorldToScreen(center);
		float screenRadius = scaleFactor * radius;
		// x1, y1 are upper left corner
		float x1 = screenCenter.x() - screenRadius;
		float y1 = screenCenter.y() - screenRadius;
		// solid outline and
		g.setColor(new Color(color.x, color.y, color.z));
		g.drawOval(x1, y1, 2 * screenRadius, 2 * screenRadius);

		if (axis != null) {
			Vec2 saxis = new Vec2();
			saxis.set(axis).mulLocal(radius).addLocal(center);
			drawSegment(center, saxis, color);
		}

		// semi-transparent fill
		g.setColor(new Color(color.x, color.y, color.z, .4f));
		g.fillOval(x1, y1, 2 * screenRadius, 2 * screenRadius);
		g.setColor(c);

	}

	@Override
	public void drawSegment(PlanVector p1, PlanVector p2, Color3f color) {

		Color c = g.getColor();
		g.setColor(new Color(color.x, color.y, color.z));
		PlanVector screenP1 = getWorldToScreen(p1);
		PlanVector screenP2 = getWorldToScreen(p2);
		g.drawLine(screenP1.x(), screenP1.y(), screenP2.x(), screenP2.y());
		g.setColor(c);
	}

	@Override
	public void drawTransform(Transform xf) {
		PlanVector temp = new Vec2();
		PlanVector temp2 = new Vec2();
		temp = getWorldToScreen(xf.p);
		temp2.set(0, 0);
		float k_axisScale = 0.4f;

		g.setColor(Color.cyan);

		temp2.set(xf.p.x + k_axisScale * xf.q.c, xf.p.y + k_axisScale * xf.q.s);
		getWorldToScreenToOut(temp2, temp2);
		g.drawLine(temp.x(), temp.y(), temp2.x(), temp2.y());

		g.setColor(Color.green);
		temp2.set(xf.p.x + k_axisScale * xf.q.c, xf.p.y + k_axisScale * xf.q.s);
		getWorldToScreenToOut(temp2, temp2);
		g.drawLine(temp.x(), temp.y(), temp2.x(), temp2.y());
	}

	@Override
	public void drawString(float x, float y, String s, Color3f color) {
		// no world-to-screen transformation; x and y are screen coordinates

		Color c = g.getColor();
		g.setColor(new Color((int) color.x, (int) color.y, (int) color.z));
		g.drawString(s, x, y);
		g.setColor(c);
	}

	/*---------------------
	 * 
	 * Getters / Setters
	 * 
	 *----------------------*/
}

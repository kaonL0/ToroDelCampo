/*******************************************************************************
 * Copyright (c) 2011, Daniel Murphy All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met: *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer. * Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials provided
 * with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 ******************************************************************************/
/**
 * Created at 4:35:29 AM Jul 15, 2010
 */
package org.jbox2d.callbacks;

import org.jbox2d.common.Color3f;
import org.jbox2d.common.IViewportTransform;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;

import com.alnaiyr.coordinates.PlanVector;

// updated to rev 100
/**
 * Implement this abstract class to allow JBox2d to automatically draw your
 * physics for debugging purposes. Not intended to replace your own custom
 * rendering routines!
 * 
 * @author Daniel Murphy
 */
public abstract class DebugDraw {

	public static final int e_shapeBit = 0x0001; // /< draw shapes
	public static final int e_jointBit = 0x0002; // /< draw joint connections
	public static final int e_aabbBit = 0x0004; // /< draw core (TOI) shapes
	public static final int e_pairBit = 0x0008; // /< draw axis aligned bounding
												// boxes
	public static final int e_centerOfMassBit = 0x0010; // /< draw center of
														// mass frame
	public static final int e_dynamicTreeBit = 0x0020; // /< draw dynamic tree.

	protected int m_drawFlags;
	protected final IViewportTransform viewportTransform;

	public DebugDraw(IViewportTransform viewport) {
		m_drawFlags = 0;
		viewportTransform = viewport;
	}

	public void setFlags(int flags) {
		m_drawFlags = flags;
	}

	public int getFlags() {
		return m_drawFlags;
	}

	public void appendFlags(int flags) {
		m_drawFlags |= flags;
	}

	public void clearFlags(int flags) {
		m_drawFlags &= ~flags;
	}

	/**
	 * Draw a closed polygon provided in CCW order. This implementation uses
	 * {@link #drawSegment(PlanVector, PlanVector, Color3f)} to draw each side
	 * of the polygon.
	 * 
	 * @param vertices
	 * @param vertexCount
	 * @param color
	 */
	public void drawPolygon(PlanVector[] vertices, int vertexCount,
			Color3f color) {
		if (vertexCount == 1) {
			drawSegment(vertices[0], vertices[0], color);
			return;
		}

		for (int i = 0; i < vertexCount - 1; i += 1) {
			drawSegment(vertices[i], vertices[i + 1], color);
		}

		if (vertexCount > 2) {
			drawSegment(vertices[vertexCount - 1], vertices[0], color);
		}
	}

	public abstract void drawPoint(PlanVector argPoint,
			float argRadiusOnScreen, Color3f argColor);

	/**
	 * Draw a solid closed polygon provided in CCW order.
	 * 
	 * @param vertices
	 * @param vertexCount
	 * @param color
	 */
	public abstract void drawSolidPolygon(PlanVector[] vertices,
			int vertexCount, Color3f color);

	/**
	 * Draw a circle.
	 * 
	 * @param center
	 * @param radius
	 * @param color
	 */
	public abstract void drawCircle(PlanVector center, float radius,
			Color3f color);

	/**
	 * Draw a solid circle.
	 * 
	 * @param center
	 * @param radius
	 * @param axis
	 * @param color
	 */
	public abstract void drawSolidCircle(PlanVector center, float radius,
			PlanVector axis, Color3f color);

	/**
	 * Draw a line segment.
	 * 
	 * @param p1
	 * @param p2
	 * @param color
	 */
	public abstract void drawSegment(PlanVector p1, PlanVector p2, Color3f color);

	/**
	 * Draw a transform. Choose your own length scale
	 * 
	 * @param xf
	 */
	public abstract void drawTransform(Transform xf);

	/**
	 * Draw a string.
	 * 
	 * @param x
	 * @param y
	 * @param s
	 * @param color
	 */
	public abstract void drawString(float x, float y, String s, Color3f color);

	public void drawString(Vec2 pos, String s, Color3f color) {
		drawString(pos.x, pos.y, s, color);
	}

	public IViewportTransform getViewportTranform() {
		return viewportTransform;
	}

	/**
	 * @param x
	 * @param y
	 * @param scale
	 * @see IViewportTransform#setCamera(float, float, float)
	 */
	public void setCamera(float x, float y, float scale) {
		viewportTransform.setCamera(x, y, scale);
	}

	/**
	 * @param argScreen
	 * @param argWorld
	 * @see org.jbox2d.common.IViewportTransform#getScreenToWorld(PlanVector,
	 *      PlanVector)
	 */
	public void getScreenToWorldToOut(PlanVector argScreen, PlanVector argWorld) {
		viewportTransform.getScreenToWorld(argScreen, argWorld);
	}

	/**
	 * @param argWorld
	 * @param argScreen
	 * @see org.jbox2d.common.IViewportTransform#getWorldToScreen(PlanVector,
	 *      PlanVector)
	 */
	public void getWorldToScreenToOut(PlanVector argWorld, PlanVector argScreen) {
		viewportTransform.getWorldToScreen(argWorld, argScreen);
	}

	/**
	 * Takes the world coordinates and puts the corresponding screen coordinates
	 * in argScreen.
	 * 
	 * @param worldX
	 * @param worldY
	 * @param argScreen
	 */
	public void getWorldToScreenToOut(float worldX, float worldY,
			PlanVector argScreen) {
		argScreen.setLocal(worldX, worldY);
		viewportTransform.getWorldToScreen(argScreen, argScreen);
	}

	/**
	 * takes the world coordinate (argWorld) and returns the screen coordinates.
	 * 
	 * @param argWorld
	 */
	public PlanVector getWorldToScreen(PlanVector argWorld) {
		Vec2 screen = new Vec2();
		// DB.test(argWorld);
		viewportTransform.getWorldToScreen(argWorld, screen);
		// DB.test(screen);
		return screen;
	}

	/**
	 * Takes the world coordinates and returns the screen coordinates.
	 * 
	 * @param worldX
	 * @param worldY
	 */
	public PlanVector getWorldToScreen(float worldX, float worldY) {
		Vec2 argScreen = new Vec2(worldX, worldY);
		viewportTransform.getWorldToScreen(argScreen, argScreen);
		return argScreen;
	}

	/**
	 * takes the screen coordinates and puts the corresponding world coordinates
	 * in argWorld.
	 * 
	 * @param screenX
	 * @param screenY
	 * @param argWorld
	 */
	public void getScreenToWorldToOut(float screenX, float screenY,
			PlanVector argWorld) {
		argWorld.setLocal(screenX, screenY);
		viewportTransform.getScreenToWorld(argWorld, argWorld);
	}

	/**
	 * takes the screen coordinates (argScreen) and returns the world
	 * coordinates
	 * 
	 * @param argScreen
	 */
	public PlanVector getScreenToWorld(PlanVector argScreen) {
		Vec2 world = new Vec2();
		viewportTransform.getScreenToWorld(argScreen, world);
		return world;
	}

	/**
	 * takes the screen coordinates and returns the world coordinates.
	 * 
	 * @param screenX
	 * @param screenY
	 */
	public PlanVector getScreenToWorld(float screenX, float screenY) {
		Vec2 screen = new Vec2(screenX, screenY);
		viewportTransform.getScreenToWorld(screen, screen);
		return screen;
	}
}

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
package org.jbox2d.common;

import com.alnaiyr.coordinates.PlanVector;

/**
 * Orientated bounding box viewport transform
 * 
 * @author Daniel Murphy
 */
public class OBBViewportTransform implements IViewportTransform {

	public static class OBB {
		public final Mat22 R = new Mat22();
		public final Vec2 center = new Vec2();
		public final Vec2 extents = new Vec2();
	}

	protected final OBB box = new OBB();
	private boolean yFlip = false;
	private final Mat22 yFlipMat = new Mat22(1, 0, 0, -1);
	private final Mat22 yFlipMatInv = yFlipMat.invert();

	public OBBViewportTransform() {
		box.R.setIdentity();
	}

	public void set(OBBViewportTransform vpt) {
		box.center.set(vpt.box.center);
		box.extents.set(vpt.box.extents);
		box.R.set(vpt.box.R);
		yFlip = vpt.yFlip;
	}

	/**
	 * @see IViewportTransform#setCamera(float, float, float)
	 */
	@Override
	public void setCamera(float x, float y, float scale) {
		box.center.setLocal(x, y);
		Mat22.createScaleTransform(scale, box.R);
	}

	/**
	 * @see IViewportTransform#getExtents()
	 */
	@Override
	public PlanVector getExtents() {
		return box.extents;
	}

	/**
	 * @see IViewportTransform#setExtents(PlanVector)
	 */
	@Override
	public void setExtents(PlanVector argExtents) {
		box.extents.set(argExtents);
	}

	/**
	 * @see IViewportTransform#setExtents(float, float)
	 */
	@Override
	public void setExtents(float argHalfWidth, float argHalfHeight) {
		box.extents.setLocal(argHalfWidth, argHalfHeight);
	}

	/**
	 * @see IViewportTransform#getCenter()
	 */
	@Override
	public PlanVector getCenter() {
		return box.center;
	}

	/**
	 * @see IViewportTransform#setCenter(PlanVector)
	 */
	@Override
	public void setCenter(PlanVector argPos) {
		box.center.set(argPos);
	}

	/**
	 * @see IViewportTransform#setCenter(float, float)
	 */
	@Override
	public void setCenter(float x, float y) {
		box.center.setLocal(x, y);
	}

	/**
	 * gets the transform of the viewport, transforms around the center. Not a
	 * copy.
	 * 
	 * @return
	 */
	public Mat22 getTransform() {
		return box.R;
	}

	/**
	 * Sets the transform of the viewport. Transforms about the center.
	 * 
	 * @param transform
	 */
	public void setTransform(Mat22 transform) {
		box.R.set(transform);
	}

	/**
	 * Multiplies the obb transform by the given transform
	 * 
	 * @param argTransform
	 */
	public void mulByTransform(Mat22 argTransform) {
		box.R.mulLocal(argTransform);
	}

	/**
	 * @see IViewportTransform#isYFlip()
	 */
	@Override
	public boolean isYFlip() {
		return yFlip;
	}

	/**
	 * @see IViewportTransform#setYFlip(boolean)
	 */
	@Override
	public void setYFlip(boolean yFlip) {
		this.yFlip = yFlip;
	}

	// djm pooling
	private final Mat22 inv = new Mat22();

	/**
	 * @see IViewportTransform#getScreenVectorToWorld(PlanVector, PlanVector)
	 */
	@Override
	public void getScreenVectorToWorld(PlanVector argScreen, PlanVector argWorld) {
		inv.set(box.R);
		inv.invertLocal();
		inv.mulToOut(argScreen, argWorld);
		if (yFlip) {
			yFlipMatInv.mulToOut(argWorld, argWorld);
		}
	}

	/**
	 * @see IViewportTransform#getWorldVectorToScreen(PlanVector, PlanVector)
	 */
	@Override
	public void getWorldVectorToScreen(PlanVector argWorld, PlanVector argScreen) {
		box.R.mulToOut(argWorld, argScreen);
		if (yFlip) {
			yFlipMatInv.mulToOut(argScreen, argScreen);
		}
	}

	/**
	 * @see IViewportTransform#getWorldToScreen(PlanVector, PlanVector)
	 */
	@Override
	public void getWorldToScreen(PlanVector argWorld, PlanVector argScreen) {
		argScreen.set(argWorld);
		argScreen.subLocal(box.center);
		box.R.mulToOut(argScreen, argScreen);
		if (yFlip) {
			yFlipMat.mulToOut(argScreen, argScreen);
		}
		argScreen.addLocal(box.extents);
	}

	private final Mat22 inv2 = new Mat22();

	/**
	 * @see IViewportTransform#getScreenToWorld(PlanVector, PlanVector)
	 */
	@Override
	public void getScreenToWorld(PlanVector argScreen, PlanVector argWorld) {
		argWorld.set(argScreen);
		argWorld.subLocal(box.extents);
		box.R.invertToOut(inv2);
		inv2.mulToOut(argWorld, argWorld);
		if (yFlip) {
			yFlipMatInv.mulToOut(argWorld, argWorld);
		}
		argWorld.addLocal(box.center);
	}
}

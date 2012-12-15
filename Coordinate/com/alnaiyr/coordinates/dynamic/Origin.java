package com.alnaiyr.coordinates.dynamic;

import org.newdawn.slick.geom.Vector2f;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.general.IV;
import com.alnaiyr.models.gamestrategy.NoEffect;

/**
 * the class describes the Origin of all PlanCoordinateSystem, like the global
 * reference
 * 
 * @author IgoЯ
 * 
 * @version 1.2
 *          <p>
 *          <Strong> Version change:</strong><br/>
 *          <ul>
 *          <li>Fully functional</li>
 *          <li>Added the static reference (0,0)</li>
 *          </ul>
 *          </p>
 */
public class Origin extends ReferencedCoordinate {

	public final float x;
	public final float y;

	/* *******************
	 * 
	 * Constructors
	 * 
	 * ***************************
	 */

	/**
	 * Static coordinates referring the the real origin, (0,0)
	 * 
	 * @author IgoЯ
	 * @version 1.0
	 *          <p>
	 *          <Strong>Version Change:</Strong>
	 *          <ul>
	 *          <li><em>No Changes</em></li>
	 *          </ul>
	 */
	public static final PlanVector ref = new PlanVector() {

		@NoEffect
		@Override
		public void setLocal(float x, float y) {
			// void
		}

		@Override
		public float y() {
			return 0;
		}

		@Override
		public float x() {
			return 0;
		}

		@Override
		public float angle() {
			return 0;
		}

		@NoEffect
		@Override
		public PlanVector set(float x, float y) {
			return this;
			// void
		}

		@Override
		public float normalize() {

			return 0;
		}

		@Override
		public float lengthSquared() {

			return 0;
		}

		@Override
		public PlanVector negateLocal() {

			return Origin.ref;
		}

		@Override
		public PlanVector subLocal(PlanVector center) {

			return Origin.ref;
		}

		@Override
		public PlanVector addLocal(PlanVector extents) {

			return Origin.ref;
		}

		@Override
		public PlanVector mulLocal(float f) {

			return Origin.ref;
		}

		@Override
		public PlanVector set(PlanVector coord) {

			return Origin.ref;
		}

		@Override
		public float length() {

			return 0;
		}

		@Override
		public PlanVector clone() {

			return this;
		}
	};

	/**
     * 
     */
	public Origin() {
		super(null);
		x = 0;
		y = 0;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param percentOfWindow
	 */
	public Origin(float x, float y, boolean percentOfWindow) {
		super(null);
		if (percentOfWindow) {
			this.x = x * IV.getWidth();
			this.y = y * IV.getHeight();
		} else {
			this.x = x;
			this.y = y;
		}
	}

	/**
	 * 
	 * @param vector
	 */
	public Origin(Vector2f vector) {
		super(null);
		x = vector.x;
		y = vector.y;
	}

	/* *******************
	 * 
	 * Methods
	 * 
	 * ***************************
	 */
	@Override
	public PlanVector set(float x, float y) {
		return this;
	}

	@Override
	public float x() {
		return x;
	}

	@Override
	public float y() {
		return y;
	}

	/** return 0 */
	@Override
	public float angle() {
		return 0;
	}

	@Override
	public String toString() {
		return new String("Origin coordinates: X: " + x + " Y: " + y);
	}

	@NoEffect
	@Override
	public void addLocalX(float x) {
		// empty
	}

	@NoEffect
	@Override
	public void addLocalY(float y) {
		// empty
	}

	@NoEffect
	@Override
	public void setLocal(float x, float y) {
		// empty
	}

	@NoEffect
	@Override
	protected float getLocXFact() {
		return 0;
	}

	@NoEffect
	@Override
	protected float getLocYFact() {
		return 0;
	}

	@Override
	public float normalize() {
		return 0;
	}

	@Override
	public float lengthSquared() {
		return 0;
	}

	@Override
	public PlanVector negateLocal() {
		return this;
	}

	@Override
	public PlanVector subLocal(PlanVector center) {
		return this;
	}

	@Override
	public PlanVector addLocal(PlanVector extents) {
		return this;
	}

	@Override
	public PlanVector mulLocal(float f) {

		return this;
	}

	@Override
	public PlanVector set(PlanVector coord) {

		return this;
	}

	@Override
	public float length() {

		return 0;
	}

	@Override
	public PlanVector clone() {

		return new Origin(x, y, false);
	}

}

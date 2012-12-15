package com.alnaiyr.coordinates.container;

import org.newdawn.slick.geom.Vector2f;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.coordinates.dynamic.Origin;
import com.alnaiyr.math.numbers.ValueStorer;

/**
 * Contains a Coordinable, making it completely safe and controlled (you can
 * destroy the inside Coordinable without destroying the link made between
 * itself and sisters Coordinables
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class CoordinateContainerImpl implements ValueStorer<PlanVector>,
		CoordinateContainer {

	protected PlanVector coord;
	protected PlanVector step = Origin.ref;

	/*------------------------------
	 * 
	 * Constructors
	 * 
	 *--------------------------------*/

	/**
	 * @param coord
	 */
	public CoordinateContainerImpl(PlanVector coord) {
		this.coord = coord;
	}

	/**
	 * @param coord
	 * @param step
	 */
	public CoordinateContainerImpl(PlanVector coord, PlanVector step) {
		this.coord = coord;
		this.step = step;
	}

	public CoordinateContainerImpl(PlanVector coord, float stepX, float stepY) {
		this(coord, new Vector2f(stepX, stepY));

	}

	/*------------------------------
	 * 
	 * Methods
	 * 
	 *--------------------------------*/

	@Override
	public void update(int delta, boolean condition) {
	}

	@Override
	public PlanVector clone() {
		return new CoordinateContainerImpl(coord.clone(), step.clone());
	}

	@Override
	public float normalize() {
		return coord.normalize();
	}

	@Override
	public float lengthSquared() {
		return coord.lengthSquared();
	}

	@Override
	public float length() {
		return coord.length();
	}

	@Override
	public PlanVector negateLocal() {
		return coord.negateLocal();
	}

	@Override
	public PlanVector subLocal(PlanVector center) {
		return coord.subLocal(center);
	}

	@Override
	public PlanVector addLocal(PlanVector extents) {
		return coord.addLocal(extents);
	}

	@Override
	public PlanVector mulLocal(float f) {
		return coord.mulLocal(f);
	}

	@Override
	public PlanVector set(PlanVector coord) {
		return this.coord.set(coord);
	}

	/*------------------------------
	 * 
	 * Getters Setters
	 * 
	 *--------------------------------*/

	@Override
	public PlanVector getValue() {
		return coord;
	}

	@Override
	public void setValue(PlanVector value) {
		coord = value;
	}

	@Override
	public float x() {
		return coord.x() - step.x();
	}

	@Override
	public float y() {
		return coord.y() - step.y();
	}

	@Override
	public void setLocal(float x, float y) {
		coord.setLocal(x, y);
	}

	@Override
	public void setCoord(PlanVector coord) {
		setValue(coord);
	}

	@Override
	public float angle() {
		return coord.angle();
	}

	@Override
	public PlanVector set(float x, float y) {
		return coord.set(x, y);
	}

	/**
	 * @return the step
	 */
	public PlanVector getStep() {
		return step;
	}

	/**
	 * @param step
	 *            the step to set
	 */
	public void setStep(PlanVector step) {
		this.step = step;
	}

	@Override
	public PlanVector getCoord() {

		return coord;
	}

	@Override
	public int width() {

		return (int) step.x();
	}

	@Override
	public int height() {

		return (int) step.y();
	}

}

/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.math.geom.shape;

import org.newdawn.slick.geom.Vector2f;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.renderables.Dimensionnable;

/**
 * A basic Rectangle, not able to rotate
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class Rectangle implements Dimensionnable {

	/*---------------------
	 * 
	 * Variables
	 * 
	 *----------------------*/

	private PlanVector	coord;
	private int			width, height;

	public Rectangle(final PlanVector coord, final int width, final int height) {
		super();
		this.coord = coord;
		this.width = width;
		this.height = height;
	}

	public Rectangle() {
		coord = new Vector2f();
		width = 0;
		height = 0;
	}

	public Rectangle(final Rectangle other) {
		coord = other.coord.clone();
		width = other.width;
		height = other.height;
	}

	public Rectangle(final float x, final float y, final int width,
			final int height) {
		super();
		coord = new Vector2f(x, y);
		this.width = width;
		this.height = height;
	}

	/*--------------------
	 * 
	 * 
	 * Utilities 
	 * 
	 *-----------------*/
	/**
	 * 
	 * @param rect
	 * @return true if one of the dimension of rect is bigger than mine
	 */
	public boolean isExceedingMe(final Rectangle rect) {
		return rect.width > width || rect.height > height;
	}

	/**
	 * 
	 * @return true if one of the dimension is 0;
	 */
	public boolean isLine() {
		return width == 0 || height == 0;
	}

	public float getBiggestDimension() {
		return isHorizontal() ? width : height;
	}

	public float getSmallestDimension() {
		return isHorizontal() ? height : width;
	}

	/**
	 * Note that is rectangle is a square, it is considered as vertical
	 * 
	 * @return true if width is bigger than height
	 */
	public boolean isHorizontal() {
		return width > height;
	}

	/**
	 * 
	 * @return true if width = height;
	 */
	public boolean isSquare() {
		return width == height;
	}

	@Override
	public Rectangle clone() {
		return new Rectangle(coord.clone(), width, height);
	}

	public void morph(final Rectangle other) {
		coord = other.coord.clone();
		width = other.width;
		height = other.height;
	}

	private static Rectangle	temp;

	@Override
	public boolean equals(final Object obj) {
		temp = (Rectangle) obj;
		return temp.x() == x() && temp.y() == y() && temp.width == width
				&& temp.height == height;
	}

	/*---------------------
	 * 
	 * Getters / Setters
	 * 
	 *----------------------*/

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public int width() {

		return width;
	}

	@Override
	public int height() {

		return height;
	}

	public void setWidth(final int width) {
		this.width = width;
	}

	public void setHeight(final int height) {
		this.height = height;
	}

	public float x() {
		return coord.x();
	}

	public float y() {
		return coord.y();
	}

	/**
	 * Changes coordinates of this rectangle, potentially loosing all references
	 * information
	 * 
	 * @param coord
	 */
	public void changeCoord(final PlanVector coord) {
		this.coord = coord;
	}

	public PlanVector getCoord() {
		return coord;
	}

	/**
	 * 
	 * @return top left corner, non referenced
	 */
	public PlanVector getTopRightCorner() {
		return new Vector2f(coord.x() + width, coord.y());
	}

	public PlanVector getDownRightCorner() {
		return new Vector2f(coord.x() + width, coord.y() + height);
	}

	public PlanVector getDownLeftCorner() {
		return new Vector2f(coord.x(), coord.y() + height);
	}

	public PlanVector getCenter() {
		return new Vector2f(coord.x() + width / 2, coord.y() + height / 2);
	}

}

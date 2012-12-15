/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.level.tiles;

/**
 * Describes any class containing data in the space and able to divide itself
 * recursively.
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public interface RecursiveQuad<Type> extends QuadZoned<Type> {

	/**
	 * 
	 * @return the quad that contains this zone.
	 */
	public RecursiveQuad<Type> getNode();

	/**
	 * 
	 * @return all children contained by this quad.
	 */
	public RecursiveQuad<Type>[] getChildrens();

	/**
	 * 
	 * @param i
	 *            (can represent the position of the children for instance)
	 * @return the children corresponding to this integer parameter.
	 */
	public RecursiveQuad<Type> getChildren(int i);

}

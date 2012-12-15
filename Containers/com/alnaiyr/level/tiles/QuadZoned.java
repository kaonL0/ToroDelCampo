/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.level.tiles;

import com.alnaiyr.level.tiles.grid.QuadPositions;

/**
 * Describes any object able to contains data in the space (and being a quad,
 * which means that it has a rectangle shape (4 corners))
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public interface QuadZoned<Type> {

	/**
	 * @return data contained by this quad
	 */
	public Type getData();

	/**
	 * 
	 * @return the column number of the quad (or general coordinate)
	 */
	public int x();

	/**
	 * 
	 * @return the line number of the quad (or general coordinate)
	 */
	public int y();

	public void setCoord(int x, int y);

	/**
	 * @param pos
	 * @return the quad next to this zone considering the position put in
	 *         entrance. returns itself if no neighbor was found.
	 */
	public QuadZoned<Type> getNeighbor(QuadPositions pos);

	/**
	 * 
	 * @param pos
	 * @return true if the neighbor does exist.
	 */
	public boolean hasNeighbor(QuadPositions pos);

	/**
	 * Puts a quad as a neighbor, and update it to specify the counter effect.
	 * This method should be used all the time for normal usage.
	 * 
	 * @param pos
	 * @param neighbor
	 */
	public void putAndUpdateNeighbor(QuadPositions pos, QuadZoned<Type> neighbor);

	/**
	 * Puts a quad as a neighbor, but does not ensure reciprocity, useful for
	 * strange builded map where going back leads to a new way... No used found
	 * yet, only internal purposes.
	 * 
	 * @param pos
	 * @param neighbor
	 */
	public void putNeighbor(QuadPositions pos, QuadZoned<Type> neighbor);

}

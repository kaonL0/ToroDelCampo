package com.alnaiyr.math.geom;

/** an interface describing different methods to manipulate shapes
 * 
 * @author Igor
 *@version 0.1 Just implement adding
 * @param <S> 
 * @param <T> 
 */
public interface ShapeComparison<S,T> {

	/** Adds a Shape to another kind of shape (different with ShapeUtil: combine them with a comparison point of view )
	 * 
	 * @param where
	 * @param to_add
	 * @return shape
	 */
	abstract S put(S where,T to_add);
	
	/**Describes a different way to add, as GeomUtil is not working all the time the way I want it to...
	 * 
	 * @param where
	 * @param to_add
	 * @return shape
	 */
	abstract S add(S where,S to_add);
	
}

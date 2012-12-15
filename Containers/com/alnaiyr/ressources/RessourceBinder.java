/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.ressources;

/**
 * Is able to set a ressource of any type, and check if the ressource was binded
 * or not
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public interface RessourceBinder<Type> {

	/**
	 * Binds the value to the corresponding enum and field
	 * 
	 * @param pack
	 * @param name
	 * @param value
	 */
	public void bind(String pack, String name, Type value);

	public boolean isBinded(String pack, String name);

}

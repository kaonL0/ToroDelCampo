
package com.alnaiyr.physics.destruction.shapes;

import org.newdawn.slick.GameContainer;


/** Describes how should react anything that can be destroyed. Two type have to
 * be choose: the type of damage, and the type of thing to destroy.

 * 
 * @author IgoЯ
 * @version 1.1
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>Works</em></li>
 *          <li><em>Removed one type</em></li>
 *          </ul> */
public interface DamageHandler<Destroy> {
	
	/** Damage the object, and return the new object, damaged
	 * 
	 * @param destroy
	 *        to be given damage
	 * @param container TODO
	 * @return */
	public void damage(Destroy destroy, GameContainer container);
	
}

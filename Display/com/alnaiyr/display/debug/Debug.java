package com.alnaiyr.display.debug;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/** Something that can display a debug
 * @author IgoЯ
 * @version 1.0
 * <p><Strong>Version Change:</Strong>
 *		<ul>
 *			<li><em>No Changes</em></li>
 *		</ul>
 */
public interface Debug {

    /**
     * @param container
     * @param g
     * @param condition
     */
    public void debug(GameContainer container,Graphics g,boolean condition);
    
}

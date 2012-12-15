package com.alnaiyr.level.layer;

/** Is able to set level
 * @author IgoЯ
 * @version 1.0
 * <p><Strong>Version Change:</Strong>
 *		<ul>
 *			<li><em>No Changes</em></li>
 *		</ul>
 */
public interface Levelable {
    /** 
     * 
     * @return the layering level of this object
     */
    public int getLevel();
    
    /** Changes the layering to a new one
     * 
     * @param level
     */
    public void setLevel(int level);

}

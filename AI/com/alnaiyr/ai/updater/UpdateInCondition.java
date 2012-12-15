package com.alnaiyr.ai.updater;

import com.alnaiyr.ai.updater.condition.Conditionable;
/** Can be updataed on a given external ConditionGiver
 * 
 * @author IgoЯ
 * @version 1.0
 * <p><Strong>Version Change:</Strong>
 *		<ul>
 *			<li><em>No Changes</em></li>
 *		</ul>
 */
public interface UpdateInCondition extends Updatable{

    /** Sets a condition giver to look at
     * 
     * @param giver
     */
    public void setCondition(Conditionable giver);
    
    /** Gets the condition giver it points to
     * 
     * @return condition
     */
    public Conditionable getCondition();
}

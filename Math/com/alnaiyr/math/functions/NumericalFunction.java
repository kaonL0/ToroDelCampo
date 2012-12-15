package com.alnaiyr.math.functions;

/** Gets a value given a number of parameters
 * @author IgoЯ
 * @version 1.0
 * <p><Strong>Version Change:</Strong>
 *		<ul>
 *			<li><em>No Changes</em></li>
 *		</ul>
 */
public interface NumericalFunction {

    /** Given a certain number of parameters, calculate the corresponding value.
     * The first parameter should always be the abscissa parameter (or 2D coordinates), and then parameters will depends on functions
     * @param params
     * @return the value
     */
    public float getValue(Float... params);
    
    
}

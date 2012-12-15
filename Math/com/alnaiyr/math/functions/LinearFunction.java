package com.alnaiyr.math.functions;

import org.newdawn.slick.util.Log;

/**
 * @author IgoЯ
 * @version 1.0
 * <p><Strong>Version Change:</Strong>
 *		<ul>
 *			<li><em>No Changes</em></li>
 *		</ul>
 */
class LinearFunction implements NumericalFunction{

    private static LinearFunction instance;

    /**
     * @param value
     */
    private LinearFunction() {
	super();
    }

    /**
     * @return current instance
     */
    public static LinearFunction getInstance() {
	if (instance == null)
	    instance = new LinearFunction();
	return instance;
    }

    /**1st parameter, 2nd is multiply, from 3rd is add
     * 
     */
    @Override
    public float getValue(Float... params) {
	try {
	    if(params.length==1)
		return params[0];
	    for (int i = 2; i < params.length; i++)
		params[1] += params[i];
	    return params[0]*params[1];
	} catch (IndexOutOfBoundsException e) {
	    Log.error("fails to interpret parameters for Sin Function!");
	    return 0;
	}
    }

    
}

package com.alnaiyr.math.functions;

import org.newdawn.slick.util.FastTrig;
import org.newdawn.slick.util.Log;

/**
 * Sin() function
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
class SinusFunction implements NumericalFunction {

    private static SinusFunction instance;

    /**
     * @param value
     */
    private SinusFunction() {
	super();
    }

    /**
     * @return current instance
     */
    public static SinusFunction getInstance() {
	if (instance == null)
	    instance = new SinusFunction();
	return instance;
    }

    /**1st parameter, 2nd is multiply, from 3rd is add
     * 
     */
    @Override
    public float getValue(Float... params) {
	try {
	    for (int i = 3; i < params.length; i++)
		params[2] += params[i];
	    return (float) FastTrig.sin(params[0] * params[1] + params[2]);
	} catch (IndexOutOfBoundsException e) {
	    Log.error("fails to interpret parameters for Sin Function!");
	    return 0;
	}
    }

}

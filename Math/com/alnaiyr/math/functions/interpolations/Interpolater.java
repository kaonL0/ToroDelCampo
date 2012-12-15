package com.alnaiyr.math.functions.interpolations;

import com.alnaiyr.math.numbers.profiles.percents.Percentable;

/**
 * Is able to interpolate between values
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 * @param <T>
 */
public interface Interpolater<T> {

    @SuppressWarnings("unchecked")
    public T interpolate(Interpolation inter, T from, T to, float percent,
	    T... params);

    @SuppressWarnings("unchecked")
    public T interpolate(Interpolation inter, T from, T to,
	    Percentable percent, T... params);

}

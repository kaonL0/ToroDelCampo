package com.alnaiyr.math.functions.interpolations;

/**
 * All possible interpolations
 * 
 * @author IgoЯ
 * @version 1.0
 */
public enum Interpolation {
    /**
     * no extra parameters
     */
    CONSTANT,
    /**
     * no extra parameters
     */
    LINEAR,
    /**
     * no extra parameters
     */
    COSINUS,
    /**
     * two extra parameters: 1. value before a 2. value after b
     */
    CUBIC,
    /**
     * n extra parameters: control values
     */
    BEZIER,
    /**
     * TODO
     */
    PERLIN;
}

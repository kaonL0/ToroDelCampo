package com.alnaiyr.generator;

/**
 * Is able to build something
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 * @param <S>
 */
public interface Factory<S> {

    public S build();

}

package com.alnaiyr.display.renderables;

/**
 * Is able to set dimensions to an object
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public interface DimensionSetter {
    /**
     * Sets height to a new one
     * 
     * @param height
     */
    public void setHeight(int height);

    /**
     * Sets width to a new one
     * 
     * @param width
     */
    public void setWidth(int width);
}

package com.alnaiyr.display.renderables;

import com.alnaiyr.display.renderables.render.rewrite.DataType;

/**
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public interface DimensionDrawable extends Dimensionnable, Drawable {

	public DataType getType();

}

package com.alnaiyr.utilities.images;

import org.newdawn.slick.Color;

import com.alnaiyr.math.MathU;

/**
 * Contains several utilities function for images and animation
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class ImageU {

    /**
     * Gets a totally random color (with random alpha) based on a random rgba
     * 
     * @return the random color
     */
    public static Color getRandomColor() {

	return new Color((float) Math.random(), (float) Math.random(),
		(float) Math.random(), (float) Math.random());
    }

    /**
     * Gets a random star color, meaning a color that is in between orange an
     * cyan, excluding all pink/violet colors.
     * 
     * In a more specific way: 1st: R:255 B:0 and G parameter: from 0 to 230
     * 2nd: R:0 B:255 and g parameter: from 0 to 200
     * 
     * @return a star color
     */
    public static Color getRandomStarColor() {
	switch (MathU.random(0, 2)) {
	case 0:
	    return new Color(255, MathU.random(0, 230), 0, 255);
	case 1:
	    return new Color(0, MathU.random(0, 200), 255, 255);
	}
	return null;
    }
    
   /**
    * @see #getRandomStarColor()
    * @param alpha the alpha mask
    * @return random star friendly color
    */
    public static Color getRandomStarColor(int alpha) {
   	switch (MathU.random(0, 2)) {
   	case 0:
   	    return new Color(255, MathU.random(0, 230), 0, alpha);
   	case 1:
   	    return new Color(0, MathU.random(0, 200), 255, alpha);
   	}
   	return null;
       }

}

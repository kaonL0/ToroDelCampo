
package com.alnaiyr.utilities.images;

import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;

import com.alnaiyr.math.functions.interpolations.NoiseUtilities;

/** This class intends to render a function using an Image Buffer. The function
 * has to be presented in an HashMap<Integer,float> containing all values, point
 * by point
 * 
 * @author IgoR
 * @version 1.1
 * 
 *          <p>
 *          <strong> 1.0 Version change </strong><br/>
 *          <ul>
 *          <li><em>Now fully functional</em></li>
 *          <li><em>Improved speed</em></li>
 *          <li><em>Works with Graphics now</em></li>
 *          </ul>
 *          </p> */
public class FunctionRenderer {
	
	/* ******************
	 * 
	 * Methods
	 * 
	 * ************************ */
	
	/** Generates an alpha mask image giving a point hash map. TODO: separate
	 * more
	 * 
	 * @param function
	 * @param container 
	 * @return generated image*/
	public static Image generateImage(HashMap<Integer, Float> function, GameContainer container) {
	
		return generateImage(function, 1, container);
	}
	
	/** Generates an alpha mask image giving a point hashmaps, and you can
	 * indicate any additional depth needed
	 * 
	 * @param function
	 * @param depth
	 * @param container
	 * @return generated image*/
	
	public static Image generateImage(HashMap<Integer, Float> function, float depth, GameContainer container) {
	
		int lenght = NoiseUtilities.findLength(function);
		
		float highest = NoiseUtilities.findHighestPoint(function).y;
		float lowest = NoiseUtilities.findLowestPoint(function).y;
		
		float height = Math.abs(lowest - highest);
		
		try {
			Image image = new Image(lenght + 1, (int) (height + 2 + depth));
			Graphics g = image.getGraphics();	
			for (Integer key : function.keySet()) {
				g.draw(new Line(key, function.get(key) - highest, key, function.get(key) - highest + depth));
			}
			g.flush();
			g.destroy();
			return image;
		}
		catch (SlickException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
}

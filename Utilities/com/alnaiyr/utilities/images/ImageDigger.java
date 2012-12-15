package com.alnaiyr.utilities.images;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

import com.alnaiyr.coordinates.dynamic.ReferencedCoordinate;
import com.alnaiyr.coordinates.dynamic.Origin;
import com.alnaiyr.math.MathU;


/**Handle all on image drawing. 
 * 
 * @author IgoЯ
 * @version 1.1
 * <p><Strong>Version Change:</Strong>
 *		<ul>
 *			<li><em>Working</em></li>
 *			<li><em>Now uses container</em></li>
 *		</ul>
 */
public class ImageDigger {

	/* ***********************
	 * 
	 * Methods
	 * 
	 * ************************** */

	/**
	 * Drill a hole in the image with a shape, given a color with which to drill
	 * 
	 * @param damage
	 * @param coord
	 *            of the image
	 * @param destroy
	 * @param color
	 * @param container
	 * @return damaged image
	 * @throws SlickException 
	 */
	public static Image damage(Shape damage, ReferencedCoordinate coord, Image destroy, Color color, GameContainer container) throws SlickException {
		return damage(damage, coord, 1, destroy, color, container);
	}
	
	/**
	 * Drill a hole in the image with a shape, given a color with which to drill.<br/>
	 * This is to be used in case shape and image and well positioned together
	 * 
	 * @param damage
	 * @param destroy
	 * @param color
	 * @param container 
	 * @return damaged image
	 * @throws SlickException 
	 */
	public static Image damage(Shape damage, Image destroy, Color color, GameContainer container) throws SlickException {	
		return damage(damage, new Origin(0,0, false), 1, destroy, color, container);
	}

	/**
	 * Drill a hole in the image with a shape, given a color with which to drill,and a security step
	 * 
	 * @param damage
	 * @param coord
	 *            of the image
	 * @param step 
	 * @param destroy
	 * @param color
	 * @param container 
	 * @return damaged image
	 * @throws SlickException 
	 */
	public static Image damage(Shape damage, ReferencedCoordinate coord,int step, Image destroy, Color color, GameContainer container) throws SlickException {

		damage.setCenterX(MathU.roundTo(damage.getCenterX()- coord.x(),step));
		damage.setCenterY(MathU.roundTo(damage.getCenterY() - coord.y(),step));
		
		Graphics g = container.getGraphics();
		g.clear();
		g.setAntiAlias(true);
		destroy.draw();
		g.setDrawMode(Graphics.MODE_ALPHA_BLEND);
		g.setColor(color);
		g.fill(damage);
		g.copyArea(destroy, 0, 0);
		g.setDrawMode(Graphics.MODE_NORMAL);
		g.clear();
		return destroy;
	}
	
}

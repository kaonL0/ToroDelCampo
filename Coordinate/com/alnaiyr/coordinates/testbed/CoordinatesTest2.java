package com.alnaiyr.coordinates.testbed;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

import com.alnaiyr.coordinates.dynamic.Cartesian;
import com.alnaiyr.coordinates.dynamic.Origin;
import com.alnaiyr.coordinates.dynamic.Polar;
import com.alnaiyr.utilities.example.Example;

/**
 * this exemple demonstrate the power of the plan coordinates ;)
 * 
 * @author IgoЯ
 * @version 0.1
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>Change</em></li>
 *          </ul>
 */

public class CoordinatesTest2 extends Example {

	private Polar coord=new Polar(200,0,new Origin(400,300, false));
	
	private Cartesian local=new Cartesian(0,-100,coord, false);
	
	private Rectangle rec= new Rectangle(local.x(),local.y(),100,100);

	/* *****************
	 * 
	 * Constructor
	 * 
	 * ********************** */

	/**
	 * @param title
	 */
	public CoordinatesTest2(String title) {
		super(title);
		local.setAngle((float) (-Math.PI/2));
	}

	/* *****************
	 * 
	 * Slick
	 * 
	 * ********************** */

	@Override
	public void renderIt(GameContainer container, Graphics g) throws SlickException {
		g.draw(rec);
		g.draw(new Circle(400,300,200));
		g.setColor(Color.blue);
		g.draw(new Rectangle(coord.x(),coord.y(),100,100));
		g.setColor(Color.white);
	}

	@Override
	public void initialize(GameContainer container) throws SlickException {
		
	}

	@Override
	public void updateIt(GameContainer container, int delta) throws SlickException {

		coord.addLocalY(.001f*delta);
		local.setAngle(local.angle()+0.01f);
		rec.setLocation(local.x(),local.y());

	}

	/**
	 * @param argv
	 */
	public static void main(String[] argv) {

		try {
			AppGameContainer container = new AppGameContainer(new CoordinatesTest2("Plan Coordinate test"));
			container.setDisplayMode(800, 600, false);
			container.setSmoothDeltas(true);
			container.setVSync(true);
			container.setTargetFrameRate(60);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}
}

package com.alnaiyr.physics.movement.testbed;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

import com.alnaiyr.coordinates.dynamic.ReferencedCoordinate;
import com.alnaiyr.coordinates.dynamic.Origin;
import com.alnaiyr.coordinates.dynamic.Polar;
import com.alnaiyr.physics.movement.OrbitalMovement;
import com.alnaiyr.utilities.example.Example;


/**
 * This exemple demonstrate the power of the plan coordinates ;)
 * 
 * @author IgoЯ
 * @version 0.1
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>Change</em></li>
 *          </ul>
 */

public class OrbitalMovementTest extends Example {

	private OrbitalMovement oMovement;
	private Circle o;
	
	private ReferencedCoordinate coord;
	
	/* *****************
	 * 
	 * Constructor
	 * 
	 * ********************** */

	public OrbitalMovementTest(String title) {
		super(title);
	}

	/* *****************
	 * 
	 * Slick
	 * 
	 * ********************** */

	@Override
	public void renderIt(GameContainer container, Graphics g) throws SlickException {
		g.draw(o);
		g.draw(new Circle(400,300,20));
		g.setColor(Color.blue);
		g.draw(new Rectangle(400,300,50,50));
		g.setColor(Color.red);
		g.draw(new Rectangle(400,300,oMovement.getPeriapsis(),2));
		g.setColor(Color.green);
		g.draw(new Rectangle(400,300,-oMovement.getApoapsis(),2));
		g.setColor(Color.white);
		g.drawString("Press space to raise excentricity, ctr to lower it (a security is set)", 50, 50);
	}

	@Override
	public void initialize(GameContainer container) throws SlickException {
		coord= new Polar(500,0,new Origin(400,300, false));
		o= new Circle(coord.x(),coord.y(),10);
		oMovement= new OrbitalMovement(100, .6f, 50f,50);
	}

	@Override
	public void updateIt(GameContainer container, int delta) throws SlickException {

		oMovement.update((Polar) coord, delta, true);
		o.setCenterX(coord.x());
		o.setCenterY(coord.y());
		
		if (container.getInput().isKeyDown(Input.KEY_SPACE)) {
			oMovement.setEcentricity(oMovement.getExcentricity()+.0005f*delta);
		}
		if (container.getInput().isKeyDown(Input.KEY_LCONTROL)) {
			oMovement.setEcentricity(oMovement.getExcentricity()-.0005f*delta);
		}

	}

	public static void main(String[] argv) {

		try {
			AppGameContainer container = new AppGameContainer(new OrbitalMovementTest("Orbital Movement test"));
			container.setDisplayMode(800, 600, false);
			container.start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

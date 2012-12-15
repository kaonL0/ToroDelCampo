package com.alnaiyr.coordinates.testbed;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.geom.Transform;

import com.alnaiyr.coordinates.dynamic.Cartesian;
import com.alnaiyr.coordinates.dynamic.ReferencedCoordinate;
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

public class CoordinatesTest extends Example {

	/* *****************
	 * 
	 * Internal Classes
	 * 
	 * ********************** */

	private class Wheel {

		public Cartesian	coord;
		public Circle		wheel;

		public Wheel(float x, float y, float radius, ReferencedCoordinate origin) {
			coord = new Cartesian(x, y, origin, false);
			wheel = new Circle(coord.x(), coord.y(), radius);
		}

	}

	private class Car {
		public Cartesian	coord;
		public Rectangle	car;

		public Car(float x, float y, float w, float h, ReferencedCoordinate origin) {
			coord = new Cartesian(x, y, origin, false);
			car = new Rectangle(coord.x(), coord.y(), w, h);
		}
	}

	private class Trim {
		public Polar			coord;
		public RoundedRectangle	trim;

		public Trim(float x, float y, float w, float h, ReferencedCoordinate origin) {
			coord = new Polar(x, y, origin);
			trim = new RoundedRectangle(x - w / 2, y - h / 2, w, h, 4);
		}
	}

	/* *****************
	 * 
	 * Variables
	 * 
	 * ********************** */

	Car		car;
	Wheel	frontWheel;
	Wheel	rearWheel;
	Trim	frontTrim;
	Trim	rearTrim;

	/* *****************
	 * 
	 * Constructor
	 * 
	 * ********************** */

	/**
	 * @param title
	 */
	public CoordinatesTest(String title) {
		super(title);
	}

	/* *****************
	 * 
	 * Slick
	 * 
	 * ********************** */

	@Override
	public void renderIt(GameContainer container, Graphics g) throws SlickException {
		g.draw(frontWheel.wheel);
		g.draw(car.car);
		g.draw(rearWheel.wheel);
		g.draw(frontTrim.trim.transform(Transform.createRotateTransform(frontTrim.coord.getTheta(), frontTrim.coord.x(), frontTrim.coord.y())));
		g.draw(rearTrim.trim.transform(Transform.createRotateTransform(rearTrim.coord.getTheta(), rearTrim.coord.x(), rearTrim.coord.y())));
		g.drawLine(0, 350, 800, 350);
	}

	@Override
	public void initialize(GameContainer container) throws SlickException {
		car = new Car(0, 100, 200, 100, new Origin(0, 100, false));
		frontWheel = new Wheel(0, 100, 50, car.coord);
		rearWheel = new Wheel(200, 100, 50, car.coord);
		frontTrim = new Trim(0, 0, 75, 75, frontWheel.coord);
		rearTrim = new Trim(0, 0, 75, 75, rearWheel.coord);
	}

	@Override
	public void updateIt(GameContainer container, int delta) throws SlickException {

		car.coord.setXl(car.coord.getXL() + .1f * delta);
		if (car.coord.getXL() >= container.getWidth())
			car.coord.setXl(0);

		frontTrim.coord.setTheta(frontTrim.coord.getTheta() + 0.001f * delta);
		rearTrim.coord.setTheta(rearTrim.coord.getTheta() + 0.001f * delta);

		car.car.setLocation(car.coord.x(), car.coord.y());
		frontWheel.wheel.setCenterX(frontWheel.coord.x());
		frontWheel.wheel.setCenterY(frontWheel.coord.y());
		rearWheel.wheel.setCenterX(rearWheel.coord.x());
		rearWheel.wheel.setCenterY(rearWheel.coord.y());
		frontTrim.trim.setCenterX(frontTrim.coord.x());
		frontTrim.trim.setCenterY(frontTrim.coord.y());
		rearTrim.trim.setCenterX(rearTrim.coord.x());
		rearTrim.trim.setCenterY(rearTrim.coord.y());

		if (container.getInput().isKeyDown(Input.KEY_ESCAPE))
			container.exit();
		if (container.getInput().isKeyDown(Input.KEY_F1)) {
			container.reinit();
		}

	}

	/**
	 * @param argv
	 */
	public static void main(String[] argv) {

		try {
			AppGameContainer container = new AppGameContainer(new CoordinatesTest("Plan Coordinate test"));
			container.setDisplayMode(800, 600, false);
			container.start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

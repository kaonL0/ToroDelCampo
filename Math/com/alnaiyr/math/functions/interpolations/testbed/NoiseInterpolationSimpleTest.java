package com.alnaiyr.math.functions.interpolations.testbed;

import java.util.HashMap;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import com.alnaiyr.math.functions.interpolations.Interpolation;
import com.alnaiyr.math.functions.interpolations.NoiseUtilities;
import com.alnaiyr.utilities.example.Example;

/**
 * This intends to shows the most simple interpolation ever... Note: you can
 * interpolate dynamicly, to see it move...
 * 
 * @author IgoR
 * @version 1.0
 */
public class NoiseInterpolationSimpleTest extends Example {

    HashMap<Integer, Float> test = new HashMap<>();

    /**
     * @param title
     */
    public NoiseInterpolationSimpleTest(String title) {
	super(title);
    }

    @Override
    public void renderIt(GameContainer container, Graphics g)
	    throws SlickException {
	for (Integer key : test.keySet())
	    g.draw(new Rectangle(key, test.get(key), 5, 5));
    }

    @Override
    public void initialize(GameContainer container) throws SlickException {
	test.put(200, 400f);
	test.put(400, 300f);
	test = NoiseUtilities.interpolate(test, Interpolation.COSINUS);
    }

    @Override
    public void updateIt(GameContainer container, int delta)
	    throws SlickException {
	if (container.getInput().isKeyDown(Input.KEY_ESCAPE))
	    container.exit();
	if (container.getInput().isKeyDown(Input.KEY_F1)) {
	    container.reinit();
	}

    }

    /** @param args */
    public static void main(String[] args) {

	AppGameContainer app;
	try {
	    app = new AppGameContainer(new NoiseInterpolationSimpleTest(
		    "Interpolation, simple."));
	    app.setDisplayMode(800, 600, false);
	    app.setVSync(true);
	    app.setTargetFrameRate(60);
	    app.setShowFPS(true);
	    app.start();
	} catch (SlickException e) {
	    e.printStackTrace();
	}

    }
}

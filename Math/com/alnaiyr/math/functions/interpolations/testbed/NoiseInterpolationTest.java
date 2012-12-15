package com.alnaiyr.math.functions.interpolations.testbed;

import java.util.HashMap;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import com.alnaiyr.generator.noise.NoiseGenerator;
import com.alnaiyr.math.functions.interpolations.Interpolation;
import com.alnaiyr.math.functions.interpolations.Smoothing;

/**
 * This exemple intends to show different kinds of interpolation using the
 * Perlin noise function
 * 
 * @author IgoR
 * @version 1.0
 */

public class NoiseInterpolationTest extends BasicGame {

    private HashMap<Integer, Float> neighint;

    private HashMap<Integer, Float> linint;

    private HashMap<Integer, Float> cosint;

    /**
     * @param title
     */
    public NoiseInterpolationTest(String title) {

	super(title);
	neighint = new HashMap<>();
	linint = new HashMap<>();
	cosint = new HashMap<>();
    }

    @Override
    public void render(GameContainer container, Graphics g)
	    throws SlickException {

	for (Integer key : neighint.keySet())
	    g.draw(new Rectangle(key, neighint.get(key), 2, 2));
	g.setColor(Color.red);
	for (Integer key : linint.keySet())
	    g.draw(new Rectangle(key, linint.get(key), 2, 2));
	g.setColor(Color.green);
	for (Integer key : cosint.keySet())
	    g.draw(new Rectangle(key, cosint.get(key), 2, 2));
	g.setColor(Color.white);
    }

    @Override
    public void init(GameContainer container) throws SlickException {

	neighint = NoiseGenerator.generatePerlin(50, 50, 50, 800, 5, 0.1f,
		0.5f, Smoothing.NULL, Interpolation.CONSTANT);
	linint = NoiseGenerator.generatePerlin(100, 100, 100, 800, 5, 0.1f,
		0.5f, Smoothing.NULL, Interpolation.LINEAR);
	cosint = NoiseGenerator.generatePerlin(200, 200, 200, 800, 5, 0.1f,
		0.5f, Smoothing.NULL, Interpolation.COSINUS);
    }

    @Override
    public void update(GameContainer container, int delta)
	    throws SlickException {

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
	    AppGameContainer container = new AppGameContainer(
		    new NoiseInterpolationTest("Noise test"));
	    container.setDisplayMode(800, 600, false);
	    container.start();
	} catch (SlickException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }
}

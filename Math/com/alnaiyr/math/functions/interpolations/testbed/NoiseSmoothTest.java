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
 * this test shows how works the smoothing procedures for a noise function
 * 
 * @author IgoR
 * @version 1.0
 */
public class NoiseSmoothTest extends BasicGame {

    private HashMap<Integer, Float> normal;
    private HashMap<Integer, Float> average;
    private HashMap<Integer, Float> gauss;
    private HashMap<Integer, Float> globalAverage;
    private HashMap<Integer, Float> movingAverage;

    /**
     * @param title
     */
    public NoiseSmoothTest(String title) {

	super(title);
	normal = new HashMap<>();
	average = new HashMap<>();
	gauss = new HashMap<>();
	globalAverage = new HashMap<>();
    }

    @Override
    public void render(GameContainer container, Graphics g)
	    throws SlickException {

	for (Integer key : normal.keySet())
	    g.draw(new Rectangle(key, normal.get(key), 2, 2));
	g.setColor(Color.red);
	for (Integer key : average.keySet())
	    g.draw(new Rectangle(key, average.get(key), 2, 2));
	g.setColor(Color.green);
	for (Integer key : gauss.keySet())
	    g.draw(new Rectangle(key, gauss.get(key), 2, 2));
	g.setColor(Color.blue);
	for (Integer key : globalAverage.keySet())
	    g.draw(new Rectangle(key, globalAverage.get(key), 2, 2));
	g.setColor(Color.white);
	g.setColor(Color.orange);
	for (Integer key : movingAverage.keySet())
	    g.draw(new Rectangle(key, movingAverage.get(key), 2, 2));
	g.setColor(Color.white);
    }

    @Override
    public void init(GameContainer container) throws SlickException {
	normal = NoiseGenerator.generatePerlin(200, 200, 200, 800, 5, 0.1f,
		0.5f, Smoothing.NULL, Interpolation.COSINUS);
	average = NoiseGenerator.generatePerlin(200, 200, 200, 800, 5, 0.1f,
		0.5f, Smoothing.LOCAL, Interpolation.COSINUS);
	gauss = NoiseGenerator.generatePerlin(200, 200, 200, 800, 5, 0.1f,
		0.5f, Smoothing.GAUSS, Interpolation.COSINUS);
	globalAverage = NoiseGenerator.generatePerlin(200, 200, 200, 800, 5,
		0.1f, 0.5f, Smoothing.GLOBAL, Interpolation.COSINUS);
	movingAverage = NoiseGenerator.generatePerlin(200, 200, 200, 800, 5,
		0.1f, 0.5f, Smoothing.MOVING, Interpolation.COSINUS);
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
		    new NoiseSmoothTest("Noise test"));
	    container.setDisplayMode(800, 600, false);
	    container.start();
	} catch (SlickException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }
}

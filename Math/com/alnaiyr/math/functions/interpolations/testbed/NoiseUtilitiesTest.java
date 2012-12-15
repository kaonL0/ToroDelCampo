package com.alnaiyr.math.functions.interpolations.testbed;

import java.util.HashMap;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import com.alnaiyr.generator.noise.NoiseGenerator;
import com.alnaiyr.math.functions.interpolations.Interpolation;
import com.alnaiyr.math.functions.interpolations.NoiseUtilities;
import com.alnaiyr.math.functions.interpolations.Smoothing;
import com.alnaiyr.utilities.example.Example;
import com.alnaiyr.utilities.images.FunctionRenderer;

/**
 * Tests all function of NoiseUtilities, check out console!
 * 
 * @author IgoR
 * @version 1.0
 */

public class NoiseUtilitiesTest extends Example {

    private float step;

    private float length;

    private float highest;

    private float lowest;

    private float average;

    private HashMap<Integer, Float> relief;
    private List<Vector2f> list;
    private List<Vector2f> list2;

    private Image img;

    private Rectangle highestr;

    private Rectangle lowestr;

    private Line lhighest;

    private Line llowest;

    private Line laverage;

    boolean one;

    /**
     * @param title
     */
    public NoiseUtilitiesTest(String title) {

	super(title);
	relief = new HashMap<>();
    }

    @Override
    public void renderIt(GameContainer container, Graphics g)
	    throws SlickException {
	img.draw();

	for (Vector2f vec : list) {
	    g.setColor(Color.green);
	    g.draw(new Rectangle(vec.x, vec.y, 5, 5));

	    if (one) {
		g.setColor(Color.green);
		g.drawString(Integer.toString(list.indexOf(vec)), vec.x + 5,
			vec.y + 5);
	    }
	}
	for (Vector2f vec : list2) {
	    g.setColor(Color.red);
	    g.draw(new Rectangle(vec.x, vec.y, 5, 5));

	    if (!one) {
		g.setColor(Color.red);
		g.drawString(Integer.toString(list2.indexOf(vec)), vec.x + 5,
			vec.y + 5);
	    }
	}

	g.drawString("highest point", highestr.getX() + 20,
		highestr.getY() - 20);
	g.draw(highestr);
	g.draw(lhighest);
	g.setColor(Color.blue);
	g.drawString("lowest point", lowestr.getX(), lowestr.getY() + 50);
	g.draw(lowestr);
	g.draw(llowest);
	g.setColor(Color.green);
	g.draw(laverage);
	g.setColor(Color.white);

    }

    @Override
    public void initialize(GameContainer container) throws SlickException {

	relief = NoiseGenerator.generatePerlin(300, 0, 400, 800, 5, 0.2f, 0.4f,
		Smoothing.NULL, Interpolation.COSINUS);
	list = NoiseUtilities.convert(relief, 40, false);
	list2 = NoiseUtilities.correctList(list, 40);

	step = NoiseUtilities.findStep(relief);
	length = NoiseUtilities.findLength(relief);
	highest = NoiseUtilities.findHighestPoint(relief).y;
	lowest = NoiseUtilities.findLowestPoint(relief).y;
	average = NoiseUtilities.findAverage(relief);

	img = FunctionRenderer.generateImage(relief, 200, container);

	highestr = new Rectangle(NoiseUtilities.findHighestPoint(relief).x,
		NoiseUtilities.findHighestPoint(relief).y, 20, 20);
	lhighest = new Line(0, NoiseUtilities.findHighestPoint(relief).y, 800,
		NoiseUtilities.findHighestPoint(relief).y);
	lowestr = new Rectangle(NoiseUtilities.findLowestPoint(relief).x,
		NoiseUtilities.findLowestPoint(relief).y, 20, 20);
	llowest = new Line(0, NoiseUtilities.findLowestPoint(relief).y, 800,
		NoiseUtilities.findLowestPoint(relief).y);
	laverage = new Line(0, average, 800, average);

	System.out.println("step: " + step);
	System.out.println("length: " + length);
	System.out.println("highest: " + highest);
	System.out.println("lowest: " + lowest);
	System.out.println("Average: " + lowest);
    }

    @Override
    public void updateIt(GameContainer container, int delta)
	    throws SlickException {

	if (container.getInput().isKeyDown(Input.KEY_ESCAPE))
	    container.exit();
	if (container.getInput().isKeyDown(Input.KEY_F1)) {
	    container.reinit();
	}
	if (container.getInput().isKeyPressed(Input.KEY_Z)) {
	    one = !one;
	}

    }

    /**
     * @param argv
     */
    public static void main(String[] argv) {

	try {
	    AppGameContainer container = new AppGameContainer(
		    new NoiseUtilitiesTest("Noise test"));
	    container.setDisplayMode(800, 600, false);
	    container.start();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}

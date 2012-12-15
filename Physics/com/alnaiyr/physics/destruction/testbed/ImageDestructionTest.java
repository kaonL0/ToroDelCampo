package com.alnaiyr.physics.destruction.testbed;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

import com.alnaiyr.generator.noise.NoiseGenerator;
import com.alnaiyr.math.functions.interpolations.Interpolation;
import com.alnaiyr.math.functions.interpolations.Smoothing;
import com.alnaiyr.utilities.example.Example;
import com.alnaiyr.utilities.images.FunctionRenderer;
import com.alnaiyr.utilities.images.ImageDigger;

/** This exemple shows a very simlpe way to make destructible terrain with alpha maps
 * 
 * @author IgoЯ
 * @version 1.0
 * <p><Strong>Version Change:</Strong>
 *		<ul>
 *			<li><em>Works</em></li>
 *		</ul>
 */
public class ImageDestructionTest extends Example{

	private ArrayList<Shape> damages= new ArrayList<Shape>();
	
	HashMap<Integer, Float> relief ;
	private Image test;
	
	public ImageDestructionTest(String title) {
		super(title);
		relief = new HashMap<Integer, Float>();
	}

	@Override
	public void renderIt(GameContainer container, Graphics g) throws SlickException {
		test.draw();
	}

	@Override
	public void initialize(GameContainer container) throws SlickException{		
		relief = NoiseGenerator.generatePerlin(600,400,100, 800, 2, 0.1f, 0.2f, Smoothing.NULL,Interpolation.COSINUS);
			if(test!=null) try {
				test.destroy();
			}
			catch (SlickException e) {
				e.printStackTrace();
			} //to avoid any stack overflow
			test=FunctionRenderer.generateImage(relief,50,container);	
	}

	@Override
	public void updateIt(GameContainer container, int delta) throws SlickException{
		
		if (container.getInput().isMouseButtonDown(0)) {
		
				ImageDigger.damage((new Circle(container.getInput().getMouseX(),container.getInput().getMouseY(),20)),test, Color.black, container);
			
		}
		for(int i=0;i<damages.size();i++){
			//test=alpham.damage(damages.get(i),test);
			damages.remove(i);
		}
	}
	/** @param args */
	public static void main(String[] args) {

		AppGameContainer app;
		try {
			app = new AppGameContainer(new ImageDestructionTest("Destruction Test"));
			app.setDisplayMode(800, 600, false);
			app.setVSync(true);
			app.setShowFPS(true);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}
}

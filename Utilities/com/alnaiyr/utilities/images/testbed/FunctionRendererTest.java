package com.alnaiyr.utilities.images.testbed;

import java.util.HashMap;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.alnaiyr.generator.noise.NoiseGenerator;
import com.alnaiyr.math.functions.interpolations.Interpolation;
import com.alnaiyr.math.functions.interpolations.Smoothing;
import com.alnaiyr.utilities.images.FunctionRenderer;

/**
 * @author IgoЯ
 * @version 1.0
 * <p><Strong>Version Change:</Strong>
 *		<ul>
 *			<li><em>No Changes</em></li>
 *		</ul>
 */
public class FunctionRendererTest extends BasicGame{
	
	HashMap<Integer, Float> relief ;
	private Image test;
	
	/**
	 * @param title
	 */
	public FunctionRendererTest(String title) {
		super(title);
		relief = new HashMap<Integer,Float>();
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		test.draw();
	}

	@Override
	public void init(GameContainer container) throws SlickException {		
		relief = NoiseGenerator.generatePerlin(400,400,100, 800, 8, 0.1f, 0.2f,Smoothing.NULL,Interpolation.LINEAR);
			if(test!=null)test.destroy(); //to avoid any stack overflow
			
			float time= System.nanoTime();
				test=FunctionRenderer.generateImage(relief,100,container);				
				
		
			time=System.nanoTime()-time;
			System.out.print(time);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		
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
			app = new AppGameContainer(new FunctionRendererTest("Image buffer curve test"));
			app.setDisplayMode(800, 600, false);
			app.setVSync(true);
			app.setTargetFrameRate(60);
			app.setShowFPS(true);
			app.start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

package com.alnaiyr.general;

import org.jbox2d.common.Vec2;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

import com.alnaiyr.commands.Commands;
import com.alnaiyr.commands.DebugCommand;
import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.math.numbers.BooleanStorer;
import com.alnaiyr.utilities.debug.DB;

/**
 * All internals variables of the game
 * 
 * @author IgoЯ
 * @version 1.4
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>Works</em></li>
 *          <li><em>Cleaned up</em></li>
 *          <li>
 *          <em>Split in 3 classes, to separate debugging, external values and internal values</em>
 *          </li>
 *          </ul>
 */

public final class IV {

	/* **********************************************
	 * 
	 * Static Variables
	 * 
	 * ************************************************
	 */

	/** the container we are in **/
	public static AppGameContainer	container;

	/** The game container */
	public static StateBasedGame	game;

	public static Graphics			g;

	/** used to switch to a general pause, meaning no update **/
	public static boolean			is_paused		= false;

	/** Virtual size of the screen */
	public static int				vWidth			= 1920;

	/** Virtual size of the screen */
	public static int				vHeight			= 1080;

	/** Center of the screen */
	public static final PlanVector	center			= new Vector2f(
															IV.vWidth / 2,
															IV.vHeight / 2);

	public static final PlanVector	endScreen		= new Vec2(IV.vWidth,
															IV.vHeight);

	/**
	 * Scaling to do to the final screen/ must be calculated with recalculate()
	 * before use
	 */
	public static float				wScale			= 0;
	/**
	 * Scaling to do to the final screen/ must be calculated with recalculate()
	 * before use
	 */
	public static float				hScale			= 0;

	/**
	 * value of current delta of update call.
	 */

	public static int				delta			= 1;

	/**
	 * how many pixels should correspong to one in game unit (somehow meter, 25
	 * pixels per meter is the jBox2D recommendation)
	 */
	public static int				pixelProportion	= 1;

	public static BooleanStorer		activator;

	/* **********************************************
	 * 
	 * Methods
	 * 
	 * ************************************************
	 */

	public static PlanVector getEndVector() {
		return new Vector2f(IV.getWidth(), IV.getHeight());
	}

	/**
	 * Recalculates all scaling factors
	 * 
	 */
	public static void recalculate() {
		IV.wScale = (float) EV.getWidth() / IV.vWidth / pixelProportion;
		IV.hScale = (float) EV.getHeight() / IV.vHeight / pixelProportion;
		if (IV.container != null)
			IV.container.getGraphics().resetTransform();
	}

	/**
	 * 
	 * @return virtual width
	 */
	public static int getWidth() {
		return IV.vWidth;
	}

	/**
	 * 
	 * @return virtual height
	 */
	public static int getHeight() {
		return IV.vHeight;
	}

	/**
	 * Updates delta , and checks for global input, such as reset, or exit. <br>
	 * Limit {@link #delta delta} to a certain amount, to avoid huge freezing to
	 * be amplified
	 * 
	 * 
	 * @param delta
	 */
	public static void update(int delta) {

		if (delta > 100)
			delta = 100;

		// special key handling
		IV.delta = delta;

		if (Commands.isPressed(DebugCommand.RESTART)) {

			try {
				IV.container.reinit();
			} catch (final SlickException e) {
				e.printStackTrace();
			}
		}
		if (Commands.isPressed(DebugCommand.MODE1)) {
			try {
				IV.game.getCurrentState().init(IV.container, IV.game);
			} catch (final SlickException e) {
				e.printStackTrace();
			} catch (final NullPointerException e) {
				Log.error("no state found!");
			}
		}

		if (Commands.isPressed(DebugCommand.EXIT))
			IV.container.exit();

		if (Commands.isPressed(DebugCommand.ORIGIN))
			DB.getOrigins = !DB.getOrigins;

	}

	/**
	 * Renders the center for all global coordinates
	 * 
	 * WARNING: Must always be called first
	 * 
	 * @param container
	 * @param g
	 */
	public static void render(final GameContainer container, final Graphics g) {
		g.scale(IV.wScale, IV.hScale);
		g.translate(EV.getWidth() / 2 / IV.wScale - center.x(), EV.getHeight()
				/ 2 / IV.hScale - center.y());
	}

}

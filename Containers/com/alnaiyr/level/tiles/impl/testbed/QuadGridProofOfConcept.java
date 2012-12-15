/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.level.tiles.impl.testbed;

import org.jbox2d.common.Vec2;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.alnaiyr.level.tiles.QuadZoned;
import com.alnaiyr.level.tiles.grid.QuadGrid;
import com.alnaiyr.level.tiles.grid.QuadGridImpl;
import com.alnaiyr.level.tiles.grid.QuadPositions;
import com.alnaiyr.level.tiles.impl.Quad;
import com.alnaiyr.level.tiles.impl.QuadWindow;
import com.alnaiyr.utilities.debug.DB;
import com.alnaiyr.utilities.example.Example;

/**
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class QuadGridProofOfConcept extends Example {

	/*---------------------
	 * 
	 * Variables
	 * 
	 *----------------------*/

	public QuadGridImpl<Integer> grid;

	public QuadWindow<Integer> window;

	public Vec2 coord = new Vec2();
	public QuadZoned<Integer> temp;

	public Vec2 winCoord = new Vec2();

	/*---------------------
	 * 
	 * Constructor
	 * 
	 *----------------------*/

	public QuadGridProofOfConcept(String title) {
		super(title);

		Quad<Integer> quad = new Quad<>();

		grid = new QuadGridImpl<>(quad, 46, 25);

		QuadZoned<Integer> temp = quad;

		window = new QuadWindow<>(grid, 10);

		this.temp = grid.getTopLeftCorner();

		int i = 35;
		while (i != 0) {
			// DB.test("\n\n\n\n\n\n\n\nChecking grid @" + i);
			// checkGrid(grid);

			populateQuad(temp, i);
			temp.putAndUpdateNeighbor(QuadPositions.DOWNRIGHT,
					new Quad<Integer>());

			temp = temp.getNeighbor(QuadPositions.DOWNRIGHT);

			i--;
		}

	}

	private void populateQuad(QuadZoned<Integer> quad, int i) {

		bindNeighbour(QuadPositions.RIGHT, quad, i);
		bindNeighbour(QuadPositions.DOWN, quad, i);
	}

	public void bindNeighbour(QuadPositions pos, QuadZoned<Integer> quad,
			int number) {

		QuadZoned<Integer> tempZone = quad;

		while (number > 0) {
			QuadZoned<Integer> toAdd = new Quad<>();

			tempZone.putAndUpdateNeighbor(pos, toAdd);

			tempZone = toAdd;

			number--;
		}
	}

	public void checkNeighbor(QuadZoned<Integer> tempZone) {

		DB.test("\nZONE:", tempZone.toString());
		DB.test("\nNeigbhors:");
		DB.test("DOWN", tempZone.getNeighbor(QuadPositions.DOWN));
		DB.test("UP", tempZone.getNeighbor(QuadPositions.UP));
		DB.test("LEFT", tempZone.getNeighbor(QuadPositions.LEFT));
		DB.test("RIGHT", tempZone.getNeighbor(QuadPositions.RIGHT));
		DB.test("DOWNRIGHT", tempZone.getNeighbor(QuadPositions.DOWNRIGHT));
		DB.test("DOWNLEFT", tempZone.getNeighbor(QuadPositions.DOWNLEFT));
		DB.test("UPLEFT", tempZone.getNeighbor(QuadPositions.LEFTUP));
		DB.test("UPRIGHT", tempZone.getNeighbor(QuadPositions.UPRIGHT));

		if (tempZone.hasNeighbor(QuadPositions.RIGHT))
			checkNeighbor(tempZone.getNeighbor(QuadPositions.RIGHT));
	}

	public void checkGrid(QuadGrid<Integer> grid) {

		checkNeighbor(grid.getTopLeftCorner());
		QuadZoned<Integer> corner = grid.getTopLeftCorner();
		do {
			checkNeighbor(corner);
			corner = corner.getNeighbor(QuadPositions.DOWNRIGHT);
		} while (corner.hasNeighbor(QuadPositions.DOWNRIGHT));

	}

	/*---------------------
	 * 
	 * Methods
	 * 
	 *----------------------*/

	@Override
	public void initialize(GameContainer container) throws SlickException {
	}

	@Override
	public void updateIt(GameContainer container, int delta)
			throws SlickException {

		winCoord.set(container.getInput().getMouseX(), container.getInput()
				.getMouseY());

		temp = grid.getZone(temp, new Vec2(
				-temp.x() * grid.width() + coord.x(), -temp.y() * grid.height()
						+ coord.y()));

		if (container.getInput().isKeyDown(Input.KEY_Q)) {
			winCoord.addLocal(-0.1f * delta, 0);
		}

		if (container.getInput().isKeyDown(Input.KEY_D)) {
			winCoord.addLocal(0.1f * delta, 0);
		}

		if (container.getInput().isKeyDown(Input.KEY_Z)) {
			winCoord.addLocal(0, -0.1f * delta);
		}

		if (container.getInput().isKeyDown(Input.KEY_S)) {
			winCoord.addLocal(0, 0.1f * delta);
		}

		window.moveTo(winCoord);

		// DB.test(winCoord);

		// DB.test(window.getCorner());

	}

	@Override
	public void renderIt(GameContainer container, Graphics g)
			throws SlickException {

		// qzgrid.debug(container, g, true);
		window.debug(container, g, true);
		try {
			Vec2 tempCoord = (Vec2) grid.getZone(temp);
			// DB.test("temp Coord", tempCoord);
			g.setColor(Color.green);
			g.drawRect(tempCoord.x, tempCoord.y, grid.width(), grid.height());
			g.setColor(Color.white);

		} catch (NullPointerException e) {
		}
	}

	/*---------------------
	 * 
	 * Getters / Setters
	 * 
	 *----------------------*/

	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(
					new QuadGridProofOfConcept("quad grid"));
			app.setDisplayMode(1650, 900, false);
			app.setAlwaysRender(true);
			app.start();
		} catch (SlickException e) {

			e.printStackTrace();
		}
	}
}

package com.alnaiyr.utilities.debug;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.RoundedRectangle;

import com.alnaiyr.general.IV;
import com.alnaiyr.utilities.list.CircularLinkedList;

/**
 * A class containing different kinds of loading and testing methods
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class DB {

	public static boolean getOrigins = false;

	/**
	 * some entertaining thing for long loading time and debugging!
	 * 
	 * @param step
	 *            The current number of step already loaded.
	 * @param total
	 *            The number of steps required to entirely load it.
	 */

	public static void loading(float step, float total) {

		System.out.println("Loading " + step + " step of " + total + " ("
				+ step / total * 100 + "% )...");
		if (step / total * 100 >= 100) {
			System.out.println("Done! ");
		}
	}

	/**
	 * 
	 * @param what
	 *            is to load, when there is no specific steps
	 */
	public static void loading(String what) {

		System.out.println("Loading " + what + " : x% left...");
	}

	/**
	 * 
	 * @param what
	 * @param step
	 * @param total
	 */

	public static void loading(String what, float step, float total) {

		System.out.println("Loading " + what);
		DB.loading(step, total);
	}

	/**
	 * Test something, its a faster-to-type System.out.print ! <br>
	 * Also iterate a number, to see who many time we've passed a point
	 */

	public static void test() {
		System.out.println("Activated " + DB.it + " time");
		DB.it++;
		if (DB.getOrigins) {
			try {
				throw new GetCallException();
			} catch (GetCallException e) {
			}
		}
	}

	/**
	 * 
	 * @param point
	 */
	public static void test(byte[] point) {
		System.out.println("x: " + point[0] + " y: " + point[1]);
		if (DB.getOrigins) {
			try {
				throw new GetCallException();
			} catch (GetCallException e) {
			}
		}
	}

	/**
	 * 
	 * @param point
	 */
	public static void test(int[] point) {
		System.out.println("x: " + point[0] + " y: " + point[1]);
		if (DB.getOrigins) {
			try {
				throw new GetCallException();
			} catch (GetCallException e) {
			}
		}
	}

	/**
	 * Prints a test, that's all!
	 * 
	 * @param test
	 */
	public static void test(Object test) {

		try {
			System.out.println(test);
		} catch (NullPointerException e) {
			System.out.print("not defined yet!");
			e.printStackTrace();
		}
		if (DB.getOrigins) {
			try {
				throw new GetCallException();
			} catch (GetCallException e) {
			}
		}
	}

	/**
	 * Prints multiple tests, when there's a need for a lot of informations!
	 * 
	 * @param test
	 *            All the tests.
	 */
	public static void test(Object... test) {

		try {
			String print = "";
			for (Object element : test)
				print += "  " + element.toString();
			System.out.println(print);
		} catch (NullPointerException e) {
			System.out.print("not defined yet!");
			e.printStackTrace();
		}
		if (DB.getOrigins) {
			try {
				throw new GetCallException();
			} catch (GetCallException e) {
			}
		}
	}

	/**
	 * Shows some debug information on screen
	 * 
	 * @param name
	 * @param to_debug
	 */
	public static void debug(String name, String to_debug) {

		DB.debug.add(name + "\n" + to_debug);
	}

	/**
     * 
     */
	public static void testClear() {
		DB.test("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}

	/**
     * 
     */
	public static void testReInit() {
		DB.it = 1;
	}

	/**
	 * Renders all the debug informations, on top of everything drawn.
	 * 
	 * Can use specific debug texts using
	 * 
	 * @param g
	 */
	public static void debugMode1(Graphics g) {

		g.resetTransform();
		RoundedRectangle r = new RoundedRectangle(1, 1,
				IV.container.getWidth() - 1, 50, 20);
		Color c = Color.lightGray;
		c.a = 0.8f;
		g.setColor(c);
		g.fill(r);
		g.draw(r);
		g.setColor(Color.white);
		IV.container.setShowFPS(true);
		/*
		 * switch (debugMode) {
		 * 
		 * case VOID: break; case MODE1: g.drawString("Zoom: " +
		 * mainCamera.getZoom(), 500, 50); g.drawString("Local Mouse:\n x: " +
		 * (container.getInput().getMouseX() / getSX() - container.getWidth() /
		 * 2 / getSX()) + " y: " + (container.getInput().getMouseY() / getSY() -
		 * container.getHeight() / 2 / getSY()), 500, 100); break; case MODE2:
		 * g.drawString(debug.get(0), 10, 20); case MODE3:
		 * g.drawString(debug.get(1), 10, 20); } debug.clear();
		 */

	}

	/**
	 * an iterator for a debug function: <br>
	 **/
	public static int it = 1;

	/**
	 * if a class need to show something, it should be added in this HashMap,
	 */
	public static CircularLinkedList<String> debug = new CircularLinkedList<>();

}

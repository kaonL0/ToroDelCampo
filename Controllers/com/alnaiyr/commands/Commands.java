package com.alnaiyr.commands;

import javax.naming.directory.InvalidAttributesException;

import org.jbox2d.common.Vec2;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.general.IV;

/**
 * This class checks commands. doesn't take into account the fact that the
 * checking has already be done ( will remain true, even if called)
 * 
 * @author IgoЯ
 * @version 1.2
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>Getters, setters, all methods</em></li>
 *          <li><em>Static now</em></li>
 *          <li><em>added try/catch safe</em></li>
 *          <li><em>added delegation methods</em></li>
 *          </ul>
 */
public class Commands {

	public static Input input;

	/* ***********************
	 * 
	 * Constructor
	 * 
	 * **************************
	 */

	/**
	 * Creates a new Controller, knowing what kind of thing to control
	 * 
	 * @param container
	 * @throws InvalidAttributesException
	 */
	public static void configure(GameContainer container)
			throws InvalidAttributesException {
		if (Commands.input == null)
			Commands.input = container.getInput();
		if (IV.wScale != 0 && IV.hScale != 0)
			Commands.input.setScale(1 / IV.wScale, 1 / IV.hScale);
		else
			throw new InvalidAttributesException("Scale is not right!");
	}

	/* ***********************
	 * 
	 * Methods
	 * 
	 * **************************
	 */

	/**
	 * Returns true if the command is pressed, and if the right command type is
	 * selected
	 * 
	 * @param commandable
	 * @return true if command is pressed
	 */
	public static boolean isPressed(Commandable commandable) {
		try {
			Commands.input.disableKeyRepeat();
			boolean b = commandable.getType().isActivated() ? Commands.input
					.isKeyPressed(commandable.getCommand()) : false;
			Commands.input.enableKeyRepeat();

			return b;
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.err.println(" Controller haven't been initialized");
			return false;
		}

	}

	/**
	 * Returns true if the command is down, and if the right command type is
	 * selected
	 * 
	 * @param commandable
	 * @return true if command is now down
	 */
	public static boolean isDown(Commandable commandable) {
		try {
			return commandable.getType().isActivated() ? Commands.input
					.isKeyDown(commandable.getCommand()) : false;
		} catch (NullPointerException e) {
			System.err.print(" Controller haven't been initialized");
			return false;
		}
	}

	/**
	 * Returns true if the command is pressed, and if the right command type is
	 * selected, on two alternative controls
	 * 
	 * @param commandable
	 * @param alt
	 * @return true if command is pressed, or alt command
	 */
	public static boolean isPressed(Commandable commandable, Commandable alt) {
		return Commands.isPressed(commandable) || Commands.isPressed(alt);
	}

	/**
	 * Returns true if the command is down, and if the right command type is
	 * selected, on two alternative controls
	 * 
	 * @param commandable
	 * @param alt
	 * @return true if command or alt command down
	 */
	public static boolean isDown(Commandable commandable, Commandable alt) {
		return Commands.isDown(commandable) || Commands.isDown(alt);
	}

	public static PlanVector getMousePosition() {
		return new Vec2(Commands.input.getMouseX(), Commands.input.getMouseY());
	}

	/* ********************************
	 * 
	 * Delegate
	 * 
	 * ***********************************
	 */

	/**
	 * Activates a command type
	 * 
	 * @param type
	 */
	public static void activate(CommandType type) {
		type.setActivated(true);
	}

	/**
	 * @param button
	 * @return true if mouse button pressed
	 */
	public static boolean isMousePressed(int button) {
		return Commands.input.isMousePressed(button);
	}

	/**
	 * @param button
	 * @return true if mouse button down
	 */
	public static boolean isMouseButtonDown(int button) {
		return Commands.input.isMouseButtonDown(button);
	}

	/**
	 * Listens to a boolean, and follows its order of activation
	 * 
	 * @param bool
	 * @param type
	 */
	public static void listenActivation(boolean bool, CommandType type) {
		type.setActivated(bool);
	}

	/**
	 * Listens to a boolean, and follows its order of activation
	 * 
	 * @param commandable
	 * 
	 * @param type
	 */
	public static void listenActivation(Commandable commandable,
			CommandType type) {
		if (Commands.input.isKeyPressed(commandable.getCommand()))
			type.setActivated(!type.isActivated());
	}

}

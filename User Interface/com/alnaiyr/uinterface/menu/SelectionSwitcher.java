package com.alnaiyr.uinterface.menu;

import com.alnaiyr.ai.updater.Updatable;
import com.alnaiyr.commands.Commandable;
import com.alnaiyr.commands.Commands;
import com.alnaiyr.commands.MenuCommand;
import com.alnaiyr.math.numbers.profiles.percents.PercentU;

/**
 * Switches between pre chosen margin values
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class SelectionSwitcher implements Updatable {

	private int value;

	private int first;
	private int last;

	private Commandable commandPlus = MenuCommand.UP;
	private Commandable commandAltPlus = MenuCommand.ALTUP;

	private Commandable commandMinus = MenuCommand.DOWN;
	private Commandable commandAltMinus = MenuCommand.ALTDOWN;

	/**
	 * @param firstPos
	 * @param lastPos
	 * @param startPos
	 */
	public SelectionSwitcher(int firstPos, int lastPos, int startPos) {
		value = startPos;
		first = firstPos;
		last = lastPos;
	}

	@Override
	public void update(int delta, boolean condition) {
		if (condition) {
			if (Commands.isPressed(commandPlus, commandAltPlus)) {
				value--;

			}

			if (Commands.isPressed(commandMinus, commandAltMinus)) {
				value++;
			}

			value = PercentU.cyclePercent(first, last, value);
		}
	}

	public void inc() {
		value++;
	}

	public void dec() {
		value--;
	}

	/* ****************
	 * 
	 * Getters and Setters
	 * 
	 * *********************
	 */

	/**
	 * @param value
	 */
	public void setValue(int value) {
		if (value <= last || value >= first)
			this.value = value;
	}

	/**
	 * @return the commandPlus
	 */
	public Commandable getCommandPlus() {
		return commandPlus;
	}

	/**
	 * @return the commandAltPlus
	 */
	public Commandable getCommandAltPlus() {
		return commandAltPlus;
	}

	/**
	 * @return the commandMinus
	 */
	public Commandable getCommandMinus() {
		return commandMinus;
	}

	/**
	 * @return the commandAltMinus
	 */
	public Commandable getCommandAltMinus() {
		return commandAltMinus;
	}

	/**
	 * @param commandPlus
	 *            the commandPlus to set
	 */
	public void setCommandPlus(Commandable commandPlus) {
		this.commandPlus = commandPlus;
	}

	/**
	 * @param commandAltPlus
	 *            the commandAltPlus to set
	 */
	public void setCommandAltPlus(Commandable commandAltPlus) {
		this.commandAltPlus = commandAltPlus;
	}

	/**
	 * @param commandMinus
	 *            the commandMinus to set
	 */
	public void setCommandMinus(Commandable commandMinus) {
		this.commandMinus = commandMinus;
	}

	/**
	 * @param commandAltMinus
	 *            the commandAltMinus to set
	 */
	public void setCommandAltMinus(Commandable commandAltMinus) {
		this.commandAltMinus = commandAltMinus;
	}

	/**
	 * @return the first
	 */
	public int getFirst() {
		return first;
	}

	/**
	 * @return the last
	 */
	public int getLast() {
		return last;
	}

	/**
	 * @param first
	 *            the first to set
	 */
	public void setFirst(int first) {
		this.first = first;
	}

	/**
	 * @param last
	 *            the last to set
	 */
	public void setLast(int last) {
		this.last = last;
	}

	/**
	 * @return
	 * @see com.alnaiyr.utilities.math.numbers.impl.ByteStorer#getValue()
	 */
	public int getValue() {
		return value;
	}

}

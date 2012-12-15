package com.alnaiyr.commands;

import org.newdawn.slick.Input;

import com.alnaiyr.utilities.debug.DebugMode;

/**
 * Commands for debug
 * 
 * @author IgoЯ
 * @version 0.1
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>?</em></li>
 *          </ul>
 */
public enum DebugCommand implements Commandable {
	SWITCH(Input.KEY_TAB, DebugMode.MODE1), ENABLE(Input.KEY_F12,
			DebugMode.MODE1), ENABLEALL(Input.KEY_F11, DebugMode.MODE1), MODE1(
			Input.KEY_1, DebugMode.MODE1), MODE2(Input.KEY_2, DebugMode.MODE2), MODE3(
			Input.KEY_3, DebugMode.MODE3), RESTART(Input.KEY_F1, DebugMode.VOID), EXIT(
			Input.KEY_F4, DebugMode.VOID), ORIGIN(Input.KEY_F10,
			DebugMode.MODE1);

	private int command;
	private DebugMode mode;

	private DebugCommand(int input, DebugMode mode) {
		command = input;
		this.mode = mode;
	}

	public void setCommand(int command) {
		this.command = command;
	}

	@Override
	public int getCommand() {
		return command;
	}

	public DebugMode getMode() {
		return mode;
	}

	@Override
	public CommandType getType() {
		return CommandType.DEBUG;
	}

}

package com.alnaiyr.commands;

import org.newdawn.slick.Input;

/**
 * Commands for all menus
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public enum MenuCommand implements Commandable {

	UP(Input.KEY_W), ALTUP(Input.KEY_UP), DOWN(Input.KEY_DOWN), ALTDOWN(
			Input.KEY_S), ENTER(Input.KEY_ENTER), BACK(Input.KEY_ESCAPE), LEFT(
			Input.KEY_LEFT), ALTLEFT(Input.KEY_A), RIGHT(Input.KEY_RIGHT), ALTRIGHT(
			Input.KEY_D);

	private int command;

	private MenuCommand(int input) {
		command = input;
	}

	public void setCommand(int command) {
		this.command = command;
	}

	@Override
	public int getCommand() {
		return command;
	}

	@Override
	public CommandType getType() {
		return CommandType.MENU;
	}

}

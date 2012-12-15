package com.alnaiyr.commands;

import org.newdawn.slick.Input;

/**
 * Describes all moving commands, and the associated input
 * 
 * @author IgoЯ
 * @version 1.2
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>added getters, type</em></li>
 *          </ul>
 */
public enum MoveCommand implements Commandable {

	LEFT(Input.KEY_A), RIGHT(Input.KEY_D), JUMP(Input.KEY_SPACE);

	private int command;

	private MoveCommand(int input) {
		command = input;
	}

	/**
	 * 
	 * @param command
	 */
	public void setCommand(int command) {
		this.command = command;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.Al_Nair.commands.Command#getCommand()
	 */
	@Override
	public int getCommand() {
		return command;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.Al_Nair.commands.Command#getType()
	 */
	@Override
	public CommandType getType() {
		return CommandType.PLAYER;
	}

}

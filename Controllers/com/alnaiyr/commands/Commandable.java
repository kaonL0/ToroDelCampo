package com.alnaiyr.commands;

/**
 * Describe everything that is related to a command
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>Works</em></li>
 *          </ul>
 */
public interface Commandable {

	/* ***********************
	 * 
	 * Methods
	 * 
	 * **************************
	 */

	/**
	 * Gets the command int on keyboard
	 * 
	 * @return the command int
	 */
	public int getCommand();

	/**
	 * Gets the type of command in use
	 * 
	 * @return the type of command
	 */
	public CommandType getType();

}

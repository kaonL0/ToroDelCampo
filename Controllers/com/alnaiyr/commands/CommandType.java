package com.alnaiyr.commands;

/**
 * Describes all kind of command
 * 
 * @author IgoЯ
 * @version 1.2
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>added opposed</em></li>
 *          <li><em>added activated</em></li>
 *          </ul>
 */
public enum CommandType {

	CAMERA, PLAYER, DEBUG, MENU;

	private boolean activated = false;

	/**
	 * 
	 * @return
	 */
	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean b) {
		this.activated = b;
	}

	public void activate() {
		activated = true;
	}

}

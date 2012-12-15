package com.alnaiyr.commands;

import org.newdawn.slick.Input;

/**
 * Commands for camera
 * 
 * @author IgoЯ
 * @version 0.1
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>?</em></li>
 *          </ul>
 */
public enum CameraCommand implements Commandable {

	LEFT(Input.KEY_Q), ALTLEFT(Input.KEY_LEFT), RIGHT(Input.KEY_D), ALTRIGHT(
			Input.KEY_RIGHT), UP(Input.KEY_Z), ALTUP(Input.KEY_UP), DOWN(
			Input.KEY_S), ALTDOWN(Input.KEY_DOWN), ZOOMIN(Input.KEY_A), ZOOMOUT(
			Input.KEY_E), TRANSLATE(Input.KEY_SPACE), SETFREE(Input.KEY_BACK), SETORIGIN(
			Input.KEY_NUMPAD0);

	private int command;

	private CameraCommand(int input) {
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
		return CommandType.CAMERA;
	}

}

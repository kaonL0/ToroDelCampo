package com.alnaiyr.uinterface.boxes.impl.text;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.uinterface.boxes.AbstractBox;

/**
 * A box containing text and a listener
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class TextBox extends AbstractBox {

	private String message;

	private int messageHeight;
	private int messageWidth;

	/**
	 * @param coord
	 * @param message
	 * @param centered
	 */
	public TextBox(PlanVector coord, String message, boolean centered) {
		super(coord, centered, AbstractBox.font.getWidth(message),
				AbstractBox.font.getHeight(message));
		setMessage(message);

		messageHeight = AbstractBox.font.getHeight(message);
		messageWidth = AbstractBox.font.getWidth(message);
	}

	/* *********************
	 * 
	 * Methods
	 * 
	 * *****************************
	 */

	@Override
	public void render(Graphics g, GameContainer container) {
		AbstractBox.font
				.drawString(coord.x(), coord.y(), message, g.getColor());
	}

	/* *************************
	 * 
	 * Getters and Setters
	 * 
	 * *******************************
	 */

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
		messageWidth = AbstractBox.font.getWidth(message);
		messageHeight = AbstractBox.font.getHeight(message);
	}

	/**
	 * @return the message Height
	 */
	public int getMessageHeight() {
		return messageHeight;
	}

	/**
	 * @return the message Width
	 */
	public int getMessageWidth() {
		return messageWidth;
	}

	@Override
	public int width() {
		return (int) (messageWidth * scale);
	}

	@Override
	public int height() {
		return (int) (messageHeight * scale);
	}
}

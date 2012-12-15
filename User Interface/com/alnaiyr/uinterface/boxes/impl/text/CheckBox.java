package com.alnaiyr.uinterface.boxes.impl.text;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.alnaiyr.commands.Commands;
import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.coordinates.dynamic.Cartesian;
import com.alnaiyr.display.renderables.render.rewrite.Image;
import com.alnaiyr.uinterface.boxes.AbstractBox;
import com.alnaiyr.uinterface.boxes.impl.images.ImageBox;
import com.alnaiyr.uinterface.menu.GroupBox;

/**
 * A box with a value to check
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class CheckBox extends GroupBox {

	private Image fullImage;
	private int distance = 100;

	private boolean check = false;

	/**
	 * @param coord
	 * @param voidImage
	 * @param fullImage
	 * @param message
	 */
	public CheckBox(PlanVector coord, Image voidImage, Image fullImage,
			int distance, String message) {
		super(coord, false, voidImage.width()
				+ AbstractBox.font.getWidth(message) + distance, voidImage
				.height() > AbstractBox.font.getHeight(message) ? voidImage
				.height() : AbstractBox.font.getHeight(message));
		TextBox textBox = new TextBox(new Cartesian(0, 0, this.coord, false),
				message, false);
		ImageBox checkBox = new ImageBox(new Cartesian(
				textBox.getMessageWidth() + distance, 0, textBox.coord, false),
				voidImage, false);

		add(textBox, checkBox);

		this.fullImage = fullImage;
		this.distance = distance;
	}

	@Override
	public void update(int delta, boolean condition) {
		super.update(delta, condition);
		if (selected
				&& (isMouseOver() && Commands.isMousePressed(0) || activated))
			switchSelected();
	}

	@Override
	public void render(Graphics g, GameContainer container) {
		if (check)
			fullImage.draw(boxes.get(1).coord.x(), boxes.get(1).coord.y(),
					g.getColor());
	}

	/* ***************************
	 * 
	 * Getters and Setters
	 * 
	 * ************************************
	 */

	/**
	 * @return the check Image
	 */
	public Image getFullImage() {
		return fullImage;
	}

	/**
	 * @param fullImage
	 */
	public void setFullImage(Image fullImage) {
		this.fullImage = fullImage;
	}

	/**
	 * @return true if the checkBox is checked
	 */
	public boolean isSelected() {
		return check;
	}

	/**
	 * @param selected
	 */
	public void setSelected(boolean selected) {
		this.check = selected;
	}

	/**
     * 
     */
	public void switchSelected() {
		check = !check;
	}

	@Override
	public int width() {
		return (int) (boxes.get(0).width() + boxes.get(1).width() + distance
				* scale);
	}

	@Override
	public int height() {
		return boxes.get(1).height();
	}

}

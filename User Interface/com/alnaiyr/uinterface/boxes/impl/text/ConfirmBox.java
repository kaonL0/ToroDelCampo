package com.alnaiyr.uinterface.boxes.impl.text;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.alnaiyr.commands.Commandable;
import com.alnaiyr.commands.Commands;
import com.alnaiyr.commands.MenuCommand;
import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.coordinates.dynamic.Cartesian;
import com.alnaiyr.uinterface.boxes.AbstractBox;
import com.alnaiyr.uinterface.menu.GroupBox;
import com.alnaiyr.uinterface.menu.SelectionSwitcher;

/**
 * A box that contains two messages
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class ConfirmBox extends GroupBox {

	private int spaceBetween;

	private Commandable command = MenuCommand.ENTER;

	private SelectionSwitcher switcher;

	/**
	 * @param coord
	 * @param message
	 * @param secondM
	 * @param spaceBetween
	 * @param centered
	 */
	public ConfirmBox(PlanVector coord, String message, String secondM,
			int spaceBetween, boolean centered) {
		super(coord, centered, AbstractBox.font.getWidth(message + secondM),
				AbstractBox.font.getHeight(message + secondM));
		this.spaceBetween = spaceBetween;

		TextBox validate = new TextBox(new Cartesian(0, 0, this.coord, false),
				message, false);
		TextBox cancel = new TextBox(new Cartesian(validate.width()
				+ spaceBetween, 0, validate.coord, false), secondM, false);

		add(validate, cancel);

		switcher = new SelectionSwitcher(0, 1, 0);

		switcher.setCommandPlus(MenuCommand.RIGHT);
		switcher.setCommandAltPlus(MenuCommand.ALTRIGHT);
		switcher.setCommandMinus(MenuCommand.LEFT);
		switcher.setCommandAltMinus(MenuCommand.ALTLEFT);
	}

	@Override
	public void update(int delta, boolean condition) {
		super.update(delta, condition);

		if (selected)
			switcher.update(delta, condition);
	}

	@Override
	public void render(Graphics g, GameContainer container) {

	}

	/**
	 * 
	 * @return true if validate button was selected
	 */
	public boolean isValidate() {
		return boxes.get(0).isMouseOver() && Commands.isMousePressed(0)
				|| switcher.getValue() == 0 && Commands.isDown(command)
				&& selected;
	}

	/**
	 * 
	 * @return true if cancel button was selected
	 */
	public boolean isCancel() {
		return boxes.get(1).isMouseOver() && Commands.isMousePressed(0)
				|| switcher.getValue() == 1 && Commands.isDown(command)
				&& selected;
	}

	@Override
	public int width() {
		return boxes.get(0).width() + spaceBetween + boxes.get(1).width();
	}

	@Override
	public int height() {
		return boxes.get(0).height();
	}

}

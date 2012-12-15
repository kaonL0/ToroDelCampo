package com.alnaiyr.uinterface.boxes.impl.list;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.alnaiyr.commands.Commands;
import com.alnaiyr.commands.MenuCommand;
import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.coordinates.dynamic.Cartesian;
import com.alnaiyr.display.renderables.render.rewrite.Image;
import com.alnaiyr.uinterface.boxes.AbstractBox;
import com.alnaiyr.uinterface.boxes.impl.images.ImageBox;
import com.alnaiyr.uinterface.boxes.impl.text.TextBox;
import com.alnaiyr.uinterface.menu.GroupBox;
import com.alnaiyr.uinterface.menu.SelectionSwitcher;
import com.alnaiyr.utilities.font.FontU;

/**
 * A list containing several values
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class ListBox extends GroupBox {

	private String[] values;

	private int separator;

	protected int maxListLength;

	protected SelectionSwitcher switcher;

	protected byte selected = 0;

	/**
	 * @param coord
	 * @param message
	 * @param arrow
	 * @param separator
	 *            length between each elements (in pixels)
	 * @param values
	 */
	public ListBox(PlanVector coord, String message, Image arrow,
			int separator, String... values) {
		super(coord, false, 0, 0);

		maxListLength = FontU.getLongest(AbstractBox.font, values) + separator;

		// makes sure size is calculated well
		initCoord(coord,
				false,
				maxListLength + AbstractBox.font.getWidth(message)
						+ arrow.width() * 2,
				arrow.height() > AbstractBox.font.getHeight(message) ? arrow
						.height() : AbstractBox.font.getHeight(message));

		this.separator = separator;

		TextBox messageb = new TextBox(this.coord, message, false);

		ImageBox firstArrow = new ImageBox(new Cartesian(
				AbstractBox.font.getWidth(message) + separator, 0,
				messageb.coord, false), arrow, false);

		TextBox cValue = new TextBox(new Cartesian(arrow.width() + separator,
				0, firstArrow.coord, false), values[0], false);

		ImageBox secondArrow = new ImageBox(new Cartesian(maxListLength, 0,
				cValue.coord, false), arrow.getFlippedCopy(true, false), false);

		// 0 message,1 1st arrow,2 cvalue, 3 second arrow
		add(messageb, firstArrow, cValue, secondArrow);

		this.values = values;
		configureSwitcher();
	}

	/**
	 * @param coord
	 * @param message
	 * @param arrow
	 * @param objects
	 */
	public ListBox(PlanVector coord, String message, Image arrow,
			List<String> objects) {
		this(coord, message, arrow, 50, objects.toArray(new String[objects
				.size()]));
	}

	private void configureSwitcher() {
		switcher = new SelectionSwitcher(0, values.length - 1, 0);
		switcher.setCommandMinus(MenuCommand.RIGHT);
		switcher.setCommandAltMinus(MenuCommand.ALTRIGHT);
		switcher.setCommandPlus(MenuCommand.LEFT);
		switcher.setCommandAltPlus(MenuCommand.ALTLEFT);
	}

	/* **********************
	 * 
	 * Methods
	 * 
	 * *******************************
	 */

	@Override
	public void update(int delta, boolean condition) {

		if (super.selected) {

			if (boxes.get(1).isMouseOver() && Commands.isMousePressed(0))
				switcher.dec();

			if (boxes.get(3).isMouseOver() && Commands.isMousePressed(0)) {
				switcher.inc();
			}
			switcher.update(delta, true);
			TextBox box = (TextBox) boxes.get(2);
			box.setMessage(values[switcher.getValue()]);
		}
	}

	@Override
	public void render(Graphics g, GameContainer container) {

	}

	/* *********************
	 * 
	 * Getters and Setters
	 * 
	 * **************************
	 */

	/**
	 * @return the currently selected value
	 */
	public String getSelectedValue() {
		return values[switcher.getValue()];
	}

	/**
	 * @param i
	 * @return true if value i has been selected
	 */
	public boolean isSelected(int i) {
		try {
			return i == switcher.getValue();
		} catch (NullPointerException e) {
			return false;
		}
	}

	/**
	 * @return all values inside the list
	 */
	public String[] getValues() {
		return values;
	}

	/**
	 * 
	 * @param values
	 */
	public void setValues(String[] values) {
		this.values = values;
		switcher.setLast(values.length - 1);
	}

	/**
	 * Sets the value indicated, or the first value if String is not in the
	 * values stored
	 * 
	 * @param value
	 */
	public void setSelectedValue(String value) {

		int j = 0;
		for (int i = 0; i < values.length; i++) {
			if (value.equals(values[i])) {
				j = i;
				break;
			}
		}
		switcher.setValue(j);
		TextBox box = (TextBox) boxes.get(2);
		box.setMessage(values[j]);
	}

	@Override
	public int width() {
		return (int) (boxes.get(0).width() + boxes.get(1).width()
				+ maxListLength * scale + boxes.get(3).width() + 2 * separator
				* scale);
	}

	@Override
	public int height() {
		return boxes.get(1).height();
	}

	public AbstractBox getFirstArrow() {
		return boxes.get(1);
	}

	public AbstractBox getSecondArrow() {
		return boxes.get(3);
	}

}

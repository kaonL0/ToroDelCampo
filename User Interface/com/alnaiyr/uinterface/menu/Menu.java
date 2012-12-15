package com.alnaiyr.uinterface.menu;

import java.util.Arrays;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.alnaiyr.commands.Commandable;
import com.alnaiyr.commands.Commands;
import com.alnaiyr.commands.MenuCommand;
import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.coordinates.dynamic.Cartesian;
import com.alnaiyr.math.geom.shape.segments.Segment;
import com.alnaiyr.uinterface.boxes.AbstractBox;
import com.alnaiyr.uinterface.boxes.impl.text.TextBox;

/**
 * A box able to contains multiple boxes, and to organize them in a way, with
 * commands to access them
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class Menu extends GroupBox {

	private SelectionSwitcher selector;

	private Commandable command = MenuCommand.ENTER;

	/**
	 * 
	 * @param coord
	 * @param step
	 * @param centered
	 * @param boxes
	 */
	public Menu(PlanVector coord, float step, boolean centered,
			AbstractBox... boxes) {
		super(coord, boxes);

		int i = 1;

		for (AbstractBox box : boxes) {
			box.coord.setCoord(new Cartesian(0, step * i, coord, centered));
			i++;
		}

		if (boxes.length > 1)
			selector = new SelectionSwitcher(0, boxes.length - 1, 0);
		else
			selector = new SelectionSwitcher(-1, -1, -1);
	}

	/**
	 * 
	 * @param coord
	 * @param centered
	 * @param boxes
	 */
	public Menu(PlanVector coord, boolean centered, AbstractBox... boxes) {
		this(coord, 1 / (float) (boxes.length + 1), centered, boxes);
	}

	/**
	 * 
	 * @param coord
	 * @param step
	 * @param centered
	 * @param messages
	 */
	public Menu(PlanVector coord, float step, boolean centered,
			String... messages) {
		super(coord);
		AbstractBox[] boxes = new AbstractBox[messages.length];

		for (int i = 0; i < messages.length; i++) {

			boxes[i] = new TextBox(
					new Cartesian(0, i * step, this.coord, true), messages[i],
					centered);
		}

		this.boxes = Arrays.asList(boxes);
		if (boxes.length > 1)
			selector = new SelectionSwitcher(0, boxes.length - 1, 0);
		else
			selector = new SelectionSwitcher(-1, 0, -1);
	}

	/* *************************
	 * 
	 * Methods
	 * 
	 * ****************************
	 */

	@Override
	public void update(int delta, boolean condition) {
		super.update(delta, condition);

		if (condition) {

			for (int i = 0; i < boxes.size(); i++) {

				boxes.get(i).selected = false;
				boxes.get(i).activated = false;

				if (boxes.get(i).isMouseOver()) {
					selector.setValue(i);
				} else if (boxes.size() == 1) {
					selector.setValue(-1);
				}
			}

			try {
				boxes.get(selector.getValue()).selected = true;

				if (Commands.isPressed(command)) {
					boxes.get(selector.getValue()).activated = true;

				}

			} catch (ArrayIndexOutOfBoundsException e) {
			}

			selector.update(delta, true);

		} else {
			for (AbstractBox box : boxes) {
				box.update(delta, condition);
			}
		}
	}

	@Override
	public void render(Graphics g, GameContainer container) {
	}

	@Override
	public void debug(GameContainer container, Graphics g, boolean condition) {

		super.debug(container, g, condition);

		AbstractBox box = getSelectedBox();
		g.setColor(Color.blue);
		try {

			g.drawRect(box.getScaledX(), box.getScaledY(), box.width(),
					box.height());

		} catch (NullPointerException e) {
		}
		g.setColor(Color.white);

	}

	/**
	 * @param i
	 * @return true if option i is activated
	 */
	public boolean isActivated(int i) {
		final boolean pressed = Commands.isPressed(command);
		return (selector.getValue() == i || selector.getValue() == -1 && i == 0)
				&& (pressed || boxes.get(i).isMouseOver()
						&& (Commands.isMousePressed(0) || pressed));
	}

	/**
	 * Sets all boxes from this menu to follow a segment, adjusting their
	 * position to it
	 * 
	 * @param segment
	 * @param centered
	 */
	public void setOnSegment(Segment segment, boolean centered) {
		int i = 0;
		for (AbstractBox box : boxes) {
			float percent = 1 / (float) boxes.size();

			segment.bindCoordinate(percent * i, box.coord.getValue());
			box.setCentered(centered);
			i++;
		}
	}

	/* ***************************
	 * 
	 * Getters and Setters
	 * 
	 * *************************
	 */

	public AbstractBox getSelectedBox() {
		try {
			return boxes.get(selector.getValue());
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	/**
	 * @param index
	 * @return the box corresponding to this index
	 */
	public AbstractBox getBox(int index) {
		return boxes.get(index);
	}
}

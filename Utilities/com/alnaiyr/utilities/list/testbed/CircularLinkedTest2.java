package com.alnaiyr.utilities.list.testbed;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import com.alnaiyr.utilities.list.CircularLinkedList;

/**
 * An exemple for circular linked list, showing how works main functionnality
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>Functionnal</em></li>
 *          </ul>
 */

public class CircularLinkedTest2 extends BasicGame {

    /**
     * @param title
     */
    public CircularLinkedTest2(String title) {
	super(title);
    }

    private CircularLinkedList<Rectangle> circularList;

    private int i = 0;

    private boolean foo = true;

    private boolean previous = false;

    private boolean next = false;

    private int color;

    /**
     * @param g
     */
    public void switchColor(Graphics g) {
	switch (color) {
	case (0):
	    g.setColor(Color.white);
	    break;
	case (1):
	    g.setColor(Color.red);
	    break;
	case (2):
	    g.setColor(Color.blue);
	    break;
	case (3):
	    g.setColor(Color.green);
	    break;
	}
    }

    @Override
    public void render(GameContainer container, Graphics g)
	    throws SlickException {
	color = 1;
	switchColor(g);
	if (previous)
	    g.draw(circularList.getPrevious(i));
	color = 2;
	switchColor(g);
	if (foo)
	    g.draw(circularList.get(i));
	color = 3;
	switchColor(g);
	if (next)
	    g.draw(circularList.getNext(i));
	color = 0;
	switchColor(g);
	g.drawString("Previous: " + previous + "     Current: " + foo
		+ "       Next: " + next + "\nIndex: " + i, 50, 100);
	g.drawString(
		"Press Space for next element, Ctrl for previous element. \n A, S, D, toogle drawing of, respectively, previous, current, and next.",
		50, 200);
    }

    @Override
    public void init(GameContainer container) throws SlickException {
	circularList = new CircularLinkedList<>();

	for (int k = 0; k < container.getWidth(); k += 50)
	    circularList.add(new Rectangle(k, 400, 50, 50));
	circularList.Set(4, new Rectangle(0, 0, 100, 100));

    }

    @Override
    public void update(GameContainer container, int delta)
	    throws SlickException {

	if (container.getInput().isKeyDown(Input.KEY_ESCAPE))
	    container.exit();
	if (container.getInput().isKeyDown(Input.KEY_F1)) {
	    container.reinit();
	}
	if (container.getInput().isKeyPressed(Input.KEY_A)) {
	    previous = !previous;
	}
	if (container.getInput().isKeyPressed(Input.KEY_S)) {
	    foo = !foo;
	}
	if (container.getInput().isKeyPressed(Input.KEY_D)) {
	    next = !next;
	}
	if (container.getInput().isKeyPressed(Input.KEY_SPACE)) {
	    i++;
	    if (i > circularList.size() - 1)
		i = 0;
	}
	if (container.getInput().isKeyPressed(Input.KEY_LCONTROL)) {
	    i--;
	    if (i < 0)
		i = circularList.size() - 1;
	}

    }

    /**
     * @param argv
     */
    public static void main(String[] argv) {

	try {
	    AppGameContainer container = new AppGameContainer(
		    new CircularLinkedTest2("Circular Linked List Test"));
	    container.setDisplayMode(800, 600, false);
	    container.start();
	} catch (SlickException e) {
	    e.printStackTrace();
	}
    }
}

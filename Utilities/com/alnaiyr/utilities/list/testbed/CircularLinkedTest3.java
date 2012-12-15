package com.alnaiyr.utilities.list.testbed;

import java.util.ListIterator;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
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

public class CircularLinkedTest3 extends BasicGame {

    /**
     * @param title
     */
    public CircularLinkedTest3(String title) {

	super(title);
    }

    private CircularLinkedList<Rectangle> circularList;
    private ListIterator<Rectangle> it;

    private boolean foo = true;

    @Override
    public void render(GameContainer container, Graphics g)
	    throws SlickException {

	if (foo) {

	    g.draw(it.next());
	}

    }

    @Override
    public void init(GameContainer container) throws SlickException {

	circularList = new CircularLinkedList<>();

	for (int k = 0; k < container.getWidth(); k += 50)
	    circularList.add(new Rectangle(k, 400, 50, 50));
	it = circularList.listIterator();
    }

    @Override
    public void update(GameContainer container, int delta)
	    throws SlickException {

	if (container.getInput().isKeyDown(Input.KEY_ESCAPE))
	    container.exit();
	if (container.getInput().isKeyDown(Input.KEY_F1)) {
	    container.reinit();
	}

	if (container.getInput().isKeyPressed(Input.KEY_SPACE)) {
	    foo = !foo;
	}

    }

    /**
     * @param argv
     */
    public static void main(String[] argv) {

	try {
	    AppGameContainer container = new AppGameContainer(
		    new CircularLinkedTest3("Circular Linked List Test"));
	    container.setDisplayMode(800, 600, false);
	    container.setTargetFrameRate(2);
	    container.start();

	} catch (SlickException e) {
	    e.printStackTrace();
	}
    }
}

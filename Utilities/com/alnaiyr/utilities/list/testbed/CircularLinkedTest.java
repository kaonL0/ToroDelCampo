package com.alnaiyr.utilities.list.testbed;

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

public class CircularLinkedTest extends BasicGame {

    /**
     * @param title
     */
    public CircularLinkedTest(String title) {
	super(title);
    }

    private CircularLinkedList<Rectangle> circularList;

    private boolean reverse = false;

    private int j = 0;
    private int i = 0;

    @Override
    public void render(GameContainer container, Graphics g)
	    throws SlickException {

	g.draw(circularList.getPrevious(i));
	g.draw(circularList.get(i));
	g.draw(circularList.getNext(i));

	g.drawString("Press space to reverse movement", 300, 300);
    }

    @Override
    public void init(GameContainer container) throws SlickException {
	circularList = new CircularLinkedList<>();
	int k = 0;
	while (k < container.getWidth()) {
	    circularList.add(new Rectangle(k, 0, 50, 50));
	    circularList.getLast();
	    k += 50;
	}
	k = 0;
	while (k < container.getHeight() - 50) {
	    circularList.add(new Rectangle(container.getWidth() - 50, 50 + k,
		    50, 50));
	    k += 50;
	}
	k = container.getWidth() - 100;
	while (k > 0) {
	    circularList.add(new Rectangle(k, container.getHeight() - 50, 50,
		    50));
	    k -= 50;
	}
	k = container.getHeight() - 50;
	while (k > 0) {
	    circularList.add(new Rectangle(0, k, 50, 50));
	    k -= 50;
	}
    }

    @Override
    public void update(GameContainer container, int delta)
	    throws SlickException {

	j = (j == 20) ? 0 : j;
	j++;

	// GV.test(i);
	// GV.test(i < circularList.size());
	if (j == 20) {
	    if (reverse) {
		i--;
		i = (i < 0) ? circularList.size() - 1 : i;
	    } else {
		i = (i < circularList.size() - 1) ? i : -1;
		i++;
	    }
	}

	if (container.getInput().isKeyDown(Input.KEY_ESCAPE))
	    container.exit();
	if (container.getInput().isKeyDown(Input.KEY_F1)) {
	    container.reinit();
	}
	if (container.getInput().isKeyPressed(Input.KEY_SPACE)) {
	    reverse = !reverse;
	}

    }

    /**
     * @param argv
     */
    public static void main(String[] argv) {

	try {
	    AppGameContainer container = new AppGameContainer(
		    new CircularLinkedTest("Circular Linked List Test"));
	    container.setDisplayMode(800, 600, false);
	    container.start();
	} catch (SlickException e) {
	    e.printStackTrace();
	}
    }
}

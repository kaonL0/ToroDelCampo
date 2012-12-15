/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.math.numbers.profiles.testbed;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import com.alnaiyr.math.numbers.advanced.PercentMM;
import com.alnaiyr.math.numbers.impl.DeltaFloatStorerImpl;
import com.alnaiyr.math.numbers.profiles.Profile;
import com.alnaiyr.math.numbers.profiles.behaves.limit.LimitBehaves;
import com.alnaiyr.math.numbers.profiles.behaves.release.ReleaseBehaves;
import com.alnaiyr.utilities.example.Example;

/**
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class ProfileTester extends Example {

	private Profile profile;
	private org.newdawn.slick.geom.Vector2f pos = new Vector2f(100, 300);

	public ProfileTester(String title) {
		super(title);

	}

	@SuppressWarnings("boxing")
	@Override
	public void initialize(GameContainer container) throws SlickException {

		profile = new Profile();
		profile.setValue(0);
		profile.setDerivative(new DeltaFloatStorerImpl(0.001f));
		profile.setMinMax(PercentMM.instance);
		profile.setLimit(LimitBehaves.LOOPBACK);
		profile.setRelease(ReleaseBehaves.TOSTART);
		// DB.test(MemoryUtil.memoryUsageOf(profile));
	}

	@Override
	public void updateIt(GameContainer container, int delta)
			throws SlickException {
		profile.update(delta, container.getInput().isKeyDown(Input.KEY_SPACE));
		pos.x = 100 * (1 - profile.getValue()) + 700 * profile.getValue();
	}

	@Override
	public void renderIt(GameContainer container, Graphics g)
			throws SlickException {
		g.draw(new Rectangle(pos.x, pos.y, 50, 50));
	}

	public static void main(String[] args) {
		try {

			AppGameContainer app = new AppGameContainer(new ProfileTester(
					"test profile"));
			app.setDisplayMode(800, 600, false);
			app.start();
		} catch (SlickException e) {

			e.printStackTrace();
		}
	}
}

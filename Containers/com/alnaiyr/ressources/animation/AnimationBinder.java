/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.ressources.animation;

import com.alnaiyr.display.renderables.render.rewrite.Animation;
import com.alnaiyr.ressources.RessourceBinder;

/**
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class AnimationBinder implements RessourceBinder<Animation> {

	@Override
	public void bind(final String pack, final String name, final Animation value) {
		switch (pack) {
		// case "Toro":
		// ToroAnimation.valueOf(name).animation = value;
		// value.setAutoUpdate(true);
		// return;
		}
		throw new NullPointerException();
	}

	@Override
	public boolean isBinded(final String pack, final String name) {

		try {
			switch (pack) {
			// case "Toro":
			// return ToroAnimation.valueOf(name).animation != null;
			}
		} catch (final NullPointerException e) {
			throw new NullPointerException(
					"type of image unknow: check AnimationBinder!");
		}

		throw new NullPointerException(
				"type of image unknow: check AnimationBinder!");
	}
}

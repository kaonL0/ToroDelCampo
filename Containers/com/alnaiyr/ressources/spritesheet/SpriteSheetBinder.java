/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.ressources.spritesheet;

import com.alnaiyr.display.renderables.render.rewrite.SpriteSheet;
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
public class SpriteSheetBinder implements RessourceBinder<SpriteSheet> {

	@Override
	public void bind(final String pack, final String name,
			final SpriteSheet value) {
		switch (pack) {
			case "Toro":
				ToroSpriteSheet.valueOf(name).spritesheet = value;
				return;
		}
		throw new NullPointerException();
	}

	@Override
	public boolean isBinded(final String pack, final String name) {
		switch (pack) {
			case "Toro":
				return ToroSpriteSheet.valueOf(name).spritesheet != null;
		}
		throw new NullPointerException();
	}

}

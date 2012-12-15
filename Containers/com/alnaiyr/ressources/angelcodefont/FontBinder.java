/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.ressources.angelcodefont;

import org.newdawn.slick.AngelCodeFont;

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
public class FontBinder implements RessourceBinder<AngelCodeFont> {

	@Override
	public void bind(final String pack, final String name,
			final AngelCodeFont value) {
		switch (pack) {
			case "Toro":
				ToroAngelCodeFont.valueOf(name).angelcodefont = value;
				return;
		}
		throw new NullPointerException();
	}

	@Override
	public boolean isBinded(final String pack, final String name) {

		switch (pack) {
			case "Toro":
				return ToroAngelCodeFont.valueOf(name).angelcodefont != null;
		}
		throw new NullPointerException();
	}
}

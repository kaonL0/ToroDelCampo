/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.ressources.image;

import com.alnaiyr.display.renderables.render.rewrite.Image;
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
public class ImageBinder implements RessourceBinder<Image> {

	/*---------------------
	 * 
	 * Methods
	 * 
	 *----------------------*/

	@Override
	public void bind(final String pack, final String name, final Image value) {
		switch (pack) {
			case "Toro":
				ToroImage.valueOf(name).image = value;
				return;
		}
		throw new NullPointerException();
	}

	@Override
	public boolean isBinded(final String pack, final String name) {
		try {
			switch (pack) {
				case "Toro":
					return ToroImage.valueOf(name).image != null;
			}
		} catch (final NullPointerException e) {
			throw new NullPointerException(
					"type of image unknow: check ImageBinder!");
		}

		throw new NullPointerException(
				"type of image unknow: check ImageBinder!");
	}

	/*---------------------
	 * 
	 * Getters / Setters
	 * 
	 *----------------------*/
}

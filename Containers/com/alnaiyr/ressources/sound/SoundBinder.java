/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.ressources.sound;

import org.newdawn.slick.Sound;

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
public class SoundBinder implements RessourceBinder<Sound> {

	@Override
	public void bind(String pack, String name, Sound value) {
	}

	@Override
	public boolean isBinded(String pack, String name) {

		return false;
	}
}

/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.sfx.filters.types.transition.config;

import org.newdawn.slick.Color;

import com.alnaiyr.math.numbers.advanced.PercentMM;
import com.alnaiyr.math.numbers.profiles.behaves.limit.LimitBehaves;
import com.alnaiyr.math.numbers.profiles.behaves.release.ReleaseBehaves;
import com.alnaiyr.sfx.filters.types.transition.TransitionSFXConfigImpl;

/**
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class FadeSFXConfig extends TransitionSFXConfigImpl {

	/*---------------------
	 * 
	 * Variables
	 * 
	 *----------------------*/

	private Color fade = new Color(Color.white);

	/*---------------------
	 * 
	 * Constructor
	 * 
	 *----------------------*/

	public FadeSFXConfig(float speed, LimitBehaves limit, ReleaseBehaves release) {
		super(speed, limit, release);
		minMax = PercentMM.instance;

	}

	public FadeSFXConfig(Color color, float speed, LimitBehaves limit,
			ReleaseBehaves release) {
		this(speed, limit, release);
		this.setFade(color);
	}

	/*---------------------
	 * 
	 * Methods
	 * 
	 *----------------------*/

	/*---------------------
	 * 
	 * Getters / Setters
	 * 
	 *----------------------*/

	public Color getFade() {
		return fade;
	}

	public void setFade(Color fade) {
		this.fade = fade;
	}
}

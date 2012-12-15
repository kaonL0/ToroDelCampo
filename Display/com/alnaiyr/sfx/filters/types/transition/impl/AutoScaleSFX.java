/**
 * Created by Al Naiyr Studio , all right reserved. Créé par Al Naiyr, tous
 * droits réservés
 */
package com.alnaiyr.sfx.filters.types.transition.impl;

import com.alnaiyr.ai.updater.condition.Conditionable;
import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.coordinates.utilities.VecU;
import com.alnaiyr.display.GraphicEntity;
import com.alnaiyr.display.renderables.Scalable;
import com.alnaiyr.sfx.filters.types.transition.TransitionSFXConfig;

/**
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class AutoScaleSFX extends ScaleSFX {

	private final Scalable	toScale;

	/**
	 * @param condition
	 * @param config
	 */
	public AutoScaleSFX(final PlanVector coord, final Conditionable condition,
			final Scalable scale, final TransitionSFXConfig config) {
		super(coord, condition, config);
		toScale = scale;
	}

	@Override
	public void update(final GraphicEntity t, final int delta,
			final boolean condition) {
		super.update(t, delta, condition);
		toScale.setScale(profile.getValue());
		toScale.setStep(VecU.getDifference(coord, toScale.getCoord()));

	}
}

package com.alnaiyr.sfx.filters.types.transition;

import com.alnaiyr.ai.updater.condition.Conditionable;
import com.alnaiyr.display.GraphicEntity;
import com.alnaiyr.math.numbers.profiles.Profile;
import com.alnaiyr.sfx.filters.types.SFXImpl;

public abstract class TransitionSFX extends SFXImpl {

	/* ****************
	 * 
	 * Variables
	 * 
	 * ***************
	 */

	public Profile profile;

	/* ****************
	 * 
	 * Constructors
	 * 
	 * ***************
	 */

	public TransitionSFX(Conditionable condition, TransitionSFXConfig config) {
		super(condition);
		profile = new Profile();
		profile.setMinMax(config.getMinMax());
		profile.setLimit(config.getLimit());
		profile.setRelease(config.getRelease());
		profile.setDerivative(config);
		profile.setMultiplier(1 / config.getMinMax().getLength());
	}

	/* ****************
	 * 
	 * Methods
	 * 
	 * ***************
	 */

	@Override
	public void update(GraphicEntity gEntity, int delta, boolean cond) {
		if (cond) {
			profile.update(delta, condition.getCondition());
		}
	}

	/* ****************
	 * 
	 * Getters / Setters
	 * 
	 * ***************
	 */

}

package com.alnaiyr.display.impl.advanced;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.particles.ParticleSystem;

import com.alnaiyr.ai.updater.condition.Conditionable;
import com.alnaiyr.ai.updater.condition.TrueCondition;
import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.GraphicEntity;

/**
 * 
 * 
 * @author IgoR from Alnaiyr
 * 
 */
public class ParticleEntity extends GraphicEntity {

	private final ParticleSystem	sys;

	private Conditionable			cond	= TrueCondition.instance;

	public ParticleEntity(final PlanVector coord, final ParticleSystem sys) {
		super(coord, false, 0, 0);
		this.sys = sys;
	}

	@Override
	public void render(final Graphics g, final GameContainer container) {
		sys.render(coord.x(), coord.y());

	}

	@Override
	public int width() {
		return 0;
	}

	@Override
	public int height() {
		return 0;
	}

	private static boolean	condition;

	@Override
	public void gUpdate(final int delta, final boolean n) {
		condition = cond.getCondition();
		for (int i = 0; i < sys.getEmitterCount(); i++)
			sys.getEmitter(i).setEnabled(condition);

		sys.update(delta);

	}

	public Conditionable getCond() {
		return cond;
	}

	public void setCond(final Conditionable cond) {
		this.cond = cond;
	}

}

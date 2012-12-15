package com.alnaiyr.sfx.filters.types;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.alnaiyr.ai.updater.condition.ConditionStorer;
import com.alnaiyr.ai.updater.condition.Conditionable;
import com.alnaiyr.ai.updater.condition.TrueCondition;
import com.alnaiyr.display.GraphicEntity;
import com.alnaiyr.sfx.filters.SFX;
import com.alnaiyr.sfx.filters.SFXProfiler;

public abstract class SFXImpl implements SFXProfiler, SFX {

	/* ****************
	 * 
	 * Variables
	 * 
	 * ***************
	 */

	protected Conditionable condition;

	/* ****************
	 * 
	 * Constructors
	 * 
	 * ***************
	 */

	/**
	 * @param condition
	 */
	public SFXImpl(Conditionable condition) {
		setCondition(condition);
	}

	/**
	 * @param condition
	 */
	public SFXImpl(boolean condition) {
		setCondition(new ConditionStorer(condition));
	}

	/**
	 * @param condition
	 */
	public SFXImpl() {
		setCondition(TrueCondition.instance);
	}

	/* ****************
	 * 
	 * Methods
	 * 
	 * ***************
	 */

	@Override
	public void pushSFX(GraphicEntity gEntity, Graphics g,
			GameContainer container) {
		preWrap(gEntity, g, container);

	}

	protected abstract void preWrap(GraphicEntity gEntity, Graphics g,
			GameContainer container);

	@Override
	public void popSFX(GraphicEntity gEntity, Graphics g,
			GameContainer container) {
		postWrap(gEntity, g, container);
	}

	protected abstract void postWrap(GraphicEntity gEntity, Graphics g,
			GameContainer container);

	/* ****************
	 * 
	 * Getters / Setters
	 * 
	 * ***************
	 */

	public Conditionable getCondition() {
		return condition;
	}

	public void setCondition(Conditionable condition) {
		this.condition = condition;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.alnaiyr.sfx.wrappers.WrapProfiler#configure(com.alnaiyr.display.layerable
	 * .LayerInPosition)
	 */
	@Override
	public void configure(GraphicEntity gEntity) {
		// condition = gEntity.getCondition();
	}

}

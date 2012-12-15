/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.display.impl.advanced;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.alnaiyr.ai.updater.condition.Conditionable;
import com.alnaiyr.ai.updater.condition.TrueCondition;
import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.GraphicEntity;
import com.alnaiyr.display.renderables.render.rewrite.Animation;
import com.alnaiyr.display.renderables.render.rewrite.SpriteSheet;

/**
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class AnimationEntity extends GraphicEntity {

	/*---------------------
	 * 
	 * Variables
	 * 
	 *----------------------*/

	public Animation		animation;
	private Conditionable	condition	= TrueCondition.instance;

	/*---------------------
	 * 
	 * Constructor
	 * 
	 *----------------------*/

	public AnimationEntity(final PlanVector coord, final boolean centered,
			final Animation animation) {
		super(coord, centered, animation.width(), animation.height());
		this.animation = animation;
		this.animation.stop();
	}

	public AnimationEntity(final PlanVector coord, final Animation animation) {
		this(coord, true, animation);
		this.animation = animation;
		this.animation.stop();
	}

	public AnimationEntity(final PlanVector coord, final SpriteSheet animation) {
		super(coord, false, animation.getSprite(0, 0).width(), animation
				.getSprite(0, 0).height());
		this.animation = new Animation(animation, 120);
		this.animation.stop();
	}

	/*---------------------
	 * 
	 * Methods
	 * 
	 *----------------------*/

	public void start() {
		animation.start();
	}

	@Override
	public void gUpdate(final int delta, final boolean condition) {
		animation.update(delta, this.condition.getCondition());
	}

	@Override
	public void render(final Graphics g, final GameContainer container) {
		animation.draw(coord);
	}

	/*------------------------
	 * 
	 * Getters and Setters
	 * 
	 *------------------------*/

	@Override
	public int width() {
		return animation.width();
	}

	@Override
	public int height() {
		return animation.height();
	}

	public Conditionable getCondition() {
		return condition;
	}

	public void setCondition(final Conditionable condition) {
		this.condition = condition;
	}

}

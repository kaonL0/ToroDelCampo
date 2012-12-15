package com.alnaiyr.display;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.impl.advanced.AnimationEntity;
import com.alnaiyr.display.impl.basic.DrawEntity;
import com.alnaiyr.display.renderables.DimensionDrawable;
import com.alnaiyr.display.renderables.render.rewrite.Animation;

/**
 * 
 * 
 * @author IgoR from Alnaiyr
 * 
 */
public class GraphicEntityFactory {

	public static GraphicEntity construct(final PlanVector coordinate,
			final DimensionDrawable drawable) {

		switch (drawable.getType()) {

			case ANIMATION:
				return new AnimationEntity(coordinate, (Animation) drawable);
			case IMAGE:
				return new DrawEntity(coordinate, drawable);
			case SPRITESHEET:
				return new DrawEntity(coordinate, drawable);

		}
		return null;
	}

}

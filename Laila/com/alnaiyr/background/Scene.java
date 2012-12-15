package com.alnaiyr.background;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.GraphicEntity;
import com.alnaiyr.general.IV;
import com.alnaiyr.math.geom.shape.Rectangle;

/**
 * 
 * 
 * @author IgoR from Alnaiyr
 * 
 */
public class Scene extends GraphicEntity {

	private final SceneGenerator		generator;
	private final List<GraphicEntity>	entities		= new ArrayList<>();

	private final int					width, height;

	private final int					creationWidth, creationHeight;
	private final Vector2f				lastgenerated	= new Vector2f();

	private Scene(final PlanVector local, final int width, final int height,
			final SceneGenerator generator, final int creationWidth,
			final int creationHeight) {
		super(local, false, width, height);
		this.generator = generator;
		this.width = width;
		this.height = height;
		this.creationWidth = creationWidth;
		this.creationHeight = creationHeight;
	}

	@Override
	public void render(final Graphics g, final GameContainer container) {
		for (final GraphicEntity ent : entities) {
			ent.render(g, container);
		}

	}

	@Override
	public int width() {
		return width;
	}

	@Override
	public int height() {
		return height;
	}

	@Override
	public void gUpdate(final int delta, final boolean condition) {

		for (int i = 0; i < entities.size(); i++) {
			// if (GeomU.isInside(entities.get(i).coord, IV.vWidth,
			// lay.focus.coord, IV.vHeight)) {
			entities.get(i).gUpdate(delta, condition);
			// } else {

			// entities.remove(i);
			// }
		}

		entities.addAll(generator.generate(new Rectangle(new Vector2f(
				lay.focus.coord.x() + IV.vWidth / 2, lay.focus.coord.y()
						+ IV.vHeight / 2), width, height)));

	}

}

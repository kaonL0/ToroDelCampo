package com.alnaiyr.background;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.newdawn.slick.geom.Vector2f;

import com.alnaiyr.display.GraphicEntity;
import com.alnaiyr.display.GraphicEntityFactory;
import com.alnaiyr.display.renderables.DimensionDrawable;
import com.alnaiyr.math.MathU;
import com.alnaiyr.math.geom.GeomU;
import com.alnaiyr.math.geom.shape.Rectangle;
import com.alnaiyr.math.numbers.advanced.MinMax;
import com.alnaiyr.math.numbers.advanced.MinMaxImpl;

/**
 * 
 * 
 * @author IgoR from Alnaiyr
 * 
 */
public class SceneGenerator {

	private final List<DimensionDrawable>	drawablePool;

	private SceneGeneratorOption			opt;
	private final MinMax					maxNumberOfElement;

	public SceneGenerator(final SceneGeneratorOption opt,
			final DimensionDrawable... dimensionDrawables) {
		super();
		this.opt = opt;
		drawablePool = Arrays.asList(dimensionDrawables);
		maxNumberOfElement = new MinMaxImpl(1, 1);
	}

	public SceneGenerator(final SceneGeneratorOption opt, final MinMax mm,
			final DimensionDrawable... dimensionDrawables) {
		super();
		drawablePool = Arrays.asList(dimensionDrawables);
		maxNumberOfElement = mm;
	}

	public List<GraphicEntity> generate(final Rectangle rect) {

		final int number = MathU.random((int) maxNumberOfElement.getStart(),
				(int) maxNumberOfElement.getEnd());

		final List<GraphicEntity> generated = new ArrayList<>(number);

		final List<DimensionDrawable> remaining = new ArrayList<>(drawablePool);
		switch (opt) {
			case OVERLAPPERMITTED:
				generateTotalRandom(rect, number, generated, remaining);
			case FILL:
				generateToFill(rect, number, generated, remaining);
			case FILLOVERLAP:
				generateToFillOverlap(rect, number, generated, remaining);
			case NOOVERLAP:
				generateNoOverLap(rect, number, generated, remaining);
		}

		return generated;
	}

	/**
	 * for now just put one or 0
	 * 
	 * @param rect
	 * @param number
	 * @param generated
	 * @param remaining
	 */
	private void generateNoOverLap(final Rectangle rect, final int number,
			final List<GraphicEntity> generated,
			final List<DimensionDrawable> remaining) {

		final DimensionDrawable temp = drawablePool.get(MathU.random(0,
				drawablePool.size() - 1));
		remaining.remove(temp);
		generated.add(GraphicEntityFactory.construct(
				GeomU.getRandomCoordinateInside(rect), temp));

	}

	/**
	 * @param rect
	 * @param number
	 * @param generated
	 * @param remaining
	 */
	private void generateTotalRandom(final Rectangle rect, final int number,
			final List<GraphicEntity> generated,
			final List<DimensionDrawable> remaining) {
		for (int i = 0; i < number; i++) {

			final DimensionDrawable temp = drawablePool.get(MathU.random(0,
					drawablePool.size() - 1));
			remaining.remove(temp);
			generated.add(GraphicEntityFactory.construct(
					GeomU.getRandomCoordinateInside(rect), temp));
		}
	}

	/**
	 * @param rect
	 * @param number
	 * @param generated
	 * @param remaining
	 */
	private void generateToFill(final Rectangle rect, final int number,
			final List<GraphicEntity> generated,
			final List<DimensionDrawable> remaining) {
		final Vector2f vec = new Vector2f(rect.getCoord());
		for (int i = 0; i < number; i++) {

			final DimensionDrawable temp = drawablePool.get(MathU.random(0,
					drawablePool.size() - 1));
			remaining.remove(temp);
			vec.addLocal(0, MathU.random(rect.getCoord().y(), rect
					.getDownLeftCorner().y()));
			generated.add(GraphicEntityFactory.construct(vec, temp));
			vec.setZero();
			vec.addLocal(temp.width(), 0);
		}
	}

	/**
	 * @param rect
	 * @param number
	 * @param generated
	 * @param remaining
	 */
	private void generateToFillOverlap(final Rectangle rect, final int number,
			final List<GraphicEntity> generated,
			final List<DimensionDrawable> remaining) {
		final Vector2f vec = new Vector2f(rect.getCoord());
		for (int i = 0; i < number; i++) {

			final DimensionDrawable temp = drawablePool.get(MathU.random(0,
					drawablePool.size() - 1));
			remaining.remove(temp);
			vec.addLocal(0, MathU.random(rect.getCoord().y(), rect
					.getDownLeftCorner().y()));
			generated.add(GraphicEntityFactory.construct(vec, temp));
			vec.setZero();
			vec.addLocal(temp.width() * MathU.random(.7f, 1), 0);
		}
	}
}

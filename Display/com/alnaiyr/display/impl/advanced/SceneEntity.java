package com.alnaiyr.display.impl.advanced;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.GraphicEntity;
import com.alnaiyr.display.camera.utilities.CameraUtilities;
import com.alnaiyr.entity.SelfAddingToLayer;
import com.alnaiyr.general.IV;
import com.alnaiyr.math.geom.GeomU;

/**
 * 
 * 
 * @author IgoR from Alnaiyr
 * 
 */
public class SceneEntity extends GraphicEntity implements SelfAddingToLayer {

	private List<GraphicEntity>	entities;
	private int					width;
	private int					height;

	public SceneEntity(final PlanVector coord, final boolean centered,
			final int width, final int height) {
		super(coord, centered, width, height);

	}

	@Override
	public void render(final Graphics g, final GameContainer container) {
		for (final GraphicEntity ent : entities) {

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
		if (GeomU.isInside(CameraUtilities.camToScreen(coord, lay.focus),
				IV.vWidth, IV.center, IV.vHeight)) {

		}

	}

	@Override
	public void addMyselfTo(final int layerNo) {
		// TODO Auto-generated method stub

	}

}

/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.level.layer;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.common.Vec2;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.alnaiyr.ai.updater.Updatable;
import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.debug.Debug;
import com.alnaiyr.display.renderables.Renderable;
import com.alnaiyr.general.IV;

/**
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public final class ParallaxLayerContainer implements Updatable, Renderable,
		Debug {

	/*---------------------
	 * 
	 * Variables
	 * 
	 *----------------------*/

	private static final boolean	parallax	= true;

	private final List<Layer>		layers;
	private final List<Float>		z;

	public float					zDistance	= 50;

	public PlanVector				center		= IV.center;

	public Layer					reference;

	private final PlanVector		thetas		= new Vec2();

	/*---------------------
	 * 
	 * Constructor
	 * 
	 *----------------------*/

	public ParallaxLayerContainer(final Layer... layers) {

		this(layers[0], 50, layers);
	}

	/**
	 * Note that reference must be contained in layers tab. The depth of layers
	 * should be define before adding them. (not necessarily the right value,
	 * but at least order should be defined).
	 * 
	 * Stupid Note: Not more than 99 Layers, please!
	 * 
	 * @param reference
	 * @param zDistance
	 * @param layers
	 */
	public ParallaxLayerContainer(final Layer reference, final float zDistance,
			final Layer... layers) {
		this.reference = reference;

		// center = new Vector2f(reference.focus.getCoord());

		this.zDistance = zDistance;

		this.layers = new ArrayList<>(layers.length);
		z = new ArrayList<>(layers.length);
		final List<Layer> temp = new ArrayList<>(layers.length);

		for (final Layer layer : layers) {
			temp.add(layer);
		}

		while (!temp.isEmpty()) {
			Layer lay = temp.get(0);
			for (final Layer tlay : temp) {
				if (tlay.z >= lay.z)
					lay = tlay;
			}
			this.layers.add(lay);
			z.add(lay.z);
			temp.remove(lay);
		}
		this.layers.remove(reference);
	}

	/*---------------------
	 * 
	 * Methods
	 * 
	 *----------------------*/

	/**
	 * Sets the depth to a percent of the total depth (0 being the actual moving
	 * layer's depth).
	 * 
	 * @param layerNo
	 * @param percent
	 */
	public void setDepth(final int layerNo, final float percent) {
		layers.get(layerNo).setDepth(zDistance * percent, zDistance);
	}

	/**
	 * Sets the depth to a percent of the total depth (0 being the actual moving
	 * layer's depth).
	 * 
	 * @param layerNo
	 * @param percent
	 */
	public void setDepth(final Layer layer, final float percent) {
		layer.setDepth(zDistance * percent, zDistance);
	}

	@Override
	public void render(final Graphics g, final GameContainer container) {
		if (!parallax)
			reference.focus.render(g, container);
		for (int i = layers.size() - 1; i > layers.indexOf(reference); i--) {
			if (parallax) {
				g.pushTransform();
				layers.get(i).focus.render(g, container);
			}
			layers.get(i).render(g, container);
			if (parallax)
				g.popTransform();
		}

		g.pushTransform();

		reference.render(g, container);
		g.popTransform();

		for (int i = layers.indexOf(reference) - 1; i >= 0; i--) {
			g.pushTransform();
			layers.get(i).focus.render(g, container);
			layers.get(i).render(g, container);
			g.popTransform();
		}
	}

	@Override
	public void update(final int delta, final boolean condition) {

		thetas.set((reference.focus.coord.x() - center.x()) / zDistance,
				(reference.focus.coord.y() - center.y()) / zDistance);

		for (int i = layers.size() - 1; i > layers.indexOf(reference); i--) {
			if (parallax)
				layers.get(i).focus.coord.set(center.x()
						+ layers.get(i).getDepth() * thetas.x(), center.y()
						+ layers.get(i).getDepth() * thetas.y());
			layers.get(i).update(delta, condition);
		}

		reference.update(delta, condition);

		for (int i = layers.indexOf(reference) - 1; i >= 0; i--) {
			if (parallax)
				layers.get(i).focus.coord.set(center.x()
						+ layers.get(i).getDepth() * thetas.x(), center.y()
						+ layers.get(i).getDepth() * thetas.y());
			layers.get(i).update(delta, condition);
		}
	}

	@Override
	public void debug(final GameContainer container, final Graphics g,
			final boolean condition) {
		for (final Layer layer : layers) {
			layer.debug(container, g, condition);
		}
	}

	/*---------------------
	 * 
	 * Getters / Setters
	 * 
	 *----------------------*/

	public Layer getReference() {
		return reference;
	}

	public void setReference(final float reference) {
		this.reference = layers.get(z.indexOf(reference));
	}

	public float getzDistance() {
		return zDistance;
	}

	public void setzDistance(final float zDistance) {
		this.zDistance = zDistance;
	}

	public Layer getLayer(final float z) {
		return layers.get(this.z.indexOf(zDistance - z));
	}

}

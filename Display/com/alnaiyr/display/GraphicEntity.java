/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.display;

import java.util.ArrayList;
import java.util.List;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.renderables.Dimensionnable;
import com.alnaiyr.display.renderables.Renderable;
import com.alnaiyr.entity.Entity;
import com.alnaiyr.level.layer.Layer;
import com.alnaiyr.sfx.filters.SFX;

/**
 * an Entity which accept SFXs, and able to render itself
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public abstract class GraphicEntity extends Entity implements Renderable {

	private List<SFX>	sfxs;
	public Layer		lay;

	/*--------------------------
	 * 
	 * Constructors
	 * 
	 *------------------------*/

	/**
	 * 
	 * @param coord
	 * @param centered
	 * @param width
	 * @param height
	 */
	public GraphicEntity(final PlanVector coord, final boolean centered,
			final float width, final float height) {
		super(coord, centered, width, height);
	}

	/**
	 * 
	 * @param coord
	 * @param width
	 * @param height
	 */
	public GraphicEntity(final PlanVector coord, final float width,
			final float height) {
		super(coord, false, width, height);
	}

	/**
	 * 
	 * @param coord
	 * @param centered
	 * @param dimension
	 */
	public GraphicEntity(final PlanVector coord, final boolean centered,
			final Dimensionnable dimension) {
		super(coord, centered, dimension.width(), dimension.height());
	}

	/*--------------------------
	 * 
	 * Methods
	 * 
	 *------------------------*/

	public void addSFX(final SFX sfx) {
		if (sfxs == null)
			sfxs = new ArrayList<>(3);
		sfxs.add(sfx);
	}

	/**
	 * Updates everything that is related to a graphic effect or whatever only
	 * graphics related
	 * 
	 * @param delta
	 * @param condition
	 */
	public abstract void gUpdate(int delta, boolean condition);

	/*--------------------------
	 * 
	 * Methods
	 * 
	 *------------------------*/

	public List<SFX> getSFXEntries() {
		return sfxs == null ? new ArrayList<SFX>() : sfxs;
	}

}

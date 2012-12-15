package com.alnaiyr.generator.layers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.GraphicEntity;
import com.alnaiyr.entity.SelfAddingToLayer;
import com.alnaiyr.general.IV;
import com.alnaiyr.math.MathU;

public class LayerFactory {

	private static LayerFactory								instance;

	private int												depth				= 50;
	private int												reference;

	private PlanVector										referenceCoordinate	= IV.center
																						.clone();

	private Map<Integer, ArrayList<GraphicEntity>>			graphics			= new HashMap<>(
																						100);
	private final Map<Integer, ArrayList<GraphicEntity>>	outOfLayer			= new HashMap<>(
																						100);

	private LayerFactory() {
		super();
	}

	private <Type> void updatemap(final int layerNo,
			final Map<Integer, ArrayList<Type>> map) {
		final int check = layerNo;

		if (!map.containsKey(check)) {
			map.put(check, new ArrayList<Type>(10));
		}

	}

	/**
	 * Returns the instance of this singleton, if exist
	 * 
	 * @return instance
	 */
	public static LayerFactory getInstance() {
		if (LayerFactory.instance == null) {
			LayerFactory.instance = new LayerFactory();
			LayerFactory.instance.clearLayers();
		}
		return LayerFactory.instance;
	}

	@SuppressWarnings("boxing")
	public void addToLayer(final int layerNo, final GraphicEntity entity) {
		updatemap(layerNo, graphics);
		graphics.get(layerNo).add(entity);
	}

	public void addHUD(final int layerNo, final GraphicEntity entity) {
		updatemap(layerNo, outOfLayer);
		outOfLayer.get(layerNo).add(entity);
	}

	public void addToLayer(final int layerNo, final GraphicEntity... toRenders) {
		for (final GraphicEntity toRender : toRenders) {
			addToLayer(layerNo, toRender);
		}
	}

	public void spreadToLayers(final int from, final int to,
			final GraphicEntity... toRenders) {
		for (final GraphicEntity gra : toRenders) {
			addToLayer(MathU.random(from, to), gra);
		}
	}

	public void addSelfLayer(final int layerNo, final SelfAddingToLayer entity) {
		entity.addMyselfTo(layerNo);

	}

	public int getMaxSize() {

		return graphics.size();
	}

	public int getSizeOutOfParal() {
		return outOfLayer.size();
	}

	/**
	 * Clear all that is container in the layers
	 * 
	 */
	private void clearLayers() {
		graphics.clear();
	}

	public Map<Integer, ArrayList<GraphicEntity>> getGraphics() {
		return graphics;
	}

	public Map<Integer, ArrayList<GraphicEntity>> getOutOfLayer() {
		return outOfLayer;
	}

	public void setGraphics(
			final Map<Integer, ArrayList<GraphicEntity>> graphics) {
		this.graphics = graphics;
	}

	public void destroyInstance() {
		LayerFactory.instance = null;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(final int depth) {
		this.depth = depth;
	}

	public int getReference() {
		return reference;
	}

	public void setReference(final int reference) {
		this.reference = reference;
	}

	public PlanVector getReferenceCoordinate() {
		return referenceCoordinate;
	}

	public void setReferenceCoordinate(final PlanVector referenceCoordinate) {
		this.referenceCoordinate = referenceCoordinate;
	}

}

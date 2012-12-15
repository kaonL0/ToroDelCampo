package com.alnaiyr.generator.noise;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.util.Log;

import com.alnaiyr.math.functions.interpolations.Interpolation;
import com.alnaiyr.math.functions.interpolations.NoiseUtilities;
import com.alnaiyr.math.functions.interpolations.Smoothing;

public class PerlinNoiseGenerator {

	/* ********************************************
	 * 
	 * PERLIN GENERATOR
	 * 
	 * *********************************************
	 */

	/**
	 * Generates a Perlin noise relief, given all these variables:
	 * 
	 * @param startY
	 *            the starting height. Please note that this considering 0: the
	 *            higher, the lower the point
	 * @param tendancy
	 *            the ending height. Please note that this considering 0: the
	 *            higher, the lower the point
	 * @param ampl
	 *            the starting amplitude for the noise function, in px
	 * @param length
	 *            the length of the relief generated, expressed in px
	 * @param octaveNumber
	 *            the number of octaves we want
	 * @param pers
	 *            the persistence, that is how much the amplitude is lowered on
	 *            each iteration (amp^per)
	 * @param smooth
	 *            <p>
	 *            the smooth algorithm wanted, knowing:<br/>
	 *            <ul>
	 *            <li>0: Nothing</li>
	 *            <li>1: Local Average</li>
	 *            <li>2: Gauss</li>
	 *            <li>3: Global Average</li>
	 *            <li>4: Moving Average</li>
	 *            </ul>
	 *            <br/>
	 *            Note that you can put two numbers, first being for main noise,
	 *            second for harmonies, and if using smooth, you need a third
	 *            number for sigma
	 * @param interpolate
	 *            <p>
	 *            the interpolation algorithm wanted, knowing:<br/>
	 *            <ul>
	 *            <li>0: neighbor</li>
	 *            <li>1: linear</li>
	 *            <li>2: cosines</li>
	 *            <li>3: cubic</li>
	 *            </ul>
	 *            <br/>
	 *            Note that you can put two numbers, first being for main noise,
	 *            second for harmonies
	 *            </p>
	 * @param wave
	 *            length to consider between points. is expressed in % of length
	 *            (1>wl>0), and remember it's for the first octave = main curve
	 * @param firstinterpolate
	 *            (as it sounds like: what's joinind StartY and EndY.)
	 * @return the generated points of the Perlin curve as an HashMap
	 */

	public static HashMap<Integer, Float> generatePerlin(float startY,
			float tendancy, float ampl, int length, int octaveNumber,
			float wavelength, float pers, Smoothing smooth,
			Interpolation... interpolate) {

		ArrayList<HashMap<Integer, Float>> octaves = PerlinNoiseGenerator
				.createPerlinOctaves(octaveNumber, length, ampl, wavelength,
						pers, interpolate, smooth);

		HashMap<Integer, Float> result = new HashMap<>();

		for (int i = 0; i < octaves.size(); i++) {
			for (Integer key : octaves.get(i).keySet()) {
				if (result.containsKey(key))
					result.put(key, result.get(key) + octaves.get(i).get(key));
				else
					result.put(key, octaves.get(i).get(key));
			}
		}

		HashMap<Integer, Float> cachenoise = new HashMap<>(result);
		for (Integer key : cachenoise.keySet()) {
			float percent = (float) key / result.size();
			// first makes the noise start at startY
			result.put(key, result.get(key) + (startY - cachenoise.get(0)));
			// then makes it follow the line
			result.put(key, result.get(key) + tendancy * percent);

		}
		return result;

	}

	/*
	 * public static float[][] generatePerlin(float startY, float endY, float
	 * ampl, int length, int octaveNumber, float wavelength, float pers,
	 * Smoothing smooth, Interpolation... interpolate) {
	 * 
	 * ArrayList<HashMap<Integer, Float>> octaves =
	 * createPerlinOctaves(octaveNumber, length, ampl, wavelength, pers,
	 * interpolate, smooth);
	 * 
	 * HashMap<Integer, Float> result = new HashMap<Integer, Float>();
	 * 
	 * for (int i = 0; i < octaves.size(); i++) { for (Integer key :
	 * octaves.get(i).keySet()) { if (result.containsKey(key)) result.put(key,
	 * result.get(key) + octaves.get(i).get(key)); else result.put(key,
	 * octaves.get(i).get(key)); } }
	 * 
	 * HashMap<Integer, Float> cachenoise = new HashMap<Integer, Float>(result);
	 * for (Integer key : cachenoise.keySet()) { float percent = ((float) key) /
	 * result.size(); // first makes the noise start at startY result.put(key,
	 * result.get(key) + (startY - cachenoise.get(0))); // then makes it follow
	 * the line result.put(key, result.get(key) + (tendancy) * percent);
	 * 
	 * } return result;
	 * 
	 * }
	 */

	/**
	 * {@link #generatePerlin(float, float, int, int, float, float, float, int, int)}
	 * without first interpolate(=cosines) and smooth(=null)
	 * 
	 * @param startY
	 * @param endY
	 * @param ampl
	 * @param length
	 * @param octaveNumber
	 * @param wavelength
	 * @param pers
	 * @param interpolate
	 * @return
	 */

	public static HashMap<Integer, Float> generatePerlin(float startY,
			float endY, float ampl, int length, int octaveNumber,
			float wavelength, float pers, Interpolation... interpolate) {

		return PerlinNoiseGenerator.generatePerlin(startY, endY, ampl, length,
				octaveNumber, wavelength, pers, Smoothing.NULL, interpolate);
	}

	/**
	 * {@link #generatePerlin(float, float, int, int, float, float, float, int, int)}
	 * without first interpolate(=linear) and smooth(=dynamic average) and
	 * interpolate (=cos)
	 * 
	 * @param startY
	 * @param endY
	 * @param ampl
	 * @param length
	 * @param octaveNumber
	 * @param wavelength
	 * @param pers
	 * @param interpolate
	 * @return
	 */
	public static HashMap<Integer, Float> generatePerlin(float startY,
			float endY, float ampl, int length, int octaveNumber,
			float wavelength, float pers) {

		return PerlinNoiseGenerator.generatePerlin(startY, endY, ampl, length,
				octaveNumber, wavelength, pers, Smoothing.NULL,
				Interpolation.COSINUS);
	}

	/**
	 * {@link #generatePerlin(float, float, int, int, float, float, float, int, int)}
	 * without first interpolate(=linear) and smooth(=dynamic average) and
	 * interpolate (=cos) and wavelenght(10%)
	 * 
	 * @param startY
	 * @param endY
	 * @param ampl
	 * @param length
	 * @param octaveNumber
	 * @param pers
	 * @return
	 */
	public static HashMap<Integer, Float> generatePerlin(float startY,
			float endY, float ampl, int length, int octaveNumber, float pers) {

		return PerlinNoiseGenerator
				.generatePerlin(startY, endY, ampl, length, octaveNumber, 0.1f,
						pers, Smoothing.NULL, Interpolation.COSINUS);
	}

	/* ***************************************
	 * 
	 * Perlin Internals
	 * 
	 * *******************************************
	 */

	/**
	 * create an ArrayList containing a list of octaves for a Perlin noise
	 * 
	 * @param octaveNumber
	 * @param length
	 * @param ampl
	 * @param wavelength
	 * @param pers
	 * @param interpolate
	 * 
	 * @return the array to be used by
	 *         {@link #generatePerlin(float, float, float, int, int, float, float, int)}
	 */

	private static ArrayList<HashMap<Integer, Float>> createPerlinOctaves(
			int octaveNumber, int length, float ampl, float wavelength,
			float pers, Interpolation[] interpolate, Smoothing smooth) {

		Interpolation second = Interpolation.LINEAR;
		Interpolation first = interpolate[0];
		if (interpolate.length > 1)
			second = interpolate[1];

		// create noises list and set arguments
		ArrayList<HashMap<Integer, Float>> noises = new ArrayList<>();

		int period = (int) (length * wavelength);
		if (period < 1)
			Log.warn("Warning: period is too small: result will be null");

		// create all noise functions
		for (int i = 0; i < octaveNumber; i++) {
			if (i == 0)
				noises.add(PerlinNoiseGenerator.generatePerlinNoise(length,
						ampl, period, first, smooth));
			else
				noises.add(PerlinNoiseGenerator.generatePerlinNoise(length,
						ampl, period, second, smooth));

			ampl *= pers;
			period /= 2;// go to next octave
		}

		return noises;
	}

	/**
	 * Generates a simple noise function, to be used as an harmonic
	 * 
	 * @param ampl
	 * @param period
	 * @param interpolation
	 * @param smooth
	 * @return
	 */
	private static HashMap<Integer, Float> generatePerlinNoise(float length,
			float ampl, int period, Interpolation interpolation,
			Smoothing smooth) {

		HashMap<Integer, Float> noise = new HashMap<>();

		if (period >= 1) {

			int x = 0;
			while (x < length + period) {

				noise.put(x, (float) Math.random() * ampl - ampl / 2);
				x += period; // note any frequency below 1HZ doesn't make
				// any sense, as min. unit is 1px
			}
		}

		noise = NoiseUtilities.smooth(noise, smooth);

		noise = NoiseUtilities.interpolate(noise, interpolation);

		return noise;
	}

}

package com.alnaiyr.generator.noise;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.util.Log;

import com.alnaiyr.math.functions.interpolations.Interpolater1D;
import com.alnaiyr.math.functions.interpolations.Interpolation;
import com.alnaiyr.math.functions.interpolations.NoiseUtilities;
import com.alnaiyr.math.functions.interpolations.Smoothing;

/**
 * <p>
 * This class can return different points ArrayList, to be used for terrain
 * generation, following different algorithm.
 * </p>
 * <p>
 * Please note that two points can't have the same x coordinate for this kind of
 * noise.<br/>
 * To perform that effect, use the surface class, that can dig inside it.
 * </p>
 * 
 * @author IgoЯ
 * @version 1.5
 *          <p>
 *          <strong>Version change:</strong><br/>
 *          <ul>
 *          <li><em>Perlin noise upgraded</em></li>
 *          <li><em>Generation simplified</em></li>
 *          <li><em>Fixed exact perlin</em></li>
 *          <li><em>Better parameters organizing</em></li>
 *          <li><em>Now working with ENUMS</em></li>
 *          <li><em>Fixed starting point</em></li>
 *          <li><em>Added Midpoint algorithm (yay!)</em></li>
 *          </ul>
 *          </p>
 */

public class NoiseGenerator {

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

		ArrayList<HashMap<Integer, Float>> octaves = NoiseGenerator
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

		return NoiseGenerator.generatePerlin(startY, endY, ampl, length,
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

		return NoiseGenerator.generatePerlin(startY, endY, ampl, length,
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

		return NoiseGenerator
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
				noises.add(NoiseGenerator.generatePerlinNoise(length, ampl,
						period, first, smooth));
			else
				noises.add(NoiseGenerator.generatePerlinNoise(length, ampl,
						period, second, smooth));

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

	/* ***************************************
	 * 
	 * Midpoint
	 * 
	 * ********************************************
	 */

	/**
	 * Generates a midpoint noise, using these parameters:
	 * 
	 * @param startY
	 *            The starting height
	 * @param endY
	 *            the ending height
	 * @param length
	 *            the total length of the curve
	 * @param amplitude
	 *            the starting random amplitude
	 * @param middle
	 *            does the point is always the middle (TODO :so far not working,
	 *            should be true)
	 * @param pers
	 *            the persitance (meaning of much is divided the amplitude on
	 *            each octave, beetween 0 and 1 times 2)
	 * @param iteration
	 *            (how many time it has to iterate)
	 * @param smooth
	 *            (if we want to smooth the noise)
	 * @param interpolation
	 *            (the interpolation to use)
	 * @return
	 */
	public static HashMap<Integer, Float> generateMidPoint(float startY,
			float endY, int length, float amplitude, boolean middle,
			float pers, int iteration, Smoothing smooth,
			Interpolation interpolation) {

		if (middle)
			return NoiseGenerator.generateMiddle(startY, endY, length,
					amplitude, pers, iteration, smooth, interpolation);
		return null;
	}

	/* ***************************************
	 * 
	 * Midpoint internal methods
	 * 
	 * ********************************************
	 */

	/**
	 * Generates the noise for midpoint method
	 * 
	 * @param startY
	 * @param endY
	 * @param length
	 * @param amplitude
	 * @param pers
	 * @param iteration
	 * @param smooth
	 * @param interpolation
	 * @return
	 */
	private static HashMap<Integer, Float> generateMiddle(float startY,
			float endY, int length, float amplitude, float pers, int iteration,
			Smoothing smooth, Interpolation interpolation) {

		HashMap<Integer, Float> relief = new HashMap<>();

		int step = length / 2;
		float percent = 0;

		// put first points
		relief.put(0, startY);
		relief.put(length - 1, endY);

		// for each octaves...
		for (int i = 0; i < iteration; i++) {
			for (int j = step; j < length; j += 2 * step) {

				// decide a random value
				float value = (float) (Math.random() * amplitude - amplitude / 2);

				if (relief.containsKey(j))
					relief.put(j, relief.get(j) + value);
				else
					relief.put(j, endY * .5f + startY * .5f + value);
				percent = 0;

				// interpolate between added values, before
				for (int k = j - step; k < j; k++) {
					relief.put(k, Interpolater1D.interpolate(interpolation,
							relief.get(j - step), relief.get(j), percent));
					percent += (float) 1 / step;
					if (percent > 1)
						percent = 1;
				}

				percent = 0;

				// interpolate between added values, after
				for (int k = j; k < j + step; k++) {
					relief.put(k, Interpolater1D.interpolate(interpolation,
							relief.get(j), relief.get(j + step - 1), percent));
					percent += (float) 1 / step;
					if (percent > 1)
						percent = 1;
				}
			}
			amplitude *= pers;
			step /= 2;
			if (step < 1)
				break;
		}
		relief = NoiseUtilities.smooth(relief, smooth);
		return relief;
	}

}

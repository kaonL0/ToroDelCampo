/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.generator.color;

import org.newdawn.slick.Color;

import com.alnaiyr.math.MathU;

/**
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class ColorGenerator {

	/*---------------------
	 * 
	 * Variables
	 * 
	 *----------------------*/

	public enum ColorType {
		STAR, STARRED, STARBLUE, GREENPATTERN, BLUEPATTERN, REDPATTERN;
	}

	/*---------------------
	 * 
	 * Constructor
	 * 
	 *----------------------*/

	/*---------------------
	 * 
	 * Methods
	 * 
	 *----------------------*/

	public static Color generateRandomColor(ColorType type, int minAlpha,
			int maxAlpha) {

		switch (type) {
			case STAR:
				return ColorGenerator.generateStarColor(false, false, minAlpha,
						maxAlpha);
			case STARRED:
				return ColorGenerator.generateStarColor(true, false, minAlpha,
						maxAlpha);
			case STARBLUE:
				return ColorGenerator.generateStarColor(false, true, minAlpha,
						maxAlpha);
			case BLUEPATTERN:
				return ColorGenerator.generatePatternedColor(2, minAlpha,
						maxAlpha);
			case GREENPATTERN:
				return ColorGenerator.generatePatternedColor(1, minAlpha,
						maxAlpha);
			case REDPATTERN:
				return ColorGenerator.generatePatternedColor(0, minAlpha,
						maxAlpha);
		}

		return null;
	}

	public static Color generateRandomColor(Color from, Color to, int minAlpha,
			int maxAlpha) {
		return new Color(MathU.random(from.r, to.r),
				MathU.random(from.g, to.g), MathU.random(from.b, to.b),
				MathU.random(minAlpha, maxAlpha));
	}

	private static Color generateStarColor(boolean red, boolean blue,
			int minAlpha, int maxAlpha) {

		if (!blue && !red) {
			float mainColor = MathU.random(0f, 1f);

			if (mainColor > 0.5) {
				return new Color(255, MathU.random(0, 255), 0, MathU.random(
						minAlpha, maxAlpha));
			}
			return new Color(0, MathU.random(50, 230), 255, MathU.random(
					minAlpha, maxAlpha));
		} else if (blue) {
			return new Color(0, MathU.random(50, 230), 255, MathU.random(
					minAlpha, maxAlpha));
		} else
			return new Color(255, MathU.random(0, 255), 0, MathU.random(
					minAlpha, maxAlpha));
	}

	public static Color generatePatternedColor(int i, int minAlpha, int maxAlpha) {
		switch (i) {
			case 0:
				return new Color(255, MathU.random(0, 190), 0, MathU.random(
						minAlpha, maxAlpha));
			case 1:
				return new Color(MathU.random(0, 150), 255,
						MathU.random(0, 145), MathU.random(minAlpha, maxAlpha));
			case 2:
				return new Color(0, MathU.random(0, 255), 255, MathU.random(
						minAlpha, maxAlpha));
		}
		return null;
	}

	/*---------------------
	 * 
	 * Getters / Setters
	 * 
	 *----------------------*/
}

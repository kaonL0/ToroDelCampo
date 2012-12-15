package com.alnaiyr.utilities.font;

import org.newdawn.slick.AngelCodeFont;

public class FontU {

	/* ****************
	 * 
	 * Methods
	 * 
	 * ***************
	 */

	public static int getLongest(AngelCodeFont font, String[] messages) {
		String longest = "";
		for (String string : messages) {
			if (string.length() > longest.length())
				longest = string;
		}
		return font.getWidth(longest);
	}

	/**
	 * 
	 * @param string
	 * @return a non destructive upper-cased-first-lettered-String
	 */
	public static String firstLetterToUpperCase(String string) {
		return string.substring(0, 1).toUpperCase()
				+ string.substring(1, string.length());
	}

	/* ****************
	 * 
	 * Getters / Setters
	 * 
	 * ***************
	 */

}

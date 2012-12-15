/**
 * Created by Al Naiyr Studio , all right reserved. Créé par Al Naiyr, tous
 * droits réservés
 */
package com.alnaiyr.utilities.debug;

/**
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 * @param <T>
 */
public class ChangeDebug<T> {
	/*
	 * --------------------------
	 * 
	 * Variables
	 * 
	 * -----------------------------
	 */

	T toCheck;

	int time = 1;

	/*-----------------------------
	 *
	 * Constructors
	 *
	 *------------------------------*/

	/**
	 * @param toCheck
	 */
	public ChangeDebug(T toCheck) {
		this.toCheck = toCheck;
	}

	/*------------------------------
	 *
	 * Methods
	 *
	 *-------------------------------*/

	public void check(T value) {
		if (!value.equals(toCheck)) {
			DB.test(value
					+ (time != 1 ? "(last called " + time + " times)" : ""));
			toCheck = value;
			time = 1;

		} else
			time++;
	}

	/*
	 * ------------------------------
	 * 
	 * Getters / Setters
	 * 
	 * -------------------------------
	 */

	/**
	 * @return the toCheck
	 */
	public T getToCheck() {
		return toCheck;
	}

	/**
	 * @param toCheck
	 *            the toCheck to set
	 */
	public void setToCheck(T toCheck) {
		this.toCheck = toCheck;
	}

}

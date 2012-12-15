package com.alnaiyr.models.patterns.singleton;

/** Exemple of application for the singleton
 * @author IgoЯ
 * @version 1.0
 * <p><Strong>Version Change:</Strong>
 *		<ul>
 *			<li><em>No Changes</em></li>
 *		</ul>
 */
public class Singleton {

    /* ***********************
	 * 
	 * Variables
	 * 
	 * ************************** */

	private static volatile Singleton	instance	= null;

	/* ***********************
	 * 
	 * Constructor
	 * 
	 * ************************** */

	protected Singleton() {
		super();
	}

	/* ***********************
	 * 
	 * Method
	 * 
	 * ************************** */

	/**
	 * 
	 */
	public static void destroy() {

	}

	/* ***********************
	 * 
	 * Getters
	 * 
	 * ************************** */
	/** Returns the instance of this singleton, if exist 
	 * @return instance*/
	public static Singleton getInstance() {
		if (instance == null) {
			synchronized (Singleton.class) {
				if (Singleton.instance == null) {
					Singleton.instance = new Singleton();
				}
			}
		}
		return instance;
	}

}

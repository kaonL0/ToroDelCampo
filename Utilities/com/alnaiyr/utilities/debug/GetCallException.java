package com.alnaiyr.utilities.debug;

public class GetCallException extends Exception {

	/**
     * 
     */
	private static final long serialVersionUID = -2888874940055946978L;

	/* ****************
	 * 
	 * Constructors
	 * 
	 * ***************
	 */

	public GetCallException() {
		System.out.print("\t called by " + getStackTrace()[1].getClassName()
				+ "." + getStackTrace()[1].getMethodName() + " \n");
	}

	/* ****************
	 * 
	 * Methods
	 * 
	 * ***************
	 */
	/* ****************
	 * 
	 * Getters / Setters
	 * 
	 * ***************
	 */

}

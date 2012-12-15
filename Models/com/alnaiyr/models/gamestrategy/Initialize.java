package com.alnaiyr.models.gamestrategy;

/** an interface describing everything able to initialize itself
 * 
 * @author IgoЯ
 *@version 1.0
 * <p>
 *          <strong> Version change:</strong>
 *          <ul>
 *          <li><em> Functional</em></li>
 *          </ul>
 *          </p> */

public interface Initialize {
	
	/** Initialize data, should be used in a game state (slick) */
	 public void init();

	
}

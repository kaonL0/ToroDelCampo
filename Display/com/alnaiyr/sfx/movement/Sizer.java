package com.alnaiyr.sfx.movement;

/** Describes every object able to measure size and compare it
 * 
 * @author IgoЯ
 * @version 1.0
 * <p><Strong>Version Change:</Strong>
 *		<ul>
 *			<li><em>Works</em></li>
 *		</ul>
 * @param <Type> 
 */
public interface Sizer<Type> {

	/** Is on object bigger than the other? 
	 * 
	 * @param test
	 * @param compare
	 * @return true if test is bigger than compare
	 */
	public boolean isBigger(Type test,Type compare);
	
	/** Is on object smaller than the other? 
	 * 
	 * @param test
	 * @param compare
	 * @return true if test is smaller than compare
	 */
	public boolean isSmaller(Type test,Type compare);
	
	
}
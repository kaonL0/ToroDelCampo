package com.alnaiyr.display.camera.utilities;

/** Describes every object that is able to detect something entering a zone
 * 
 * @author IgoЯ
 * @version 1.1
 * <p><Strong>Version Change:</Strong>
 *		<ul>
 *			<li><em>Working</em></li>
 *			<li><em>added method</em></li>
 *		</ul>
 * @param <Type> 
 */
public interface Detector<Type> {

	/** Detects an object entering an other object
	 * 
	 * @param toDetect
	 * @param detector
	 * @return true if detected
	 */
	public boolean detect(Type toDetect,Type detector);
	
	/** Detects an object entering an other object with a margin
	 * 
	 * @param toDetect
	 * @param detector
	 * @param margin
	 * @return  true if detected
	 */
	
	public boolean detect(Type toDetect,Type detector, float margin);
	
	/** Detects an object with a margin entering an other object 
	 * @since 1.1
	 * @param toDetect
	 * @param detector
	 * @param margin
	 * @return  true if detected
	 */
	
	public boolean detect(Type toDetect, float margin,Type detector);
	
	
	/** Detects an object with a margin entering an other object with a margin
	 * 
	 * @param toDetect
	 * @param margintd 
	 * @param detector
	 * @param margind 
	 * @return  true if detected
	 */	
	public boolean detect(Type toDetect,float margintd, Type detector, float margind);
}

package com.alnaiyr.math.geom;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

import com.alnaiyr.display.camera.utilities.Detector;
import com.alnaiyr.sfx.movement.Sizer;

/**
 * Contains some useful function to use along with shapes
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>Works</em></li>
 *          </ul>
 */
public class ShapeDetection implements Detector<Shape>, Sizer<Shape> {

	/* ***************
	 * 
	 * Constructor
	 * 
	 *****************************/
	
	
	/* ********************
	 * 
	 * Detector
	 * 
	 * ********************** */

	/* (non-Javadoc)
	 * 
	 * @see
	 * com.Al_Nair.render.camera.utilities.Detector#detect(java.lang.Object,
	 * java.lang.Object) */
	@Override
	public boolean detect(Shape toDetect, Shape detector) {
		return (detector.intersects(toDetect) | detector.contains(toDetect)) ? true : false;
	}

	/* (non-Javadoc)
	 * 
	 * @see
	 * com.Al_Nair.render.camera.utilities.Detector#detect(java.lang.Object,
	 * java.lang.Object, float) */
	@Override
	public boolean detect(Shape toDetect, Shape detector, float margin) {
		Shape detect = detector.transform(Transform.createScaleTransform((detector.getWidth() + margin) / detector.getWidth(),
				(detector.getHeight() + margin) / detector.getHeight()));
		return (detect.intersects(toDetect) | detect.contains(toDetect)) ? true : false;
	}

	/* (non-Javadoc)
	 * 
	 * @see
	 * com.Al_Nair.render.camera.utilities.Detector#detect(java.lang.Object,
	 * float, java.lang.Object) */
	@Override
	public boolean detect(Shape toDetect, float margin, Shape detector) {
		Shape tdetect = toDetect.transform(Transform.createScaleTransform((toDetect.getWidth() + margin) / toDetect.getWidth(),
				(toDetect.getHeight() + margin) / toDetect.getHeight()));
		return (detector.intersects(tdetect) | detector.contains(tdetect)) ? true : false;
	}

	/* (non-Javadoc)
	 * 
	 * @see
	 * com.Al_Nair.render.camera.utilities.Detector#detect(java.lang.Object,
	 * float, java.lang.Object, float) */
	@Override
	public boolean detect(Shape toDetect, float margintd, Shape detector, float margind) {
		Shape detect = detector.transform(Transform.createScaleTransform((detector.getWidth() + margind) / detector.getWidth(),
				(detector.getHeight() + margind) / detector.getHeight()));
		Shape tdetect = toDetect.transform(Transform.createScaleTransform((toDetect.getWidth() + margintd) / toDetect.getWidth(),
				(toDetect.getHeight() + margintd) / toDetect.getHeight()));
		return (detect.intersects(tdetect) | detect.contains(tdetect)) ? true : false;
	}

	/* ********************
	 * 
	 * Detector
	 * 
	 * ********************** */

	/*
	 * (non-Javadoc)
	 * @see com.Al_Nair.render.camera.utilities.Sizer#isBigger(java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean isBigger(Shape test, Shape compare) {

		return (test.getBoundingCircleRadius() > compare.getBoundingCircleRadius()) ? true : false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.Al_Nair.render.camera.utilities.Sizer#isSmaller(java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean isSmaller(Shape test, Shape compare) {
		return (test.getBoundingCircleRadius() > compare.getBoundingCircleRadius()) ? false : true;
	}

}

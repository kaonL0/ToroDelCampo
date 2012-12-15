/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.display.camera.sfxs;

import org.newdawn.slick.geom.Vector2f;

import com.alnaiyr.ai.updater.condition.Conditionable;
import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.coordinates.utilities.VecU;
import com.alnaiyr.display.camera.Focus;
import com.alnaiyr.math.MathU;
import com.alnaiyr.math.geom.shape.segments.LinearSegment;
import com.alnaiyr.math.geom.shape.segments.Segment;
import com.alnaiyr.math.numbers.advanced.MinMax;
import com.alnaiyr.math.numbers.advanced.PercentMM;

/**
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class FocusSoftShaker implements FocusSFX {

	/*---------------------
	 * 
	 * Variables
	 * 
	 *----------------------*/

	private final MinMax		xAmpMM;
	private final MinMax		yAmpMM;

	private PlanVector			previousCoord;

	private float				percent;

	private final Conditionable	luck;

	private final float			speed;

	private final Segment		seg;

	private final Focus			foc;

	private boolean				activated	= true;

	/*---------------------
	 * 
	 * Constructor
	 * 
	 *----------------------*/

	public FocusSoftShaker(final MinMax xAmpMM, final MinMax yAmpMM,
			final float speed, final Conditionable luck, final Focus foc) {
		super();
		this.xAmpMM = xAmpMM;
		this.yAmpMM = yAmpMM;
		this.luck = luck;
		this.foc = foc;
		this.speed = speed;
		previousCoord = new Vector2f(foc.getCoord());

		getLocalMove();
		seg = new LinearSegment(previousCoord, VecU.sum(foc.getCoord(),
				localMove));
	}

	/*---------------------
	 * 
	 * Methods
	 * 
	 *----------------------*/

	private final Vector2f	localMove	= new Vector2f();

	@Override
	public void update(final int delta, final boolean condition) {
		if (luck.getCondition() || percent >= 1) {
			getLocalMove();
			seg.getFrom().set(foc.getCoord());
			percent = 0;
		}

		seg.getTo().set(VecU.sum(previousCoord, localMove));

		seg.bindCoordinate(percent, foc.getCoord());

		percent += speed;
		percent = PercentMM.instance.correctValue(percent);

	}

	private void getLocalMove() {
		localMove.set(MathU.random(xAmpMM.getStart(), xAmpMM.getEnd()),
				MathU.random(yAmpMM.getStart(), yAmpMM.getEnd()));
	}

	@Override
	public boolean isDone() {

		return !activated;
	}

	@Override
	public void setActivate(final boolean activate) {
		activated = activate;

	}

	public void updatePreviousCoord() {
		previousCoord = foc.getCoord();
	}

	/*---------------------
	 * 
	 * Getters / Setters
	 * 
	 *----------------------*/
}

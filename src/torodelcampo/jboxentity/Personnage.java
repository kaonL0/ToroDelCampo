package torodelcampo.jboxentity;

import org.jbox2d.dynamics.Body;


import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.renderables.DimensionDrawable;

public class Personnage extends JboxEntity {

	public Personnage(final PlanVector coord, final Body body,
			final DimensionDrawable drawable) {
		super(coord, drawable);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void gUpdate(final int delta, final boolean condition) {
		// TODO Auto-generated method stub

	}

}

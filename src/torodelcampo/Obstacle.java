package torodelcampo;

import org.jbox2d.dynamics.Body;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.renderables.DimensionDrawable;

public class Obstacle extends JboxEntity {

	public Obstacle(final PlanVector coord, final Scene scene, final Body body,
			final DimensionDrawable drawable) {
		super(coord, drawable);

	}

	@Override
	public void gUpdate(final int delta, final boolean condition) {
		// TODO Auto-generated method stub

	}

}

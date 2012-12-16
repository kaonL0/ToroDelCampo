package torodelcampo;

import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.dynamics.Body;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.renderables.DimensionDrawable;

public class Mur extends Obstacle {

	public Mur(PlanVector coord, DebugDraw debug, Body body,
			DimensionDrawable drawable) {
		super(coord, debug, body, drawable);
		// TODO Auto-generated constructor stub
	}
}

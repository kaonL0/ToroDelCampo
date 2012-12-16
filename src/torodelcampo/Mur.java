package torodelcampo;

import org.jbox2d.dynamics.Body;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.renderables.DimensionDrawable;

public class Mur extends Obstacle {

	public Mur(final PlanVector coord, final Scene scene, final Body body,
			final DimensionDrawable drawable) {
		super(coord, body, drawable);

	}

	@Override
	public void render(final Graphics g, final GameContainer container) {
	}

}

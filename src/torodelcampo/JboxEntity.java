package torodelcampo;

import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.dynamics.Body;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.GraphicEntity;
import com.alnaiyr.display.renderables.DimensionDrawable;

public abstract class JboxEntity extends GraphicEntity {

	DebugDraw			debug;
	Body				body;
	DimensionDrawable	drawable;

	public JboxEntity(final PlanVector coord, final DebugDraw debug,
			final Body body, final DimensionDrawable drawable) {
		super(coord, drawable.width(), drawable.height());
		this.body = body;
		this.drawable = drawable;
	}

	@Override
	public int width() {
		return drawable.width();
	}

	@Override
	public int height() {
		return drawable.height();
	}

	@Override
	public void render(final Graphics g, final GameContainer container) {
		drawable.draw(debug.getWorldToScreen(body.getWorldCenter()));

	}
}

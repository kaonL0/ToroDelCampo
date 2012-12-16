package torodelcampo.jboxentity;

import org.jbox2d.dynamics.Body;

import torodelcampo.scene.SceneCreator;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.impl.basic.DrawEntity;
import com.alnaiyr.display.renderables.DimensionDrawable;

public abstract class JboxEntity extends DrawEntity {

	public Body			body;
	DimensionDrawable	drawable;

	public JboxEntity(final PlanVector coord, final DimensionDrawable drawable) {
		super(coord, drawable);
		this.drawable = drawable;
	}

	@Override
	public void gUpdate(final int delta, final boolean condition) {
		// super.gUpdate(delta, condition);

		if (body != null)
			coord.set(SceneCreator.debug.getWorldToScreen(body.getWorldCenter()));
	}

}

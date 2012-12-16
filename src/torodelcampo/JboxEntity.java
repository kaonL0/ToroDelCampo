package torodelcampo;

import org.jbox2d.dynamics.Body;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.impl.basic.DrawEntity;
import com.alnaiyr.display.renderables.DimensionDrawable;

public abstract class JboxEntity extends DrawEntity {

	Body				body;
	DimensionDrawable	drawable;

	public void JBoxEntity() {

	}	
	
	public JboxEntity(final PlanVector coord, final DimensionDrawable drawable) {
		super(coord, drawable);
		this.drawable = drawable;
	}

	@Override
	public void gUpdate(final int delta, final boolean condition) {
		super.gUpdate(delta, condition);
		coord.set(SceneCreator.debug.getWorldToScreen(body.getWorldCenter()));
	}

}

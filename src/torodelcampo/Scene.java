package torodelcampo;

import java.util.Arrays;
import java.util.List;

import org.jbox2d.collision.shapes.EdgeShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.coordinates.container.CoordinateContainerImpl;
import com.alnaiyr.display.GraphicEntity;
import com.alnaiyr.display.renderables.render.rewrite.Image;
import com.alnaiyr.math.geom.shape.segments.Segment;

public class Scene extends GraphicEntity {

	List<Personnage>	personnages;
	List<Obstacle>		obstacles;
	Image				back;

	List<Segment>		segs;

	public Scene(final Image background, final Segment... segment) {
		super(new Vec2(), false, background);
		back = background;
		segs = Arrays.asList(segment);

	}

	public void init(final PlanVector vec, final Personnage... personnage) {
		coord = new CoordinateContainerImpl(vec);
		for (final Segment seg : segs) {
			final BodyDef bd = new BodyDef();
			bd.type = BodyType.STATIC;

			final Body bod = SceneCreator.world.createBody(bd);

			final EdgeShape sh = new EdgeShape();
			sh.set((Vec2) SceneCreator.debug.getScreenToWorld(seg.getFrom()
					.addLocal(vec)), (Vec2) SceneCreator.debug
					.getScreenToWorld(seg.getTo().addLocal(vec)));
			bod.createFixture(sh, 0);
		}

		personnages = Arrays.asList(personnage);

	}

	@Override
	public void render(final Graphics g, final GameContainer container) {
		back.draw(coord);
		for (final Personnage pers : personnages) {
			pers.render(g, container);
		}
	}

	@Override
	public int width() {
		return 0;
	}

	@Override
	public int height() {
		return 0;
	}

	@Override
	public void gUpdate(final int delta, final boolean condition) {
		for (final Personnage pers : personnages) {
			pers.gUpdate(delta, true);
		}

	}

}
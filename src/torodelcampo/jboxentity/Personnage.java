package torodelcampo.jboxentity;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.contacts.Contact;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import torodelcampo.scene.SceneCreator;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.renderables.render.rewrite.Animation;
import com.alnaiyr.ressources.spritesheet.ToroSpriteSheet;

public class Personnage extends JboxEntity {

	private final Animation	perso;

	public Personnage(final PlanVector coord, final float scale) {
		super(coord, new Animation(ToroSpriteSheet.HUMAIN.spritesheet, 50)
				.getScaledCopy(scale));
		perso = new Animation(ToroSpriteSheet.HUMAIN.spritesheet, 50)
				.getScaledCopy(scale);
		perso.setAutoUpdate(false);

		final FixtureDef fd = new FixtureDef();
		fd.restitution = 0f;
		fd.friction = 1f;
		fd.density = 2f;

		final BodyDef bd = new BodyDef();
		bd.type = BodyType.DYNAMIC;
		bd.position = (Vec2) SceneCreator.debug.getScreenToWorld(coord.x(),
				coord.y());

		final Body pers = SceneCreator.world.createBody(bd);

		final CircleShape shape = new CircleShape();
		shape.setRadius(.4f);

		fd.shape = shape;
		pers.createFixture(fd);

		body = pers;

	}

	@Override
	public void render(final Graphics g, final GameContainer container) {
		perso.draw(coord);
	}

	@Override
	public void gUpdate(final int delta, final boolean condition) {
		super.gUpdate(delta, true);

		if (body == null) {
			perso.stop();
		} else {
			for (Contact cont = SceneCreator.world.getContactList(); cont != null; cont = cont
					.getNext()) {
				if (cont.m_fixtureA.m_body == SceneCreator.taureau.body
						&& cont.m_fixtureB.m_body == body) {
					SceneCreator.world.destroyBody(body);
					super.body = null;
					SceneCreator.score++;
					break;
				}

			}
			perso.update(delta);
		}

	}

}

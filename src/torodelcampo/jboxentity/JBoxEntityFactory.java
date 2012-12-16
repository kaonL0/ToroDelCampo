package torodelcampo.jboxentity;

import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;


public class JBoxEntityFactory {

	// public JboxEntity getJBoxEntity(int typeObjet) throws Exception {
	public void getJBoxEntity(final TypeObjet typeObjet) throws Exception {
		switch (typeObjet) {
		// case JBoxEntityFactory.HOMME: return ((JboxEntity) new Personnage(0,
		// 0, false, 0f, 1f, 2f)); break;
		// case JBoxEntityFactory.FEMME: return ((JboxEntity) new Personnage(0,
		// 0, false, 0f, 1f, 2f)); break;
		// case JBoxEntityFactory.ENFANT: return ((JboxEntity) new Personnage(0,
		// 0, false, 0f, 1f, 2f)); break;
		// case JBoxEntityFactory.CAISSE: return ((JboxEntity) new Obstacle(0,
		// 0, true)); break;
		// case JBoxEntityFactory.BARRIERE: return ((JboxEntity) new Obstacle(0,
		// 0, true)); break;
			default:
				// ((JboxEntity) new Personnage(x, y, isStatic, restitution,
				// friction, density));
				break;
		}
	}

	public void create() {
		final FixtureDef fd = new FixtureDef();
		fd.restitution = 0f;
		fd.friction = 1f;
		fd.density = 2f;

		final BodyDef bd = new BodyDef();
		bd.type = BodyType.DYNAMIC;
		// bd.position = (Vec2) debug.getScreenToWorld(toro.lay.focus.coord.x(),
		// toro.lay.focus.coord.y());

		bd.angle = 5f;
	}
}

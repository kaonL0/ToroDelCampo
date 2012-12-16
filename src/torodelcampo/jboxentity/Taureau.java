package torodelcampo.jboxentity;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

import torodelcampo.scene.SceneCreator;

import com.alnaiyr.commands.Commands;
import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.renderables.render.rewrite.Animation;
import com.alnaiyr.general.IV;
import com.alnaiyr.ressources.spritesheet.ToroSpriteSheet;

public class Taureau extends JboxEntity {

	// Direction direction;
	private final Animation	toro;

	public Taureau(final PlanVector coord) {
		super(coord, new Animation(ToroSpriteSheet.TORO.spritesheet, 200)
				.getScaledCopy(.3f));
		toro = (Animation) super.drawable;

		final FixtureDef fd = new FixtureDef();
		fd.restitution = 0f;
		fd.friction = 1f;
		fd.density = 2f;

		final BodyDef bd = new BodyDef();
		bd.type = BodyType.DYNAMIC;
		bd.position = (Vec2) SceneCreator.debug.getScreenToWorld(coord.x(),
				coord.y());
		bd.angle = 0f;
		final Body tor = SceneCreator.world.createBody(bd);

		// BodyDef bodyDef = new BodyDef();

		final PolygonShape shape = new PolygonShape();

		shape.setAsBox(.6f, 1.2f);

		fd.shape = shape;
		tor.createFixture(fd);
		body = tor;

		/*
		 * 
		 * final FixtureDef fdTete = new FixtureDef(); fdTete.restitution = 0f;
		 * fdTete.friction = 1f; fdTete.density = 2f;
		 * 
		 * final BodyDef bdTete = new BodyDef(); bdTete.type = BodyType.DYNAMIC;
		 * bdTete.position = (Vec2)
		 * SceneCreator.debug.getScreenToWorld(coord.x(), coord.y()-30);
		 * bdTete.angle = 0f; tete = SceneCreator.world.createBody(bdTete);
		 * 
		 * //final PolygonShape shape = new PolygonShape(); final CircleShape
		 * shapeTete = new CircleShape(); //shape.setAsBox(1.8f, 1.8f);
		 * shapeTete.setRadius(0.5f);
		 * 
		 * fdTete.shape = shapeTete; tete.createFixture(fdTete);
		 * 
		 * RevoluteJointDef jointDef = new RevoluteJointDef(); //Vec2 basTete =
		 * tete.getWorldCenter(); //basTete = basTete.add(new Vec2(1,1));
		 * 
		 * Vec2 anchorTete = tete.getLocalCenter(); // new Vec2(0, 0); Vec2
		 * anchorCorps = body.getLocalCenter(); //new Vec2(0, 0);
		 * jointDef.localAnchorA = anchorTete; jointDef.localAnchorB =
		 * anchorCorps; jointDef.initialize(body, tete, tete.getWorldCenter());
		 * 
		 * //jointDef.lowerAngle = -0.25f * 3.14f; // -45 degrees
		 * //jointDef.upperAngle = 0.25f * 3.14f; // 45 degrees
		 * //jointDef.enableLimit = true; //jointDef.maxMotorTorque = 10.0f;
		 * //jointDef.motorSpeed = 0.0f; //jointDef.enableMotor = true;
		 * SceneCreator.world.createJoint(jointDef);
		 * 
		 * tete.setLinearVelocity(new Vector2f(0, 16f));
		 */
		body.setLinearVelocity(new Vector2f(0, 16f));
		body.setFixedRotation(true);

	}

	public void moveUp() {

	}

	public void moveLeft() {

	}

	@Override
	public void gUpdate(final int delta, final boolean condition) {
		super.gUpdate(delta, condition);

		// coord.set(SceneCreator.debug.getWorldToScreen(tete.getWorldCenter()));

		toro.update(delta);

		body.setLinearVelocity(new Vector2f(0, 8f));

		// tete.setLinearVelocity(new Vector2f(0, 4f));
		// this.tete.setLinearVelocity(vitesse);

		final GameContainer container = IV.container;
		final Input input = container.getInput();
		if (input.isKeyDown(Commands.input.KEY_LEFT))
			move(Commands.input.KEY_LEFT);
		if (input.isKeyDown(Commands.input.KEY_RIGHT))
			move(Commands.input.KEY_RIGHT);

	}

	public void move(final int direction) {
		// box
		// System.out.println("");
		Vec2 vitesse = body.getLinearVelocity();
		// Vec2 vitesseTete = this.body.getLinearVelocity();

		final float pasLateral = 4.0f;
		if (direction == Commands.input.KEY_RIGHT) {
			// vitesse.set(vitesse.x+pasLateral, vitesse.y);
			vitesse = (Vec2) vitesse.addLocal(pasLateral, 0);
			// vitesseTete = (Vec2) vitesseTete.addLocal(pasLateral, 0);
		} else if (direction == Commands.input.KEY_LEFT) {
			// vitesse.set(vitesse.x-pasLateral, vitesse.y);
			vitesse = (Vec2) vitesse.addLocal(-pasLateral, 0);
			// vitesseTete = (Vec2) vitesseTete.addLocal(-pasLateral, 0);
		}

		body.setLinearVelocity(vitesse);
		// this.tete.setLinearVelocity(vitesse);

		// PlanVector pos2 = this.coord.getValue();
		// PlanVector pos = this.body.getPosition();
	}

}

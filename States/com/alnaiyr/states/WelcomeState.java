package com.alnaiyr.states;

import java.io.IOException;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

import com.alnaiyr.ai.updater.condition.RandomChangingCondition;
import com.alnaiyr.ai.updater.condition.RandomLuckCondition;
import com.alnaiyr.ai.updater.condition.TrueCondition;
import com.alnaiyr.commands.CommandType;
import com.alnaiyr.commands.Commands;
import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.coordinates.dynamic.Origin;
import com.alnaiyr.display.camera.sfxs.FocusSoftShaker;
import com.alnaiyr.display.impl.advanced.NullEntity;
import com.alnaiyr.display.impl.advanced.ParticleEntity;
import com.alnaiyr.display.impl.basic.DrawEntity;
import com.alnaiyr.display.renderables.render.rewrite.Image;
import com.alnaiyr.general.IV;
import com.alnaiyr.generator.layers.LayerFactory;
import com.alnaiyr.math.geom.shape.segments.LinearSegment;
import com.alnaiyr.math.numbers.advanced.MinMaxImpl;
import com.alnaiyr.math.numbers.profiles.behaves.limit.LimitBehaves;
import com.alnaiyr.math.numbers.profiles.behaves.release.ReleaseBehaves;
import com.alnaiyr.physics.movement.itinerary.Itinary;
import com.alnaiyr.sfx.filters.types.transition.TransitionSFXConfigImpl;
import com.alnaiyr.sfx.filters.types.transition.config.FadeSFXConfig;
import com.alnaiyr.sfx.filters.types.transition.impl.AutoScaleSFX;
import com.alnaiyr.sfx.filters.types.transition.impl.FadeSFX;
import com.alnaiyr.sfx.rewrite.FadeInTransition;
import com.alnaiyr.sfx.rewrite.FadeOutTransition;
import com.alnaiyr.uinterface.boxes.impl.text.TextBox;
import com.alnaiyr.uinterface.boxes.listeners.BoxListener;
import com.alnaiyr.uinterface.boxes.listeners.BoxListeners;

/**
 * Welcoming screen, that's all
 * 
 * @author IgoЯ
 * @version 1.0
 */
public class WelcomeState extends State {

	/*---------------------------------
	 * 
	 * User interface
	 * 
	 *-------------------------------------*/

	private DrawEntity			title;

	private TextBox				welcomeBox;

	/*---------------------------------
	 * 
	 * Containers
	 * 
	 *-------------------------------------*/

	/*---------------------------------
	 * 
	 * Logic
	 * 
	 *-------------------------------------*/

	/*---------------------------------
	 * 
	 *Movements
	 * 
	 *-------------------------------------*/

	private final PlanVector	or	= new Vector2f(IV.getWidth() * -0.8f,
											IV.getHeight() * .5f);
	private final PlanVector	to	= new Vector2f(IV.getWidth() * 1.8f,
											IV.getHeight() * .5f);

	private final Itinary		it	= new Itinary(new LinearSegment(or, to));

	private Image				background;
	private Image				dune1;

	private ParticleEntity		particles;

	/*---------------------------------
	 * 
	 * Rest
	 * 
	 *-------------------------------------*/

	/**
	 * @param id
	 */
	public WelcomeState(final byte id) {
		super(id);
	}

	@Override
	public void initLayerable(final GameContainer container,
			final StateBasedGame arg1) throws SlickException {

		it.limit = LimitBehaves.LOOPFRONT;
		try {
			particles = new ParticleEntity(
					new Vector2f(.5f, .5f, true),
					ParticleIO
							.loadConfiguredSystem("data/Animations/Particules/sand1.xml"));
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		configureMessage();

		addToLayer();

		Log.debug("done!");

		CommandType.MENU.setActivated(true);
	}

	/*---------------------------------------------------------------
	 * 
	 * 
	 * SUB FUNCTIONS
	 * 
	 * 
	 *--------------------------------------------------------------------*/

	private void configureMessage() {

		welcomeBox = new TextBox(new Origin(0.5f, 0.8f, true),
				"Press Enter to Start!", true);

		final FadeSFXConfig conf = new FadeSFXConfig(Color.black, .0015f,
				LimitBehaves.LOOPBACK, ReleaseBehaves.NULL);
		final TransitionSFXConfigImpl scale = new TransitionSFXConfigImpl(
				.003f, LimitBehaves.BLOCKON, ReleaseBehaves.TOSTART);
		scale.setMinMax(new MinMaxImpl(1, 1.4f));

		welcomeBox.addSFX(new AutoScaleSFX(welcomeBox.coord.getValue(),
				new BoxListener(welcomeBox, BoxListeners.MOUSEOVER),
				welcomeBox, scale));
		welcomeBox.addSFX(new FadeSFX(TrueCondition.instance, conf));

	}

	private void addToLayer() {
		LayerFactory.getInstance().addHUD(0, title);

		LayerFactory.getInstance().addHUD(0, welcomeBox);
		LayerFactory.getInstance().addToLayer(25, particles);

		LayerFactory.getInstance().addToLayer(50, NullEntity.instance);
		LayerFactory.getInstance().addToLayer(20,
				new DrawEntity(new Vector2f(.4f, .8f, true), dune1));

		LayerFactory.getInstance().addToLayer(70,
				new DrawEntity(new Vector2f(.1f, .8f, true), dune1));

		LayerFactory.getInstance().addToLayer(80,
				new DrawEntity(new Vector2f(.4f, .4f, true), background));
		LayerFactory.getInstance().setReference(50);
		LayerFactory.getInstance().setDepth(200);
		LayerFactory.getInstance().setReferenceCoordinate(IV.center.clone());

		particles.setCond(new RandomChangingCondition(true, 1, 50));

	}

	/*----------------------------
	 * 
	 * Methods
	 * 
	 *----------------------------*/

	@Override
	public void renderIt(final GameContainer container,
			final StateBasedGame arg1, final Graphics g) throws SlickException {
	}

	@Override
	public void updateIt(final GameContainer container,
			final StateBasedGame game, final int delta) throws SlickException {
		welcomeBox.update(delta, true);
		if (welcomeBox.isMouseOver() && Commands.isMousePressed(0)) {
			leave(container, game);
			game.addState(GameStates.GAMEPLAYSTATE2.getState());
			GameStates.GAMEPLAYSTATE2.getState().init(container, game);
			game.enterState(GameStates.GAMEPLAYSTATE2.getID(),
					new FadeOutTransition(), new FadeInTransition());
		}
	}

	@Override
	public int getID() {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.alnaiyr.states.State#initLayerDependant(org.newdawn.slick.GameContainer
	 * , org.newdawn.slick.state.StateBasedGame)
	 */
	@Override
	public void initLayerDependant(final GameContainer container,
			final StateBasedGame game) {
		getReference().focus.setAMove(new FocusSoftShaker(new MinMaxImpl(-10,
				+10), new MinMaxImpl(-10, +10), .01f, new RandomLuckCondition(
				10, 100), getReference().focus));
	}

}

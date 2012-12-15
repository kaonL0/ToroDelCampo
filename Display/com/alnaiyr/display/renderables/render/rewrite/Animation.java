package com.alnaiyr.display.renderables.render.rewrite;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import com.alnaiyr.ai.updater.Updatable;
import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.renderables.DimensionDrawable;
import com.alnaiyr.general.IV;

public class Animation extends org.newdawn.slick.Animation implements
		DimensionDrawable, Updatable {

	/**
     * 
     */
	public Animation() {
		super();

	}

	/**
	 * @param autoUpdate
	 */
	public Animation(final boolean autoUpdate) {
		super(autoUpdate);

	}

	/**
	 * @param frames
	 * @param duration
	 * @param autoUpdate
	 */
	public Animation(final Image[] frames, final int duration,
			final boolean autoUpdate) {
		super(frames, duration, autoUpdate);

	}

	/**
	 * @param frames
	 * @param duration
	 */
	public Animation(final Image[] frames, final int duration) {
		super(frames, duration);

	}

	/**
	 * @param frames
	 * @param durations
	 * @param autoUpdate
	 */
	public Animation(final Image[] frames, final int[] durations,
			final boolean autoUpdate) {
		super(frames, durations, autoUpdate);

	}

	/**
	 * @param frames
	 * @param durations
	 */
	public Animation(final Image[] frames, final int[] durations) {
		super(frames, durations);

	}

	/**
	 * @param frames
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param horizontalScan
	 * @param duration
	 * @param autoUpdate
	 */
	public Animation(final SpriteSheet frames, final int x1, final int y1,
			final int x2, final int y2, final boolean horizontalScan,
			final int duration, final boolean autoUpdate) {
		super(frames, x1, y1, x2, y2, horizontalScan, duration, autoUpdate);

	}

	/**
	 * @param frames
	 * @param duration
	 */
	public Animation(final SpriteSheet frames, final int duration) {
		super(frames, duration);

	}

	/**
	 * @param ss
	 * @param frames
	 * @param duration
	 */
	public Animation(final SpriteSheet ss, final int[] frames,
			final int[] duration) {
		super(ss, frames, duration);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Animation#addFrame(int, int, int)
	 */
	@Override
	public void addFrame(final int duration, final int x, final int y) {

		super.addFrame(duration, x, y);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Animation#setAutoUpdate(boolean)
	 */
	@Override
	public void setAutoUpdate(final boolean auto) {

		super.setAutoUpdate(auto);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Animation#setPingPong(boolean)
	 */
	@Override
	public void setPingPong(final boolean pingPong) {

		super.setPingPong(pingPong);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Animation#isStopped()
	 */
	@Override
	public boolean isStopped() {

		return super.isStopped();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Animation#setSpeed(float)
	 */
	@Override
	public void setSpeed(final float spd) {

		super.setSpeed(spd);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Animation#getSpeed()
	 */
	@Override
	public float getSpeed() {

		return super.getSpeed();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Animation#stop()
	 */
	@Override
	public void stop() {

		super.stop();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Animation#start()
	 */
	@Override
	public void start() {

		super.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Animation#restart()
	 */
	@Override
	public void restart() {

		super.restart();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Animation#addFrame(org.newdawn.slick.Image, int)
	 */
	@Override
	public void addFrame(final Image frame, final int duration) {

		super.addFrame(frame, duration);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Animation#draw()
	 */
	@Override
	public void draw() {

		super.draw();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Animation#draw(float, float)
	 */
	@Override
	public void draw(final PlanVector coord) {
		super.draw(coord.x(), coord.y(), IV.g.getColor());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Animation#draw(float, float,
	 * org.newdawn.slick.Color)
	 */
	@Override
	public void draw(final float x, final float y, final Color filter) {

		super.draw(x, y, filter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Animation#draw(float, float, float, float)
	 */
	@Override
	public void draw(final float x, final float y, final float width,
			final float height) {

		super.draw(x, y, width, height);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Animation#draw(float, float, float, float,
	 * org.newdawn.slick.Color)
	 */
	@Override
	public void draw(final float x, final float y, final float width,
			final float height, final Color col) {

		super.draw(x, y, width, height, col);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Animation#renderInUse(int, int)
	 */
	@Override
	public void renderInUse(final int x, final int y) {

		super.renderInUse(x, y);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Animation#getWidth()
	 */
	@Override
	public int width() {

		return super.width();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Animation#getHeight()
	 */
	@Override
	public int height() {

		return super.height();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Animation#drawFlash(float, float, float, float)
	 */
	@Override
	public void drawFlash(final float x, final float y, final float width,
			final float height) {

		super.drawFlash(x, y, width, height);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Animation#drawFlash(float, float, float, float,
	 * org.newdawn.slick.Color)
	 */
	@Override
	public void drawFlash(final float x, final float y, final float width,
			final float height, final Color col) {

		super.drawFlash(x, y, width, height, col);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Animation#updateNoDraw()
	 */
	@Deprecated
	@Override
	public void updateNoDraw() {

		super.updateNoDraw();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Animation#update(long)
	 */
	@Override
	public void update(final long delta) {

		super.update(delta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Animation#getFrame()
	 */
	@Override
	public int getFrame() {

		return super.getFrame();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Animation#setCurrentFrame(int)
	 */
	@Override
	public void setCurrentFrame(final int index) {

		super.setCurrentFrame(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Animation#getImage(int)
	 */
	@Override
	public Image getImage(final int index) {

		return super.getImage(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Animation#getFrameCount()
	 */
	@Override
	public int getFrameCount() {

		return super.getFrameCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Animation#getCurrentFrame()
	 */
	@Override
	public Image getCurrentFrame() {

		return super.getCurrentFrame();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Animation#setLooping(boolean)
	 */
	@Override
	public void setLooping(final boolean loop) {

		super.setLooping(loop);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Animation#stopAt(int)
	 */
	@Override
	public void stopAt(final int frameIndex) {

		super.stopAt(frameIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Animation#getDuration(int)
	 */
	@Override
	public int getDuration(final int index) {

		return super.getDuration(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Animation#setDuration(int, int)
	 */
	@Override
	public void setDuration(final int index, final int duration) {

		super.setDuration(index, duration);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Animation#getDurations()
	 */
	@Override
	public int[] getDurations() {

		return super.getDurations();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Animation#toString()
	 */
	@Override
	public String toString() {

		return super.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Animation#copy()
	 */
	@Override
	public org.newdawn.slick.Animation copy() {
		return super.copy();
	}

	@Override
	public void update(final int delta, final boolean condition) {
		if (condition && isStopped()) {
			restart();
		}

		super.update(delta);
	}

	public Animation getScaledCopy(final float scale) {

		final Image[] temp = new Image[getFrameCount()];

		for (int i = 0; i < getFrameCount(); i++) {
			temp[i] = getImage(i).getScaledCopy(scale);
		}

		return new Animation(temp, getDurations());
	}

	@Override
	public DataType getType() {
		return DataType.ANIMATION;
	}
}

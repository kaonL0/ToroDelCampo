package com.alnaiyr.display.renderables.render.rewrite;

import java.io.InputStream;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.ImageData;
import org.newdawn.slick.opengl.Texture;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.renderables.DimensionDrawable;
import com.alnaiyr.general.IV;

/**
 * Image class, that is Dimensionnable
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public class Image extends org.newdawn.slick.Image implements DimensionDrawable {

	/**
     * 
     */
	public Image() {
		super();
	}

	/**
	 * @param other
	 */
	public Image(final org.newdawn.slick.Image other) {
		super(other);
	}

	/**
	 * @param data
	 * @param f
	 */
	public Image(final ImageData data, final int f) {
		super(data, f);
	}

	/**
	 * @param data
	 */
	public Image(final ImageData data) {
		super(data);
	}

	/**
	 * @param in
	 * @param ref
	 * @param flipped
	 * @param filter
	 * @throws SlickException
	 */
	public Image(final InputStream in, final String ref, final boolean flipped,
			final int filter) throws SlickException {
		super(in, ref, flipped, filter);
	}

	/**
	 * @param in
	 * @param ref
	 * @param flipped
	 * @throws SlickException
	 */
	public Image(final InputStream in, final String ref, final boolean flipped)
			throws SlickException {
		super(in, ref, flipped);
	}

	/**
	 * @param width
	 * @param height
	 * @throws SlickException
	 */
	public Image(final int width, final int height) throws SlickException {
		super(width, height);
	}

	/**
	 * @param ref
	 * @param flipped
	 * @param f
	 * @param transparent
	 * @throws SlickException
	 */
	public Image(final String ref, final boolean flipped, final int f,
			final Color transparent) throws SlickException {
		super(ref, flipped, f, transparent);
	}

	/**
	 * @param ref
	 * @param flipped
	 * @param filter
	 * @throws SlickException
	 */
	public Image(final String ref, final boolean flipped, final int filter)
			throws SlickException {
		super(ref, flipped, filter);
	}

	/**
	 * @param ref
	 * @param flipped
	 * @throws SlickException
	 */
	public Image(final String ref, final boolean flipped) throws SlickException {
		super(ref, flipped);
	}

	/**
	 * @param ref
	 * @param trans
	 * @throws SlickException
	 */
	public Image(final String ref, final Color trans) throws SlickException {
		super(ref, trans);
	}

	/**
	 * @param ref
	 * @throws SlickException
	 */
	public Image(final String ref) throws SlickException {
		super(ref);
	}

	/**
	 * @param texture
	 */
	public Image(final Texture texture) {
		super(texture);
	}

	@Override
	public void setFilter(final int f) throws SlickException {

		super.setFilter(f);
	}

	@Override
	public int getFilter() {

		return super.getFilter();
	}

	@Override
	public String getResourceReference() {

		return super.getResourceReference();
	}

	@Override
	public void setColor(final int corner, final float r, final float g,
			final float b, final float a) {

		super.setColor(corner, r, g, b, a);
	}

	@Override
	public void clampTexture() {

		super.clampTexture();
	}

	@Override
	public void setName(final String name) {

		super.setName(name);
	}

	@Override
	public String getName() {

		return super.getName();
	}

	@Override
	public Graphics getGraphics() throws SlickException {

		return super.getGraphics();
	}

	@Override
	public void bind() {

		super.bind();
	}

	@Override
	protected void reinit() {

		super.reinit();
	}

	@Override
	protected void initImpl() {

		super.initImpl();
	}

	@Override
	public void draw() {

		super.draw();
	}

	@Override
	public void drawCentered(final float x, final float y) {

		super.drawCentered(x, y);
	}

	/**
	 * Pays attention on current g color
	 */
	@Override
	public void draw(final PlanVector coord) {
		super.draw(coord.x(), coord.y(), IV.g.getColor());
	}

	@Override
	public void draw(final float x, final float y, final Color filter) {
		super.draw(x, y, filter);
	}

	@Override
	public void drawEmbedded(final float x, final float y, final float width,
			final float height) {

		super.drawEmbedded(x, y, width, height);
	}

	@Override
	public float getTextureOffsetX() {

		return super.getTextureOffsetX();
	}

	@Override
	public float getTextureOffsetY() {

		return super.getTextureOffsetY();
	}

	@Override
	public float getTextureWidth() {

		return super.getTextureWidth();
	}

	@Override
	public float getTextureHeight() {

		return super.getTextureHeight();
	}

	@Override
	public void draw(final float x, final float y, final float scale) {

		super.draw(x, y, scale);
	}

	@Override
	public void draw(final float x, final float y, final float scale,
			final Color filter) {

		super.draw(x, y, scale, filter);
	}

	@Override
	public void draw(final float x, final float y, final float width,
			final float height) {

		super.draw(x, y, width, height);
	}

	@Override
	public void drawSheared(final float x, final float y, final float hshear,
			final float vshear) {

		super.drawSheared(x, y, hshear, vshear);
	}

	@Override
	public void draw(final float x, final float y, final float width,
			final float height, final Color filter) {

		super.draw(x, y, width, height, filter);
	}

	@Override
	public void drawFlash(final float x, final float y, final float width,
			final float height) {

		super.drawFlash(x, y, width, height);
	}

	@Override
	public void setCenterOfRotation(final float x, final float y) {

		super.setCenterOfRotation(x, y);
	}

	@Override
	public float getCenterOfRotationX() {

		return super.getCenterOfRotationX();
	}

	@Override
	public float getCenterOfRotationY() {

		return super.getCenterOfRotationY();
	}

	@Override
	public void drawFlash(final float x, final float y, final float width,
			final float height, final Color col) {

		super.drawFlash(x, y, width, height, col);
	}

	@Override
	public void drawFlash(final float x, final float y) {

		super.drawFlash(x, y);
	}

	@Override
	public void setRotation(final float angle) {

		super.setRotation(angle);
	}

	@Override
	public float getRotation() {

		return super.getRotation();
	}

	@Override
	public float getAlpha() {

		return super.getAlpha();
	}

	@Override
	public void setAlpha(final float alpha) {

		super.setAlpha(alpha);
	}

	@Override
	public void rotate(final float angle) {

		super.rotate(angle);
	}

	@Override
	public Image getSubImage(final int x, final int y, final int width,
			final int height) {

		init();

		final float newTextureOffsetX = x / (float) this.width * textureWidth
				+ textureOffsetX;
		final float newTextureOffsetY = y / (float) this.height * textureHeight
				+ textureOffsetY;
		final float newTextureWidth = width / (float) this.width * textureWidth;
		final float newTextureHeight = height / (float) this.height
				* textureHeight;

		final Image sub = new Image();
		sub.inited = true;
		sub.texture = texture;
		sub.textureOffsetX = newTextureOffsetX;
		sub.textureOffsetY = newTextureOffsetY;
		sub.textureWidth = newTextureWidth;
		sub.textureHeight = newTextureHeight;

		sub.width = width;
		sub.height = height;
		sub.ref = ref;
		sub.centerX = width / 2;
		sub.centerY = height / 2;

		return sub;
	}

	@Override
	public void draw(final float x, final float y, final float srcx,
			final float srcy, final float srcx2, final float srcy2) {

		super.draw(x, y, srcx, srcy, srcx2, srcy2);
	}

	@Override
	public void draw(final float x, final float y, final float x2,
			final float y2, final float srcx, final float srcy,
			final float srcx2, final float srcy2) {

		super.draw(x, y, x2, y2, srcx, srcy, srcx2, srcy2);
	}

	@Override
	public void draw(final float x, final float y, final float x2,
			final float y2, final float srcx, final float srcy,
			final float srcx2, final float srcy2, final Color filter) {

		super.draw(x, y, x2, y2, srcx, srcy, srcx2, srcy2, filter);
	}

	@Override
	public void drawEmbedded(final float x, final float y, final float x2,
			final float y2, final float srcx, final float srcy,
			final float srcx2, final float srcy2) {

		super.drawEmbedded(x, y, x2, y2, srcx, srcy, srcx2, srcy2);
	}

	@Override
	public void drawEmbedded(final float x, final float y, final float x2,
			final float y2, final float srcx, final float srcy,
			final float srcx2, final float srcy2, final Color filter) {

		super.drawEmbedded(x, y, x2, y2, srcx, srcy, srcx2, srcy2, filter);
	}

	@Override
	public int width() {
		return super.width();
	}

	@Override
	public int height() {
		return super.height();
	}

	@Override
	public Image copy() {
		return (Image) super.copy();
	}

	@Override
	public Image getScaledCopy(final float scale) {
		init();
		return getScaledCopy((int) (width * scale), (int) (height * scale));
	}

	@Override
	public Image getScaledCopy(final int width, final int height) {
		init();
		final Image image = copy();
		image.width = width;
		image.height = height;
		image.centerX = width / 2;
		image.centerY = height / 2;
		return image;
	}

	@Override
	public void ensureInverted() {
		super.ensureInverted();
	}

	@Override
	public Image getFlippedCopy(final boolean flipHorizontal,
			final boolean flipVertical) {
		return (Image) super.getFlippedCopy(flipHorizontal, flipVertical);
	}

	@Override
	public void endUse() {
		super.endUse();
	}

	@Override
	public void startUse() {
		super.startUse();
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public Texture getTexture() {
		return super.getTexture();
	}

	@Override
	public void setTexture(final Texture texture) {
		super.setTexture(texture);
	}

	@Override
	public Color getColor(final int x, final int y) {
		return super.getColor(x, y);
	}

	@Override
	public boolean isDestroyed() {
		return super.isDestroyed();
	}

	@Override
	public void destroy() throws SlickException {
		super.destroy();
	}

	@Override
	public void flushPixelData() {
		super.flushPixelData();
	}

	@Override
	public DataType getType() {
		return DataType.IMAGE;
	}
}

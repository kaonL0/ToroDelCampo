package com.alnaiyr.general;

import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.InvalidAttributesException;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.Log;

import com.alnaiyr.commands.Commands;
import com.alnaiyr.loading.OptionLoader;

/**
 * Contains all external informations about the game (meaning all options
 * entered as an input)
 * 
 * @author IgoЯ
 * @version 1.2
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>Change</em></li>
 *          <li><em>can change display mode</em></li>
 *          <li>
 *          <em>delegate storing value for a better player friendly interface (storing values in a xml file)</em>
 *          </li>
 *          </ul>
 */
public final class EV {

	/* ***********************
	 * 
	 * Variables
	 * 
	 * **************************
	 */

	public static boolean qwerty = true;

	public static int musicVolume = 10;
	public static int soundVolume = 10;

	public static int width = 800;
	public static int height = 600;

	public static boolean antiAlias = false;
	public static boolean vSync = false;
	public static boolean fullScreen = false;

	public static int sampleSize = 1;

	/**
	 * All display modes available
	 */
	public static DisplayMode[] mods;

	/* ***********************
	 * 
	 * Constructor
	 * 
	 * **************************
	 */

	/**
	 * Initialize risky parts
	 * 
	 */
	public static void init() {
		try {
			if (EV.mods == null)
				EV.mods = Display.getAvailableDisplayModes();
		} catch (LWJGLException e) {
			Log.error("unable to get display mods!");
		}
	}

	/* ***********************
	 * 
	 * Methods
	 * 
	 * **************************
	 */

	/**
	 * Sets all graphics values
	 * 
	 * @return true if binded successful
	 */
	public static boolean bindAllValues() {
		try {
			IV.container.setDisplayMode(EV.getWidth(), EV.getHeight(),
					EV.isFullScreen());
			IV.container.getGraphics().setAntiAlias(EV.isAntiAlias());
			IV.container.setVSync(EV.isvSync());
			IV.container.setMultiSample(EV.getMutliSample());
			IV.recalculate();
			OptionLoader.writeNewValues("data/OptionProfile.conf");
			Mouse.create();
			Commands.configure(IV.container);
			return true;
		} catch (LWJGLException e) {
			Log.error("Failed to bind value!");
		} catch (SlickException e) {
			Log.error("Failed to set on FullScreen!");
		} catch (InvalidAttributesException e) {
			Log.error("Error binding controller!");
		}
		return false;
	}

	/**
	 * @return true if succeed to bind default values
	 */
	public static boolean bindDefaultValues() {
		try {
			IV.container.setDisplayMode(800, 600, false);
			IV.container.getGraphics().setAntiAlias(false);
			IV.container.setVSync(false);
			IV.recalculate();
			Mouse.create();
			Commands.configure(IV.container);
			return true;
		} catch (LWJGLException e) {
			Log.error("Failed to bind value!");

		} catch (SlickException e) {
			Log.error("Failed to set on FullScreen!");

		} catch (InvalidAttributesException e) {
			Log.error("Error binding controller!");
		}
		return false;
	}

	/**
	 * 
	 * @param i
	 *            (takes only (obviously) the two first values
	 * @return the correct diplaying modes for this resolution
	 */
	public static List<DisplayMode> findCorrDisplayModes(int[] i) {
		return EV.findCorrDisplayModes(i[0], i[1]);
	}

	/**
	 * @param width
	 * @param height
	 * @return list of corresponding modes
	 */
	public static List<DisplayMode> findCorrDisplayModes(int width, int height) {
		ArrayList<DisplayMode> list = new ArrayList<>();
		for (DisplayMode mod : EV.mods) {
			if (mod.getWidth() == width && mod.getHeight() == height) {
				list.add(mod);
			}
		}
		return list;
	}

	/* ***********************
	 * 
	 * Getters
	 * 
	 * **************************
	 */

	/**
	 * 
	 * @return real screen width
	 */
	public static int getWidth() {
		return EV.width;
	}

	/**
	 * 
	 * @return true if Anti Alias is activated
	 */
	public static boolean isAntiAlias() {
		return EV.antiAlias;
	}

	/**
	 * 
	 * @return true if vsync is activated
	 */
	public static boolean isvSync() {
		return EV.vSync;
	}

	/**
	 * 
	 * @return true if fullscreen is activated
	 */
	public static boolean isFullScreen() {
		return EV.fullScreen;
	}

	/**
	 * 
	 * @return number of multisample anti alias
	 */
	public static int getMutliSample() {
		return EV.sampleSize;
	}

	/**
	 * 
	 * @return real height of the screen
	 */
	public static int getHeight() {
		return EV.height;
	}

	/**
	 * 
	 * @return current volume
	 */
	public static int getMusicVolume() {
		return EV.musicVolume;
	}

	/**
	 * 
	 * @return current volume
	 */
	public static int getSoundVolume() {
		return EV.soundVolume;
	}

	/* ***********************
	 * 
	 * Setters
	 * 
	 * **************************
	 */
	/**
	 * 
	 * @param width
	 */
	public static void setWidth(int width) {
		EV.width = width;
	}

	/**
	 * 
	 * @param height
	 */
	public static void setHeight(int height) {
		EV.height = height;
	}

	/**
	 * 
	 * @return true if the keyboard is set as a Qwerty one
	 */
	public static boolean isQwerty() {
		return EV.qwerty;
	}

	/**
	 * 
	 * @param antiAlias
	 */
	public static void setAntiAlias(boolean antiAlias) {
		EV.antiAlias = antiAlias;
	}

	/**
     * 
     */
	public static void setAntiAlias() {
		EV.antiAlias = !EV.antiAlias;
	}

	/**
	 * 
	 * @param vSync
	 */
	public static void setvSync(boolean vSync) {
		EV.vSync = vSync;
	}

	/**
	 * 
	 * @param fullScreen
	 */
	public static void setFullScreen(boolean fullScreen) {
		EV.fullScreen = fullScreen;
	}

	/**
	 * 
	 * @param qwerty
	 */
	public static void setQwerty(boolean qwerty) {
		EV.qwerty = qwerty;
	}

	/**
	 * 
	 * @param multiSample
	 */
	public static void setMutliSample(int multiSample) {
		EV.sampleSize = multiSample;
	}

	/**
	 * @param musicVolume
	 */
	public static void setMusicVolume(int musicVolume) {
		EV.musicVolume = musicVolume;
	}

	/**
	 * 
	 * @param soundVolume
	 */
	public static void setSoundVolume(int soundVolume) {
		EV.soundVolume = soundVolume;
	}

	/**
	 * 
	 * @param bool
	 * @return
	 * @throws NullPointerException
	 */
	public static boolean getParsedBool(String bool)
			throws NullPointerException {
		bool = bool.toLowerCase();
		switch (bool) {
			case "antialias":
				return EV.antiAlias;
			case "fullscreen":
				return EV.fullScreen;
			case "qwerty":
				return EV.qwerty;
			case "vsync":
				return EV.vSync;
		}
		throw new NullPointerException();
	}

	/**
	 * 
	 * @param bool
	 * @return
	 * @throws NullPointerException
	 */
	public static void setParsedBool(String bool, boolean value)
			throws NullPointerException {
		bool = bool.toLowerCase();
		switch (bool) {
			case "antialias":
				EV.antiAlias = value;
				return;
			case "fullscreen":
				EV.fullScreen = value;
				return;
			case "qwerty":
				EV.qwerty = value;
				return;
			case "vsync":
				EV.vSync = value;
				return;
		}
		throw new NullPointerException();
	}

	public static int getParsedInt(String i) throws NullPointerException {
		i = i.toLowerCase();
		switch (i) {
			case "width":
				return EV.width;
			case "height":
				return EV.height;
			case "musicvolume":
				return EV.musicVolume;
			case "soundvolume":
				return EV.soundVolume;
			case "samplesize":
				return EV.sampleSize;
		}
		throw new NullPointerException();
	}

	public static void setParsedInt(String i, int value)
			throws NullPointerException {
		i = i.toLowerCase();
		switch (i) {
			case "width":
				EV.width = value;
				return;
			case "height":
				EV.height = value;
				return;
			case "musicvolume":
				EV.musicVolume = value;
				return;
			case "soundvolume":
				EV.soundVolume = value;
				return;
			case "samplesize":
				EV.sampleSize = value;
				return;
		}
		throw new NullPointerException();
	}

}

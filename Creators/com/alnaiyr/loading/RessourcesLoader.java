package com.alnaiyr.loading;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.BigImage;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.util.Log;
import org.newdawn.slick.util.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.alnaiyr.display.renderables.render.rewrite.Animation;
import com.alnaiyr.display.renderables.render.rewrite.Image;
import com.alnaiyr.display.renderables.render.rewrite.SpriteSheet;
import com.alnaiyr.ressources.angelcodefont.FontBinder;
import com.alnaiyr.ressources.animation.AnimationBinder;
import com.alnaiyr.ressources.image.ImageBinder;
import com.alnaiyr.ressources.music.MusicBinder;
import com.alnaiyr.ressources.sound.SoundBinder;
import com.alnaiyr.ressources.spritesheet.SpriteSheetBinder;

/**
 * This RessourcesManager stores values into static classes, using an Xml Parser
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public final class RessourcesLoader {

	private static String	baseDir	= null;
	private static String	pack;

	/**
	 * @param ref
	 * @throws IOException
	 */
	public static void loadResources(final String ref) throws IOException {
		// RessourcesLoader.updateRelatedEnum(ref);
		RessourcesLoader.loadResources(ResourceLoader.getResourceAsStream(ref));
	}

	/**
	 * @param ref
	 * @throws IOException
	 */
	public static void loadResources(final File ref) throws IOException {
		RessourcesLoader.loadResources(new BufferedInputStream(
				new FileInputStream(ref)));
	}

	/**
	 * @param ref
	 * @throws IOException
	 */
	public static void loadResources(final InputStream ref) throws IOException {
		try {

			final DocumentBuilder builder = DocumentBuilderFactory
					.newInstance().newDocumentBuilder();
			final Document document = builder.parse(ref);

			final Element element = document.getDocumentElement();
			if (!element.getNodeName().equals("resources")) {
				throw new IOException("Not a resource configuration file");
			}

			// first look for basedir
			NodeList list = element.getElementsByTagName("basedir");
			for (int i = 0; i < list.getLength(); i++) {
				RessourcesLoader.setBaseDirectory((Element) list.item(i));
			}

			Log.info("Loading Sounds...");
			// load sounds
			list = element.getElementsByTagName("sound");
			for (int i = 0; i < list.getLength(); i++) {
				RessourcesLoader.loadSound((Element) list.item(i));
			}
			Log.info("Loading Musics...");
			// load songs

			list = element.getElementsByTagName("music");
			for (int i = 0; i < list.getLength(); i++) {
				RessourcesLoader.loadMusic((Element) list.item(i));
			}
			Log.info("Loading Images...");
			// load images
			list = element.getElementsByTagName("image");
			for (int i = 0; i < list.getLength(); i++) {
				RessourcesLoader.loadImage((Element) list.item(i));
			}
			Log.info("Loading Image groups...");
			// load images tables
			list = element.getElementsByTagName("animation");
			for (int i = 0; i < list.getLength(); i++) {
				RessourcesLoader.loadAnimation((Element) list.item(i));
			}
			Log.info("Loading Sprite sheets...");
			// load sheets
			list = element.getElementsByTagName("spritesheet");
			for (int i = 0; i < list.getLength(); i++) {
				RessourcesLoader.loadSpriteSheet((Element) list.item(i));
			}
			Log.info("Loading Fonts...");
			// load fonts
			list = element.getElementsByTagName("angelcodefont");
			for (int i = 0; i < list.getLength(); i++) {
				RessourcesLoader.loadAngelCodeFont((Element) list.item(i));
			}
			Log.info("Loading Done!");
		} catch (final IOException e) {
			Log.error(e);
			throw e;
		} catch (final SlickException e) {
			Log.info("files already loaded!");
		} catch (final Exception e) {
			Log.error(e);
			throw new IOException("Unable to load resource configuration file");
		}
		ref.close();
	}

	private static void setBaseDirectory(final Element basedir)
			throws SlickException {
		final String dir = basedir.getAttribute("path");
		final String pack = basedir.getAttribute("package");
		RessourcesLoader.setBaseDirectory(dir, pack);
	}

	/**
	 * @param baseDirectory
	 * @throws SlickException
	 */
	public static void setBaseDirectory(final String baseDirectory,
			final String pack) throws SlickException {
		Log.debug("Setting base directory to '" + baseDirectory + "'");
		Log.debug("Setting main package to '" + pack + "'");
		if (baseDirectory == null || baseDirectory.isEmpty())
			throw new SlickException("BaseDirectory must not be null or empty!");
		if (pack == null || pack.isEmpty())
			throw new SlickException("Package declaration is null!");
		RessourcesLoader.baseDir = baseDirectory;
		RessourcesLoader.pack = pack;
		if (!RessourcesLoader.baseDir.endsWith("/"))
			RessourcesLoader.baseDir = RessourcesLoader.baseDir + "/";
	}

	/* ****************************
	 * 
	 * Music and Sounds
	 * 
	 * ******************************
	 */

	private static void loadMusic(final Element music) throws SlickException {
		final String key = music.getAttribute("key");
		final String file = music.getAttribute("file");
		RessourcesLoader.loadMusic(key, file);
	}

	private static void loadMusic(final String key, String file)
			throws SlickException {
		Log.debug("Loading music '" + key + "'...");
		if (new MusicBinder().isBinded(RessourcesLoader.pack, key))
			throw new SlickException("Music " + key + " already binded!");

		if (RessourcesLoader.baseDir != null
				&& !file.startsWith(RessourcesLoader.baseDir))
			file = RessourcesLoader.baseDir + file;

		final Music song = new Music(file);

		new MusicBinder().bind(RessourcesLoader.pack, key, song);
	}

	private static void loadSound(final Element snd) throws SlickException {
		final String key = snd.getAttribute("key");
		final String file = snd.getAttribute("file");
		RessourcesLoader.loadSound(key, file);
	}

	private static void loadSound(final String key, String file)
			throws SlickException {
		Log.debug("Loading sound '" + key + "'...");
		if (new SoundBinder().isBinded(RessourcesLoader.pack, key))
			throw new SlickException("Sound " + key + " already binded!");
		if (RessourcesLoader.baseDir != null
				&& !file.startsWith(RessourcesLoader.baseDir))
			file = RessourcesLoader.baseDir + file;

		final Sound sound = new Sound(file);
		new SoundBinder().bind(RessourcesLoader.pack, key, sound);
	}

	/* **********************
	 * 
	 * Images
	 * 
	 * ********************************
	 */

	private static void loadImage(final Element img) throws SlickException {
		final String key = img.getAttribute("key");
		final String file = img.getAttribute("file");
		RessourcesLoader.loadImage(key, file);
	}

	private static void loadImage(final String key, String file)
			throws SlickException {
		Log.debug("Loading Image " + key + "'...");

		if (new ImageBinder().isBinded(RessourcesLoader.pack, key))
			throw new SlickException("Image " + key + " already binded!");

		if (RessourcesLoader.baseDir != null
				&& !file.startsWith(RessourcesLoader.baseDir))
			file = RessourcesLoader.baseDir + file;

		final Image image = new Image(file);

		new ImageBinder().bind(RessourcesLoader.pack, key, image);
	}

	private static void loadSpriteSheet(final Element sprsheet)
			throws SlickException {
		final String key = sprsheet.getAttribute("key");
		final String file = sprsheet.getAttribute("file");
		final int width = Integer.parseInt(sprsheet.getAttribute("width"));
		final int height = Integer.parseInt(sprsheet.getAttribute("height"));

		RessourcesLoader.loadSpriteSheet(key, file, width, height);
	}

	private static void loadSpriteSheet(final String key, String file,
			final int width, final int height) throws SlickException {
		Log.debug("Loading spritesheet '" + key + "'...");

		if (new SpriteSheetBinder().isBinded(RessourcesLoader.pack, key))
			throw new SlickException("SpriteSheet " + key + " already binded!");

		if (RessourcesLoader.baseDir != null
				&& !file.startsWith(RessourcesLoader.baseDir))
			file = RessourcesLoader.baseDir + file;

		SpriteSheet spriteSheet;
		try {
			spriteSheet = new SpriteSheet(file, width, height);
		} catch (final Exception e) {
			spriteSheet = new SpriteSheet(new BigImage(file), width, height);
		}
		new SpriteSheetBinder().bind(RessourcesLoader.pack, key, spriteSheet);
	}

	private static void loadAnimation(final Element imageTab)
			throws SlickException {

		final String key = imageTab.getAttribute("key");
		final String file = imageTab.getAttribute("file");
		final int duration = Integer.valueOf(imageTab.getAttribute("duration"));
		RessourcesLoader.loadAnimation(key, RessourcesLoader.baseDir + file,
				duration);
	}

	private static void loadAnimation(final String key, String file,
			final int duration) throws SlickException {

		Log.debug("Loading image table '" + key + "'...");

		if (new AnimationBinder().isBinded(RessourcesLoader.pack, key))
			throw new SlickException("Image Tab " + key + " already binded!");

		if (RessourcesLoader.baseDir != null
				&& !file.startsWith(RessourcesLoader.baseDir))
			file = RessourcesLoader.baseDir + file;

		int number = 0;
		try (DirectoryStream<Path> ds = Files.newDirectoryStream(Paths
				.get(file));) {

			for (@SuppressWarnings("unused")
			final Path path : ds) {
				number++;
			}

		} catch (final IOException e1) {

			e1.printStackTrace();
		}

		final Image[] images = new Image[number];
		final DecimalFormat df = new DecimalFormat();
		df.setMinimumIntegerDigits(4);
		df.setGroupingSize(4);
		for (int i = 0; i < number; i++) {
			try {
				images[i] = new Image(file + df.format(number) + ".png");
			} catch (final SlickException e) {
				e.printStackTrace();
			}
		}
		new AnimationBinder().bind(RessourcesLoader.pack, key, new Animation(
				images, duration, true));

	}

	/* **********************
	 * 
	 * Fonts
	 * 
	 * ********************************
	 */

	private static void loadAngelCodeFont(final Element fnt)
			throws SlickException {
		final String key = fnt.getAttribute("key");
		final String fntfile = fnt.getAttribute("fontFile");
		final String imagefile = fnt.getAttribute("imageFile");
		RessourcesLoader.loadAngelCodeFont(key, fntfile, imagefile);
	}

	private static void loadAngelCodeFont(final String key, String fontFile,
			String imageFile) throws SlickException {
		Log.debug("Loading Angelcode font '" + key + "'...");

		if (new FontBinder().isBinded(RessourcesLoader.pack, key))
			throw new SlickException("AngelCodeFont " + key
					+ " already binded!");

		if (RessourcesLoader.baseDir != null
				&& !fontFile.startsWith(RessourcesLoader.baseDir)) {
			fontFile = RessourcesLoader.baseDir + fontFile;
			imageFile = RessourcesLoader.baseDir + imageFile;
		}
		final AngelCodeFont font = new AngelCodeFont(fontFile, imageFile, true);
		new FontBinder().bind(RessourcesLoader.pack, key, font);
	}

	/*--------------------
	 * 
	 * Updating
	 * 
	 *--------------------*/

	public static void updateRelatedEnum(final String ref) {

		RessourcesLoader.updateRelatedEnum(ResourceLoader
				.getResourceAsStream(ref));
	}

	private static void updateRelatedEnum(final InputStream ref) {

		try {
			final DocumentBuilder builder = DocumentBuilderFactory
					.newInstance().newDocumentBuilder();
			final Document document = builder.parse(ref);

			final Element element = document.getDocumentElement();
			if (!element.getNodeName().equals("resources")) {
				throw new IOException("Not a resource configuration file");
			}

			// first look for basedir
			final NodeList list = element.getElementsByTagName("basedir");

			final Element elem = (Element) list.item(0);
			final String name = elem.getAttribute("package");

			RessourcesLoader.updateRelatedEnum(name, element, document);

		} catch (IOException | SAXException | ParserConfigurationException e) {
			e.printStackTrace();
			Log.error("Failed to bind values from " + ref);
		}
	}

	private static void updateRelatedEnum(final String name,
			final Element element, final Document document) {

		final String packaging = "com.alnaiyr.ressources.";

		final NodeList map = element.getChildNodes();

		final List<String> tested = new ArrayList<>(5);

		for (int i = 0; i < map.getLength(); i++) {

			final String currName = map.item(i).getNodeName();

			if (!currName.equals("#text") && !currName.equals("#comment")
					&& !currName.equals("basedir")
					&& !tested.contains(currName)) {

				tested.add(currName);
				RessourcesLoader.updateRelatedEnumType(name, packaging,
						currName, document);
			}
		}
	}

	private static void updateRelatedEnumType(final String name,
			final String packaging, final String type, final Document document) {

		final NodeList list = document.getElementsByTagName(type);
		final List<String> values = new ArrayList<>();

		for (int i = 0; i < list.getLength(); i++) {
			final Element elem = (Element) list.item(i);
			values.add(elem.getAttribute("key"));
		}

		final String pkg = TypeLoader.getPackage(type, "data/Types.conf");
		final String clas = TypeLoader.getClass(type, "data/Types.conf");

		RessourceEnumerationCompiler.compileDataEnum(
				packaging + clas.toLowerCase(), name, clas, pkg, true,
				"Containers", values.toArray(new String[values.size()]));
	}

	public static void main(final String[] args) {
		RessourcesLoader.updateRelatedEnum("data/main.load");
	}

}

package com.alnaiyr.generator.terrain;

import com.alnaiyr.generator.noise.NoiseGenerator;
import com.alnaiyr.utilities.images.FunctionRenderer;

/**
 * <p>
 * A class intending to create a "slice of planet", using a relief generator, an
 * alpha mask generator. <br>
 * Basically an image of relief giving an alpha mask, to render the first layer
 * of the planet.
 * </p>
 * 
 * @version 1.0
 * @author IgoR
 * 
 *         <p>
 *         <strong>1.0 Version change:</strong><br/>
 *         <ul>
 *         <li> <em>Now functional </em></li>
 *         </ul>
 *         </p>
 */

public class OrbitalTerrainGenerator {

    /* ***************************
     * 
     * Variables
     * 
     * 
     * *****************************
     */

    private NoiseGenerator noiseGen;

    private FunctionRenderer functionRend;

    private float depth;

    /** the max length the alphamask can handle */

    private float maxSystemLength;

    /* ***************************
     * 
     * Constructor
     * 
     * 
     * *****************************
     */

    /**
     * The main constructor which will set TODO: find a way to say: the side of
     * the buffer will be as big as what needed for the biggest planet of the
     * current solar system...
     */
    public OrbitalTerrainGenerator(int maxSystemLength) {

	noiseGen = new NoiseGenerator();

	functionRend = new FunctionRenderer();

	this.maxSystemLength = maxSystemLength;
    }

    /* ***************************
     * 
     * Method
     * 
     * *****************************
     */

    /**
     * Gets all the values for the generation for the new orbital object, and
     * generate all images for the planet
     * 
     * @param planet
     * @return
     */

    /*
     * public Terrain generateFor(Orbital planet, int zoneNumber) {
     * 
     * float length = maxSystemLength; depth = 50;
     * 
     * float zeroSurface = planet.getAtmheight(); HashMap<Integer, Float>
     * tendency = new HashMap<Integer, Float>(); // creates global tendency of
     * the relief (ie montains, plain, blabla) for (int i = 0; i < zoneNumber;
     * i++) { Random r = new Random(); float valeur = r.nextInt((int)
     * zeroSurface); tendency.put(i, valeur); } Log.info("Generating relief");
     * 
     * // generates the first relief HashMap<Integer, Float> cacheNoise =
     * noiseGen.generatePerlin(tendency.get(0), tendency.get(1), zeroSurface /
     * 4f, (int) length, 5, 0.1f, 1 / 4f, 2, 2);
     * 
     * ArrayList<HashMap<Integer, Float>> relief = new
     * ArrayList<HashMap<Integer, Float>>(); relief.add(cacheNoise);
     * 
     * // create all reliefs for (int i = 1; i < tendency.size(); i++) { if
     * (tendency.containsKey(i + 1))
     * relief.add(noiseGen.generatePerlin(relief.get(i - 1).get((int) length),
     * tendency.get(i + 1), zeroSurface / 4f, (int) length, 5, 0.1f, 1 / 4f, 2,
     * 3)); else relief.add(noiseGen.generatePerlin(relief.get(i - 1).get((int)
     * length), relief.get(0).get((int) length), zeroSurface / 4f, (int) length,
     * 5, 0.1f, 1 / 4f, 2, 3)); }
     * 
     * HashMap<Float, Image> images = new HashMap<Float, Image>(); // a hash map
     * containing image, and key being startY.
     * 
     * Log.info("Generating images..."); for (int i = 0; i < relief.size(); i++)
     * { images.put(NoiseUtilities.findHighestPoint(relief.get(i)).y,
     * functionRend.generateImage(relief.get(i), depth)); // put all values in
     * the map } Log.info("Done");
     * 
     * return new Terrain(planet, zoneNumber, images); }
     */
}

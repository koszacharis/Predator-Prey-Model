/*
 * The MIT License
 *
 * Copyright 2015 Konstantinos Zacharis (s1569729@sms.ed.ac.uk)
 * and George Zacharis (s1569743@sms.ed.ac.uk).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package uk.ac.ed.ph.ps.coursework.io;

import java.io.BufferedWriter;
import java.io.IOException;
import uk.ac.ed.ph.ps.coursework.model.Landscape;
import uk.ac.ed.ph.ps.coursework.model.Region;

/**
 * A class to output the densities of Hares and Pumas in a given Landscape
 * object in a PPM format file. Hare densities are scaled and exported in the
 * Red channel, Puma densities are scaled and exported in the Green channel. Where
 * a particular region of the Landscape is a WaterRegion (and hence has no Hare
 * or Puma density), a special 3-channel RGB value (representing a pale blue
 * colour in the RGB spectrum - @see WATER_NUMBER_STRING) is exported instead.
 *
 * @author Konstantinos Zacharis (s1569729@sms.ed.ac.uk)
 * @author George Zacharis (s1569743@sms.ed.ac.uk)
 * @author Andy Law (s1578554@sms.ed.ac.uk)
 */
public class PpmWriter {

    /**
     * An object that we will use to scale the actual hare densities to integer
     * values in a range
     */
    private PpmDensityScaler hareDensityScaler = new PpmDensityScaler();
    /**
     * An object that we will use to scale the actual puma densities to integer
     * values in a range
     */
    private PpmDensityScaler pumaDensityScaler = new PpmDensityScaler();

    /**
     * The 3 number string that we use to represent water in the PPM file
     */
    public static String WATER_NUMBER_STRING = "195 225 255 ";

    /**
     * Write densities of animals into Ppm.
     *
     * @param bufferedWriter
     * @param landscape
     * @return a boolean flag to indicate success or failure
     */
    public boolean writePpm(BufferedWriter bufferedWriter, Landscape landscape) {

        int width = landscape.getWidth();
        int height = landscape.getHeight();

        int regionCounter = 0;

        try {

            bufferedWriter.write("P3"); // plain ppm file magic number
            bufferedWriter.newLine();

            bufferedWriter.write((width) + " " + (height));
            bufferedWriter.newLine();

            bufferedWriter.write("255");
            bufferedWriter.newLine();

            for (int y = 0; y < height; y++) {

                for (int x = 0; x < width; x++) {
                    Region region = landscape.getRegion(x, y);
                    // Fill water cells with blue.
                    if (region.getLandCount() == 0) {
                        bufferedWriter.write(WATER_NUMBER_STRING);
                        regionCounter++;
                    } 
                    /*
                     * Fill land cells with higher intensity corresponding to
                     * greater value of density in a given cell.
                     */ 
                    else {
                        int hDensity = hareDensityScaler.scaledValue(region.getHareDensity());
                        int pDensity = pumaDensityScaler.scaledValue(region.getPumaDensity());
                        // fixed length of string for animal Densities (12
                        // characters)
                        bufferedWriter.write(String.format("%3d %3d %3d ", hDensity, pDensity, 0));
                        regionCounter++;
                    }
                    if (regionCounter == 5) {
                        bufferedWriter.newLine();
                        regionCounter = 0;
                    }
                }
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            return true;

        } catch (IOException ioe) {
            ioe.printStackTrace();
            return false;
        }

    }

    /**
     * @return the hareDensityScaler
     */
    public PpmDensityScaler getHareDensityScaler() {
        return hareDensityScaler;
    }

    /**
     * @param hareDensityScaler the hareDensityScaler to set
     */
    public void setHareDensityScaler(PpmDensityScaler hareDensityScaler) {
        this.hareDensityScaler = hareDensityScaler;
    }

    /**
     * @return the pumaDensityScaler
     */
    public PpmDensityScaler getPumaDensityScaler() {
        return pumaDensityScaler;
    }

    /**
     * @param pumaDensityScaler the pumaDensityScaler to set
     */
    public void setPumaDensityScaler(PpmDensityScaler pumaDensityScaler) {
        this.pumaDensityScaler = pumaDensityScaler;
    }

}

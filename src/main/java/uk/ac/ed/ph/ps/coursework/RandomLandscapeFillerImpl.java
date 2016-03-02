/*
 * The MIT License
 *
 * Copyright 2015 Andy Law (s1578554@sms.ed.ac.uk).
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
package uk.ac.ed.ph.ps.coursework;

import java.util.Random;
import uk.ac.ed.ph.ps.coursework.model.Landscape;
import uk.ac.ed.ph.ps.coursework.model.Region;

/**
 * A class that implements LandscapeFiller by putting random values for Hare and
 * Puma Density into the Landscape.
 *
 * @author Andy Law (s1578554@sms.ed.ac.uk)
 */
public class RandomLandscapeFillerImpl implements LandscapeFiller {

    private final double maxHareDensity;
    private final double maxPumaDensity;

    /**
     * Construct a RandomLandscapeFiller object which will push densities for
     * Pumas and Hares constrained to the same maximum value.
     *
     * @param maxDensity the maximum density value for Hares/Pumas
     */
    public RandomLandscapeFillerImpl(double maxDensity) {
        this.maxHareDensity = maxDensity;
        this.maxPumaDensity = maxDensity;
    }

    /**
     * Construct a RandomLandscapeFiller object which will push independently
     * constrained densities for Pumas and Hares.
     *
     * @param maxHareDensity the maximum density value for Hares
     * @param maxPumaDensity the maximum density value for Pumas
     */
    public RandomLandscapeFillerImpl(double maxHareDensity, double maxPumaDensity) {
        this.maxHareDensity = maxHareDensity;
        this.maxPumaDensity = maxPumaDensity;
    }

    @Override
    public void fillLandscape(Landscape landscape) {
        Random rng = new Random();

        int width = landscape.getWidth();
        int height = landscape.getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Region region = landscape.getRegion(x, y);
                region.setHareDensity(maxHareDensity * rng.nextDouble());
                region.setPumaDensity(maxPumaDensity * rng.nextDouble());
            }
        }
    }

    /**
     * @return the maxHareDensity
     */
    public double getMaxHareDensity() {
        return maxHareDensity;
    }

    /**
     * @return the maxPumaDensity
     */
    public double getMaxPumaDensity() {
        return maxPumaDensity;
    }

}

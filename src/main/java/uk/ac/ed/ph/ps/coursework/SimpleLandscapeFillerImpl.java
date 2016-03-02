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

import uk.ac.ed.ph.ps.coursework.model.Landscape;
import uk.ac.ed.ph.ps.coursework.model.Region;

/**
 * This class has a single function. Given a Landscape object, it will assign
 * the same single density of Hares and Pumas into each of the regions within
 * that Landscape. Because we make no distinction at this level between Water
 * Regions and Land Regions, this code simply assigns that value into each and
 * leaves any logical consequences to the receiving object to deal with
 *
 * @author Andy Law (s1578554@sms.ed.ac.uk)
 */
public class SimpleLandscapeFillerImpl implements LandscapeFiller {

    private final double density;

    /**
     * Construct a SimpleLandscapeFillerImpl object by passing in the density
     * value to be used for both Puma and Hare densities
     *
     * @param density The density to be set for hares and pumas in the Landscape
     */
    public SimpleLandscapeFillerImpl(double density) {
        this.density = density;
    }

    /**
     * Do the filling of the supplied Landscape. Push the defined density into
     * each Region in the Landscape object
     *
     * @param landscape A Landscape object to be filled
     */
    @Override
    public void fillLandscape(Landscape landscape) {

        int width = landscape.getWidth();
        int height = landscape.getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Region region = landscape.getRegion(x, y);
                region.setHareDensity(density);
                region.setPumaDensity(density);
            }
        }

    }

}

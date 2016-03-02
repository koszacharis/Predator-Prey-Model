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

import java.io.BufferedReader;
import java.util.Scanner;
import uk.ac.ed.ph.ps.coursework.model.Landscape;
import uk.ac.ed.ph.ps.coursework.model.Region;

/**
 * An implementation of a LandscapeFiller that takes a list of locations and
 * Hare and Puma densities and fills them in to the Landscape
 *
 * @author Andy Law (s1578554@sms.ed.ac.uk)
 */
public class SpecificLandscapeFillerImpl implements LandscapeFiller {

    private final BufferedReader bufferedReader;

    /**
     * Create a new SpecificLandscapeFiller object. Since we need to read from a
     * data source, this must be supplied in the form of a BufferedReader
     *
     * @param bufferedReader A BufferedReader through which the file or other
     * data source which defines the densities
     */
    public SpecificLandscapeFillerImpl(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    /**
     * Go through the process of reading from the data source and filling in the
     * densities as we go
     *
     * @param landscape the Landscape object to be filled
     */
    @Override
    public void fillLandscape(Landscape landscape) {
    	Scanner s = new Scanner(bufferedReader);
    	while (s.hasNext()) {
            int x = s.nextInt();
            int y = s.nextInt();
            double hareDensity = s.nextDouble();
            double pumaDensity = s.nextDouble();

            Region region = landscape.getRegion(x, y);
            region.setHareDensity(hareDensity);
            region.setPumaDensity(pumaDensity);

            if (s.hasNext()) {
                s.nextLine();
            }
    	}
        s.close();
    }

}

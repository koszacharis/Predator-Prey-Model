/*
 * The MIT License
 *
 * Copyright 2015 Andy Law (s1578554@sms.ed.ac.uk)
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
package uk.ac.ed.ph.ps.coursework.simtools;

import java.util.ArrayList;
import java.util.Collection;
import uk.ac.ed.ph.ps.coursework.model.Landscape;
import uk.ac.ed.ph.ps.coursework.model.Region;

/**
 * The NeighbourCount class is calclulating the sum of neighbour landRegion objects, the sum of the neighbour 
 * Hare densities and the sum of the neighbour Puma densities for a given Landscape and a set of coordinates.
 * 
 * @author Andy Law (s1578554@sms.ed.ac.uk)
 * @author George Zacharis (s1569743@sms.ed.ac.uk)
 *
 */
public class NeighbourCount {

    NeighbourCountResult getNeighbourCountResult(Landscape landscape, int x, int y) {

        double pumaDensitySum = 0.0;
        double hareDensitySum = 0.0;
        int landCount = 0;

        Collection<Region> regions = new ArrayList<>();
        regions.add(landscape.getRegion(x - 1, y));
        regions.add(landscape.getRegion(x + 1, y));
        regions.add(landscape.getRegion(x, y - 1));
        regions.add(landscape.getRegion(x, y + 1));

        for (Region region : regions) {
            pumaDensitySum += region.getPumaDensity();
            hareDensitySum += region.getHareDensity();
            landCount += region.getLandCount();
        }

        return new NeighbourCountResult(landCount, pumaDensitySum, hareDensitySum);
    }

}

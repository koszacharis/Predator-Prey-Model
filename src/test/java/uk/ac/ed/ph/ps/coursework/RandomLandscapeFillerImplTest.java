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

import static org.easymock.EasyMock.and;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.geq;
import static org.easymock.EasyMock.leq;
import static org.easymock.EasyMock.lt;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import org.junit.Test;
import uk.ac.ed.ph.ps.coursework.model.Landscape;
import uk.ac.ed.ph.ps.coursework.model.Region;

/**
 *
 * @author Andy Law (s1578554@sms.ed.ac.uk)
 */
public class RandomLandscapeFillerImplTest {

    public RandomLandscapeFillerImplTest() {
    }

    /**
     * Test that all the densities set are in the range 0..5.0 when we use the
     * single argument constructor.
     */
    @Test
    public void testFillLandscapeSingleArgConstructor() {
        final double maxDensity = 5.0;
        final int WIDTH = 500;
        final int HEIGHT = 500;
        Landscape landscape = mock(Landscape.class);
        Region region = mock(Region.class);

        RandomLandscapeFillerImpl instance = new RandomLandscapeFillerImpl(maxDensity);

        expect(landscape.getWidth()).andReturn(WIDTH);
        expect(landscape.getHeight()).andReturn(HEIGHT);

        expect(landscape.getRegion(
                and(geq(0), lt(WIDTH)),
                and(geq(0), lt(HEIGHT))))
                .andReturn(region)
                .times(WIDTH * HEIGHT);

        region.setHareDensity(and(geq(0.0), leq(maxDensity)));
        expectLastCall().times(WIDTH * HEIGHT);

        region.setPumaDensity(and(geq(0.0), leq(maxDensity)));
        expectLastCall().times(WIDTH * HEIGHT);

        replay(landscape, region);

        instance.fillLandscape(landscape);

        verify(landscape, region);
    }

    /**
     * Test that all the hare densities set are in the range 0..5.0 and the puma
     * densities are in the range 0..15.0 when we use the double argument
     * constructor with those arguments supplied.
     */
    @Test
    public void testFillLandscapeDoubleArgConstructor() {
        final double maxHareDensity = 5.0;
        final double maxPumaDensity = 15.0;
        final int WIDTH = 500;
        final int HEIGHT = 500;
        Landscape landscape = mock(Landscape.class);
        Region region = mock(Region.class);

        RandomLandscapeFillerImpl instance = new RandomLandscapeFillerImpl(maxHareDensity, maxPumaDensity);

        expect(landscape.getWidth()).andReturn(WIDTH);
        expect(landscape.getHeight()).andReturn(HEIGHT);

        expect(landscape.getRegion(
                and(geq(0), lt(WIDTH)),
                and(geq(0), lt(HEIGHT))))
                .andReturn(region)
                .times(WIDTH * HEIGHT);

        region.setHareDensity(and(geq(0.0), leq(maxHareDensity)));
        expectLastCall().times(WIDTH * HEIGHT);

        region.setPumaDensity(and(geq(0.0), leq(maxPumaDensity)));
        expectLastCall().times(WIDTH * HEIGHT);

        replay(landscape, region);

        instance.fillLandscape(landscape);

        verify(landscape, region);
    }

}

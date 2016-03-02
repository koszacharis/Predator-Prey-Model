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

import com.beust.jcommander.JCommander;
import java.io.FileNotFoundException;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import org.junit.Test;
import static org.junit.Assert.*;
import static uk.ac.ed.ph.ps.coursework.Configuration.LANDSCAPE_FILENAME;
import uk.ac.ed.ph.ps.coursework.model.Landscape;
import uk.ac.ed.ph.ps.coursework.simtools.Simulator;

/**
 * Tests of the Runner helper class
 *
 * @author Andy Law (s1578554@sms.ed.ac.uk)
 */
public class RunnerTest {

    private static final double DOUBLE_TEST_SLOP = 0.00000001;

    public RunnerTest() {
    }

    @Test
    public void testGetConfiguration() throws Exception {
        final String filename = "elephantMan.dat";
        final String[] args = {LANDSCAPE_FILENAME, filename};

        Runner instance = new Runner();

        Configuration configuration = instance.getConfiguration(args);
        assertNotNull(configuration);

        JCommander jCommander = configuration.getJCommander();
        assertNotNull(jCommander);
    }

    /**
     * Test that we get back a RandomLandscapeFiller object if we ask for one
     * and that it gets the values from the Configuration object
     *
     * @throws java.io.FileNotFoundException if the landscape file filler file
     * cannot be found
     */
    @Test
    public void testGetRandomLandscapeFiller() throws FileNotFoundException {

        final double expectedHareDensity = 17.322;
        final double expectedPumaDensity = 3.911;

        Runner instance = new Runner();

        Configuration config = mock(Configuration.class);

        expect(config.getFillMethod()).andReturn("random");
        expect(config.getMaxStartHareDensity()).andReturn(expectedHareDensity);
        expect(config.getMaxStartPumaDensity()).andReturn(expectedPumaDensity);

        replay(config);

        LandscapeFiller result = instance.getLandscapeFiller(config);

        verify(config);

        assertTrue(result instanceof RandomLandscapeFillerImpl);
        assertEquals(expectedHareDensity, ((RandomLandscapeFillerImpl) result).getMaxHareDensity(), DOUBLE_TEST_SLOP);
        assertEquals(expectedPumaDensity, ((RandomLandscapeFillerImpl) result).getMaxPumaDensity(), DOUBLE_TEST_SLOP);
    }

    /**
     * Test that the Runner builds the Simulator correctly (or at least that it
     * asks the Configuration object for the correct information)
     */
    @Test
    public void testGetSimulator() {

        final double hareBirthRate = 13.225;
        final double harePredationRate = 6.7;
        final double hareDiffusionRate = 0.1;

        final double pumaBirthRate = 0.001;
        final double pumaPredationRate = 4.332;
        final double pumaDiffusionRate = 16;

        final int width = 17;
        final int height = 13;

        Runner instance = new Runner();

        Configuration config = mock(Configuration.class);
        Landscape landscape = mock(Landscape.class);

        expect(config.getHareBirthRate()).andReturn(hareBirthRate);
        expect(config.getHarePredationRate()).andReturn(harePredationRate);
        expect(config.getHareDiffusionRate()).andReturn(hareDiffusionRate);
        expect(config.getPumaBirthRate()).andReturn(pumaBirthRate);
        expect(config.getPumaMortalityRate()).andReturn(pumaPredationRate);
        expect(config.getPumaDiffusionRate()).andReturn(pumaDiffusionRate);

        expect(landscape.getWidth()).andReturn(width);
        expect(landscape.getHeight()).andReturn(height);

        replay(config, landscape);
        Simulator result = instance.getSimulator(landscape, config);
        verify(config, landscape);

        assertNotNull(result);

    }

}

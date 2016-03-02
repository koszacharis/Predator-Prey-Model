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

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
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
public class SimpleLandscapeFillerImplTest {

    /**
     * Test of fillLandscape method, of class SimpleLandscapeFillerImpl.
     */
    @Test
    public void testFillLandscape() {
        // The density value we'll be using in this test
        final double DENSITY = 32.4;
        final int WIDTH = 2;
        final int HEIGHT = 3;

        // A MOCK Landscape object
        Landscape mockLandscape = mock(Landscape.class);

        // A MOCK Region object. We will re-use this single Region multiple
        // times
        Region mockRegion = mock(Region.class);

        // Create the object to be tested
        SimpleLandscapeFillerImpl instance = new SimpleLandscapeFillerImpl(DENSITY);

        // Record the things that we *expect* to be called on the Mock Landscape
        expect(mockLandscape.getWidth()).andReturn(WIDTH);
        expect(mockLandscape.getHeight()).andReturn(HEIGHT);
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                expect(mockLandscape.getRegion(x, y)).andReturn(mockRegion);
            }
        }

        // Record the things that we expect to be called on the Mock Region
        // NOTE that we'll be calling the same object multiple times because
        // we are re-using the region
        mockRegion.setHareDensity(DENSITY);
        expectLastCall().times(WIDTH * HEIGHT);
        mockRegion.setPumaDensity(DENSITY);
        expectLastCall().times(WIDTH * HEIGHT);

        // replay the mock objects to set them up
        replay(mockLandscape, mockRegion);
        
        // Call the function to be tested, passing the MOCK Landscape object
        instance.fillLandscape(mockLandscape);
        
        // verify that everything was called properly
        verify(mockLandscape, mockRegion);

    }

}

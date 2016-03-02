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
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import org.junit.Test;
import uk.ac.ed.ph.ps.coursework.model.Landscape;
import uk.ac.ed.ph.ps.coursework.model.Region;

/**
 * Test code for the SpecificLandscapeFillerImpl code
 *
 * @author Andy Law (s1578554@sms.ed.ac.uk)
 */
public class SpecificLandscapeFillerImplTest {

    /**
     * Test of fillLandscape method, of class SpecificLandscapeFillerImpl.
     */
    @Test
    public void testFillLandscape() {

        String str = "0 0 9.334 7.655\n"
                + "0   1    13.55 0";

        // convert String into InputStream
        InputStream is = new ByteArrayInputStream(str.getBytes());

        // read it with BufferedReader
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

        Region region1 = mock(Region.class);
        Region region2 = mock(Region.class);

        Landscape landscape = mock(Landscape.class);
        
        expect(landscape.getRegion(0, 0)).andReturn(region1);
        region1.setHareDensity(9.334);
        region1.setPumaDensity(7.655);

        expect(landscape.getRegion(0, 1)).andReturn(region2);
        region2.setHareDensity(13.55);
        region2.setPumaDensity(0.0);
        
        SpecificLandscapeFillerImpl instance = new SpecificLandscapeFillerImpl(bufferedReader);

        replay(region1, region2, landscape);

        instance.fillLandscape(landscape);

        verify(region1, region2, landscape);
    }

}

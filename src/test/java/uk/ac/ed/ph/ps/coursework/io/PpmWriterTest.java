/*
 * The MIT License
 *
 * Copyright 2015 Konstantinos Zacharis (s1569729@sms.ed.ac.uk).
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

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.IOException;

import org.junit.Test;
import static uk.ac.ed.ph.ps.coursework.io.PpmWriter.WATER_NUMBER_STRING;

import uk.ac.ed.ph.ps.coursework.model.Landscape;
import uk.ac.ed.ph.ps.coursework.model.Region;

/**
 * Check that the code asks the Landscape object for the data and then passes
 * that same data on to the BufferedWriter.
 * 
 * @author Konstantinos Zacharis (s1569729@sms.ed.ac.uk)
 * 
 */
public class PpmWriterTest {

	@Test
	public void testPpmWriterOk() {

		PpmWriter instance = new PpmWriter();

		BufferedWriter bufferedWriter = mock(BufferedWriter.class);
		Landscape landscape = mock(Landscape.class);

		Region waterRegion = mock(Region.class);
		Region bothRegion = mock(Region.class);
		Region noHares = mock(Region.class);
		Region noPumas = mock(Region.class);
		Region waterRegion1 = mock(Region.class);
		Region waterRegion2 = mock(Region.class);
		Region waterRegion3 = mock(Region.class);
		Region waterRegion4 = mock(Region.class);
        
        PpmDensityScaler hareDensityScaler = mock(PpmDensityScaler.class);
        PpmDensityScaler pumaDensityScaler = mock(PpmDensityScaler.class);

        instance.setHareDensityScaler(hareDensityScaler);
        instance.setPumaDensityScaler(pumaDensityScaler);
        
		// Landscape is 6 x 1
		// The first one is Water
		// The second one is Land with no Pumas/Hares
		// The third one is Land with Pumas but no hares
		// The fourth one is Land with Hares but no Pumas
		// The last three are Water regions to check for the newLine counter

		try {
			bufferedWriter.write("P3");
			bufferedWriter.newLine();

			expect(landscape.getWidth()).andReturn(1);
			expect(landscape.getHeight()).andReturn(8);
			bufferedWriter.write((1) + " " + (8));
			bufferedWriter.newLine();

			bufferedWriter.write("255");
			bufferedWriter.newLine();

			// The Water region
			expect(landscape.getRegion(0, 0)).andReturn(waterRegion);
			expect(waterRegion.getLandCount()).andReturn(0);
			bufferedWriter.write(WATER_NUMBER_STRING);
            bufferedWriter.newLine();

			// The both region
			expect(landscape.getRegion(0, 1)).andReturn(bothRegion);
			expect(bothRegion.getLandCount()).andReturn(1);
			expect(bothRegion.getHareDensity()).andReturn(0.0);
            expect(hareDensityScaler.scaledValue(0.0)).andReturn(27);
			expect(bothRegion.getPumaDensity()).andReturn(0.0);
            expect(pumaDensityScaler.scaledValue(0.0)).andReturn(32);
			bufferedWriter.write(" 27  32   0 ");
            bufferedWriter.newLine();

			// Only pumas
			expect(landscape.getRegion(0, 2)).andReturn(noHares);
			expect(noHares.getLandCount()).andReturn(1);
			expect(noHares.getHareDensity()).andReturn(0.0);
            expect(hareDensityScaler.scaledValue(0.0)).andReturn(0);
			expect(noHares.getPumaDensity()).andReturn(2.6);
            expect(pumaDensityScaler.scaledValue(2.6)).andReturn(6);
			bufferedWriter.write("  0   6   0 ");
            bufferedWriter.newLine();

			// Only hares
			expect(landscape.getRegion(0, 3)).andReturn(noPumas);
			expect(noPumas.getLandCount()).andReturn(1);
			expect(noPumas.getHareDensity()).andReturn(3.1);
            expect(hareDensityScaler.scaledValue(3.1)).andReturn(223);
			expect(noPumas.getPumaDensity()).andReturn(0.0);
            expect(pumaDensityScaler.scaledValue(0.0)).andReturn(99);
			bufferedWriter.write("223  99   0 ");
            bufferedWriter.newLine();

			// The Water region1
			expect(landscape.getRegion(0, 4)).andReturn(waterRegion1);
			expect(waterRegion1.getLandCount()).andReturn(0);
			bufferedWriter.write(WATER_NUMBER_STRING);

			bufferedWriter.newLine();

			// The Water region2
			expect(landscape.getRegion(0, 5)).andReturn(waterRegion2);
			expect(waterRegion2.getLandCount()).andReturn(0);
			bufferedWriter.write(WATER_NUMBER_STRING);
            bufferedWriter.newLine();

			// The Water region3
			expect(landscape.getRegion(0, 6)).andReturn(waterRegion3);
			expect(waterRegion3.getLandCount()).andReturn(0);
			bufferedWriter.write(WATER_NUMBER_STRING);
            bufferedWriter.newLine();

			// The Water region4
			expect(landscape.getRegion(0, 7)).andReturn(waterRegion4);
			expect(waterRegion4.getLandCount()).andReturn(0);
			bufferedWriter.write(WATER_NUMBER_STRING);
            bufferedWriter.newLine();

			bufferedWriter.newLine();
			bufferedWriter.flush();

			replay(bufferedWriter, landscape, waterRegion, bothRegion, noHares, noPumas);
            replay(hareDensityScaler, pumaDensityScaler);
			Boolean result = instance.writePpm(bufferedWriter, landscape);
			assertNotNull(result);
			verify(bufferedWriter, landscape, waterRegion, bothRegion, noHares, noPumas);
            verify(hareDensityScaler, pumaDensityScaler);

		} catch (IOException ex) {
			fail();
		}

	}

}

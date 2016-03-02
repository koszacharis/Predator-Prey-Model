/*
 * The MIT License
 *
 * Copyright 2015 George Zacharis (s1569743@sms.ed.ac.uk).
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

import java.io.BufferedReader;
import java.io.IOException;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import org.junit.Test;
import static org.junit.Assert.*;
import uk.ac.ed.ph.ps.coursework.model.Landscape;

/**
 *
 * @author George Zacharis (s1569743@sms.ed.ac.uk)
 */
public class LandscapeFileReaderTest {

    /**
     * Test of readLandscapeFile method, of class LandscapeFileReader. Assuming
     * that the input looks correct in all aspects
     */
    @Test
    public void testReadLandscapeFileOK() {

        try {
            LandscapeFileReader instance = new LandscapeFileReader();

            BufferedReader bufferedReader = mock(BufferedReader.class);

            expect(bufferedReader.readLine())
                    .andReturn("2 4")
                    .andReturn("1 1")
                    .andReturn("0 0")
                    .andReturn("0 1")
                    .andReturn("1 0");
            replay(bufferedReader);
            
            Landscape result = instance.readLandscapeFile(bufferedReader);
            assertNotNull(result);
            
            // Check if the first line is parsed correctly as width and height
            assertEquals("Expected a width of 4",4, instance.getHeight());
            assertEquals(2, instance.getWidth());
            
            // Check if each cell has the correct Region inserted (x = column , y = row)
            assertEquals(result.getRegion(0, 0).getLandCount(),1);
            assertEquals(result.getRegion(1, 0).getLandCount(),1);
            assertEquals(result.getRegion(0, 3).getLandCount(),1); 
            assertEquals(result.getRegion(1, 3).getLandCount(),0);

            verify(bufferedReader);
        } catch (IOException e) {
            fail();
        }
    }

}

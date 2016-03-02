/*
 * The MIT License
 *
 * Copyright 2015 George Zacharis (s1569743@sms.ed.ac.uk), Konstantinos Zacharis (s1569729@sms.ed.ac.uk).
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
import uk.ac.ed.ph.ps.coursework.model.LandRegion;
import uk.ac.ed.ph.ps.coursework.model.Landscape;
import uk.ac.ed.ph.ps.coursework.model.LandscapeImpl;
import uk.ac.ed.ph.ps.coursework.model.Region;

/**
 * The purpose of this class is to read to receive a BufferedReader and return a
 * Landscape object filled with the data read from BufferedReader.
 *
 * @author George Zacharis (s1569743@sms.ed.ac.uk)
 * @author Konstantinos Zacharis (s1569729@sms.ed.ac.uk)
 */
public class LandscapeFileReader {

    private int width;
    private int height;

    /**
     * This method gets as input a bufferedReader from Main, reads from the
     * bufferedReader, extracts the necessary information and returns a
     * Landscape object with the previous information collected.
     *
     * @param bufferedReader is reading from the file specified from Main
     * @return a Landscape object containing the information from .dat file
     * @throws java.io.IOException if there are problems with the file
     */
    public Landscape readLandscapeFile(BufferedReader bufferedReader) throws IOException {
        String[] values;
        Landscape landscape;

        // Read in the first two values which will be dimensions
        String line = null;
        try {
            line = bufferedReader.readLine();
        } catch (IOException e) {
            throw new IOException("Couldn't read the dimensions line from the LandscapeFile");
        }
        values = line.split(" ");

        // Store dimensions
        width = Integer.parseInt(values[0]);
        height = Integer.parseInt(values[1]);
        
        if (width <=0 || height <=0) {
        	throw new IOException("The width or height should be positive value");
        }

        // Check if dimensions are out of bounds
        if (width > 2000 || height > 2000) {
            throw new IOException("Either width or height were > 2000 in the LandscapeFile");
        }

        // Fill the landscape with WaterRegion
        landscape = new LandscapeImpl(width, height);

        // Set LandRegion where the values read from bufferedReader are equal
        // to "1"
        for (int y = 0; y < height; y++) {

            try {
                line = bufferedReader.readLine();
            } catch (IOException e) {
                throw new IOException("Problem reading data line " + (y+2) + " from the LandscapeFile");
            }

            // Split each line on whitespace
            values = line.split(" ");  
            if (values.length != width) {
            	throw new IOException("Not enough values on line " + (y+2) + " in the LandscapeFile");
            }

            for (int x = 0; x < width; x++) {

            	char[] charArray = values[x].toCharArray();
            	if (!(charArray.length==1 && (charArray[0]=='0' || charArray[0]=='1'))) {
            		throw new IOException("There is invalid value on line " + (y+2) + " in the LandscapeFile");
            	}

            	// Check whether the value from bufferedReader is equal to "1"
            	if (values[x].equals("1")) {

            		// Set LandRegion
            		Region landRegion = new LandRegion();
            		landscape.setRegion(x, y, landRegion);
            	}
            }

        }
        return landscape;
    }

    /**
     * returns the width
     *
     * @return the width specified as argument
     */
    public int getWidth() {
        return width;
    }

    /**
     * sets the width
     *
     * @param width the width of Landscape object
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * return the height
     *
     * @return the height specified as argument
     */
    public int getHeight() {
        return height;
    }

    /**
     * sets the height
     *
     * @param height the height of Landscape object
     */
    public void setHeight(int height) {
        this.height = height;
    }
}

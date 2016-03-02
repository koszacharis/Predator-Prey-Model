/*
 * The MIT License
 *
 * Copyright 2015 Andy Law (s1578554@sms.ed.ac.uk)
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
package uk.ac.ed.ph.ps.coursework.model;

/**
 *
 * @author Andy Law (s1578554@sms.ed.ac.uk)
 */
public interface Landscape {

    /**
     * Get the width of the Landscape
     *
     * @return the width of the Landscape object
     */
    int getWidth();

    /**
     * Get the height of the Landscape
     *
     * @return the height of the Landscape
     */
    int getHeight();

    /**
     * Given an x and y coordinate pair, return the Region object at that
     * location
     *
     * @param x the x-coordinate of the requested Region
     * @param y the y-coordinate of the requested Region
     * @return the Region object at the requested (x,y) coordinate
     */
    Region getRegion(int x, int y);

    /**
     * Set the Region at the x and y coordinate to be the Region supplied
     *
     * @param x the x-coordinate of the Location to be set
     * @param y the y-coordinate of the Location to be set
     * @param region the Region object to be placed at the supplied (x,y)
     * coordinate
     */
    void setRegion(int x, int y, Region region);

    /**
     * Calculate and return the average density of Hares across the entire
     * Landscape. We effectively ignore Water regions because they report their
     * land content to be zero.
     *
     * @return the average density of Hares across the entire Landscape
     */
    double getAverageHareDensity();

    /**
     * Calculate and return the average density of Pumas across the entire
     * Landscape. We effectively ignore Water regions because they report their
     * land content to be zero.
     *
     * @return the average density of Pumas across the entire Landscape
     */
    double getAveragePumaDensity();
}

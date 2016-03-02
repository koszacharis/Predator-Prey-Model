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
public class LandscapeImpl implements Landscape {

    /**
     * The grid of objects that go to make up this Landscape
     */
    private final Region[][] regionGrid;
    /**
     * The width of the grid
     */
    private final int width;
    /**
     * The height of the grid
     */
    private final int height;

    /**
     * A singleton waterRegion. This will be our default value
     */
    private final static Region waterRegion = new WaterRegion();

    /**
     * Create a new Landscape object
     *
     * @param width the width of the Landscape
     * @param height the height of the Landscape
     */
    public LandscapeImpl(int width, int height) {
        this.width = width;
        this.height = height;
        int hiddenWidth = width + 2;
        int hiddenHeight = height + 2;
        this.regionGrid = new Region[hiddenWidth][hiddenHeight];
        for (int i = 0; i < hiddenWidth; i++) {
            for (int j = 0; j < hiddenHeight; j++) {
                this.regionGrid[i][j] = waterRegion;
            }
        }

    }

    /**
     * Set the width of the Landscape
     *
     * @return the width of the Landscape
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * Get the height of the Landscape
     *
     * @return the height of the Landscape
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     * Get the region at the location specified
     *
     * @param x the x-coordinate of the location from which the Region is to be
     * retrieved
     * @param y the y-coordinate of the location from which the Region is to be
     * retrieved
     * @return the Region at the specified location
     */
    @Override
    public Region getRegion(int x, int y) {
        return this.regionGrid[x + 1][y + 1];
    }

    /**
     * Set the region supplied into the landscape at the location specified.
     * Attempts to set a Region outside the specified range result in an
     * ArrayIndexOutOfBoundsException
     *
     * @param x the x-coordinate of the location to which the Region is to be
     * assigned
     * @param y the y-coordinate of the location to which the Region is to be
     * assigned
     * @param region the Region to be assigned
     */
    @Override
    public final void setRegion(int x, int y, Region region) {
        if ((x < 0) || (y < 0) || (x >= this.width) || (y >= this.height)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.regionGrid[x + 1][y + 1] = region;
    }

    @Override
    public double getAverageHareDensity() {
        double sum = 0;
        int landcount = 0;
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                Region region = this.getRegion(x, y);
                landcount += region.getLandCount();
                sum += region.getHareDensity();
            }
        }
        return (landcount > 0) ? sum / landcount : 0.0;
    }

    @Override
    public double getAveragePumaDensity() {
        double sum = 0;
        int landcount = 0;
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                Region region = this.getRegion(x, y);
                landcount += region.getLandCount();
                sum += region.getPumaDensity();
            }
        }
        return (landcount > 0) ? sum / landcount : 0.0;
    }

}

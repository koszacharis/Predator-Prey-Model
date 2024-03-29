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
 * @author Sheng Wang (s1532126@sms.ed.ac.uk)
 * @author Andy Law (s1578554@sms.ed.ac.uk)
 */
public class LandRegion implements Region {

    private double hareDensity;
    private double pumaDensity;

    public LandRegion() {
        this.hareDensity = 0;
        this.pumaDensity = 0;
    }

    @Override
    public double getHareDensity() {
        return this.hareDensity;
    }

    @Override
    public void setHareDensity(double hareDensity) {
        this.hareDensity = hareDensity;
    }

    @Override
    public double getPumaDensity() {
        return this.pumaDensity;
    }

    @Override
    public void setPumaDensity(double pumaDensity) {
        this.pumaDensity = pumaDensity;
    }

    @Override
    public int getLandCount() {
        return 1;
    }

}

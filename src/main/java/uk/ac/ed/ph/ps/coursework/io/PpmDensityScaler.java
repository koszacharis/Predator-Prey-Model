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
package uk.ac.ed.ph.ps.coursework.io;

/**
 * A utility class to convert a Hare or Puma Density value into a scaled integer
 * value.
 *
 * @author Andy Law (s1578554@sms.ed.ac.uk)
 */
public class PpmDensityScaler {

    public final double DEFAULT_SCALE_VALUE = 80.0;
    public final int DEFAULT_MAXIMUM_OUTPUT = 255;

    private double scaleValue = DEFAULT_SCALE_VALUE;
    private int maximumOutput = DEFAULT_MAXIMUM_OUTPUT;

    /**
     * Get the value of maximumOutput
     *
     * @return the current upper limit for scaled values i.e. the largest value
     * our conversion routine will return
     */
    public int getMaximumOutput() {
        return maximumOutput;
    }

    /**
     * Set the new value of maximumOutput
     *
     * @param maximumOutput the new upper limit for scaled values i.e. the
     * largest value our conversion routine will return
     */
    public void setMaximumOutput(int maximumOutput) {
        this.maximumOutput = maximumOutput;
    }

    /**
     * Get the current value of scaleValue
     *
     * @return the current Scale Value used to convert the density to an integer
     * value
     */
    public double getScaleValue() {
        return scaleValue;
    }

    /**
     * Set the value of scaleValue
     *
     * @param scaleValue the new Scale Value to be used to convert the density
     * to an integer value
     */
    public void setScaleValue(double scaleValue) {
        this.scaleValue = scaleValue;
    }

    /**
     * Convert a supplied density to a scaled integer value, bounded by 0 and
     * the specified maximum value
     *
     * @param density the supplied density value
     * @return the scaled integer value
     */
    public int scaledValue(double density) {
        int retVal = (int) (density * this.getScaleValue());
        if (retVal > this.getMaximumOutput()) {
            return this.getMaximumOutput();
        }
        if (retVal < 0) {
            return 0;
        }
        return retVal;
    }

}

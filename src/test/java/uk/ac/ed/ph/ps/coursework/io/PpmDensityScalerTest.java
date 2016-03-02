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

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andy Law (s1578554@sms.ed.ac.uk)
 */
public class PpmDensityScalerTest {

    public PpmDensityScalerTest() {
    }

    /**
     * Test of getMaximumOutput method, of class PpmDensityScaler.
     */
    @Test
    public void testGetMaximumOutput() {
        PpmDensityScaler instance = new PpmDensityScaler();

        final int expResult = 255;
        final int result = instance.getMaximumOutput();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMaximumOutput method, of class PpmDensityScaler.
     */
    @Test
    public void testSetMaximumOutput() {
        final int newValue = 334;
        PpmDensityScaler instance = new PpmDensityScaler();
        instance.setMaximumOutput(newValue);

        final int result = instance.getMaximumOutput();
        assertEquals(newValue, result);
    }

    /**
     * Test of getScaleValue method, of class PpmDensityScaler.
     */
    @Test
    public void testGetScaleValue() {
        PpmDensityScaler instance = new PpmDensityScaler();
        double expResult = 80.0;
        double result = instance.getScaleValue();
        assertEquals(expResult, result, 0.00000001);
    }

    /**
     * Test of setScaleValue method, of class PpmDensityScaler.
     */
    @Test
    public void testSetScaleValue() {
        final double newValue = 43.788;
        PpmDensityScaler instance = new PpmDensityScaler();
        instance.setScaleValue(newValue);

        final double result = instance.getScaleValue();
        assertEquals(newValue, result, 0.00000001);
    }

    /**
     * Test of scaledValue method, of class PpmDensityScaler.
     */
    @Test
    public void testScaledGoodValue() {
        double density = 2.711;
        PpmDensityScaler instance = new PpmDensityScaler();
        int expResult = 216;
        int result = instance.scaledValue(density);
        assertEquals(expResult, result);
    }

    /**
     * Test of scaledValue method, of class PpmDensityScaler.
     */
    @Test
    public void testScaledZeroValue() {
        double density = 0;
        PpmDensityScaler instance = new PpmDensityScaler();
        int expResult = 0;
        int result = instance.scaledValue(density);
        assertEquals(expResult, result);
    }

    /**
     * Test of scaledValue method, of class PpmDensityScaler.
     */
    @Test
    public void testScaledNegativeValue() {
        double density = -5.322;
        PpmDensityScaler instance = new PpmDensityScaler();
        int expResult = 0;
        int result = instance.scaledValue(density);
        assertEquals(expResult, result);
    }

    /**
     * Test of scaledValue method, of class PpmDensityScaler.
     */
    @Test
    public void testScaledBigValue() {
        double density = 40.9474;
        PpmDensityScaler instance = new PpmDensityScaler();
        int expResult = 255;
        int result = instance.scaledValue(density);
        assertEquals(expResult, result);
    }

}

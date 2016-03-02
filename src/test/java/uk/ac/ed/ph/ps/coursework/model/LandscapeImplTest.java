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
package uk.ac.ed.ph.ps.coursework.model;

import java.util.Random;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andy Law (s1578554@sms.ed.ac.uk)
 */
public class LandscapeImplTest {

    /**
     * The object under test
     */
    private LandscapeImpl testedObject;

    private static final int TEST_WIDTH = 8;
    private static final int TEST_HEIGHT = 4;

    private static final double DOUBLE_SLOP_FACTOR = 0.00000001;

    public LandscapeImplTest() {
    }

    @Before
    public void setUp() {
        this.testedObject = new LandscapeImpl(TEST_WIDTH, TEST_HEIGHT);
    }

    @After
    public void tearDown() {
    }

    /**
     * @return the testedObject
     */
    public LandscapeImpl getTestedObject() {
        return testedObject;
    }

    /**
     * Test the constructor with random widths and heights which will be between
     * 1 and the standard test parameters minus 1. This is to ensure that we
     * exercise the constructor with different values
     */
    @Test
    public void testConstructor() {
        Random randomGenerator = new Random();
        int randomWidth = randomGenerator.nextInt(TEST_WIDTH - 2) + 1;
        int randomHeight = randomGenerator.nextInt(TEST_HEIGHT - 2) + 1;

        LandscapeImpl instance = new LandscapeImpl(randomWidth, randomHeight);

        assertEquals(randomWidth, instance.getWidth());
        assertEquals(randomHeight, instance.getHeight());
    }

    /**
     * Test of getWidth method, of class LandscapeImpl.
     *
     * Tested in the constructor test above but confirms the value which should
     * be different from that used above
     */
    @Test
    public void testGetWidth() {
        LandscapeImpl instance = this.getTestedObject();

        assertEquals(TEST_WIDTH, instance.getWidth());
    }

    /**
     * Test of getHeight method, of class LandscapeImpl.
     *
     * Tested in the constructor test above but confirms the value which should
     * be different from that used above
     */
    @Test
    public void testGetHeight() {
        LandscapeImpl instance = this.getTestedObject();

        assertEquals(TEST_HEIGHT, instance.getHeight());
    }

    /**
     * Test that all Regions are WaterRegions by default.
     */
    @Test
    public void testGetRegionDefaults() {

        LandscapeImpl instance = this.getTestedObject();
        int width = instance.getWidth();
        int height = instance.getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Region region = instance.getRegion(x, y);
                assertNotNull(region);
                assertTrue(region instanceof WaterRegion);
            }
        }
    }

    /**
     * Test that we can set a region successfully.
     */
    @org.junit.Test
    public void testSetRegion() {

        Random randomGenerator = new Random();
        int randomWidth = randomGenerator.nextInt(TEST_WIDTH - 2) + 1;
        int randomHeight = randomGenerator.nextInt(TEST_HEIGHT - 2) + 1;

        Region region = new LandRegion();
        LandscapeImpl instance = this.getTestedObject();
        instance.setRegion(randomWidth, randomHeight, region);

        assertSame(region, instance.getRegion(randomWidth, randomHeight));
    }

    @Test
    public void testgetAverageHareDensityEmptyLandscape() {
        LandscapeImpl instance = this.getTestedObject();

        double hareAverage = instance.getAverageHareDensity();
        assertEquals(0, hareAverage, DOUBLE_SLOP_FACTOR);
    }

    @Test
    public void testgetAveragePumaDensityEmptyLandscape() {
        LandscapeImpl instance = this.getTestedObject();

        double hareAverage = instance.getAveragePumaDensity();
        assertEquals(0, hareAverage, DOUBLE_SLOP_FACTOR);
    }

    @Test
    public void testGetAverageHareDensity() {
        LandscapeImpl instance = this.getTestedObject();

        final double r1HareDensity = 1.5;
        final double r2HareDensity = 77;
        final double r3HareDensity = 13.4;
        final double expectedResult = 30.63333333;
        final double expectedResult2 = 22.975;

        Region r1 = new LandRegion();
        r1.setHareDensity(r1HareDensity);
        Region r2 = new LandRegion();
        r2.setHareDensity(r2HareDensity);
        Region r3 = new LandRegion();
        r3.setHareDensity(r3HareDensity);
        Region r4 = new LandRegion();

        instance.setRegion(1, 2, r1);
        instance.setRegion(7, 3, r2);
        instance.setRegion(3, 3, r3);

        double result = instance.getAverageHareDensity();
        assertEquals(expectedResult, result, DOUBLE_SLOP_FACTOR);

        instance.setRegion(1, 1, r4);
        double result2 = instance.getAverageHareDensity();
        assertEquals(expectedResult2, result2, DOUBLE_SLOP_FACTOR);

    }

    @Test
    public void testGetAveragePumaDensity() {
        LandscapeImpl instance = this.getTestedObject();

        final double r1PumaDensity = 13.2;
        final double r2PumaDensity = 3.7;
        final double r3PumaDensity = 63.7244;
        final double expectedResult = 26.8748;
        final double expectedResult2 = 20.1561;

        Region r1 = new LandRegion();
        r1.setPumaDensity(r1PumaDensity);
        Region r2 = new LandRegion();
        r2.setPumaDensity(r2PumaDensity);
        Region r3 = new LandRegion();
        r3.setPumaDensity(r3PumaDensity);
        Region r4 = new LandRegion();

        instance.setRegion(1, 2, r1);
        instance.setRegion(7, 3, r2);
        instance.setRegion(3, 3, r3);

        double result = instance.getAveragePumaDensity();
        assertEquals(expectedResult, result, DOUBLE_SLOP_FACTOR);

        instance.setRegion(1, 1, r4);
        double result2 = instance.getAveragePumaDensity();
        assertEquals(expectedResult2, result2, DOUBLE_SLOP_FACTOR);

    }

    /**
     * We should be able to get a value back if we ask for a region with an x
     * value of -1 or a y value of -1. The region should be a WaterRegion
     */
    @Test
    public void testMinusOneBoundaries() {
        LandscapeImpl instance = this.getTestedObject();

        try {
            Region region1 = instance.getRegion(-1, TEST_HEIGHT - 1);
            assertTrue(region1 instanceof WaterRegion);
            Region region2 = instance.getRegion(TEST_WIDTH - 1, -1);
            assertTrue(region2 instanceof WaterRegion);
            assertSame(region1, region2);
        } catch (Exception e) {
            fail();
        }

    }

    /**
     * We should not be able to get a value back if we ask for a region with an
     * x or y value of -2 or less.
     */
    @Test
    public void testMinusTwoBoundaries() {
        LandscapeImpl instance = this.getTestedObject();

        try {
            Region region = instance.getRegion(-2, TEST_HEIGHT - 1);
            fail();
        } catch (Exception e) {
            // Correct result
        }

        try {
            Region region = instance.getRegion(TEST_WIDTH - 1, -2);
            fail();
        } catch (Exception e) {
            // Correct result
        }

    }

    /**
     * We should be able to get a value back if we ask for a region with an x or
     * a y value equal to the WIDTH or HEIGHT of the landscape respectively. The
     * region should be a WaterRegion
     */
    @Test
    public void testMinusMaxBoundaries() {
        LandscapeImpl instance = this.getTestedObject();

        try {
            Region region1 = instance.getRegion(0, TEST_HEIGHT);
            assertTrue(region1 instanceof WaterRegion);
            Region region2 = instance.getRegion(TEST_WIDTH, 0);
            assertTrue(region2 instanceof WaterRegion);
            assertSame(region1, region2);
        } catch (Exception e) {
            fail();
        }

    }

    /**
     * We should not be able to get a value back if we ask for a region with an
     * x or a y value greater than the WIDTH or HEIGHT of the landscape
     * respectively.
     */
    @Test
    public void testMinusSupraMaxBoundaries() {
        LandscapeImpl instance = this.getTestedObject();

        try {
            Region region = instance.getRegion(TEST_WIDTH + 1, 0);
            fail();
        } catch (Exception e) {
            // Correct result
        }

        try {
            Region region = instance.getRegion(0, TEST_HEIGHT + 1);
            fail();
        } catch (Exception e) {
            // Correct result
        }

    }

    /**
     * We should not be able to set a a region into a location with an x value
     * of -1.
     */
    @Test
    public void testSetMinusOneWidthBoundaries() {
        LandscapeImpl instance = this.getTestedObject();

        Region region = new LandRegion();

        try {
            instance.setRegion(-1, TEST_HEIGHT - 1, region);
            fail();
        } catch (ArrayIndexOutOfBoundsException ae) {
            // correct behaviour
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * We should not be able to set a a region into a location with a y value of
     * -1.
     */
    @Test
    public void testSetMinusOneHeightBoundaries() {
        LandscapeImpl instance = this.getTestedObject();

        Region region = new LandRegion();
        try {
            instance.setRegion(TEST_WIDTH - 1, -1, region);
            fail();
        } catch (ArrayIndexOutOfBoundsException ae) {
            // correct behaviour
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * We should not be able to set a a region into a location with an x value
     * greater than or equal to the width of the Landscape.
     */
    @Test
    public void testSetMaxWidthBoundaries() {
        LandscapeImpl instance = this.getTestedObject();

        Region region = new LandRegion();

        try {
            instance.setRegion(TEST_WIDTH, TEST_HEIGHT - 1, region);
            fail();
        } catch (ArrayIndexOutOfBoundsException ae) {
            // correct behaviour
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * We should not be able to set a a region into a location with a y value
     * less than or equal to the height of the Landscape.
     */
    @Test
    public void testSetMaxHeightBoundaries() {
        LandscapeImpl instance = this.getTestedObject();

        Region region = new LandRegion();
        try {
            instance.setRegion(TEST_WIDTH - 1, TEST_HEIGHT, region);
            fail();
        } catch (ArrayIndexOutOfBoundsException ae) {
            // correct behaviour
        } catch (Exception e) {
            fail();
        }
    }

}

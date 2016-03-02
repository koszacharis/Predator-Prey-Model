/*
 * The MIT License
 *
 * Copyright 2015 Sheng Wang (s1532126@sms.ed.ac.uk).
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
package uk.ac.ed.ph.ps.coursework.simtools;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * @author Sheng Wang (s1532126@sms.ed.ac.uk)
 *
 */
public class HarePopulationCalculatorTest {

    private final double ERROR_TOLERANCE = 0.0001;

    @Test
    public void testGetNextHareDensityCase1() {
        //suggested default values
        double hareBirthRate = 0.08;
        double pumaPredationRate = 0.04;
        double hareDiffusionRate = 0.2;

        HarePopulationCalculator calculator
                = new HarePopulationCalculator(
                        hareBirthRate,
                        pumaPredationRate,
                        hareDiffusionRate);

        /*
         * case 1: currently 0 hare,  each neighbour has 1000 hares, 
         * 						(1000,unknown)	
         * 		(1000,unknown)	 (0,unknown)	(1000,unknown)
         * 						(1000,unknown)
         * 
         *nextHareDensity = timeStep * (hareDiffusionRate * totalNeighbourHareDensity)
         */
        double currentHareDensity = 0;
        double totalNeighbourHareDensity = 4000;
        int neighbourLandCount = 4;
        double timeStep = 0.4;
        double currentPumaDensity = 1000;

        double expectedHareDensity = timeStep * (hareDiffusionRate * totalNeighbourHareDensity);
        double calculatedHareDensity
                = calculator.getNextHareDensity(
                        currentHareDensity,
                        totalNeighbourHareDensity,
                        neighbourLandCount,
                        timeStep,
                        currentPumaDensity);

        assertEquals(expectedHareDensity, calculatedHareDensity, ERROR_TOLERANCE);
    }

    @Test
    public void testGetNextHareDensityCase2() {
        //suggested default values
        double hareBirthRate = 0.08;
        double pumaPredationRate = 0.04;
        double hareDiffusionRate = 0.2;

        HarePopulationCalculator calculator
                = new HarePopulationCalculator(
                        hareBirthRate,
                        pumaPredationRate,
                        hareDiffusionRate);

        /*
         * case 2: currently 1000 hares, 0 puma,  each neighbour has 1000 hares, 
         * 						(1000,unknown)	
         * 		(1000,unknown)	 (1000,0)	(1000,unknown)
         * 						(1000,unknown)
         * 
         *nextHareDensity = currentHareDensity + timeStep * (hareBirthRate*currentHareDensity + hareDiffusionRate * (totalNeighbourHareDensity - neighbourLandCount*currentHareDensity))
         */
        double currentHareDensity = 1000;
        double totalNeighbourHareDensity = 4000;
        int neighbourLandCount = 4;
        double timeStep = 0.4;
        double currentPumaDensity = 0;

        double expectedHareDensity = currentHareDensity + timeStep * (hareBirthRate * currentHareDensity + hareDiffusionRate * (totalNeighbourHareDensity - neighbourLandCount * currentHareDensity));
        double calculatedHareDensity
                = calculator.getNextHareDensity(
                        currentHareDensity,
                        totalNeighbourHareDensity,
                        neighbourLandCount,
                        timeStep,
                        currentPumaDensity);

        assertEquals(expectedHareDensity, calculatedHareDensity, ERROR_TOLERANCE);
    }

    @Test
    public void testGetNextHareDensityCase3() {
        //suggested default values
        double hareBirthRate = 0.08;
        double pumaPredationRate = 0.04;
        double hareDiffusionRate = 0.2;

        HarePopulationCalculator calculator = new HarePopulationCalculator(hareBirthRate, pumaPredationRate, hareDiffusionRate);

        /*
         * case 3: currently 500 hares, 500 pumas,  all neighbours are water region, 
         * 					  (0,0)	
         * 			(0,0)	(500,500)	(0,0)
         * 					  (0,0)
         * 
         *nextHareDensity = currentHareDensity + timeStep * (hareBirthRate*currentHareDensity - pumaPredationRate*currentHareDensity*currentPumaDensity)
         */
        double currentHareDensity = 500;
        double totalNeighbourHareDensity = 0;
        int neighbourLandCount = 0;
        double timeStep = 0.4;
        double currentPumaDensity = 500;

        double expectedHareDensity = currentHareDensity + timeStep * (hareBirthRate * currentHareDensity - pumaPredationRate * currentHareDensity * currentPumaDensity);
 
        double calculatedHareDensity
                = calculator.getNextHareDensity(
                        currentHareDensity,
                        totalNeighbourHareDensity,
                        neighbourLandCount,
                        timeStep,
                        currentPumaDensity);

        assertEquals(expectedHareDensity, calculatedHareDensity, ERROR_TOLERANCE);
    }

}

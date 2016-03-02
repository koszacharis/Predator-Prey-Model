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
public class PumaPopulationCalculatorTest {

    private final double ERROR_TOLERANCE = 0.0001;

    @Test
    public void testGetNextPumaDensityCase1() {
        //suggested default values
        double pumaBirthRate = 0.02;
        double pumaDeathRate = 0.06;
        double pumaDiffusionRate = 0.2;

        PumaPopulationCalculator calculator = new PumaPopulationCalculator(pumaBirthRate, pumaDeathRate, pumaDiffusionRate);

        /*
         * case 1: currently 0 puma, 1000 hares  each neighbour has 1000 pumas 
         * 						(0,1000)	
         * 			 (0,1000) (0,unknown) (0,1000)
         * 						(0,1000)
         * 
         *nextPumaDensity = timeStep * (pumaDiffusionRate * totalNeighbourPumaDensity)
         */
        double currentPumaDensity = 0;
        double totalNeighbourPumaDensity = 4000;
        int neighbourLandCount = 4;
        double timeStep = 0.4;
        double currentHareDensity = 1000;

        double expectedPumaDensity = timeStep * (pumaDiffusionRate * totalNeighbourPumaDensity);

        double calculatedPumaDensity
                = calculator.getNextPumaDensity(
                        currentPumaDensity,
                        totalNeighbourPumaDensity,
                        neighbourLandCount,
                        timeStep,
                        currentHareDensity);

        assertEquals(expectedPumaDensity, calculatedPumaDensity, ERROR_TOLERANCE);
    }

    @Test
    public void testGetNextPumaDensityCase2() {
        //suggested default values
        double pumaBirthRate = 0.02;
        double pumaDeathRate = 0.06;
        double pumaDiffusionRate = 0.2;

        PumaPopulationCalculator calculator
                = new PumaPopulationCalculator(
                        pumaBirthRate,
                        pumaDeathRate,
                        pumaDiffusionRate);

        /*
         * case 2: currently 1000 pumas, 0 hare,  each neighbour has 1000 pumas 
         * 						(unknown,1000)	
         * 			(unknown,1000) (1000,0) (unknown,1000)
         * 						(unknown,1000)
         * 
         *nextPumaDensity = currentPumaDensity + timeStep * ( - pumaDeathRate*currentPumaDensity + pumaDiffusionRate * (totalNeighbourPumaDensity-neighbourLandCount*currentPumaDensity))
         */
        double currentPumaDensity = 1000;
        double totalNeighbourPumaDensity = 4000;
        int neighbourLandCount = 4;
        double timeStep = 0.4;
        double currentHareDensity = 0;

        double expectedPumaDensity = currentPumaDensity + timeStep * (-pumaDeathRate * currentPumaDensity + pumaDiffusionRate * (totalNeighbourPumaDensity - neighbourLandCount * currentPumaDensity));

        double calculatedPumaDensity
                = calculator.getNextPumaDensity(
                        currentPumaDensity,
                        totalNeighbourPumaDensity,
                        neighbourLandCount,
                        timeStep,
                        currentHareDensity);

        assertEquals(expectedPumaDensity, calculatedPumaDensity, ERROR_TOLERANCE);
    }

    @Test
    public void testGetNextPumaDensityCase3() {

        //suggested default values
        double pumaBirthRate = 0.02;
        double pumaDeathRate = 0.06;
        double pumaDiffusionRate = 0.2;

        PumaPopulationCalculator calculator = new PumaPopulationCalculator(pumaBirthRate, pumaDeathRate, pumaDiffusionRate);

        /*
         * case 3: currently 500 pumas, 500 hares,  all neighbours are water region
         * 					  (0,0)	
         * 			(0,0)	(500,500)	(0,0)
         * 					  (0,0)
         * 
         *nextPumaDensity = currentPumaDensity + timeStep * (pumaBirthRate*currentHareDensity*currentPumaDensity - pumaDeathRate*currentPumaDensity )
         */
        double currentPumaDensity = 500;
        double totalNeighbourPumaDensity = 0;
        int neighbourLandCount = 0;
        double timeStep = 0.4;
        double currentHareDensity = 500;

        double expectedPumaDensity = currentPumaDensity + timeStep * (pumaBirthRate * currentHareDensity * currentPumaDensity - pumaDeathRate * currentPumaDensity);

        double calculatedPumaDensity
                = calculator.getNextPumaDensity(
                        currentPumaDensity,
                        totalNeighbourPumaDensity,
                        neighbourLandCount,
                        timeStep,
                        currentHareDensity);

        assertEquals(expectedPumaDensity, calculatedPumaDensity, ERROR_TOLERANCE);
    }
}

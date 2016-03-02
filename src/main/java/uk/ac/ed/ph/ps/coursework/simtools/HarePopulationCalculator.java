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

/**
 *
 * @author Sheng Wang (s1532126@sms.ed.ac.uk)
 * 
 */
public class HarePopulationCalculator {
    
	private double hareBirthRate;
	private double harePredationRate;
	private double hareDiffusionRate;

    /**
     * Hare Calculator constructor
     *
     * @param hareBirthRate Hare birth rate 
     * @param harePredationRate Hare predation rate
     * @param hareDiffusionRate Hare diffusion rate
     */
    public HarePopulationCalculator(double hareBirthRate, double harePredationRate, double hareDiffusionRate) {
        this.hareBirthRate = hareBirthRate;
        this.harePredationRate = harePredationRate;
        this.hareDiffusionRate = hareDiffusionRate;
    }
 
    /**
     * Calculate the next Hare population value for a given set of circumstances
     *
     * @param currentHareDensity Current hare density 
     * @param totalNeighbourHareDensity Total hare density in the neighbourhood 
     * @param neighbourLandCount Land count in the neighbourhood
     * @param timeStep Timestep
     * @param currentPumaDensity Current puma density
     * 
     * @return nextHareDensity
     */
    public double getNextHareDensity(
            double currentHareDensity,
            double totalNeighbourHareDensity,
            int neighbourLandCount,
            double timeStep,
            double currentPumaDensity
            ) {
    	
    	double nextHareDensity = currentHareDensity + timeStep * (this.hareBirthRate*currentHareDensity - this.harePredationRate*currentHareDensity*currentPumaDensity + this.hareDiffusionRate * ((totalNeighbourHareDensity) - neighbourLandCount*currentHareDensity));
    	
        return nextHareDensity;
    }
}
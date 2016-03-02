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
public class PumaPopulationCalculator {

	private double pumaBirthRate;
	private double pumaDeathRate;
	private double pumaDiffusionRate;
	
	/**
	 * 
	 * @param pumaBirthRate Puma birth rate
	 * @param pumaDeathRate Puma death rate
	 * @param pumaDiffusionRate Puma diffusion rate
	 */
	public PumaPopulationCalculator(double pumaBirthRate, double pumaDeathRate, double pumaDiffusionRate) {
		this.pumaBirthRate = pumaBirthRate;
        this.pumaDeathRate = pumaDeathRate;
        this.pumaDiffusionRate = pumaDiffusionRate;
	}
    
    /**
     * Calculate the next Puma population value for a given set of circumstances
     *
     * @param currentPumaDensity Current puma density 
     * @param totalNeighbourPumaDensity Total puma density in the neighbourhood 
     * @param neighbourLandCount Land count in the neighbourhood
     * @param timeStep Timestep
     * @param currentHareDensity Current hare density  
     * 
     * @return nextPumaDensity
     */
    public double getNextPumaDensity(
            double currentPumaDensity,
            double totalNeighbourPumaDensity,
            int neighbourLandCount,
            double timeStep,
            double currentHareDensity
            ) {
    	
    	double nextPumaDensity = currentPumaDensity + timeStep*(this.pumaBirthRate*currentHareDensity*currentPumaDensity - this.pumaDeathRate*currentPumaDensity + this.pumaDiffusionRate*((totalNeighbourPumaDensity) - neighbourLandCount*currentPumaDensity));
    	
        return nextPumaDensity;
    }
}
/*
 * The MIT License
 *
 * Copyright 2015 George Zacharis (s1569743@sms.ed.ac.uk)
 * and Konstantinos Zacharis (s1569729@sms.ed.ac.uk).
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

import uk.ac.ed.ph.ps.coursework.model.LandRegion;
import uk.ac.ed.ph.ps.coursework.model.Landscape;
import uk.ac.ed.ph.ps.coursework.model.LandscapeImpl;
import uk.ac.ed.ph.ps.coursework.model.Region;
import uk.ac.ed.ph.ps.coursework.model.WaterRegion;

/**
 * Checks that the Simulator object created is calculating the new densities through the 
 * doSimulationStep method.
 * 
 * @author George Zacharis (s1569743@sms.ed.ac.uk)
 * @author Konstantinos Zacharis (s1569729@sms.ed.ac.uk)
 *
 */
public class SimulatorTest {

	@Test
	public void test(){
		
		// Create test PopulationCalculators
		HarePopulationCalculator harePC = new HarePopulationCalculator(0.08, 0.04, 0.2);
		PumaPopulationCalculator pumaPC = new PumaPopulationCalculator(0.02, 0.06, 0.2);
		
		// Initialise NeighbourCount and a Landscape object
		NeighbourCount nc = new NeighbourCount();
		double hareDensitytest = 10.0;
		double pumaDensitytest = 5.0;
		
		// region1 --> WaterRegion
		Region region1 = new WaterRegion();
		
		// region2 --> LandRegion
		Region region2 = new LandRegion();
		region2.setHareDensity(hareDensitytest);
		region2.setPumaDensity(pumaDensitytest);
		
		//region3 --> LandRegion
		Region region3 = new LandRegion();
		region3.setHareDensity(hareDensitytest);
		region3.setPumaDensity(pumaDensitytest);
		
		// region4 --> WaterRegion
		Region region4 = new WaterRegion();
		
		// Initialise landscape with WaterRegions
		Landscape landscape = new LandscapeImpl(2, 2);
		
		landscape.setRegion(0, 0, region1);
		landscape.setRegion(0, 1, region2);
		landscape.setRegion(1, 0, region3);
		landscape.setRegion(1, 1, region4);
		
		// Construct a Simulator with the above and simulate
		Simulator sim = new Simulator (2,2,harePC,pumaPC,nc);
		sim.doSimulationStep(landscape, 0.04);
		
		// WaterRegion Hare/Puma densities should remain the same and equal to 0
		assertEquals(" Hare density expected to remain zero ",region1.getHareDensity(),0.0,0.1);
		assertEquals(" Puma density expected to remain zero ",region1.getPumaDensity(),0.0,0.1);
		assertEquals(" Hare density expected to remain zero ",region4.getHareDensity(),0.0,0.1);
		assertEquals(" Puma density expected to remain zero ",region4.getPumaDensity(),0.0,0.1);
		
		// LandRegion Hare/Puma densities should be different than the original
		assertTrue("Hare density should be changed", region2.getHareDensity() != 10.0);
		assertTrue("Puma density should be changed", region2.getPumaDensity() != 5.0);
		assertTrue("Hare density should be changed", region3.getHareDensity() != 10.0);
		assertTrue("Puma density should be changed", region3.getPumaDensity() != 5.0);
		
		
		
	}
}

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
 *  The test to check the functionality of the NeighbourCount is implemented in the following way.
 *  
 *  Create 4 Regions with fixed densities (2 Water Regions and 2 Land Regions). 
 *  So we have a 2x2 Landscape. Place them in a Landscape object ([0,0] Water Region, [0,1] LandRegion, 
 *  [1,0] LandRegion, [1,1] WaterRegion).
 *  
 *  The assumption is that NeighbourCount method for the [0,1] LandRegion should return the 
 *  same results as in [1,0] LandRegion. Also the NeighbourCount method for the [0,0] WaterRegion 
 *  should  return the same results as in [1,1] WaterRegion. 
 *  
 * @author George Zacharis (s1569743@sms.ed.ac.uk)
 * @author Konstantinos Zacharis (s1569729@sms.ed.ac.uk) 
 *
 */
public class NeighbourCountTest {

	
	@Test
	public void test() {
		
		NeighbourCount nc = new NeighbourCount();
		double hareDensitytest = 10.0;
		double pumaDensitytest = 5.0;
		
		Region region1 = new WaterRegion();
		Region region2 = new LandRegion();
		region2.setHareDensity(hareDensitytest);
		region2.setPumaDensity(pumaDensitytest);
		
		Region region3 = new LandRegion();
		region3.setHareDensity(hareDensitytest);
		region3.setPumaDensity(pumaDensitytest);
		Region region4 = new WaterRegion();
		
		Landscape landscape = new LandscapeImpl(2, 2);
		
		// setRegion(width,height)
		landscape.setRegion(0, 0, region1);
		landscape.setRegion(1, 0, region2);
		landscape.setRegion(0, 1, region3);
		landscape.setRegion(1, 1, region4);
		
		// getNeighbourCountResult(landscape,width,height)
		NeighbourCountResult ncr1 = nc.getNeighbourCountResult(landscape, 0, 0);
		NeighbourCountResult ncr2 = nc.getNeighbourCountResult(landscape, 1, 0);
		NeighbourCountResult ncr3 = nc.getNeighbourCountResult(landscape, 0, 1);
		NeighbourCountResult ncr4 = nc.getNeighbourCountResult(landscape, 1, 1);
		
		assertEquals(" Check the density of hares in neighbour cells ",ncr1.getNeighbourHareSum(), ncr4.getNeighbourHareSum(), 0.1);
		assertEquals(" Check the density of pumas in neighbour cells ",ncr1.getNeighbourPumaSum(), ncr4.getNeighbourPumaSum(), 0.1);
		assertEquals(" Check the sum of neighbour cells ",ncr1.getNeighbourLandCount(), ncr4.getNeighbourLandCount());
		
		assertEquals(" Check the density of hares in neighbour cells ",ncr2.getNeighbourHareSum(), ncr3.getNeighbourHareSum(), 0.1);
		assertEquals(" Check the density of pumas in neighbour cells ",ncr2.getNeighbourPumaSum(), ncr3.getNeighbourPumaSum(), 0.1);
		assertEquals(" Check the sum of neighbour cells ",ncr2.getNeighbourLandCount(), ncr3.getNeighbourLandCount());
		
		
		
	}

}

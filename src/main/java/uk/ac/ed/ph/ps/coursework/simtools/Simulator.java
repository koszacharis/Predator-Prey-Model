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

import uk.ac.ed.ph.ps.coursework.model.Landscape;
import uk.ac.ed.ph.ps.coursework.model.Region;

/**
 * The Simulator class is calculating the new densities of a Landscape object
 * 
 * @author George Zacharis (s1569743@sms.ed.ac.uk)
 * @author Konstantinos Zacharis (s1569729@sms.ed.ac.uk)
 */
public class Simulator {

	private final HarePopulationCalculator harePC;
	private final PumaPopulationCalculator pumaPC;
	private final NeighbourCount neighbourCount;

	private double[][] newHareDensities;
	private double[][] newPumaDensities;

	/**
	 * Constructor of a Simulator object with the width, height,
	 * HarePopulationCalculator, PumePopulationCalculator and NeighbourCount
	 * 
	 * @param width
	 *            the width of Landscape
	 * @param height
	 *            the height of Landscape
	 * @param hpc
	 *            the HarePopulationCalculator to be used
	 * @param ppc
	 *            the PumaPopulationCalculator to be used
	 * @param neighbourCount
	 *            the NeighbourCount to be used
	 */
	public Simulator(int width, int height, HarePopulationCalculator hpc, PumaPopulationCalculator ppc,
			NeighbourCount neighbourCount) {
		this.harePC = hpc;
		this.pumaPC = ppc;
		this.neighbourCount = neighbourCount;

		this.newHareDensities = new double[width][height];
		this.newPumaDensities = new double[width][height];
	}

	/**
	 * This method is simulating the changes in densities of Hares and Pumas and
	 * then updating the new densities in the Landscape object.
	 * 
	 * @param landscape
	 *            the Landscape object used by the simulation
	 * @param timeStep
	 *            the new timeStep given for the simulation
	 */
	public void doSimulationStep(Landscape landscape, double timeStep) {

		int height = landscape.getHeight();
		int width = landscape.getWidth();

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {

				if (landscape.getRegion(x, y).getLandCount() == 1) {

					Region region = landscape.getRegion(x, y);
					NeighbourCountResult neighbourCounts = this.neighbourCount.getNeighbourCountResult(landscape, x, y);

					// Calculate the new Hare density
					newHareDensities[x][y] = harePC.getNextHareDensity(region.getHareDensity(),
							neighbourCounts.getNeighbourHareSum(), neighbourCounts.getNeighbourLandCount(), timeStep,
							region.getPumaDensity());

					// Calculate the new Puma density
					newPumaDensities[x][y] = pumaPC.getNextPumaDensity(region.getPumaDensity(),
							neighbourCounts.getNeighbourPumaSum(), neighbourCounts.getNeighbourLandCount(), timeStep,
							region.getHareDensity());
				}

			}
		}

		// put the new values back into the Landscape
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {

				Region region = landscape.getRegion(x, y);
				if (landscape.getRegion(x, y).getLandCount() == 1) {

					// Check if the new calculated density is lower than zero
					// and replace it with zero
					if (newHareDensities[x][y] < 0) {
						region.setHareDensity(0);
						region.setPumaDensity(0);
					}

					// Store the new density
					else {
						region.setHareDensity(newHareDensities[x][y]);
						region.setPumaDensity(newPumaDensities[x][y]);
					}
				}

			}
		}

	}

}

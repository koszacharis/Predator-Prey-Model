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

/**
 * Checking that the NeighbourCountResult class returns the correct values of the given arguments.
 * 
 * @author George Zacharis (s1569743@sms.ed.ac.uk)
 * @author Konstantinos Zacharis (s1569729@sms.ed.ac.uk)
 *
 */
public class NeightbourCountResultTest {

	final int landCount = 5;
	final double pumaNeighboursDensity = 20.0;
	final double hareNeighboursDensity = 20.0;
	
	NeighbourCountResult neighbourCountResult = new NeighbourCountResult(landCount, hareNeighboursDensity, pumaNeighboursDensity);
	
	@Test
	public void checkgetNeighbourLandCount() {
		
		
		assertEquals("Check if the LandCount returns the correct value",
				neighbourCountResult.getNeighbourLandCount(),5);
	}
	
	@Test
	public void checkgetNeighbourHareSum() {
		
		assertEquals("Check if the getNeighbourHareSum returns the correct value",
				neighbourCountResult.getNeighbourHareSum(), 20.0, 0.1);
	}
	
	@Test
	public void checkgetNeighbourPumaSum() {
		
		assertEquals("Check if the getNeighbourPumaSum returns the correct value",
				neighbourCountResult.getNeighbourPumaSum(), 20.0, 0.1);
	}

}

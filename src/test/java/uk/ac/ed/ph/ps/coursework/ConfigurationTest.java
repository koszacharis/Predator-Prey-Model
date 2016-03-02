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
package uk.ac.ed.ph.ps.coursework;

import com.beust.jcommander.JCommander;
import static org.easymock.EasyMock.mock;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static uk.ac.ed.ph.ps.coursework.Configuration.FILL_METHOD;
import static uk.ac.ed.ph.ps.coursework.Configuration.HARE_BIRTH_RATE;
import static uk.ac.ed.ph.ps.coursework.Configuration.HARE_BIRTH_RATE_SHORT;
import static uk.ac.ed.ph.ps.coursework.Configuration.HARE_DIFFUSION_RATE;
import static uk.ac.ed.ph.ps.coursework.Configuration.HARE_DIFFUSION_RATE_SHORT;
import static uk.ac.ed.ph.ps.coursework.Configuration.HARE_PREDATION_RATE;
import static uk.ac.ed.ph.ps.coursework.Configuration.HARE_PREDATION_RATE_SHORT;
import static uk.ac.ed.ph.ps.coursework.Configuration.LANDSCAPE_FILENAME;
import static uk.ac.ed.ph.ps.coursework.Configuration.MAX_HARE_START_DENSITY;
import static uk.ac.ed.ph.ps.coursework.Configuration.MAX_PUMA_START_DENSITY;
import static uk.ac.ed.ph.ps.coursework.Configuration.OUTPUT_FILENAME_ROOT;
import static uk.ac.ed.ph.ps.coursework.Configuration.OUTPUT_TIME_STEP_INTERVAL;
import static uk.ac.ed.ph.ps.coursework.Configuration.OUTPUT_TIME_STEP_INTERVAL_SHORT;
import static uk.ac.ed.ph.ps.coursework.Configuration.PUMA_BIRTH_RATE;
import static uk.ac.ed.ph.ps.coursework.Configuration.PUMA_BIRTH_RATE_SHORT;
import static uk.ac.ed.ph.ps.coursework.Configuration.PUMA_DIFFUSION_RATE;
import static uk.ac.ed.ph.ps.coursework.Configuration.PUMA_DIFFUSION_RATE_SHORT;
import static uk.ac.ed.ph.ps.coursework.Configuration.PUMA_MORTALITY_RATE;
import static uk.ac.ed.ph.ps.coursework.Configuration.PUMA_MORTALITY_RATE_SHORT;
import static uk.ac.ed.ph.ps.coursework.Configuration.TIME_STEP;
import static uk.ac.ed.ph.ps.coursework.Configuration.TIME_STEP_SHORT;
import static uk.ac.ed.ph.ps.coursework.Configuration.USAGE;
import static uk.ac.ed.ph.ps.coursework.Configuration.USAGE_HELP;
import static uk.ac.ed.ph.ps.coursework.Configuration.USAGE_HELP_SHORT;
import static uk.ac.ed.ph.ps.coursework.Configuration.USAGE_QUESTION_MARK;

/**
 *
 * @author Andy Law (s1578554@sms.ed.ac.uk)
 */
public class ConfigurationTest {

    /**
     * Slop value to be used when comparing double values
     */
    private static final double DOUBLE_SLOP_VALUE = 0.00000001;

    public ConfigurationTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getLandscapeFilename method, of class Configuration.
     */
    @Test
    public void testGetLandscapeFilename() {
        Configuration instance = new Configuration();

        String expResult = "";
        String result = instance.getLandscapeFilename();
        assertEquals(expResult, result);

    }

    /**
     * Test of setLandscapeFilename method, of class Configuration.
     */
    @Test
    public void testSetLandscapeFilename() {
        final String landscapeFilename = "NEW_NAME";
        Configuration instance = new Configuration();
        instance.setLandscapeFilename(landscapeFilename);

        String result = instance.getLandscapeFilename();
        assertEquals(landscapeFilename, result);
    }

    /**
     * Test that the JCommander parsing of the landscapeFilename works.
     */
    @Test
    public void testLandscapeFileNameCommandLine() {
        final String filename = "elephantMan.dat";
        final String[] args = {LANDSCAPE_FILENAME, filename};
        Configuration instance = new Configuration();
        JCommander jCommander = new JCommander(instance, args);

        assertEquals(filename, instance.getLandscapeFilename());
    }

    /**
     * Test of getOutputFilenameRoot method, of class Configuration.
     */
    @Test
    public void testGetOutputFilenameRoot() {
        Configuration instance = new Configuration();

        String expResult = "";
        String result = instance.getOutputFilenameRoot();
        assertEquals(expResult, result);

    }

    /**
     * Test of setOutputFilenameRoot method, of class Configuration.
     */
    @Test
    public void testSetOutputFilenameRoot() {
        final String outputFilenameRoot = "NEW_NAME";
        Configuration instance = new Configuration();
        instance.setOutputFilenameRoot(outputFilenameRoot);

        String result = instance.getOutputFilenameRoot();
        assertEquals(outputFilenameRoot, result);
    }

    /**
     * Test that the JCommander parsing of the outputFilenameRoot works.
     */
    @Test
    public void testOutputFileNameRootCommandLine() {
        final String filename = "elephantMan.dat";
        final String[] args = {OUTPUT_FILENAME_ROOT, filename};
        Configuration instance = new Configuration();
        JCommander jCommander = new JCommander(instance, args);

        assertEquals(filename, instance.getOutputFilenameRoot());
    }

    /**
     * Test of getHareBirthRate method. Checking the default value
     */
    @Test
    public void testGetHareBirthRate() {
        Configuration instance = new Configuration();
        // The default hare birth rate that we expect. Defined here to prevent
        // unwitting changes in the Configuration object
        final double CHECK_HARE_BIRTH_RATE_DEFAULT = 0.08;

        final double result = instance.getHareBirthRate();
        assertEquals(CHECK_HARE_BIRTH_RATE_DEFAULT, result, DOUBLE_SLOP_VALUE);

    }

    /**
     * Test of setHareBirthRate method. Checking we can set a value
     */
    @Test
    public void testSetHareBirthRate() {
        Configuration instance = new Configuration();
        final double newValue = 99.3221;

        instance.setHareBirthRate(newValue);
        double result = instance.getHareBirthRate();
        assertEquals(newValue, result, DOUBLE_SLOP_VALUE);

    }

    /**
     * Test that the JCommander parsing of the long-form hare birth rate works.
     */
    @Test
    public void testHareBirthRateCommandLine() {
        final String birthRateString = "0.3401";
        final String[] args = {HARE_BIRTH_RATE, birthRateString};

        final double birthRate = Double.parseDouble(birthRateString);
        Configuration instance = new Configuration();
        JCommander jCommander = new JCommander(instance, args);

        assertEquals(birthRate, instance.getHareBirthRate(), DOUBLE_SLOP_VALUE);
    }

    /**
     * Test that the JCommander parsing of the short-form hare birth rate works.
     */
    @Test
    public void testShortFormHareBirthRateCommandLine() {
        final String birthRateString = "0.73321";
        final String[] args = {HARE_BIRTH_RATE_SHORT, birthRateString};

        final double birthRate = Double.parseDouble(birthRateString);
        Configuration instance = new Configuration();
        JCommander jCommander = new JCommander(instance, args);

        assertEquals(birthRate, instance.getHareBirthRate(), DOUBLE_SLOP_VALUE);
    }

    /**
     * Test of getHarePredationRate method. Checking the default value
     */
    @Test
    public void testGetHarePredationRate() {
        Configuration instance = new Configuration();
        // The default hare predation rate that we expect. Defined here to prevent
        // unwitting changes in the Configuration object
        final double CHECK_HARE_PREDATION_RATE_DEFAULT = 0.04;

        final double result = instance.getHarePredationRate();
        assertEquals(CHECK_HARE_PREDATION_RATE_DEFAULT, result, DOUBLE_SLOP_VALUE);

    }

    /**
     * Test of setHarePredationRate method. Checking we can set a value
     */
    @Test
    public void testSetHarePredationRate() {
        Configuration instance = new Configuration();
        final double newValue = 354.5119;

        instance.setHarePredationRate(newValue);
        double result = instance.getHarePredationRate();
        assertEquals(newValue, result, DOUBLE_SLOP_VALUE);

    }

    /**
     * Test that the JCommander parsing of the long-form hare predation rate
     * works.
     */
    @Test
    public void testHarePredationRateCommandLine() {
        final String predationRateString = "0.005";
        final String[] args = {HARE_PREDATION_RATE, predationRateString};

        final double predationRate = Double.parseDouble(predationRateString);
        Configuration instance = new Configuration();
        JCommander jCommander = new JCommander(instance, args);

        assertEquals(predationRate, instance.getHarePredationRate(), DOUBLE_SLOP_VALUE);
    }

    /**
     * Test that the JCommander parsing of the short-form hare predation rate
     * works.
     */
    @Test
    public void testShortFormHarePredationRateCommandLine() {
        final String predationRateString = "0.30098";
        final String[] args = {HARE_PREDATION_RATE_SHORT, predationRateString};

        final double predationRate = Double.parseDouble(predationRateString);
        Configuration instance = new Configuration();
        JCommander jCommander = new JCommander(instance, args);

        assertEquals(predationRate, instance.getHarePredationRate(), DOUBLE_SLOP_VALUE);
    }

    /**
     * Test of getHareDiffusionRate method. Checking the default value
     */
    @Test
    public void testGetHareDiffusionRate() {
        Configuration instance = new Configuration();
        // The default hare diffusion rate that we expect. Defined here to prevent
        // unwitting changes in the Configuration object
        final double CHECK_HARE_DIFFUSION_RATE_DEFAULT = 0.2;

        final double result = instance.getHareDiffusionRate();
        assertEquals(CHECK_HARE_DIFFUSION_RATE_DEFAULT, result, DOUBLE_SLOP_VALUE);

    }

    /**
     * Test of setHareDiffusionRate method. Checking we can set a value
     */
    @Test
    public void testSetHareDiffusionRate() {
        Configuration instance = new Configuration();
        final double newValue = 12.1;

        instance.setHareDiffusionRate(newValue);
        double result = instance.getHareDiffusionRate();
        assertEquals(newValue, result, DOUBLE_SLOP_VALUE);

    }

    /**
     * Test that the JCommander parsing of the long-form hare diffusion rate
     * works.
     */
    @Test
    public void testHareDiffusionRateCommandLine() {
        final String diffusionRateString = "0.57913";
        final String[] args = {HARE_DIFFUSION_RATE, diffusionRateString};

        final double diffusionRate = Double.parseDouble(diffusionRateString);
        Configuration instance = new Configuration();
        JCommander jCommander = new JCommander(instance, args);

        assertEquals(diffusionRate, instance.getHareDiffusionRate(), DOUBLE_SLOP_VALUE);
    }

    /**
     * Test that the JCommander parsing of the short-form hare diffusion rate
     * works.
     */
    @Test
    public void testShortFormHareDiffusionRateCommandLine() {
        final String diffusionRateString = "0.68024";
        final String[] args = {HARE_DIFFUSION_RATE_SHORT, diffusionRateString};

        final double diffusionRate = Double.parseDouble(diffusionRateString);
        Configuration instance = new Configuration();
        JCommander jCommander = new JCommander(instance, args);

        assertEquals(diffusionRate, instance.getHareDiffusionRate(), DOUBLE_SLOP_VALUE);
    }

    /**
     * Test of getPumaBirthRate method. Checking the default value
     */
    @Test
    public void testGetPumaBirthRate() {
        Configuration instance = new Configuration();
        // The default puma birth rate that we expect. Defined here to prevent
        // unwitting changes in the Configuration object
        final double CHECK_PUMA_BIRTH_RATE_DEFAULT = 0.02;

        double result = instance.getPumaBirthRate();
        assertEquals(CHECK_PUMA_BIRTH_RATE_DEFAULT, result, DOUBLE_SLOP_VALUE);

    }

    /**
     * Test of setPumaBirthRate method. Checking we can set a value
     */
    @Test
    public void testSetPumaBirthRate() {
        Configuration instance = new Configuration();
        final double newValue = 99.3221;

        instance.setPumaBirthRate(newValue);
        double result = instance.getPumaBirthRate();
        assertEquals(newValue, result, DOUBLE_SLOP_VALUE);

    }

    /**
     * Test that the JCommander parsing of the long-form puma birth rate works.
     */
    @Test
    public void testPumaBirthRateCommandLine() {
        final String birthRateString = "0.3401";
        final String[] args = {PUMA_BIRTH_RATE, birthRateString};

        final double birthRate = Double.parseDouble(birthRateString);
        Configuration instance = new Configuration();
        JCommander jCommander = new JCommander(instance, args);

        assertEquals(birthRate, instance.getPumaBirthRate(), DOUBLE_SLOP_VALUE);
    }

    /**
     * Test that the JCommander parsing of the short-form puma birth rate works.
     */
    @Test
    public void testShortFormPumaBirthRateCommandLine() {
        final String birthRateString = "0.73321";
        final String[] args = {PUMA_BIRTH_RATE_SHORT, birthRateString};

        final double birthRate = Double.parseDouble(birthRateString);
        Configuration instance = new Configuration();
        JCommander jCommander = new JCommander(instance, args);

        assertEquals(birthRate, instance.getPumaBirthRate(), DOUBLE_SLOP_VALUE);
    }

    /**
     * Test of getPumaMortalityRate method. Checking the default value
     */
    @Test
    public void testGetPumaMortalityRate() {
        Configuration instance = new Configuration();
        // The default puma mortality rate that we expect. Defined here to prevent
        // unwitting changes in the Configuration object
        final double CHECK_PUMA_MORTALITY_RATE_DEFAULT = 0.06;

        double result = instance.getPumaMortalityRate();
        assertEquals(CHECK_PUMA_MORTALITY_RATE_DEFAULT, result, DOUBLE_SLOP_VALUE);

    }

    /**
     * Test of setPumaMortalityRate method. Checking we can set a value
     */
    @Test
    public void testSetPumaMortalityRate() {
        Configuration instance = new Configuration();
        final double newValue = 99.3221;

        instance.setPumaMortalityRate(newValue);
        double result = instance.getPumaMortalityRate();
        assertEquals(newValue, result, DOUBLE_SLOP_VALUE);

    }

    /**
     * Test that the JCommander parsing of the long-form puma mortality rate
     * works.
     */
    @Test
    public void testPumaMortalityRateCommandLine() {
        final String mortalityRateString = "0.3401";
        final String[] args = {PUMA_MORTALITY_RATE, mortalityRateString};

        final double mortalityRate = Double.parseDouble(mortalityRateString);
        Configuration instance = new Configuration();
        JCommander jCommander = new JCommander(instance, args);

        assertEquals(mortalityRate, instance.getPumaMortalityRate(), DOUBLE_SLOP_VALUE);
    }

    /**
     * Test that the JCommander parsing of the short-form puma mortality rate
     * works.
     */
    @Test
    public void testShortFormPumaMortalityRateCommandLine() {
        final String mortalityRateString = "0.73321";
        final String[] args = {PUMA_MORTALITY_RATE_SHORT, mortalityRateString};

        final double mortalityRate = Double.parseDouble(mortalityRateString);
        Configuration instance = new Configuration();
        JCommander jCommander = new JCommander(instance, args);

        assertEquals(mortalityRate, instance.getPumaMortalityRate(), DOUBLE_SLOP_VALUE);
    }

    /**
     * Test of getPumaDiffusionRate method. Checking the default value
     */
    @Test
    public void testGetPumaDiffusionRate() {
        Configuration instance = new Configuration();
        // The default puma diffusion rate that we expect. Defined here to prevent
        // unwitting changes in the Configuration object
        final double CHECK_PUMA_DIFFUSION_RATE_DEFAULT = 0.2;

        final double result = instance.getPumaDiffusionRate();
        assertEquals(CHECK_PUMA_DIFFUSION_RATE_DEFAULT, result, DOUBLE_SLOP_VALUE);

    }

    /**
     * Test of setPumaDiffusionRate method. Checking we can set a value
     */
    @Test
    public void testSetPumaDiffusionRate() {
        Configuration instance = new Configuration();
        final double newValue = 12.1;

        instance.setPumaDiffusionRate(newValue);
        double result = instance.getPumaDiffusionRate();
        assertEquals(newValue, result, DOUBLE_SLOP_VALUE);

    }

    /**
     * Test that the JCommander parsing of the long-form puma diffusion rate
     * works.
     */
    @Test
    public void testPumaDiffusionRateCommandLine() {
        final String diffusionRateString = "0.57913";
        final String[] args = {PUMA_DIFFUSION_RATE, diffusionRateString};

        final double diffusionRate = Double.parseDouble(diffusionRateString);
        Configuration instance = new Configuration();
        JCommander jCommander = new JCommander(instance, args);

        assertEquals(diffusionRate, instance.getPumaDiffusionRate(), DOUBLE_SLOP_VALUE);
    }

    /**
     * Test that the JCommander parsing of the short-form puma diffusion rate
     * works.
     */
    @Test
    public void testShortFormPumaDiffusionRateCommandLine() {
        final String diffusionRateString = "0.68024";
        final String[] args = {PUMA_DIFFUSION_RATE_SHORT, diffusionRateString};

        final double diffusionRate = Double.parseDouble(diffusionRateString);
        Configuration instance = new Configuration();
        JCommander jCommander = new JCommander(instance, args);

        assertEquals(diffusionRate, instance.getPumaDiffusionRate(), DOUBLE_SLOP_VALUE);
    }

    /**
     * Test of setTimeStep method. Checking we can set a value
     */
    @Test
    public void testSetTimeStep() {
        Configuration instance = new Configuration();
        final double newValue = 0.87;

        instance.setTimeStep(newValue);
        double result = instance.getTimeStep();
        assertEquals(newValue, result, DOUBLE_SLOP_VALUE);

    }

    /**
     * Test that the JCommander parsing of the long-form time step works.
     */
    @Test
    public void testTimeStepCommandLine() {
        final String timeStepString = "0.9944";
        final String[] args = {TIME_STEP, timeStepString};

        final double timeStep = Double.parseDouble(timeStepString);
        Configuration instance = new Configuration();
        JCommander jCommander = new JCommander(instance, args);

        assertEquals(timeStep, instance.getTimeStep(), DOUBLE_SLOP_VALUE);
    }

    /**
     * Test that the JCommander parsing of the short-form time step works.
     */
    @Test
    public void testShortFormTimeStepCommandLine() {
        final String timeStepString = "0.00103";
        final String[] args = {TIME_STEP_SHORT, timeStepString};

        final double timeStep = Double.parseDouble(timeStepString);
        Configuration instance = new Configuration();
        JCommander jCommander = new JCommander(instance, args);

        assertEquals(timeStep, instance.getTimeStep(), DOUBLE_SLOP_VALUE);
    }

    /**
     * Test of setOutputTimeStepInterval method. Checking we can set a value
     */
    @Test
    public void testSetOutputTimeStepInterval() {
        Configuration instance = new Configuration();
        final int newValue = 13;

        instance.setOutputTimeStepInterval(newValue);
        int result = instance.getOutputTimeStepInterval();
        assertEquals(newValue, result);

    }

    /**
     * Test that the JCommander parsing of the long-form time step works.
     */
    @Test
    public void testOutputTimeStepIntervalCommandLine() {
        final String timeStepString = "4";
        final String[] args = {OUTPUT_TIME_STEP_INTERVAL, timeStepString};

        final Integer timeStep = Integer.parseInt(timeStepString);
        Configuration instance = new Configuration();
        JCommander jCommander = new JCommander(instance, args);

        assertEquals(timeStep, instance.getOutputTimeStepInterval());
    }

    /**
     * Test that the JCommander parsing of the short-form time step works.
     */
    @Test
    public void testShortFormOutputTimeStepIntervalCommandLine() {
        final String timeStepString = "27";
        final String[] args = {OUTPUT_TIME_STEP_INTERVAL_SHORT, timeStepString};

        final Integer timeStep = Integer.parseInt(timeStepString);
        Configuration instance = new Configuration();
        JCommander jCommander = new JCommander(instance, args);

        assertEquals(timeStep, instance.getOutputTimeStepInterval());
    }

    /**
     * Test that the JCommander parsing of the long-form usage works.
     */
    @Test
    public void testUsageCommandLine() {
        final String[] args = {USAGE};

        final Boolean usage = true;
        Configuration instance = new Configuration();
        JCommander jCommander = new JCommander(instance, args);

        assertTrue(instance.isUsageNeeded());
    }

    /**
     * Test that the JCommander parsing of the short-form usage works.
     */
    @Test
    public void testUsageHelpShortCommandLine() {
        final String[] args = {USAGE_HELP_SHORT};

        final Boolean usage = true;
        Configuration instance = new Configuration();
        JCommander jCommander = new JCommander(instance, args);

        assertTrue(instance.isUsageNeeded());
    }

    /**
     * Test that the JCommander parsing of the 'help' usage works.
     */
    @Test
    public void testUsageHelpCommandLine() {
        final String[] args = {USAGE_HELP};

        final Boolean usage = true;
        Configuration instance = new Configuration();
        JCommander jCommander = new JCommander(instance, args);

        assertTrue(instance.isUsageNeeded());
    }

    /**
     * Test that the JCommander parsing of the question mark usage works.
     */
    @Test
    public void testUsageQuestionMarkCommandLine() {
        final String[] args = {USAGE_QUESTION_MARK};

        final Boolean usage = true;
        Configuration instance = new Configuration();
        JCommander jCommander = new JCommander(instance, args);

        assertTrue(instance.isUsageNeeded());
    }

    /**
     * Test of isUsageNeeded() method. Checking the default value
     */
    @Test
    public void testIsUsageNeeded() {
        Configuration instance = new Configuration();

        assertFalse(instance.isUsageNeeded());

    }

    /**
     * Test of setUsage() method. Checking we can set a value
     */
    @Test
    public void testSetUsage() {
        Configuration instance = new Configuration();
        final boolean newValue = true;

        instance.setUsageNeeded(newValue);
        assertTrue(instance.isUsageNeeded());

    }

    /**
     * Test that the JCommander object is null by default
     */
    @Test
    public void testJCommanderIsNull() {
        Configuration instance = new Configuration();

        assertNull(instance.getJCommander());
    }

    /**
     * Test that the JCommander object can be set
     */
    @Test
    public void testJCommanderCanBeSet() {
        Configuration instance = new Configuration();
        JCommander jCommander = mock(JCommander.class);

        instance.setJCommander(jCommander);

        JCommander result = instance.getJCommander();
        assertNotNull(result);
        assertSame(jCommander, result);
    }

    /**
     * Test of getMaxStartHareDensity method. Checking the default value
     */
    @Test
    public void testGetMaxStartHareDensity() {
        Configuration instance = new Configuration();
        // The default maximum starting hare density that we expect. Defined here to prevent
        // unwitting changes in the Configuration object
        final double CHECK_MAX_START_HARE_DENSITY_DEFAULT = 10.0;

        double result = instance.getMaxStartHareDensity();
        assertEquals(CHECK_MAX_START_HARE_DENSITY_DEFAULT, result, DOUBLE_SLOP_VALUE);

    }

    /**
     * Test of setMaxStartHareDensity method. Checking we can set a value
     */
    @Test
    public void testSetMaxStartHareDensity() {
        Configuration instance = new Configuration();
        final double newValue = 99.3221;

        instance.setMaxStartHareDensity(newValue);
        double result = instance.getMaxStartHareDensity();
        assertEquals(newValue, result, DOUBLE_SLOP_VALUE);

    }

    /**
     * Test that the JCommander parsing of the long-form maximum starting hare
     * density works.
     */
    @Test
    public void testMaxStartHareDensityCommandLine() {
        final String birthRateString = "0.3401";
        final String[] args = {MAX_HARE_START_DENSITY, birthRateString};

        final double birthRate = Double.parseDouble(birthRateString);
        Configuration instance = new Configuration();
        JCommander jCommander = new JCommander(instance, args);

        assertEquals(birthRate, instance.getMaxStartHareDensity(), DOUBLE_SLOP_VALUE);
    }

    /**
     * Test of getMaxStartPumaDensity method. Checking the default value
     */
    @Test
    public void testGetMaxStartPumaDensity() {
        Configuration instance = new Configuration();
        // The default maximum starting puma density that we expect. Defined here to prevent
        // unwitting changes in the Configuration object
        final double CHECK_MAX_START_PUMA_DENSITY_DEFAULT = 10.0;

        double result = instance.getMaxStartPumaDensity();
        assertEquals(CHECK_MAX_START_PUMA_DENSITY_DEFAULT, result, DOUBLE_SLOP_VALUE);

    }

    /**
     * Test of setMaxStartPumaDensity method. Checking we can set a value
     */
    @Test
    public void testSetMaxStartPumaDensity() {
        Configuration instance = new Configuration();
        final double newValue = 99.3221;

        instance.setMaxStartPumaDensity(newValue);
        double result = instance.getMaxStartPumaDensity();
        assertEquals(newValue, result, DOUBLE_SLOP_VALUE);

    }

    /**
     * Test that the JCommander parsing of the long-form maximum starting puma
     * density works.
     */
    @Test
    public void testMaxStartPumaDensityCommandLine() {
        final String birthRateString = "0.3401";
        final String[] args = {MAX_PUMA_START_DENSITY, birthRateString};

        final double birthRate = Double.parseDouble(birthRateString);
        Configuration instance = new Configuration();
        JCommander jCommander = new JCommander(instance, args);

        assertEquals(birthRate, instance.getMaxStartPumaDensity(), DOUBLE_SLOP_VALUE);
    }

    /**
     * Test of getFillMethod method, of class Configuration.
     */
    @Test
    public void testGetFillMethod() {
        Configuration instance = new Configuration();

        String expResult = "random";
        String result = instance.getFillMethod();
        assertEquals(expResult, result);

    }

    /**
     * Test of setFillMethod method, of class Configuration.
     */
    @Test
    public void testSetFillMethod() {
        final String fillMethod = "NEW_NAME";
        Configuration instance = new Configuration();
        instance.setFillMethod(fillMethod);

        String result = instance.getFillMethod();
        assertEquals(fillMethod, result);
    }

    /**
     * Test that the JCommander parsing of the fillMethod works.
     */
    @Test
    public void testFillMethodCommandLine() {
        final String filename = "fromfile";
        final String[] args = {FILL_METHOD, filename};
        Configuration instance = new Configuration();
        JCommander jCommander = new JCommander(instance, args);

        assertEquals(filename, instance.getFillMethod());
    }

}

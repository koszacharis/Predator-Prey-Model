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
import com.beust.jcommander.Parameter;
import uk.ac.ed.ph.ps.coursework.parameters.FillMethodValidator;
import uk.ac.ed.ph.ps.coursework.parameters.PositiveDoubleValidator;
import uk.ac.ed.ph.ps.coursework.parameters.PositiveIntegerValidator;

/**
 *
 * @author Andy Law (s1578554@sms.ed.ac.uk)
 */
public class Configuration {

    /**
     * The JCommand object that was used to parse this Configuration file
     *
     */
    private JCommander jCommander;

    // ----------   Hare stuff   -------------------------
    //
    /**
     * Flag for the hare birth rate setting, plus the variable and default
     * setting
     */
    public static final String HARE_BIRTH_RATE = "--hare-birth-rate";
    public static final String HARE_BIRTH_RATE_SHORT = "-r";
    public static final double HARE_BIRTH_RATE_DEFAULT = 0.08;
    @Parameter(
            names = {HARE_BIRTH_RATE, HARE_BIRTH_RATE_SHORT},
            description = "The birth rate of hares as a fraction of the current population",
            validateWith = PositiveDoubleValidator.class)
    private double hareBirthRate = HARE_BIRTH_RATE_DEFAULT;

    /**
     * Flag for the hare predation rate setting, plus the variable and default
     * setting
     */
    public static final String HARE_PREDATION_RATE = "--hare-predation-rate";
    public static final String HARE_PREDATION_RATE_SHORT = "-a";
    public static final double HARE_PREDATION_RATE_DEFAULT = 0.04;
    @Parameter(
            names = {HARE_PREDATION_RATE, HARE_PREDATION_RATE_SHORT},
            description = "The predation rate at which pumas eat hares",
            validateWith = PositiveDoubleValidator.class)
    private double harePredationRate = HARE_PREDATION_RATE_DEFAULT;

    /**
     * Flag for the hare diffusion rate setting, plus the variable and default
     * setting
     */
    public static final String HARE_DIFFUSION_RATE = "--hare-diffusion-rate";
    public static final String HARE_DIFFUSION_RATE_SHORT = "-k";
    public static final double HARE_DIFFUSION_RATE_DEFAULT = 0.2;
    @Parameter(
            names = {HARE_DIFFUSION_RATE, HARE_DIFFUSION_RATE_SHORT},
            description = "The diffusion rate for hares. The rate at which "
                    + "hares move between neighbouring regions within the "
                    + "Landscape.",
            validateWith = PositiveDoubleValidator.class)
    private double hareDiffusionRate = HARE_DIFFUSION_RATE_DEFAULT;

    // ----------   Puma stuff   -------------------------
    //
    /**
     * Flag for the puma birth rate setting, plus the variable and default
     * setting
     */
    public static final String PUMA_BIRTH_RATE = "--puma-birth-rate";
    public static final String PUMA_BIRTH_RATE_SHORT = "-b";
    public static final double PUMA_BIRTH_RATE_DEFAULT = 0.02;
    @Parameter(
            names = {PUMA_BIRTH_RATE, PUMA_BIRTH_RATE_SHORT},
            description = "The birth rate of pumas per one hare eaten",
            validateWith = PositiveDoubleValidator.class)
    private double pumaBirthRate = PUMA_BIRTH_RATE_DEFAULT;

    /**
     * Flag for the puma mortality rate setting, plus the variable and default
     * setting
     */
    public static final String PUMA_MORTALITY_RATE = "--puma-mortality-rate";
    public static final String PUMA_MORTALITY_RATE_SHORT = "-m";
    public static final double PUMA_MORTALITY_RATE_DEFAULT = 0.06;
    @Parameter(
            names = {PUMA_MORTALITY_RATE, PUMA_MORTALITY_RATE_SHORT},
            description = "The puma mortality rate",
            validateWith = PositiveDoubleValidator.class)
    private double pumaMortalityRate = PUMA_MORTALITY_RATE_DEFAULT;

    /**
     * Flag for the puma diffusion rate setting, plus the variable and default
     * setting
     */
    public static final String PUMA_DIFFUSION_RATE = "--puma-diffusion-rate";
    public static final String PUMA_DIFFUSION_RATE_SHORT = "-l";
    public static final double PUMA_DIFFUSION_RATE_DEFAULT = 0.2;
    @Parameter(
            names = {PUMA_DIFFUSION_RATE, PUMA_DIFFUSION_RATE_SHORT},
            description = "The diffusion rate for pumas.  The rate at which "
                    + "hares move between neighbouring regions within the "
                    + "Landscape.",
            validateWith = PositiveDoubleValidator.class)
    private double pumaDiffusionRate = PUMA_DIFFUSION_RATE_DEFAULT;

    // ----------   Time-related configurations   -------------------------
    //
    /**
     * Flag for the time step setting, plus the variable and default setting
     */
    public static final String TIME_STEP = "--delta-time";
    public static final String TIME_STEP_SHORT = "-d";
    public static final double TIME_STEP_DEFAULT = 0.4;
    @Parameter(
            names = {TIME_STEP, TIME_STEP_SHORT},
            description = "The time step for the simulation. The fraction of a "
                    + "full unit of time that should be considered to have "
                    + "elapsed between each simulated step.",
            validateWith = PositiveDoubleValidator.class)
    private double timeStep = TIME_STEP_DEFAULT;

    /**
     * Flag for the output time step interval setting, plus the variable and
     * default setting
     */
    public static final String OUTPUT_TIME_STEP_INTERVAL = "--output-timestep-interval";
    public static final String OUTPUT_TIME_STEP_INTERVAL_SHORT = "-s";
    public static final Integer OUTPUT_TIME_STEP_INTERVAL_DEFAULT = 20;
    @Parameter(
            names = {OUTPUT_TIME_STEP_INTERVAL, OUTPUT_TIME_STEP_INTERVAL_SHORT},
            description = "The number of time steps between outputs. If the "
            + "user specifies a value of 10 for this parameter, then "
            + "the application will export PPM and average density data "
            + "files at step 0, step 10, step 20 etc. in the overall simulation.",
            validateWith = PositiveIntegerValidator.class)
    private Integer outputTimeStepInterval = OUTPUT_TIME_STEP_INTERVAL_DEFAULT;

    // ----------   Files in and out   -------------------------
    //
    /**
     * Flag for the output filename root
     */
    public final static String OUTPUT_FILENAME_ROOT = "--output-filename-root";

    @Parameter(
            names = OUTPUT_FILENAME_ROOT,
            description = "A path that defines the root name of the output files "
            + "Each individual output will be named/numbered using "
            + "suffixes to this root name. For example, if the output "
            + "file root is specified as 'simulation1' then the program "
            + "will export a series of PPM-formatted files called "
            + "'simulation1-step0097.ppm', 'simulation1-step0180.ppm' etc.")
    private String outputFilenameRoot = "";

    /**
     * Flag for the landscape filename
     */
    public final static String LANDSCAPE_FILENAME = "--landscape-filename";

    @Parameter(
            names = LANDSCAPE_FILENAME,
            description = "Path to the file that defines the landscape. "
            + "The landscape file will define the width and height of the "
            + "Landscape and then will specify if a given region within "
            + "that Landscape should be designated as Land (1) or Water (0).")
    private String landscapeFilename = "";

    // ----------   Starting density settings   -------------------------
    //
    /**
     * Flag for the fill method
     */
    public final static String FILL_METHOD = "--fill-method";
    @Parameter(
            names = FILL_METHOD,
            description = "The method to be used to fill the initial landscape. "
            + "Options are 'random', 'constant' or 'fromfile'. If 'random' is "
            + "selected, the user may also supply a maximum starting hare "
            + "density and a maximum starting puma density by using the "
            + "'" + Configuration.MAX_HARE_START_DENSITY
            + "' and '" + Configuration.MAX_PUMA_START_DENSITY +"' flags "
            + "respectively (default for each is 1.0). For the 'fromfile' option, "
            + "the user must also specify a path to a file of densities using "
            + "the " + DENSITIES_FILE + " parameter.",
            validateWith = FillMethodValidator.class)
    private String fillMethod = "random";

    /**
     * Flag for the name of the density file. For use with the 'fromfile' fill
     * method
     */
    public final static String DENSITIES_FILE = "--densities-file";
    @Parameter(
            names = DENSITIES_FILE,
            description = "The path to a file of densities to be used in the "
                    + "initial set up of the Landscape. The file should be a "
                    + "plain text, space-delimited file. Each line should "
                    + "contain four values which represent the x-coordinate, "
                    + "y-coordinate, hare density and puma density respectively."
            )
    private String densitiesFilename = "";

    /**
     * Flag for setting the maximum starting hare density
     */
    public final static String MAX_HARE_START_DENSITY = "--max-hare-start-density";
    public final static double DEFAULT_MAX_HARE_START_DENSITY = 10.0;

    @Parameter(
            names = MAX_HARE_START_DENSITY,
            description = "The maximum starting value for the density of hares "
            + "in a given Region. Values will be assigned at random "
            + "between zero and this value which must be a positive "
            + "real number.",
            validateWith = PositiveDoubleValidator.class)
    private double maxStartHareDensity = DEFAULT_MAX_HARE_START_DENSITY;

    /**
     * Flag for setting the maximum starting puma density
     */
    public final static String MAX_PUMA_START_DENSITY = "--max-puma-start-density";
    public final static double DEFAULT_MAX_PUMA_START_DENSITY = 10.0;

    @Parameter(
            names = MAX_PUMA_START_DENSITY,
            description = "The maximum starting value for the density of pumas "
            + "in a given Region. Values will be assigned at random "
            + "between zero and this value which must be a positive "
            + "real number.",
            validateWith = PositiveDoubleValidator.class)
    private double maxStartPumaDensity = DEFAULT_MAX_PUMA_START_DENSITY;

    // ----------   Usage   -------------------------
    //
    /**
     * Flags for the usage
     */
    public final static String USAGE = "--usage";
    public final static String USAGE_HELP = "--help";
    public final static String USAGE_HELP_SHORT = "-h";
    public final static String USAGE_QUESTION_MARK = "-?";

    @Parameter(
            names = {USAGE, USAGE_HELP, USAGE_HELP_SHORT, USAGE_QUESTION_MARK},
            description = "Display this help message")
    private Boolean usageNeeded = false;

    // ----------   Code begins   -------------------------
    /**
     * @return the landscapeFilename
     */
    public String getLandscapeFilename() {
        return landscapeFilename;
    }

    /**
     * @param landscapeFilename the landscapeFilename to set
     */
    public void setLandscapeFilename(String landscapeFilename) {
        this.landscapeFilename = landscapeFilename;
    }

    /**
     * @return the hareBirthRate
     */
    public double getHareBirthRate() {
        return hareBirthRate;
    }

    /**
     * @param hareBirthRate the hareBirthRate to set
     */
    public void setHareBirthRate(double hareBirthRate) {
        this.hareBirthRate = hareBirthRate;
    }

    /**
     * @return the harePredationRate
     */
    public double getHarePredationRate() {
        return harePredationRate;
    }

    /**
     * @param harePredationRate the harePredationRate to set
     */
    public void setHarePredationRate(double harePredationRate) {
        this.harePredationRate = harePredationRate;
    }

    /**
     * @return the hareDiffusionRate
     */
    public double getHareDiffusionRate() {
        return hareDiffusionRate;
    }

    /**
     * @param hareDiffusionRate the hareDiffusionRate to set
     */
    public void setHareDiffusionRate(double hareDiffusionRate) {
        this.hareDiffusionRate = hareDiffusionRate;
    }

    /**
     * @return the pumaBirthRate
     */
    public double getPumaBirthRate() {
        return pumaBirthRate;
    }

    /**
     * @param pumaBirthRate the pumaBirthRate to set
     */
    public void setPumaBirthRate(double pumaBirthRate) {
        this.pumaBirthRate = pumaBirthRate;
    }

    /**
     * @return the pumaMortalityRate
     */
    public double getPumaMortalityRate() {
        return pumaMortalityRate;
    }

    /**
     * @param pumaMortalityRate the pumaMortalityRate to set
     */
    public void setPumaMortalityRate(double pumaMortalityRate) {
        this.pumaMortalityRate = pumaMortalityRate;
    }

    /**
     * @return the pumaDiffusionRate
     */
    public double getPumaDiffusionRate() {
        return pumaDiffusionRate;
    }

    /**
     * @param pumaDiffusionRate the pumaDiffusionRate to set
     */
    public void setPumaDiffusionRate(double pumaDiffusionRate) {
        this.pumaDiffusionRate = pumaDiffusionRate;
    }

    /**
     * @return the timeStep
     */
    public double getTimeStep() {
        return timeStep;
    }

    /**
     * @param timeStep the timeStep to set
     */
    public void setTimeStep(double timeStep) {
        this.timeStep = timeStep;
    }

    /**
     * @return the outputTimeStepInterval
     */
    public Integer getOutputTimeStepInterval() {
        return outputTimeStepInterval;
    }

    /**
     * @param outputTimeStepInterval the outputTimeStepInterval to set
     */
    public void setOutputTimeStepInterval(Integer outputTimeStepInterval) {
        this.outputTimeStepInterval = outputTimeStepInterval;
    }

    /**
     * @return the outputFilenameRoot
     */
    public String getOutputFilenameRoot() {
        return outputFilenameRoot;
    }

    /**
     * @param outputFilenameRoot the outputFilenameRoot to set
     */
    public void setOutputFilenameRoot(String outputFilenameRoot) {
        this.outputFilenameRoot = outputFilenameRoot;
    }

    /**
     * @return the usageNeeded
     */
    public Boolean isUsageNeeded() {
        return usageNeeded;
    }

    /**
     * @param usageNeeded the usageNeeded to set
     */
    public void setUsageNeeded(Boolean usageNeeded) {
        this.usageNeeded = usageNeeded;
    }

    /**
     * @return the jCommander
     */
    public JCommander getJCommander() {
        return jCommander;
    }

    /**
     * @param jCommander the jCommander to set
     */
    public void setJCommander(JCommander jCommander) {
        this.jCommander = jCommander;
    }

    /**
     * @return the maxStartHareDensity
     */
    public double getMaxStartHareDensity() {
        return maxStartHareDensity;
    }

    /**
     * @param maxStartHareDensity the maxStartHareDensity to set
     */
    public void setMaxStartHareDensity(double maxStartHareDensity) {
        this.maxStartHareDensity = maxStartHareDensity;
    }

    /**
     * @return the maxStartPumaDensity
     */
    public double getMaxStartPumaDensity() {
        return maxStartPumaDensity;
    }

    /**
     * @param maxStartPumaDensity the maxStartPumaDensity to set
     */
    public void setMaxStartPumaDensity(double maxStartPumaDensity) {
        this.maxStartPumaDensity = maxStartPumaDensity;
    }

    /**
     * @return the fillMethod
     */
    public String getFillMethod() {
        return fillMethod;
    }

    /**
     * @param fillMethod the fillMethod to set
     */
    public void setFillMethod(String fillMethod) {
        this.fillMethod = fillMethod;
    }

    /**
     * @return the densitiesFilename
     */
    public String getDensitiesFilename() {
        return densitiesFilename;
    }

    /**
     * @param densitiesFilename the densitiesFilename to set
     */
    public void setDensitiesFilename(String densitiesFilename) {
        this.densitiesFilename = densitiesFilename;
    }
}

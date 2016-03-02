/*
 * The MIT License
 *
 * Copyright 2015 Sheng Wang (s1532126@sms.ed.ac.uk)
 * and Andy Law (s1578554@sms.ed.ac.uk).
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

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.beust.jcommander.JCommander;
import uk.ac.ed.ph.ps.coursework.io.PpmWriter;
import uk.ac.ed.ph.ps.coursework.model.Landscape;
import uk.ac.ed.ph.ps.coursework.simtools.Simulator;

/**
 *
 * @author Sheng Wang (s1532126@sms.ed.ac.uk)
 * @author Andy Law (s1578554@sms.ed.ac.uk)
 *
 */
public class Main {

    private Runner runner = new Runner();

    /**
     * Get the currently-set Runner object from this class
     *
     * @return the Runner object owned by this Main class
     */
    public Runner getRunner() {
        return this.runner;
    }

    /**
     * Set a new Runner object. Primarily for testing purposes
     *
     * @param runner a Runner object to be used by this class
     */
    public void setRunner(Runner runner) {
        this.runner = runner;
    }

    private static final int TOTAL_TIME = 500;

    /**
     * Do the main bulk of the running stuff, using helper/runner objects as
     * required
     *
     * @param args an array of String arguments as supplied on the command line
     */
    public void run(String[] args) {

        // Get the command line arguments
        Configuration config;
        try {
            config = this.runner.getConfiguration(args);
        } catch (Exception e) {
            System.err.println("Wrong command is provided");
            return;
        }
        JCommander jCom = config.getJCommander();
        if (args.length == 0 || config.isUsageNeeded()) {
            jCom.usage();
            return;
        }

        long startTime = System.currentTimeMillis();

        // try to get a Landscape object
        Landscape landscape;
        try {
            landscape = this.runner.getLandscape(config);
        } catch (FileNotFoundException ex) {
            System.err.println("Could not find the Landscape File");
            jCom.usage();
            return;
        } catch (IOException ex) {
            System.err.println("There was a problem reading the Landscape File");
            System.err.println("The error was '" + ex.getMessage() + "'");
            return;
        }

        // Get an appropriate Landscape Filler object. Calling code
        // doesn't care what kind of filler this is, just as long as it
        // gets given something to work with.
        // This code may throw an exception if we get an invalid configuration
        LandscapeFiller filler;
        try {
            filler = this.runner.getLandscapeFiller(config);
        } catch (FileNotFoundException ex) {
            System.err.println("There was a problem getting the Landscape Filler object");
            System.err.println("The error was '" + ex.getMessage() + "'");
            return;
        }
        filler.fillLandscape(landscape);

        // create a directory for storing results
        System.out.println("Preparing for output file directory ...");
        String directoryPath = this.getRunner().getDirectoryPath();
        if (directoryPath == null) {
            // unable to create directory
            String error = "Unable to create directory for storing output";
            System.err.print(error);
            String suggestion = "Check your permission for writing files";
            System.out.println(suggestion);
            return;
        } else {
            System.out.println("Results will go in directory '" + directoryPath + "'");
        }

        // create simulator and ppm writer
        Simulator simulator = this.getRunner().getSimulator(landscape, config);
        PpmWriter writer = new PpmWriter();

        // run the simulator and write ppm file every T timesteps
        double timestep = config.getTimeStep();
        int printStepInterval = config.getOutputTimeStepInterval();

        int index = 0;
        for (double elapsedTime = 0; elapsedTime <= TOTAL_TIME; elapsedTime += timestep) {

            // use modulo arithmetic to print out every 'printStepInterval' steps
            if ((index % printStepInterval) == 0) {

                // print out averge hare and puma density
                System.out.println("Average Hare Density at iteration "
                        + ((index / printStepInterval) + 1) + " : "
                        + landscape.getAverageHareDensity());
                System.out.println("Average Puma Density at iteration "
                        + ((index / printStepInterval) + 1) + " : "
                        + landscape.getAveragePumaDensity());

                // get buffered writer for ppm writer
                BufferedWriter bufferedWriter
                        = this.runner.getBufferedWriterForPpmWriter(
                                directoryPath,
                                (index / printStepInterval) + 1, config);

                // write a ppm file
                System.out.println("Generating ppm file for iteration "
                        + String.valueOf((index / printStepInterval) + 1)
                        + " ...");
                writer.writePpm(bufferedWriter, landscape);
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // do one step simulation
            simulator.doSimulationStep(landscape, timestep);

            index++;
        }
        System.out.println("-----------------Simulation Finished-----------------");
        long endTime = System.currentTimeMillis();
        System.out.println("Total time used is : " + (endTime - startTime) + " milliseconds");
    }

    /**
     * The Main static entry point for the application
     *
     * @param args an array of String arguments as supplied on the command line
     */
    public static void main(String[] args) {
        Main main = new Main();
        main.run(args);
    }
}

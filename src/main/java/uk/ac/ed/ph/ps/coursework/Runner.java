/*
 * The MIT License
 *
 * Copyright 2015 Andy Law (s1578554@sms.ed.ac.uk)
 *   and Sheng Wang (s1532126@sms.ed.ac.uk).
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.beust.jcommander.JCommander;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import uk.ac.ed.ph.ps.coursework.io.LandscapeFileReader;
import uk.ac.ed.ph.ps.coursework.model.Landscape;
import uk.ac.ed.ph.ps.coursework.simtools.HarePopulationCalculator;
import uk.ac.ed.ph.ps.coursework.simtools.NeighbourCount;
import uk.ac.ed.ph.ps.coursework.simtools.PumaPopulationCalculator;
import uk.ac.ed.ph.ps.coursework.simtools.Simulator;

/**
 *
 * @author Andy Law (s1578554@sms.ed.ac.uk)
 * @author Sheng Wang (s1532126@sms.ed.ac.uk)
 */
public class Runner {

    private static final String APPLICATION_NAME = "PreyPredatorSimulation";

    /**
     * Create a new Configuration object, parsing the command line arguments as
     * required and stashing the JCommander object before returning
     *
     * @param args the command line arguments specified by the user
     * @return a Configuration object with a JCommander object embedded
     * @throws java.lang.Exception if JCommander finds an argument that it does
     * not recognise
     */
    public Configuration getConfiguration(String[] args) throws Exception {

        Configuration config = new Configuration();
        JCommander jcommander = new JCommander(config, args);
        jcommander.setProgramName(APPLICATION_NAME);
        config.setJCommander(jcommander);
        return config;
    }

    /**
     * Get a Landscape object by reading in from the file specified on the
     * command line
     *
     * @param config the Configuration object
     * @return a Landscape object with Land and Water regions filled in
     * @throws FileNotFoundException if the specified file does not exist
     */
    public Landscape getLandscape(Configuration config) throws FileNotFoundException, IOException {

        File file = new File(config.getLandscapeFilename());
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        LandscapeFileReader reader = new LandscapeFileReader();
        Landscape landscape = null;
        try {
            landscape = reader.readLandscapeFile(bufferedReader);
        } catch (IOException e) {
            bufferedReader.close();
            throw e;
        }

        return landscape;
    }

    /**
     * Create a LandscapeFiller object appropriate for the arguments specified
     * by the user
     *
     * @param config the Configuration object that contains all the users
     * settings
     * @return a LandscapeFiller object
     * @throws java.io.FileNotFoundException if a file filler is specified and
     * there is a problem with reading from that file
     */
    public LandscapeFiller getLandscapeFiller(Configuration config) throws FileNotFoundException {

        LandscapeFiller landscapeFiller = null;

        String fillMethod = config.getFillMethod();

        switch (fillMethod) {
            case "random":
                double maxHareDensity = config.getMaxStartHareDensity();
                double maxPumaDensity = config.getMaxStartPumaDensity();
                landscapeFiller = new RandomLandscapeFillerImpl(maxHareDensity, maxPumaDensity);
                break;

            case "fromfile":
                String fillerFileName = config.getDensitiesFilename();
                File file = new File(fillerFileName);
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                landscapeFiller = new SpecificLandscapeFillerImpl(bufferedReader);
                break;

        }

        return landscapeFiller;
    }

    public String getDirectoryPath() {

        Date date = new Date(System.currentTimeMillis());
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd-HHmmss");
        String directoryName = String.format("Simulation-%s", formatter.format(date));
        File directory = new File(directoryName);
        if (!directory.exists()) {
            boolean success = directory.mkdirs();
            if (!success) {
                return null;
            }
        }
        String directoryPath = directory.getAbsolutePath();

        return directoryPath;
    }

    public Simulator getSimulator(Landscape landscape, Configuration config) {

        HarePopulationCalculator hareCalculator
                = new HarePopulationCalculator(
                        config.getHareBirthRate(),
                        config.getHarePredationRate(),
                        config.getHareDiffusionRate());

        PumaPopulationCalculator pumaCalculator
                = new PumaPopulationCalculator(
                        config.getPumaBirthRate(),
                        config.getPumaMortalityRate(),
                        config.getPumaDiffusionRate());

        NeighbourCount neighbourCount = new NeighbourCount();

        Simulator simulator = new Simulator(
                landscape.getWidth(),
                landscape.getHeight(),
                hareCalculator,
                pumaCalculator,
                neighbourCount);

        return simulator;
    }

    /**
     * Get a BufferedWriter object wrapped around a suitably named output file
     * so we can write the PPM-formatted data out
     *
     * @param directoryPath the Path to the directory of PPM files
     * @param index the index number of the file within the series
     * @param config the Configurations object that contains everything else we
     * need to know
     * @return
     */
    public BufferedWriter getBufferedWriterForPpmWriter(String directoryPath, int index, Configuration config) {

        String outputFilenameRoot = config.getOutputFilenameRoot();
        if (!"".equals(outputFilenameRoot)) {
            outputFilenameRoot += "_";
        }

        // create ppm file
        String filename = outputFilenameRoot + String.valueOf(index) + ".ppm";
        File file = new File(directoryPath + "/" + filename);

        // if file doesnt exists, then create it
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // wrap file writer into buffered writer
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file.getAbsoluteFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        return bufferedWriter;
    }

}

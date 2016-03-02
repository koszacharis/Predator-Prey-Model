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
import java.io.FileNotFoundException;
import java.io.IOException;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static uk.ac.ed.ph.ps.coursework.Configuration.USAGE;

/**
 *
 * @author Andy Law (s1578554@sms.ed.ac.uk)
 */
public class MainTest {

    protected Main testedObject;

    protected Runner runner = mock(Runner.class);

    /**
     * @return the testedObject
     */
    public Main getTestedObject() {
        return testedObject;
    }

    /**
     * Create a new test object and give it our tame Runner object
     */
    @Before
    public void setUp() {
        testedObject = new Main();
        testedObject.setRunner(runner);
    }

    /**
     * Test of getRunner method, of class Main.
     */
    @Test
    public void testGetRunner() {
        Main instance = this.getTestedObject();

        Runner theRunner = instance.getRunner();
        assertNotNull(theRunner);
        assertSame(runner, theRunner);

    }

    /**
     * Test that the Main class has a sensible default Runner object.
     */
    @Test
    public void testGetDefaultRunner() {
        Main instance = new Main();

        Runner theRunner = instance.getRunner();
        assertNotNull(theRunner);
        assertTrue(theRunner instanceof Runner);

    }

    /**
     * Test that if we have no command line arguments then we print the usage
     * and exit
     *
     * @throws Exception is not actually thrown
     */
    @Test
    public void testNoArgs() throws Exception {
        String[] args = {};
        Main instance = this.getTestedObject();

        Configuration config = mock(Configuration.class);
        JCommander jCom = mock(JCommander.class);

        expect(runner.getConfiguration(args)).andReturn(config);
        expect(config.getJCommander()).andReturn(jCom);

        jCom.usage();

        replay(runner, config, jCom);
        instance.run(args);
        verify(runner, config, jCom);

    }

    /**
     * Test that if we have command line arguments but the Configuration object
     * says that we want a usage display then we display usage and exit
     *
     * @throws Exception is not actually thrown
     */
    @Test
    public void testArgsWantUsage() throws Exception {
        String[] args = {USAGE}; // irrelevant because we use a mock config!
        Main instance = this.getTestedObject();

        Configuration config = mock(Configuration.class);
        JCommander jCom = mock(JCommander.class);

        expect(runner.getConfiguration(args)).andReturn(config);

        expect(config.isUsageNeeded()).andReturn(true);
        expect(config.getJCommander()).andReturn(jCom);

        jCom.usage();

        replay(runner, config, jCom);
        instance.run(args);
        verify(runner, config, jCom);

    }

    /**
     * Test that if we have command line arguments and we haven't asked for
     * usage then we look to get a file/bufferedReader from which we can read
     * the Landscape. If we fail to get one, then we print usage and exit.
     *
     * @throws java.io.FileNotFoundException is not actually thrown
     * @throws java.io.IOException is not actually thrown
     * @throws Exception is not actually thrown
     */
    @Test
    public void testArgsNoUsageLandscapeFails() throws FileNotFoundException, IOException, Exception {
        String[] args = {USAGE}; // irrelevant because we use a mock config!
        Main instance = this.getTestedObject();

        Configuration config = mock(Configuration.class);
        JCommander jCom = mock(JCommander.class);

        expect(runner.getConfiguration(args)).andReturn(config);

        expect(config.isUsageNeeded()).andReturn(false);
        expect(config.getJCommander()).andReturn(jCom);

        expect(runner.getLandscape(config)).andThrow(new FileNotFoundException());
        jCom.usage();

        replay(runner, config, jCom);
        instance.run(args);
        verify(runner, config, jCom);

    }

}

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
package uk.ac.ed.ph.ps.coursework.parameters;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

/**
 *
 * @author Andy Law (s1578554@sms.ed.ac.uk)
 */
public class FillMethodValidator implements IParameterValidator {

    /**
     * Validate a supplied parameter value by checking that it is a positive
     * double value
     *
     * @param name the name of the parameter
     * @param value A String containing the parameter value as supplied by the
     * user on the command line
     * @throws ParameterException A ParameterException indicating which
     * parameter failed
     */
    @Override
    public void validate(String name, String value)
            throws ParameterException {
        if ("random".equals(value)) {
            return;
        }
        if ("constant".equals(value)) {
            return;
        }
        if ("fromfile".equals(value)) {
            return;
        }
        throw new ParameterException("Parameter " + name + " should be one of ['random'|'constant'|'fromfile']");

    }
}

/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Spring 2017 *
  * Name: NAMES of team members
  * Date: Mar 21, 2017
  * Time: 9:53:00 PM *
  * Project: csci205_hw
  * Package: hw02
  * File: SigmoidalActivationFunctionTest
  * Description:
  *
  * ****************************************
 */
package hw02;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author haoyuxiong
 */
public class SigmoidalActivationFunctionTest {

    static final double EPSILON = 1.0E-12;

    public SigmoidalActivationFunctionTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getValue method, of class SigmoidalActivationFunction.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        double input = 1;
        SigmoidalActivationFunction instance = new SigmoidalActivationFunction();
        double expResult = (1 / (1 + Math.pow(Math.E, (-1) * input)));
        double result = instance.getValue(input);
        assertEquals(expResult, result, EPSILON);

    }

}

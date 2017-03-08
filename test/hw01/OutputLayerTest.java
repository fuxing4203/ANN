/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Spring 2017 *
  * Name: NAMES of team members
  * Date: Mar 8, 2017
  * Time: 4:23:41 AM *
  * Project: csci205_hw
  * Package: hw01
  * File: OutputLayerTest
  * Description:
  *
  * ****************************************
 */
package hw01;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author haoyuxiong
 */
public class OutputLayerTest {

    public OutputLayerTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of classify_OutputNode method, of class OutputLayer.
     */
    @Test
    public void testClassify_OutputNode() {
        System.out.println("classify_OutputNode");
        double[] data = null;
        OutputLayer instance = null;
        double expResult = 0.0;
        double result = instance.classify_OutputNode(data);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateLittleDeltaOfOutputLayer method, of class OutputLayer.
     */
    @Test
    public void testCalculateLittleDeltaOfOutputLayer() {
        System.out.println("calculateLittleDeltaOfOutputLayer");
        double expected = 0.0;
        double actual = 0.0;
        OutputLayer instance = null;
        double expResult = 0.0;
        double result = instance.calculateLittleDeltaOfOutputLayer(expected,
                                                                   actual);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of train_OutputNode method, of class OutputLayer.
     */
    @Test
    public void testTrain_OutputNode() {
        System.out.println("train_OutputNode");
        double actual = 0.0;
        double expected = 0.0;
        double[] fNets = null;
        OutputLayer instance = null;
        double[] expResult = null;
        double[] result = instance.train_OutputNode(actual, expected, fNets);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

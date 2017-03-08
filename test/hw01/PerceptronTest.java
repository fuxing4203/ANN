/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Spring 2017 *
  * Name: NAMES of team members
  * Date: Mar 8, 2017
  * Time: 4:23:41 AM *
  * Project: csci205_hw
  * Package: hw01
  * File: PerceptronTest
  * Description:
  *
  * ****************************************
 */
package hw01;

import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author haoyuxiong
 */
public class PerceptronTest {

    public PerceptronTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getWeights method, of class Perceptron.
     */
    @Test
    public void testGetWeights() {
        System.out.println("getWeights");
        Perceptron instance = null;
        ArrayList<Double> expResult = null;
        ArrayList<Double> result = instance.getWeights();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumInp method, of class Perceptron.
     */
    @Test
    public void testGetNumInp() {
        System.out.println("getNumInp");
        Perceptron instance = null;
        int expResult = 0;
        int result = instance.getNumInp();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNumInp method, of class Perceptron.
     */
    @Test
    public void testSetNumInp() {
        System.out.println("setNumInp");
        int numInp = 0;
        Perceptron instance = null;
        instance.setNumInp(numInp);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setWeights method, of class Perceptron.
     */
    @Test
    public void testSetWeights() {
        System.out.println("setWeights");
        ArrayList<Double> weights = null;
        Perceptron instance = null;
        instance.setWeights(weights);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of train_Perceptron method, of class Perceptron.
     */
    @Test
    public void testTrain_Perceptron() {
        System.out.println("train_Perceptron");
        double[] fgets = null;
        double littleDelta = 0.0;
        Perceptron instance = null;
        instance.train_Perceptron(fgets, littleDelta);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTheta method, of class Perceptron.
     */
    @Test
    public void testGetTheta() {
        System.out.println("getTheta");
        Perceptron instance = null;
        double expResult = 0.0;
        double result = instance.getTheta();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTheta method, of class Perceptron.
     */
    @Test
    public void testSetTheta() {
        System.out.println("setTheta");
        double theta = 0.0;
        Perceptron instance = null;
        instance.setTheta(theta);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateDeltaWeight method, of class Perceptron.
     */
    @Test
    public void testCalculateDeltaWeight() {
        System.out.println("calculateDeltaWeight");
        double prevFnet = 0.0;
        double littleDelta = 0.0;
        double expResult = 0.0;
        double result = Perceptron.calculateDeltaWeight(prevFnet, littleDelta);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of classify_Perceptron method, of class Perceptron.
     */
    @Test
    public void testClassify_Perceptron() {
        System.out.println("classify_Perceptron");
        double[] classify_Data1 = null;
        Perceptron instance = null;
        double expResult = 0.0;
        double result = instance.classify_Perceptron(classify_Data1);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of step_function method, of class Perceptron.
     */
    @Test
    public void testStep_function() {
        System.out.println("step_function");
        double net = 0.0;
        int expResult = 0;
        int result = Perceptron.step_function(net);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sigmoidal_activation_func method, of class Perceptron.
     */
    @Test
    public void testSigmoidal_activation_func() {
        System.out.println("sigmoidal_activation_func");
        double net = 0.0;
        Perceptron instance = null;
        double expResult = 0.0;
        double result = instance.sigmoidal_activation_func(net);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeWeightAtIndex method, of class Perceptron.
     */
    @Test
    public void testChangeWeightAtIndex() {
        System.out.println("changeWeightAtIndex");
        double deltaWeight = 0.0;
        int index = 0;
        Perceptron instance = null;
        instance.changeWeightAtIndex(deltaWeight, index);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWeightAtIndex method, of class Perceptron.
     */
    @Test
    public void testGetWeightAtIndex() {
        System.out.println("getWeightAtIndex");
        int index = 0;
        Perceptron instance = null;
        double expResult = 0.0;
        double result = instance.getWeightAtIndex(index);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

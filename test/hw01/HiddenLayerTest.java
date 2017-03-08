/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Spring 2017 *
  * Name: NAMES of team members
  * Date: Mar 8, 2017
  * Time: 4:23:41 AM *
  * Project: csci205_hw
  * Package: hw01
  * File: HiddenLayerTest
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
public class HiddenLayerTest {

    public HiddenLayerTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getfNets method, of class HiddenLayer.
     */
    @Test
    public void testGetfNets() {
        System.out.println("getfNets");
        HiddenLayer instance = null;
        double[] expResult = null;
        double[] result = instance.getfNets();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of train_HiddenLayer method, of class HiddenLayer.
     */
    @Test
    public void testTrain_HiddenLayer() {
        System.out.println("train_HiddenLayer");
        double[] kLittleDeltas = null;
        ArrayList<Perceptron> prevLayerNodes = null;
        double[] prevFgets = null;
        HiddenLayer instance = null;
        double[] expResult = null;
        double[] result = instance.train_HiddenLayer(kLittleDeltas,
                                                     prevLayerNodes, prevFgets);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateLittleDeltaAtNoNode method, of class HiddenLayer.
     */
    @Test
    public void testCalculateLittleDeltaAtNoNode() {
        System.out.println("calculateLittleDeltaAtNoNode");
        int no = 0;
        double fgets = 0.0;
        ArrayList<Perceptron> prevLayerNodes = null;
        double[] kLittleDeltas = null;
        HiddenLayer instance = null;
        double expResult = 0.0;
        double result = instance.calculateLittleDeltaAtNoNode(no, fgets,
                                                              prevLayerNodes,
                                                              kLittleDeltas);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Spring 2017 *
  * Name: NAMES of team members
  * Date: Mar 8, 2017
  * Time: 4:23:41 AM *
  * Project: csci205_hw
  * Package: hw01
  * File: SUB_ANNTest
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
public class SUB_ANNTest {

    public SUB_ANNTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of Feed_Forward_Train_SUB_ANN method, of class SUB_ANN.
     */
    @Test
    public void testFeed_Forward_Train_SUB_ANN() {
        System.out.println("Feed_Forward_Train_SUB_ANN");
        double[] inputDataAtRowJ = null;
        int no = 0;
        SUB_ANN instance = null;
        double expResult = 0.0;
        double result = instance.Feed_Forward_Train_SUB_ANN(inputDataAtRowJ, no);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Back_Propagation_Train_SUB_ANN method, of class SUB_ANN.
     */
    @Test
    public void testBack_Propagation_Train_SUB_ANN() {
        System.out.println("Back_Propagation_Train_SUB_ANN");
        double actual = 0.0;
        double expected = 0.0;
        double[] inputDataAtRowJ = null;
        SUB_ANN instance = null;
        instance.Back_Propagation_Train_SUB_ANN(actual, expected,
                                                inputDataAtRowJ);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of train_SUB_ANN method, of class SUB_ANN.
     */
    @Test
    public void testTrain_SUB_ANN() {
        System.out.println("train_SUB_ANN");
        double[][] data = null;
        int no = 0;
        SUB_ANN instance = null;
        instance.train_SUB_ANN(data, no);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Classify_SUB_ANN method, of class SUB_ANN.
     */
    @Test
    public void testClassify_SUB_ANN() {
        System.out.println("Classify_SUB_ANN");
        double[] data = null;
        SUB_ANN instance = null;
        double expResult = 0.0;
        double result = instance.Classify_SUB_ANN(data);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHiddenLayerList method, of class SUB_ANN.
     */
    @Test
    public void testGetHiddenLayerList() {
        System.out.println("getHiddenLayerList");
        SUB_ANN instance = null;
        ArrayList<HiddenLayer> expResult = null;
        ArrayList<HiddenLayer> result = instance.getHiddenLayerList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHiddenLayerList method, of class SUB_ANN.
     */
    @Test
    public void testSetHiddenLayerList() {
        System.out.println("setHiddenLayerList");
        ArrayList<HiddenLayer> hiddenLayerList = null;
        SUB_ANN instance = null;
        instance.setHiddenLayerList(hiddenLayerList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOutputLayer method, of class SUB_ANN.
     */
    @Test
    public void testGetOutputLayer() {
        System.out.println("getOutputLayer");
        SUB_ANN instance = null;
        OutputLayer expResult = null;
        OutputLayer result = instance.getOutputLayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOutputLayer method, of class SUB_ANN.
     */
    @Test
    public void testSetOutputLayer() {
        System.out.println("setOutputLayer");
        OutputLayer outputLayer = null;
        SUB_ANN instance = null;
        instance.setOutputLayer(outputLayer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumOfLayers method, of class SUB_ANN.
     */
    @Test
    public void testGetNumOfLayers() {
        System.out.println("getNumOfLayers");
        SUB_ANN instance = null;
        int expResult = 0;
        int result = instance.getNumOfLayers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNumOfLayers method, of class SUB_ANN.
     */
    @Test
    public void testSetNumOfLayers() {
        System.out.println("setNumOfLayers");
        int numOfLayers = 0;
        SUB_ANN instance = null;
        instance.setNumOfLayers(numOfLayers);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumOfLayerLayers method, of class SUB_ANN.
     */
    @Test
    public void testGetNumOfLayerLayers() {
        System.out.println("getNumOfLayerLayers");
        SUB_ANN instance = null;
        int expResult = 0;
        int result = instance.getNumOfLayerLayers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNumOfLayerLayers method, of class SUB_ANN.
     */
    @Test
    public void testSetNumOfLayerLayers() {
        System.out.println("setNumOfLayerLayers");
        int numOfLayerLayers = 0;
        SUB_ANN instance = null;
        instance.setNumOfLayerLayers(numOfLayerLayers);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumIp method, of class SUB_ANN.
     */
    @Test
    public void testGetNumIp() {
        System.out.println("getNumIp");
        SUB_ANN instance = null;
        int expResult = 0;
        int result = instance.getNumIp();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNumIp method, of class SUB_ANN.
     */
    @Test
    public void testSetNumIp() {
        System.out.println("setNumIp");
        int numIp = 0;
        SUB_ANN instance = null;
        instance.setNumIp(numIp);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumNodesInHiddenLayers method, of class SUB_ANN.
     */
    @Test
    public void testGetNumNodesInHiddenLayers() {
        System.out.println("getNumNodesInHiddenLayers");
        SUB_ANN instance = null;
        int expResult = 0;
        int result = instance.getNumNodesInHiddenLayers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNumNodesInHiddenLayers method, of class SUB_ANN.
     */
    @Test
    public void testSetNumNodesInHiddenLayers() {
        System.out.println("setNumNodesInHiddenLayers");
        int numNodesInHiddenLayers = 0;
        SUB_ANN instance = null;
        instance.setNumNodesInHiddenLayers(numNodesInHiddenLayers);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

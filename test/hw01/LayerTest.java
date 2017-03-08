/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Spring 2017 *
  * Name: NAMES of team members
  * Date: Mar 8, 2017
  * Time: 4:23:41 AM *
  * Project: csci205_hw
  * Package: hw01
  * File: LayerTest
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
public class LayerTest {

    public LayerTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getfNets method, of class Layer.
     */
    @Test
    public void testGetfNets() {
        System.out.println("getfNets");
        Layer instance = null;
        double[] expResult = null;
        double[] result = instance.getfNets();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFnetsAtNoNode method, of class Layer.
     */
    @Test
    public void testGetFnetsAtNoNode() {
        System.out.println("getFnetsAtNoNode");
        int index = 0;
        Layer instance = null;
        double expResult = 0.0;
        double result = instance.getFnetsAtNoNode(index);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setfNets method, of class Layer.
     */
    @Test
    public void testSetfNets() {
        System.out.println("setfNets");
        double[] fNets = null;
        Layer instance = null;
        instance.setfNets(fNets);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumOfPrevNodes method, of class Layer.
     */
    @Test
    public void testGetNumOfPrevNodes() {
        System.out.println("getNumOfPrevNodes");
        Layer instance = null;
        int expResult = 0;
        int result = instance.getNumOfPrevNodes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNumOfPrevNodes method, of class Layer.
     */
    @Test
    public void testSetNumOfPrevNodes() {
        System.out.println("setNumOfPrevNodes");
        int numOfPrevNodes = 0;
        Layer instance = null;
        instance.setNumOfPrevNodes(numOfPrevNodes);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumOfNodes method, of class Layer.
     */
    @Test
    public void testGetNumOfNodes() {
        System.out.println("getNumOfNodes");
        Layer instance = null;
        int expResult = 0;
        int result = instance.getNumOfNodes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNumOfNodes method, of class Layer.
     */
    @Test
    public void testSetNumOfNodes() {
        System.out.println("setNumOfNodes");
        int numOfNodes = 0;
        Layer instance = null;
        instance.setNumOfNodes(numOfNodes);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNodes method, of class Layer.
     */
    @Test
    public void testGetNodes() {
        System.out.println("getNodes");
        Layer instance = null;
        ArrayList<Perceptron> expResult = null;
        ArrayList<Perceptron> result = instance.getNodes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNodes method, of class Layer.
     */
    @Test
    public void testSetNodes() {
        System.out.println("setNodes");
        ArrayList<Perceptron> Nodes = null;
        Layer instance = null;
        instance.setNodes(Nodes);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNodeAtNo method, of class Layer.
     */
    @Test
    public void testGetNodeAtNo() {
        System.out.println("getNodeAtNo");
        int index = 0;
        Layer instance = null;
        Perceptron expResult = null;
        Perceptron result = instance.getNodeAtNo(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMinSSE method, of class Layer.
     */
    @Test
    public void testGetMinSSE() {
        System.out.println("getMinSSE");
        Layer instance = null;
        double expResult = 0.0;
        double result = instance.getMinSSE();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMinSSE method, of class Layer.
     */
    @Test
    public void testSetMinSSE() {
        System.out.println("setMinSSE");
        double minSSE = 0.0;
        Layer instance = null;
        instance.setMinSSE(minSSE);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of classify_Layer method, of class Layer.
     */
    @Test
    public void testClassify_Layer() {
        System.out.println("classify_Layer");
        double[] data = null;
        Layer instance = null;
        double[] expResult = null;
        double[] result = instance.classify_Layer(data);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

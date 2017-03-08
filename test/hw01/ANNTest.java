/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Spring 2017 *
  * Name: NAMES of team members
  * Date: Mar 8, 2017
  * Time: 4:23:40 AM *
  * Project: csci205_hw
  * Package: hw01
  * File: ANNTest
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
public class ANNTest {

    private ANN ann;
    private double[][] data = {{0, 0, 0, 0}, {0, 1, 1, 0}, {1, 0, 1, 0}, {1, 1, 1, 1}};

    public ANNTest() {
    }

    @Before
    public void setUp() {
        ann = new ANN(2, 2, 3, 3, 0.001);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of Train_ANN method, of class ANN.
     */
    @Test
    public void testTrain_ANN() {
        System.out.println("Train_ANN");
        double[][] data = null;
        ANN instance = null;
        ArrayList<SUB_ANN> expResult = null;
        ArrayList<SUB_ANN> result = instance.Train_ANN(data);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Classify_ANN method, of class ANN.
     */
    @Test
    public void testClassify_ANN() {
        System.out.println("Classify_ANN");
        double[][] data = null;
        ANN instance = null;
        int[][] expResult = null;
        int[][] result = instance.Classify_ANN(data);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

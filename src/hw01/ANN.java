/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Spring 2017 *
  * Name: NAMES of team members
  * Date: Mar 7, 2017
  * Time: 11:02:43 PM *
  * Project: csci205_hw
  * Package: hw01
  * File: ANN
  * Description:
  *
  * ****************************************
 */
package hw01;

import java.util.ArrayList;

/**
 *
 * @author haoyuxiong
 */
public class ANN {

    private ArrayList<SUB_ANN> subANNList;
    private int numInp;
    private int numNodesInHiddenLayers;

    /**
     *
     */
    public static double minSSE;
    private int numOut;

    /**
     *
     * @param numInp
     * @param numOut
     * @param numOfLayers
     * @param numNodesInHiddenLayers
     * @param minSSE
     */
    public ANN(int numInp, int numOut, int numOfLayers,
               int numNodesInHiddenLayers, double minSSE) {
        this.numInp = numInp;
        this.numOut = numOut;
        this.numNodesInHiddenLayers = this.numNodesInHiddenLayers;
        ANN.minSSE = minSSE;
        subANNList = new ArrayList<SUB_ANN>();
        for (int i = 0; i < numOut; i++) {
            subANNList.add(new SUB_ANN(numInp, numOfLayers,
                                       numNodesInHiddenLayers));

        }

    }

    /**
     *
     * @return
     */
    public ArrayList<SUB_ANN> getSubANNList() {
        return subANNList;
    }

    /**
     *
     * @param subANNList
     */
    public void setSubANNList(ArrayList<SUB_ANN> subANNList) {
        this.subANNList = subANNList;
    }

    /**
     *
     * @return
     */
    public int getNumInp() {
        return numInp;
    }

    /**
     *
     * @param numInp
     */
    public void setNumInp(int numInp) {
        this.numInp = numInp;
    }

    /**
     *
     * @return
     */
    public int getNumNodesInHiddenLayers() {
        return numNodesInHiddenLayers;
    }

    /**
     *
     * @param numNodesInHiddenLayers
     */
    public void setNumNodesInHiddenLayers(int numNodesInHiddenLayers) {
        this.numNodesInHiddenLayers = numNodesInHiddenLayers;
    }

    /**
     *
     * @return
     */
    public static double getMinSSE() {
        return minSSE;
    }

    /**
     *
     * @param minSSE
     */
    public static void setMinSSE(double minSSE) {
        ANN.minSSE = minSSE;
    }

    /**
     *
     * @return
     */
    public int getNumOut() {
        return numOut;
    }

    /**
     *
     * @param numOut
     */
    public void setNumOut(int numOut) {
        this.numOut = numOut;
    }

    /**
     *
     * @param data
     * @return
     */
    public ArrayList<SUB_ANN> Train_ANN(double[][] data) {
        for (int i = 0; i < this.numOut; i++) {
            this.subANNList.get(i).train_SUB_ANN(data, i);

        }
        return subANNList;
    }

    /**
     *
     * @param data
     * @return
     */
    public int[][] Classify_ANN(double[][] data) {
        int[][] output2DArray = new int[data.length][this.numOut];
        double output;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < this.numOut; j++) {
                output = this.subANNList.get(j).Classify_SUB_ANN(data[i]);
                output2DArray[i][j] = Perceptron.step_function(output);

            }
        }
        return output2DArray;

    }

}

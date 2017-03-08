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
    public static double minSSE;
    private int numOut;

    public ANN(int numInp, int numOfLayers,
               int numNodesInHiddenLayers, int numOut, double minSSE) {
        this.numInp = numInp;
        this.numOut = numOut;
        this.numNodesInHiddenLayers = this.numNodesInHiddenLayers;
        ANN.minSSE = minSSE;
        subANNList = new ArrayList<SUB_ANN>();
        for (int i = 0; i < this.numOut; i++) {
            subANNList.add(new SUB_ANN(numInp, numOfLayers,
                                       numNodesInHiddenLayers));

        }

    }

    public ArrayList<SUB_ANN> Train_ANN(double[][] data) {
        for (int i = 0; i < this.numOut; i++) {
            this.subANNList.get(i).train_SUB_ANN(data, i);

        }
        return subANNList;
    }

    public double[][] Classify_ANN(double[][] data) {
        double[][] output2DArray = new double[data.length][this.numOut];
        double output;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < this.numOut; j++) {
                output = this.subANNList.get(j).Classify_SUB_ANN(data[i]);
                output2DArray[i][j] = output;

            }
        }
        return output2DArray;

    }

}

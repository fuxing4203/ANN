/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Spring 2017 *
  * Name: Haoyu Xiong and Jingya Wu
  * Date: Mar 3, 2017
  * Time: 8:53:25 PM *
  * Project: csci205_hw
  * Package: Assignment01
  * File: SUB_ANN
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
public class SUB_ANN {

    private ArrayList<HiddenLayer> hiddenLayerList;

    private OutputLayer outputLayer;

    private int numOfLayers;

    private int numOfLayerLayers;

    private int numIp;

    private int numNodesInHiddenLayers;

    public SUB_ANN(int numInp, int numOfLayers,
                   int numNodesInHiddenLayers) {
        System.out.println("Constructor");
        this.numOfLayers = numOfLayers;

        this.numOfLayerLayers = this.numOfLayers - 1;

        this.numIp = numInp;

        this.numNodesInHiddenLayers = numNodesInHiddenLayers;

        this.hiddenLayerList = new ArrayList<HiddenLayer>();

        if (numOfLayers > 2) {
            this.outputLayer = new OutputLayer(numNodesInHiddenLayers);
        }
        else {
            this.outputLayer = new OutputLayer(numIp);
        }

        int numPrevNodes = numInp;

        for (int i = 0; i < numOfLayers - 3; i++) {
            this.hiddenLayerList.add(new HiddenLayer(numPrevNodes,
                                                     numNodesInHiddenLayers));
            numPrevNodes = numNodesInHiddenLayers;

        }

        if (numOfLayers - 2 > 0) {

            this.hiddenLayerList.add(new HiddenLayer(numPrevNodes, 1));
        }
    }

    public double Feed_Forward_Train_SUB_ANN(
            double[] inputDataAtRowJ, int no) {
        double[] outputDataRow = inputDataAtRowJ;

        for (int z = 0; z < this.numOfLayerLayers - 1; z++) {

            outputDataRow = this.hiddenLayerList.get(z).classify_Layer(
                    outputDataRow);

        }
        double actualOutput = this.outputLayer.classify_OutputNode(
                outputDataRow);

        double expectedOutput = inputDataAtRowJ[this.numIp + no];
        return expectedOutput - actualOutput;
    }

    public void Back_Propagation_Train_SUB_ANN(double actual, double expected,
                                               double[] inputDataAtRowJ) {
        double[] outputDataRow;
        ArrayList<Perceptron> prevLayerNodes;
        double[] prevFgets;
        if (this.numOfLayerLayers - 1 > 0) {
            //train OutputLayer
            outputDataRow = this.outputLayer.train_OutputNode(
                    actual,
                    expected,
                    this.hiddenLayerList.get(
                            this.numOfLayerLayers - 2).getFnets());

            //between OutputLayer and firstHiddenLayer
            prevLayerNodes = this.outputLayer.getNodes();

            for (int z = this.numOfLayerLayers - 2; z > 0; z++) {
                outputDataRow = this.hiddenLayerList.get(
                        z).train_HiddenLayer(
                                outputDataRow,
                                prevLayerNodes,
                                this.hiddenLayerList.get(z - 1).getFnets()
                        );
                prevLayerNodes = this.hiddenLayerList.get(z).getNodes();

            }

            outputDataRow = this.hiddenLayerList.get(0).train_HiddenLayer(
                    outputDataRow, prevLayerNodes, inputDataAtRowJ);

        }
        else {
            this.outputLayer.train_OutputNode(actual,
                                              expected,
                                              inputDataAtRowJ
            );

        }

    }

    public void train_SUB_ANN(double[][] data, int no) {
        int row = data.length;
        double SSE;
        double error;
        double actualOutput;
        double expectedOutput;
        do {
            SSE = 0;

            for (int j = 0; j < row; j++) {

                error = Feed_Forward_Train_SUB_ANN(data[j], no);
                SSE += error;
                expectedOutput = data[j][this.numIp + no];
                actualOutput = expectedOutput + error;

                this.Back_Propagation_Train_SUB_ANN(actualOutput, expectedOutput,
                                                    data[j]);

            }

        } while (SSE >= ANN.minSSE);
    }

    public double Classify_SUB_ANN(double[] data) {
        double[] outputDataRow = data;

        for (int z = 0; z < this.numOfLayerLayers - 1; z++) {

            outputDataRow = this.hiddenLayerList.get(z).classify_Layer(
                    outputDataRow
            );

        }
        double actualOutput = this.outputLayer.classify_OutputNode(
                outputDataRow);

        return actualOutput;
    }

    public ArrayList<HiddenLayer> getHiddenLayerList() {
        return hiddenLayerList;
    }

    public void setHiddenLayerList(ArrayList<HiddenLayer> hiddenLayerList) {
        this.hiddenLayerList = hiddenLayerList;
    }

    public OutputLayer getOutputLayer() {
        return outputLayer;
    }

    public void setOutputLayer(OutputLayer outputLayer) {
        this.outputLayer = outputLayer;
    }

    public int getNumOfLayers() {
        return numOfLayers;
    }

    public void setNumOfLayers(int numOfLayers) {
        this.numOfLayers = numOfLayers;
    }

    public int getNumOfLayerLayers() {
        return numOfLayerLayers;
    }

    public void setNumOfLayerLayers(int numOfLayerLayers) {
        this.numOfLayerLayers = numOfLayerLayers;
    }

    public int getNumIp() {
        return numIp;
    }

    public void setNumIp(int numIp) {
        this.numIp = numIp;
    }

    public int getNumNodesInHiddenLayers() {
        return numNodesInHiddenLayers;
    }

    public void setNumNodesInHiddenLayers(int numNodesInHiddenLayers) {
        this.numNodesInHiddenLayers = numNodesInHiddenLayers;
    }

}

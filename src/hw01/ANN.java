/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Spring 2017 *
  * Name: Haoyu Xiong and Jingya Wu
  * Date: Mar 3, 2017
  * Time: 8:53:25 PM *
  * Project: csci205_hw
  * Package: Assignment01
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

    private ArrayList<HiddenLayer> hiddenLayerList;

    private InputLayer inputLayer;

    private OutputLayer outputLayer;

    private int numOfLayers;

    private int numOfLayerLayers;

    private int numIp;

    private int numOut;

    private static double minSSE = 0.001;

    private int numNodesInHiddenLayers;

    public ANN(int numInp, int numOut, int numOfLayers,
               int numNodesInHiddenLayers, double minSSE) {

        this.numOfLayers = numOfLayers;

        this.numOfLayerLayers = this.numOfLayers - 1;

        this.numOut = numOut;

        this.numIp = numInp;

        ANN.minSSE = minSSE;

        this.numNodesInHiddenLayers = numNodesInHiddenLayers;

        this.inputLayer = new InputLayer(numInp, numOut);

        this.hiddenLayerList = new ArrayList<HiddenLayer>();
        this.outputLayer = new OutputLayer(numNodesInHiddenLayers, numOut);

        int numPrevNodes = numInp;

        for (int i = 0; i < numOfLayers - 2; i++) {
            this.hiddenLayerList.add(new HiddenLayer(numPrevNodes,
                                                     numNodesInHiddenLayers));
            numPrevNodes = numNodesInHiddenLayers;

        }

        this.hiddenLayerList.add(new HiddenLayer(numPrevNodes, numOut));
    }

    public void train_ANN(double[][] data) {
        int row = data.length;
        int col = data[0].length;
        double SSE;
        double error;
        //double[] inputDataRow;
        double[] outputDataRow;
        double actualOutput;
        double expectedOutput;
        ArrayList<Perceptron> prevLayerNodes;

        for (int i = 0; i < this.numOut; i++) {

            SSE = 0;

            for (int j = 0; j < row; j++) {

                outputDataRow = data[j];

                for (int z = 0; z < this.numOfLayerLayers; z++) {

                    outputDataRow = this.hiddenLayerList.get(z).classify_Layer(
                            outputDataRow,
                            i + this.numIp);

                }
                actualOutput = this.outputLayer.classify_NoOfOutputNode(
                        outputDataRow, i);

                expectedOutput = data[j][this.numIp + i] - actualOutput;
                SSE += (expectedOutput - actualOutput);

                if (this.numOfLayerLayers - 1 > 0) {
                    //train OutputLayer
                    outputDataRow = this.outputLayer.train_NoOfOutputNode(
                            actualOutput,
                            expectedOutput,
                            this.hiddenLayerList.get(
                                    this.numOfLayerLayers - 2).getFnets(),
                            i);

                    //between OutputLayer and firstHiddenLayer
                    prevLayerNodes = new ArrayList<Perceptron>();
                    prevLayerNodes.add(this.outputLayer.getNodeAtNo(i));
                    outputDataRow = this.hiddenLayerList.get(
                            this.numOfLayerLayers - 2).train_HiddenLayer(
                                    outputDataRow,
                                    this.hiddenLayerList.get(z + 1).getNodes(),
                                    this.hiddenLayerList.get(z - 1).getFnets()
                            );

                    //train HiddenLayers
                    for (int z = this.numOfLayerLayers - 3; z > 0; z--) {

                        outputDataRow = this.hiddenLayerList.get(z).train_HiddenLayer(
                                outputDataRow,
                                this.hiddenLayerList.get(z + 1).getNodes(),
                                this.hiddenLayerList.get(z - 1).getFnets());

                    }
                }
                else {
                    this.outputLayer.train_NoOfOutputNode(actualOutput,
                                                          expectedOutput,
                                                          data[j],
                                                          i);

                }

            }

        }

    }

}

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
               int numNodesInHiddenLayers, int minSSE) {

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
                    this.outputLayer.train_NoOfOutputNode(actualOutput,
                                                          expectedOutput,
                                                          this.hiddenLayerList.get(
                                                                  this.numOfLayerLayers - 2).getFnets(),
                                                          i);
                }
                else {
                    this.outputLayer.train_NoOfOutputNode(actualOutput,
                                                          expectedOutput,
                                                          data[j],
                                                          i);

                }

                for (int z = 0; z < this.hiddenLayerList.size() - 1; z++) {

                }

            }

        }

    }

}

/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Spring 2017 *
  * Name: NAMES of team members
  * Date: Mar 5, 2017
  * Time: 10:13:10 PM *
  * Project: csci205_hw
  * Package: hw01
  * File: HiddenLayer
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
public class HiddenLayer extends Layer {

    private double[] fnets;

    /**
     *
     * @param numOfPrevNodes
     * @param numOfNodes
     */
    public HiddenLayer(int numOfPrevNodes, int numOfNodes) {
        super(numOfPrevNodes, numOfNodes);
        fnets = new double[numOfNodes];

    }

    public double[] getFnets() {
        return fnets;
    }

    public void setFnets(double[] fnets) {
        this.fnets = fnets;
    }

    public double[] train_HiddenLayer(double[] kLittleDeltas,
                                      ArrayList<Perceptron> prevLayerNodes,
                                      double[] prevFgets) {
        double[] returnLittleDelta = new double[prevLayerNodes.size()];
        double littleDelta;

        for (int i = 0; i < this.getNumOfNodes(); i++) {
            littleDelta = calculateLittleDeltaAtNoNode(i, this.getFnetsAtNoNode(
                                                       i), prevLayerNodes,
                                                       kLittleDeltas);
            this.getNodeAtNo(i).train_Perceptron(fnets, littleDelta);
            returnLittleDelta[i] = littleDelta;

        }

        return returnLittleDelta;

    }

    public double calculateLittleDeltaAtNoNode(int no, double fgets,
                                               ArrayList<Perceptron> prevLayerNodes,
                                               double[] kLittleDeltas) {
        double error = 0;
        for (int i = 0; i < prevLayerNodes.size(); i++) {
            error += (prevLayerNodes.get(i).getWeightAtIndex(no) * kLittleDeltas[i]);
        }
        return error * fgets * (1 - fgets);
    }

}

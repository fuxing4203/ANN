/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Spring 2017 *
  * Name: NAMES of team members
  * Date: Mar 6, 2017
  * Time: 12:37:32 AM *
  * Project: csci205_hw
  * Package: hw01
  * File: OutputLayer
  * Description:
  *
  * ****************************************
 */
package hw01;

/**
 *
 * @author haoyuxiong
 */
public class OutputLayer extends Layer {

    public OutputLayer(int numOfPrevNodes, int numOutputNodes) {

        super(numOfPrevNodes, numOutputNodes);

    }

    public double classify_NoOfOutputNode(double[] data, int no) {
        double fGets;
        fGets = this.getNodes().get(no).classify_Perceptron(data);
        return fGets;
    }

    public double calculateLittleDeltaOfOutputLayer(double expected,
                                                    double actual) {
        return actual * (1 - actual) * (expected - actual);
    }

    public double[] train_NoOfOutputNode(double expected, double actual,
                                         double[] fNets, int no) {
        double[] returnLittleDelta = new double[1];
        returnLittleDelta[0] = this.calculateLittleDeltaOfOutputLayer(expected,
                                                                      actual);
        for (int i = 0; i < this.getNodes().get(no).getWeights().size(); i++) {

        }

        return returnLittleDelta;
    }

    public double calculateDeltaWeight(double prevfnet, double littleDelta) {
        return Perceptron.alpha * prevfnet * littleDelta;

    }
}

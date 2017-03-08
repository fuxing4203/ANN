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

    /**
     *
     * @param numOfPrevNodes
     */
    public OutputLayer(int numOfPrevNodes) {

        super(numOfPrevNodes, 1);

    }

    /**
     *
     * @param data
     * @return
     */
    public double classify_OutputNode(double[] data) {
        double fGets;
        fGets = this.getNodeAtNo(0).classify_Perceptron(data);
        this.getfNets()[0] = fGets;
        return fGets;
    }

    /**
     *
     * @param expected
     * @param actual
     * @return
     */
    public double calculateLittleDeltaOfOutputLayer(double expected,
                                                    double actual) {
        return actual * (1 - actual) * (expected - actual);
    }

    /**
     *
     * @param actual
     * @param expected
     * @param fNets
     * @return
     */
    public double[] train_OutputNode(double actual, double expected,
                                     double[] fNets) {
        double[] returnLittleDelta = new double[1];
        double LittleDeltaOfOutputNode = this.calculateLittleDeltaOfOutputLayer(
                expected,
                actual);
        returnLittleDelta[0] = LittleDeltaOfOutputNode;
        this.getNodeAtNo(0).train_Perceptron(fNets,
                                             LittleDeltaOfOutputNode);
        //System.out.printf("%f, %f\n", expected, actual);
        return returnLittleDelta;
    }

}

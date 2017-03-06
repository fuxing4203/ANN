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

    public int[][] classify_HiddenLayer(double[] classifyData) {
        int row = classifyData.length;
        int outputResult[][] = new int[row][this.Nodes.size()];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < this.Nodes.size(); j++) {
                outputResult[i][j] = this.getNodes().get(j).classify_Perceptron(
                        classifyData);
            }
        }
        return outputResult;

    }
}

/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Spring 2017 *
  * Name: NAMES of team members
  * Date: Mar 3, 2017
  * Time: 9:31:02 PM *
  * Project: csci205_hw
  * Package: Assignment01
  * File: Perceptron
  * Description:
  *
  * ****************************************
 */
package hw01;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author haoyuxiong
 */
public class Perceptron {

    private ArrayList<Double> weights;
    final private static double alpha = 0.3;

    /**
     *
     * @param numInp
     */
    public Perceptron(int numInp) {

        this.weights = new ArrayList<Double>();
        Random randomDoubleGenerator = new Random();
        for (int i = 0; i < numInp + 1; i++) {
            this.weights.add(randomDoubleGenerator.nextDouble() - 0.5);

        }

    }

    /**
     *
     * @return
     */
    public ArrayList<Double> getWeights() {
        return weights;
    }

    /**
     *
     * @param weights
     */
    public void setWeights(ArrayList<Double> weights) {
        this.weights = weights;
    }

    public void train_Perceptron(int[] data1,
                                 int indexOfOutput) {
        double net = 1 * this.weights.get(0);
        for (int i = 0; i < this.weights.size() - 1; i++) {
            net += data1[i] * this.weights.get(i + 1);

        }
        double Error = data1[indexOfOutput] - step_function(net);
        this.weights.set(0, this.weights.get(0) + alpha * 1 * Error);
        for (int j = 1; j < this.weights.size(); j++) {
            this.weights.set(j,
                             this.weights.get(j) + alpha * data1[j - 1] * Error);

        }

    }

    public int classify_Perceptron(int[] classify_Data1) {
        double net = 1 * this.weights.get(0);
        for (int i = 0; i < this.weights.size() - 1; i++) {
            net += classify_Data1[i] * this.weights.get(i + 1);

        }
        return step_function(net);

    }

    public int step_function(double net) {
        if (net >= 0) {
            return 1;
        }
        else {
            return 0;
        }

    }

}

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
    private double theta;
    final static double alpha = 0.2;

    /**
     *
     * @param numInp
     */
    public Perceptron(int numInp) {

        this.weights = new ArrayList<Double>();
        Random randomDoubleGenerator = new Random();
        this.theta = randomDoubleGenerator.nextDouble() - 0.5;
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

    public double train_Perceptron(double[] data1,
                                   int indexOfOutput) {

        double fgets = classify_Perceptron(data1);
        double Error = data1[indexOfOutput] - fgets;
        this.weights.set(0, this.weights.get(0) + alpha * 1 * Error);
        for (int j = 1; j < this.weights.size(); j++) {
            this.weights.set(j,
                             this.weights.get(j) + alpha * data1[j - 1] * Error);

        }
        return Error;

    }

    public double classify_Perceptron(double[] classify_Data1) {
        double net = this.theta;
        for (int i = 0; i < this.weights.size(); i++) {
            net += classify_Data1[i] * this.weights.get(i);

        }
        return sigmoidal_activation_func(net);

    }

    public int step_function(double net) {
        if (net >= 0) {
            return 1;
        }
        else {
            return 0;
        }

    }

    public double sigmoidal_activation_func(double net) {

        return (double) 1 / (1 + Math.pow(Math.E, (-1) * net));

    }

    public void changeWeightAtIndex(double deltaWeight, int index) {

        this.getWeights().set(deltaWeight + this.getWeights().get(index), index);

    }

}

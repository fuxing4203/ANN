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
package Assignment01;

import java.util.ArrayList;

/**
 *
 * @author haoyuxiong
 */
public class ANN {

    private ArrayList<Perceptron> perceptronsList;

    private int layer;

    private int numIp;

    public ANN(int numInp, int numOut, int layer) {

        this.layer = layer;

        this.numIp = numInp;

        this.perceptronsList = new ArrayList<Perceptron>();
        for (int i = 0; i < numOut; i++) {
            this.perceptronsList.add(new Perceptron(numInp));

        }
    }

    public int getNumIp() {
        return numIp;
    }

    public void setNumIp(int numIp) {
        this.numIp = numIp;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public int getNumOfOutput() {
        return this.perceptronsList.size();
    }

    public ArrayList<Perceptron> getPerceptronsList() {
        return perceptronsList;
    }

    public void setPerceptronsList(ArrayList<Perceptron> perceptronsList) {
        this.perceptronsList = perceptronsList;
    }

    public void train_ANN(double[][] data) {
        int row = data.length;
        int col = data[0].length;

        for (int i = 0; i < row; i++) {

            for (int j = this.numIp; j < col; j++) {
                this.perceptronsList.get(j + 1 - col).train_Perceptron(data[i],
                                                                       j);
            }

        }

    }

    public double[][] classify_ANN(double[][] classifyData) {
        int row = classifyData.length;
        double outputResult[][] = new double[row][this.perceptronsList.size()];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < this.perceptronsList.size(); j++) {
                outputResult[i][j] = this.perceptronsList.get(j).classify_Perceptron(
                        classifyData[i]);
            }
        }
        return outputResult;

    }

}

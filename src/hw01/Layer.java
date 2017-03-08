/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Spring 2017 *
  * Name: NAMES of team members
  * Date: Mar 6, 2017
  * Time: 12:48:20 AM *
  * Project: csci205_hw
  * Package: hw01
  * File: Layer
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
public class Layer {

    private int numOfPrevNodes;
    private int numOfNodes;
    private ArrayList<Perceptron> Nodes;
    private double minSSE;
    private double[] fNets;

    public Layer(int numOfPrevNodes, int numOfNodes) {
        this.numOfNodes = numOfNodes;
        this.numOfPrevNodes = numOfPrevNodes;
        this.Nodes = new ArrayList<Perceptron>();
        for (int i = 0; i < numOfNodes; i++) {
            this.Nodes.add(new Perceptron(numOfPrevNodes));

        }
        fNets = new double[numOfNodes];

    }

    public double[] getfNets() {
        return fNets;
    }

    public double getFnetsAtNoNode(int index) {
        return this.fNets[index];
    }

    public void setfNets(double[] fNets) {
        this.fNets = fNets;
    }

    public int getNumOfPrevNodes() {
        return numOfPrevNodes;
    }

    public void setNumOfPrevNodes(int numOfPrevNodes) {
        this.numOfPrevNodes = numOfPrevNodes;
    }

    public int getNumOfNodes() {
        return numOfNodes;
    }

    public void setNumOfNodes(int numOfNodes) {
        this.numOfNodes = numOfNodes;
    }

    public ArrayList<Perceptron> getNodes() {
        return Nodes;
    }

    public void setNodes(ArrayList<Perceptron> Nodes) {
        this.Nodes = Nodes;
    }

    public Perceptron getNodeAtNo(int index) {
        return this.getNodes().get(index);

    }

    public double getMinSSE() {
        return minSSE;
    }

    public void setMinSSE(double minSSE) {
        this.minSSE = minSSE;
    }

    public double[] classify_Layer(double[] data
    ) {
        double fgets;

        for (int i = 0; i < this.numOfNodes; i++) {
            fgets = this.Nodes.get(i).classify_Perceptron(data);
            this.fNets[i] = fgets;
            //System.out.printf("fgets, %f\n", fgets);
        }
        return this.getfNets();

    }

}

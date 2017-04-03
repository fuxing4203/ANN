/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Spring 2017 *
  * Name: NAMES of team members
  * Date: Mar 31, 2017
  * Time: 8:55:33 PM *
  * Project: csci205_hw
  * Package: hw03.view
  * File: DesignController
  * Description:
  *
  * ****************************************
 */
package hw03.controller;

import hw03.model.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.NumberBinding;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

/**
 * FXML Controller class
 *
 * @author Jingya
 */
public class DesignController implements Initializable {

    private ANNModel theModel;
    private ANNTask theTask;
    @FXML
    private Button learnBtn;
    @FXML
    private Tab fileTab;
    @FXML
    private Tab configTab;
    @FXML
    private TextField numIN;
    @FXML
    private TextField numOUT;
    @FXML
    private TextField numNeurons;
    @FXML
    private TextField maxSSE;
    @FXML
    private TextField maxEpoch;
    @FXML
    private RadioButton actFuncRadio1;
    @FXML
    private RadioButton actFuncRadio2;
    @FXML
    private TextField alpha;
    @FXML
    private TextField momentum;
    @FXML
    private Button exitBtn;
    @FXML
    private Button classifyBtn;
    @FXML
    private Pane canvasPane;
    @FXML
    private Label currentSSE;
    @FXML
    private Label currentEpoch;
    @FXML
    private Button pauseBtn;
    @FXML
    private Button saveBtn;
    @FXML
    private TabPane tabPane;
    @FXML
    private Button fileUploadBtn;
    @FXML
    private Button applyConfigBtn;
    @FXML
    private Button applyMomentumBtn;

    private ArrayList<Circle> inputLayerNodes;
    private ArrayList<Circle> hiddenLayerNodes;
    private ArrayList<Circle> outputLayerNodes;
    private VBox inputLayer;
    private VBox hiddenLayer;
    private VBox outputLayer;
    private ArrayList<ArrayList<Label>> inputWeights;
    private ArrayList<ArrayList<Label>> outputWeights;

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void generateGraph(ActionEvent event) {
        theTask = new ANNTask(theModel);
        NumberBinding radBinding = canvasPane.heightProperty().divide(
                Math.max(
                        this.theModel.getANN().getNumInputs(),
                        this.theModel.getANN().getNumHidden()) * 2).add(
                -15);
        for (int i = 0; i < this.theModel.getANN().getNumInputs(); i++) {
            Circle c = new Circle(50);
            c.radiusProperty().bind(radBinding);
            inputLayerNodes.add(c);
        }
        inputLayer = new VBox(10);
        inputLayer.setAlignment(Pos.CENTER);
        inputLayer.setMinHeight(300);
        inputLayer.getChildren().addAll(inputLayerNodes);

        for (int i = 0; i < this.theModel.getANN().getNumHidden(); i++) {
            Circle c = new Circle(50);
            c.radiusProperty().bind(radBinding);
            hiddenLayerNodes.add(c);
        }
        hiddenLayer = new VBox(10);
        hiddenLayer.setAlignment(Pos.CENTER);
        hiddenLayer.setMinHeight(300);
        hiddenLayer.getChildren().addAll(inputLayerNodes);

        outputLayer = new VBox(10);
        outputLayer.setAlignment(Pos.CENTER);
        outputLayer.setMinHeight(300);
        for (int i = 0; i < this.theModel.getANN().getNumOutputs(); i++) {
            Circle c = new Circle(50);
            c.radiusProperty().bind(radBinding);
            outputLayerNodes.add(c);
        }
        outputLayer.getChildren().addAll(outputLayerNodes);

        inputWeights = new ArrayList();
        for (int i = 0; i < inputLayerNodes.size(); i++) {
            ArrayList<Label> labelOfWeights = new ArrayList();
            for (int j = 0; j < hiddenLayerNodes.size(); j++) {
                Line line = new Line();
                line.setStrokeWidth(3);
                updateLinePosition(line, inputLayerNodes.get(i),
                                   hiddenLayerNodes.get(j));
                canvasPane.getChildren().add(line);

                Label weight = new Label();
                weight.getTransforms().add(new Rotate(calcDegree(line)));
                weight.getTransforms().add(new Translate(line.getStartX(),
                                                         line.getStartY()));
                weight.setText(String.format(".4f",
                                             theModel.getANN().getEdgeConnections()[0].getEdges()[i][j]));
                labelOfWeights.add(weight);
                canvasPane.getChildren().add(weight);
            }
            inputWeights.add(labelOfWeights);
        }

        outputWeights = new ArrayList();
        for (int i = 0; i < hiddenLayerNodes.size(); i++) {
            ArrayList<Label> labelOfWeights = new ArrayList();
            for (int j = 0; j < outputLayerNodes.size(); j++) {
                Line line = new Line();
                line.setStrokeWidth(3);
                updateLinePosition(line, inputLayerNodes.get(i),
                                   outputLayerNodes.get(j));
                canvasPane.getChildren().add(line);

                Label weight = new Label();
                weight.getTransforms().add(new Rotate(calcDegree(line)));
                weight.getTransforms().add(new Translate(line.getStartX(),
                                                         line.getStartY()));
                weight.setText(String.format(".4f",
                                             theModel.getANN().getEdgeConnections()[1].getEdges()[i][j]));
                labelOfWeights.add(weight);
                canvasPane.getChildren().add(weight);
            }
            outputWeights.add(labelOfWeights);
        }

        Thread th = new Thread(theTask);
        th.setDaemon(true);
        th.start();
    }

    public double calcDegree(Line line) {
        double x = line.getEndX() - line.getStartX();
        double y = line.getEndY() - line.getStartY();
        return Math.tan(y / x);
    }

    private void updateLinePosition(Line line, Circle c1, Circle c2) {
        line.setStartX(c1.getCenterX() + c1.getRadius());
        line.setStartY(c1.getCenterY() + c1.getRadius());
        line.setEndX(c2.getCenterX() + c2.getRadius());
        line.setEndY(c2.getCenterY() + c2.getRadius());
    }

    public void updateData() {
        for (int i = 0; i < inputLayerNodes.size(); i++) {
            for (int j = 0; j < hiddenLayerNodes.size(); j++) {
                inputWeights.get(i).get(j).setText(String.format(".4f",
                                                                 theModel.getANN().getEdgeConnections()[0].getEdges()[i][j]));
            }
        }
        for (int i = 0; i < hiddenLayerNodes.size(); i++) {
            for (int j = 0; j < outputLayerNodes.size(); j++) {
                outputWeights.get(i).get(j).setText(String.format(".4f",
                                                                  theModel.getANN().getEdgeConnections()[1].getEdges()[i][j]));
            }
        }
    }

    public void setModel(ANNModel theModel) {
        this.theModel = theModel;
    }

    public void writeOutputs(ArrayList<ArrayList<Double>> resultList) {
        PrintWriter out;
        try {
            out = new PrintWriter("output.csv");
            for (int i = 0; i < resultList.size(); i++) {
                for (int j = 0; j < resultList.get(i).size(); j++) {
                    if (j != 0) {
                        out.print(", ");
                    }
                    out.printf("%d", Math.round(resultList.get(i).get(j)));
                }
                out.println();
            }
            out.close();
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException");
        }
    }

    class ANNTask extends Task<Void> {

        public ANNTask(ANNModel theModel) {
        }

        @Override
        protected Void call() throws Exception {
            // training show be running here
            if (isCancelled()) {
                updateMessage("Cancelled");
            }
            currentSSE.setText(String.format("%.3f",
                                             theModel.getANN().currSSE));
            //need to update current epoch, don't know how
            //currentEpoch.setText(String.format("%d",
            //                                   theModel.getANN().getSubANNList().get(
            //                                           0).getEpoch()));
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    DesignController.this.updateData();
                }
            });
            Thread.sleep(1);

            return null;
        }
    }
}

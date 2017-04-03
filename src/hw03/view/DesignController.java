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
package hw03.view;

import hw03.model.ANNModel;
import hw03.model.data.LabeledInstances;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Optional;
import javafx.application.Platform;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
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
public class DesignController {

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
    @FXML
    private TextField fileNameBox;

    private ArrayList<Circle> inputLayerNodes;
    private ArrayList<Circle> hiddenLayerNodes;
    private ArrayList<Circle> outputLayerNodes;
    private VBox inputLayer;
    private VBox hiddenLayer;
    private VBox outputLayer;
    private ArrayList<ArrayList<Label>> inputWeights;
    private ArrayList<ArrayList<Label>> outputWeights;
    private Dialog dialog;
    private LabeledInstances data;
    private ArrayList<ArrayList<Double>> resultList;
    private SimpleBooleanProperty ifPause;

    @FXML
    void initialize() {
        theTask = null;
        theModel.getSigmoidalChosen().bind(actFuncRadio1.selectedProperty());

    }

    @FXML
    void applyConfigBtn(ActionEvent event) throws FileNotFoundException {

        try {
            this.theModel.takeInAttributeCreate(Integer.parseInt(
                    this.numIN.getText()), Integer.parseInt(
                                                this.numOUT.getText()),
                                                Integer.parseInt(
                                                        this.numNeurons.getText()),
                                                Double.parseDouble(
                                                        this.maxSSE.getText()),
                                                Integer.parseInt(
                                                        this.maxEpoch.getText()),

                                                Integer.parseInt(
                                                        this.alpha.getText()),
                                                Integer.parseInt(
                                                        this.momentum.getText()));
        } catch (NumberFormatException numberFormatException) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect input!");
            alert.setHeaderText("Incorrect input specified!");
            alert.show();
        }

    }

    @FXML
    void fileUploadBtn(ActionEvent event) throws FileNotFoundException, IOException, ClassNotFoundException {

        try {
            this.theModel.takeInFileCreate(this.fileNameBox.getText());

        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input file not found!");
            alert.setHeaderText("Incorrect input specified!");
            alert.show();
        } catch (IOException e1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect input filename");
            alert.setHeaderText("Incorrect input specified!");
            alert.show();

        }

    }

    @FXML
    void learnBtn(ActionEvent event) throws FileNotFoundException {

        dialog = new TextInputDialog("Please input the file directory");
        dialog.setTitle("Learn");
        dialog.setHeaderText("Enter the directory");
        Optional<String> result = dialog.showAndWait();

        String entered = "None yet";
        if (result.isPresent()) {

            entered = result.get();
        }

        try {
            data = new LabeledInstances(entered, true, 2);
            theTask = new ANNTask(theModel, data);

            Thread th = new Thread(theTask);
            th.setDaemon(true);
            th.start();
        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect input filename");
            alert.setHeaderText("Incorrect input specified!");
            alert.show();

        }

    }

    @FXML
    void classifyBtn(ActionEvent event) throws FileNotFoundException {

        dialog = new TextInputDialog("Please input the file directory");
        dialog.setTitle("Classify");
        dialog.setHeaderText("Enter the directory");
        Optional<String> result = dialog.showAndWait();

        String entered = "None yet";
        if (result.isPresent()) {

            entered = result.get();
        }

        try {
            data = new LabeledInstances(entered, true, 2);
            resultList = theModel.getANN().classifyInstances(data);
            writeOutputs(resultList);

        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect input filename");
            alert.setHeaderText("Incorrect input specified!");
            alert.show();

        }

    }

    @FXML
    void generateGraph(ActionEvent event) {

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

        currentEpoch.textProperty().bind(theTask.messageProperty());
        currentSSE.textProperty().bind(theTask.valueProperty().asString("%.5f"));

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

    public void setModel(ANNModel theModel) {
        this.theModel = theModel;
    }

    class ANNTask extends Task<Double> {

        private ANNModel theModel;
        private LabeledInstances data;

        public ANNTask(ANNModel theModel, LabeledInstances data) {
            this.theModel = theModel;
            this.data = data;
        }

        @Override
        protected Double call() throws Exception {

            double totalError = Double.NaN;
            ArrayList<ArrayList<Double>> output = theModel.getANN().classifyInstances(
                    data);
            int epoch;
            for (epoch = 0; epoch < this.theModel.getANN().maxEpochs; epoch++) {
                if (isCancelled()) {
                    // change button
                    break;
                }
                totalError = theModel.getANN().learn(data, true, 1);
                if (epoch % 1000 == 0) {
                    output = theModel.getANN().classifyInstances(data);
                    double error = theModel.getANN().computeOutputError(data,
                                                                        output);

                    if (error <= theModel.getANN().errStopThresh) {
                        System.out.println("SUCCESS!");
                        break;
                    }

                }

                updateValue(totalError);
                updateMessage(String.format("%d", epoch));
                updateProgress(epoch, this.theModel.getANN().maxEpochs);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        DesignController.this.updateData();
                    }
                });
                Thread.sleep(1);

            }

            return totalError;
        }
    }
}

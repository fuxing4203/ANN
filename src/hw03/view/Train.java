/* *****************************************
  * CSCI205 - Software Engineering and Design
  * Spring 2017 *
  * Name: NAMES of team members
  * Date: Mar 27, 2017
  * Time: 1:30:51 PM *
  * Project: csci205_hw
  * Package: hw03.view
  * File: Train
  * Description:
  *
  * ****************************************
 */
package hw03.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Iris
 */
public class Train {

    private BorderPane root;
    private Button btnTrain;
    private VBox leftPane;
    private ToggleGroup rbs;
    private RadioButton newANN;
    private RadioButton selectConfig;

    public Train() {
        root = new BorderPane();
        root.setPrefWidth(300);
        root.setPadding(new Insets(15, 15, 15, 15));
        root.setPrefHeight(150);

        btnTrain = new Button();
        btnTrain.setText("Train!");

        // Left Pane with 2 radio buttons
        leftPane = new VBox(10);

        rbs = new ToggleGroup();
        newANN = new RadioButton("Create new ANN");
        selectConfig = new RadioButton("Select Config File");
        newANN.setToggleGroup(rbs);
        selectConfig.setToggleGroup(rbs);

        leftPane.getChildren().addAll(newANN, selectConfig);

        root.getChildren().addAll(leftPane);

    }
}

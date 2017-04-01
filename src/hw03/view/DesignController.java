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

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Jingya
 */
public class DesignController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}

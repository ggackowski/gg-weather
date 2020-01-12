package gg;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class ControllerStartScreen {

    @FXML
    private Button exitButton;
    @FXML
    private Button enterStationButton;

    public ControllerStartScreen() {

    }

    @FXML
    void initialize() {

    }

    @FXML
    void enterStationButton_onAction(ActionEvent e) throws java.io.IOException {
        Main.scenesManager.setScene("MPickStation");
    }

    @FXML
    void exitButton_onAction() {
        System.exit(0);
    }




}

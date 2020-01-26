package gg;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class ControllerStartScreen {

    @FXML
    private Button exitButton;
    @FXML
    private Button enterStationButton;
    @FXML
    private Button GraphButton;
    @FXML
    private Button UVButton;

    @FXML
    private Button plotButton;

    public ControllerStartScreen() {

    }

    @FXML
    void initialize() {

    }

    /**
     * Funkcja obsługująca przycisk przechodzący do sceny MPickStation
     */
    @FXML
    void enterStationButton_onAction() {
        Main.scenesManager.setScene("MPickStation");
    }

    /**
     * Funkcja obsługująca przycisk wychodzący z aplikacji
     */
    @FXML
    void exitButton_onAction() {
        System.exit(0);
    }

    /**
     * Funkcja obsługująca przycisk przechodzący do sceny MUV
     */
    @FXML
    void UVButton_onAction() {
        Main.scenesManager.setScene("MUV");
    }

    /**
     * Funkcja obsługująca przycisk przechodzący do sceny MGraph
     */
    @FXML
    void GraphButton_onAction() {
        Main.scenesManager.setScene("MGraph");
    }

    /**
     * Funkcja obsługująca przycisk przechodzący do sceny MPlot
     */
    @FXML
    void plotButton_onAction() { Main.scenesManager.setScene("MPlot"); }
}

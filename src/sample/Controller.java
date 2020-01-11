package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;



public class Controller {

    @FXML
            private Button exitButton;

    public Controller() {
        System.out.println("dziala");
    }

    @FXML
    void initialize() {
        System.out.println("init");
        if (exitButton.isPressed()) System.exit(0);
    }



}

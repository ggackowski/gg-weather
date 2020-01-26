package gg;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.LinkedList;
import java.util.List;

public class ControllerMGraphScreen {
    Image img;
    @FXML
    private TextField TextField;
    @FXML
    private ImageView map;


    void parseStations() throws java.io.IOException {
        String[] text = TextField.getCharacters().toString().split(", *");
        String imgsrc = Ow_api.INSTANCE.getMap(text[0], text[1], text[2], text[3]);
        img = new Image(imgsrc);
        map.setImage(img);
        System.out.println(img.getHeight());
        System.out.println(map.getFitHeight());

    }
    @FXML
    void actionBack() throws java.io.IOException{
        Main.scenesManager.setScene("Start");
    }

    @FXML
    void actionNext() throws java.io.IOException{
        parseStations();

    }
}

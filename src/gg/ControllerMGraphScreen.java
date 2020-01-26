package gg;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *  Kontroler do widoku wyświetlającego mapę
 */
public class ControllerMGraphScreen {
    Image img;
    @FXML
    private TextField TextField;
    @FXML
    private ImageView map;


    /**
     *  Funkcja parsująca wprowadzone informacje o stacjach pomiarowych.
     */
    void parseStations() {
        String[] text = TextField.getCharacters().toString().split(", *");
            String imgsrc = Ow_api.INSTANCE.getMap(text[0], text[1], text[2], text[3]);
            System.out.println(imgsrc);
                img = new Image(imgsrc);
                map.setImage(img);
                System.out.println(img.getHeight());
                System.out.println(map.getFitHeight());
            }

    /**
     *  Funkcja obsługująca przycisk powrotu do ekranu głównego
     */
    @FXML
    void actionBack() {
        Main.scenesManager.setScene("Start");
    }

    /**
     * Funkcja obsługująca przycisk "Next"
     */
    @FXML
    void actionNext() {
        parseStations();

    }
}

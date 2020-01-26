package gg;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.LinkedList;
import java.util.List;

public class ControllerMUVScreen {
    List<String> stations;

    @FXML
            private TextField stationTextField;
    @FXML
        private Text test;
    @FXML
        private Text resultText;


    /**
     * Funkcja łącząca się z API i wyświetlająca informacje na ekranie.
     */
    void parseStations() {
       List<UV> uvs = new LinkedList<>();
        String[] text = stationTextField.getCharacters().toString().split(", *");
        System.out.println(text[0] + text[1] + text[2] + text[3] + text[4]);
       for (int i = 0; i + 4 < text.length; i += 5) {
           UV[] arr = Ow_api.INSTANCE.getUV(text[i], text[i + 1], text[i + 2], text[i + 3], text[i + 4]);
           for (UV uv : arr)
               uvs.add(uv);
       }
       UV[] result = uvs.toArray(new UV[0]);
       float uv_min = result[0].value;
       int uv_min_i = 0;
       float uv_max = result[0].value;
       int uv_max_i = 0;
       for (int i = 0; i < result.length; ++i) {
           if (result[i].value > uv_max) {
               uv_max_i = i;
               uv_max = result[i].value;
           }
           if (result[i].value < uv_min) {
               uv_min_i = i;
               uv_min = result[i].value;
           }
       }
       resultText.setText("The biggest UV is in: " + result[uv_max_i].lat + " " + result[uv_max_i].lon + " on " + result[uv_max_i].date_iso + " and equals " + result[uv_max_i].value
        + "The lowest UV is in: " + result[uv_min_i].lat + " " + result[uv_min_i].lon + " on " + result[uv_min_i].date_iso + " and equals " + result[uv_min_i].value);

    }

    /**
     * Funkcja obsługująca przycisk powracający do ekranu początkowego
     */
    @FXML
    void actionBack() {
        Main.scenesManager.setScene("Start");
    }

    /**
     * Funkcja obsługująca przycisk "Next" wywołuje funkcję parsującą podany tekst
     */
    @FXML
    void actionNext() {
        parseStations();

    }
}




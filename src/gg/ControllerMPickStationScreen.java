package gg;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class ControllerMPickStationScreen {

    @FXML
    private Button backButton;
    @FXML
    private TextField stationTextField;
    @FXML
    private  Button nextButton;
    @FXML
    private Text resultText;

    private Weather weather;
    private String stationName;

    /**
     *  Kontroler widoku wyboru stacji pomiarowej
     */
    public ControllerMPickStationScreen() {
    }

    /**
     * Funkcja obsługująca przycisk powrotu do ekranu początkowego
     */
    @FXML
    void actionBack() {
       Main.scenesManager.setScene("Start");
    }


    /**
     * Funkcja obsługująca przycisk "Next". Laczy się z API
     * i wyświetla informacje o pogodzie w danym miejscu
     */
    @FXML
    void actionNext() {
        stationName = stationTextField.getCharacters().toString();
        weather = Ow_api.INSTANCE.getActualWeather(stationName);
        if (weather == null) {
            resultText.setText("Error");
        }
        else {
            resultText.setText("Actual weather in " + stationName + "\nTemperature " + String.format("%.2f", (weather.temperature - 273.15)) + "'C\nShort weather description " + weather.description
                    + "\nHumidity " + weather.humidity + "\nPressure " + weather.pressure + "\nWind speed " + weather.wind + "\nClouds " + weather.clouds);
            resultText.setVisible(true);
        }
    }
}

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
    Ow_api api;

    public ControllerMPickStationScreen() {
        api = new Ow_api();
    }

    @FXML
    void actionBack() throws java.io.IOException{
       Main.scenesManager.setScene("Start");
    }



    @FXML
    void actionNext() throws  java.io.IOException {
        stationName = stationTextField.getCharacters().toString();
        weather = api.getActualWeather(stationName);
        System.out.println("Actual weather in " + stationName + "\nTemperature " + String.format("%.2f",(weather.temperature - 273.15)) + "'C\nShort weather description " + weather.description
                + "\nHumidity " + weather.humidity + "\nPressure " + weather.pressure + "\nWind speed " +weather.wind + "\nClouds " + weather.clouds );
        resultText.setText("Actual weather in " + stationName + "\nTemperature " + String.format("%.2f", (weather.temperature - 273.15)) + "'C\nShort weather description " + weather.description
        + "\nHumidity " + weather.humidity + "\nPressure " + weather.pressure + "\nWind speed " +weather.wind + "\nClouds " + weather.clouds );
        resultText.setVisible(true);

    }
}

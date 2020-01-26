/**
 * Grzegorz Gackowski 2020
 */

package gg;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Aplikacja pogodowa korzystająca z API  OpenWeatherMap
 * Główna klasa od której rozpoczyna się wykonywanie aplikacji
 *
 * @author Grzegorz Gackowski
 */

public class Main extends Application {
    static ScenesManagerSingleton scenesManager;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Cache cache = new Cache();
        scenesManager = ScenesManagerSingleton.getInstance(primaryStage);
        primaryStage.setTitle("GG Weather");
        scenesManager.setScene("Start");
    }


    public static void main(String[] args) {
        launch(args);
    }
}

package gg;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    static ScenesManagerSingleton scenesManager;
    @Override
    public void start(Stage primaryStage) throws Exception{
      //  Ow_api api = new Ow_api();
      //  Gson gson = new Gson();
      //  Weather response = api.getActualWeather("London");
      //  System.out.println(response.temperature);
      ////  System.out.println(response.description);
     //   System.out.println(response.pressure);
      //  System.out.println(response.humidity);
      ////  System.out.println(response.wind);
       // System.out.println(response.clouds);
       scenesManager = ScenesManagerSingleton.getInstance(primaryStage);
       primaryStage.setTitle("GG Weather");
       scenesManager.setScene("Start");
    }


    public static void main(String[] args) {
        launch(args);
    }
}

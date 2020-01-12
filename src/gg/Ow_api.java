package gg;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import java.net.URL;
import java.time.LocalTime;
import java.util.Date;
import java.util.Scanner;

public class Ow_api implements IAPI {
    static int numberOfCalls;
    static LocalTime time;
    String APIkey =  "cd63b7543809bce5009d0e5b9f73053c";


    public Ow_api() {
        numberOfCalls = 0;
        time = LocalTime.now();



    }
    public void connect() {
        if (updateAndCheck()) {
            System.out.println("lacze sie " + numberOfCalls);
        }

    }

    public Weather getActualWeather(String stationName) throws  java.io.IOException {
        if (updateAndCheck()) {
            String ask = "https://api.openweathermap.org/data/2.5/weather?q=";
            try {
                URL url = new URL(ask + stationName + "&APPID=" + APIkey);
                System.out.println("I did query the API now; " + (49 - numberOfCalls) + " calls left" );
                String out = new Scanner(url.openStream(), "UTF-8").useDelimiter("\\A").next();
                JsonParser jsonParser = new JsonParser();
                Double temperature = jsonParser.parse(out)
                        .getAsJsonObject().get("main")
                        .getAsJsonObject().get("temp").getAsDouble();
                String description = jsonParser.parse(out).getAsJsonObject()
                        .getAsJsonArray("weather").get(0)
                        .getAsJsonObject().get("description").getAsString();
                int pressure = jsonParser.parse(out)
                        .getAsJsonObject().get("main")
                        .getAsJsonObject().get("pressure").getAsInt();
                int humidity = jsonParser.parse(out)
                        .getAsJsonObject().get("main")
                        .getAsJsonObject().get("humidity").getAsInt();
                Double wind = jsonParser.parse(out)
                        .getAsJsonObject().get("wind")
                        .getAsJsonObject().get("speed").getAsDouble();
                int clouds = jsonParser.parse(out)
                        .getAsJsonObject().get("clouds")
                        .getAsJsonObject().get("all").getAsInt();
                Weather w = new Weather(temperature, description, pressure, humidity, clouds, wind);
                return w;
            } catch(java.net.MalformedURLException e) {
                System.out.println("err with url " + e.getLocalizedMessage());
            }
        }
        return null;



    }

    private void updateCounter() {
        if (time.getMinute() < LocalTime.now().getMinute()) {
            time = LocalTime.now();
            numberOfCalls = 0;
        }
        else numberOfCalls++;
    }

    private boolean notTooMuch() {
        return numberOfCalls < 50;
    }

    private boolean updateAndCheck() {
        updateCounter();
        return notTooMuch();
    }
}
